package com.example.listapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listapp.ui.theme.ListAppTheme
import com.example.listapp.ui.theme.data.DataSource
import com.example.listapp.ui.theme.models.PicModel

    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                ListAppTheme {
                    Surface (
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ListApp()
                    }
                }
            }
        }
    }

    @Composable
    fun ListApp(){
        val layoutDirection = LocalLayoutDirection.current
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(
                    start = WindowInsets.safeDrawing
                        .asPaddingValues()
                        .calculateStartPadding(layoutDirection),
                    end = WindowInsets.safeDrawing
                        .asPaddingValues()
                        .calculateEndPadding(layoutDirection),
                ),
        ) {
            ListOfCards(picList = DataSource().getListOfPic(), modifier = Modifier)
        }
    }

    @Composable
    fun ListOfCards(picList : List<PicModel>, modifier: Modifier) {
        LazyColumn (modifier = modifier) {
            items(picList) { oneModel ->
                OneCardExample(picModel = oneModel)
            }
        }
    }
    @Composable
    fun OneCardExample(picModel: PicModel) {
        Card(modifier = Modifier) {
            Column {
               Image(
                   painter = painterResource(id = picModel.imageResourceId),
                   contentDescription = stringResource(id = picModel.stringResourceId),
                   modifier = Modifier
                       .fillMaxSize()
                       .height(180.dp),
                   contentScale = ContentScale.Crop
               )
                Text(
                    text = LocalContext.current.getString(picModel.stringResourceId),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.displaySmall.copy(fontSize = 24.sp)
                )
            }
        }
    }
