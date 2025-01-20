import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubusers.ui.detail.UserDetailViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var sut: UserDetailViewModel
    private val apiMock: GitHubApiService = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = UserDetailViewModel(apiMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadUserDetail updates user when API call is successful`() = runTest {
        // Given
        val username = "testUser"
        val expectedUser = GitHubUser(
            login = "testUser",
            avatarUrl = "http://example.com",
            htmlUrl = "http://example.com"
        )
        whenever(apiMock.getUserDetail(username)).thenReturn(expectedUser)

        // When
        sut.loadUserDetail(username)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val actualUser = sut.user.value
        assertEquals(expectedUser, actualUser)
    }

    @Test
    fun `loadUserDetail handles error gracefully`() = runTest {
        // Given
        val username = "testUser"
        whenever(apiMock.getUserDetail(username)).thenThrow(RuntimeException("API Error"))

        // When
        sut.loadUserDetail(username)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val actualUser = sut.user.value
        assertNull(actualUser)
    }

    @Test
    fun `initial state has null user`() = runTest {
        // Then
        assertNull(sut.user.value)
    }

}
