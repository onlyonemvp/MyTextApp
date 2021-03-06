package com.example.mytextapp.activity

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.example.mytextapp.R
import com.example.mytextapp.ui.theme.myAppTheme
import com.example.mytextapp.ui.theme.myTextAppTheme

class TesActivity1 : AppCompatActivity() {
    private var pList = ArrayList<People>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDAta()
        setContent {
            listView(pList)
        }

    }

    private fun setDAta() {
        pList.add(People(R.drawable.wifi_level0,"wifi0"))
        pList.add(People(R.drawable.wifi_level1,"wifi1"))
        pList.add(People(R.drawable.wifi_level2,"wifi2"))
        pList.add(People(R.drawable.wifi_level3,"wifi3"))
    }


}

@Composable
fun multipleStylesInText() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("H")
            }
            append("ello ")

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                append("W")
            }
            append("orld")
        }
    )
}

@Composable
fun listView(items:List<People>){
    var index:Int =0
 Column {
     for (item in items){
         Row {
             Image(painter = painterResource(item.ico), contentDescription = null,Modifier.size(20.dp))
             Text(text = item.name,Modifier.clickable {  })
         }
         index++


     }

 }
}




@Preview(showBackground = true)
@Composable
fun TestCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        // ?????????
        drawLine(
            start = Offset(x = 0f, y = 100f), // ????????????
            end = Offset(x = w, y = 100f), // ????????????
            color = Color.Black, // ????????????
            strokeWidth = 10f // ????????????
        )
        // ??????
        drawCircle(
            color = Color.Red, // ????????????
            center = Offset(x = w / 2, y = 220f), // ????????????
            radius = 100f // ??????
        )
        // inset????????????????????????????????????????????????????????????????????????????????????+50
        inset(50f, 50f) {
            drawRect(
                color = Color.Blue, // ????????????
                topLeft = Offset(x = 0f, y = 350f), // ???????????????
                size = Size(300f, 150f) // ????????????????????????
            )
        }
        // rotate??????????????????????????????????????????30???
        rotate(degrees = 30f) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = w / 2, y = 900f),
                size = Size(200f, 90f)
            )
        }
    }
}

//2.1???Guideline
//??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//
//createGuidelineFromStart(offset: Dp)
//createGuidelineFromAbsoluteLeft(offset: Dp)
//createGuidelineFromStart(fraction: Float)
//createGuidelineFromAbsoluteLeft(fraction: Float)
//createGuidelineFromEnd(offset: Dp)
//createGuidelineFromAbsoluteRight(offset: Dp)
//createGuidelineFromEnd(fraction: Float)
//createGuidelineFromAbsoluteRight(fraction: Float)
//createGuidelineFromTop(offset: Dp)
//createGuidelineFromTop(fraction: Float)
//createGuidelineFromBottom(offset: Dp)
//createGuidelineFromBottom(fraction: Float)
//???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//?????????Absolute????????????????????????????????????????????????????????????????????????


//?????????????????????
@Preview(showBackground = true)
@Composable
fun constraint() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
            .padding(0.dp, 15.dp, 0.dp, 0.dp)
    ) {
        var (text1, text2, text3) = createRefs()
        var line1 = createGuidelineFromStart(0.3f)
        var line2 = createGuidelineFromStart(0.6f)
        var line3 = createGuidelineFromStart(0.9f)
        var tL = createGuidelineFromTop(100.dp)
        Text(text = "11", modifier = Modifier.constrainAs(text1) {
            end.linkTo(line1)
            top.linkTo(tL)

        })
        Text(text = "22", modifier = Modifier.constrainAs(text2) {
            end.linkTo(line2)
            top.linkTo(tL)

        })
        Text(text = "33", modifier = Modifier.constrainAs(text3) {
            end.linkTo(line3)
            top.linkTo(tL)
        })
    }
}


//???????????????????????????????????????
//
//createStartBarrier()
//createAbsoluteLeftBarrier()
//createTopBarrier()
//createEndBarrier()
//createAbsoluteRightBarrier()
//createBottomBarrier()
//?????????????????????
@Preview(showBackground = true)
@Composable
fun constraint2(){
    ConstraintLayout (
        ConstraintSet {
            var box1 = createRefFor("box1")
            var box2 = createRefFor("box2")
            var box3 = createRefFor("box3")

            constrain(box1) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
            constrain(box2) {
                top.linkTo(box1.bottom)
                start.linkTo(parent.start)
            }
            val barrier = createEndBarrier(box1, box2)

            constrain(box3) {
                start.linkTo(barrier)
                top.linkTo(box1.top)
                bottom.linkTo(box2.bottom)
            }

        }
    ){

        Box(
            modifier = Modifier.layoutId("box1")
                .background(color = Color.Red)
                .width(100.dp)
                .height(100.dp)
        )
        Box(
            modifier = Modifier.layoutId("box2")
                .background(color = Color.Yellow)
                .width(150.dp)
                .height(100.dp)
        )
        Box(
            modifier = Modifier.layoutId("box3")
                .background(color = Color.Blue)
                .width(200.dp)
                .height(100.dp)
        )


    }
}



@Composable
fun myTabRow(
    onTabItemClick: (name: String) -> Unit,
    indexDefault: Int = 0
) {

    val indexState = remember {
        mutableStateOf(indexDefault)
    }

    val colorsTab = arrayOf("blue", "red", "green", "yellow")

    TabRow(
        selectedTabIndex = indexState.value,
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        for ((index, name) in colorsTab.withIndex()) {
            Tab(
                selected = index == indexState.value,
                onClick = {
                    indexState.value = index
                    onTabItemClick(colorsTab[index])
                },
                modifier = Modifier.fillMaxHeight(),

                ) {
                Text(
                    text = name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun redThemePreview() {
    myAppTheme(themeName = "red") {
        myTabRow(
            onTabItemClick = {},
            indexDefault = 1
        )
    }
}

data class People(var ico: Int, var name: String)



