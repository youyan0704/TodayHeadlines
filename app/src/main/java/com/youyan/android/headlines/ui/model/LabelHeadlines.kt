package com.youyan.android.headlines.ui.model

data class LabelHeadlines(
        val abstract: String,
        val action_extra: String,
        val action_list: List<Action>,
        val aggr_type: Int,
        val allow_download: Boolean,
        val article_alt_url: String,
        val article_sub_type: Int,
        val article_type: Int,
        val article_url: String,
        val ban_comment: Int,
        val behot_time: Int,
        val bury_count: Int,
        val cell_flag: Int,
        val cell_layout_style: Int,
        val cell_type: Int,
        val comment_count: Int,
        val content_decoration: String,
        val cursor: Long,
        val digg_count: Int,
        val display_url: String,
        val forward_info: ForwardInfo,
        val gallary_image_count: Int,
        val group_id: Long,
        val has_image: Boolean,
        val has_m3u8_video: Boolean,
        val has_mp4_video: Int,
        val has_video: Boolean,
        val hot: Int,
        val ignore_web_transform: Int,
        val interaction_data: String,
        val is_stick: Boolean,
        val is_subject: Boolean,
        val item_id: Long,
        val item_version: Int,
        val keywords: String,
        val label: String,
        val label_style: Int,
        val level: Int,
        val log_pb: LogPb,
        val middle_image: MiddleImage,
        val need_client_impr_recycle: Int,
        val publish_time: Int,
        val read_count: Int,
        val repin_count: Int,
        val rid: String,
        val share_count: Int,
        val share_info: ShareInfo,
        val share_type: Int,
        val share_url: String,
        val show_dislike: Boolean,
        val show_portrait: Boolean,
        val show_portrait_article: Boolean,
        val source: String,
        val source_avatar: String,
        val source_icon_style: Int,
        val source_open_url: String,
        val stick_label: String,
        val stick_style: Int,
        val tag: String,
        val tag_id: Long,
        val tip: Int,
        val title: String,
        val ugc_recommend: UgcRecommend,
        val url: String,
        val user_repin: Int,
        val user_verified: Int,
        val verified_content: String,
        val video_style: Int
)


/*
{
    "abstract": "党的十八大以来,习近平总书记踏寻英雄、缅怀英烈的足迹遍布祖国的大江南北。铭记历史、缅怀英烈,一直是习近平总书记情之所牵、行之所至。",
    "action_extra": "{\"channel_id\": 0}",
    "action_list": [
    {
        "action": 1,
        "desc": "",
        "extra": {

    }
    },
    {
        "action": 3,
        "desc": "",
        "extra": {

    }
    },
    {
        "action": 7,
        "desc": "",
        "extra": {

    }
    },
    {
        "action": 9,
        "desc": "",
        "extra": {

    }
    }
    ],
    "aggr_type": 1,
    "allow_download": false,
    "article_alt_url": "http://m.toutiao.com/group/article/6559353619169149454/",
    "article_sub_type": 1,
    "article_type": 1,
    "article_url": "http://m2.people.cn/r/MV8wXzExMDM4NDA2XzIwM18xNTI3MjA2MTYw",
    "ban_comment": 0,
    "behot_time": 1527241757,
    "bury_count": 0,
    "cell_flag": 262155,
    "cell_layout_style": 1,
    "cell_type": 0,
    "comment_count": 593,
    "content_decoration": "",
    "cursor": 1527241757999,
    "digg_count": 5,
    "display_url": "http://m2.people.cn/r/MV8wXzExMDM4NDA2XzIwM18xNTI3MjA2MTYw",
    "forward_info": {
    "forward_count": 1268
},
    "gallary_image_count": 5,
    "group_id": 6559353619169149454,
    "has_image": true,
    "has_m3u8_video": false,
    "has_mp4_video": 0,
    "has_video": false,
    "hot": 0,
    "ignore_web_transform": 1,
    "interaction_data": "",
    "is_stick": true,
    "is_subject": false,
    "item_id": 6559353619169149454,
    "item_version": 0,
    "keywords": "周易,英雄精神,中华民族,离骚,精忠报国,卧薪尝胆,纪念日",
    "label": "置顶",
    "label_style": 1,
    "level": 0,
    "log_pb": {
    "impr_id": "201805251749170100080611035443D5"
},
    "middle_image": {
    "height": 450,
    "uri": "list/85fe000c8fa514c6c026",
    "url": "http://p1.pstatp.com/list/300x196/85fe000c8fa514c6c026.webp",
    "url_list": [
    {
        "url": "http://p1.pstatp.com/list/300x196/85fe000c8fa514c6c026.webp"
    },
    {
        "url": "http://pb3.pstatp.com/list/300x196/85fe000c8fa514c6c026.webp"
    },
    {
        "url": "http://pb9.pstatp.com/list/300x196/85fe000c8fa514c6c026.webp"
    }
    ],
    "width": 800
},
    "need_client_impr_recycle": 1,
    "publish_time": 1527218481,
    "read_count": 1063854,
    "repin_count": 13444,
    "rid": "201805251749170100080611035443D5",
    "share_count": 11401,
    "share_info": {
    "cover_image": null,
    "description": null,
    "share_type": {
    "pyq": 2,
    "qq": 0,
    "qzone": 0,
    "wx": 0
},
    "share_url": "http://m.toutiao.com/group/6559353619169149454/?iid=31136043219\u0026app=news_article",
    "title": "习近平推崇的英雄精神"
},
    "share_type": 2,
    "share_url": "http://m.toutiao.com/group/6559353619169149454/?iid=31136043219\u0026app=news_article",
    "show_dislike": false,
    "show_portrait": false,
    "show_portrait_article": false,
    "source": "人民网",
    "source_avatar": "http://p3.pstatp.com/medium/dce000583275aeb699e",
    "source_icon_style": 4,
    "source_open_url": "sslocal://search?from=feed_source\u0026keyword=%E4%BA%BA%E6%B0%91%E7%BD%91",
    "stick_label": "置顶",
    "stick_style": 1,
    "tag": "news_politics",
    "tag_id": 6559353619169149454,
    "tip": 0,
    "title": "习近平推崇的英雄精神",
    "ugc_recommend": {
    "activity": "",
    "reason": ""
},
    "url": "http://m2.people.cn/r/MV8wXzExMDM4NDA2XzIwM18xNTI3MjA2MTYw",
    "user_repin": 0,
    "user_verified": 0,
    "verified_content": "",
    "video_style": 0
}*/
