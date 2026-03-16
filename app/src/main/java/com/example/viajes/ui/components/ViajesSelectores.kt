package com.example.viajes.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viajes.data.model.Destino

// 1. EL DIÁLOGO PARA CANTIDAD DE PERSONAS
@Composable
fun SelectorPersonasDialog(
    adultosActuales: Int,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var cantidad by remember { mutableIntStateOf(adultosActuales) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Número de viajeros") },
        text = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Adultos", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
                IconButton(onClick = { if (cantidad > 1) cantidad-- }) {
                    Icon(Icons.Default.Remove, contentDescription = "Menos")
                }
                Text(text = "$cantidad", style = MaterialTheme.typography.titleLarge)
                IconButton(onClick = { if (cantidad < 10) cantidad++ }) {
                    Icon(Icons.Default.Add, contentDescription = "Más")
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(cantidad) }) { Text("Aceptar") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}

// 2. EL PANEL INFERIOR PARA ELEGIR DESTINO
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorDestinoSheet(
    destinos: List<Destino>,
    onDismiss: () -> Unit,
    onDestinoSeleccionado: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        LazyColumn(modifier = Modifier.padding(bottom = 32.dp)) {
            item {
                Text(
                    text = "¿A dónde viajas?",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(destinos) { destino ->
                ListItem(
                    headlineContent = { Text(destino.ciudad) },
                    supportingContent = { Text(destino.descripcion) },
                    modifier = Modifier.clickable { onDestinoSeleccionado(destino.ciudad) }
                )
            }
        }
    }
}