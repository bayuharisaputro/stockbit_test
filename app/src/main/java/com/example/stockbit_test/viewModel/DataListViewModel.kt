package com.example.stockbit_test.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.stockbit_test.model.Data
import java.util.ArrayList

abstract class DataListViewModel : ViewModel() {

    abstract val ldIsLoading: LiveData<Boolean>

    abstract val ldIsLoadingMore: LiveData<Boolean>

    abstract val ldErrorHandler: LiveData<Exception?>


    abstract val ldDataList: LiveData<ArrayList<Data?>?>

    abstract fun requestGetDataList(page: Int,tsym: String, loadFromApi:Boolean)
    abstract fun requestLoadMoreDataList(page: Int,tsym: String)

}