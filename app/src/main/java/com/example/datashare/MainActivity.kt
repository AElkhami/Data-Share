package com.example.datashare

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datashare.ui.theme.DataShareTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataShareTheme {
                Column(modifier = Modifier.fillMaxSize().background(Color.Transparent)) {
                    val appLinkIntent: Intent = intent
                    val appLinkAction: String? = appLinkIntent.action
                    if (Intent.ACTION_VIEW == appLinkAction) {
                        BottomSheet()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(modifier: Modifier = Modifier) {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = modifier.fillMaxSize(),
        sheetState = sheetState,
        onDismissRequest = { /*TODO*/ }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Wallet", fontWeight = FontWeight.Bold, fontSize = 21.sp)
                Image(
                    Icons.Rounded.Close, contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        Color.Black
                    ),
                    modifier = Modifier
                        .size(30.dp)
                        .clip(
                            CircleShape,
                        )
                        .background(color = Color.Gray)
                        .padding(2.dp)
                )
            }
            Image(
                Icons.Rounded.AccountBox,
                contentDescription = null
            )
            Text(text = "Account Info", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "The following information will be presented:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Column(Modifier.weight(0.5f)) {
                        Row(Modifier.padding(bottom = 8.dp)) {
                            Image(Icons.Rounded.Person, contentDescription = null)
                            Text(text = "Name", modifier = Modifier.padding(start = 8.dp))
                        }
                        Row(Modifier.padding(bottom = 8.dp)) {
                            Image(Icons.Rounded.DateRange, contentDescription = null)
                            Text(text = "Date of birth", modifier = Modifier.padding(start = 8.dp))
                        }
                        Row(Modifier.padding(bottom = 8.dp)) {
                            Image(Icons.Rounded.Star, contentDescription = null)
                            Text(text = "Sex", modifier = Modifier.padding(start = 8.dp))
                        }
                        Row(Modifier.padding(bottom = 8.dp)) {
                            Image(Icons.Rounded.List, contentDescription = null)
                            Text(text = "ID number", modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                    Column(Modifier.weight(0.5f)) {
                        Row(Modifier.padding(bottom = 8.dp)) {
                            Image(Icons.Rounded.DateRange, contentDescription = null)
                            Text(text = "Issue date", modifier = Modifier.padding(start = 8.dp))
                        }
                        Row(Modifier.padding(bottom = 8.dp)) {
                            Image(Icons.Rounded.DateRange, contentDescription = null)
                            Text(
                                text = "Expiration date",
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        Row(Modifier.padding(bottom = 8.dp)) {
                            Image(Icons.Rounded.AccountCircle, contentDescription = null)
                            Text(text = "ID photo", modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    DataShareTheme {
        BottomSheet()
    }
}