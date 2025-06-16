package com.zonkesoft.headlinr.data.models

data class TopHeadlineResponseModel(
    val status : String?= null,
    val totalResults: Int,
    val articles: List<ArticlesModel>? = null
)

data class ArticlesModel(
    val source: SourceModel? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)

data class SourceModel(
    val id: String? = null,
    val name: String? = null
)