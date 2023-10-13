package com.example.totd

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class TaskLabel(
    val labelName: String,
    val labelColor: Color
) {
    @Composable
    fun DrawTaskLabel() {
        Box(
            modifier = Modifier
                .background(labelColor)
                .clip(RoundedCornerShape(8.dp))
                .padding(1.dp)
        ) {
            Text(
                text = labelName,
                color = Color.White
            )
        }
    }

    companion object
}
