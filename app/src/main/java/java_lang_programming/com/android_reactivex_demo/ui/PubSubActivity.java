/**
 * Copyright (C) 2016 Programming Java Android Development Project
 * Programming Java is
 * <p>
 * http://java-lang-programming.com/
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java_lang_programming.com.android_reactivex_demo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java_lang_programming.com.android_reactivex_demo.R;
import java_lang_programming.com.android_reactivex_demo.eventbus.Result;
import java_lang_programming.com.android_reactivex_demo.eventbus.RxBusProvider;
import java_lang_programming.com.android_reactivex_demo.ui.dialog.PubSubInputDialogFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * for PubSub Sample
 */
public class PubSubActivity extends AppCompatActivity {

    private TextView result;
    private Button btn;

    private CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub_sub);

        result = (TextView) findViewById(R.id.result);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(view -> {
            openDialog();
        });

        // pub sub
        compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(RxBusProvider.getInstance().
                toObserverable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    if (o instanceof Result) {
                        setTextViewResult(((Result) o).getResult());
                    }
                })
        );
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

    private void openDialog() {
        PubSubInputDialogFragment newFragment = PubSubInputDialogFragment.newInstance();
        newFragment.show(getFragmentManager(), "dialog");
    }

    /**
     * set str data to result.
     *
     * @param str
     */
    public void setTextViewResult(String str) {
        result.setText(str);
    }
}
