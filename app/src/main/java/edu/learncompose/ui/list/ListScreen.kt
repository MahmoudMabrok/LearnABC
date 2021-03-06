package edu.learncompose.ui.list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.learncompose.util.Screen
import java.util.*


@Composable
fun ListScreen(navigateTo: (Screen) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(
            title = { Text(text = "Learn ABC") }
        ) }
    ) {
        ABCList(chars = CharRange('A', 'Z').map { it.toString() }.toList(), navigate = navigateTo)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewABc() {
    val list = CharRange('A', 'Z').map { it.toString() }.toList()
    ABCList(chars = list, Modifier.fillMaxWidth()) {}
}

@Composable
fun ABCList(chars: List<String>, modifier: Modifier = Modifier, navigate: (Screen) -> Unit) {
    Column(modifier = modifier) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(chars) { item ->
                CharItem(item = item, Modifier.fillMaxWidth()) {
                   navigate.invoke(Screen.DetailScreenType(it))
                }
                Divider(modifier = Modifier.height(2.dp))
            }
        }
    }
}

@Composable
fun CharItem(item: String, modifier: Modifier, onCLick: (String) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onCLick(item) }
        .clip(RoundedCornerShape(10.dp))
        .padding(8.dp),
        horizontalArrangement = Arrangement.Center) {
        Text(text = item, color = Color.Blue, style = MaterialTheme.typography.h2)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = item.toLowerCase(Locale.ENGLISH),
            color = Color.Green,
            style = MaterialTheme.typography.h3
        )
    }
}