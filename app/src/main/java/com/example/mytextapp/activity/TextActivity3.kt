package com.example.mytextapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mytextapp.ui.theme.*

class TextActivity3 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            linearProgress()
            cons()
        }
    }


    //LinearProgressIndicator（线性进度条）
    @Preview(showBackground = true)
    @Composable
    fun linearProgress() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            //进度条进度
            var progressLinear = remember {
                mutableStateOf(0.1f)
            }
            //线性进度条--无限循环
            LinearProgressIndicator()
            Spacer(modifier = Modifier.requiredHeight(10.dp))
            //线性进度条--按进度变化
            LinearProgressIndicator(progress = progressLinear.value, backgroundColor = Color.LightGray)
            Spacer(modifier = Modifier.requiredHeight(10.dp))
            TextButton(
                onClick = {
                    if (progressLinear.value < 1.0f) progressLinear.value = progressLinear.value + 0.1f
                },
                modifier = Modifier.background(Color.LightGray)
            ) {
                Text(text = "增加线性进度")
            }
        }


//        CircularProgressIndicator(progress = 0.5f, modifier = Modifier.fillMaxWidth())
////            progress: Float,//进度
////            modifier: Modifier = Modifier,//布局修饰
////        color: Color = MaterialTheme.colors.primary,//进度指示器颜色
////        strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth//进度指示器的宽度
//
//        CircularProgressIndicator(
////            modifier: Modifier = Modifier,//布局修饰
////        color: Color = MaterialTheme.colors.primary,//进度指示器颜色
////        strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth//进度指示器的宽度
//        )

    }

    //CircularProgress(圆形进度条)
    @Preview(showBackground = true)
    @Composable
    fun circularProgress() {

        //进度条进度
        var progress = remember {
            mutableStateOf(0.1f)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(color = black)
        ) {
            //圆形进度条--无限循环
            CircularProgressIndicator()
            Spacer(modifier = Modifier.requiredHeight(10.dp))
            //圆形进度条--按进度变化
            CircularProgressIndicator(progress = progress.value)
            Spacer(modifier = Modifier.requiredHeight(10.dp))
            TextButton(
                onClick = { if (progress.value < 1.0f) progress.value = progress.value + 0.1f },
                modifier = Modifier.background(Color.LightGray)
            ) {
                Text(text = "增加进度")
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun cons() {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            var (box, filetext) = createRefs()

            var text = rememberSaveable { mutableStateOf("") }
            var isError = rememberSaveable { mutableStateOf(false) }
            TextField(
                value = text.value, onValueChange = { changeText -> text.value = changeText },
                modifier = Modifier.constrainAs(filetext) {
                    top.linkTo(parent.top, margin = 100.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }.clip(shape = Shapes.small).width(296.dp),
                label = {
                    if (text.value.length in 1..9) {
                        Text("长度小于10", color = orange)
                    }

                },
            )
            Box(modifier = Modifier.constrainAs(box) {
                top.linkTo(parent.top, margin = 30.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
                Row(modifier = Modifier.width(296.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(
                        { Toast.makeText(this@TextActivity3, "Click Button", Toast.LENGTH_SHORT).show() },
                        modifier = Modifier.width(96.dp).height(40.dp).background(color = black),
                        enabled = true

                    ) {
                        Text("确认")
                    }

                    Button(
                        { Toast.makeText(this@TextActivity3, "Click Button", Toast.LENGTH_SHORT).show() },
                        modifier = Modifier.background(color = blue).width(96.dp).height(40.dp),
                        enabled = true

                    ) {
                        Text("确认", color = orange)
                    }


                    Button(
                        { Toast.makeText(this@TextActivity3, "Click Button", Toast.LENGTH_SHORT).show() },
                        modifier = Modifier.width(96.dp).height(40.dp).background(color = btnBlue),
                        enabled = true

                    ) {
                        Text("确认")
                    }

                }
            }
        }


    }
}