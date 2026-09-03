package com.example.instantgarage.data.model

import java.io.Serializable


data class Mechanic(
    val address: String,
    val distance: Double,
    val id: Int,
    val isOpen: Boolean,
    val location: String,
    val name: String,
    val phone: String,
    val rating: Double,
    val services: List<String>,
    val workingHours: String
) : Serializable
