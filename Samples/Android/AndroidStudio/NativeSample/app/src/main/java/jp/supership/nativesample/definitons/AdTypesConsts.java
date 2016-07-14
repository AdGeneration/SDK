package jp.supership.nativesample.definitons;

import android.content.Context;

import jp.supership.nativesample.utilities.Utilities;

public class AdTypesConsts {

    /**
     * AdTypes
     */
    public static final int RECTANGLE_NATIVEAD = 0;

    /**
     * AdSizes
     */
    public static final int RECTANGLE_NATIVEAD_WIDTH = 300;

    public static final int RECTANGLE_NATIVEAD_HEIGHT = 250;


    public static int getRectangleNativeadWidthWithConvertDp(Context context) {
        return Utilities.convertedPixelToDp(context, RECTANGLE_NATIVEAD_WIDTH);
    }

    public static int getRectangleNativeadHeightWithConvertDp(Context context) {
        return Utilities.convertedPixelToDp(context, RECTANGLE_NATIVEAD_HEIGHT);
    }

}
