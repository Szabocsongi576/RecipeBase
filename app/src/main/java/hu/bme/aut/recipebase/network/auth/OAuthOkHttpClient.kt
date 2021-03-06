package hu.bme.aut.recipebase.network.auth

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.apache.oltu.oauth2.client.HttpClient
import org.apache.oltu.oauth2.client.request.OAuthClientRequest
import org.apache.oltu.oauth2.client.response.OAuthClientResponse
import org.apache.oltu.oauth2.client.response.OAuthClientResponseFactory
import org.apache.oltu.oauth2.common.exception.OAuthProblemException
import org.apache.oltu.oauth2.common.exception.OAuthSystemException
import java.io.IOException

class OAuthOkHttpClient : HttpClient {
    private var client: OkHttpClient

    constructor() {
        client = OkHttpClient()
    }

    constructor(client: OkHttpClient) {
        this.client = client
    }

    @Throws(OAuthSystemException::class, OAuthProblemException::class)
    override fun <T : OAuthClientResponse?> execute(
        request: OAuthClientRequest,
        headers: Map<String, String?>?,
        requestMethod: String?,
        responseClass: Class<T>?
    ): T {
        var mediaType: MediaType? = "application/json".toMediaTypeOrNull()
        val requestBuilder: Request.Builder = Request.Builder().url(request.locationUri)
        if (headers != null) {
            for ((key, value) in headers) {
                if (key.equals("Content-Type", ignoreCase = true)) {
                    mediaType = value?.toMediaTypeOrNull()
                } else {
                    if (value != null) {
                        requestBuilder.addHeader(key, value)
                    }
                }
            }
        }
        val body: RequestBody? = if (request.body != null) request.body
            .toRequestBody(mediaType) else null
        if (requestMethod != null) {
            requestBuilder.method(requestMethod, body)
        }
        return try {
            val response: Response = client.newCall(requestBuilder.build()).execute()
            OAuthClientResponseFactory.createCustomResponse(
                response.body!!.string(),
                response.body!!.contentType().toString(),
                response.code,
                responseClass
            )
        } catch (e: IOException) {
            throw OAuthSystemException(e)
        }
    }

    override fun shutdown() {
        // Nothing to do here
    }
}