package com.example.myexperiment.RxJavaTest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myexperiment.R;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxJavaTestActivity extends AppCompatActivity {

    private Button button;
    private TextView tv1, tv2, tv3, tv4;
    private List<String> DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_test);

        button = findViewById(R.id.btn_rxjava);
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv_4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Service service = RetrofitManager.getInstance().create(Service.class);
                service.getdata("30549ca60997f4f69aa548b93d38b689","哥斯拉")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RBean> () {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(RBean rBean) {
                                tv1.setText(rBean.getResultcode());
                                tv2.setText(rBean.getResult().get(0).getTitle());
                                tv3.setText(rBean.getResult().get(0).getType());
                                tv4.setText(rBean.getResult().get(0).getCountry());
                            }

                            @Override
                            public void onError(Throwable e) {
                                tv1.setText(e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(RxJavaTestActivity.this, "Getting movie is completed", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}