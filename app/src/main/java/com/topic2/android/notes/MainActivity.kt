package com.topic2.android.notes

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import com.topic2.android.notes.routing.Screen
import com.topic2.android.notes.theme.NotesTheme
import com.topic2.android.notes.viewmodel.MainViewModel
import com.topic2.android.notes.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch
import ui.components.AppDrawer
import ui.components.Note

/**
 * Main activity приложения.
 */
class MainActivity() : AppCompatActivity(), Parcelable {

  private val viewModel: MainViewModel by viewModels(factoryProducer = {
    MainViewModelFactory(
      this,
      (application as NotesApplication).dependencyInjector.repository
    )
  })

  constructor(parcel: Parcel) : this() {
  }

  @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      NotesTheme {
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState: ScaffoldState = rememberScaffoldState()

        Scaffold(
          scaffoldState = scaffoldState,
          drawerContent = {
            AppDrawer(
              currentScreen = Screen.Notes,
              closeDrawerAction = {
                coroutineScope.launch {
                  scaffoldState.drawerState.close()
                }
              }
            )
          },
          content = {
            Note()
          }
        )
      }
    }
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {

  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<MainActivity> {
    override fun createFromParcel(parcel: Parcel): MainActivity {
      return MainActivity(parcel)
    }

    override fun newArray(size: Int): Array<MainActivity?> {
      return arrayOfNulls(size)
    }
  }
}
