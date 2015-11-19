//
//  ADGImage.h
//  ADG
//
//  Copyright © 2015年 adgeneration. All rights reserved.
//

@interface ADGImage : NSObject

@property (nonatomic, strong, readonly) NSString *url;
@property (nonatomic, assign, readonly) double width;
@property (nonatomic, assign, readonly) double height;
@property (nonatomic, strong, readonly) NSObject *ext;

- (instancetype)initWithDictionary:(NSDictionary *)dict;

@end