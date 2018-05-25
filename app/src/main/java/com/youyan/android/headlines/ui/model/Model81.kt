package com.youyan.android.headlines.ui.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

data class HeadlinesResponse(
    val message: String,
    val data: List<Data>,
    val total_number: Int,
    val has_more: Boolean,
    val login_status: Int,
    val show_et_status: Int,
    val post_content_hint: String,
    val has_more_to_refresh: Boolean,
    val action_to_last_stick: Int,
    val feed_flag: Int,
    val tips: Tips
)

data class Data(
    val content: String,
    val code: String
)

data class Tips(
    val type: String,
    val display_duration: Int,
    val display_info: String,
    val display_template: String,
    val open_url: String,
    val web_url: String,
    val download_url: String,
    val app_name: String,
    val package_name: String
)

data class Headlines(
    val abstract: String,
//    val action_extra: ActionExtra,
    val action_list: List<Action>,
    val aggr_type: Int,
    val allow_download: Boolean,
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
    val filter_words: List<FilterWord>,
    val forward_info: ForwardInfo,
    val group_id: Long,
    val has_image: Boolean,
    val has_m3u8_video: Boolean,
    val has_mp4_video: Int,
    val has_video: Boolean,
    val hot: Int,
    val ignore_web_transform: Int,
    val image_list: List<Image>,
    val is_subject: Boolean,
    val item_id: Long,
    val item_version: Int,
    val level: Int,
    val log_pb: LogPb,
    val media_info: MediaInfo,
    val media_name: String,
    val middle_image: MiddleImage,
    val need_client_impr_recycle: Int,
    val publish_time: Int,
    val read_count: Int,
    val rid: String,
    val share_count: Int,
    val share_info: ShareInfo,
    val share_url: String,
    val show_dislike: Boolean,
    val show_portrait: Boolean,
    val show_portrait_article: Boolean,
    val source: String,
    val source_avatar: String,
    val source_icon_style: Int,
    val tag: String,
    val tag_id: Long,
    val tip: Int,
    val title: String,
    val ugc_recommend: UgcRecommend,
    val url: String,
    val user_info: UserInfo,
    val user_repin: Int,
    val user_verified: Int,
    val verified_content: String,
    val video_style: Int,
    var read : Boolean
)

data class Action(
    val action: Int,
    val desc: String,
    val extra: Extra
)

data class Extra(
    val isExtra: Extra
)

data class ShareInfo(
    val cover_image: Any,
    val description: Any,
    val share_type: ShareType,
    val share_url: String,
    val title: String
)

data class ShareType(
    val pyq: Int,
    val qq: Int,
    val qzone: Int,
    val wx: Int
)

data class FilterWord(
    val id: String,
    val is_selected: Boolean,
    val name: String
)

data class ForwardInfo(
    val forward_count: Int
)

data class Image(
    val height: Int,
    val uri: String,
    val url: String,
    val url_list: List<Url>,
    val width: Int
)

data class Url(
    val url: String
)

data class UgcRecommend(
    val activity: String,
    val reason: String
)

data class LogPb(
    val impr_id: String
)

data class MediaInfo(
        val avatar_url: String,
        val follow: Boolean,
        val is_star_user: Boolean,
        val media_id: Long,
        val name: String,
        val recommend_reason: String,
        val recommend_type: Int,
        val user_id: Long,
        val user_verified: Boolean,
        val verified_content: String
)

data class MiddleImage(
    val height: Int,
    val uri: String,
    val url: String,
    val url_list: List<Url>,
    val width: Int
)

@Entity
data class UserInfo(
    @Id var id:Long = 0,

    val avatar_url: String,
    val description: String,
    val follow: Boolean,
    val follower_count: Int,
    val name: String,
    val user_id: Long,
    val user_verified: Boolean
)