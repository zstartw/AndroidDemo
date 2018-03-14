package com.betterzw.androiddemo.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by zhengwu on 3/14/18.
 */

public class PackageUtils {

    public void install(Context context, String filePath){
        String dirPath = "/data/data/" + context.getPackageName() + "/files/test.apk"; //文件需有可读权限
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void uninstall(Context context){
        Uri packageUri = Uri.parse("package:" + context.getPackageName());
        Intent deleteIntent = new Intent();
        deleteIntent.setAction(Intent.ACTION_DELETE);
        deleteIntent.setData(packageUri);
        context.startActivity(deleteIntent);
    }
}
