import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusers.data.api.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<GitHubUser>>(emptyList())
    val users = _users.asStateFlow()

    private var currentPage = 0
    private var isLoading = false

    private val api = RetrofitClient.createService()

    init {
        loadMoreUsers()
    }

    fun loadMoreUsers() {
        if (isLoading) return

        viewModelScope.launch {
            try {
                isLoading = true

                val newUsers = api.getUsers(since = currentPage * 20)
                _users.update { currentUsers -> currentUsers + newUsers }
                currentPage++
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}
