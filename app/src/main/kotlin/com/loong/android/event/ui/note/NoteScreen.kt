package com.loong.android.event.ui.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.IosShare
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteScreen(modifier: Modifier = Modifier, backAction: () -> Unit) {
    RichTextEditor(modifier, backAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RichTextEditor(modifier: Modifier = Modifier, backAction: () -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue(AnnotatedString(""))) }
    var isBold by remember { mutableStateOf(false) }
    var isItalic by remember { mutableStateOf(false) }
    var isUnderline by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Rich Text Editor")
        }, navigationIcon = {
            IconButton(onClick = {
                backAction()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "ArrowBack"
                )
            }
        }, actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.IosShare, contentDescription = "Share")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More")
            }
        })
    }) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            // Buttons to toggle styles
            Row(Modifier.padding(8.dp)) {
                Button(onClick = { isBold = !isBold }) {
                    Text(text = "Bold")
                }
                Button(onClick = { isItalic = !isItalic }) {
                    Text(text = "Italic")
                }
                Button(onClick = { isUnderline = !isUnderline }) {
                    Text(text = "Underline")
                }
            }

            BasicTextField(
                value = text,
                onValueChange = { newTextFieldValue ->
                    text = newTextFieldValue.copy(
                        annotatedString = AnnotatedString.Builder(newTextFieldValue.text).apply {
                            // Apply styles based on button states
                            if (isBold) {
                                addStyle(
                                    SpanStyle(fontWeight = FontWeight.Bold),
                                    0,
                                    newTextFieldValue.text.length
                                )
                            }
                            if (isItalic) {
                                addStyle(
                                    SpanStyle(fontStyle = FontStyle.Italic),
                                    0,
                                    newTextFieldValue.text.length
                                )
                            }
                            if (isUnderline) {
                                addStyle(
                                    SpanStyle(textDecoration = TextDecoration.Underline),
                                    0,
                                    newTextFieldValue.text.length
                                )
                            }
                        }.toAnnotatedString()
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .onFocusChanged { focusState ->
                        // Handle focus state if needed
                    },
                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = modifier
                            .padding(8.dp)
                    ) {
                        if (text.text.isEmpty()) {
                            Text("Enter rich text...", color = Color.Gray, fontSize = 18.sp)
                        }
                        innerTextField()
                    }
                },
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                )
            )
        }
    }
}

@Preview
@Composable
private fun RichTextEditorPreview() {
    RichTextEditor {}
}
