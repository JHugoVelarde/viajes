package com.example.viajes.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.viajes.data.model.destinosDePrueba
import com.example.viajes.ui.components.DestinoItem
import com.example.viajes.ui.components.SelectorDestinoSheet
import com.example.viajes.ui.components.SelectorPersonasDialog
import com.example.viajes.ui.components.ViajesBackdrop
import com.example.viajes.ui.components.ViajesDatePicker
import com.example.viajes.ui.components.ViajesPestana
import com.example.viajes.ui.components.ViajesTopBar
import com.example.viajes.ui.components.ViajesUserInput
import kotlinx.datetime.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun MainViajes(
    viewModel: ViajesViewModel,
    onDestinoClick: (Int) -> Unit
) {
    // Escuchamos el estado del ViewModel reactivamente
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Estado que recuerda qué pestaña está activa
    var pestanaSeleccionada by remember { mutableStateOf(ViajesPestana.VUELOS) }

    // Creamos un estado para observar el scroll de la lista
    val estadoDeLaLista = rememberLazyListState()

    // Calculamos matemáticamente si estamos al principio de la lista.
    // derivedStateOf es súper eficiente, solo avisa a Compose cuando el resultado cambia.
    val mostrarFiltros by remember {
        derivedStateOf {
            estadoDeLaLista.firstVisibleItemIndex == 0 && estadoDeLaLista.firstVisibleItemScrollOffset == 0
        }
    }

    // 1. Estado para mostrar u ocultar el diálogo del calendario
    var mostrarCalendario by remember { mutableStateOf(false) }

    // 2. Estado para guardar el texto formateado de las fechas
    var textoFechasVuelo by remember { mutableStateOf("Seleccionar fechas") }

    // 3. Pequeña función para formatear los milisegundos a "dd MMM" (ej. 15 Mar)
    val formatearFecha = { millis: Long ->
        // Usamos la ruta absoluta 'java.time.Instant' para evitar conflictos con 'kotlinx.datetime'
        val localDate = java.time.Instant.ofEpochMilli(millis)
            .atZone(java.time.ZoneId.of("UTC"))
            .toLocalDate()
        val formatter = java.time.format.DateTimeFormatter.ofPattern("dd MMM", java.util.Locale("es", "ES"))
        localDate.format(formatter)
    }

    // Estados para las Personas
    var mostrarDialogoPersonas by remember { mutableStateOf(false) }
    var cantidadAdultos by remember { mutableIntStateOf(1) }

    // Estados para el Destino
    var mostrarSheetDestino by remember { mutableStateOf(false) }
    var textoDestinoVuelo by remember { mutableStateOf("Elegir destino") }

    // Opcional pero recomendado: Si cambiamos de pestaña (Vuelos -> Dormir),
    // hacemos que la lista vuelva arriba automáticamente para mostrar los filtros.

    LaunchedEffect(pestanaSeleccionada) {
        estadoDeLaLista.scrollToItem(0)
    }

    Scaffold(
        topBar = {
            ViajesTopBar(
                pestanaActual = pestanaSeleccionada,
                onPestanaSeleccionada = { nuevaPestana ->
                    pestanaSeleccionada = nuevaPestana
                }
            )
        }
    ) { innerPadding ->
        ViajesBackdrop(
            mostrarFiltros = mostrarFiltros,
            modifier = Modifier.padding(innerPadding),
            backLayerContent = {
                // El contenido cambia dinámicamente según la pestaña seleccionada
                when (pestanaSeleccionada) {
                    ViajesPestana.VUELOS -> BuscadorVuelos(
                        textoPersonas = if (cantidadAdultos == 1) "1 Adulto" else "$cantidadAdultos Adultos",
                        onPersonasClick = { mostrarDialogoPersonas = true },
                        textoFechas = textoFechasVuelo,
                        onFechasClick = { mostrarCalendario = true },
                        textoDestino = textoDestinoVuelo,
                        onDestinoClick = { mostrarSheetDestino = true }
                    )
                    ViajesPestana.DORMIR -> BuscadorDormir()
                    ViajesPestana.COMER -> BuscadorComer()
                }

                /*// Contenido temporal de la capa trasera
                Text(
                    text = "Buscador para ${pestanaSeleccionada.titulo} irá aquí...",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )*/
            },
            frontLayerContent = {
                // Evaluamos el estado de la UI
                when (val state = uiState) {
                    is ViajesUiState.Loading -> {
                        // Mostramos un círculo girando mientras carga
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    is ViajesUiState.Success -> {
                        // Si hay éxito, mostramos la lista con los datos reales del state
                        LazyColumn(
                            state = estadoDeLaLista,
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(bottom = 24.dp)
                        ) {
                            item {
                                Text(
                                    text = "Explorar Destinos",
                                    style = MaterialTheme.typography.headlineMedium,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                            }
                            items(state.destinos) { destino -> // <-- Usamos state.destinos en lugar de la lista estática
                                DestinoItem(
                                    destino = destino,
                                    onClick = { onDestinoClick(destino.id) }
                                )
                            }
                        }
                    }
                    is ViajesUiState.Error -> {
                        // Si hay error, mostramos un mensaje
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(text = state.message, color = MaterialTheme.colorScheme.error)
                        }
                    }
                }
                /*LazyColumn(
                    state = estadoDeLaLista,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 24.dp) // Espacio extra al final del scroll
                ) {
                    // El título principal de la sección
                    item {
                        Text(
                            text = "Explorar Destinos",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }

                    // La lista mágica que se renderiza dinámicamente
                    items(destinosDePrueba) { destino ->
                        DestinoItem(
                            destino = destino,
                            onClick = { onDestinoClick(destino.id) }
                        )
                    }
                }*/
                /*// Contenido temporal de la capa frontal
                Text(
                    text = "Resultados de ${pestanaSeleccionada.titulo}",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineMedium
                )*/
            }
        )
    }

    // Si el estado es true, mostramos el calendario flotante
    if (mostrarCalendario) {
        ViajesDatePicker(
            onDismiss = { mostrarCalendario = false },
            onFechasConfirmadas = { inicioMillis, finMillis ->
                mostrarCalendario = false

                // Si el usuario seleccionó ambas fechas, actualizamos el texto
                if (inicioMillis != null && finMillis != null) {
                    val inicio = formatearFecha(inicioMillis)
                    val fin = formatearFecha(finMillis)
                    textoFechasVuelo = "$inicio - $fin"
                } else if (inicioMillis != null) {
                    textoFechasVuelo = formatearFecha(inicioMillis)
                }
            }
        )
    }

    // Si el estado es true, mostramos el selector de Personas
    if (mostrarDialogoPersonas) {
        SelectorPersonasDialog(
            adultosActuales = cantidadAdultos,
            onDismiss = { mostrarDialogoPersonas = false },
            onConfirm = { nuevaCantidad ->
                cantidadAdultos = nuevaCantidad
                mostrarDialogoPersonas = false
            }
        )
    }

    // Si el estado es true, mostramos el BottomSheet de Destinos
    if (mostrarSheetDestino) {
        SelectorDestinoSheet(
            destinos = destinosDePrueba,
            onDismiss = { mostrarSheetDestino = false },
            onDestinoSeleccionado = { ciudad ->
                textoDestinoVuelo = ciudad
                mostrarSheetDestino = false
            }
        )
    }
}

@Composable
fun BuscadorVuelos(
    textoPersonas: String,
    onPersonasClick: () -> Unit,
    textoFechas: String,
    onFechasClick: () -> Unit,
    textoDestino: String,
    onDestinoClick: () -> Unit
) {
    Column {
        ViajesUserInput(text = textoPersonas, icon = Icons.Default.Person, onClick = onPersonasClick)
        HorizontalDivider(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f))

        ViajesUserInput(text = textoFechas, icon = Icons.Default.DateRange, onClick = onFechasClick)
        HorizontalDivider(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f))

        ViajesUserInput(text = textoDestino, icon = Icons.Default.LocationOn, onClick = onDestinoClick)
    }
}

@Composable
fun BuscadorDormir() {
    Column {
        ViajesUserInput(text = "1 Habitación, 2 Adultos", icon = Icons.Default.Hotel, onClick = { })
        HorizontalDivider(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f))
        ViajesUserInput(text = "Seleccionar fechas", icon = Icons.Default.DateRange, onClick = { })
        HorizontalDivider(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f))
        ViajesUserInput(text = "Elegir ciudad", icon = Icons.Default.LocationOn, onClick = { })
    }
}

@Composable
fun BuscadorComer() {
    Column {
        ViajesUserInput(text = "2 Personas", icon = Icons.Default.Person, onClick = { })
        HorizontalDivider(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f))
        ViajesUserInput(text = "Elegir restaurante o tipo de comida", icon = Icons.Default.Restaurant, onClick = { })
    }
}