package data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.Category
import model.News
import kotlin.random.Random

class NewsRepository {

    private val titles = listOf(
        "AI Mengubah Dunia Kerja",
        "Final Liga Champions Memanas",
        "Tips Hidup Sehat di 2026",
        "Startup Baru Raup Pendanaan",
        "Film Terbaru Pecahkan Rekor"
    )

    fun streamNews(): Flow<News> = flow {
        var counter = 1
        while (true) {
            delay(2000)

            val category = Category.entries.random()
            val title = titles.random()

            emit(
                News(
                    id = counter.toString(),
                    title = "[$category] $title #$counter",
                    category = category
                )
            )

            counter++
        }
    }

    suspend fun fetchNewsDetail(newsId: String): model.NewsDetail {
        delay(1500) // simulasi API call
        return model.NewsDetail(
            id = newsId,
            content = "Ini adalah isi lengkap berita dengan id $newsId. Berita ini menjelaskan detail kejadian...",
            author = "Reporter ${Random.nextInt(1, 100)}"
        )
    }
}
