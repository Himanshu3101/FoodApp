package com.example.foodapp.Repository

import com.example.foodapp.Domain.BannerModel
import com.example.foodapp.Domain.CategoryModel
import com.example.foodapp.Domain.FoodModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(){
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadCategory(): Flow<MutableList<CategoryModel>> = callbackFlow {
        val ref = firebaseDatabase.getReference("Category")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(CategoryModel::class.java)
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

    fun loadBanner(): Flow<MutableList<BannerModel>> = callbackFlow {
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

    fun loadFilter(id:String): Flow<MutableList<FoodModel>> = callbackFlow {
        val ref = firebaseDatabase.getReference("Foods")
        var query:Query= ref.orderByChild("CategoryId").equalTo(id)

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FoodModel>()
                for(childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(FoodModel::class.java)
                    if(list != null){
                        lists.add(list)
                    }
                }
                trySend(lists).isSuccess // Send data to the flow
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException()) // Close the flow on error
                }
        }
        query.addListenerForSingleValueEvent(listener)

        awaitClose { query.removeEventListener(listener) } // Ensure cleanup
    }

}