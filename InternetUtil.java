package com.credithc.gedaiacquisition.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * 
 *
 * @description 检查网络操作类
 *
 * @author Halo-CHN
 *
 * @mail halo-chn@outlook.com
 *
 * @date 2015年1月6日
 *
 * @version 1.0
 *
 */
public class InternetUtil {
	public static boolean isNetWorking(final Context context) {
		boolean flag = checkNet(context);
		if (!flag) {
			// AlertDialog.Builder builders = new AlertDialog.Builder(context);
			// builders.setTitle("连接网络失败,是否进行网络设置？");
			// builders.setPositiveButton("设置",
			// new DialogInterface.OnClickListener() {
			// public void onClick(DialogInterface dialog, int which) {
			// // 进入无线网络配置界面
			// context.startActivity(new Intent(
			// Settings.ACTION_WIRELESS_SETTINGS));
			// // startActivity(new
			// // Intent(Settings.ACTION_WIFI_SETTINGS));
			// // //进入手机中的wifi网络设置界面
			// }
			// });
			// builders.setNegativeButton("稍后",
			// new DialogInterface.OnClickListener() {
			// public void onClick(DialogInterface dialog,
			// int whichButton) {
			// // 关闭当前activity
			// // mActivity.finish();
			// }
			// });
			// builders.show();
			Toast.makeText(context, "应用当前处于离线操作状态", Toast.LENGTH_SHORT).show();
		}
		return flag;
	}

	/**
	 * 
	 * @param context
	 * @return 判断是否有网络连接
	 */
	private static boolean checkNet(Context context) {

		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 
	 * @param context
	 * @return 判断WIFI网络是否可用
	 */
	public boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 
	 * @param context
	 * @return 判断MOBILE网络是否可用
	 */
	public boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 
	 * @param context
	 * @return 获取当前网络连接的类型信息
	 */
	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}
}
