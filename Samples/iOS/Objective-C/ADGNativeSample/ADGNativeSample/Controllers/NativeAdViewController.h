//
//  NativeAdViewController.h
//  ADGNativeSample
//
//  Copyright © 2016年 supership. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <ADG/ADGManagerViewController.h>
#import <FBAudienceNetwork/FBNativeAd.h>

@interface NativeAdViewController : UIViewController

@property (nonatomic, retain) ADGManagerViewController *adg;
@property (nonatomic, retain) FBNativeAd *nativeAd;

@end
