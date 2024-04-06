package com.vr.beerinformation.data.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vr.beerinformation.data.model.Beer
import com.vr.beerinformation.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImpl():BeerRepository {

    override fun getAllBeer(): Flow<List<Beer>> = flow{
        val collection = Firebase.firestore.collection("ListBeer")
        try {
            val querySnapshot = collection.get().await()
            val beers = querySnapshot.toObjects(Beer::class.java)
            emit(beers)
        } catch (e: Exception) {
            // Обработка ошибки
            throw e
        }
    }
}