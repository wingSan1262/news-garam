@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package com.example.tasklist.feature.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.compose.white
import com.example.tasklist.base.compose.component.BackgroundGradationBox
import com.example.tasklist.base.compose.component.ImageUrlLoader
import com.example.tasklist.base.compose.component.ShowSlideLeft
import com.example.tasklist.data.remote.model.response.Articles


@Composable
fun NewsList(
    tasks: List<Articles>,
    onSelectTask : (Articles) -> Unit,
) {
    Column(
        Modifier.verticalScroll(rememberScrollState())
    ){
        tasks.forEachIndexed { index, it ->
            NewsItem(articles = it, index, onSelectTask)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun NewsItem(articles: Articles, index: Int, onSelectTask: (Articles) -> Unit) {
    if(index % 2 == 0)
        ShowSlideLeft(delay = 200) {
            ContentNews(articles = articles, onSelectTask, true)
        }
    else
        ShowSlideLeft(delay = 200, true) {
            ContentNews(articles = articles, onSelectTask, false)
        }
}


@Composable
fun ContentNews(
    articles: Articles, onSelectTask: (Articles) -> Unit,
    isRight: Boolean
){
    Box(
        Modifier.clickable { onSelectTask(articles) },
        contentAlignment = if(!isRight) Alignment.CenterStart
        else Alignment.CenterEnd
    ){
        ImageUrlLoader(
            articles.urlToImage ?: "",
            articles.title ?: ""
        )
        BackgroundGradationBox(
            Modifier.height(200.dp).fillMaxWidth(),
            isRight
        ){
            Text(
                text = articles.title ?: "",
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = white,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    top = 24.dp, bottom = 24.dp,
                    start = if(isRight) 0.dp else 8.dp,
                    end = if(isRight) 8.dp else 0.dp,
                ).fillMaxWidth(0.5f)
            )
        }
    }
}


@Preview
@Composable
fun PreviewTaskList() {
    NewsList(tasks = tasks){}
}

val tasks: List<Articles> = listOf(
    Articles(
        title = "",
        urlToImage = "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://d1i4t8bqe7zgj6.cloudfront.net/12-18-2023/t_e4e90ce21e4d4d1987ab1aef78dab5d4_name_TB_1c.jpg&w=1440"
    )
)