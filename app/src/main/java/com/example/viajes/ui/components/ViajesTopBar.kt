package com.example.viajes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

// 1. Definimos nuestras pestañas de forma segura
enum class ViajesPestana(val titulo: String) {
    VUELOS("Vuelos"),
    DORMIR("Dormir"),
    COMER("Comer")
}

@Composable
fun ViajesTopBar(
    pestanaActual: ViajesPestana,
    onPestanaSeleccionada: (ViajesPestana) -> Unit,
    modifier: Modifier = Modifier
) {
    // Surface envuelve todo para darle el color de fondo primario (Púrpura)
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.statusBarsPadding()
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            // 2. El Logo (Usamos un ícono de Material por ahora)
            Icon(
                imageVector = Icons.Default.Flight,
                contentDescription = "Logo Viajes",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(end = 32.dp)
            )

            // 3. El grupo de pestañas
            Row(
                modifier = Modifier.selectableGroup(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                ViajesPestana.entries.forEach { pestana ->
                    val seleccionada = pestanaActual == pestana

                    // Si está seleccionada es blanca, si no, es blanca con opacidad
                    val colorTexto = if (seleccionada) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
                    }

                    Text(
                        text = pestana.titulo,
                        style = MaterialTheme.typography.titleMedium,
                        color = colorTexto,
                        modifier = Modifier.selectable(
                            selected = seleccionada,
                            onClick = { onPestanaSeleccionada(pestana) },
                            role = Role.Tab // Importante para la accesibilidad
                        )
                    )
                }
            }
        }
    }
}