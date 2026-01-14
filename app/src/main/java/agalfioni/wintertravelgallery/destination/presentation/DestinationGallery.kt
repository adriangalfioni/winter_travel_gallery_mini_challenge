package agalfioni.wintertravelgallery.destination.presentation

import agalfioni.wintertravelgallery.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationGallery(
    destinationTitle: String,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {}
) {
    val navigationIconSize = 40.dp
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                navigationIcon = {
                    OutlinedButton(
                        onClick = onBackClicked,
                        modifier= Modifier.size(navigationIconSize),
                        shape = RoundedCornerShape(12.dp),
                        border= BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                        contentPadding = PaddingValues(0.dp),  //avoid the little icon
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(R.color.white))
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = navigationIconSize), // compensate nav icon
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = destinationTitle,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp,
                        )
                    }
                },
                windowInsets = WindowInsets.statusBars.add(WindowInsets(top = 32.dp)),
            )
        }
    ) { innerPadding ->

        val imageUrls = remember(destinationTitle) {
            destinationByTitle[destinationTitle]
                ?.imageUrls
                .orEmpty()
        }

        val lazyGridState = rememberLazyGridState()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = lazyGridState,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            items(
                items = imageUrls,
                key = { it }
            ) { destinationImageUrl ->
                DestinationItem(
                    modifier = Modifier
                        .height(220.dp)
                        .width(188.dp),
                    destinationUrl = destinationImageUrl
                )
            }
        }
    }
}

@Preview
@Composable
fun DestinationGalleryPreview() {
    MaterialTheme {
        DestinationGallery(destinationTitle = "Alps")
    }
}
