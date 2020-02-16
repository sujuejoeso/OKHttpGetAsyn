package com.joeso.okhttptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Handler mHandler=new Handler();
    TextView tvCode,tvDetails,tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
        tvCode=findViewById(R.id.tvCode);
        tvDetails=findViewById(R.id.tvDetails);
        tvContent=findViewById(R.id.tvContent);
        tvContent.setMovementMethod(new ScrollingMovementMethod());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient=new OkHttpClient();
                final Request request=new Request.Builder().url("http://dummy.restapiexample.com/api/v1/employees").build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        tvContent.setText(e.getMessage());
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        //can't call UI component here
                        Log.d("jjjj",String.valueOf(response.code()));
                        Log.d("jjjj",String.valueOf(response.body().string()));
                    }
                });
            }
        });




    }
}
