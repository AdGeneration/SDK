#import <UIKit/UIKit.h>
#import "ADGAdTruthManager.h"

typedef enum {
	kADGIVM_None = 0,
	kADGIVM_Interstitial,
	kADGIVM_Offerwall
} ADGInterstitialViewMode;

@interface ADGInterstitialWindow : UIWindow <UIWebViewDelegate, ADGAdTruthManagerDelegate>
{
	id delegate_;
}

@property (nonatomic, assign) id delegate;

- (id)initWithParams:(NSString *)locId;
- (void)setLocationId:(NSString *)locationId;
- (void)loadRequest;
- (void)showInterstitialView;
- (void)cancelInterstitial;
@end

@protocol ADGInterstitialViewDelegate
@optional
- (void)ADGInterstitialViewReceiveAd;
- (void)ADGInterstitialViewFailedToReceiveAd:(NSError *)error;
- (void)ADGInterstitialViewShow;
- (void)ADGInterstitialViewClose;
@end
