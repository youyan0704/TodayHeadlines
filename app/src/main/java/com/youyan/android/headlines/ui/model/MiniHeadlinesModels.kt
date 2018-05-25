package com.youyan.android.headlines.ui.model

import io.objectbox.annotation.Entity

@Entity
data class MiniHeadlines(
    val abstract: String,
    val action_list: List<Action>,
    val allow_download: Boolean,
    val article_sub_type: Int,
    val article_type: Int,
    val ban_comment: Int,
    val behot_time: Int,
    val brand_info: String,
    val bury_count: Int,
    val cell_flag: Int,
    val cell_layout_style: Int,
    val cell_type: Int,
    val cell_ui_type: String,
    val comment_count: Int,
    val comments: List<Comment>,
    val content: String,
    val content_decoration: String,
    val content_rich_span: String,
    val create_time: Int,
    val cursor: Long,
    val default_text_line: Int,
    val digg_count: Int,
    val filter_words: List<FilterWord>,
    val follow: Int,
    val follow_button_style: Int,
    val forum: Forum,
    val forward_info: ForwardInfo,
    val friend_digg_list: List<String>,
    val has_m3u8_video: Boolean,
    val has_mp4_video: Int,
    val has_video: Boolean,
    val hot: Int,
    val ignore_web_transform: Int,
    val inner_ui_flag: Int,
    val is_stick: Int,
    val is_subject: Boolean,
    val item_version: Int,
    val large_image_list: List<LargeImage>,
    val level: Int,
    val log_pb: MiniLogPb,
    val max_text_line: Int,
    val need_client_impr_recycle: Int,
    val position: Position,
    val preload: Int,
    val read_count: Int,
    val repost_params: RepostParams,
    val rid: String,
    val schema: String,
    val share_count: Int,
    val share_info: ShareInfo,
    val share_url: String,
    val show_dislike: Boolean,
    val show_portrait: Boolean,
    val show_portrait_article: Boolean,
    val stick_style: Int,
    val thread_id: Long,
    val thumb_image_list: List<ThumbImage>,
    val tiny_toutiao_url: String,
    val tip: Int,
    val title: String,
    val ugc_cut_image_list: List<UgcCutImage>,
    val ugc_recommend: UgcRecommend,
    val ugc_u13_cut_image_list: List<UgcU13CutImage>,
    val ui_type: Int,
    val user: User,
    val user_digg: Int,
    val user_repin: Int,
    val user_verified: Int,
    val verified_content: String,
    val video_style: Int
)

data class Comment(
    val content: String
)

data class Position(
    val position: String
)

data class UgcCutImage(
    val height: Int,
    val type: Int,
    val uri: String,
    val url: String,
    val url_list: List<Url>,
    val width: Int
)

data class LargeImage(
    val height: Int,
    val type: Int,
    val uri: String,
    val url: String,
    val url_list: List<Url>,
    val width: Int
)

data class User(
    val avatar_url: String,
    val desc: String,
    val id: Long,
    val is_blocked: Int,
    val is_blocking: Int,
    val is_following: Int,
    val is_friend: Int,
    val medals: List<Any>,
    val name: String,
    val remark_name: Any,
    val schema: String,
    val screen_name: String,
    val user_auth_info: String,
    val user_decoration: String,
    val user_id: Long,
    val user_verified: Int,
    val verified_content: String
)

data class MiniLogPb(
    val group_source: String,
    val impr_id: String
)

data class RepostParams(
    val cover_url: Any,
    val fw_id: Long,
    val fw_id_type: Int,
    val fw_native_schema: Any,
    val fw_share_url: Any,
    val fw_user_id: Long,
    val has_video: Any,
    val opt_id: Long,
    val opt_id_type: Int,
    val repost_type: Int,
    val schema: String,
    val title: Any,
    val title_rich_span: Any
)


data class Forum(
    val isForum: Forum
)

data class ThumbImage(
    val height: Int,
    val type: Int,
    val uri: String,
    val url: String,
    val url_list: List<Url>,
    val width: Int
)

data class UgcU13CutImage(
    val height: Int,
    val type: Int,
    val uri: String,
    val url: String,
    val url_list: List<Url>,
    val width: Int
)