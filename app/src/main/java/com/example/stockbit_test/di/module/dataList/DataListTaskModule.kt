package com.example.stockbit_test.di.module.dataList

import androidx.lifecycle.ViewModel
import com.example.stockbit_test.di.module.ViewModelKey
import com.example.stockbit_test.viewModel.DataListViewModel
import com.example.stockbit_test.viewModel.DataListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DataListTaskModule {
    @Binds
    @IntoMap
    @ViewModelKey(DataListViewModelImpl::class)
    fun bindViewModelImpl(viewModel: DataListViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DataListViewModel::class)
    fun bindViewModel(viewModel: DataListViewModelImpl): ViewModel
}