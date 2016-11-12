package java_lang_programming.com.android_reactivex_demo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java_lang_programming.com.android_reactivex_demo.R;
import rx.Observer;
import rx.subjects.BehaviorSubject;

/**
 * BehaviorSubject Sample
 */
public class BehaviorSubjectActivity extends AppCompatActivity {

    private static final String TAG = "BehaviorSubjectActivity";

    BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
    BehaviorSubject<String> behaviorSubjectDistinct = BehaviorSubject.create();

    private TextView result;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavor_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        result = (TextView) findViewById(R.id.result);
        text = (EditText) findViewById(R.id.text);

        Button behavior_subject = (Button) findViewById(R.id.behavior_subject);
        behavior_subject.setOnClickListener(view -> behavior_subject_java_7());
        behavior_subject_java_7_init();
        behavior_subject_distinct_java_7_init();

        Button behavior_subject_distinctUntilChanged = (Button) findViewById(R.id.behavior_subject_distinctUntilChanged);
        behavior_subject_distinctUntilChanged.setOnClickListener(view -> behavior_subject_distinctUntilChanged_java_7());
    }

    /**
     * initialize
     */
    private void behavior_subject_java_7_init() {
        behaviorSubject.subscribe(
                new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onNext(String strings) {
                        Log.d(TAG, "onNext :" + strings);
                        result.setText(result.getText().toString() + strings);
                    }
                });
    }

    /**
     * initialize
     */
    private void behavior_subject_distinct_java_7_init() {
        behaviorSubjectDistinct.distinctUntilChanged().subscribe(
                new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String strings) {
                        result.setText(result.getText().toString() + strings);
                    }
                });
    }

    /**
     * execution
     */
    private void behavior_subject_java_7() {
        behaviorSubject.onCompleted();
        behaviorSubject.onNext(text.getText().toString());
    }

    /**
     * execution
     */
    private void behavior_subject_distinctUntilChanged_java_7() {
        behaviorSubjectDistinct.onNext(text.getText().toString());
    }


}
