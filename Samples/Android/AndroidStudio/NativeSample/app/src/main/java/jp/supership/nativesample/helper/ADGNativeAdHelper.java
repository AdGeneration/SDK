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

import com.socdm.d.adgeneration.nativead.ADGInformationIconView;
import com.socdm.d.adgeneration.nativead.ADGNativeAd;

import jp.supership.nativesample.utilities.DownloadImageAsync;
import jp.supership.nativesample.utilities.Utilities;

public class ADGNativeAdHelper {

    /**
     * ネイティブ広告を作成します。
     *
     * @param nativeAd com.socdm.d.adgeneration.nativead.ADGNativeAd
     * @param context  Context
     * @return ad
     */
    public static FrameLayout createAdView(ADGNativeAd nativeAd, Context context) {
        // 広告枠の設定
        FrameLayout layout = new FrameLayout(context);
        layout.setLayoutParams(new ViewGroup.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        layout.setBackgroundColor(Color.WHITE);

        // 広告枠のレイアウト設定
        LinearLayout nativeAdContainer = new LinearLayout(context);
        nativeAdContainer.setLayoutParams(new LinearLayout.LayoutParams(
                Utilities.convertDpToPixel(context, 300),
                Utilities.convertDpToPixel(context, 250)));
        nativeAdContainer.setOrientation(LinearLayout.VERTICAL);
        layout.addView(nativeAdContainer);

        // Headerの設定
        LinearLayout headerWrapper = new LinearLayout(context);
        headerWrapper.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                Utilities.convertDpToPixel(context, 30)));
        headerWrapper.setOrientation(LinearLayout.HORIZONTAL);
        headerWrapper.setGravity(Gravity.LEFT);
        nativeAdContainer.addView(headerWrapper);

        // アイコンイメージの設定
        if (nativeAd.getIconImage() != null) {
            ImageView adIconView = new ImageView(context);
            adIconView.setLayoutParams(new ViewGroup.LayoutParams(
                    Utilities.convertDpToPixel(context, 30),
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            String adIconImageUrl = nativeAd.getIconImage().getUrl();

            // 画像をロードします(方法については任意で行ってください)
            new DownloadImageAsync(adIconView).execute(adIconImageUrl);

            headerWrapper.addView(adIconView);
        }

        // タイトルと広告マークを縦に並べる
        LinearLayout titlesWrapper = new LinearLayout(context);
        titlesWrapper.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        titlesWrapper.setOrientation(LinearLayout.VERTICAL);
        titlesWrapper.setPadding(Utilities.convertDpToPixel(context, 6), 0, 0, 0);
        headerWrapper.addView(titlesWrapper);

        // タイトルの設定
        if (nativeAd.getTitle() != null) {
            LinearLayout nativeAdTitle = new LinearLayout(context);
            nativeAdTitle.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    Utilities.convertDpToPixel(context, 16)));
            TextView titleView = new TextView(context);
            titleView.setTextColor(Color.BLACK);
            titleView.setTextSize(9);
            titleView.setLayoutParams(new ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            titleView.setPadding(0, 0, 0, Utilities.convertDpToPixel(context, 3));
            titleView.setTypeface(Typeface.DEFAULT_BOLD);
            String title = nativeAd.getTitle().getText();
            Utilities.setOneLineAndEllipsisForTextView(titleView);
            titleView.setText(title);
            nativeAdTitle.addView(titleView);
            titlesWrapper.addView(nativeAdTitle);
        }

        // 広告マークの設定
        TextView prTextView = new TextView(context);
        prTextView.setText("広告");
        prTextView.setTextColor(Color.rgb(218, 218, 218));
        prTextView.setTextSize(9);
        prTextView.setLayoutParams(new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        titlesWrapper.addView(prTextView);

        // 本文の設定
        if (nativeAd.getDesc() != null) {
            TextView descView = new TextView(context);
            descView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    Utilities.convertDpToPixel(context, 24)));
            descView.setTextSize(8);
            descView.setTextColor(Color.rgb(218, 218, 218));
            descView.setPadding(0, Utilities.convertDpToPixel(context, 6), 0, Utilities.convertDpToPixel(context, 6));
            nativeAdContainer.addView(descView);
            Utilities.setOneLineAndEllipsisForTextView(descView);
            String desc = nativeAd.getDesc().getValue();
            descView.setText(desc);
        }

        // メインイメージの設定
        FrameLayout nativeAdImage = new FrameLayout(context);
        nativeAdImage.setLayoutParams(new FrameLayout.LayoutParams(
                Utilities.convertDpToPixel(context, 300),
                Utilities.convertDpToPixel(context, 156)));
        nativeAdContainer.addView(nativeAdImage);
        if (nativeAd.getMainImage() != null) {
            ImageView adCoverView = new ImageView(context);
            adCoverView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            adCoverView.setAdjustViewBounds(true);
            adCoverView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            String adCoverImageUrl = nativeAd.getMainImage().getUrl();

            // 画像をロードします(方法については任意で行ってください)
            new DownloadImageAsync(adCoverView).execute(adCoverImageUrl);

            nativeAdImage.addView(adCoverView);
        }

        // インフォメーションアイコンの設定
        ADGInformationIconView infoIcon = new ADGInformationIconView(context, nativeAd);
        nativeAdImage.addView(infoIcon);

        // Bottomの設定
        LinearLayout bottomWrapper = new LinearLayout(context);
        bottomWrapper.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        bottomWrapper.setOrientation(LinearLayout.HORIZONTAL);
        bottomWrapper.setGravity(Gravity.LEFT);
        nativeAdContainer.addView(bottomWrapper);

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

        // スポンサーの設定
        TextView spTextView = new TextView(context);
        spTextView.setLayoutParams(new LinearLayout.LayoutParams(
                Utilities.convertDpToPixel(context, 150),
                Utilities.convertDpToPixel(context, 14)));
        spTextView.setTextSize(8);
        spTextView.setTextColor(Color.rgb(218, 218, 218));
        spTextView.setText(
                (nativeAd.getSponsored() != null) ?
                        "sponsored by " + nativeAd.getSponsored().getValue() : "sponsored");
        Utilities.setOneLineAndEllipsisForTextView(spTextView);
        bottomLeftWrapper.addView(spTextView);

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
        //setBackgroundDrawableはDeprecatedですが、古いバージョンの端末サポートのため使用しています
        nativeAdButton.setBackgroundDrawable(borders);
        nativeAdButton.setText(
                nativeAd.getCtatext() != null ?
                        nativeAd.getCtatext().getValue() : "詳しくはこちら");
        Utilities.setOneLineAndEllipsisForTextView(nativeAdButton);
        nativeAd.setClickEvent(nativeAdButton); // ボタンへのタップ設定

        nativeAdButtonArea.addView(nativeAdButton);
        bottomRightWrapper.addView(nativeAdButtonArea);

        return layout;
    }
}
