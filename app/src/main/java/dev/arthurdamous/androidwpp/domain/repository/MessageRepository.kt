package dev.arthurdamous.androidwpp.domain.repository

import dev.arthurdamous.androidwpp.domain.model.Message
import dev.arthurdamous.androidwpp.util.Resource
import dev.arthurdamous.androidwpp.util.SimpleResource
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    suspend fun sendMessage(message: Message): SimpleResource

    suspend fun getAllMessages(): Flow<Resource<List<Message>>>
}