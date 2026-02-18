package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Category
import presentation.NewsViewModel

@Composable
fun NewsScreen(vm: NewsViewModel) {
    val newsList by vm.newsList.collectAsState()
    val readCount by vm.readCount.collectAsState()
    val detail by vm.selectedDetail.collectAsState()
    val selectedCategory by vm.selectedCategory.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text(
            text = "📰 News Feed Simulator",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Read Count: $readCount")

        Spacer(modifier = Modifier.height(12.dp))

        // Filter kategori
        Row {
            Button(onClick = { vm.setCategory(null) }) {
                Text("ALL")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { vm.setCategory(Category.TECHNOLOGY) }) {
                Text("TECH")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { vm.setCategory(Category.SPORTS) }) {
                Text("SPORT")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Selected: ${selectedCategory ?: "ALL"}")

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(newsList) { news ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable {
                            vm.markAsRead(news.id)
                        }
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(news.title, style = MaterialTheme.typography.titleMedium)
                        Text(news.categoryLabel)
                        Text(news.timeLabel, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        detail?.let {
            Text("📌 Detail Berita:", style = MaterialTheme.typography.titleMedium)
            Text(it.content)
            Text("Author: ${it.author}")
        }
    }
}
