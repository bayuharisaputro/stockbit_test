package com.example.stockbit_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.stockbit_test.adapter.DataListAdapter
import com.example.stockbit_test.di.component.BaseApp
import com.example.stockbit_test.viewModel.DataListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: DataListAdapter
    val loadPaginThreshold = 2
    var firstLoadPage = 0
    private var mErrorDialog: ErrorDialog? = null
    @Inject
    lateinit var dataListViewModelFactory: ViewModelProvider.Factory
    private val dataListVM by viewModels<DataListViewModel> { dataListViewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as BaseApp).appComponent.dataListTaskComponent().create().inject(this)
        dataListVM.requestGetDataList(0 , "USD", false)
        initView()
        initEvent()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mErrorDialog?.isShowing == true) {
            mErrorDialog?.dismiss()
        }
    }

    private fun initEvent() {
        iv_back.setOnClickListener {
            onBackPressed()
        }

        dataListVM.ldErrorHandler.observe(this, Observer {
            it?.let {
                mErrorDialog?.setDialogData("Get Data Error", it.message.toString())
                mErrorDialog?.show()
            }
        })

        rv_data.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dataListVM.ldIsLoadingMore.value == true) return

                (rv_data.layoutManager as? LinearLayoutManager)?.let {
                    val lastPos = it.findLastVisibleItemPosition()
                    if (mAdapter.listData.size - lastPos < loadPaginThreshold ) {
                        if(mAdapter.itemCount > 0) {
                            firstLoadPage = (mAdapter.listData.size)/50
                            firstLoadPage++
                            mAdapter.listData.add(null)
                            mAdapter.notifyDataSetChanged()
                            dataListVM.requestLoadMoreDataList(firstLoadPage, "USD")
                        }
                    }
                }
            }
        })

        sr_layout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            dataListVM.requestGetDataList(0, "USD", true)
        })

        dataListVM.ldIsLoading.observe(this, Observer {
            sr_layout.isRefreshing = it
        })

        dataListVM.ldDataList.observe(this, Observer {
            it?.let {
                if(it.isNotEmpty()) {
                    txt_empty_data.visibility = View.GONE
                    sr_layout.visibility = View.VISIBLE
                    mAdapter.listData = it
                    mAdapter.notifyDataSetChanged()
                }
                else {
                    txt_empty_data.visibility = View.VISIBLE
                    sr_layout.visibility = View.GONE
                }
            }
        })

        dataListVM.ldIsLoadingMore.observe(this, Observer{
            if (!it) {
                mAdapter.listData.remove(null)
                mAdapter.notifyDataSetChanged()
            }
        })

    }

    private fun initView() {
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        mErrorDialog = ErrorDialog(this)
        mAdapter = DataListAdapter(arrayListOf())
        mAdapter.setHasStableIds(true)
        rv_data.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
            itemAnimator = null
        }

    }


}