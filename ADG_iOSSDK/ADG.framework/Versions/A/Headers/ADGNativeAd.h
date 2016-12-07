//
//  ADGNativeAd.h
//  ADG
//
//  Copyright © 2015年 adgeneration. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "ADGTitle.h"
#import "ADGImage.h"
#import "ADGData.h"
#import "ADGLink.h"

@interface ADGNativeAd : NSObject

@property (nonatomic, assign, readonly) int ver;

@property (nonatomic, strong, readonly, nullable) ADGTitle *title;
@property (nonatomic, strong, readonly, nullable) ADGImage *mainImage;
@property (nonatomic, strong, readonly, nullable) ADGImage *iconImage;
@property (nonatomic, strong, readonly, nullable) ADGData *sponsored;
@property (nonatomic, strong, readonly, nullable) ADGData *desc;
@property (nonatomic, strong, readonly, nullable) ADGData *ctatext;

@property (nonatomic, strong, readonly, nullable) ADGLink *link;
@property (nonatomic, strong, readonly, nullable) NSArray *imptrackers;
@property (nonatomic, strong, readonly, nullable) NSString *jstracker;
@property (nonatomic, strong, readonly, nullable) NSObject *ext;

@property (nonatomic, strong, readonly, nullable) ADGData *informationIconAccompanyingText;
@property (nonatomic, strong, readonly, nullable) ADGData *informationIconOptoutURL;
@property (nonatomic, strong, readonly, nullable) ADGData *informationIconIconURL;

- (nonnull instancetype)initWithDictionary:(nonnull NSDictionary *)dict;
- (void)callImptrackers;
- (void)callJstracker;
- (void)setTapEvent:(nonnull UIView *)view;

+ (void)setUserAgent:(nullable NSString *)userAgent;
+ (void)callTrackers:(nullable NSArray *)trackers;
+ (void)callTrackers:(nullable NSArray *)trackers isPostRequest:(BOOL)isPostRequest;

@end
