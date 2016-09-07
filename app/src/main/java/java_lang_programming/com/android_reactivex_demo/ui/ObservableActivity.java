package java_lang_programming.com.android_reactivex_demo.ui;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import java_lang_programming.com.android_reactivex_demo.R;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Observable Sample
 */
public class ObservableActivity extends AppCompatActivity {

    private static final String TAG = "ObservableActivity";

    TextView result;
    int countSuzuki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button observable_just_j7 = (Button) findViewById(R.id.observable_just_j7);
        observable_just_j7.setOnClickListener(view -> {
            observableJustJava7();
        });

        Button observable_just_j8 = (Button) findViewById(R.id.observable_just_j8);
        observable_just_j8.setOnClickListener(view -> {
            observableJustJava8();
        });

        Button observable_just_map_j7 = (Button) findViewById(R.id.observable_from_map_j7);
        observable_just_map_j7.setOnClickListener(view -> initFromMapJava7());

        Button observable_just_map_j8 = (Button) findViewById(R.id.observable_from_map_j8);
        observable_just_map_j8.setOnClickListener(view -> initFromMapJava8());

        Button observable_just_flatmap_j7 = (Button) findViewById(R.id.observable_just_flatmap_j7);
        observable_just_flatmap_j7.setOnClickListener(view -> {
            observableJustFlatMapJava7();
        });

        result = (TextView) findViewById(R.id.result);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    /**
     * initialization
     */
    private void observableJustJava7() {
        Observable<List<String>> observable = Observable.just(getList());
        observable.subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observableJustJava7 onCompleted.");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<String> list) {
                Log.d(TAG, "observableJustJava7 onNext : list count :" + list.size());
                result.setText(String.valueOf(list.size()));
            }
        });
    }

    /**
     * initialization
     */
    private void observableJustJava8() {
        Observable<List<String>> observable = Observable.just(getList());
        observable.subscribe(list -> onNext(list), error -> onError(error), () -> onCompleted());
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

    /**
     * Get result
     *
     * @param list
     */
    private void onNext(List<String> list) {
        Log.d(TAG, "observableJustJava8 onNext : list count :" + list.size());
        result.setText(String.valueOf(list.size()));
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
     * observable just map sample for java 7
     */
    private void initFromMapJava7() {
        countSuzuki = 0;
        Observable<String> observable = Observable.from(getList());
        observable.map(new Func1<String, Integer>() {
            @Override
            public Integer call(String str) {
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
     * observable just map sample for java 8
     */
    private void initFromMapJava8() {
        countSuzuki = 0;
        Observable<String> observable = Observable.from(getList());
        observable.map(str -> call(str)).subscribe(integer -> onNext(integer), error -> onError(error), () -> onCompleted());
    }

    /**
     * observable just map call for java 8
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
     * observable just map sample for java 7
     */
    private void observableJustFlatMapJava7() {
        Observable<List<String>> observable = Observable.just(getList());
        observable.flatMap(new Func1<List<String>, Observable<List<String>>>() {
            @Override
            public Observable<List<String>> call(List<String> list) {
                List<String> newList = new ArrayList<String>();
                for (String string : list) {
                    if ("suzuki".equals(string)) {
                        newList.add(string);
                    }
                }
                return Observable.just(newList);
            }
        }).map(new Func1<List<String>, Integer>() {
            @Override
            public Integer call(List<String> list) {
                return list.size();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observableJustFlatMapJava7 onCompleted.");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "observableJustFlatMapJava7 onNext : list count :" + integer);
            }
        });
    }
}

//            @Override
//            public Integer call(List<String> list) {
//                Log.d(TAG, "call");
//                return list.size();
//            }