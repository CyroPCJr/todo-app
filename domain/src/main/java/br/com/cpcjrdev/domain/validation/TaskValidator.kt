package br.com.cpcjrdev.domain.validation

sealed class ValidationResult {
    object Success : ValidationResult()

    data class Error(
        val message: String,
    ) : ValidationResult()
}

object TaskValidator {
    private const val MIN_TITLE_LENGTH = 1
    private const val MAX_TITLE_LENGTH = 100
    private const val MIN_DESCRIPTION_LENGTH = 1
    private const val MAX_DESCRIPTION_LENGTH = 500

    fun validateTitle(title: String): ValidationResult =
        when {
            title.isBlank() -> ValidationResult.Error("Title cannot be empty")
            title.length < MIN_TITLE_LENGTH -> ValidationResult.Error("Title is too short")
            title.length > MAX_TITLE_LENGTH -> ValidationResult.Error("Title is too long (max $MAX_TITLE_LENGTH characters)")
            else -> ValidationResult.Success
        }

    fun validateDescription(description: String): ValidationResult =
        when {
            description.isBlank() -> ValidationResult.Error("Description cannot be empty")
            description.length < MIN_DESCRIPTION_LENGTH -> ValidationResult.Error("Description is too short")
            description.length > MAX_DESCRIPTION_LENGTH -> ValidationResult.Error(
                "Description is too long (max $MAX_DESCRIPTION_LENGTH characters)",
            )
            else -> ValidationResult.Success
        }

    fun validateTask(
        title: String,
        description: String,
    ): List<ValidationResult> =
        listOf(
            validateTitle(title),
            validateDescription(description),
        )

    fun isTaskValid(
        title: String,
        description: String,
    ): Boolean = validateTask(title, description).all { it is ValidationResult.Success }
}
