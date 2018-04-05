package com.youyan.android.headlines.network

import com.youyan.android.headlines.common.getHtml
import com.youyan.android.headlines.ui.model.Recommend
import com.youyan.android.headlines.utils.LoggerUtil
import org.jsoup.Jsoup

/**
 * Created by android on 3/27/18.
 */
class RecommendSource : Source<ArrayList<Recommend>>{

    companion object {
        val BASE_URL = "https://www.toutiao.com/"
        val URL = "http://news.qq.com/"
    }

    override fun get(): ArrayList<Recommend> {
        val recommendSourceList = java.util.ArrayList<Recommend>()

        val html = getHtml(URL)
        val doc = Jsoup.parse(html)
//        LoggerUtil.i("TAG",doc.toString())

        val elements = doc.select("div.Q-tpList").select("div.Q-tpWrap")
        for (element in elements){
            val url = element.select("a").attr("href")
            val image = element.select("a").select("img").attr("src")
            val title = element.select("div.text").select("em").select("a").text()
            LoggerUtil.i("TAG",title)

            val recommend = Recommend(title,url,image,"","","","",false)
            recommendSourceList.add(recommend)
        }


        /*val elements = doc.select("div.bui-box single-mode")
        for (element in elements){
            val image = "https:" + element.select("div.bui-left single-mode-lbox").select("a.img-wrap")
                    .select("img.lazy-load-img").attr("src")

            val element_inner = element.select("div.single-mode-rbox").select("div.single-mode-rbox-inner")
            val title = element_inner.select("div.title-box").select("a.link").text()
            val url = BASE_URL + element_inner.select("div.title-box").select("a.link").attr("href")

            val element_inner_footer = element_inner.select("div.bui-box footer-bar").select("div.bui-left footer-bar-left")
            val category = element_inner_footer.select("a.footer-bar-action tag tag-style-other").text()
            val source = element_inner_footer.select("a.footer-bar-action source").text()
            val comment = element_inner_footer.select("a.footer-bar-action source").text()
            val publishTime = element_inner_footer.select("span.footer-bar-action").text()

            val recommend = Recommend(title,url,image,category,source,comment,publishTime,false)
            recommendSourceList.add(recommend)
        }*/

        /*val elements = doc.select("li.item    ").select("div.item-inner y-box")
        for (element in elements){
            val id = element.parent().attr("group_id").toInt()
            val element_title = element.select("div.normal rbox ").select("div.rbox-inner")
                    .select("div.title-box").select("a")
            val title = element_title.text()
            val url = "https:" + element_title.attr("href")
            val image = element.select("div.lbox").select("a").select("img").attr("src")
            val element_footer = element.select("div.normal rbox ").select("div.y-box footer")
                    .select("div.y-left")
            val publishTime = element_footer.select("span").text()
            val source = element.select("div.y-left").select("a.lbtn source").text()
            val comment = element.select("div.y-left").select("a.lbtn comment").text()


        }*/

        return recommendSourceList
    }

}