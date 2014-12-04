#import <UIKit/UIKit.h>
#import <CoreLocation/CoreLocation.h>
#import "ADGInterstitialWindow.h"

typedef enum {
	kInterstitialADNW_none = 0,
	kInterstitialADNW_millennial,
	kInterstitialADNW_ADG
} ADGInterstitialADNW;

@interface ADGInterstitialManager : UIViewController <ADGInterstitialViewDelegate>
{
	NSDictionary *settings_;
	id delegate_;
}
@property (nonatomic, assign) id delegate;
@property (nonatomic, retain) NSDictionary *settings;
- (id)adgInit:(BOOL)useLocation;
- (void) setLocationid:(NSString *)locationid;
- (void) setLocationid:(NSString *)locationid withKey:(NSString *)key;
- (void)loadInterstitialRequest;
- (void)loadInterstitialRequest:(NSString *)eventName span:(int)span;
- (void)loadInterstitialRequest:(NSString *)eventName span:(int)span doSave:(BOOL)save;
- (void)preloadInterstitialRequest;
- (void)preloadInterstitialRequest:(NSString *)eventName span:(int)span;
- (void)preloadInterstitialRequest:(NSString *)eventName span:(int)span doSave:(BOOL)save;
- (void)showInterstitialView;
- (void)loadMillennialRequest;
- (void)loadMillennialRequest:(NSString *)eventName span:(int)span;
- (void)loadMillennialRequest:(NSString *)eventName span:(int)span doSave:(BOOL)save;
- (void)interstitialCallBack:(NSError *)error;
- (void)cancelInterstitial;
@end

@protocol ADGInterstitialManagerDelegate
@optional
- (void)ADGInterstitialManagerReceiveAd;
- (void)ADGInterstitialManagerFailedToReceiveAd:(ADGInterstitialADNW)source error:(NSError *)error;
@end
