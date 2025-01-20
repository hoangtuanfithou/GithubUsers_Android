import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusers.data.api.RetrofitClient
import com.example.githubusers.data.model.StorageService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val storageService: StorageService = StorageService()) : ViewModel() {
    private val _users = MutableStateFlow<List<GitHubUser>>(emptyList())
    val users = _users.asStateFlow()

    private var currentPage = 0
    private var isLoading = false
    private val api = RetrofitClient.createService()

    init {
        // Load cached data first
        loadCachedUsers()
        // Then fetch fresh data
        loadMoreUsers()
    }

    private fun loadCachedUsers() {
        val cachedUsers = storageService.loadCachedUsers()
        _users.value = cachedUsers
        // Update page number based on cached data
        currentPage = (cachedUsers.size / 20)
    }

    fun loadMoreUsers() {
        if (isLoading) return

        viewModelScope.launch {
            try {
                isLoading = true
                val newUsers = api.getUsers(since = currentPage * 20)
                _users.update { currentUsers ->
                    val updatedUsers = currentUsers + newUsers
                    // Cache the updated list
                    storageService.cacheUsers(updatedUsers)
                    updatedUsers
                }
                currentPage++
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}
