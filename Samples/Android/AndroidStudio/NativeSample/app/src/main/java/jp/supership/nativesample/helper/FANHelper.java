package jp.supership.nativesample.helper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.AdChoicesView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;

import jp.supership.nativesample.utilities.Utilities;

public class FANHelper {

    private static final int LMP = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final int LWC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private static final int VGLMP = ViewGroup.LayoutParams.MATCH_PARENT;
    private static final int VGLWC = ViewGroup.LayoutParams.WRAP_CONTENT;

    public static void setIcon(NativeAd nativeAd, LinearLayout layout, Context context) {
        ImageView nativeIcon = new ImageView(context);
        ViewGroup.LayoutParams nativeIconLayoutParams = new LinearLayout.LayoutParams(Utilities.convertedPixelToDp(context, 30), VGLWC);
        NativeAd.Image icon = nativeAd.getAdIcon();
        NativeAd.downloadAndDisplayImage(icon, nativeIcon);
        layout.addView(nativeIcon, nativeIconLayoutParams);
    }

    public static void setTitle(NativeAd nativeAd, LinearLayout layout, Context context) {
        TextView titleView = new TextView(context);
        titleView.setTextColor(Color.BLACK);
        titleView.setTextSize(10);
        titleView.setLayoutParams(new LinearLayout.LayoutParams(LMP, LWC));
        titleView.setPadding(0, 0, 0, Utilities.convertedPixelToDp(context, 3));
        titleView.setTypeface(Typeface.DEFAULT_BOLD);
        Utilities.setOneLineAndEllipsisForTextView(titleView);
        String title = nativeAd.getAdTitle();
        titleView.setText(title);
        layout.addView(titleView);
    }

    public static void setDescription(NativeAd nativeAd, LinearLayout layout, Context context) {
        TextView nativeAdBody = new TextView(context);
        nativeAdBody.setLayoutParams(new LinearLayout.LayoutParams(LMP, Utilities.convertedPixelToDp(context, 24)));
        nativeAdBody.setTextSize(8);
        nativeAdBody.setTextColor(Color.rgb(218, 218, 218));
        nativeAdBody.setPadding(0, Utilities.convertedPixelToDp(context, 6), 0, Utilities.convertedPixelToDp(context, 6));
        Utilities.setOneLineAndEllipsisForTextView(nativeAdBody);
        layout.addView(nativeAdBody);
        String description = nativeAd.getAdBody();
        nativeAdBody.setText(description);

    }

    public static void setAdMark(LinearLayout layout, Context context) {
        TextView prTextView = new TextView(context);
        prTextView.setText("広告");
        prTextView.setTextColor(Color.rgb(218, 218, 218));
        prTextView.setTextSize(9);
        prTextView.setLayoutParams(new ViewGroup.LayoutParams(LWC, LWC));
        layout.addView(prTextView);
    }

    public static void setMediaView(NativeAd nativeAd, FrameLayout layout, Context context) {
        MediaView mediaView = new MediaView(context);
        // 動画/静止画兼用のとき
        mediaView.setLayoutParams(new ViewGroup.LayoutParams(VGLMP, VGLMP));
        mediaView.setNativeAd(nativeAd);
        layout.addView(mediaView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utilities.convertedPixelToDp(context, 157)));
    }

    public static void setCta(NativeAd nativeAd, LinearLayout layout, Context context) {
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
        Utilities.setOneLineAndEllipsisForTextView(nativeAdButton);
        nativeAdButton.setText(nativeAd.getAdCallToAction());
        nativeAdButtonArea.addView(nativeAdButton);
        layout.addView(nativeAdButtonArea);
    }

    public static void setNativeSocialContext(NativeAd nativeAd, LinearLayout layout, Context context) {
        TextView nativeSocialContext = new TextView(context);
        nativeSocialContext.setTextColor(Color.rgb(218, 218, 218));
        nativeSocialContext.setWidth(Utilities.convertedPixelToDp(context, 130));
        nativeSocialContext.setTextSize(8);
        Utilities.setOneLineAndEllipsisForTextView(nativeSocialContext);
        //〇〇人が利用中ですorドメインが表示される
        String socialText = nativeAd.getAdSocialContext() != null ? nativeAd.getAdSocialContext() : "";
        nativeSocialContext.setText(socialText);
        layout.addView(nativeSocialContext);
    }

    public static void setAdChoicesView(NativeAd nativeAd, FrameLayout innerLayout, LinearLayout adChoiceContainer, Context context) {
        AdChoicesView adChoicesView = new AdChoicesView(context, nativeAd, true);
        adChoiceContainer.addView(adChoicesView);
        nativeAd.registerViewForInteraction(innerLayout);
    }

}
