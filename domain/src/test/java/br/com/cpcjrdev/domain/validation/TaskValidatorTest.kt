package br.com.cpcjrdev.domain.validation

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TaskValidatorTest {
    @Test
    fun `validateTitle should return Success for valid title`() {
        val result = TaskValidator.validateTitle("Valid Title")

        assertTrue(result is ValidationResult.Success)
    }

    @Test
    fun `validateTitle should return Error for empty title`() {
        val result = TaskValidator.validateTitle("")

        assertTrue(result is ValidationResult.Error)
        assertEquals("Title cannot be empty", (result as ValidationResult.Error).message)
    }

    @Test
    fun `validateTitle should return Error for blank title`() {
        val result = TaskValidator.validateTitle("   ")

        assertTrue(result is ValidationResult.Error)
        assertEquals("Title cannot be empty", (result as ValidationResult.Error).message)
    }

    @Test
    fun `validateTitle should return Error for too long title`() {
        val longTitle = "a".repeat(101)
        val result = TaskValidator.validateTitle(longTitle)

        assertTrue(result is ValidationResult.Error)
        assertEquals(
            "Title is too long (max 100 characters)",
            (result as ValidationResult.Error).message,
        )
    }

    @Test
    fun `validateDescription should return Success for valid description`() {
        val result = TaskValidator.validateDescription("Valid description")

        assertTrue(result is ValidationResult.Success)
    }

    @Test
    fun `validateDescription should return Error for empty description`() {
        val result = TaskValidator.validateDescription("")

        assertTrue(result is ValidationResult.Error)
        assertEquals("Description cannot be empty", (result as ValidationResult.Error).message)
    }

    @Test
    fun `validateDescription should return Error for too long description`() {
        val longDescription = "a".repeat(501)
        val result = TaskValidator.validateDescription(longDescription)

        assertTrue(result is ValidationResult.Error)
        assertEquals(
            "Description is too long (max 500 characters)",
            (result as ValidationResult.Error).message,
        )
    }

    @Test
    fun `isTaskValid should return true for valid task`() {
        val isValid = TaskValidator.isTaskValid("Valid Title", "Valid Description")

        assertTrue(isValid)
    }

    @Test
    fun `isTaskValid should return false for invalid task`() {
        val isValid = TaskValidator.isTaskValid("", "")

        assertFalse(isValid)
    }

    @Test
    fun `validateTask should return multiple errors for invalid task`() {
        val results = TaskValidator.validateTask("", "")

        assertEquals(2, results.size)
        assertTrue(results.all { it is ValidationResult.Error })
    }
}
