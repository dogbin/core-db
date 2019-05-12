import dogbin.db.Database
import dogbin.db.model.user.User
import kotlinx.dnq.query.asSequence
import java.io.File

// TODO: Add actual tests and turn this into a simple sample

fun main() {
    val db = Database.getInstance(File("dogbin.test-db"), "test")

    db.store.transactional {
        User.all().asSequence().forEach {
            println(it.displayName)
        }
    }
}