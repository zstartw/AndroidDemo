package com.betterzw.androiddemo.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class EShopContentProvider extends ContentProvider {
    private SQLiteOpenHelper mOpenHelper;

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SqlArguments args = new SqlArguments(uri, selection, selectionArgs);

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        return db.delete(args.table, args.where, args.args);
    }

    @Override
    public String getType(Uri uri) {
        SqlArguments args = new SqlArguments(uri, null, null);
        if (TextUtils.isEmpty(args.where)) {
            return "vnd.android.cursor.dir/" + args.table;
        } else {
            return "vnd.android.cursor.item/" + args.table;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SqlArguments args = new SqlArguments(uri);

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final long rowId = db.insert(args.table, null, values);
        if (rowId <= 0) return null;

        return ContentUris.withAppendedId(uri, rowId);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = DatabaseHelper.getInstance(getContext());
//        Log.d("EShopContentProvider onCreate");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        try {
            SqlArguments args = new SqlArguments(uri, selection, selectionArgs);
            SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
            qb.setTables(args.table);

            SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            if (!db.isOpen()) {
                db = mOpenHelper.getWritableDatabase();
            }
            Cursor result = qb.query(db, projection, args.where, args.args, null, null, sortOrder);
            result.setNotificationUri(getContext().getContentResolver(), uri);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SqlArguments args = new SqlArguments(uri, selection, selectionArgs);

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        return db.update(args.table, values, args.where, args.args);
    }

    static class SqlArguments {
        public final String table;
        public final String where;
        public final String[] args;

        SqlArguments(Uri url, String where, String[] args) {
            if (url.getPathSegments().size() == 1) {
                this.table = url.getPathSegments().get(0);
                this.where = where;
                this.args = args;
            } else if (url.getPathSegments().size() != 2) {
                throw new IllegalArgumentException("Invalid URI: " + url);
            } else if (!TextUtils.isEmpty(where)) {
                throw new UnsupportedOperationException("WHERE clause not supported: " + url);
            } else {
                this.table = url.getPathSegments().get(0);
                this.where = "_id=" + ContentUris.parseId(url);
                this.args = null;
            }
        }

        SqlArguments(Uri url) {
            if (url.getPathSegments().size() == 1) {
                table = url.getPathSegments().get(0);
                where = null;
                args = null;
            } else {
                throw new IllegalArgumentException("Invalid URI: " + url);
            }
        }
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "eshop.db";
        private static final int DATABASE_VERSION = 1;

        private static DatabaseHelper sInstance;

        public static synchronized DatabaseHelper getInstance(Context context) {
            // Use the application context, which will ensure that you
            // don't accidentally leak an Activity's context.
            // See this article for more information: http://bit.ly/6LRzfx
            if (sInstance == null) {
                sInstance = new DatabaseHelper(context);
            }
            return sInstance;
        }

        /**
         * Constructor should be private to prevent direct instantiation.
         * Make a call to the static method "getInstance()" instead.
         */
        private DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + Suggestions.TABLE_NAME_SUGGESTIONS
                    + " (" + Suggestions.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Suggestions.SEARCH_TITLE + " TEXT NULL,"
                    + Suggestions.SEARCH_VALUE + " TEXT NULL,"
//                    + Suggestions.SEARCH_ID + " TEXT NULL,"
//                    + Suggestions.SEARCH_ACTION + " TEXT NULL,"
//                    + Suggestions.SEARCH_REFINE_VALUE + " TEXT NULL,"
//                    + Suggestions.SEARCH_WID + " TEXT NULL,"
//                    + Suggestions.SEARCH_ORDER_BY_NAME + " TEXT NULL,"
                    + Suggestions.SEARCH_TIME + " TEXT NULL);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(" DROP TABLE IF EXISTS " + Suggestions.TABLE_NAME_SUGGESTIONS);
            onCreate(db);
        }

    }
}
