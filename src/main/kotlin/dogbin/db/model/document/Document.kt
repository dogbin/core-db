package dogbin.db.model.document

import dogbin.db.model.user.User
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.simple.length
import kotlinx.dnq.simple.regex

class Document(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<Document>()

    var slug by xdRequiredStringProp(unique = true, trimmed = true) {
        length(min = 3)
        regex(Regex("^[\\w-]*\$"))
    }

    var type by xdLink1(DocumentType)

    // TODO: Require at least one of these to have a value depending on the type
    var stringContent by xdBlobStringProp()
    var blobContent by xdBlobProp()

    var viewCount by xdRequiredLongProp()
    var version by xdRequiredIntProp()
    var owner by xdLink1(User)

    // TODO: add support for expirable documents
}