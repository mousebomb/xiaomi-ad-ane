package org.mousebomb.ane.miad;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;

/**
 * Created by rhett on 16/10/2.
 */
public class MiAdIsInterstitialReadyFunction extends BaseFunction {


    private static final String TAG = "MiAdIsInterstitialReadyFunction";

    @Override
    public FREObject call(FREContext context, FREObject[] args) {
        super.call(context, args);
        try {
            return FREObject.newObject(MiAdExtension.context.isInterstitialReady());
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
