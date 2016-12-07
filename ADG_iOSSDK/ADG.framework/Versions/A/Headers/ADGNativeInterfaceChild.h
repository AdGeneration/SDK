#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface ADGNativeInterfaceChild : NSObject

@property (nonatomic, assign) id delegate;
@property (nonatomic, copy) NSString *adId;
@property (nonatomic, copy) NSDictionary *param;
@property (nonatomic, copy) NSString *vastXML;
@property (nonatomic, assign) CGRect rect;
@property (nonatomic, assign) UIViewController *viewCon;
@property (nonatomic, assign) UIViewController *rootViewCon;
@property (nonatomic, assign) BOOL isOriginInterstitial;
@property (nonatomic, assign) BOOL enableSound;
@property (nonatomic, assign) BOOL isTest;
@property (nonatomic, assign) BOOL usePartsResponse;
- (void)sendReceiveAd;
- (void)sendReceiveNativeAd:(id)nativeAd;
- (void)sendFailedToReceiveAd;
- (void)sendCompleteMovieAd;
- (void)sendOpenUrl;
- (void)sendCloseInterstitial;
- (void)sendShowInterstitial;
- (void)sendReplayMovieAd;
- (void)sendResumeRefresh;
- (void)sendErrorClassName;
- (BOOL)loadProcess;
- (void)startProcess;
- (void)stopProcess;
- (BOOL)checkOSVersion;
@end

@protocol ADGNativeInterfaceChildDelegate
@optional
- (void)ADGNativeInterfaceChildReceiveAd;
- (void)ADGNativeInterfaceChildReceiveAd:(id)nativeAd;
- (void)ADGNativeInterfaceChildFailedToReceiveAd;
- (void)ADGNativeInterfaceChildCompleteMovieAd;
- (void)ADGNativeInterfaceChildOpenUrl;
- (void)ADGNativeInterfaceChildCloseInterstitial;
- (void)ADGNativeInterfaceChildShowInterstitial;
- (void)ADGNativeInterfaceChildReplayMovieAd;
- (void)ADGNativeInterfaceChildResumeRefresh;
- (void)ADGNativeInterfaceChildErrorClassName;
- (void)ADGNativeInterfaceChildCompleteRewardAd:(NSDictionary *)response;
@end
