package com.itheima.yqzmobliesafe.activity.utils;

import java.io.File;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;

/**
 * 
 * @author Mryang
 * 
 */
public class packinfoutils {
	// 版本名字
	public static String getversionname(Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo packinfo = pm.getPackageInfo(context.getPackageName(),
					0);
			return packinfo.versionName;
		} catch (NameNotFoundException e) {

			e.printStackTrace();
		}
		return null;
	}

	// 版本号
	public static int getversioncode(Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo packageinfo = pm.getPackageInfo(
					context.getPackageName(), 0);
			return packageinfo.versionCode;
		} catch (NameNotFoundException e) {

			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 获取应用程序的名称
	 * 
	 * @param context shangxiawen
	 * @param info  baoxinxi
	 * @return 
	 */
	public static String getAppName(Context context, PackageInfo info) {
		PackageManager pm = context.getPackageManager();
		ApplicationInfo applicationInfo = info.applicationInfo;
		return applicationInfo.loadLabel(pm).toString();
	}

	// 获取应用程序的icon
	public static Drawable getAppIcon(Context context, PackageInfo info) {
		PackageManager pm = context.getPackageManager();
		ApplicationInfo applicationInfo = info.applicationInfo;
		return applicationInfo.loadIcon(pm);
	}

	// 获取应用程序的apk大小
	public static long getAppSpace(PackageInfo info) {
		ApplicationInfo applicationInfo = info.applicationInfo;
		String sourceDir = applicationInfo.sourceDir;
		File file = new File(sourceDir);
		return file.length();
	}

	// 判断当前应用是否安装在sd卡里
	public static boolean isInstallSd(PackageInfo info) {
		ApplicationInfo applicationInfo = info.applicationInfo;
		int flags = applicationInfo.flags;
		// 利用标记的能力，用与算法来判断是否安装
		boolean isInsallSd = false;
		if ((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) == ApplicationInfo.FLAG_EXTERNAL_STORAGE) {
			isInsallSd = true;
		}
		return isInsallSd;
	}

	// 判断当前应用是否为系统的应用
	public static boolean isSystem(PackageInfo info) {
		ApplicationInfo applicationInfo = info.applicationInfo;
		int flags = applicationInfo.flags;
		boolean isSystemApp = false;
		if ((flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) {
			isSystemApp = true;
		}
		return isSystemApp;
	}
}
