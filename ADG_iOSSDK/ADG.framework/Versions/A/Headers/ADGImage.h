//
//  ADGImage.h
//  ADG
//
//  Copyright © 2015年 adgeneration. All rights reserved.
//

@interface ADGImage : NSObject

@property (nonatomic, strong, readonly, nullable) NSString *url;
@property (nonatomic, assign, readonly) double width;
@property (nonatomic, assign, readonly) double height;
@property (nonatomic, strong, readonly, nullable) NSObject *ext;

- (nonnull instancetype)initWithDictionary:(nullable NSDictionary *)dict;

@end
