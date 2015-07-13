#import <UIKit/UIKit.h>

@protocol ADGAdTruthManagerDelegate<NSObject>
@optional
- (void)ADGFinishPreparingAdTruth:(BOOL)success;
@end


@interface ADGAdTruthManager : NSObject{
    id delegate_;
}

- (id)initWithWebView:(UIWebView*)webview;
- (void)loadAdTruthJS;
- (NSString*)getPayLoad;
- (void)clean;

@property (nonatomic , assign) id<ADGAdTruthManagerDelegate> delegate;
@end