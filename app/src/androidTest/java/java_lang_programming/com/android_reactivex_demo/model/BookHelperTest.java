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

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.RenamingDelegatingContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
@SmallTest

/**
 * A Test class for for BookHelper
 */
public class BookHelperTest {

    private Context mContext;

    @Before
    public void setUp() {
        mContext = new RenamingDelegatingContext(InstrumentationRegistry.getInstrumentation().getTargetContext(), "test_");
    }

    @Test
    public void getBookList_not_found() {
        List<Book> list = BookHelper.getBookList(mContext);
        assertEquals(list.size(), 0);
    }

    @Test
    public void getBook_not_found() {
        Book book = BookHelper.getBook(mContext, "1");
        assertNull(book);
    }

    // TODO You should fix getContentValues
    @Test
    public void insert_success() {
        long result = BookHelper.insert(mContext, BookHelper.getContentValues());
        //assertNotEquals(result, -1);
    }

    @Test
    public void insert_failure() {
        long result = BookHelper.insert(mContext, BookHelper.getContentValues());
        assertEquals(result, -1);
    }

    @Test
    public void getMaxId_data_not_found() {
        int id = BookHelper.getMaxId(mContext);
        assertEquals(id, 1);
    }

    // TODO You should fix getContentValues
    @Test
    public void insert_confirm_data() {
        long result = BookHelper.insert(mContext, BookHelper.getContentValues());
        Book book = BookHelper.getBook(mContext, "1");
        //assertEquals(book.id, "1");
    }
}
