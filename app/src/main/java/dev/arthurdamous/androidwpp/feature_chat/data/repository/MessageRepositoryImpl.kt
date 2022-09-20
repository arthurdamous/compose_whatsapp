package dev.arthurdamous.androidwpp.feature_chat.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import dev.arthurdamous.androidwpp.feature_chat.domain.model.Message
import dev.arthurdamous.androidwpp.feature_chat.domain.repository.MessageRepository
import dev.arthurdamous.androidwpp.util.Constants.COLLECTION_NAME_MESSAGES
import dev.arthurdamous.androidwpp.util.Resource
import dev.arthurdamous.androidwpp.util.SimpleResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class MessageRepositoryImpl(
    private val remoteDb: FirebaseFirestore
) : MessageRepository {

    override suspend fun sendMessage(message: Message): SimpleResource {
        val messageMap = hashMapOf(
            "id" to message.id,
            "text" to message.message,
            "date" to message.hour,
            "isSent" to message.isSent
        )
        return try {
            remoteDb
                .collection(COLLECTION_NAME_MESSAGES)
                .document()
                .set(messageMap)
                .addOnCompleteListener {
                    Log.d("remote", "Message send successfully")
                }
                .addOnFailureListener {
                    Log.d("remote", "Message not send")
                }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(
                message = e.message ?: "Error"
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getAllMessages(): Flow<Resource<List<Message>>> = channelFlow {
        send(Resource.Loading())
        val request = remoteDb.collection(COLLECTION_NAME_MESSAGES)

        val flow = request.snapshots()

        try {
            flow.collect { result ->
                val listOfMessages = mutableListOf<Message>()
                for (i in result) {
                    val message = Message(
                        id = i.data["id"].toString(),
                        message = i.data["text"].toString(),
                        hour = i.data["date"].toString(),
                        isSent = i.data["isSent"] as Boolean
                    )
                    listOfMessages.add(message)
                }
                send(Resource.Success(data = listOfMessages.toList()))
            }
        } catch (e: Exception) {
            send(Resource.Error(message = e.message ?: "Error on downloading messages"))
        }

    }
}