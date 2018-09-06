package com.caucse.seoulproject

import android.content.Context
import android.test.mock.MockContext
import com.caucse.seoulproject.controller.ApiController
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class ControllerTest {
    @Mock
    private lateinit var mockContext: Context

    @Before
    fun init() {
        mockContext = Mockito.mock(Context::class.java)
        ApiController.context = mockContext

    }

    @Test
    fun testController() {
        var data = ApiController.getHotelData(1, 3)

    }
}