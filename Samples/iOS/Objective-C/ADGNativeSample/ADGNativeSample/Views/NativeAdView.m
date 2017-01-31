//
//  NativeAdView.m
//  ADGNativeSample
//
//  Copyright © 2016年 supership. All rights reserved.
//

#import <ADG/ADGInformationIconView.h>
#import "NativeAdView.h"

@implementation NativeAdView

- (UIView *)createAdView:(ADGNativeAd *)nativeAd {
    // アイコン
    UIImageView *iconImageView = [[UIImageView alloc] initWithFrame:CGRectMake(4, 4, 30, 30)];
    if (nativeAd.iconImage.url.length > 0) {
        NSURL *iconImageUrl = [NSURL URLWithString:nativeAd.iconImage.url];
        NSData *iconImageData = [NSData dataWithContentsOfURL:iconImageUrl];
        iconImageView.image = [UIImage imageWithData:iconImageData];
    }

    // タイトル
    UILabel *titleLbl = [[UILabel alloc] initWithFrame:CGRectMake(38, 4, 258, 15)];
    if (nativeAd.title.text.length > 0) {
        titleLbl.numberOfLines = 1;
        titleLbl.font = [titleLbl.font fontWithSize:13];
        titleLbl.text = nativeAd.title.text;
    }

    // 広告マーク
    UILabel *adTextLabel = [[UILabel alloc] initWithFrame:CGRectMake(40, 20, 28, 14)];
    adTextLabel.textColor = [UIColor lightGrayColor];
    adTextLabel.font = [adTextLabel.font fontWithSize:11];
    adTextLabel.text = @"広告";

    // 本文
    UILabel *descriptionLbl = [[UILabel alloc] initWithFrame:CGRectMake(4, 30, 296, 40)];
    if (nativeAd.desc.value.length > 0) {
        descriptionLbl.numberOfLines = 2;
        descriptionLbl.font = [descriptionLbl.font fontWithSize:11];
        descriptionLbl.text = nativeAd.desc.value;
        descriptionLbl.textColor = [UIColor lightGrayColor];
    }

    // メインイメージ
    UIImageView *mainImageView = [[UIImageView alloc] initWithFrame:CGRectMake(4, 64, 292, 156)];
    if (nativeAd.mainImage.url.length > 0) {
        NSURL *mainUrl = [NSURL URLWithString:nativeAd.mainImage.url];
        NSData *mainImageData = [NSData dataWithContentsOfURL:mainUrl];
        mainImageView.image = [UIImage imageWithData:mainImageData];
        mainImageView.contentMode = UIViewContentModeScaleAspectFit;
        mainImageView.clipsToBounds = YES;
    }

    // インフォメーションアイコン
    ADGInformationIconView *infoIconView = [[ADGInformationIconView alloc] initWithNativeAd:nativeAd];
    if (infoIconView) {
        [mainImageView addSubview:infoIconView];
        [infoIconView updateFrameFromSuperview:UIRectCornerTopRight];
    }

    // スポンサー
    UILabel *sponsoredLbl = [[UILabel alloc] initWithFrame:CGRectMake(4, 228, 150, 20)];
    sponsoredLbl.numberOfLines = 1;
    sponsoredLbl.font = [sponsoredLbl.font fontWithSize:10];
    sponsoredLbl.textColor = [UIColor colorWithRed:0.7 green:0.7 blue:0.7 alpha:1.0];
    if (nativeAd.sponsored.value.length > 0) {
        sponsoredLbl.text = [NSString stringWithFormat:@"sponsored by %@", nativeAd.sponsored.value];
    } else {
        sponsoredLbl.text = @"sponsored";
    }

    // CTA
    UIButton *actionBtn = [[UIButton alloc] initWithFrame:CGRectMake(178, 223, 114, 25)];
    if (nativeAd.ctatext.value.length > 0) {
        [actionBtn setTitle:nativeAd.ctatext.value forState:UIControlStateNormal];
        [actionBtn setTitleColor:[UIColor colorWithRed:0.12 green:0.56 blue:1.00 alpha:1.0]
                        forState:UIControlStateNormal];
        [actionBtn.titleLabel setFont:[UIFont boldSystemFontOfSize:14]];
        actionBtn.backgroundColor = [UIColor whiteColor];
        actionBtn.layer.borderWidth = 1.0f;
        actionBtn.layer.borderColor = [[UIColor colorWithRed:0.12 green:0.56 blue:1.00 alpha:1.0] CGColor];
        actionBtn.layer.cornerRadius = 5.0f;
        actionBtn.titleEdgeInsets = UIEdgeInsetsMake(1.0f, 1.0f, 1.0f, 1.0f);
        actionBtn.titleLabel.adjustsFontSizeToFitWidth = YES;
        [nativeAd setTapEvent:actionBtn];  // ボタンへのタップ反応追加
    }

    UIView *nativeAdView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 300, 250)];
    [nativeAdView addSubview:iconImageView];
    [nativeAdView addSubview:titleLbl];
    [nativeAdView addSubview:adTextLabel];
    [nativeAdView addSubview:mainImageView];
    [nativeAdView addSubview:descriptionLbl];
    [nativeAdView addSubview:sponsoredLbl];
    [nativeAdView addSubview:actionBtn];

    return nativeAdView;
}

@end
