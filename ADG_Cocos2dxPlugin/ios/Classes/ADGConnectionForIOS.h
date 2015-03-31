#include <stddef.h>

class ADGConnectionForIOS
{   
public:
 static void initADG(char *adid , char *type , int x , int y , void (*)(const char * , const char *));
 static void initADGWithWH(char *adid , char *type , int x , int y , int width , int height , void (*func)(const char * , const char *));
 static void initADGWithScale(char *adid , char *type , int x , int y , int width , int height , double scale , void (*func)(const char * , const char *));
 static void initADGAction(char *adid , char *type , int x , int y , int width , int height , double scale , void (*func)(const char * , const char *));
 static void showADG();
 static void hideADG();
 static void pauseADG();
 static void resumeADG();
 static void changeLocationADG(int x , int y);
 static void changeLocationEasyADG(char *horizontal , char *vertical);
 static void moveDefaultLocationADG();
 static void finishADG();
 static void initInterADG(char *adid , int backgroundType , int closeButtonType , int span , bool isPercentage , bool isPreventAccidentClick , void (*func)(const char * , const char *));
 static void loadInterADG();
 static void showInterADG();
 static void dismissInterADG();
};
