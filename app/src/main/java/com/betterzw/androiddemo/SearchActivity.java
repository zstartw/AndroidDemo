package com.betterzw.androiddemo;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.betterzw.androiddemo.model.TestBean;
import com.betterzw.androiddemo.provider.Suggestions;
import com.google.android.flexbox.FlexboxLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhengwu on 8/15/18.
 */
public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, TextWatcher {

    private static final int URL_LOADER = 0;


    @BindView(R.id.search_help_listView)
    ListView searchHelpListView;
    @BindView(R.id.search_hot_listView)
    FlexboxLayout searchHotListView;
    @BindView(R.id.search_hot_container)
    LinearLayout searchHotContainer;
    @BindView(R.id.search_clear_history)
    TextView searchClearHistory;
    @BindView(R.id.search_history_container)
    LinearLayout searchHistoryContainer;
    @BindView(R.id.search_history_listView)
    FlexboxLayout searchHistoryListView;
    @BindView(R.id.search_container)
    NestedScrollView searchContainer;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }


    @Override
    protected void onResume() {
        super.onResume();

       /* Loader<Object> loader = getLoaderManager().getLoader(0);
        if (loader != null && !loader.isReset()) {
            getLoaderManager().restartLoader(0, null, this);
        } else {
            getLoaderManager().initLoader(0, null, this);
        }*/


        TestBean testBean = JSONObject.parseObject("{\n" +
                "\"age\":1231231231231,\n" +
                "\"name\":\"firstname\"\n" +
                "}", TestBean.class);

//        var bean = JSONObject.parseObject<TestBean>("{\"age\":1231231111111111111111121212}", TestBean::class.java)
        Log.d("MainActivity", "==========="+testBean.age);
        Log.d("MainActivity", "==========="+testBean.name);
        Log.d("MainActivity", "==========="+Long.MAX_VALUE);
        Log.d("MainActivity", "==========="+Integer.MAX_VALUE);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case URL_LOADER:
                return new CursorLoader(this,   // Parent activity mContext
                        Suggestions.CONTENT_URI,        // Table to query
                        null,     // Projection to return
                        null,            // No selection clause
                        null,            // No selection arguments
                        Suggestions.SEARCH_TIME + " DESC" + " LIMIT 10" // Default sort order and limit
                );
            default:// An invalid id was passed in
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
