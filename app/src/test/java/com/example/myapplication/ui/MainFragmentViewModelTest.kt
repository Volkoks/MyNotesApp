package com.example.myapplication.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.entity.Note
import com.example.myapplication.data.model.NoteResult
import com.example.myapplication.data.repository.NoteRepository
import com.example.myapplication.ui.main.MainFragmentViewModel
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainFragmentViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val mockkRepository = mockk<NoteRepository>()
    private val notesLiveData = MutableLiveData<NoteResult>()
    private lateinit var viewModel: MainFragmentViewModel

    @Before
    fun setUp() {
        clearAllMocks()
        every { mockkRepository.getNotes() } returns notesLiveData
        viewModel = MainFragmentViewModel(mockkRepository)
    }

    @Test
    fun `вызов getNotes один раз`() {
        verify(exactly = 1) { mockkRepository.getNotes() }
    }

    @Test
    fun `проверка передачи ошибки`() {
        var result: Throwable? = null
        val testDataThrowable = Throwable("error")
        viewModel.getViewState().observeForever {
            result = it.error
        }
        notesLiveData.value = NoteResult.Error(testDataThrowable)
        assertEquals(result, testDataThrowable)
    }

    @Test
    fun `проверка передачи заметок в Фрагмент`() {
        var result: List<Note>? = null
        val testListNotes = listOf(Note("1"), Note("2"))
        viewModel.getViewState().observeForever {
            result = it.data
        }
        notesLiveData.value = NoteResult.Success(testListNotes)
        assertEquals(result, testListNotes)
    }

    @Test
    fun `проверка отписки от LiveData`() {
        viewModel.onCleared()
        assertFalse(notesLiveData.hasObservers())
    }

}