package com.example.viajes.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViajesDatePicker(
    onDismiss: () -> Unit,
    onFechasConfirmadas: (Long?, Long?) -> Unit
) {
    // El estado que controla qué fechas están seleccionadas
    val dateRangePickerState = rememberDateRangePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    // Devolvemos los milisegundos de la fecha de inicio y fin
                    onFechasConfirmadas(
                        dateRangePickerState.selectedStartDateMillis,
                        dateRangePickerState.selectedEndDateMillis
                    )
                }
            ) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        // El componente visual del calendario
        DateRangePicker(
            state = dateRangePickerState,
            title = {
                Text(text = "Selecciona las fechas de tu viaje", modifier = Modifier.padding(16.dp))
            },
            headline = {
                // Puedes personalizar el texto de la cabecera aquí si lo deseas
            },
            showModeToggle = false // Oculta el botón para escribir la fecha manualmente
        )
    }
}