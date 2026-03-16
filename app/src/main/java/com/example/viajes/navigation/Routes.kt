package com.example.viajes.navigation

import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data class DestinoDetailRoute(val destinoId: Int)
