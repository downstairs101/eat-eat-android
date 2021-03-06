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
    val home =  System.getProperty("user.home")
    val path = System.getenv(variable) ?: "$home/.android/debug.keystore"
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