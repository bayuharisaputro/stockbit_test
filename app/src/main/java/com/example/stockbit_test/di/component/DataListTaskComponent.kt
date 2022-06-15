package com.example.stockbit_test.di.component

import com.example.stockbit_test.MainActivity
import com.example.stockbit_test.di.module.dataList.DataListDataStoreModule
import com.example.stockbit_test.di.module.dataList.DataListRepoModule
import com.example.stockbit_test.di.module.dataList.DataListTaskModule
import dagger.Subcomponent

@Subcomponent(
        modules = [DataListDataStoreModule::class,
            DataListRepoModule::class,
            DataListTaskModule::class
        ]
)
interface DataListTaskComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DataListTaskComponent
    }

    fun inject(activity: MainActivity)
}