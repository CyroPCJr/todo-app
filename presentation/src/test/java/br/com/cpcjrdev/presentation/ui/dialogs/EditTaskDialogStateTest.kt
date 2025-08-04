package br.com.cpcjrdev.presentation.ui.dialogs

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EditTaskDialogStateTest {
    @Test
    fun `initial state should have correct values`() {
        val state = EditTaskDialogState("Test Title", "Test Description")

        assertEquals("Test Title", state.title)
        assertEquals("Test Description", state.description)
        assertFalse(state.hasAttemptedSubmit)
        assertFalse(state.titleError)
        assertFalse(state.descriptionError)
        assertTrue(state.isValid)
    }

    @Test
    fun `updateTitle should change title`() {
        val state = EditTaskDialogState("", "")

        state.updateTitle("New Title")

        assertEquals("New Title", state.title)
    }

    @Test
    fun `updateDescription should change description`() {
        val state = EditTaskDialogState("", "")

        state.updateDescription("New Description")

        assertEquals("New Description", state.description)
    }

    @Test
    fun `attemptSubmit should return true when valid`() {
        val state = EditTaskDialogState("Title", "Description")

        val result = state.attemptSubmit()

        assertTrue(result)
        assertTrue(state.hasAttemptedSubmit)
    }

    @Test
    fun `attemptSubmit should return false when invalid`() {
        val state = EditTaskDialogState("", "")

        val result = state.attemptSubmit()

        assertFalse(result)
        assertTrue(state.hasAttemptedSubmit)
        assertTrue(state.titleError)
        assertTrue(state.descriptionError)
    }

    @Test
    fun `titleError should show when attempted submit with blank title`() {
        val state = EditTaskDialogState("", "Description")

        state.attemptSubmit()

        assertTrue(state.titleError)
        assertFalse(state.descriptionError)
    }

    @Test
    fun `descriptionError should show when attempted submit with blank description`() {
        val state = EditTaskDialogState("Title", "")

        state.attemptSubmit()

        assertFalse(state.titleError)
        assertTrue(state.descriptionError)
    }

    @Test
    fun `reset should clear hasAttemptedSubmit flag`() {
        val state = EditTaskDialogState("", "")
        state.attemptSubmit()

        state.reset()

        assertFalse(state.hasAttemptedSubmit)
        assertFalse(state.titleError)
        assertFalse(state.descriptionError)
    }
}
