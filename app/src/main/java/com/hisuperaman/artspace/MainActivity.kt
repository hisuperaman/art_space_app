package com.hisuperaman.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hisuperaman.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceLayout(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtworkWall(imageRes: Int, modifier: Modifier = Modifier) {
    Surface(
        shadowElevation = 10.dp,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ArtworkDescriptor(description: String, artist: String, year: String, modifier: Modifier = Modifier) {
    val annotatedArtistAndYear = buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontWeight = FontWeight.SemiBold
        )) {
            append("$artist ")
        }
        withStyle(style = SpanStyle(
            fontWeight = FontWeight.Light
        )) {
            append("($year)")
        }
    }

    Surface(
        tonalElevation = 10.dp,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Text(
                text = description,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = annotatedArtistAndYear
            )
        }
    }
}

@Composable
fun ArtworkController(onPreviousClick: () -> Unit, onNextClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier
                .weight(0.4f)
        ) {
            Text(
                text = "Previous",
            )
        }
        Spacer(modifier = Modifier.weight(0.2f))
        Button(
            onClick = onNextClick,
            modifier = Modifier
                .weight(0.4f)
        ) {
            Text(
                text = "Next",
            )
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    val imageList = listOf(
        hashMapOf("res" to R.drawable.i1, "description" to "Batman Beyond 1", "artist" to "Batman", "year" to "2024"),
        hashMapOf("res" to R.drawable.i2, "description" to "Batman Beyond 2", "artist" to "Batman", "year" to "2024"),
        hashMapOf("res" to R.drawable.i3, "description" to "Batman Beyond 3", "artist" to "Batman", "year" to "2024"),
        hashMapOf("res" to R.drawable.i4, "description" to "Across the Spider-Verse 1", "artist" to "Spiderman", "year" to "2023"),
        hashMapOf("res" to R.drawable.i5, "description" to "Across the Spider-Verse 2", "artist" to "Spiderman", "year" to "2023"),
        hashMapOf("res" to R.drawable.i6, "description" to "Across the Spider-Verse 3", "artist" to "Spiderman", "year" to "2023"),
        hashMapOf("res" to R.drawable.i7, "description" to "Across the Spider-Verse 4", "artist" to "Spiderman", "year" to "2023"),
        hashMapOf("res" to R.drawable.i8, "description" to "Across the Spider-Verse 5", "artist" to "Spiderman", "year" to "2023"),
        hashMapOf("res" to R.drawable.i9, "description" to "Across the Spider-Verse 6", "artist" to "Spiderman", "year" to "2023"),
        hashMapOf("res" to R.drawable.i10, "description" to "Across the Spider-Verse 7", "artist" to "Spiderman", "year" to "2023"),
        hashMapOf("res" to R.drawable.i11, "description" to "Across the Spider-Verse 8", "artist" to "Spiderman", "year" to "2023"),
        hashMapOf("res" to R.drawable.i12, "description" to "The Wolf Among Us 1", "artist" to "Bigby Wolf", "year" to "2013"),
        hashMapOf("res" to R.drawable.i13, "description" to "The Wolf Among Us 2", "artist" to "Bigby Wolf", "year" to "2013"),
        hashMapOf("res" to R.drawable.i14, "description" to "The Wolf Among Us 3", "artist" to "Bigby Wolf", "year" to "2013"),
        hashMapOf("res" to R.drawable.i15, "description" to "The Wolf Among Us 4", "artist" to "Bigby Wolf", "year" to "2013"),
    )
    var currentImageIndex by remember { mutableIntStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight(0.9f)
        ) {
            ArtworkWall(
                imageRes = imageList[currentImageIndex]["res"] as Int,
                Modifier.fillMaxHeight(0.7f)
            )
            ArtworkDescriptor(
                description = imageList[currentImageIndex]["description"] as String,
                artist = imageList[currentImageIndex]["artist"] as String,
                year = imageList[currentImageIndex]["year"] as String
            )
        }
        ArtworkController(
            onPreviousClick = {
                currentImageIndex = when (currentImageIndex) {
                    0 -> (imageList.size - 1)
                    else -> currentImageIndex - 1
                }
            },
            onNextClick =  {
                currentImageIndex = (currentImageIndex + 1) % (imageList.size - 1)
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout(

        )
    }
}