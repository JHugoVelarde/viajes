package com.example.viajes.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.viajes.data.model.destinosDePrueba
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun DetailScr(
    destinoId: Int,
    onBackClick: () -> Unit
) {
    val destino = destinosDePrueba.find { it.id == destinoId }

    if (destino == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Destino no encontrado")
        }
        return
    }

    val posicionDestino = LatLng(destino.latitud, destino.longitud)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(posicionDestino, 13f) // Zoom un poco más cercano
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // --- SECCIÓN SUPERIOR: IMAGEN (Altura fija para evitar saltos) ---
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(280.dp) // Altura exacta y perfecta
        ) {
            AsyncImage(
                model = destino.imageUrl,
                contentDescription = "Imagen de ${destino.ciudad}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(Color.Black.copy(alpha = 0.7f), Color.Transparent)
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.background(
                        color = Color.Black.copy(alpha = 0.4f),
                        shape = CircleShape
                    )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
            }
        }

        // --- SECCIÓN CENTRAL: TEXTOS ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 24.dp, vertical = 16.dp) // Margen ajustado
        ) {
            Text(
                text = destino.ciudad,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = destino.descripcion,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }

        // --- SECCIÓN INFERIOR: MAPA DE GOOGLE ---
        // weight(1f) le dice: "Toma el 100% del espacio que quede hacia abajo"
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = posicionDestino),
                    title = destino.ciudad,
                    snippet = "¡Excelente destino para viajar!"
                )
            }
        }
    }
}

/*
@Composable
fun DetailScr(
    destinoId: Int,
    onBackClick: () -> Unit
) {
    // Buscamos el destino en nuestra lista temporal
    val destino = destinosDePrueba.find { it.id == destinoId }

    if (destino == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Destino no encontrado")
        }
        return
    }

    // Diseño de pantalla completa con la imagen de fondo
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = destino.imageUrl,
            contentDescription = "Imagen de ${destino.ciudad}",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Una capa oscura en la parte superior para que el botón de volver y el texto se lean bien
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    verticalGradient(
                        colors = listOf(Color.Black.copy(alpha = 0.7f), Color.Transparent)
                    )
                )
        )

        // Botón de retroceso y Título
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = destino.ciudad,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )
        }
    }
}*/
