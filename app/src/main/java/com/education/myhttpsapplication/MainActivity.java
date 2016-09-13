package com.education.myhttpsapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.education.myhttpsapplication.http.HttpUtils;
import com.education.myhttpsapplication.http.RetrofitHelper;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button mBtnHttps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnHttps = (Button) findViewById(R.id.btn_https);
        mBtnHttps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = RetrofitHelper.getInstance().createHttpInter().getToken();
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("MainActivity", "网络访问正常");
                        try {
                            Log.d("MainActivity", "正常数据:" + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("MainActivity", "解析数据报错");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("MainActivity", "网络访问错误");
                        t.printStackTrace();
                    }
                });

            }
        });
    }
}
