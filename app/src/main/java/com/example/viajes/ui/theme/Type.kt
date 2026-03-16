package com.example.viajes.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.viajes.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Raleway")

val viajesFontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.Bold)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = viajesFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = viajesFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = viajesFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = viajesFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = viajesFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    )
)


























/*
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    */
/* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    *//*

)*/
