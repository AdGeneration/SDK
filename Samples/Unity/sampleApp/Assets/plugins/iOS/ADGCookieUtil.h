//
//  ADGCookieUtil.h
//  InterstitialSample2
//
//  Created by   on 2014/02/26.
//  Copyright (c) 2014年 mediba inc. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface ADGCookieUtil : NSObject
{
    
}
+ (void) saveMasCookieData;
+ (NSArray*) getMasCookieData;
+ (void) setMasCookie;
+ (void) saveADGCookieData;
+ (NSArray*) getADGCookieData;
+ (void) setADGCookie;
+ (void) clearWebStorage:(UIWebView*)wv;
+ (void) setAppFlag;
@end
