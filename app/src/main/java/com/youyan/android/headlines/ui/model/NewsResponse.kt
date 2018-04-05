package com.youyan.android.headlines.ui.model


data class NewsResponse(
		val message: String,
		val data: List<Data>,
		val total_number: Int,
		val has_more: Boolean,
		val login_status: Int,
		val show_et_status: Int,
		val post_content_hint: String,
		val has_more_to_refresh: Boolean,
		val action_to_last_stick: Int,
		val sub_entrance_list: List<Any>,
		val feed_flag: Int,
		val tips: Tips
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

data class Data(
		val content: String,
		val code: String
)