package fake

import com.example.marsphotos.data.NetworkMarsPhotosRepository
import com.example.marsphotos.network.MarsPhoto
import kotlinx.coroutines.test.runTest
import org.junit.Test


class NetworkMarsRepositoryTest {
}
@Test
fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {}

fun assertEquals(photosList: List<MarsPhoto>, marsPhotos: List<MarsPhoto>) {
}
