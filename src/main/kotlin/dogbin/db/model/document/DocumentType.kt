package dogbin.db.model.document

import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.XdEnumEntity
import kotlinx.dnq.enum.XdEnumEntityType

class DocumentType(entity: Entity) : XdEnumEntity(entity) {
    companion object : XdEnumEntityType<DocumentType>() {
        val PASTE by enumField { }
        val URL by enumField { }

        // TODO: add support for images and general files
    }
}