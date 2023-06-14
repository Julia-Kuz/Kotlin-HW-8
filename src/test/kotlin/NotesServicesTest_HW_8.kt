import org.junit.Test

import org.junit.Assert.*

class NotesServicesTestHW8 {
    //                                                    *** add test ***
    @Test
    fun addNoteShouldAdd() {
        val note = NotesServices()
        note.addNote("1", "note1")
        val result = note.getNotes().isNotEmpty()
        assertTrue(result)
    }

    @Test
    fun addCommentShouldAdd() {
        val note = NotesServices()
        note.addNote("1", "note1")
        note.addComment(1, "comment 1")
        val result = note.getCommentsToNote(1).isNotEmpty()
        assertTrue(result)
    }

    //                                                              *** delete test ***
    @Test
    fun deleteNoteShouldShouldDelete() {
        val note = NotesServices()
        note.addNote("1", "note1")
        note.deleteNote(1)
        val result = note.getNotes().isEmpty()
        assertTrue(result)
    }

    @Test(expected = NotesServices.NoteNotFoundException::class)
    fun deleteNoteShouldShouldThrowException() {
        val note = NotesServices()
        note.addNote("1", "note1")
        note.addNote("2", "note2")
        note.deleteNote(2)
        note.getNoteById(2)

    }

    @Test
    fun deleteCommentShouldDelete() {
        val note = NotesServices()
        note.addNote("1", "note1")
        note.addComment(1, "comment 1")
        note.deleteComment(1)
        val result = note.getCommentsToNote(1).isEmpty()
        assertTrue(result)
    }

    @Test(expected = NotesServices.CommentNotFoundException::class)
    fun deleteCommentShouldThrowException() {
        val note = NotesServices()
        note.addNote("1", "note1")
        note.addComment(1, "comment 1")
        note.deleteComment(2)
    }

    //                                                             *** edit test ***
    @Test
    fun editNoteShouldEdit() {
        val note = NotesServices()
        note.addNote("1", "note1")
        val result1 = note.getNotes()
        note.editNote(1, "1 CHANGED", "note1 CHANGED")
        val result2 = note.getNotes()
        val expected = result1 != result2
        assertTrue(expected)
    }

    @Test(expected = NotesServices.NoteNotFoundException::class)
    fun editNoteShouldThrowException() {
        val note = NotesServices()
        note.addNote("1", "note1")
        note.editNote(2, "1 CHANGED", "note1 CHANGED")
    }

    @Test
    fun editCommentShouldEdit() {
        val note = NotesServices()
        note.addNote("1", "note1")
        note.addComment(1, "comment 1")
        val result1 = note.getCommentsToNote(1)
        note.editComment(1, "CHANGED 1")
        val result2 = note.getCommentsToNote(1)
        val expected = result1 != result2
        assertTrue(expected)
    }

    @Test(expected = NotesServices.CommentNotFoundException::class)
    fun editCommentShouldThrowException() {
        val note = NotesServices()
        note.addNote("1", "note1")
        note.addComment(1, "comment 1")
        note.editComment(2, "CHANGED 1")
    }

    //                                                                *** restore test ***
    @Test
    fun restoreCommentShouldRestore() {
        val note = NotesServices()
        note.addNote("1", "note1")
        note.addComment(1, "comment 1")
        note.deleteComment(1)
        note.restoreComment(1)
        val result = note.getCommentsToNote(1).isNotEmpty()
        assertTrue(result)
    }

    @Test(expected = NotesServices.CommentNotFoundException::class)
    fun restoreCommentShouldThrowException() {
        val note = NotesServices()
        note.addNote("1", "note1")
        note.addComment(1, "comment 1")
        note.deleteComment(1)
        note.restoreComment(2)
    }
}