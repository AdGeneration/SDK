#ifndef ADGConstants_h
#define ADGConstants_h

#define kADGSlideAnimationDuration  0.5

#define kADGAdSize_Sp_Width         320
#define kADGAdSize_Sp_Height        50

#define kADGAdSize_Large_Width      320
#define kADGAdSize_Large_Height     100

#define kADGAdSize_Rect_Width       300
#define kADGAdSize_Rect_Height      250

#define kADGAdSize_Tablet_Width     728
#define kADGAdSize_Tablet_Height    90

typedef enum {
    kADG_AdType_Sp = 0,
    kADG_AdType_Large,
    kADG_AdType_Rect,
    kADG_AdType_Tablet,
    kADG_AdType_Free
} ADGAdType;

typedef enum {
    kADG_Mw_None=0,
    kADG_Mw_Other,
    kADG_Mw_Unity,
    kADG_Mw_Titanium,
    kADG_Mw_Cocos2dx
} ADGMiddleware;
#endif
