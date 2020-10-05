package com.example.myapplication.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.data.entity.Note
import com.example.myapplication.data.errors.NoAuthExp
import com.example.myapplication.data.model.NoteResult
import com.example.myapplication.data.provider.FireStoreProvider
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*
import io.mockk.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FireStoreProviderTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val mockkDb = mockk<FirebaseFirestore>()
    private val mockkAuth = mockk<FirebaseAuth>()
    private val mockkResultCollection = mockk<CollectionReference>()
    private val mockkUser = mockk<FirebaseUser>()

    private val mockkDocument1 = mockk<DocumentSnapshot>()
    private val mockkDocument2 = mockk<DocumentSnapshot>()
    private val mockkDocument3 = mockk<DocumentSnapshot>()

    private val testNotes = listOf(Note("1"), Note("2"), Note("3"))

    private val provider: FireStoreProvider = FireStoreProvider(mockkDb, mockkAuth)


    @Before
    fun setUP() {
        clearAllMocks()
        every { mockkAuth.currentUser } returns mockkUser
        every { mockkUser.uid } returns ""
        every {
            mockkDb.collection(any()).document(any()).collection(any())
        } returns mockkResultCollection
        every { mockkDocument1.toObject(Note::class.java) } returns testNotes[0]
        every { mockkDocument2.toObject(Note::class.java) } returns testNotes[1]
        every { mockkDocument3.toObject(Note::class.java) } returns testNotes[2]

    }

    @Test
    fun `возвращение ошибки когда пользователь не авторизован`() {
        var result: Any? = null
        every { mockkAuth.currentUser } returns null
        provider.subscribeToAllNote().observeForever {
            result = (it as NoteResult.Error).error
        }
        assertTrue(result is NoAuthExp)
    }

    @Test
    fun `возвращение списка заметок`() {
        var result: List<Note>? = null
        val slot = slot<EventListener<QuerySnapshot>>()
        val mockSnapshot = mockk<QuerySnapshot>()

        every { mockSnapshot.documents } returns listOf(
            mockkDocument1,
            mockkDocument2,
            mockkDocument3
        )
        every { mockkResultCollection.addSnapshotListener(capture(slot)) } returns mockk()

        provider.subscribeToAllNote().observeForever {
            result = (it as? NoteResult.Success<List<Note>>)?.data
        }
        slot.captured.onEvent(mockSnapshot, null)
        assertEquals(testNotes, result)
    }
    @Test
    fun `возвращение ошибки при авторизированом пользователе`() {
        var result: Throwable? = null
        val mockkError = mockk<FirebaseFirestoreException>()
        val slot = slot<EventListener<QuerySnapshot>>()
        every { mockkResultCollection.addSnapshotListener(capture(slot)) } returns mockk()

        provider.subscribeToAllNote().observeForever {
            result = (it as? NoteResult.Error)?.error
        }
        slot.captured.onEvent(null, mockkError)

        assertNotNull(result)
        assertEquals(result, mockkError)
    }
    @Test
    fun `проверка сохранения заметки`() {
        val mockDocumentReference = mockk<DocumentReference>()
        every { mockkResultCollection.document(testNotes[0].id) } returns mockDocumentReference
        provider.saveNote(testNotes[0])
        verify(exactly = 1) { mockDocumentReference.set(testNotes[0]) }
    }
    @Test
    fun `успешное возврашение экземпляра сохраненной заметки`() {
        var result: Note? = null
        val mockDocumentReference = mockk<DocumentReference>()
        val slot = slot<OnSuccessListener<in Void>>()

        every { mockkResultCollection.document(testNotes[0].id) } returns mockDocumentReference
        every { mockDocumentReference.set(testNotes[0]).addOnSuccessListener(capture(slot)) } returns mockk()

        provider.saveNote(testNotes[0]).observeForever {
            result = (it as? NoteResult.Success<Note>)?.data
        }

        slot.captured.onSuccess(null)
        assertEquals(result, testNotes[0])
    }


}
