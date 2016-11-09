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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import java_lang_programming.com.android_reactivex_demo.BuildConfig;
import java_lang_programming.com.android_reactivex_demo.util.DBHelper;

/**
 * An ModelHelper class for BookHelper
 */
public class BookHelper {

    public static final String TAG = "BookHelper";

    /**
     * return Book List
     *
     * @param context you should use ApplicationContext. ApplicationContext can get getApplicationContext().
     * @return the list objects of rows, null otherwise.
     */
    public static List<Book> getBookList(final Context context) {
        List<Book> list = new ArrayList<Book>();
        Book book = null;
        Cursor c = null;
        DBHelper dBHelper = null;
        try {
            dBHelper = new DBHelper(context);

            StringBuffer sql = new StringBuffer();
            sql.append("select ");
            sql.append(" " + Book.TABLE_NAME_OMISSION + "." + Book.COL.get(0) + ",");
            sql.append(" " + Book.TABLE_NAME_OMISSION + "." + Book.COL.get(1) + ",");
            sql.append(" " + Book.TABLE_NAME_OMISSION + "." + Book.COL.get(2) + ",");
            sql.append(" " + Book.TABLE_NAME_OMISSION + "." + Book.COL.get(3));
            sql.append(" from ");
            sql.append(" " + Book.TABLE_NAME + " " + Book.TABLE_NAME_OMISSION);

            if (BuildConfig.DEBUG) {
                Log.d(TAG, "sql" + sql.toString());
            }

            c = dBHelper.db.rawQuery(sql.toString(), null);

            boolean isResult = c.moveToFirst();

            while (isResult) {
                book = new Book();
                book.id = c.getLong(0);
                book.title = c.getString(1);
                book.category = c.getInt(2);
                book.summary = c.getString(3);
                list.add(book);
                isResult = c.moveToNext();
            }

        } catch (Exception e) {
            Log.e(TAG, "error occured!! cause : " + e.getMessage());
        } finally {

            if (c != null) {
                c.close();
            }

            if (dBHelper != null) {
                dBHelper.cleanup();
            }
        }
        return list;
    }

    /**
     * return Book
     *
     * @param context you should use ApplicationContext. ApplicationContext can get getApplicationContext().
     * @param id Book's id
     * @return the object of rows affected if id is passed in, null otherwise.
     */
    public static Book getBook(final Context context, final String id) {
        Book book = null;
        Cursor c = null;
        DBHelper dBHelper = null;
        try {
            dBHelper = new DBHelper(context);

            StringBuffer sql = new StringBuffer();
            sql.append("select ");
            sql.append(" " + Book.TABLE_NAME_OMISSION + "." + Book.COL.get(0) + ",");
            sql.append(" " + Book.TABLE_NAME_OMISSION + "." + Book.COL.get(1) + ",");
            sql.append(" " + Book.TABLE_NAME_OMISSION + "." + Book.COL.get(2) + ",");
            sql.append(" " + Book.TABLE_NAME_OMISSION + "." + Book.COL.get(3));
            sql.append(" from ");
            sql.append(" " + Book.TABLE_NAME + " " + Book.TABLE_NAME_OMISSION);
            sql.append(" where " + Book.TABLE_NAME_OMISSION + "." + Book.COL.get(0) + "=\"" + id + "\"");
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "sql" + sql.toString());
            }

            c = dBHelper.db.rawQuery(sql.toString(), null);

            boolean isResult = c.moveToFirst();

            if (isResult) {
                book = new Book();
                book.id = c.getLong(0);
                book.title = c.getString(1);
                book.category = c.getInt(2);
                book.summary = c.getString(3);
                isResult = c.moveToNext();
            }

        } catch (Exception e) {
            Log.e(TAG, "error occured!! cause : " + e.getMessage());
        } finally {

            if (c != null) {
                c.close();
            }

            if (dBHelper != null) {
                dBHelper.cleanup();
            }
        }
        return book;
    }

    /**
     * Return max id + 1 if not 1
     *
     * @param context you should use ApplicationContext. ApplicationContext can get getApplicationContext().
     * @return maxId of id
     */
    public static int getMaxId(final Context context) {
        Cursor c = null;
        DBHelper dbhelper = null;
        int maxId = 0;
        try {
            String sql = "select coalesce(id, 0) from " + Book.TABLE_NAME + " order by id desc limit 1 offset 0";
            dbhelper = new DBHelper(context);
            c = dbhelper.db.rawQuery(sql, null);
            int numRows = c.getCount();
            c.moveToFirst();
            if (0 < numRows) {
                maxId = c.getInt(0);
                c.moveToNext();
            }
            maxId++;
        } catch (Exception e) {
            Log.v(TAG, TAG, e);
        } finally {
            if (c != null && !c.isClosed()) {
                c.close();
            }
            if (dbhelper != null) {
                dbhelper.cleanup();
            }
            return maxId;
        }
    }

    /**
     * Return ContentValues
     *
     * @return contentValues
     */
    public static ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        // TODO something
        return contentValues;
    }

    /**
     * update
     *
     * @param context you should use ApplicationContext. ApplicationContext can get getApplicationContext().
     * @param contentValues
     * @param id is primary key
     * @return the number of rows affected
     */
    public static long update(final Context context, final ContentValues contentValues, String id) {
        DBHelper dBHelper = new DBHelper(context);
        long result = dBHelper.db.update(Book.TABLE_NAME, contentValues, Book.COL.get(0) + "=" + id, null);
        dBHelper.cleanup();
        return result;
    }

    /**
     * insert
     *
     * @param context you should use ApplicationContext. ApplicationContext can get getApplicationContext().
     * @param contentValues
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public static long insert(final Context context, final ContentValues contentValues) {
        DBHelper dBHelper = new DBHelper(context);
        long result = dBHelper.db.insert(Book.TABLE_NAME, null, contentValues);
        dBHelper.cleanup();
        return result;
    }

    /**
     * delete
     *
     * @param context you should use ApplicationContext. ApplicationContext can get getApplicationContext().
     * @param id is primary key
     * @return the number of rows affected if a whereClause is passed in, 0
     *         otherwise. To remove all rows and get a count pass "1" as the
     *         whereClause.
     */
    public static long delete(final Context context, String id) {
        DBHelper dBHelper = new DBHelper(context);
        int result = dBHelper.db.delete(Book.TABLE_NAME, Book.COL.get(0) + "=" + id, null);
        dBHelper.cleanup();
        return result;
    }
}