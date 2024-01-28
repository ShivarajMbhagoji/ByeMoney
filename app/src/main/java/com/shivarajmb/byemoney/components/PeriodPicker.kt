package com.shivarajmb.byemoney.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.shivarajmb.byemoney.ui.theme.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shivarajmb.byemoney.R
import com.shivarajmb.byemoney.ui.theme.ByeMoneyTheme
import com.shivarajmb.byemoney.ui.theme.Shapes
import com.shivarajmb.byemoney.ui.theme.periodPickerFill

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun periodPicker(
    label:String,
    onClick:()->Unit,
    modifier: Modifier=Modifier
) {
        Surface(
            shape = Shapes.medium,
            modifier = modifier,
            color = periodPickerFill,
            onClick = onClick
        ){
            Row(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(label, style = Typography.titleSmall)
                Icon(
                    painterResource(R.drawable.unfold_more),
                    contentDescription = "unfold more icon",
                    modifier.padding(start = 4.dp)
                )
            }
        }

}

@Preview(showBackground = true)
@Composable
fun PickerPreview() {
    ByeMoneyTheme {
        periodPicker("This", onClick = {})
    }
}