//
//  ADGInterstitialManager.h
//  ADG
//
//  Created by    on 2013/12/12.
//  Copyright (c) 2013年 mediba.inc. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <CoreLocation/CoreLocation.h>



@interface ADGInterstitialManager : UIViewController
{
    NSDictionary *settings_;
    id delegate_;
}
@property (nonatomic, assign) id delegate;
@property (nonatomic, retain) NSDictionary *settings;
- (void) setRates:(NSDictionary*)params;
- (id) adgInit:(BOOL)useLocation;
- (void) loadRequest;
- (void) loadRequest:(NSString*)eventName countSpan:(int)span;
- (void) loadRequest:(NSString*)eventName countSpan:(int)span doSave:(BOOL)save;
- (void) interstitialCallBack:(NSError*)error;
@end

typedef enum {
    kInterstitialADNW_none=0,
    kInterstitialADNW_millennial
} ADGInterstitialADNW;

@protocol ADGInterstitialManagerDelegate
@optional
- (void)ADGInterstitialManagerReceiveAd;
- (void)ADGInterstitialManagerFailedToReceiveAd:(ADGInterstitialADNW)source error:(NSError*)error;
@end

