package dogbin.db.model.user

import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.XdEnumEntity
import kotlinx.dnq.enum.XdEnumEntityType
import kotlinx.dnq.xdBooleanProp
import kotlinx.dnq.xdStringProp

class UserState(entity: Entity) : XdEnumEntity(entity) {
    companion object : XdEnumEntityType<UserState>() {
        /**
         * Every user is initially an anonymous user before signing up. All pastes from the same cookie are collected in one account
         * so all your content is already there if you eventually decide to sign up
         */
        val ANONYMOUS by enumField {
            usernameOverride = "Anonymous"
            canSignin = true
        }
        /**
         * The normal state a user is in after signing up
         */
        val ACTIVE by enumField {
            requiresPassword = true
            canSignin = true
        }
        /**
         * The state a user switches to after being banned
         */
        val INACTIVE by enumField {
            usernameOverride = "[deleted]"
            requiresPassword = true
        }
    }

    var usernameOverride by xdStringProp()
    var requiresPassword by xdBooleanProp()
    var canSignin by xdBooleanProp()
}