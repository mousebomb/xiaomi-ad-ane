package org.mousebomb.ane.miad;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;

/**
 * Created by rhett on 16/5/14.
 */
public class MiAdShowInterstitialFunction extends BaseFunction {


    private static final String TAG = "MiAdShowInterstitialFunction";

    @Override
    public FREObject call(FREContext context, FREObject[] args) {
        super.call(context, args);
        try {
            MiAdExtension.context.showInterstitial();
            return FREObject.newObject(true);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
