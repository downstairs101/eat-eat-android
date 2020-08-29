import java.io.File

object Release {
    val keystoreFile = getKeystoreFile("RELEASE_KEYSTORE_FILE_PATH")
    val keystorePass = getKeystorePass("RELEASE_KEYSTORE_PASS")
    val keyAlias = getKeystoreAlias("RELEASE_KEY_ALIAS")
    val keyPass = getKeyPass("RELEASE_KEY_PASS")
}

object Development {
    val keystoreFile = getKeystoreFile("DEV_KEYSTORE_FILE_PATH")
    val keystorePass = getKeystorePass("DEV_KEYSTORE_PASS")
    val keyAlias = getKeystoreAlias("DEV_KEY_ALIAS")
    val keyPass = getKeyPass("DEV_KEY_PASS")
}

private fun getKeystoreFile(variable: String): File {

    val path = System.getProperty(variable) ?: "${System.getProperty("HOME")}/.android/debug.keystore"

    if (path.isNullOrBlank()) {
        throw KeystoreNotFound(path)
    }

    return File(path)
}

private fun getKeystorePass(variable: String): String {
    return System.getenv(variable) ?: "android"
}

private fun getKeystoreAlias(variable: String): String {
    return System.getenv(variable) ?: "androiddebugkey"

}

private fun getKeyPass(variable: String): String {
    return System.getenv(variable) ?: "android"
}


class KeystoreNotFound(filePath: String?) :
    RuntimeException("Keystore file not found for path $filePath")