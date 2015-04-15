#import <Foundation/Foundation.h>
#import <GoogleMobileAds/Mediation/GADCustomEventInterstitial.h>
#import <GoogleMobileAds/Mediation/GADCustomEventInterstitialDelegate.h>
#import <ADG/ADGInterstitial.h>

@interface ADGAdMobInterstitialMediation : NSObject<GADCustomEventInterstitial , ADGInterstitialDelegate>

@property (nonatomic , assign) ADGInterstitial *adg;
@property(nonatomic, assign) NSObject<GADCustomEventInterstitialDelegate> *delegate;

@end
