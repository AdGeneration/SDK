//
//  ADGLogger.h
//  ADG
//
//  Copyright © 2016年 adgeneration. All rights reserved.
//

typedef NS_ENUM(NSInteger, ADGLogLevel) {
    kADGLogLevelDebug = 0,
    kADGLogLevelInfo,
    kADGLogLevelWarning,
    kADGLogLevelError
};

@interface ADGLogger : NSObject

+ (ADGLogLevel)logLevel;
+ (void)setLogLevel:(ADGLogLevel)logLevel;
+ (void)outputDebug:(NSString *)str;
+ (void)outputInfo:(NSString *)str;
+ (void)outputWarning:(NSString *)str;
+ (void)outputError:(NSString *)str;
+ (void)outputDebug:(NSString *)str cls:(Class)cls method:(SEL)sel;
+ (void)outputInfo:(NSString *)str cls:(Class)cls method:(SEL)sel;
+ (void)outputWarning:(NSString *)strb cls:(Class)cls method:(SEL)sel;
+ (void)outputError:(NSString *)str cls:(Class)cls method:(SEL)sel;
+ (void)outputDebug:(NSString *)str error:(NSObject *)error cls:(Class)cls method:(SEL)sel;
+ (void)outputError:(NSString *)str error:(NSObject *)error cls:(Class)cls method:(SEL)sel;

@end
