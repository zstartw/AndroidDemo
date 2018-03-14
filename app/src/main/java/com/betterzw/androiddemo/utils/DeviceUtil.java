package com.betterzw.androiddemo.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

import static android.os.Environment.isExternalStorageRemovable;

/**
 * Created by zhengwu on 3/14/18.
 */

public class DeviceUtil {
    private static final String TAG = DeviceUtil.class.getSimpleName();

    public static boolean checkForLocationServices(Context paramContext) {
        return (paramContext.getPackageManager().hasSystemFeature("android.hardware.location")) || ((!isKindleFireGen1()) && (isKindle()));
    }

    public static boolean checkForPermission(String paramString, Context paramContext) {
        return paramContext.checkCallingOrSelfPermission(paramString) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isHoneycomb() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public static boolean isIcs() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static boolean isJB() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static boolean isKindle() {
        return "Amazon".equals(Build.MANUFACTURER);
    }

    public static boolean isKindleFireGen1() {
        return (isKindle()) && (("Kindle Fire".equals(Build.MODEL)) || ("KFOT".equals(Build.MODEL)));
    }

    public static boolean isKitKat() {
        return Build.VERSION.SDK_INT >= 19;
    }


    /**
     * Check how much usable space is available at a given path.
     *
     * @param path The path to check
     * @return The space available in bytes
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static long getUsableSpace(File path) {
        /*if (Utils.hasGingerbread()) {
            return path.getUsableSpace();
        }*/
        final StatFs stats = new StatFs(path.getPath());
        return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
    }


    /**
     * Get a usable cache directory (external if available, internal otherwise).
     *
     * @param context The context to use
     * @param uniqueName A unique directory name to append to the cache dir
     * @return The cache dir
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
        // otherwise use internal cache dir
        final String cachePath =
                Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                        !isExternalStorageRemovable() ? getExternalCacheDir(context).getPath() :
                        context.getCacheDir().getPath();

        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * Get the external app cache directory.
     *
     * @param context The context to use
     * @return The external cache dir
     */
    @TargetApi(Build.VERSION_CODES.FROYO)
    public static File getExternalCacheDir(Context context) {
       /* if (Utils.hasFroyo()) {
            return context.getExternalCacheDir();
        }*/

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }
}
