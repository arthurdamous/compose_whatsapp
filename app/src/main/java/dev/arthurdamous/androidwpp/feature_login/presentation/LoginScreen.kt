package dev.arthurdamous.androidwpp.feature_login.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.arthurdamous.androidwpp.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    onLoginSuccessful: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                UiEvent.OnLoginSuccessful -> {
                    onLoginSuccessful()
                }
            }
        }
    }

    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(
                value = state.email,
                onValueChange = { text ->
                    viewModel.onEvent(LoginEvent.OnChangeEmail(text))
                },
                label = { Text(text = "Email") },
                placeholder = { Text(text = "Email") }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = state.password,
                onValueChange = { text ->
                    viewModel.onEvent(LoginEvent.OnChangePassword(text))
                },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Password") }
            )

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { viewModel.onEvent(LoginEvent.OnLogin) }) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(onClick = { viewModel.onEvent(LoginEvent.OnRegister) }) {
                Text(text = "Register right now")
            }
        }
    }
}