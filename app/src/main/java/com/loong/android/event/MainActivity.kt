package com.loong.android.event

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loong.android.event.ui.theme.EventTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventTheme {
            }
        }
    }
}

@Composable
fun TopBar() {
    Row {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "back")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    Column {
        Text(text = "2024年6月")
        val weeks = remember { listOf("周一", "周二", "周三", "周四", "周五", "周六", "周日") }
        LazyRow {
            weeks.forEach {
                item(it) {
                    Text(text = it)
                }
            }
        }
        val pagerState = rememberPagerState { 5 }
        val items = (1..100).toList()
        HorizontalPager(state = pagerState) {
            LazyVerticalGrid(columns = GridCells.Fixed(7)) {
                items(items) {
                    Box(modifier = Modifier.size(50.dp)) {
                        Text(text = it.toString())
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EventTheme {
        MainScreen()
    }
}