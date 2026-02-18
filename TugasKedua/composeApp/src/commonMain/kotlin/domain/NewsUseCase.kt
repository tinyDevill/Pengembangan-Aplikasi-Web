package domain

import data.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import model.Category
import model.News

class NewsUseCase(
    private val repository: NewsRepository
) {
    fun getNewsStream(category: Category?): Flow<News> {
        return repository.streamNews()
            .filter { news ->
                category == null || news.category == category
            }
    }
}
