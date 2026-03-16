package com.example.viajes.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ViajesBackdrop(
    mostrarFiltros: Boolean,
    modifier: Modifier = Modifier,
    backLayerContent: @Composable () -> Unit,
    frontLayerContent: @Composable () -> Unit
) {
    // 1. El fondo base: Todo es púrpura por defecto
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // 2. La Capa Trasera (Back Layer)
            // Aquí irán los campos de texto para buscar (Destino, Fechas, etc.)
            AnimatedVisibility(
                visible = mostrarFiltros,
                enter = expandVertically(animationSpec = tween(500)) + fadeIn(),
                exit = shrinkVertically(animationSpec = tween(500)) + fadeOut()
            ) {
                Box(
                    modifier = Modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp, bottom = 12.dp)
                ) {
                    backLayerContent()
                }
            }

            // 3. La Capa Frontal (Front Layer)
            // Usamos Surface para darle color blanco, sombra y bordes redondeados
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f) // Esto hace que ocupe todo el espacio restante hacia abajo
            ) {
                Box(modifier = Modifier.padding(20.dp)) {
                    frontLayerContent()
                }
            }
        }
    }
}