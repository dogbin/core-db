package dogbin.db.model.user

import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.XdEnumEntity
import kotlinx.dnq.enum.XdEnumEntityType
import kotlinx.dnq.xdBooleanProp

/**
 * Special roles which can be assigned to a user
 */
class UserRole(entity: Entity) : XdEnumEntity(entity) {
    companion object : XdEnumEntityType<UserRole>() {
        /**
         * A System User / Bot (most notably the "dogbin" user used as author for the about page)
         */
        val SYSTEM by enumField {
            noSignin = true
        }
        /**
         * Administrators have full edit rights on all pastes and access to the (upcoming) dashboard
         */
        val ADMIN by enumField {
            canAccessModOptions = true
            canAccessAdminOptions = true
        }
        /**
         * Moderators can delete reported content and have limited access to the dashboard
         */
        val MOD by enumField {
            canAccessModOptions = true
        }
    }

    var canAccessModOptions by xdBooleanProp()
    var canAccessAdminOptions by xdBooleanProp()
    var noSignin by xdBooleanProp()
}