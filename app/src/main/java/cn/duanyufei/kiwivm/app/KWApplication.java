package cn.duanyufei.kiwivm.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by DUAN Yufei on 2017/3/13.
 */

public class KWApplication extends Application {

    // Global context;
    private static Context context;

    private static final String TAG = "KWApplication";

    private static final String INTERNAL_VERSION = "Internal Version: ";
    private static final String VERSION = "Version V";
    private static final String SVN = "SVN: ";
    private static KWApplication instance = null;

    private static String mVersion = null;
    private static int mSvnNum = 1;

    public static KWApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
    }

    public String getVersion() {

        if (mVersion == null) {
            try {
                mVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                mVersion = "00.00.00";
            }
        }

        return VERSION + mVersion.substring(0, 8);


    }

    public String getInternalVersion() {
        if (mVersion == null) {
            try {
                mVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                mVersion = "v0.0.0";
            }
        }
        if (mSvnNum == 0) {
            try {
                mSvnNum = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                mSvnNum = 1;
            }
        }

        return INTERNAL_VERSION + mVersion + " " + SVN + mSvnNum;
    }

    public static Context getContext() {
        return context;
    }
}