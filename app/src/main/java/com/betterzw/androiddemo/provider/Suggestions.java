package com.betterzw.androiddemo.provider;

import android.net.Uri;

/**
 * Please specify the function of this class
 *
 * @author zhengwu
 * @date 2015/8/14
 */
public class Suggestions {

    public static final String TABLE_NAME_SUGGESTIONS = "suggestions";

    public static final String AUTHORITY = "com.globalegrow.app.gearbest.mycontentprovier";

    public static final String URI = "content://" + AUTHORITY + "/suggestions";

    public static final Uri CONTENT_URI = Uri.parse(URI);

    public static final String ID = "_id"; // 唯一标识主键
    public static final String SEARCH_TITLE = "search_title";
    public static final String SEARCH_VALUE = "search_value";
    public static final String SEARCH_TIME = "search_time";
//    public static final String SEARCH_ID = "search_id";
//    public static final String SEARCH_ACTION = "search_action";
//    public static final String SEARCH_REFINE_VALUE = "search_refine_value";
//    public static final String SEARCH_ORDER_BY_NAME = "search_order_by_name";
//    public static final String SEARCH_WID = "search_wid";

}
