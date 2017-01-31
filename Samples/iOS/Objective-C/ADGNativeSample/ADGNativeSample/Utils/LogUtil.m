//
//  LogUtil.m
//  ADGNativeSample
//
//  Copyright © 2016年 supership. All rights reserved.
//

#import "LogUtil.h"

@implementation LogUtil

+ (NSString *) format:(NSString *)log {
    NSDateFormatter *format = [[NSDateFormatter alloc] init];
    [format setLocale:[[NSLocale alloc] initWithLocaleIdentifier:@"ja_JP"]];
    [format setDateFormat:@"HH:mm:ss.SSS"];
    return [NSString stringWithFormat:@"%@ %@", [format stringFromDate:[NSDate date]], log];
}

@end
