package com.example.navigationlab.model

import com.example.navigationlab.navigation.AppDestinations

// Незмінний опис поточного стану застосунку.
data class LabState(
    val destination: AppDestinations = AppDestinations.COUNTER,
    val count: Int = 0,
    val messageChanged: Boolean = false,
    val score: Int = 0,
)
