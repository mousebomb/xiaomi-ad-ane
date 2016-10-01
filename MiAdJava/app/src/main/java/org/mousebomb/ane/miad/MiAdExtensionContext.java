//////////////////////////////////////////////////////////////////////////////////////
//
//  Copyright 2012 Freshplanet (http://freshplanet.com | opensource@freshplanet.com)
//  
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//  
//    http://www.apache.org/licenses/LICENSE-2.0
//  
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//  
//////////////////////////////////////////////////////////////////////////////////////

package org.mousebomb.ane.miad;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.xiaomi.ad.AdListener;
import com.xiaomi.ad.Interstitial.InterstitialAd;
import com.xiaomi.ad.common.pojo.AdError;
import com.xiaomi.ad.common.pojo.AdEvent;

import java.util.HashMap;
import java.util.Map;

public class MiAdExtensionContext extends FREContext
{
	private static final String TAG ="MiAdExtensionContext" ;

	public MiAdExtensionContext() {
		super();
	}

	@Override
	public void dispose()
	{
		MiAdExtension.context = null;
	}

	@Override
	public Map<String, FREFunction> getFunctions()
	{
		Map<String, FREFunction> functions = new HashMap<String, FREFunction>();
		
		functions.put("setKeys", new MiAdSetKeysFunction());
		functions.put("showInterstitial", new MiAdShowInterstitialFunction());
		functions.put("cacheInterstitial", new MiAdCacheInterstitialFunction());
		functions.put("setDebugMode", new MiAdSetDebugModeFunction());

		return functions;
	}

	// DEBUGMODE
	private Boolean isDebugMode =false;
	public void setDebugMode(Boolean b)
	{
		isDebugMode = b;
	}


	// CONFIG
	public void setKeys(String appID,String splashID,String interstitialID)
	{
		cAppID = appID;
		cSplashID=splashID;
		cInterstitialID = interstitialID;
		//第一次 显示开屏
		showSplashAd();
	}
	public static String cAppID;
	public static String cSplashID;
	public static String cInterstitialID;


	//开屏
	public void showSplashAd()
	{
		if(isSplashShown) return;
		Intent intent = new Intent(getActivity(), SplashActivity.class);
		getActivity().startActivity(intent);
		isSplashShown = true;
	}
	private Boolean isSplashShown = false;


	public void cacheInterstitial()
	{
		if(mInterstitialAd == null)
		{
			mInterstitialAd = new InterstitialAd(getActivity().getApplicationContext(), getActivity().getWindow().getDecorView());
		}
		if(mInterstitialAd.isReady()) return;
		//
		mInterstitialAd.requestAd(cInterstitialID, new AdListener() {
			@Override
			public void onAdError(AdError adError) {
                logD("onAdError : " + adError.toString());
			}

			@Override
			public void onAdEvent(AdEvent adEvent) {
				try {
					switch (adEvent.mType) {
						case AdEvent.TYPE_SKIP:
							//用户关闭了广告
							logD("ad skip!");
							break;
						case AdEvent.TYPE_CLICK:
							//用户点击了广告
							logD("ad click!");
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onAdLoaded() {
			}

			@Override
			public void onViewCreated(View view) {
				logD("ad is ready : " + mInterstitialAd.isReady());

			}
		});
		logD("加载中");
	}
	public void showInterstitial()
	{
		if (mInterstitialAd!=null && mInterstitialAd.isReady()) {
			mInterstitialAd.show();
		} else {
			cacheInterstitial();
		}
	}
	private InterstitialAd mInterstitialAd;


    private void logD( String txt )
    {
        if(isDebugMode)
            Toast.makeText(getActivity().getApplicationContext(), txt, Toast.LENGTH_LONG).show();
        Log.e(TAG, txt);
    }

}
