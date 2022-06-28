package com.example.mytextapp

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mytextapp.ui.theme.myTextAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myTextAppTheme {
                thisActivityXml()
            }
        }
    }

}


@Composable
fun thisActivityXml() {
    var list = listOf<LString>(
        LString("name1", "this1"),
        LString("name2", "this2"),
        LString("name1", "this1"),
    )
    conversation(list)
}

@Composable
fun greeting(name: String) {
    Text(text = "this $name")
}

@Composable
fun conversation(strings: List<LString>) {
    Column {
        topBar()
        LazyColumn(Modifier.fillMaxHeight().fillMaxHeight()) {
            items(strings) { str ->
                messageCard(str)
            }

        }
    }

}

data class LString(var name: String, var thi: String)

//Scaffold 为悬浮操作按钮提供了槽。
//您可以使用 floatingActionButton 槽和 FloatingActionButton：
@Composable
fun messageCard(str: LString) {

    Row(Modifier.padding(all = 5.dp).fillMaxWidth()) {
//        Image(painter = painterResource(R.drawable.abc_vector_test))
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.bg_socket_checked),
            contentDescription = null,
            alignment = Alignment.CenterStart,

            )

        Column(Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) {
            Text(
                text = str.name, color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = str.thi,
                style = MaterialTheme.typography.subtitle2

            )
        }
        Button(
            onClick = { Log.v("btnClick", "点击了按钮") },
            Modifier.padding(15.dp),
            ) {
            Text("确认", modifier = Modifier)

        }
    }
}
//   Button
//2.shape 形状
//Android官方给我们提供了以下四种形状
//RoundedCornerShape 圆角形状
//CutCornerShape 切角形状
//AbsoluteRoundedCornerShape 绝对圆角形状
//AbsoluteCutCornerShape 绝对切角形状

//自定义布局
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = layout { measurable, constraints ->

    val placeable = measurable.measure(constraints)

    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {

        placeable.placeRelative(0, placeableY)
    }
}


@Composable
fun topBar(){
    Scaffold(modifier = Modifier.height(40.dp),
        topBar = { TopAppBar {
            Text(text = "ico")
            Text(text = "ico")
            Text(text = "ico")
        } },
        floatingActionButton = { FloatingActionButton(onClick = {}, modifier =Modifier.size(20.dp) , ) {
            Image(painter = painterResource(R.drawable.abc_vector_test,),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        } },
    ) {

    }
}

@Composable
fun constraint(){
    ConstraintLayout {
        val (text1,text2,button) = createRefs()
        Text("text1", modifier = Modifier.constrainAs(text1){
            
        })
        Text("text2", modifier = Modifier.constrainAs(text2){})

        Button(onClick = {},modifier = Modifier.constrainAs(button){}){
            Text("button")
        }
    }

}

@Preview()
@Preview(showBackground = true, name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun thisActivity() {
    myTextAppTheme {
        thisActivityXml()
    }

}