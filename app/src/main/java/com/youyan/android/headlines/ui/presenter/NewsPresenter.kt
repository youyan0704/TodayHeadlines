package com.youyan.android.headlines.ui.presenter

import com.youyan.android.headlines.ui.base.BasePresenter
import com.youyan.android.headlines.ui.model.NewsResponse
import com.youyan.android.headlines.ui.view.NewsView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewsPresenter : BasePresenter<NewsView>() {

    fun getNewsResponse(){
        apiService.getNewsResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<NewsResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: NewsResponse) {
                        mBaseView.onGetNewsResponseResult(t)
                    }

                    override fun onError(e: Throwable) {
                    }

                })

    }
}