#import <UIKit/UIKit.h>

typedef enum{
    BOTH,
    FRONT,
    BACK
} ADGADVIEW_TARGET;

@interface ADGAdWebView : UIView <UIWebViewDelegate>
{
    id delegate_;
}
@property (nonatomic, assign) id delegate;
- (void) loadRequest:(NSURLRequest*)request;
- (void) loadRequest:(NSURLRequest*)request target:(ADGADVIEW_TARGET)target;
- (void) setFrame:(CGRect) rect;
- (void) swapAdWebView;
- (void) stringByEvaluatingJavaScriptFromString:(NSString*)string;
- (NSString*) stringByEvaluatingJavaScriptFromString:(NSString*)string target:(ADGADVIEW_TARGET)target;
- (void) stopLoading;
- (UIWebView*) frontAdWebView;
- (UIWebView*) backAdWebView;
- (BOOL) isNoAd:(ADGADVIEW_TARGET)target callDelegate:(BOOL)callDelegate;
- (void) resetNoAdCount;
- (void) setWebViewBackgroundColor:(UIColor*)color;
- (void) setWebViewOpaque:(BOOL)opaque;
- (void) setAdScale:(float)scale;
@end

@protocol ADGAdWebViewDelegate

@optional
- (void)adgSetHidden:(BOOL)hidden;
- (BOOL)adgAdWebView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType source:(ADGADVIEW_TARGET)source;
- (void)adgAdWebViewDidFinishLoad:(UIWebView *)webView source:(ADGADVIEW_TARGET)source;
- (void)adgAdWebView:(UIWebView *)webView didFailLoadWithError:(NSError *)error source:(ADGADVIEW_TARGET)source;
- (void)adgNoAd;
@end

