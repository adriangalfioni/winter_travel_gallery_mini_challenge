package agalfioni.wintertravelgallery.destination.presentation

import agalfioni.wintertravelgallery.ui.theme.AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationList(
    modifier: Modifier = Modifier,
    onDestinationClicked: (String) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.customColors.bgMainGradientDark),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Winter Travel Gallery",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 28.sp,
                    )
                },
                windowInsets = WindowInsets.statusBars.add(WindowInsets(top = 32.dp)),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->

        val lazyGridState = rememberLazyGridState()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = lazyGridState,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            items(
                Destination.entries,
                key = { it.title }
            ) { destination ->
                DestinationItem(
                    modifier = Modifier
                        .height(220.dp)
                        .width(186.dp),
                    destinationTitle = destination.title,
                    destinationUrl = destination.imageUrls.first(),
                    onDestinationClicked = onDestinationClicked
                )
            }
        }
    }
}

@Preview
@Composable
fun DestinationListPreview() {
    MaterialTheme {
        DestinationList()
    }
}
