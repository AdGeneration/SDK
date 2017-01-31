//
//  ADGFBNativeAdView.h
//  ADGNativeSample
//
//  Copyright © 2016年 supership. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <ADG/ADGManagerViewController.h>
#import <FBAudienceNetwork/FBAdSettings.h>
#import <FBAudienceNetwork/FBNativeAd.h>
#import <FBAudienceNetwork/FBAdChoicesView.h>
#import <FBAudienceNetwork/FBMediaView.h>

@interface ADGFBNativeAdView : UIView

- (UIView*) createFBNativeAdView:(UIViewController *)viewController adgManagerViewController:(ADGManagerViewController *)adgManagerViewController nativeAd:(FBNativeAd *)nativeAd;

@end
