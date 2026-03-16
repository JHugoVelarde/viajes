package com.example.viajes.data.model

data class Destino(
    val id: Int,
    val ciudad: String,
    val descripcion: String,
    val imageUrl: String,
    val latitud: Double,
    val longitud: Double
)

// Datos falsos para probar la interfaz rápidamente
val destinosDePrueba = listOf(
    Destino(1, "París", "Francia • Vuelos desde $400", "https://images.unsplash.com/photo-1511739001486-6bfe10ce785f?auto=format&fit=crop&w=600&q=80", 48.8566, 2.3522),
    Destino(2, "Tokio", "Japón • Vuelos desde $850", "https://images.unsplash.com/photo-1540959733332-eab4deabeeaf?auto=format&fit=crop&w=600&q=80", 35.6762, 139.6503),
    Destino(3, "Roma", "Italia • Vuelos desde $500", "https://images.unsplash.com/photo-1552832230-c0197dd311b5?auto=format&fit=crop&w=600&q=80", 41.9028, 12.4964),
    Destino(4, "Nueva York", "Estados Unidos • Vuelos desde $300", "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9?auto=format&fit=crop&w=600&q=80", 40.7128, -74.0060),
    Destino(5, "Kioto", "Japón • Vuelos desde $820", "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?auto=format&fit=crop&w=600&q=80", 35.0116, 135.7681),
    Destino(1, "París", "Francia • Vuelos desde $400", "https://images.unsplash.com/photo-1511739001486-6bfe10ce785f?auto=format&fit=crop&w=600&q=80", 48.8566, 2.3522),
    Destino(2, "Tokio", "Japón • Vuelos desde $850", "https://images.unsplash.com/photo-1540959733332-eab4deabeeaf?auto=format&fit=crop&w=600&q=80", 35.6762, 139.6503),
    Destino(3, "Roma", "Italia • Vuelos desde $500", "https://images.unsplash.com/photo-1552832230-c0197dd311b5?auto=format&fit=crop&w=600&q=80", 41.9028, 12.4964),
    Destino(4, "Nueva York", "Estados Unidos • Vuelos desde $300", "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9?auto=format&fit=crop&w=600&q=80", 40.7128, -74.0060),
    Destino(5, "Kioto", "Japón • Vuelos desde $820", "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?auto=format&fit=crop&w=600&q=80", 35.0116, 135.7681)
)