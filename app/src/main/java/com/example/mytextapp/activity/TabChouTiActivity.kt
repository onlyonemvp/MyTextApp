package com.example.mytextapp.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytextapp.R
import com.example.mytextapp.ui.theme.black
import com.example.mytextapp.ui.theme.btnBlue
import kotlinx.coroutines.launch

//抽屉式导航栏
class TabChouTiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            compose_19()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun compose_19() {
        compose_drawer()
    }

    @Composable
    fun compose_drawer() {
        // 脚手架状态
        val scaffoldState = rememberScaffoldState()
        // 协程
        val coroutineScope = rememberCoroutineScope()
        // 打开抽屉
        val openDrawer: () -> Unit = { coroutineScope.launch { scaffoldState.drawerState.open() } }
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {  // 顶部栏
                IconButton(onClick = openDrawer, modifier = Modifier.width(96.dp).height(46.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.bg_socket_checked),
                        contentDescription = "打开抽屉",
                        tint = btnBlue
                    )
                }
            },
            drawerContent = { // 抽屉内容
//                compose_drawerContent()
                DrawerView()
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "抽屉")
            }
        }
    }

    /**
     * 抽屉内容
     */
    @Composable
    fun compose_drawerContent() {
        LazyColumn(modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(5) { index ->
                Text(text = "抽屉内容 ${index+1} ",
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.primary,)
            }

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DrawerView() {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.white))
                .padding(0.dp, 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_socket_checked), contentDescription = "头像",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "初学者-Study",
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily.Monospace,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Android | Kotlin | Compose",
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily.SansSerif,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                ItemView("文章", 188, Modifier.weight(1f))
                ItemView("点赞", 2109, Modifier.weight(1f))
                ItemView("评论", 2897, Modifier.weight(1f))
                ItemView("收藏", 6450, Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))
            Divider(
                color = black,
                thickness = 1.dp,//线的高度
            )
            ItemViewOnClick("CSDN主页", "https://llw-study.blog.csdn.net/", context)
            ItemViewOnClick("GitHub主页", "https://github.com/lilongweidev/", context)
        }
    }

    @Composable
    fun ItemView(name: String, num: Int, modifier: Modifier) {
        Column(
            modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = num.toString(), modifier = Modifier.padding(0.dp, 6.dp))
            Text(text = name)

        }
    }

    @Composable
    fun ItemViewOnClick(name: String, url: String, context: Context) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
                .height(50.dp)
                .padding(12.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painterResource(R.drawable.wifi_level2), contentDescription = name)
            Text(
                text = name, modifier = Modifier
                    .padding(12.dp, 0.dp)
                    .weight(1f)
            )
            Icon(painterResource(R.drawable.wifi_level2), contentDescription = "打开")
        }
    }




}