/**
 * Copyright (C) 2016 Programming Java Android Development Project
 * Programming Java is
 * <p>
 * http://java-lang-programming.com/
 * <p>
 * Validation View Generator version : 1.0.2
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

package java_lang_programming.com.android_reactivex_demo.ui.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java_lang_programming.com.android_reactivex_demo.R;
import java_lang_programming.com.android_reactivex_demo.eventbus.Result;
import java_lang_programming.com.android_reactivex_demo.eventbus.RxBusProvider;

/**
 * for PubSub Sample
 */
public class PubSubInputDialogFragment extends DialogFragment {

    private TextInputLayout mWordLayout;
    private AutoCompleteTextView mWordView;

    public static PubSubInputDialogFragment newInstance() {
        PubSubInputDialogFragment fragment = new PubSubInputDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pub_sub_input_dialog, container, false);

        mWordLayout = (TextInputLayout) view.findViewById(R.id.word_text_input_layout);
        mWordView = (AutoCompleteTextView) view.findViewById(R.id.word);

        mWordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_SEND) {
                    String word = textView.getText().toString();
                    mWordLayout.setError(getInValidWordMessage(word));
                    return true;
                }
                return false;
            }
        });

        Button btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(v -> {
            setResult();
        });

        return view;
    }

    /**
     * Return error message.
     * エラーがない場合は、nullを返す。
     *
     * @param str word
     * @return
     */
    public String getInValidWordMessage(String str) {
        if (TextUtils.isEmpty(str)) {
            return getString(R.string.validate_require, "word");
        } else if (str.length() < getResources().getInteger(R.integer.word_min_count)) {
            return getString(R.string.validate_minimun_word, "word", getResources().getInteger(R.integer.word_min_count));
        } else if (str.length() > getResources().getInteger(R.integer.word_max_count)) {
            return getString(R.string.validate_maximun_word, "word", getResources().getInteger(R.integer.word_max_count));
        } else {
            return null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * 入力結果をActivityに設定する
     */
    private void setResult() {
        RxBusProvider.getInstance().send(new Result(mWordView.getText().toString()));
//        if (getActivity() instanceof PubSubActivity) {
//            ((PubSubActivity) getActivity()).setTextViewResult(mWordView.getText().toString());
//        }
        dismiss();
    }
}
