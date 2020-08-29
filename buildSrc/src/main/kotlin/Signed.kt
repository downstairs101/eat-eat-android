import java.io.File

object Release {
    val keystoreFile = File(System.getenv("RELEASE_KEYSTORE_FILE_PATH") ?: "")
    val keystorePass: String = System.getenv("RELEASE_KEYSTORE_PASS")
    val keyAlias: String = System.getenv("RELEASE_KEY_ALIAS")
    val keyPass: String = System.getenv("RELEASE_KEY_PASS")

}

object Development {
    val keystoreFile = File(System.getenv("DEV_KEYSTORE_FILE_PATH") ?: "")
    val keystorePass: String = System.getenv("DEV_KEYSTORE_PASS")
    val keyAlias: String = System.getenv("DEV_KEY_ALIAS")
    val keyPass: String = System.getenv("DEV_KEY_PASS")
}