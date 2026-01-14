package agalfioni.wintertravelgallery.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    onSurface = TextPrimary,
    outline = Outline
)

data class CustomColors(
    val bgMainGradientDark: Color = Color(0xFFDAEEFF),
    val bgMainGradientLight: Color = Color(0xFFEDF7FF),
    val bgErrorGradientDark: Color = Color(0xFFFDCAD6),
    val bgErrorGradientLight: Color = Color(0xFFFFFBFC),
)

// 2. Create a "Local" provider with a default dummy value
val LocalCustomColors = staticCompositionLocalOf { CustomColors() }

@Composable
fun WinterTravelGalleryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // 2. Provide them down the tree
    CompositionLocalProvider(LocalCustomColors provides CustomColors()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            content = content
        )
    }
}

object AppTheme {
    val customColors: CustomColors
        @Composable
        get() = LocalCustomColors.current
}