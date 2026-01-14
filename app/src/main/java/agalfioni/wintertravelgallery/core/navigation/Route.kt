package agalfioni.wintertravelgallery.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey {

    @Serializable
    data object DestinationsList: Route

    @Serializable
    data class DestinationGallery(val destinationTitle: String): Route
}