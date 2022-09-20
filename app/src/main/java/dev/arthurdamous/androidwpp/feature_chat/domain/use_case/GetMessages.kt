package dev.arthurdamous.androidwpp.feature_chat.domain.use_case

import dev.arthurdamous.androidwpp.feature_chat.domain.model.Message
import dev.arthurdamous.androidwpp.feature_chat.domain.repository.MessageRepository
import dev.arthurdamous.androidwpp.util.Resource
import kotlinx.coroutines.flow.Flow

class GetMessages(private val repository: MessageRepository) {

    suspend operator fun invoke(): Flow<Resource<List<Message>>> {
        return repository.getAllMessages()
    }

}
