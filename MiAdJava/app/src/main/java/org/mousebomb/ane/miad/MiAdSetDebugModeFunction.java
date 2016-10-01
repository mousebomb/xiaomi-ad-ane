package org.mousebomb.ane.miad;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;

/**
 * Created by rhett on 16/5/14.
 */
public class MiAdSetDebugModeFunction extends BaseFunction {


    private static final String TAG = "MiAdSetDebugModeFunction";

    @Override
    public FREObject call(FREContext context, FREObject[] args) {
        super.call(context, args);
        Boolean isDebug;
        try {
            isDebug = args[0].getAsBool();
            MiAdExtension.context.setDebugMode(isDebug);
            return FREObject.newObject(true);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
