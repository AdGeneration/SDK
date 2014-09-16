#import <UIKit/UIKit.h>
#import <MediaPlayer/MPMoviePlayerController.h>

@interface ADGVideoViewController : UIViewController
{
    id delegate_;
    NSURL *movieURL_;
}
@property (nonatomic, assign) id delegate;
@property (nonatomic, retain) NSURL *movieURL;

- (id)initWithUrl:(NSString *)url;
-(void)dismiss;

@end

@protocol ADGVideoViewControllerDelegate

@optional 

- (void)showViewADGVideoViewController;
- (void)closeViewADGVideoViewController;

@end
