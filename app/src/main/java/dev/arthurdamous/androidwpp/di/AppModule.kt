package dev.arthurdamous.androidwpp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.arthurdamous.androidwpp.feature_chat.data.repository.MessageRepositoryImpl
import dev.arthurdamous.androidwpp.feature_chat.domain.repository.MessageRepository
import dev.arthurdamous.androidwpp.feature_chat.domain.use_case.GetMessages
import dev.arthurdamous.androidwpp.feature_chat.domain.use_case.MessageUseCase
import dev.arthurdamous.androidwpp.feature_chat.domain.use_case.SentMessage
import dev.arthurdamous.androidwpp.feature_login.data.repository.UserRepositoryImpl
import dev.arthurdamous.androidwpp.feature_login.domain.repository.UserRepository
import dev.arthurdamous.androidwpp.feature_login.domain.use_case.Login
import dev.arthurdamous.androidwpp.feature_login.domain.use_case.Register
import dev.arthurdamous.androidwpp.feature_login.domain.use_case.UserUseCases
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
    fun providesFirebaseAuthInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
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
    fun providesUserRepository(
        firebaseAuth: FirebaseAuth,
        firebaseDb: FirebaseFirestore
    ): UserRepository {
        return UserRepositoryImpl(firebaseAuth, firebaseDb)
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

    @Provides
    @Singleton
    fun providesUserUseCase(
        repository: UserRepository
    ): UserUseCases {
        return UserUseCases(
            login = Login(repository),
            register = Register(repository)
        )
    }
}