package domain

import model.News
import model.NewsUi

fun News.toUi(): NewsUi {
    return NewsUi(
        id = id,
        title = title,
        categoryLabel = category.name.lowercase().replaceFirstChar { it.uppercase() },
        timeLabel = createdAt.toString()
    )
}
