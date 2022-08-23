package com.example.repete.ui.composeables.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.repete.repository.StorageRepository
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import javax.security.auth.Subject

class FormViewModel(
    private val repository: StorageRepository = StorageRepository()
): ViewModel() {
    var formUiState by mutableStateOf(FormUiState())
        private set


    private val hasUser: Boolean
    get() = repository.hasUser()//restriction of sending data

    private val user:FirebaseUser?
    get() = repository.user()



    fun onSubjectChange(subject: String) {
        formUiState = formUiState.copy(subject = subject)
    }

    fun onTypeOfEducationChange(typeOfEducation: String) {
        formUiState = formUiState.copy(typeOfEducation = typeOfEducation)
    }

    fun onPriceChange(price: String) {
        formUiState = formUiState.copy(price = price)
    }

    fun onContactChange(contact: String) {
        formUiState = formUiState.copy(contact = contact)
    }

    fun addOglas(){
        if(hasUser){//ako je logiran onda tek dopustit dodavanje
            repository.addOglas(
                userId = user!!.uid,
                subject = formUiState.subject,
                price = formUiState.price,
                typeOfEducation = formUiState.typeOfEducation,
                contact = formUiState.contact,
                timestamp = Timestamp.now()
            ){
                formUiState = formUiState.copy(oglasAddedStatus = it)
            }
        }

    }

  /*  fun getOglas(oglasId: String){
        repository.getOglas(oglasId = oglasId, onError = {}){
            formUiState = formUiState.copy(sel)
        }
    }
*/

    fun resetOglasAddedStatus(){
        formUiState = formUiState.copy(
            oglasAddedStatus = false,
        )
    }

    fun resetState(){
        formUiState = FormUiState()
    }



}


data class FormUiState(
    val subject: String = "",
    val typeOfEducation: String = "",
    val price: String = "",
    val contact: String = "",
    val oglasAddedStatus: Boolean = false,
)