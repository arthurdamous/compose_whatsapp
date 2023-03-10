package dev.arthurdamous.androidwpp.feature_login.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dev.arthurdamous.androidwpp.feature_login.domain.repository.UserRepository
import dev.arthurdamous.androidwpp.util.Resource
import dev.arthurdamous.androidwpp.util.SimpleResource

class UserRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {

    override suspend fun loginUser(email: String, password: String): SimpleResource {
        return try {
            val request = firebaseAuth.signInWithEmailAndPassword(
                email, password
            )
            if (request.isSuccessful) {
                println(request.result.user)
                Resource.Success(Unit)
            } else {
                Resource.Error("")
            }

        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "Error on login")
        }
    }

    override suspend fun registerUser(
        name: String,
        email: String,
        password: String
    ): SimpleResource {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnFailureListener {
                    println(it)
                }
                .addOnSuccessListener { authResult ->
                    val user = hashMapOf(
                        "email" to authResult.user?.email,
                        "token" to authResult.user?.uid,
                        "name" to name
                    )
                    firebaseFirestore.collection("users")
                        .document()
                        .set(user)
                        .addOnSuccessListener {
                            println("User saved: $it")
                        }
                }

            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "")
        }
    }


}