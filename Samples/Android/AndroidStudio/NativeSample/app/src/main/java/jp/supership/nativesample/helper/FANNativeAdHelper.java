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

public class FANNativeAdHelper {

    /**
     * ネイティブ広告を作成します
     *
     * @param nativeAd com.facebook.ads.NativeAd
     * @param context  Context
     * @return ad
     */
    public static FrameLayout createAdView(NativeAd nativeAd, Context context) {
        // 広告枠の設定
        FrameLayout layout = new FrameLayout(context);
        layout.setLayoutParams(new ViewGroup.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        layout.setBackgroundColor(Color.WHITE);

        FrameLayout nativeAdContainer = new FrameLayout(context);
        nativeAdContainer.setLayoutParams(new FrameLayout.LayoutParams(
                Utilities.convertDpToPixel(context, 300),
                Utilities.convertDpToPixel(context, 250)));
        layout.addView(nativeAdContainer);

        // 広告枠のレイアウト
        LinearLayout nativeAdFrame = new LinearLayout(context);
        nativeAdFrame.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        nativeAdFrame.setOrientation(LinearLayout.VERTICAL);
        nativeAdContainer.addView(nativeAdFrame);

        // Headerの設定
        LinearLayout headerWrapper = new LinearLayout(context);
        headerWrapper.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                Utilities.convertDpToPixel(context, 30)));
        headerWrapper.setOrientation(LinearLayout.HORIZONTAL);
        headerWrapper.setGravity(Gravity.LEFT);
        nativeAdFrame.addView(headerWrapper);

        // アイコンの設定
        ImageView nativeIcon = new ImageView(context);
        NativeAd.Image icon = nativeAd.getAdIcon();
        NativeAd.downloadAndDisplayImage(icon, nativeIcon);
        headerWrapper.addView(nativeIcon, new LinearLayout.LayoutParams(
                Utilities.convertDpToPixel(context, 30), ViewGroup.LayoutParams.WRAP_CONTENT));

        // タイトルと広告マークを縦に並べる
        LinearLayout titlesWrapper = new LinearLayout(context);
        titlesWrapper.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        titlesWrapper.setOrientation(LinearLayout.VERTICAL);
        titlesWrapper.setPadding(Utilities.convertDpToPixel(context, 5), 0, 0, 0);
        headerWrapper.addView(titlesWrapper);

        // タイトルの設定
        LinearLayout nativeAdTitle = new LinearLayout(context);
        nativeAdTitle.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                Utilities.convertDpToPixel(context, 17)));
        TextView titleView = new TextView(context);
        titleView.setTextColor(Color.BLACK);
        titleView.setTextSize(10);
        titleView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        titleView.setPadding(0, 0, 0, Utilities.convertDpToPixel(context, 3));
        titleView.setTypeface(Typeface.DEFAULT_BOLD);
        Utilities.setOneLineAndEllipsisForTextView(titleView);
        String title = nativeAd.getAdTitle();
        titleView.setText(title);
        nativeAdTitle.addView(titleView);
        titlesWrapper.addView(nativeAdTitle);

        // 広告マークの設定
        TextView prTextView = new TextView(context);
        prTextView.setText("広告");
        prTextView.setTextColor(Color.rgb(218, 218, 218));
        prTextView.setTextSize(9);
        prTextView.setLayoutParams(new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        titlesWrapper.addView(prTextView);

        // 本文をセット
        TextView nativeAdBody = new TextView(context);
        nativeAdBody.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                Utilities.convertDpToPixel(context, 24)));
        nativeAdBody.setTextSize(8);
        nativeAdBody.setTextColor(Color.rgb(218, 218, 218));
        nativeAdBody.setPadding(
                0,
                Utilities.convertDpToPixel(context, 6),
                0,
                Utilities.convertDpToPixel(context, 6));
        Utilities.setOneLineAndEllipsisForTextView(nativeAdBody);
        nativeAdFrame.addView(nativeAdBody);
        String description = nativeAd.getAdBody();
        nativeAdBody.setText(description);

        // 画像または動画のViewを設定
        FrameLayout nativeAdImage = new FrameLayout(context);
        nativeAdImage.setLayoutParams(new FrameLayout.LayoutParams(
                Utilities.convertDpToPixel(context, 300),
                Utilities.convertDpToPixel(context, 156)));
        nativeAdFrame.addView(nativeAdImage);
        MediaView mediaView = new MediaView(context);
        // 動画/静止画兼用のとき
        mediaView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mediaView.setNativeAd(nativeAd);
        nativeAdImage.addView(mediaView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                Utilities.convertDpToPixel(context, 157)));

        // Bottomの設定
        LinearLayout bottomWrapper = new LinearLayout(context);
        bottomWrapper.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        bottomWrapper.setOrientation(LinearLayout.HORIZONTAL);
        nativeAdFrame.addView(bottomWrapper);

        LinearLayout bottomLeftWrapper = new LinearLayout(context);
        bottomLeftWrapper.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f));
        bottomLeftWrapper.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        bottomWrapper.addView(bottomLeftWrapper);

        LinearLayout bottomRightWrapper = new LinearLayout(context);
        bottomRightWrapper.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f));
        bottomRightWrapper.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        bottomWrapper.addView(bottomRightWrapper);

        // ソーシャルコンテキストの設定
        TextView nativeSocialContext = new TextView(context);
        nativeSocialContext.setTextColor(Color.rgb(218, 218, 218));
        nativeSocialContext.setWidth(Utilities.convertDpToPixel(context, 130));
        nativeSocialContext.setTextSize(8);
        Utilities.setOneLineAndEllipsisForTextView(nativeSocialContext);
        //〇〇人が利用中ですorドメインが表示される
        nativeSocialContext.setText(
                nativeAd.getAdSocialContext() != null ? nativeAd.getAdSocialContext() : "");
        bottomLeftWrapper.addView(nativeSocialContext);

        // ボタンの設定
        LinearLayout nativeAdButtonArea = new LinearLayout(context);
        nativeAdButtonArea.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        nativeAdButtonArea.setGravity(Gravity.CENTER);

        TextView nativeAdButton = new TextView(context);
        nativeAdButton.setLayoutParams(new LinearLayout.LayoutParams(
                Utilities.convertDpToPixel(context, 130),
                Utilities.convertDpToPixel(context, 25)));
        nativeAdButton.setTextColor(Color.rgb(11, 144, 255));
        nativeAdButton.setTextSize(12);
        nativeAdButton.setBackgroundColor(Color.WHITE);
        nativeAdButton.setGravity(Gravity.CENTER);

        // ボタンにborderや角丸を設定
        GradientDrawable borders = new GradientDrawable();
        borders.setColor(Color.WHITE);
        borders.setCornerRadius(10);
        borders.setStroke(3, Color.rgb(11, 144, 255));
        // setBackgroundDrawableはDeprecatedですが、古いバージョンの端末サポートのため使用しています
        nativeAdButton.setBackgroundDrawable(borders);
        Utilities.setOneLineAndEllipsisForTextView(nativeAdButton);
        nativeAdButton.setText(nativeAd.getAdCallToAction());
        nativeAdButtonArea.addView(nativeAdButton);
        bottomRightWrapper.addView(nativeAdButtonArea);

        // AdChoiceの設定
        LinearLayout adChoiceContainer = new LinearLayout(context);
        adChoiceContainer.setGravity(Gravity.RIGHT | Gravity.TOP);
        nativeAdImage.addView(adChoiceContainer, new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        AdChoicesView adChoicesView = new AdChoicesView(context, nativeAd, true);
        adChoiceContainer.addView(adChoicesView);
        nativeAd.registerViewForInteraction(nativeAdImage);

        return layout;
    }
}
