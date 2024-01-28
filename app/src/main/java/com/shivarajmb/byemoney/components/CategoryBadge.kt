package com.shivarajmb.byemoney.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shivarajmb.byemoney.models.Category
import com.shivarajmb.byemoney.ui.theme.Shapes
import com.shivarajmb.byemoney.ui.theme.Typography


@Composable
fun CategoryBadge(category:Category,modifier: Modifier=Modifier) {
    Surface(
        shape = Shapes.large,
        color= category.color.copy(.25f),
        modifier = modifier
    ) {
        Text(
            category.name,
            color=category.color,
            style = Typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

//@Preview
//@Composable
//fun CategoryBadgePreview() {
//    ByeMoneyTheme {
//        CategoryBadge(category = Category("Book", Color.Red))
//    }
//
//}