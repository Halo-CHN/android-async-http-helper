package com.credithc.gedaiacquisition.http;

import java.io.File;
import java.util.Map;

import org.apache.http.Header;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 
 *
 * @description 对 android-async-http 进行封装的类
 *
 * @author Halo-CHN
 *
 * @mail halo-chn@outlook.com
 *
 * @date 2015年1月16日
 *
 * @version 1.0
 *
 */
public class AndroidAsyncHttpHelper {

	private static AndroidAsyncHttpHelper instance;

	private AndroidAsyncHttpHelper() {
	}

	public static AndroidAsyncHttpHelper getInstance() {
		if (null == instance) {
			synchronized (AndroidAsyncHttpHelper.class) {
				if (null == instance) {
					instance = new AndroidAsyncHttpHelper();
				}
			}
		}
		return instance;
	}

	/**
	 * 定义一个异步网络客户端 默认超时未20秒 当超过，默认重连次数为5次 默认最大连接数为10个 　　
	 */
	private static AsyncHttpClient client = new AsyncHttpClient();

	static {
		client.setTimeout(20000);
	}

	/**
	 * HTTP GET METHODs -- 存在异常或者请求超时情况下，回调返回值将是空字符串
	 * 
	 * @param uri
	 *            请求的url
	 * @param callback
	 *            请求完成后回调的方法
	 */
	public void get(Context context, String url,
			final AsyncHttpResponseHandler callback) {
		httpRequest(context, url, null, callback, EHttpMethod.GET);
	}

	public void get(Context context, String url, Map<String, Object> params,
			final AsyncHttpResponseHandler callback) {
		httpRequest(context, url, params, callback, EHttpMethod.GET);
	}

	/**
	 * HTTP POST METHODs -- 存在异常或者请求超时情况下，回调返回值将是空字符串
	 * 
	 * @param uri
	 *            请求的url
	 * @param callback
	 *            请求完成后回调的方法
	 */
	public void post(Context context, String url,
			final AsyncHttpResponseHandler callback) {
		httpRequest(context, url, null, callback, EHttpMethod.POST);
	}

	public void post(Context context, String url, Map<String, Object> params,
			final AsyncHttpResponseHandler callback) {
		httpRequest(context, url, params, callback, EHttpMethod.POST);
	}

	/**
	 * http请求
	 * 
	 * @param context
	 *            传入调用的页面
	 * @param url
	 *            请求的url
	 * @param params
	 *            传入的参数
	 * @param callback
	 *            请求完成后回调的方法
	 * @param method
	 *            POST or GET
	 */
	private void httpRequest(Context context, String url,
			Map<String, Object> params,
			final AsyncHttpResponseHandler callback, EHttpMethod method) {
		/* 判断网络状态 */
		if (!InternetUtil.isNetWorking(context)) {
			String str = "无网络";
			callback.sendFailureMessage(40, null, str.getBytes(), null);
			return;
		}
		/* 得到请求参数 */
		RequestParams requestParams = new RequestParams();
		if (null != params && params.size() > 0) {
			for (String key : params.keySet()) {
				requestParams.put(key, params.get(key));
			}
		}

		switch (method) {
		case GET:
			client.get(context, url, requestParams, callback);
			break;

		case POST:
			client.post(context, url, requestParams, callback);
			break;

		default:
			break;
		}
	}

	/**
	 * @param path
	 *            要上传的文件路径
	 * @param url
	 *            服务端接收URL
	 * @throws Exception
	 */
	public static void uploadFile(String path, String url) throws Exception {
		File file = new File(path);
		if (file.exists() && file.length() > 0) {
			AsyncHttpClient client = new AsyncHttpClient();
			RequestParams params = new RequestParams();
			params.put("uploadfile", file);
			// 上传文件
			client.post(url, params, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(int statusCode, Header[] headers,
						byte[] responseBody) {
					// 上传成功后要做的工作
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,
						byte[] responseBody, Throwable error) {
				}

				@Override
				public void onProgress(int bytesWritten, int totalSize) {
					super.onProgress(bytesWritten, totalSize);
					// int count = (int) ((bytesWritten * 1.0 / totalSize) *
					// 100);
					// 上传进度显示
				}

				@Override
				public void onRetry(int retryNo) {
					super.onRetry(retryNo);
					// 返回重试次数
				}

			});
		} else {
		}

	}

	/**
	 *
	 * @description 请求类型的枚举
	 *
	 * @author Wiken
	 *
	 * @mail root_wiken@163.com
	 *
	 * @date 2015年1月6日
	 *
	 * @version 1.0
	 *
	 */
	public enum EHttpMethod {
		GET, POST
	}

	public enum EResponseHandlerType {
		Async, Text, Json, BaseJson
	}
}
