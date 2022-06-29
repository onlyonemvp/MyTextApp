package com.example.mytextapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mytextapp.R
import com.example.mytextapp.ui.theme.blue

class AnimationTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContent {
           animSize()
       }
    }
//
//    低级别动画 API
//    animate*AsState
//    所能处理属性的种类：Float、Color、Dp、Size、Bounds、Offset、Rect、Int、IntOffset 和 IntSize
//    通过 animate*AsState 我们可以实现单一属性的动画效果,我们只需要提供目标值就可以自动从当前进度动画过渡到目标值
    @Preview(showBackground = true)
    @Composable
    fun animSize() {
        val enable = remember { mutableStateOf(true) }
        val size = animateSizeAsState(targetValue = if (enable.value) Size(50f, 50f) else Size(300f, 300f))
        val color = animateColorAsState(targetValue = if(enable.value) Color.Blue else Color.Green)
        Column(
            modifier = Modifier.fillMaxSize(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(size.value.width.dp, size.value.height.dp)
                    .background(color.value)
                    .clickable {
                        enable.value = !enable.value
                    },
                painter = painterResource(id = R.drawable.bg_socket_checked),
                contentDescription = ""
            )
        }
    }

}