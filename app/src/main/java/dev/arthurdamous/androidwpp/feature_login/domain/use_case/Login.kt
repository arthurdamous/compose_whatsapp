package dev.arthurdamous.androidwpp.feature_login.domain.use_case

import dev.arthurdamous.androidwpp.feature_login.domain.repository.UserRepository
import dev.arthurdamous.androidwpp.util.SimpleResource

class Login(
    private val repository: UserRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): SimpleResource {
        return repository.loginUser(email, password)
    }
}
