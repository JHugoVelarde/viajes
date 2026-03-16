package com.example.viajes.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.viajes.data.model.Destino

@Composable
fun DestinoItem(
    destino: Destino,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp), // Espaciado entre cada elemento de la lista
        verticalAlignment = Alignment.CenterVertically
    ) {
        // La imagen asíncrona de Coil
        AsyncImage(
            model = destino.imageUrl,
            contentDescription = "Imagen de ${destino.ciudad}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp)) // Bordes suavemente redondeados
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Los textos
        Column {
            Text(
                text = destino.ciudad,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = destino.descripcion,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}