package com.education.myhttpsapplication.http;

import com.education.myhttpsapplication.App;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhonghang on 16/9/11.
 */

public class RetrofitHelper {
    private static volatile RetrofitHelper singleton;

    private RetrofitHelper() {
    }

    public static RetrofitHelper getInstance() {
        if (singleton == null) {
            synchronized (HttpUtils.class) {
                if (singleton == null) {
                    singleton = new RetrofitHelper();
                }
            }
        }
        return singleton;
    }

    private Retrofit retrofit;

    private OkHttpClient createClient() {
        OkHttpClient client = new OkHttpClient
                .Builder()
                //忽略域名验证
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        //添加域名验证,这里是默认所有的都信任
                        return true;
                    }
                })
                //添加安全证书
                .sslSocketFactory(SSLHelper.getSSLCertificate(App.getInstance()), new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                })
                .build();
        return client;
    }

    private Retrofit createRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://10.0.164.76:8443/")
                    .client(createClient())
                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory()
                    .build();
        }
        return retrofit;
    }

    private IHttpInterface httpInterface;

    public IHttpInterface createHttpInter() {
        if (httpInterface == null) {
            httpInterface = createRetrofit().create(IHttpInterface.class);
        }
        return httpInterface;
    }
}
