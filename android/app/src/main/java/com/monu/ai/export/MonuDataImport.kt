package com.monu.ai.export

data class MonuImportRequest(
    val content: String,
    val format: MonuExportFormat
)

data class MonuImportResult(
    val success: Boolean,
    val importedItems: Int,
    val message: String
)

class MonuDataImporter {

    fun validate(
        request: MonuImportRequest
    ): MonuImportResult {

        if (request.content.isBlank()) {
            return MonuImportResult(
                success = false,
                importedItems = 0,
                message = "Import content is empty"
            )
        }

        return MonuImportResult(
            success = true,
            importedItems = 1,
            message = "Import data accepted"
        )
    }
}
