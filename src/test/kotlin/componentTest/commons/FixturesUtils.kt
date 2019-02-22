package componentTest.commons

import org.json.JSONObject

private const val DEFAULT_RESPONSE_BASE_DIR = "/fixtures/responses/"
private const val DEFAULT_REQUEST_BASE_DIR = "/fixtures/requests/"

/**
 * Converts multiline strings into string compatible with khttp response.
 */
private fun String.toJsonString() = JSONObject(this).toString()

/**
 * Retrieves the content of resource that contains a response in any format.
 *
 * @param responseIdentifier the name of the file that contains the response.
 * @param baseDir the directory where the response is in.
 * @return the String with resource content.
 */
fun getFixture(responseIdentifier : String, baseDir : String) =
    System::class.java.getResource(
        "$baseDir$responseIdentifier"
    ).readText()

/**
 * Retrieves JSON responses and parses into a compatible format with khttp response.
 *
 * @param responseIdentifier the name of the file that contains the JSON response (without the .json extension).
 * @param baseDir the directory where the response is in.
 * @return the JSON string.
 */
fun getJsonResponse(responseIdentifier : String, baseDir : String = DEFAULT_RESPONSE_BASE_DIR) = getFixture(
        "$responseIdentifier.json", baseDir
).toJsonString()

/**
 * Retrieves JSON request
 *
 * @param requestIdentifier the name of the file that contains the JSON request (without the .json extension).
 * @param baseDir the directory where the request is in.
 * @return the JSON string.
 */
fun getJsonRequestBody(requestIdentifier : String, baseDir : String = DEFAULT_REQUEST_BASE_DIR) = getFixture(
        "$requestIdentifier.json", baseDir
).toJsonString()
