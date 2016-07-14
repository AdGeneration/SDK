package jp.supership.nativesample.helper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.socdm.d.adgeneration.nativead.ADGNativeAd;

import jp.supership.nativesample.utilities.Utilities;


public class ADGNativeAdHelper {

    private static final int LMP = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final int LWC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private static final int VGLMP = ViewGroup.LayoutParams.MATCH_PARENT;
    private static final int VGLWC = ViewGroup.LayoutParams.WRAP_CONTENT;

    public static void setIcon(ADGNativeAd nativeAd, LinearLayout layout, Context context) {
        if (ADGNativeAdHelper.hasIconImage(nativeAd)) {
            ImageView adIconView = new ImageView(context);
            adIconView.setLayoutParams(new ViewGroup.LayoutParams(Utilities.convertedPixelToDp(context, 30), VGLWC));
            String adIconImageUrl = nativeAd.getIconImage().getUrl();

            // adCoverViewにimageUrlの画像をロードする処理を記述して下さい。

            layout.addView(adIconView);
        }
    }

    public static void setTitle(ADGNativeAd nativeAd, LinearLayout layout, Context context) {
        if (ADGNativeAdHelper.hasTitle(nativeAd)) {
            TextView titleView = new TextView(context);
            titleView.setTextColor(Color.BLACK);
            titleView.setTextSize(9);
            titleView.setLayoutParams(new ViewGroup.LayoutParams(LMP, LWC));
            titleView.setPadding(0, 0, 0, Utilities.convertedPixelToDp(context, 3));
            titleView.setTypeface(Typeface.DEFAULT_BOLD);
            String title = nativeAd.getTitle().getText();
            Utilities.setOneLineAndEllipsisForTextView(titleView);
            titleView.setText(title);
            layout.addView(titleView);
        }
    }

    public static void setDescription(ADGNativeAd nativeAd, LinearLayout layout, Context context) {
        if (ADGNativeAdHelper.hasDesc(nativeAd)) {
            TextView descView = new TextView(context);
            descView.setLayoutParams(new LinearLayout.LayoutParams(LMP, Utilities.convertedPixelToDp(context, 24)));
            descView.setTextSize(8);
            descView.setTextColor(Color.rgb(218, 218, 218));
            descView.setPadding(0, Utilities.convertedPixelToDp(context, 6), 0, Utilities.convertedPixelToDp(context, 6));
            layout.addView(descView);
            Utilities.setOneLineAndEllipsisForTextView(descView);
            String desc = nativeAd.getDesc().getValue();
            descView.setText(desc);
        }
    }

    public static void setAdMark(LinearLayout layout, Context context) {
        TextView prTextView = new TextView(context);
        prTextView.setText("広告");
        prTextView.setTextColor(Color.rgb(218, 218, 218));
        prTextView.setTextSize(9);
        prTextView.setLayoutParams(new ViewGroup.LayoutParams(LWC, LWC));
        layout.addView(prTextView);
    }


    public static void setMainImage(ADGNativeAd nativeAd, LinearLayout layout, Context context) {
        if (ADGNativeAdHelper.hasMainImage(nativeAd)) {
            ImageView adCoverView = new ImageView(context);
            adCoverView.setLayoutParams(new ViewGroup.LayoutParams(VGLMP, VGLMP));
            adCoverView.setAdjustViewBounds(true);
            adCoverView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            String adCoverImageUrl = nativeAd.getMainImage().getUrl();

            // adCoverViewにimageUrlの画像をロードする処理を記述して下さい。

            layout.addView(adCoverView);
        }
    }

    public static void setCta(ADGNativeAd nativeAd, LinearLayout layout, Context context) {

        if (ADGNativeAdHelper.hasCtaText(nativeAd)) {
            LinearLayout nativeAdButtonArea = new LinearLayout(context);
            nativeAdButtonArea.setLayoutParams(new LinearLayout.LayoutParams(LMP, LMP));
            nativeAdButtonArea.setGravity(Gravity.CENTER);

            TextView nativeAdButton = new TextView(context);
            LinearLayout.LayoutParams nativeAdButtonParam = new LinearLayout.LayoutParams(Utilities.convertedPixelToDp(context, 130), Utilities.convertedPixelToDp(context, 25));
            nativeAdButton.setLayoutParams(nativeAdButtonParam);
            nativeAdButton.setTextColor(Color.rgb(11, 144, 255));
            nativeAdButton.setTextSize(12);
            nativeAdButton.setBackgroundColor(Color.WHITE);
            nativeAdButton.setGravity(Gravity.CENTER);

            //borderや角丸をセット
            GradientDrawable borders = new GradientDrawable();
            borders.setColor(Color.WHITE);
            borders.setCornerRadius(10);
            borders.setStroke(3, Color.rgb(11, 144, 255));
            //setBackgroundDrawableはDeprecatedですが、古いバージョンの端末サポートのため使用しています
            nativeAdButton.setBackgroundDrawable(borders);
            nativeAdButton.setText(nativeAd.getCtatext().getValue());
            Utilities.setOneLineAndEllipsisForTextView(nativeAdButton);
            nativeAd.setClickEvent(nativeAdButton);//ボタンへのタップ設定

            nativeAdButtonArea.addView(nativeAdButton);
            layout.addView(nativeAdButtonArea);
        }
    }

    public static void setSponsored(ADGNativeAd nativeAd, LinearLayout layout, Context context) {
        TextView prTextView = new TextView(context);
        prTextView.setLayoutParams(new LinearLayout.LayoutParams(Utilities.convertedPixelToDp(context, 150), Utilities.convertedPixelToDp(context, 14)));
        prTextView.setTextSize(8);
        prTextView.setTextColor(Color.rgb(218, 218, 218));
        prTextView.setText(ADGNativeAdHelper.getSponsored(nativeAd));
        Utilities.setOneLineAndEllipsisForTextView(prTextView);
        layout.addView(prTextView);
    }

    /**
     * sponsoredがあれば返す・無ければsponsoredのみ
     *
     * @param nativeAd
     * @return
     */
    private static String getSponsored(ADGNativeAd nativeAd) {
        if (nativeAd.getSponsored() != null && nativeAd.getSponsored().getValue() != null) {
            return "sponsored by " + nativeAd.getSponsored().getValue();
        }
        return "sponsored";
    }


    private static Boolean hasMainImage(ADGNativeAd nativeAd) {
        if (nativeAd.getMainImage() == null) return false;
        else if (nativeAd.getMainImage().getUrl() == null) return false;
        return true;
    }

    private static Boolean hasIconImage(ADGNativeAd nativeAd) {
        if (nativeAd.getIconImage() == null) return false;
        else if (nativeAd.getIconImage().getUrl() == null) return false;
        return true;
    }

    private static Boolean hasTitle(ADGNativeAd nativeAd) {
        if (nativeAd.getTitle() == null) return false;
        else if (nativeAd.getTitle().getText() == null) return false;
        return true;
    }

    private static Boolean hasDesc(ADGNativeAd nativeAd) {
        if (nativeAd.getDesc() == null) return false;
        else if (nativeAd.getDesc().getValue() == null) return false;
        return true;
    }

    private static Boolean hasCtaText(ADGNativeAd nativeAd) {
        if (nativeAd.getCtatext() == null) return false;
        else if (nativeAd.getCtatext().getValue() == null) return false;
        return true;
    }


}
