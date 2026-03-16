package com.example.viajes.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ViajesUserInput(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        // 1. Icono principal (izquierda)
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // 2. Texto (Le damos weight(1f) para que ocupe todo el centro y empuje la flecha a la derecha)
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.weight(1f)
        )

        // 3. Icono de flecha indicadora (derecha)
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Seleccionar",
            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f), // Un poco más transparente para que sea sutil
            modifier = Modifier.size(20.dp)
        )
    }
}

/*
@Composable
fun ViajesUserInput(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            // El clickable le da la animación de onda (ripple) al tocarlo
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            // Blanco con un poco de transparencia para el ícono
            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary // Texto blanco puro
        )
    }
}*/
