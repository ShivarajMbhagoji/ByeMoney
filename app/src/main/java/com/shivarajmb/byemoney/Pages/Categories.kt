package com.shivarajmb.byemoney.Pages

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shivarajmb.byemoney.Components.TableRow
import com.shivarajmb.byemoney.ui.theme.BackgroundElevated
import com.shivarajmb.byemoney.ui.theme.DividerColour
import com.shivarajmb.byemoney.ui.theme.Shapes
import com.shivarajmb.byemoney.ui.theme.TopAppBarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Categories(navController: NavController) {
    Scaffold(
        topBar = {
            MediumTopAppBar(title = { Text("Categories") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground
                ),
                navigationIcon = {
                    Surface(
                        onClick = { navController.popBackStack() },
                        color = Color.Transparent
                    ) {
                        Row {
                            Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "")
                            Text(text = "Settings")
                        }
                    }
                }
            )
        },
        content = {innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Column(modifier = Modifier
                    .clickable { }
                    .padding(16.dp)
                    .clip(Shapes.medium)
                    .background(BackgroundElevated)
                    .fillMaxWidth()
                ) {
                    TableRow("Groceries")
                    Divider(modifier= Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)
                    TableRow("Gas")
                    Divider(modifier= Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)
                    TableRow("Bills")
                    Divider(modifier= Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)
                    TableRow("Subscription",
                        Modifier.fillMaxWidth()){
                        Text(
                            text = "Delete",
                            modifier = Modifier.background(Color.Red)
                            )
                    }
                    Divider(modifier= Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)
                    TableRow("Hobbies")
                }

                Row(){

                }
            }
        }
    )
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CategoryPreview(){
    Categories(navController = rememberNavController())
}
