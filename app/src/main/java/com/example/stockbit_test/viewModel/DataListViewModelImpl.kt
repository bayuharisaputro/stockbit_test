package com.example.stockbit_test.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.stockbit_test.model.Data
import com.example.stockbit_test.repository.DataListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class DataListViewModelImpl @Inject constructor(
        private val repository: DataListRepository
) : DataListViewModel() {

    private val mIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val mIsLoadMore: MutableLiveData<Boolean> = MutableLiveData()
    private val mErrorHandler: MutableLiveData<Exception?> = MutableLiveData()
    private val mDataList: MutableLiveData<ArrayList<Data?>?> = MutableLiveData()

    override val ldIsLoading: LiveData<Boolean> = mIsLoading
    override val ldErrorHandler: LiveData<Exception?> = mErrorHandler
    override val ldDataList: LiveData<java.util.ArrayList<Data?>?> = mDataList
    override val ldIsLoadingMore: LiveData<Boolean> = mIsLoadMore

    private var wholeRawData: ArrayList<Data?>? = arrayListOf()

    override fun requestGetDataList(page: Int,tsym: String, loadFromApi: Boolean) {
        viewModelScope.launch {
            mIsLoading.value = true
            try {
                wholeRawData = repository.getDataList(page, tsym, loadFromApi, false)?.Data
                mDataList.value = wholeRawData
                mErrorHandler.value = null

            }
            catch (e: Exception) {
                mDataList.value = null
                mErrorHandler.value = e
            }
            finally {
                mIsLoading.value = false
            }
        }
    }

    override fun requestLoadMoreDataList(page: Int,tsym: String) {
        viewModelScope.launch {
            mIsLoadMore.value = true
            try {
                wholeRawData?.addAll(wholeRawData?.lastIndex?:0,repository.getDataList(page, tsym,
                    isRefresh = false,
                    isLoadMore = true
                )?.Data?: arrayListOf())
                mDataList.value = wholeRawData
                mErrorHandler.value = null
            }
            catch (e: Exception) {
                mErrorHandler.value = e
            }
            finally {
                mIsLoadMore.value = false
            }
        }
    }

}