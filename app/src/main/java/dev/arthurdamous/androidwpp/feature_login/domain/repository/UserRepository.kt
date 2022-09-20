package dev.arthurdamous.androidwpp.feature_login.domain.repository

import dev.arthurdamous.androidwpp.util.SimpleResource

interface UserRepository {

    suspend fun loginUser(email: String, password: String): SimpleResource

    suspend fun registerUser(
        name: String,
        email: String,
        password: String
    ): SimpleResource
}