package com.youyan.android.headlines.network.service

import com.youyan.android.headlines.ui.model.HeadlinesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    //http://is.snssdk.com/api/news/feed/v81/?list_count=20&refer=1&refresh_reason=5&session_refresh_idx=1&count=20&min_behot_time=1523715460&last_refresh_sub_entrance_interval=1523800439&loc_mode=0&loc_time=1522107365&latitude=22.600269&longitude=114.044794&city=%E9%BE%99%E5%8D%8E%E5%8C%BA&tt_from=enter_auto&lac=9547&cid=102640319&plugin_enable=3&st_time=1&strict=1&iid=30354265461&device_id=48766275096&ac=wifi&channel=update&aid=13&app_name=news_article&version_code=666&version_name=6.6.6&device_platform=android&ab_version=321579%2C320329%2C317111%2C295921%2C313416%2C304139%2C319583%2C311767%2C317778%2C313901%2C317498%2C319798%2C318243%2C295827%2C313558%2C239097%2C321783%2C311779%2C170988%2C318441%2C320218%2C318406%2C281392%2C297059%2C276206%2C286211%2C313219%2C313473%2C320224%2C312563%2C321362%2C322964%2C322322%2C319495%2C317412%2C323265%2C304132%2C317697%2C317077%2C280773%2C321012%2C319960%2C185731%2C317209%2C312798%2C321405%2C313496%2C301282%2C214069%2C31648%2C319257%2C323371%2C258356%2C247850%2C280447%2C281294%2C249045%2C322100%2C309751%2C321413%2C288417%2C290194%2C260657%2C321025%2C271178%2C314796%2C249828&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_feature=94563%2C102749&abflag=3&ssmix=a&device_type=SUGAR+S11&device_brand=SUGAR&language=zh&os_api=25&os_version=7.1.2&uuid=867794030048631&openudid=4e5bcda9b903eec2&manifest_version_code=666&resolution=1080*2052&dpi=480&update_version_code=66611&_rticket=1523800440373&plugin=10607&fp=G2TqJ2KWFSHeFlD5FSU1FlxScrZO&pos=5r_-9Onkv6e_dCMEeBATeBEnv7G_8fLz-vTp6Pn4v6esrKmzrampqqSpsb_x_On06ej5-L-nr6-zq62tr6uksb_88Pzt3vTp5L-nv3sqLHgBLnglH7-xv_zw_O3R8vP69Ono-fi_p6ysqbOtqamlr6-xv_zw_O3R_On06ej5-L-nr6-zq62tr6iv4A%3D%3D&rom_version=25&ts=1523800437&as=a255e58dc527ea99839715&mas=0055f616cd37e8cde27d793f42c6aba29c5ddd05582d4a6c78&cp=53a9df365e977q1

    @GET("api/news/feed/v81/?refer=1&count=20&loc_mode=0&device_id=48766275096&iid=31136043219")
    fun getHeadlinesResponse(
            @Query("min_behot_time") lastTime : Long,
            @Query("last_refresh_sub_entrance_interval") currentTime : Long
        ): Observable<HeadlinesResponse>


//    http://is.snssdk.com/api/news/feed/v81/?
    // list_count=18
    // &category=weitoutiao
    // &concern_id=6368255615201970690
    // &refer=1
    // &refresh_reason=1
    // &session_refresh_idx=2
    // &count=20
    // &min_behot_time=1524903086
    // &last_refresh_sub_entrance_interval=1524923276
    // &loc_mode=0
    // &loc_time=1524063214
    // &latitude=22.600139
    // &longitude=114.044714
    // &city=%E9%BE%99%E5%8D%8E%E5%8C%BA
    // &tt_from=pull
    // &lac=9547
    // &cid=102640425
    // &plugin_enable=3
    // &iid=31136043219
    // &device_id=48766275096
    // &ac=wifi
    // &channel=wifi-ky-dsp-and37
    // &aid=13
    // &app_name=news_article
    // &version_code=667
    // &version_name=6.6.7
    // &device_platform=android
    // &ab_version=333792%2C317498%2C336556%2C295827%2C334674%2C315441%2C239097%2C324285%2C170988%2C335428%2C327771%2C332098%2C325199%2C335850%2C336443%2C330632%2C297059%2C276206%2C286211%2C313219%2C328615%2C332041%2C317667%2C329477%2C321362%2C322964%2C322322%2C327534%2C334583%2C333881%2C335102%2C334827%2C323265%2C328670%2C324005%2C317077%2C280773%2C319960%2C185731%2C333681%2C321405%2C331720%2C336452%2C214069%2C31648%2C332126%2C247850%2C280447%2C281294%2C328218%2C322100%2C325619%2C333331%2C330464%2C336199%2C323430%2C288417%2C290194%2C260657%2C333409%2C326187%2C324614%2C335477%2C271178%2C326589%2C326524%2C326532
    // &ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7
    // &ab_feature=94563%2C102749
    // &abflag=3
    // &ssmix=a
    // &device_type=SUGAR+S11
    // &device_brand=SUGAR
    // &language=zh
    // &os_api=25
    // &os_version=7.1.2
    // &uuid=867794030048631
    // &openudid=4e5bcda9b903eec2
    // &manifest_version_code=667
    // &resolution=1080*2052
    // &dpi=480
    // &update_version_code=66709
    // &_rticket=1524923276387
    // &plugin=10607
    // &fp=G2TqJ2KWFSHeFlD5FSU1FlxScrZO
    // &pos=5r_-9Onkv6e_dCMEeBATeBEnv7G_8fLz-vTp6Pn4v6esrKmzrampqqypsb_x_On06ej5-L-nr6-zq62trK6ksb_88Pzt3vTp5L-nv3sqLHgBLnglH7-xv_zw_O3R8vP69Ono-fi_p6ysrrOkpK6lr7G__PD87dH86fTp6Pn4v6evr7OorqWpq6vg
    // &rom_version=25
    // &ts=1524923272
    // &as=a2c5b7bea8e84adbe41272
    // &mas=00d8af7b88cb0c6ff9fcfcb0716c57eac02cca4e468c208af8
    // &cp=5ca4e64d7fb8cq1

    @GET("api/news/feed/v81/?refer=1&count=20&loc_mode=0&device_id=48766275096&iid=31136043219")
    fun getDataResponse(@Query("category") category : String,
                    @Query("min_behot_time") lastTime : Long,
                    @Query("last_refresh_sub_entrance_interval") currentTime : Long)
            : Observable<HeadlinesResponse>
}