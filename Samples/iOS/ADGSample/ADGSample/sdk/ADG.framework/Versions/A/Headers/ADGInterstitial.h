#import "ADGManagerViewController.h"

@interface ADGInterstitial : NSObject<ADGManagerViewControllerDelegate> {
}
@property (nonatomic, assign) id delegate;
- (void)setTemplateType:(int)type;
- (void)setPortraitTemplateType:(int)type;
- (void)setLandscapeTemplateType:(int)type;

- (void)setBackgroundType:(int)designType;
- (void)setPortraitBackgroundType:(int)designType;
- (void)setLandscapeBackgroundType:(int)designType;

- (void)setCloseButtonType:(int)designType;
- (void)setPortraitCloseButtonType:(int)designType;
- (void)setLandscapeCloseButtonType:(int)designType;


- (void)setSpan:(int)span;
- (void)setSpan:(int)span isPercentage:(BOOL)isPercentage;

- (void)setLocationId:(NSString*)locationId;
- (void)setAdType:(ADGAdType)adType;
- (void)setAdBackGroundColor:(UIColor*)color;
- (void)setAdScale:(double)scale;
- (void)setMiddleware:(ADGMiddleware)middleware;
- (void)setPreventAccidentClick:(BOOL)preventAccidentClick;

- (void)preload;
- (void)show;
- (void)dismiss;
@end

@protocol ADGInterstitialDelegate <ADGManagerViewControllerDelegate>
@optional
- (void)ADGInterstitialClose;
@end

