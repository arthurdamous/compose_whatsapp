package dev.arthurdamous.androidwpp.feature_login.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.arthurdamous.androidwpp.feature_login.domain.use_case.UserUseCases
import dev.arthurdamous.androidwpp.util.Resource
import dev.arthurdamous.androidwpp.util.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCases
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnChangeEmail -> {
                _state.value = state.value.copy(
                    email = event.text
                )
            }
            is LoginEvent.OnChangePassword -> {
                _state.value = state.value.copy(
                    password = event.text
                )
            }
            is LoginEvent.OnLogin -> {
                viewModelScope.launch {
                    when (userUseCase.login(state.value.email, state.value.password)) {
                        is Resource.Success -> {
                            _uiEvent.emit(
                                UiEvent.OnLoginSuccessful
                            )
                        }
                        is Resource.Error -> {
                            println("Error aqui no viewmodel")
                        }
                        is Resource.Loading -> {

                        }
                    }
                }
            }
            is LoginEvent.OnRegister -> {
                viewModelScope.launch {
                    when (userUseCase.register(
                        "Arthur Damous",
                        state.value.email,
                        state.value.password
                    )) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {

                        }
                    }
                }
            }
        }

    }

}