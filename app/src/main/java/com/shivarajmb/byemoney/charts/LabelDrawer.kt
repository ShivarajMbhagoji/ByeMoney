package com.shivarajmb.byemoney.charts

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import com.github.tehras.charts.bar.renderer.label.LabelDrawer
import com.github.tehras.charts.piechart.utils.toLegacyInt
import com.shivarajmb.byemoney.models.Recurrance
import com.shivarajmb.byemoney.ui.theme.LabelSecondary

class labelDrawer (val recurrance: Recurrance,private val lastDay: Int? = -1):LabelDrawer {
    private val paint = android.graphics.Paint().apply {
        this.textAlign = android.graphics.Paint.Align.CENTER
        this.color = LabelSecondary.toLegacyInt()
        this.textSize=42f
    }

    val leftInc=when(recurrance){
        Recurrance.Weekly->50f
        Recurrance.Monthly->16f
        Recurrance.Yearly->40f
        else->50f
    }



    override fun drawLabel(
        drawScope: DrawScope,
        canvas: Canvas,
        label: String,
        barArea: Rect,
        xAxisArea: Rect
    ) {

        val monthlyCondition=
            recurrance==
                Recurrance.Monthly &&(
                        Integer.parseInt(label)%5==0||
                        Integer.parseInt(label)==1||
                        Integer.parseInt(label)==lastDay
                        )

        if(monthlyCondition||recurrance!=Recurrance.Monthly) {
            canvas.nativeCanvas.drawText(
                label,
                barArea.left + leftInc,
                barArea.bottom + 65f,
                paint
            )
        }
    }
}