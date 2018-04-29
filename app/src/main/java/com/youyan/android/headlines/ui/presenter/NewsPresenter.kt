package com.youyan.android.headlines.ui.presenter

import com.google.gson.Gson
import com.youyan.android.headlines.ui.base.BasePresenter
import com.youyan.android.headlines.ui.model.NewsData
import com.youyan.android.headlines.ui.view.NewsView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsPresenter @Inject constructor(): BasePresenter<NewsView>() {

    fun getNewsResponse(){
        apiService.getNewsResponse(0,System.currentTimeMillis())
                .map { t -> t.data }
                .map { t ->
                    val newsData:ArrayList<NewsData> = ArrayList()
                    for (data in t){
                        val news = Gson().fromJson(data.content,NewsData::class.java)
                        if (news.has_image)
                            newsData.add(news)
                    }
                    newsData
                }
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ArrayList<NewsData>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: ArrayList<NewsData>) {
                        mBaseView.onGetNewsResponseResult(t)
                    }

                    override fun onError(e: Throwable) {
                    }

                })
//        Your version of Kotlin runtime in 'Gradle: org.jetbrains.kotlin:kotlin-stdlib:1.2.30@jar' library is 1.2.30-release-78 (1.2.30),
// while plugin version is 1.2.41-release-Studio3.1-1.
//        Runtime library should be updated to avoid compatibility problems
    }
}