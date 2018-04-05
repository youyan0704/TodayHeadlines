package com.youyan.android.headlines.network.service

import com.youyan.android.headlines.ui.model.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService{

    //http://it.snssdk.com/api/news/feed/v80/?
    // list_count=104
    // &concern_id=6286225228934679042
    // &refer=1
    // &refresh_reason=1
    // &session_refresh_idx=8
    // &count=20
    // &min_behot_time=1522913066
    // &last_refresh_sub_entrance_interval=1522913114
    // &loc_mode=0
    // &loc_time=1522107365
    // &latitude=22.600269
    // &longitude=114.044794
    // &city=%E9%BE%99%E5%8D%8E%E5%8C%BA
    // &tt_from=pull
    // &lac=9547
    // &cid=102640329
    // &plugin_enable=3
    // &st_time=7598
    // &strict=1
    // &iid=28264464895
    // &device_id=48766275096
    // &ac=wifi
    // &channel=wifi-ky-dsp-and37
    // &aid=13
    // &app_name=news_article
    // &version_code=664
    // &version_name=6.6.4
    // &device_platform=android
    // &ab_version=309091%2C313613%2C316146%2C313416%2C307345%2C304139%2C311767%2C314314%2C312392%2C313901%2C314157%2C313237%2C307808%2C295827%2C241818%2C239097%2C314902%2C311779%2C170988%2C311208%2C316124%2C304234%2C281392%2C299983%2C297059%2C243584%2C276206%2C286211%2C313219%2C313473%2C257281%2C302273%2C302060%2C312563%2C314162%2C313117%2C304603%2C307325%2C304132%2C311912%2C314164%2C240867%2C280773%2C308865%2C185731%2C309934%2C312798%2C313496%2C301282%2C315204%2C315208%2C31648%2C309921%2C315750%2C315432%2C258356%2C247850%2C280447%2C281294%2C310242%2C249045%2C315709%2C309751%2C314561%2C312613%2C314401%2C296952%2C288417%2C290194%2C260657%2C310995%2C241181%2C316174%2C271178%2C249828%2C246859%2C311000
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
    // &manifest_version_code=664
    // &resolution=1080*2052
    // &dpi=480
    // &update_version_code=66409
    // &_rticket=1522913114531
    // &plugin=10575
    // &fp=G2TqJ2KWFSHeFlD5FSU1FlxScrZO
    // &pos=5r_-9Onkv6e_dCMEeBATeBEnv7G_8fLz-vTp6Pn4v6esrKmzrampqqSpsb_x_On06ej5-L-nr6-zq62tr6uksb_88Pzt3vTp5L-nv3sqLHgBLnglH7-xv_zw_O3R8vP69Ono-fi_p6ysqbOtqamlrKuxv_zw_O3R_On06ej5-L-nr6-zq62tr6mo4A%3D%3D
    // &rom_version=25
    // &ts=1522913110
    // &as=a2d5fcec66b5babf650236
    // &mas=0000a7dda7438fea15a363eb7da409907d0a0262ce8400a89e
    // &cp=57a7ce5ac5f5aq1


    @GET("api/news/feed/v80/")
    fun getNewsResponse(): Observable<NewsResponse>

}