package dogbin.db.util

import at.favre.lib.crypto.bcrypt.BCrypt

object Password {
    fun hash(password: String) = BCrypt.withDefaults().hashToString(12, password.toCharArray())
    fun verify(password: String, hash: String) = BCrypt.verifyer().verify(password.toCharArray(), hash.toCharArray())
}
