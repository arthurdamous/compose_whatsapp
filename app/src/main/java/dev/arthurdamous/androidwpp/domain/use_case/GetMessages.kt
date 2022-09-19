package dev.arthurdamous.androidwpp.domain.use_case

import dev.arthurdamous.androidwpp.domain.model.Message
import dev.arthurdamous.androidwpp.domain.repository.MessageRepository
import dev.arthurdamous.androidwpp.util.Resource
import kotlinx.coroutines.flow.Flow

class GetMessages(private val repository: MessageRepository) {

    suspend operator fun invoke(): Flow<Resource<List<Message>>> {
        return repository.getAllMessages()
    }

}
