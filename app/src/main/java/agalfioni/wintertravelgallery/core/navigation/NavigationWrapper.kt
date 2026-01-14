package agalfioni.wintertravelgallery.core.navigation

import agalfioni.wintertravelgallery.destination.presentation.DestinationGallery
import agalfioni.wintertravelgallery.destination.presentation.DestinationList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay

@Composable
fun NavigationWrapper(
    modifier: Modifier = Modifier
) {
    val backStack = remember { mutableStateListOf<NavKey>(Route.DestinationsList) }

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Route.DestinationsList -> NavEntry(key) {
                    DestinationList(
                        onDestinationClicked = {
                            backStack.add(Route.DestinationGallery(it))
                        }
                    )
                }

                is Route.DestinationGallery -> NavEntry(key) {
                    DestinationGallery(
                        destinationTitle = key.destinationTitle,
                        onBackClicked = { backStack.removeLastOrNull() }
                    )
                }

                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )

}