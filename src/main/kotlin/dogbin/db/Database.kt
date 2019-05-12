package dogbin.db

import dogbin.db.model.user.User
import jetbrains.exodus.database.TransientEntityStore
import kotlinx.dnq.XdModel
import kotlinx.dnq.store.container.StaticStoreContainer
import kotlinx.dnq.util.initMetaData
import java.io.File

class Database private constructor(location: File, environment: String) {
    val store: TransientEntityStore

    init {
        XdModel.scanJavaClasspath()

        store = StaticStoreContainer.init(
            dbFolder = location,
            environmentName = "dogbin-$environment"
        )

        initMetaData(XdModel.hierarchy, store)

        store.transactional {
            // Create "dogbin" user if it doesn't exist yet
            User.findOrNewSystem("dogbin")
        }
    }

    companion object {
        private var instance: Database? = null

        fun getInstance(location: File, environment: String): Database {
            if (instance == null) {
                instance = Database(location, environment)
            }
            return instance!!
        }
    }
}