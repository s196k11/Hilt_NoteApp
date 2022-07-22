package com.example.hilt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository):ViewModel() {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()


    init{
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllNote().distinctUntilChanged()
                .collect{listOfNote ->
                    _noteList.value = listOfNote
                }
        }
    }

    fun addNote(note:Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.addNote(note = note)
        }
    }

    fun removeNote(note:Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.removeNote(note)
        }
    }
}
