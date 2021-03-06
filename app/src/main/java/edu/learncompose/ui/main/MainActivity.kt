package edu.learncompose.ui.main

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import edu.learncompose.ui.detail.DetailScreen
import edu.learncompose.ui.list.ListScreen
import edu.learncompose.ui.theme.ABCTheme
import edu.learncompose.util.NavigationViewModel
import edu.learncompose.util.Screen
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private val navigationModel by viewModels<NavigationViewModel>()
    private var isAbleToSpeck = false

    private var tts:TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ABCTheme {
                AppContent(navigationViewModel = navigationModel)
            }
        }

        tts = TextToSpeech(this, this)
    }

    @Composable
    private fun AppContent(
        navigationViewModel: NavigationViewModel
    ) {
        Crossfade(navigationViewModel.screen.observeAsState(Screen.ListScreenType).value) { screen ->
            Surface(color = MaterialTheme.colors.background) {
                when (screen) {
                    is Screen.ListScreenType -> ListScreen(navigateTo = navigationViewModel::navigateTo)
                    is Screen.DetailScreenType -> DetailScreen(item = screen.item , toSay = this::say , navigateTo = navigationViewModel::navigateTo)
                }
            }
        }
    }

    private fun say(word: String) {
        Log.d("TTTTTTT", "say: wwewwwew")
        if (isAbleToSpeck){
            Log.d("TTTTTTT", "say: aaaa")
            tts?.speak(word,TextToSpeech.QUEUE_FLUSH, null ,"")
        }
    }


    override fun onBackPressed() {
        if (!navigationModel.onBack()) {
            super.onBackPressed()
        }

    }

    override fun onInit(status: Int) {
        Log.d("TTTTTTT", "say: s$status")
        isAbleToSpeck = if (status == TextToSpeech.SUCCESS) {
            Log.d("TTTTTTT", "say: SUCCESS")
            // set US English as language for tts
            val result = tts?.setLanguage(Locale.US)
            !(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
        } else {
            Log.d("TTTTTTT", "say: erttt")
            false
        }
    }

    override fun onDestroy() {
       tts?.let {tts ->
           tts.stop()
           tts.shutdown()
       }
        super.onDestroy()
    }
}

