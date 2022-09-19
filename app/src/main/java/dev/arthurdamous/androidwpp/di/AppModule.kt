package dev.arthurdamous.androidwpp.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.arthurdamous.androidwpp.data.repository.MessageRepositoryImpl
import dev.arthurdamous.androidwpp.domain.repository.MessageRepository
import dev.arthurdamous.androidwpp.domain.use_case.GetMessages
import dev.arthurdamous.androidwpp.domain.use_case.MessageUseCase
import dev.arthurdamous.androidwpp.domain.use_case.SentMessage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseFirestoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun providesMessageRepository(
        firebaseDb: FirebaseFirestore
    ): MessageRepository {
        return MessageRepositoryImpl(firebaseDb)
    }

    @Provides
    @Singleton
    fun providesMessageUseCase(
        repository: MessageRepository
    ): MessageUseCase {
        return MessageUseCase(
            sentMessage = SentMessage(repository),
            getMessages = GetMessages(repository)
        )
    }
}