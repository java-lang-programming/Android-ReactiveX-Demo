package java_lang_programming.com.android_reactivex_demo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import java_lang_programming.com.android_reactivex_demo.R;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * ObservableMap sample
 */
public class ObservableMapActivity extends AppCompatActivity {

    private static final String TAG = "ObservableMapActivity";

    TextView result;
    int countSuzuki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        result = (TextView) findViewById(R.id.result);

        Button observable_from_map_j7 = (Button) findViewById(R.id.observable_from_map_j7);
        observable_from_map_j7.setOnClickListener(view -> initFromMapJava7());

        Button observable_from_map_j8 = (Button) findViewById(R.id.observable_from_map_j8);
        observable_from_map_j8.setOnClickListener(view -> initFromMapJava8());
    }

    /**
     * observable from map sample for java 7
     */
    private void initFromMapJava7() {
        countSuzuki = 0;
        Observable<String> observable = Observable.from(getList());
        observable.map(new Func1<String, Integer>() {
            @Override
            public Integer call(String str) {
                Log.d(TAG, "initFromMapJava7 call");
                if (str.equals("suzuki")) {
                    return 1;
                }
                return 0;
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "initFromMapJava7 onCompleted.");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "initFromMapJava7 onNext : integer :" + integer);
                countSuzuki = countSuzuki + integer;
                result.setText(String.valueOf(countSuzuki));
            }
        });
    }

    /**
     * observable from map sample for java 8
     */
    private void initFromMapJava8() {
        countSuzuki = 0;
        Observable<String> observable = Observable.from(getList());
        observable.map(str -> call(str)).subscribe(integer -> onNext(integer), error -> onError(error), () -> onCompleted());
    }

    /**
     * observable from map call for java 8
     *
     * @param str
     * @return
     */
    public Integer call(String str) {
        Log.d(TAG, "initJustMapJava8 call");
        if (str.equals("suzuki")) {
            return 1;
        }
        return 0;
    }

    /**
     * Get Error
     *
     * @param e
     */
    private void onError(Throwable e) {

    }

    /**
     * call complete
     */
    private void onCompleted() {
        Log.d(TAG, "onCompleted.");
    }

    /**
     * Get result
     *
     * @param integer
     */
    private void onNext(Integer integer) {
        Log.d(TAG, "initFromMapJava8 onNext : integer :" + integer);
        countSuzuki = countSuzuki + integer;
        result.setText(String.valueOf(countSuzuki));
    }

    /**
     * Get list
     *
     * @return
     */
    private List<String> getList() {
        List<String> list = new ArrayList<String>();
        list.add("suzuki");
        list.add("sasaki");
        list.add("sato");
        return list;
    }

}
