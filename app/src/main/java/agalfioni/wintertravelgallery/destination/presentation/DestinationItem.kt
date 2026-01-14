package agalfioni.wintertravelgallery.destination.presentation

import agalfioni.wintertravelgallery.R
import agalfioni.wintertravelgallery.ui.theme.AppTheme
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@Composable
fun DestinationItem(
    destinationUrl: String,
    modifier: Modifier = Modifier,
    destinationTitle: String? = null,
    onDestinationClicked: (String) -> Unit = {},
) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(12.dp))
    ) {
        var isLoading by remember { mutableStateOf(true) }
        var isError by remember { mutableStateOf(false) }
        var isLoadedSuccessfully by remember { mutableStateOf(false) }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .matchParentSize()
                .clickable(
                    enabled = isLoadedSuccessfully,
                    onClick = { destinationTitle?.let { onDestinationClicked(it) } }
                )
        ) {
            // Using lightest AsyncImage
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(destinationUrl)
                    .listener(
                        onStart = { isLoading = true },
                        onSuccess = { _, _ ->
                            isLoadedSuccessfully = true
                            isLoading = false
                            isError = false
                        },
                        onError = { imageRequest, errorResult ->
                            Log.d("error", "MainDestinationItem: ${imageRequest.data} $errorResult")
                            isLoading = false
                            isError = true
                        }
                    )
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            if (isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .matchParentSize()
                        .background(Brush.verticalGradient(
                            colors = listOf(
                                AppTheme.customColors.bgMainGradientDark,
                                AppTheme.customColors.bgMainGradientLight
                            )
                        ))
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                }
            }

            if (isError) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .matchParentSize()
                        .background(Brush.verticalGradient(
                            colors = listOf(
                                AppTheme.customColors.bgErrorGradientDark,
                                AppTheme.customColors.bgErrorGradientLight
                            )
                        ))
                ) {
                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_alert_triangle), contentDescription = "Error")
                }
            }
        }

        if (isLoadedSuccessfully && destinationTitle != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = destinationTitle,
                        color = colorResource(R.color.white),
                        fontWeight = FontWeight.Medium,
                        lineHeight = 20.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(colorResource(R.color.white))
                            .size(22.dp),
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right),
                            contentDescription = "Error",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DestinationItemPreview() {
    MaterialTheme {
        DestinationItem(
            destinationTitle = Destination.SWISS.title,
            destinationUrl = Destination.SWISS.imageUrls.first()
        )
    }
}


