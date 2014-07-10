//
//  ADGInterstitialManager.h
//  ADG
//
//  Created by    on 2013/12/12.
//  Copyright (c) 2013年 mediba.inc. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <CoreLocation/CoreLocation.h>
#import "ADGInterstitialWindow.h"


@interface ADGInterstitialManager : UIViewController<ADGInterstitialViewDelegate>
{
    NSDictionary *settings_;
    id delegate_;
}
@property (nonatomic, assign) id delegate;
@property (nonatomic, retain) NSDictionary *settings;
- (void) setRates:(NSDictionary*)params;
- (id) adgInit:(BOOL)useLocation;
- (void) loadMillennialRequest;
- (void) loadMillennialRequest:(NSString*)eventName countSpan:(int)span;
- (void) loadMillennialRequest:(NSString*)eventName countSpan:(int)span doSave:(BOOL)save;
- (void) loadInterstitialRequest;
- (void) loadInterstitialRequest:(NSString*)eventName countSpan:(int)span;
- (void) loadInterstitialRequest:(NSString*)eventName countSpan:(int)span doSave:(BOOL)save;
- (void) interstitialCallBack:(NSError*)error;
- (void) showInterstitialView;
@end

typedef enum {
    kInterstitialADNW_none=0,
    kInterstitialADNW_millennial,
    kInterstitialADNW_ADG
} ADGInterstitialADNW;

@protocol ADGInterstitialManagerDelegate
@optional
- (void)ADGInterstitialManagerReceiveAd;
- (void)ADGInterstitialManagerFailedToReceiveAd:(ADGInterstitialADNW)source error:(NSError*)error;
@end

