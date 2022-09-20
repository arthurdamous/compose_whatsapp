package dev.arthurdamous.androidwpp.feature_login.domain.use_case

import dev.arthurdamous.androidwpp.feature_login.domain.repository.UserRepository
import dev.arthurdamous.androidwpp.util.SimpleResource

class Register(
    private val repository: UserRepository
) {

    suspend operator fun invoke(
        name: String,
        email: String,
        password: String
    ): SimpleResource {
        return repository.registerUser(name, email, password)
    }
}
