#import <Foundation/Foundation.h>
#import "GADCustomEventBanner.h"
#import "GADCustomEventBannerDelegate.h"
#import <ADG/ADGManagerViewController.h>

@interface ADGAdMobMediation : NSObject<GADCustomEventBanner , ADGManagerViewControllerDelegate>

@property (nonatomic) ADGManagerViewController *adg;
@property(nonatomic, unsafe_unretained) NSObject<GADCustomEventBannerDelegate> *delegate;

@end
