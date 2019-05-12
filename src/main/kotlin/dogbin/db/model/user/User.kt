package dogbin.db.model.user

import dogbin.db.util.Password
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.creator.findOrNew
import kotlinx.dnq.simple.requireIf
import java.util.*

class User(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<User>() {
        fun newAnon() = new {
            username = UUID.randomUUID().toString()
            state = UserState.ANONYMOUS
        }

        fun findOrNewSystem(username: String) = findOrNew {
            this.username = username
            state = UserState.ACTIVE
            role = UserRole.SYSTEM
        }
    }

    var username by xdRequiredStringProp(unique = true, trimmed = true)
    private var password by xdStringProp {
        requireIf { state.requiresPassword && role?.noSignin != true }
    }
    var role by xdLink0_1(UserRole)
    var state by xdLink1(UserState)

    val displayName get() = state.usernameOverride ?: username

    fun setPw(password: String) {
        this.password = Password.hash(password)
    }

    fun checkPw(candidate: String) = password != null && Password.verify(candidate, password!!).verified
}