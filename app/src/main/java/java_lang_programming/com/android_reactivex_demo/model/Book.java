/**
 * Copyright (C) 2016 Programming Java Android Development Project
 * Programming Java is
 * <p>
 * http://java-lang-programming.com/
 * <p>
 * Model Generator version : 1.3.1
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

package java_lang_programming.com.android_reactivex_demo.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.text.TextUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * An Model class for Book
 */
public class Book implements Parcelable {

    public static final String TAG = "Book";
    // table name
    public static final String TABLE_NAME = "books";
    // table name aliases
    public static final String TABLE_NAME_OMISSION = "b";
    // column list constant
    public static final List<String> COL = Collections
            .unmodifiableList(new LinkedList<String>() {
                {
                    add("id");
                    add("title");
                    add("category");
                    add("summary");

                }
            });

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            CATEGORY_BOOK,
            CATEGORY_KINDLE,
    })
    public @interface category {
    }

    public static final int CATEGORY_BOOK = 1;
    public static final int CATEGORY_KINDLE = 2;

    // id
    public long id;
    // title
    public String title;
    // category
    public int category;
    // summary
    public String summary;

    public Book() {
    }

    public static final Parcelable.Creator<Book> CREATOR
            = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    private Book(Parcel in) {
        id = in.readLong();
        title = in.readString();
        category = in.readInt();
        summary = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(title);
        out.writeInt(category);
        out.writeString(summary);
    }

    /**
     * Return true if book
     *
     * @param category
     * @return
     */
    public boolean isBook(@category int category) {
        if (CATEGORY_BOOK == category) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("Book [");
        str.append(" id=" + id);
        if (!TextUtils.isEmpty(title)) {
            str.append(", title=" + title);
        }
        str.append(", category=" + category);
        if (!TextUtils.isEmpty(summary)) {
            str.append(", summary=" + summary);
        }
        str.append("]");
        return str.toString();
    }
}
