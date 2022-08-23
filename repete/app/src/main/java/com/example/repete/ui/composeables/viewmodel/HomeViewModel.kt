package com.example.repete.ui.composeables.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repete.model.Oglas
import com.example.repete.repository.Resources
import com.example.repete.repository.StorageRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: StorageRepository = StorageRepository()
): ViewModel() {
    var homeUiState by mutableStateOf(HomeUiState())

    val user = repository.user()
    val hasUser: Boolean
    get() = repository.hasUser()
    private val userId: String
    get() = repository.getUserId()

    fun loadOglas() { //1:00:37
        if(hasUser){
            getUserOglas()
        }
    }

    private fun getUserOglas() = viewModelScope.launch {
        repository.getUserOglas().collect{ //AKO NEBUDE OVAKO RADILO JE ZATO STA TI FALI USERID ODE, DA SE PRIKAZUJU SAMO OBJAVE OD ODREDENOG USERA
            homeUiState = homeUiState.copy(oglasList = it)
        }
    }

    fun signOut() = repository.signOut()





}

data class HomeUiState(
    val oglasList:Resources<List<Oglas>> = Resources.Loading()
)