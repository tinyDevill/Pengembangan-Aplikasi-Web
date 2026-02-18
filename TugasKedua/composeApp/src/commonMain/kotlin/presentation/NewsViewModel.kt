package presentation

import data.NewsRepository
import domain.NewsUseCase
import domain.toUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import model.Category
import model.NewsDetail
import model.NewsUi

class NewsViewModel(
    private val repository: NewsRepository
) {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val useCase = NewsUseCase(repository)

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    private val _newsList = MutableStateFlow<List<NewsUi>>(emptyList())
    val newsList = _newsList.asStateFlow()

    private val _readCount = MutableStateFlow(0)
    val readCount = _readCount.asStateFlow()

    private val _selectedDetail = MutableStateFlow<NewsDetail?>(null)
    val selectedDetail = _selectedDetail.asStateFlow()

    fun startStreaming() {
        scope.launch {
            selectedCategory
                .flatMapLatest { category ->
                    useCase.getNewsStream(category)
                }
                .map { it.toUi() }
                .collect { newsUi ->
                    _newsList.update { old ->
                        listOf(newsUi) + old
                    }
                }
        }
    }

    fun setCategory(category: Category?) {
        _selectedCategory.value = category
        _newsList.value = emptyList() // reset list biar filter terasa
    }

    fun markAsRead(newsId: String) {
        _readCount.update { it + 1 }
        fetchDetail(newsId)
    }

    private fun fetchDetail(newsId: String) {
        scope.launch {
            val detail = repository.fetchNewsDetail(newsId)
            _selectedDetail.value = detail
        }
    }
}
