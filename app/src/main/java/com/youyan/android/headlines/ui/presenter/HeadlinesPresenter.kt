package com.youyan.android.headlines.ui.presenter

import com.google.gson.Gson
import com.youyan.android.headlines.ui.base.BasePresenter
import com.youyan.android.headlines.ui.model.Headlines
import com.youyan.android.headlines.ui.view.HeadlinesView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeadlinesPresenter @Inject constructor(): BasePresenter<HeadlinesView>() {

    fun getNewsResponse(){
        apiService.getHeadlinesResponse(0,System.currentTimeMillis())
                .map { t -> t.data }
                .map { t ->
                    val headlinesList:ArrayList<Headlines> = ArrayList()
                    for (data in t){
                        val headlines = Gson().fromJson(data.content,Headlines::class.java)
                        if (headlines.has_image)
                            headlinesList.add(headlines)
                    }
                    headlinesList
                }
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ArrayList<Headlines>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: ArrayList<Headlines>) {
                        mBaseView.onGetHeadlinesResponseResult(t)
                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }
}