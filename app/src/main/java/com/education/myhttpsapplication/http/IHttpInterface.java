package com.education.myhttpsapplication.http;


import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by zhonghang on 16/9/9.
 */

public interface IHttpInterface {
    /**
     * @return 返回的信息是json数据, 主要包含了token的信息, code返回值为200时返回的为正常数据
     */
    @GET("MyJsonFileTest/helloworld.html")
    Call<ResponseBody> getToken();
}
