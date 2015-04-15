#import <Foundation/Foundation.h>
#import <GoogleMobileAds/Mediation/GADCustomEventBanner.h>
#import <GoogleMobileAds/Mediation/GADCustomEventBannerDelegate.h>
#import <ADG/ADGManagerViewController.h>

@interface ADGAdMobMediation : NSObject<GADCustomEventBanner , ADGManagerViewControllerDelegate>

@property (nonatomic) ADGManagerViewController *adg;
@property(nonatomic, unsafe_unretained) NSObject<GADCustomEventBannerDelegate> *delegate;

@end
