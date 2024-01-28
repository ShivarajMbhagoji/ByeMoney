package com.shivarajmb.byemoney.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shivarajmb.byemoney.ui.theme.BackgroundElevated
import com.shivarajmb.byemoney.ui.theme.TopAppBarBackground
import com.shivarajmb.byemoney.ui.theme.Shapes
import com.shivarajmb.byemoney.components.TableRow
import com.shivarajmb.byemoney.ui.theme.DividerColour

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController) {
    Scaffold(
        topBar = {
            MediumTopAppBar(title = { Text("Settings") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground
            ))
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
                    TableRow("Categories",
                        Modifier.clickable {
                                           navController.navigate("Settings/Categories")
                        },
                        hasArrow = true
                    )
                    Divider(modifier=Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColour)
                    TableRow("Erase all data", isDestructive = true)
                }
            }
        }
    )
}