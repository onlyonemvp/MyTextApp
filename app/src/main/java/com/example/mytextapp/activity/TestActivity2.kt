package com.example.mytextapp.activity

import android.graphics.drawable.shapes.Shape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.example.mytextapp.R
import com.example.mytextapp.ui.theme.Shapes
import com.example.mytextapp.ui.theme.black


class TestActivity2 : AppCompatActivity() {

    var pList = ArrayList<WF>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDAta()
        setContent {
            text2Activity()
        }
    }

    private fun setDAta() {
        pList.add(WF(R.drawable.wifi_level0, "wifi0"))
        pList.add(WF(R.drawable.wifi_level1, "wifi1"))
        pList.add(WF(R.drawable.wifi_level2, "wifi2"))
        pList.add(WF(R.drawable.wifi_level3, "wifi3"))
    }

    @Preview(showBackground = true)
    @Composable
    fun text2Activity() {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            recycleView(pList)
        }

    }

    @Composable
    fun recycleView(dataList: ArrayList<WF>) {
        Column {
            for (item in dataList) {
                itemXml(item.ico, item.name,)
            }
        }


    }


    @Composable
    private fun itemXml(ico: Int, wName: String) {
        Row(
            modifier = Modifier
                .padding(horizontal = 0.dp, vertical = 15.dp)
                .fillMaxWidth().height(50.dp)
                .clickable {  Toast.makeText(this, "item", Toast.LENGTH_SHORT).show() }
        ) {
            ConstraintLayout(
                ConstraintSet {
                    var img1 = createRefFor("img1")
                    var text = createRefFor("text")
                    var img2 = createRefFor("img2")

                    constrain(img1) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    val line = createGuidelineFromStart(0.5f)
                    constrain(text) {
                        top.linkTo(parent.top)
                        start.linkTo(line)

                    }
                }


            ) {
                Image(
                    painter = painterResource(ico),
                    contentDescription = null,
                    modifier = Modifier.layoutId("img1").size(30.dp).clip(shape = Shapes.small).padding(15.dp)
                )
                Text(text = wName, textAlign = TextAlign.Center, modifier = Modifier.layoutId("text"))

            }

        }

    }

}

data class WF(var ico: Int, var name: String)


