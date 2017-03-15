#import <ADG/ADGManagerViewController.h>
#import <Foundation/Foundation.h>
#import <GoogleMobileAds/GADCustomEventBanner.h>
#import <GoogleMobileAds/GADCustomEventBannerDelegate.h>

@interface ADGAdMobMediation : NSObject <GADCustomEventBanner, ADGManagerViewControllerDelegate>

@property (nonatomic, retain) ADGManagerViewController *adg;
@property (nonatomic, retain) id nativeAd;  // FBNativeAd
@property (nonatomic, unsafe_unretained) NSObject<GADCustomEventBannerDelegate> *delegate;

@end
