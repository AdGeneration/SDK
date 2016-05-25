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

@property (nonatomic, strong, readonly) ADGTitle *title;
@property (nonatomic, strong, readonly) ADGImage *mainImage;
@property (nonatomic, strong, readonly) ADGImage *iconImage;
@property (nonatomic, strong, readonly) ADGData *sponsored;
@property (nonatomic, strong, readonly) ADGData *desc;
@property (nonatomic, strong, readonly) ADGData *ctatext;

@property (nonatomic, strong, readonly) ADGLink *link;
@property (nonatomic, strong, readonly) NSArray *imptrackers;
@property (nonatomic, strong, readonly) NSString *jstracker;
@property (nonatomic, strong, readonly) NSObject *ext;

- (instancetype)initWithDictionary:(NSDictionary *)dict;
- (void)callImptrackers;
- (void)callJstracker;
- (void)setTapEvent:(UIView *)view;

+ (void)setUserAgent:(NSString *)userAgent;
+ (void)callTrackers:(NSArray *)trackers;
+ (void)callTrackers:(NSArray *)trackers isPostRequest:(BOOL)isPostRequest;

@end
