//
//  ADGInterstitialWindow.h
//  InterstitialSample2
//
//  Created by   on 2014/04/02.
//  Copyright (c) 2014年 mediba inc. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ADGAdTruthManager.h"

typedef enum{
    kADGIVM_None=0,
    kADGIVM_Interstitial,
    kADGIVM_Offerwall
}ADGInterstitialViewMode;

@interface ADGInterstitialWindow : UIWindow <UIWebViewDelegate,ADGAdTruthManagerDelegate>
{
    id delegater_;
}

@property (nonatomic, assign) id delegater;

- (id) initWithParams:(NSString*)locId;
- (void) setLocationId:(NSString*)locationId;
- (void) loadRequest;
- (void) showInterstitialView;

@end

@protocol ADGInterstitialViewDelegate
@optional
- (void)ADGInterstitialViewReceiveAd;
- (void)ADGInterstitialViewFailedToReceiveAd:(NSError*)error;
- (void)ADGInterstitialViewShow;
- (void)ADGInterstitialViewClose;
@end
