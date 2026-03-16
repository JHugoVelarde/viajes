package com.example.viajes.data.repository

import com.example.viajes.data.model.Destino
import com.example.viajes.data.model.destinosDePrueba
import kotlinx.coroutines.delay

// 1. La interfaz que define qué puede hacer nuestra app
interface DestinoRepository {
    suspend fun getDestinos(): List<Destino>
}

// 2. La implementación falsa que simula una descarga de internet
class FakeDestinoRepositoryImpl : DestinoRepository {
    override suspend fun getDestinos(): List<Destino> {
        delay(1500)     // Simulamos que el internet tarda 1.5 segundos
        return destinosDePrueba
    }
}