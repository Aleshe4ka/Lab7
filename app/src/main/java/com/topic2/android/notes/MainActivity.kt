package com.topic2.android.notes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.topic2.android.notes.theme.NotesTheme
import com.topic2.android.notes.viewmodel.MainViewModel
import com.topic2.android.notes.viewmodel.MainViewModelFactory
import ui.components.screens.NoteScreen

/**
 * Main activity приложения.
 */
class MainActivity : AppCompatActivity() {

  private val viewModel: MainViewModel by viewModels(factoryProducer = {
    MainViewModelFactory(
      this,
      (application as NotesApplication).dependencyInjector.repository
    )
  })


  @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      NotesTheme {
      NoteScreen(viewModel = viewModel)
    }
  }}}