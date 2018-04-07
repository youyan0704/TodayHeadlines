package com.youyan.android.headlines.ui.presenter

import com.google.gson.Gson
import com.youyan.android.headlines.ui.base.BasePresenter
import com.youyan.android.headlines.ui.model.Data
import com.youyan.android.headlines.ui.model.NewsData
import com.youyan.android.headlines.ui.model.NewsResponse
import com.youyan.android.headlines.ui.view.NewsView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

class NewsPresenter : BasePresenter<NewsView>() {

    fun getNewsResponse(){
        apiService.getNewsResponse()
                .map(object : Function<NewsResponse,ArrayList<Data>> {
                    override fun apply(t: NewsResponse): ArrayList<Data> {
                        return t.data
                    }

                })
                .map(object : Function<ArrayList<Data>,ArrayList<NewsData>> {
                    override fun apply(t: ArrayList<Data>): ArrayList<NewsData> {
                        val newsData:ArrayList<NewsData> = ArrayList()
                        for (data in t){
                            val news = Gson().fromJson(data.content,NewsData::class.java)
                            if (news.has_image)
                                newsData.add(news)
                        }
                        return newsData
                    }

                })
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

    }
}