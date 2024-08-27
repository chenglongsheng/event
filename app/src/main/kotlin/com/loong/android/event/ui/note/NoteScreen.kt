package com.loong.android.event.ui.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteScreen(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    BasicTextField(
        value = text,
        onValueChange = { text = it },
        modifier = modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
                    .background(Color.LightGray)
                    .padding(8.dp)
            ) {
                if (text.isEmpty()) {
                    Text("Enter text...", color = Color.Gray, fontSize = 18.sp)
                }
                innerTextField()  // This is where the editable text is displayed
            }
        }
    )
}

@Preview
@Composable
private fun NoteScreenPreview() {
    NoteScreen()
}