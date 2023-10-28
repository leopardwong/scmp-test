package com.example.scmptest.ui.page

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun ErrorMsgDialog(showErrorDialog: Boolean, errorMsg: String, onDismiss: () -> Unit) {
    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Error") },
            text = { Text(text = errorMsg) },
            confirmButton = {
                Button(
                    onClick = { onDismiss() }
                ) {
                    Text(text = "Close")
                }
            }
        )
    }
}