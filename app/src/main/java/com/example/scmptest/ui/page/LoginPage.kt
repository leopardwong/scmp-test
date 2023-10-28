package com.example.scmptest.ui.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.scmptest.ui.viewModel.LoginPageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }

    var isVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var token by remember { mutableStateOf("") }

    var errorMsg by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    val viewModel = LoginPageViewModel()

    LaunchedEffect(token){
        if(token.isNotEmpty()){
            navController.navigate("staffList/${token}")
        }
    }

    ErrorMsgDialog(
        showErrorDialog = showErrorDialog,
        errorMsg = errorMsg,
        onDismiss = { showErrorDialog = false }
    )

    if(isLoading){
        LoadingDialog()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedTextField(
            value = email,
            onValueChange = { newEmail ->
                email = newEmail
                isEmailValid = viewModel.isValidEmail(newEmail)
            },
            label = { Text("Email") },
            isError = email.isNotEmpty() && !isEmailValid,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { newPassword ->
                password = newPassword
                isPasswordValid = viewModel.isValidPassword(newPassword)
            },
            label = { Text("Password") },
            visualTransformation = if (!isVisible) PasswordVisualTransformation() else VisualTransformation.None,
            isError = password.isNotEmpty() && !isPasswordValid,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            trailingIcon = {
                Icon(
                    imageVector = if (isVisible) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    },
                    contentDescription = "Password visibility toggle",
                    modifier = Modifier.clickable {
                        isVisible = !isVisible
                    }
                )
            },
            )

        Button(
            onClick = {
                viewModel.loginValid(
                    email = email,
                    password = password,
                    token = {token = it},
                    loading = { isLoading = it},
                    error = {
                        errorMsg = "Please Enter the correct Email or Password!"
                        showErrorDialog = it
                    }
                )
            },
            enabled = isEmailValid && isPasswordValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

