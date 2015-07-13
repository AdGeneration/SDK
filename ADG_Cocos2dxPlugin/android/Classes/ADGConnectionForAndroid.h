#include <stddef.h>

class ADGConnectionForAndroid
{
public:
 static void initADG(char *adid , char *type , char *horizontal , char *vertical);
 static void initADGWithWH(char *adid , char *type , char *horizontal , char *vertical , int width , int height);
 static void initADGWithScale(char *adid , char *type , char *horizontal , char *vertical , int width , int height , double scale);
 static void showADG();
 static void hideADG();
 static void pauseADG();
 static void resumeADG();
 static void changeLocationADG(char *horizontal , char *vertical);
 static void moveDefaultLocationADG();
 static void finishADG();
 static void changeMarginADG(int left , int top , int right , int bottom);
 static void initInterADG(char *adid , int backgroundType , int closeButtonType , int span , bool isPercentage , bool isPreventAccidentClick);
 static void loadInterADG();
 static void showInterADG();
 static void dismissInterADG();
 static bool canCallADG();
};
