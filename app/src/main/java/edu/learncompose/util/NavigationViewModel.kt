package edu.learncompose.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class Screen {
    object ListScreenType : Screen()
    data class DetailScreenType(val item:String) : Screen()
}

class NavigationViewModel : ViewModel() {

    private val _screen = MutableLiveData<Screen>()
    val screen = (_screen as LiveData<Screen>)

    fun navigateTo(destination: Screen) {
        _screen.value = destination
    }

    fun onBack(): Boolean {
        val wasHandled = _screen.value != Screen.ListScreenType
        _screen.value = Screen.ListScreenType
        return wasHandled

    }

    init {
        _screen.value = Screen.ListScreenType
    }
}