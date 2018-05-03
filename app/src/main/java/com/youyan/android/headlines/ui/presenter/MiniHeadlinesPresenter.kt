package com.youyan.android.headlines.ui.presenter

import com.youyan.android.headlines.ui.base.BasePresenter
import com.youyan.android.headlines.ui.model.HeadlinesResponse
import com.youyan.android.headlines.ui.view.MiniHeadlinesView
import com.youyan.android.headlines.utils.LoggerUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MiniHeadlinesPresenter @Inject constructor(): BasePresenter<MiniHeadlinesView>() {

    fun getMiniHeadlinesResponse(){
        apiService.getDataResponse("weitoutiao",0,System.currentTimeMillis())
//                .map { t -> t.data }
//                .map { t ->
//                    val newsData:ArrayList<NewsData> = ArrayList()
//                    for (data in t){
//                        val news = Gson().fromJson(data.content,NewsData::class.java)
//                        LoggerUtil.i("newsGson",news.toString())
//                            newsData.add(news)
//                    }
//                    newsData
//                }
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<HeadlinesResponse> {
                    override fun onComplete() {
                        LoggerUtil.i("NewsResponse","onCompleted")
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: HeadlinesResponse) {
//                        LoggerUtil.i("onGetMiniHeadlinesResponseResult",t.toString())
                        mBaseView.onGetMiniHeadlinesResponseResult(t)
                    }

                    override fun onError(e: Throwable) {
                        LoggerUtil.i("NewsResponse",e.message)
                    }

                })

    }
}