package br.com.cpcjrdev.domain.usecase

sealed class DomainTaskResult {
    object Success : DomainTaskResult()

    data class Failure(
        val error: String,
    ) : DomainTaskResult()
}
