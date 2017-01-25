//
//  ADGInformationIconView.h
//  ADG
//
//  Created on 2016/11/01.
//  Copyright © 2016 adgeneration. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ADGData.h"
#import "ADGNativeAd.h"

typedef NS_ENUM(NSInteger, kADGInformationIconViewBackgroundType){
    kADGInformationIconViewBackgroundTypeWhite,
    kADGInformationIconViewBackgroundTypeBlack
};

@interface ADGInformationIconView : UIView

///The link for opt-out.
@property (nonatomic, copy, readonly, nullable) NSString *optoutURL;

///Determines whether the black background mask is shown, or a white mask is used.
@property (nonatomic, assign, getter=getBackgroundType, setter=setBackgroundType:) kADGInformationIconViewBackgroundType backgroundType;

///Unavailable initializers
- (nonnull instancetype)init __attribute__((unavailable("init is not available")));
- (nonnull instancetype)initWithFrame:(CGRect)frame __attribute__((unavailable("init is not available")));
- (nullable instancetype)initWithCoder:(nonnull NSCoder *)aDecoder __attribute__((unavailable("init is not available")));

/**
 Initialize this view with a given native ad. Configuration is pulled from the native ad.
 @param nativeAd The native ad to initialize with.
 */
- (nonnull instancetype)initWithNativeAd:(nonnull ADGNativeAd *)nativeAd;

/**
 Initialize this view with a given native ad. Configuration is pulled from the native ad.
 @param nativeAd The native ad to initialize with.
 @param expandable The configuration for expandable view.
 */
- (nonnull instancetype)initWithNativeAd:(nonnull ADGNativeAd *)nativeAd expandable:(BOOL)expandable NS_DESIGNATED_INITIALIZER;

/**
 Using the superview, this updates the frame of this view, positioning the icon in the corner specified. UIRectCornerAllCorners not supported.
 @param corner The corner to display this view from.
 */
- (void)updateFrameFromSuperview:(UIRectCorner)corner;

@end

@interface ADGInformationIconViewIconImageView : UIImageView

- (nonnull instancetype)initWithURL:(nonnull NSString *)urlString;
- (void)registerIconURLWithType:(kADGInformationIconViewBackgroundType)type urlString:(nonnull NSString *)urlString;
- (void)changeIconWithType:(kADGInformationIconViewBackgroundType)type;

@end

@interface ADGInformationIconViewLabel : UILabel

- (nonnull instancetype)initWithText:(nonnull NSString *)text;
- (void)setColorWithType:(kADGInformationIconViewBackgroundType)type;
- (CGFloat)getWidthByText;

@end
