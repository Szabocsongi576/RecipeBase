package hu.bme.aut.recipebase.network.auth

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.apache.oltu.oauth2.client.OAuthClient
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest
import org.apache.oltu.oauth2.client.request.OAuthClientRequest
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse
import org.apache.oltu.oauth2.common.exception.OAuthProblemException
import org.apache.oltu.oauth2.common.exception.OAuthSystemException
import org.apache.oltu.oauth2.common.message.types.GrantType
import org.apache.oltu.oauth2.common.token.BasicOAuthToken
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection

class OAuth(client: OkHttpClient, requestBuilder: OAuthClientRequest.TokenRequestBuilder) :
    Interceptor {
    interface AccessTokenListener {
        fun notify(token: BasicOAuthToken?)
    }

    @get:Synchronized
    @set:Synchronized
    @Volatile
    var accessToken: String? = null
    private val oauthClient: OAuthClient
    private var tokenRequestBuilder: OAuthClientRequest.TokenRequestBuilder
    private var authenticationRequestBuilder: OAuthClientRequest.AuthenticationRequestBuilder? = null
    private var accessTokenListener: AccessTokenListener? = null

    constructor(requestBuilder: OAuthClientRequest.TokenRequestBuilder) : this(OkHttpClient(), requestBuilder) {}
    constructor(
        flow: OAuthFlow?,
        authorizationUrl: String?,
        tokenUrl: String?,
        scopes: String?
    ) : this(OAuthClientRequest.tokenLocation(tokenUrl).setScope(scopes)) {
        setFlow(flow)
        authenticationRequestBuilder = OAuthClientRequest.authorizationLocation(authorizationUrl)
    }

    private fun setFlow(flow: OAuthFlow?) {
        when (flow) {
            OAuthFlow.accessCode, OAuthFlow.implicit -> tokenRequestBuilder.setGrantType(GrantType.AUTHORIZATION_CODE)
            OAuthFlow.password -> tokenRequestBuilder.setGrantType(GrantType.PASSWORD)
            OAuthFlow.application -> tokenRequestBuilder.setGrantType(GrantType.CLIENT_CREDENTIALS)
            else -> {}
        }
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return retryingIntercept(chain, true)
    }

    @Throws(IOException::class)
    fun retryingIntercept(
        chain: Interceptor.Chain,
        updateTokenAndRetryOnAuthorizationFailure: Boolean
    ): Response {
        val request: Request = chain.request()

        // If the request already have an authorization (eg. Basic auth), do nothing
        if (request.header("Authorization") != null) {
            return chain.proceed(request)
        }

        // If first time, get the token
        val oAuthRequest: OAuthClientRequest
        if (accessToken == null) {
            updateAccessToken(null)
        }
        return if (accessToken != null) {
            // Build the request
            val rb: Request.Builder = request.newBuilder()
            val requestAccessToken: String = accessToken as String
            oAuthRequest = try {
                OAuthBearerClientRequest(request.url.toString())
                    .setAccessToken(requestAccessToken)
                    .buildHeaderMessage()
            } catch (e: OAuthSystemException) {
                throw IOException(e)
            }
            for ((key, value) in oAuthRequest.headers) {
                rb.addHeader(key, value)
            }
            rb.url(oAuthRequest.locationUri)

            //Execute the request
            val response: Response = chain.proceed(rb.build())

            // 401/403 most likely indicates that access token has expired. Unless it happens two times in a row.
            if ((response.code == HttpURLConnection.HTTP_UNAUTHORIZED || response.code == HttpURLConnection.HTTP_FORBIDDEN) && updateTokenAndRetryOnAuthorizationFailure) {
                try {
                    if (updateAccessToken(requestAccessToken)) {
                        response.body?.close()
                        return retryingIntercept(chain, false)
                    }
                } catch (e: Exception) {
                    response.body?.close()
                    throw e
                }
            }
            response
        } else {
            chain.proceed(chain.request())
        }
    }

    /*
     * Returns true if the access token has been updated
     */
    @Synchronized
    @Throws(IOException::class)
    fun updateAccessToken(requestAccessToken: String?): Boolean {
        return if (accessToken == null || accessToken == requestAccessToken) {
            try {
                val accessTokenResponse: OAuthJSONAccessTokenResponse = oauthClient.accessToken(
                    tokenRequestBuilder.buildBodyMessage()
                )
                if (accessTokenResponse.accessToken != null) {
                    accessToken = accessTokenResponse.accessToken
                    if (accessTokenListener != null) {
                        accessTokenListener!!.notify(accessTokenResponse.oAuthToken as BasicOAuthToken)
                    }
                    accessToken != requestAccessToken
                } else {
                    false
                }
            } catch (e: OAuthSystemException) {
                throw IOException(e)
            } catch (e: OAuthProblemException) {
                throw IOException(e)
            }
        } else true
    }

    fun registerAccessTokenListener(accessTokenListener: AccessTokenListener?) {
        this.accessTokenListener = accessTokenListener
    }

    fun getTokenRequestBuilder(): OAuthClientRequest.TokenRequestBuilder {
        return tokenRequestBuilder
    }

    fun setTokenRequestBuilder(tokenRequestBuilder: OAuthClientRequest.TokenRequestBuilder) {
        this.tokenRequestBuilder = tokenRequestBuilder
    }

    fun getAuthenticationRequestBuilder(): OAuthClientRequest.AuthenticationRequestBuilder? {
        return authenticationRequestBuilder
    }

    fun setAuthenticationRequestBuilder(authenticationRequestBuilder: OAuthClientRequest.AuthenticationRequestBuilder?) {
        this.authenticationRequestBuilder = authenticationRequestBuilder
    }

    init {
        oauthClient = OAuthClient(OAuthOkHttpClient(client))
        tokenRequestBuilder = requestBuilder
    }
}