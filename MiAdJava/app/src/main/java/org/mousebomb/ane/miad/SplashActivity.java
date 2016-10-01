package org.mousebomb.ane.miad;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import com.xiaomi.ad.SplashAd;
import com.xiaomi.ad.SplashAdListener;

/**
 * Created by rhett on 16/10/1.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//SPLASH
        setContentView(R.layout.splashad);
        ViewGroup container = (ViewGroup) findViewById(R.id.splash_ad_container);
        SplashAd splashAd = new SplashAd(this, container, new SplashAdListener() {
            @Override
            public void onAdPresent() {
                // 开屏广告展示
                Log.d(TAG, "onAdPresent");
                mHandler.postDelayed(mRemoveDefaultPicture, 500);
            }

            @Override
            public void onAdClick() {
                //用户点击了开屏广告
                Log.d(TAG, "onAdClick");
                gotoNextActivity();
            }

            @Override
            public void onAdDismissed() {
                //这个方法被调用时，表示从开屏广告消失。
                Log.d(TAG, "onAdDismissed");
                Toast.makeText(getApplicationContext(), "onAdDismissed", Toast.LENGTH_LONG).show();
                gotoNextActivity();
            }

            @Override
            public void onAdFailed(String s) {
                Log.d(TAG, "onAdFailed, message: " + s);
                Toast.makeText(getApplicationContext(), "onAdFailed, message: " + s, Toast.LENGTH_LONG).show();
                //这个方法被调用时，表示从服务器端请求开屏广告时，出现错误。
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gotoNextActivity();
                    }
                }, 500);
            }
        });
        splashAd.requestAd(MiAdExtensionContext.cSplashID);
    }


    private Handler mHandler = new Handler(Looper.getMainLooper());

    private static final String TAG = "SplashAdActivity";

    private Runnable mRemoveDefaultPicture = new Runnable() {
        @Override
        public void run() {
            View logo = findViewById(R.id.splash_default_picture);
            AlphaAnimation alpha = new AlphaAnimation(1, 0);
            alpha.setDuration(200);
            logo.startAnimation(alpha);
            logo.setVisibility(View.GONE);
        }
    };

    /**
     * 跳转到Main Activity
     */
    private void gotoNextActivity() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        finish();
    }
}
