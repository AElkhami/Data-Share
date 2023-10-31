package com.elkhami.instantdatashare.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.elkhami.instantdatashare.view.ui.theme.DataShareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataShareTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                ) {
                    val appLinkIntent: Intent = intent
                    val appLinkAction: String? = appLinkIntent.action
                    if (Intent.ACTION_VIEW == appLinkAction) {
                        BottomSheet()
                        viewModel.insertUser()
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

    val activity = (LocalContext.current as? Activity)

    ModalBottomSheet(modifier = modifier.fillMaxSize(),
        sheetState = sheetState,
        onDismissRequest = { activity?.finish() }) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Wallet", fontWeight = FontWeight.Bold, fontSize = 21.sp)
                Image(Icons.Rounded.Close,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        Color.Black
                    ),
                    modifier = Modifier
                        .size(30.dp)
                        .clip(
                            CircleShape,
                        )
                        .background(color = Color(0xffdcdcdc))
                        .padding(2.dp)
                        .clickable {
                            activity?.finish()
                        })
            }
            Image(
                Icons.Rounded.AccountBox, contentDescription = null
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

                        SharedRecord(Icons.Rounded.Person, "Name")

                        SharedRecord(Icons.Rounded.DateRange, "Date of birth")

                        SharedRecord(Icons.Rounded.Star, "Sex")

                        SharedRecord(Icons.Rounded.List, "ID number")
                    }
                    Column(Modifier.weight(0.5f)) {

                        SharedRecord(Icons.Rounded.DateRange, "Issue date")

                        SharedRecord(Icons.Rounded.DateRange, "Expiration date")

                        SharedRecord(Icons.Rounded.AccountCircle, "ID photo")
                    }
                }
            }
        }
    }
}

@Composable
fun SharedRecord(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier.padding(bottom = 4.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(icon, contentDescription = null)
        Text(
            text = title,
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(start = 8.dp)
        )
        var isChecked by remember {
            mutableStateOf(false)
        }
        Checkbox(modifier = Modifier
            .size(10.dp)
            .padding(start = 4.dp),
            checked = isChecked,
            onCheckedChange = { isChecked = it })
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    DataShareTheme {
        BottomSheet()
    }
}