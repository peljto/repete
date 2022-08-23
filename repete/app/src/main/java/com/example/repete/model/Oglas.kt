package com.example.repete.model

import com.google.firebase.Timestamp

data class Oglas(
    val userId: String = "",
    val subject: String = "",
    val typeOfEducation: String = "",
    val price: String = "",
    val contact: String = "",
    val timestamp: Timestamp = Timestamp.now(),
    val oglasId: String = "",
    val document: String = ""
)
