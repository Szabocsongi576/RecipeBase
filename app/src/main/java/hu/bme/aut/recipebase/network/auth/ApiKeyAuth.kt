package hu.bme.aut.recipebase.network.auth

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException

class ApiKeyAuth(val location: String, val paramName: String) : Interceptor {
    var apiKey: String? = null
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val paramValue: String
        var request: Request = chain.request()
        if ("query" == location) {
            var newQuery: String = request.url().uri().getQuery()
            paramValue = "$paramName=$apiKey"
            if (newQuery == null) {
                newQuery = paramValue
            } else {
                newQuery += "&$paramValue"
            }
            val newUri: URI
            newUri = try {
                URI(
                    request.url().uri().getScheme(), request.url().uri().getAuthority(),
                    request.url().uri().getPath(), newQuery, request.url().uri().getFragment()
                )
            } catch (e: URISyntaxException) {
                throw IOException(e)
            }
            request = request.newBuilder().url(newUri.toURL()).build()
        } else if ("header" == location) {
            request = request.newBuilder()
                .addHeader(paramName, apiKey)
                .build()
        }
        return chain.proceed(request)
    }
}