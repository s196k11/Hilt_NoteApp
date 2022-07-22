package com.example.hilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.hilt.data.Note
import com.example.hilt.ui.theme.HiltTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiltTheme {
                val noteViewModel : MainViewModel by viewModels()
                MainScreen(viewModel = noteViewModel)
            }
        }
    }
}




@Composable
fun MainScreen(viewModel: MainViewModel){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val onTitleChange = {value:String ->
        title = value
    }
    val onDescriptionChange = {value:String ->
        description = value
    }

    val listOfNote = viewModel.noteList.collectAsState().value

    Column {
        TextField(value =title , onValueChange = onTitleChange,modifier = Modifier.fillMaxWidth(), placeholder = {Text(text = "Title")})
        TextField(value =description , onValueChange = onDescriptionChange,modifier = Modifier.fillMaxWidth(), placeholder = {Text(text = "Description")})
        Button(onClick = {
            if (title.isNotEmpty()||description.isNotEmpty()){
                viewModel.addNote(Note(title = title, description = description))
            }
        }) {
            Text(text = "Save")
        }

        Divider()

        LazyColumn{
            items(listOfNote){note ->
                Surface(modifier = Modifier.fillMaxWidth()) {
                    Text(text = note.title, style = MaterialTheme.typography.body1)
                    Text(text = note.description, style = MaterialTheme.typography.subtitle2)
                }
            }
        }

    }
}


