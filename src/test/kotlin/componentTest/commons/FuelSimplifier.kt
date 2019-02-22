package componentTest.commons

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Response

/**
 * A simplifier of [Fuel] lib methods usage where [_baseUrl] is the root url path to any routes where tests are desired.
 * A treatment for use [_baseUrl] with or without '/' at the end of string is made
 */
data class FuelSimplifier(private val _baseUrl: String) {
    val baseUrl: String
        get() {
            if (_baseUrl.last() == '/') {
                return _baseUrl.take(_baseUrl.length - 1)
            }

            return _baseUrl
        }

    /**
     * Mounts [urlComplete] to call [Fuel.get] internally and retrieves the [Response]
     */
    fun get(urlComplement: String): Response {
        return Fuel.get(this.urlComplete(urlComplement)).response()
            .let { (_, response, _) -> response }
    }

    /**
     * Mounts [urlComplete] to call [Fuel.post] internally and retrieves the [Response]
     */
    fun post(urlComplement: String): Response {
        return Fuel.post(this.urlComplete(urlComplement)).response()
            .let { (_, response, _) -> response }
    }

    /**
     * Mounts [urlComplete] to call [Fuel.patch] internally with the request body contained in [requestBodyFixtureFile]
     *  and retrieves the [Response]
     */
    fun patch(urlComplement: String, requestBodyFixtureFile: String? = null): Response {
        return Fuel.patch(this.urlComplete(urlComplement))
                .body(requestBodyFixtureFile?.let { getJsonRequestBody(it) } ?: "")
                .response()
                .let { (_, response, _) -> response }
    }

    /**
     * Mounts [urlComplete] to call [Fuel.post] internally with the request body contained in [requestBodyFixtureFile]
     *  and retrieves the [Response]
     */
    fun post(urlComplement: String, requestBodyFixtureFile: String): Response {
        return Fuel.post(this.urlComplete(urlComplement))
                .body(getJsonRequestBody(requestBodyFixtureFile))
                .response()
                .let { (_, response, _) -> response }
    }

    /**
     * Junction [baseUrl] and [urlComplement] to define complete url
     * A treatment for use [urlComplement] with or without '/' at the end of string is made
     */
    private fun urlComplete(urlComplement: String): String {
        return if (urlComplement.first() == '/') "${this.baseUrl}$urlComplement" else "${this.baseUrl}/$urlComplement"
    }
}
