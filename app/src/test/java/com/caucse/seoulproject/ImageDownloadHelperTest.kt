package com.caucse.seoulproject

import android.content.Context
import com.caucse.seoulproject.helper.CultureApiHelper
import com.caucse.seoulproject.helper.ImageDownloadHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImageDownloadHelperTest {

    @Mock
    private lateinit var mockContext: Context
    @Mock
    val imageDownloadHelperTest = ImageDownloadHelper()
    @Mock
    val cultureApiHelper = CultureApiHelper()

    @Test
    fun testApi() {
        cultureApiHelper.getData(mockContext, 1, 1)
    }
}