package org.mousebomb.ane.miad;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;

/**
 * Created by rhett on 16/5/14.
 */
public class MiAdSetKeysFunction extends BaseFunction {


    private static final String TAG = "MiAdSetKeysFunction";

    @Override
    public FREObject call(FREContext context, FREObject[] args) {
        super.call(context, args);
        String appID;
        String splashID;
        String interstitialID;
        try {
            appID = args[0].getAsString();
            splashID = args[1].getAsString();
            interstitialID = args[2].getAsString();
            MiAdExtension.context.setKeys(appID,splashID,interstitialID);
            return FREObject.newObject(true);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
