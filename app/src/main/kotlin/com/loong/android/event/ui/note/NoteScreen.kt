package com.loong.android.event.ui.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.Redo
import androidx.compose.material.icons.automirrored.rounded.Undo
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.IosShare
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    id: String? = null,
    vm: NoteViewModel = viewModel(),
    backAction: () -> Unit
) {
    if (id != null) {
        vm.openNote(id)
    }
    RichTextEditor(modifier, backAction) {
        vm.updateNote(it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RichTextEditor(
    modifier: Modifier = Modifier,
    backAction: () -> Unit,
    saveCallback: (String) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue(AnnotatedString(""))) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }

    val view = LocalView.current
    DisposableEffect(view) {
        val listener = OnApplyWindowInsetsListener { _, insets ->
            val isKeyboardVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
            if (!isKeyboardVisible) {
                // 键盘关闭时清除焦点
                focusManager.clearFocus()
            }
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(view, listener)

        onDispose {
            ViewCompat.setOnApplyWindowInsetsListener(view, null)
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Rich Text Editor")
        }, navigationIcon = {
            IconButton(onClick = {
                focusManager.clearFocus()
                backAction()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }, actions = {
            if (isFocused) {
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier) {
                    Icon(imageVector = Icons.AutoMirrored.Rounded.Undo, contentDescription = "Undo")
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                val icon =
                    if (isFocused) Icons.AutoMirrored.Rounded.Redo else Icons.Rounded.IosShare
                val desc = if (isFocused) "Redo" else "Share"
                Icon(imageVector = icon, contentDescription = desc)
            }
            IconButton(onClick = {
                if (isFocused) {
                    focusManager.clearFocus()
                }
            }) {
                val icon = if (isFocused) Icons.Rounded.Done else Icons.Rounded.MoreVert
                val desc = if (isFocused) "Done" else "More"
                Icon(imageVector = icon, contentDescription = desc)
            }
        })
    }) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            BasicTextField(
                value = text,
                onValueChange = { newTextFieldValue ->
                    text = newTextFieldValue
                },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                        if (!isFocused) {
                            saveCallback(text.text)
                        }
                    },
                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = modifier.padding(8.dp)
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
