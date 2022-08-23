package com.example.repete.repository

import com.example.repete.model.Oglas
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.Flow

const val OGLAS_COLLECTION_REF = "oglas"

class StorageRepository() {

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val oglasRef: CollectionReference = Firebase
        .firestore.collection(OGLAS_COLLECTION_REF)

    fun getUserOglas(): Flow<Resources<List<Oglas>>> = callbackFlow {
        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = oglasRef
                .orderBy("timestamp") //fali whereEqualTo gdje se pokazuju oglasi asmo za specificnog usera
                .addSnapshotListener{ snapshot, e->
                    val response = if(snapshot != null) {
                        val oglas = snapshot.toObjects(Oglas::class.java)
                        Resources.Success(data = oglas)
                    } else {
                        Resources.Error(throwable = e?.cause)
                    }

                    trySend(response)

                }

        }catch(e:Exception) {
            trySend(Resources.Error(e?.cause))
            e.printStackTrace()
        }

        awaitClose{
            snapshotStateListener?.remove()
        }
    }

    fun getOglas(oglasId: String, onError:(Throwable?)->Unit, onSuccess: (Oglas) -> Unit){
        oglasRef
            .document(oglasId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(Oglas::class.java)!!)
            }
            .addOnFailureListener{ result ->
                onError.invoke(result.cause)
            }
    }

    fun addOglas(
        userId: String,
        subject: String,
        typeOfEducation: String,
        price: String,
        contact: String,
        timestamp: Timestamp = Timestamp.now(),
        onComplete: (Boolean) -> Unit
    ) {
        val documentId = oglasRef.document().id
        val oglas = Oglas(userId, subject, typeOfEducation, price, contact, timestamp, document = documentId)

        oglasRef
            .document(documentId)
            .set(oglas)
            .addOnCompleteListener{ result ->
                onComplete.invoke(result.isSuccessful)
            }

    }

    fun signOut() = Firebase.auth.signOut()

}

sealed class Resources<T>(val data:T? = null, val throwable: Throwable? = null) {
    class Loading<T>:Resources<T>()
    class Success<T>(data: T?):Resources<T>(data = data)
    class Error<T>(throwable: Throwable?):Resources<T>(throwable = throwable)
}