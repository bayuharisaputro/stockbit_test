package com.example.stockbit_test.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.stockbit_test.model.DataListResponse
import com.example.stockbit_test.repository.DataListRepositoryImpl
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DataListViewModelImplTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var repo: DataListRepositoryImpl? = null

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun requestGetDataList_normal() {
        runBlocking {
            val vm = DataListViewModelImpl(repo!!)
            Mockito.`when`(repo?.getDataList(1, "USD", true, false)).thenReturn(DataListResponse())

            vm.requestGetDataList(1, "USD", true)

            val dataList = vm.ldDataList.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(dataList).isNotNull()
            Truth.assertThat(error).isNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }

    @Test
    fun requestGetDataList_error() {
        runBlocking {
            val vm = DataListViewModelImpl(repo!!)
            Mockito.`when`(repo?.getDataList(1, "USD", true, false)).thenThrow(IOException())

            vm.requestGetDataList(1, "USD", true)

            val dataList = vm.ldDataList.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(dataList).isNull()
            Truth.assertThat(error).isNotNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }
}