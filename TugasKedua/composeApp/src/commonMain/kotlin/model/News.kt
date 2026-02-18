package model

enum class Category {
    TECHNOLOGY, SPORTS, HEALTH, BUSINESS, ENTERTAINMENT
}

data class News(
    val id: String,
    val title: String,
    val category: Category,
    val createdAt: String = "Baru saja."
)

data class NewsDetail(
    val id: String,
    val content: String,
    val author: String
)
