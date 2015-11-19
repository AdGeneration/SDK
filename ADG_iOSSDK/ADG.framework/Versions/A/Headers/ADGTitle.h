//
//  ADGTitle.h
//  ADG
//
//  Copyright © 2015年 adgeneration. All rights reserved.
//

@interface ADGTitle : NSObject

@property (nonatomic, strong, readonly) NSString *text;
@property (nonatomic, strong, readonly) NSObject *ext;

- (instancetype)initWithDictionary:(NSDictionary *)dict;

@end