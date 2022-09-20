package dev.arthurdamous.androidwpp.feature_chat.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.arthurdamous.androidwpp.feature_chat.domain.use_case.MessageUseCase
import dev.arthurdamous.androidwpp.util.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val messageUseCase: MessageUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        getMessagesList()
    }

    private fun getMessagesList() {
        viewModelScope.launch {
            messageUseCase.getMessages().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            listOfMessages = result.data?.sortedBy { it.hour } ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            listOfMessages = emptyList()
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true,
                            listOfMessages = emptyList()
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: MessageEvent) {
        when (event) {
            is MessageEvent.OnMessageSent -> {
                viewModelScope.launch {
                    try {
                        when (messageUseCase.sentMessage(event.text)) {
                            is Resource.Success -> {
                                Log.d("response", "Mensagem Salva com Sucesso: ${event.text}")
                                _state.value = state.value.copy(
                                    messageText = ""
                                )
                            }
                            is Resource.Error -> {
                                Log.d("error", "Erro, tente novamente do viewmodel")
                            }
                            is Resource.Loading -> {
                                Log.d("error", "Erro, tente novamente")
                            }
                        }
                    } catch (e: Exception) {
                        Log.d("error", e.message ?: "Erro, tente novamente")
                    }
                }
            }
            is MessageEvent.OnChangeTextMessage -> {
                _state.value = state.value.copy(
                    messageText = event.text,
                    isEnabledToSendMessage = state.value.messageText.isNotEmpty()
                )
            }
            is MessageEvent.OnChangeEnabledToSendMessage -> {
                _state.value = state.value.copy(
                    isEnabledToSendMessage = !state.value.isEnabledToSendMessage
                )
            }
        }
    }

}