package com.example.foodapp.Repository

import com.example.foodapp.Domain.BannerModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(){

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadBanner(): Flow<List<BannerModel>> = callbackFlow {
        val ref = firebaseDatabase.getReference("Banners")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<BannerModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(BannerModel::class.java)
                    item?.let { list.add(it) }
                }
                trySend(list).isSuccess // Send data to the flow
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException()) // Close the flow on error
            }
        }

        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) } // Remove listener when flow is canceled
    }

}