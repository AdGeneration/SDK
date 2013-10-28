#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface ADGUtil : NSObject
{
    
}
+ (BOOL) isLowerOS:(float)version;
+ (UIWindow*) getWindow;
+ (UIViewController*) getFrontViewController;
+ (CGFloat)floatFromDictionary:(NSDictionary *)dictionary forKey:(NSString *)key;
+ (int)intFromDictionary:(NSDictionary *)dictionary forKey:(NSString *)key;
+ (CGFloat)floatFromDictionary:(NSDictionary *)dictionary forKey:(NSString *)key withDefault:(CGFloat)defaultValue;
+ (BOOL)booleanFromDictionary:(NSDictionary *)dictionary forKey:(NSString *)key;
+ (NSString *)requiredStringFromDictionary:(NSDictionary *)dictionary forKey:(NSString *)key;
+ (UInt64)getEpochMilliSeconds;
@end
