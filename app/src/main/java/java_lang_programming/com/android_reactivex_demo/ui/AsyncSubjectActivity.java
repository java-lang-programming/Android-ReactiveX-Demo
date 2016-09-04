package java_lang_programming.com.android_reactivex_demo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java_lang_programming.com.android_reactivex_demo.R;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

public class AsyncSubjectActivity extends AppCompatActivity {

    private static final String TAG = "AsyncSubjectActivity";

    private TextView result;
    private Button push;

    //private PublishSubject<String> publishSubject;
    private BehaviorSubject<String> behaviorSubject;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_subject);

        result = (TextView) findViewById(R.id.result);
        push = (Button) findViewById(R.id.push);
        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push();
            }
        });
        init();
    }

    private void init() {
        //publishSubject = PublishSubject.create();
        behaviorSubject = BehaviorSubject.create("test");

        //subscription = publishSubject.debounce(400, TimeUnit.MILLISECONDS).
        subscription = behaviorSubject.debounce(400, TimeUnit.MILLISECONDS).
                observeOn(Schedulers.io()).map(new Func1<String, List<String>>() {
            @Override
            public List<String> call(String s) {
                Log.d(TAG, "map func call :" + s);
                List<String> list = getSampleList();
                return list;
            }
        }).subscribe(new Observer<List<String>>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted :");
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onNext(List<String> list) {
                Log.d(TAG, "onNext :" + list.size());
                result.setText(String.valueOf(list.size()));
            }
        });
    }

    private void push() {
        //getSampleList()
        behaviorSubject.onNext("ffff");
        //publishSubject.onNext("5");
    }

    private List<String> getSampleList() {
        List<String> list = new ArrayList<String>();
        list.add("su-zuki");
        list.add("sa-saki");
        list.add("sa-to");
        return list;
    }
}
