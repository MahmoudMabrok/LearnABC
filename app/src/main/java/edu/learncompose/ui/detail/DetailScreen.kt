package edu.learncompose.ui.detail

import android.util.Log
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.learncompose.R
import edu.learncompose.util.Screen
import java.util.*


@Composable
fun DetailScreen(item:String ,  toSay : (String) -> Unit  , navigateTo: (Screen) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Learn ABC") }) }
    ) {
        AbcDetailView(item, toSay = toSay , navigate = navigateTo)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewABc() {
    AbcDetailView(chars = "A", Modifier.fillMaxWidth(), {} , {} )
}

@Composable
fun AbcDetailView(chars: String, modifier: Modifier = Modifier, toSay : (String) -> Unit , navigate: (Screen) -> Unit) {
    Column(modifier = modifier.fillMaxWidth()
        .padding(all = 16.dp) , horizontalAlignment = Alignment.CenterHorizontally ) {
        Text(text = chars, Modifier
            .border(width = 2.dp, shape = CutCornerShape(topStart = 16.dp), color = Color.Black)
            .padding(all = 16.dp),
            style = MaterialTheme.typography.h1
        )

        Spacer(modifier = Modifier.height(8.dp))
        
        Button(onClick = { toSay(chars) }) {
            Text(text = "Speak")
            Icon(painter = painterResource(id = R.drawable.ic_mic), contentDescription = null)
        }

    }
}

