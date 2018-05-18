package com.youyan.android.headlines.ui.presenter

import com.youyan.android.headlines.app.BaseApplication
import com.youyan.android.headlines.ui.base.BasePresenter
import com.youyan.android.headlines.ui.model.HeadlinesResponse
import com.youyan.android.headlines.ui.model.MiniHeadlinesData
import com.youyan.android.headlines.ui.view.MiniHeadlinesView
import com.youyan.android.headlines.utils.LoggerUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MiniHeadlinesPresenter @Inject constructor(): BasePresenter<MiniHeadlinesView>() {

    private val miniHeadlinesDataBox = BaseApplication.getBoxStoreInstance().boxFor(MiniHeadlinesData::class.java)
    private var miniHeadlinesData: MiniHeadlinesData = MiniHeadlinesData()
    init {
        if (miniHeadlinesDataBox.all.isEmpty()){
            miniHeadlinesData.lastRequestTime = 0
        }
    }

    fun getMiniHeadlinesResponse(){
        apiService.getDataResponse("weitoutiao",miniHeadlinesData.lastRequestTime,System.currentTimeMillis())
                .distinct()
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<HeadlinesResponse> {
                    override fun onComplete() {
                        mBaseView.hideLoading()
                        LoggerUtil.i("getMiniHeadlinesResponse","onCompleted")
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: HeadlinesResponse) {
//                        LoggerUtil.i("getMiniHeadlinesResponse",t.toString())
                        mBaseView.onGetMiniHeadlinesResponseResult(t)
                        miniHeadlinesData.lastRequestTime = System.currentTimeMillis()
                        miniHeadlinesDataBox.put(miniHeadlinesData)

                    }

                    override fun onError(e: Throwable) {
                        mBaseView.onGetResultError()
                        LoggerUtil.i("getMiniHeadlinesResponse",e.message)
                    }

                })

    }
}