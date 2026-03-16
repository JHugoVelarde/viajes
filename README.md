# ✈️ ViajesApp

![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-blue.svg?logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Modern%20UI-4285F4.svg?logo=android)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%2B%20UDF-success.svg)
![Dagger Hilt](https://img.shields.io/badge/Dependency%20Injection-Hilt-red.svg)

Una aplicación de demostración de viajes nativa para Android construida 100% con **Jetpack Compose**. Este proyecto toma inspiración visual del caso de estudio "Crane" de Google, pero lo reescribe desde cero aplicando las prácticas, bibliotecas y arquitecturas más recientes del ecosistema Android (2024/2025).

Esta aplicación demuestra el uso avanzado de animaciones (Backdrop Scaffold personalizado), estado reactivo, inyección de dependencias, navegación fuertemente tipada y la integración nativa de Google Maps en Compose.

## 📷 Capturas de pantallas

| Pantalla Inicial | Número de viajeros | Selección fecha | Detalle con Mapa |
| :---: | :---: | :---: | :---: |
| <img src="/assets/viajes01.jpeg" width="250"/> | <img src="/assets/viajes02.jpeg" width="250"/> | <img src="/assets/viajes03.jpeg" width="250"/> | <img src="/assets/viajes05.jpeg" width="250"/> |

## ✨ Características Principales

* **UI Dinámica e Interactiva:** Implementación de un patrón de diseño *Backdrop* personalizado que reacciona al scroll del usuario.
* **Material Design 3:** Uso de componentes modernos como `DateRangePicker` (Calendario), `ModalBottomSheet` y `AlertDialog` para la selección de datos.
* **Integración de Mapas:** Pantalla de detalles inmersiva con `maps-compose` para mostrar la ubicación exacta de los destinos.
* **Arquitectura Robusta:** Patrón **MVVM** con **Unidirectional Data Flow (UDF)** utilizando `StateFlow`.
* **Navegación Type-Safe:** Uso de Compose Navigation 2.8+ con clases y objetos serializables (sin rutas de texto propensas a errores).
* **Carga de Imágenes Optimizada:** Integración de **Coil 3** para la carga asíncrona y en caché de imágenes de alta calidad.

## 🛠️ Stack Tecnológico

El proyecto está construido utilizando el stack moderno y recomendado por Google para el desarrollo Android:

* **Lenguaje:** [Kotlin](https://kotlinlang.org/)
* **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
* **Arquitectura:** MVVM (Model-View-ViewModel)
* **Inyección de Dependencias:** [Dagger Hilt](https://dagger.dev/hilt/)
* **Navegación:** [Type-Safe Navigation Compose](https://developer.android.com/guide/navigation/design/type-safety)
* **Gestión de Estados:** `ViewModel`, `StateFlow` y corrutinas.
* **Imágenes Asíncronas:** [Coil 3](https://coil-kt.github.io/coil/)
* **Mapas:** [Google Maps SDK for Compose](https://github.com/googlemaps/android-maps-compose)
* **Gestión de Dependencias:** Gradle Version Catalogs (`libs.versions.toml`)

## 📂 Estructura del Proyecto

El código está organizado siguiendo las directrices de arquitectura moderna de Android, separando claramente las responsabilidades en capas (Datos, Interfaz de Usuario y Navegación) para asegurar escalabilidad y fácil mantenimiento:

```text
com.example.viajes
│
├── di/                     # Módulos de inyección de dependencias (Hilt)
│   └── RepositoryModule.kt # Provisión del repositorio al ViewModel
│
├── data/                   # Capa de datos y lógica de negocio
│   ├── model/              # Modelo de dominio (Destino)
│   └── repository/         # Interfaz y origen de datos (Fake repository simulando red)
│
├── navigation/             # Configuración de Compose Navigation Type-Safe
│   ├── AppNavigation.kt    # Grafo de navegación y NavHost
│   └── Routes.kt           # Definición de objetos y clases serializables para rutas
│
└── ui/                     # Capa de presentación (Jetpack Compose)
    ├── components/         # Componentes reutilizables (Backdrop, DatePicker, Selectores, Items)
    ├── screens/            # Pantallas de la aplicación y sus ViewModels
    │   ├── home/           # Pantalla principal (UI interactiva, ViewModel, UiState)
    │   └── detail/         # Pantalla de detalle (UI inmersiva con Google Maps)
    └── theme/              # Configuración visual de Material Design 3 (Colors, Typography)
```

## 🚀 Instalación y Configuración

Para ejecutar este proyecto en tu máquina local, necesitarás configurar tu propia clave de la API de Google Maps, ya que no se incluye en el repositorio por razones de seguridad.

1. **Clona el repositorio:**
   ```bash
   git clone [https://github.com/JHugoVelarde/ViajesApp.git](https://github.com/JHugoVelarde/ViajesApp.git)

2. **Obtén una API Key de Google Maps:**
- Dirígete a la Consola de Google Cloud.
- Crea un proyecto y habilita el Maps SDK for Android.
- Genera una clave de API (API Key) en la sección de Credenciales.

3. **Configura la clave en el proyecto:**
- En la raíz del proyecto (al mismo nivel que la carpeta app/), crea o abre el archivo local.properties.
- Añade la siguiente línea con tu clave real:
  ```bash
  Properties
  MAPS_API_KEY=TuClaveDeApiAqui

> [!Note]
> *El archivo local.properties está ignorado en .gitignore para proteger tus credenciales.*

4. Ejecuta el proyecto:
- Abre el proyecto en Android Studio.
- Sincroniza Gradle (File > Sync Project with Gradle Files).
- Selecciona tu emulador o dispositivo físico y presiona Run.

## 💡 Lecciones Clave de Arquitectura y UI

- **Unidirectional Data Flow (UDF):** La interfaz de usuario es completamente pasiva y reactiva. Simplemente observa y reacciona a los estados (`Loading`, `Success`, `Error`) emitidos por el `ViajesViewModel` a través de `StateFlow`, aislando toda la lógica de negocio de la capa visual.
- **Patrón Slot API en Compose:** El componente personalizado `ViajesBackdrop` fue construido utilizando el patrón *Slot API* (recibiendo `backLayerContent` y `frontLayerContent` como lambdas). Esto lo convierte en un contenedor altamente reutilizable que no está acoplado a ningún dato específico.
- **Type-Safe Navigation:** La navegación entre el inicio y el detalle se realiza pasando el `destinoId` a través de una clase de datos (data class) fuertemente tipada y serializable, eliminando por completo la fragilidad y los errores asociados a las antiguas rutas basadas en strings (`"ruta/{id}"`).

## 📄 Licencia

Este proyecto está licenciado bajo la Licencia Apache 2.0 - consulta el archivo [LICENSE](LICENSE) para más detalles.
