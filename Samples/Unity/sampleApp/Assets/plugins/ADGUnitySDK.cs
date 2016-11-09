/* AdGeneration UnityPlugin Ver.2.0.0 */

using UnityEngine;
using System;
using System.Collections;
using System.Runtime.InteropServices;

public class ADGUnitySDK : ADGMonoBehaviour {

	//パラメータ
	private static string iosLocationID = "";
	private static string iosInterLocationID = "";
	private static string androidLocationID = "";
	private static string androidInterLocationID = "";
	private static string adType = "SP";
	private static bool isIOSEasyPosition = false;
	private static float x = 0;
	private static float y = 0;
	private static string horizontal = "CENTER";
	private static string vertical = "TOP";
	private static string messageObjName = "";
	private static int width = 0;
	private static int height = 0;
	private static int[] margin = {0};
	private static double scale = 1;

	private static int backgroundType = 0;
	private static int closeButtonType = 0;
	private static int span = 0;
	private static bool isPercentage = false;
	private static bool isPreventAccidentClick = false;
	//パラメータ

	private const int androidDesignShift = 100;

	#if UNITY_IPHONE
	private static IntPtr adgni = IntPtr.Zero;
	#elif UNITY_ANDROID
	private static AndroidJavaObject androidPlugin = null;
	#endif
	
	private static ADGUnitySDK myInstance;

	public static string IOSLocationID{
    	set{iosLocationID = value;}
    	get{return iosLocationID;}
	}

	public static string IOSInterLocationID{
    	set{iosInterLocationID = value;}
    	get{return iosInterLocationID;}
	}

	public static string AndroidLocationID{
    	set{androidLocationID = value;}
    	get{return androidLocationID;}
	}

	public static string AndroidInterLocationID{
    	set{androidInterLocationID = value;}
    	get{return androidInterLocationID;}
	}

	public static string AdType{
    	set{adType = value;}
    	get{return adType;}
	}

	public static bool IsIOSEasyPosition{
		set{isIOSEasyPosition = value;}
		get{return isIOSEasyPosition;}
	}

	public static float X{
    	set{x = value;}
    	get{return x;}
	}

	public static float Y{
    	set{y = value;}
    	get{return y;}
	}

	public static string Horizontal{
    	set{horizontal = value;}
    	get{return horizontal;}
	}

	public static string Vertical{
    	set{vertical = value;}
    	get{return vertical;}
	}

	public static string MessageObjName{
    	set{messageObjName = value;}
    	get{return messageObjName;}
	}

	public static int Width{
    	set{width = value;}
    	get{return width;}
	}

	public static int Height{
    	set{height = value;}
    	get{return height;}
	}

	public static int[] Margin{
    	set{margin = value;}
    	get{return margin;}
	}

	public static double Scale{
    	set{scale = value;}
    	get{return scale;}
	}

    public static int BackgroundType{
    	set{backgroundType = value;}
    	get{return backgroundType;}
    }

    public static int CloseButtonType{
    	set{closeButtonType = value;}
    	get{return closeButtonType;}
    }
    public static int Span{
    	set{span = value;}
    	get{return span;}
    }

    public static bool IsPercentage{
    	set{isPercentage = value;}
    	get{return isPercentage;}
    }

    public static bool IsPreventAccidentClick{
    	set{isPreventAccidentClick = value;}
    	get{return isPreventAccidentClick;}
    }

	private static bool isEditor{
		get{
			return Application.isEditor;
		}
	}

	public static void initADG(){
		initADGCommon();
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			if(!isIOSEasyPosition){
				horizontal = "";
				vertical = "";
			}
			adgni = _initADG(adgni , iosLocationID , adType, x , y , messageObjName , width , height , (float)scale , horizontal , vertical);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			makeJavaInstance();
			androidPlugin.Call("initADG", androidLocationID , adType , horizontal , vertical ,  messageObjName , width , height , (float)scale , margin);
		}
		#endif
	}

	private static void initADGCommon(){
		if(myInstance == null){
			GameObject gameObject = new GameObject("ADGForUnity");
			DontDestroyOnLoad(gameObject);//Makes the object target not be destroyed automatically when loading a new scene.
			gameObject.hideFlags = HideFlags.HideAndDontSave;//A combination of not shown in the hierarchy and not saved to to scenes.
			myInstance = gameObject.AddComponent<ADGUnitySDK>();
		}		
	}

	#if UNITY_ANDROID
	private static void makeJavaInstance(){
		if(androidPlugin == null){
			AndroidJavaClass manager = new AndroidJavaClass("com.socdm.d.adgeneration.plugin.unity.ADGNativeManager");
			androidPlugin = manager.CallStatic<AndroidJavaObject>("instance");
		}
	}
	#endif

	public static bool canCallADG(){
		if(noInstance)return false;
		else return true;
	}
	
	private static bool noInstance{
		get{
			return myInstance == null || isEditor;
		}
	}

	public static void finishADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_finishADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("finishADG");
		}
		#endif
		Destroy(myInstance);
		myInstance = null;
	}

	public static void resumeADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_resumeADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("resumeADG");
		}
		#endif
	}
	
	public static void pauseADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_pauseADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("pauseADG");
		}
		#endif
	}
	
	public static void showADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_resumeADG(adgni);
			_showADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("resumeADG");
			androidPlugin.Call("showADG");
		}
		#endif
	}
	
	public static void hideADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_hideADG(adgni);
			_pauseADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("hideADG");
			androidPlugin.Call("pauseADG");
		}
		#endif
	}
	
	public static void changeLocationADG(float tempx , float tempy){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_changeLocationADG(adgni , tempx , tempy);
		}
		#endif
	}
	
	public static void changeLocationADG(string temphorizontal , string tempvertical){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			if(isIOSEasyPosition){
				_changeLocationEasyADG(adgni , temphorizontal , tempvertical);
			}
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("changeLocationADG" , temphorizontal , tempvertical);
		}
		#endif
	}
	
	public static void setDefaultLocationADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_changeLocationADG(adgni , x , y);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("changeLocationADG" , horizontal , vertical);
		}
		#endif
	}

	public static void changeMarginADG(int[] tempmargin){
		if(noInstance)return;
		#if UNITY_IPHONE
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("changeMarginADG" , tempmargin);
		}
		#endif
	}

	public static void setBackgroundColorADG(int red , int green , int blue , int alpha){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_setBackgroundColorADG(adgni , red , green , blue , alpha);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("setBackgroundColorADG" , red , green , blue , alpha);
		}
		#endif
	}

	public static void initInterADG(){
		initADGCommon();
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			adgni = _initInterADG(adgni , iosInterLocationID , messageObjName , backgroundType , closeButtonType , span , isPercentage , isPreventAccidentClick);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			makeJavaInstance();
			androidPlugin.Call("initInterADG", androidInterLocationID , messageObjName , changeDesignNum(backgroundType) , changeDesignNum(closeButtonType) , span , isPercentage , isPreventAccidentClick);
		}
		#endif
	}

	public static void loadInterADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_loadInterADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("loadInterADG");
		}
		#endif
	}

	public static void showInterADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_showInterADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("showInterADG");
		}
		#endif
	}

	public static void dismissInterADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_dismissInterADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("dismissInterADG");
		}
		#endif
	}

	public static void finishInterADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_finishInterADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("finishInterADG");
		}
		#endif
	}

	private static int changeDesignNum(int num){
		if(num < androidDesignShift && Application.platform == RuntimePlatform.Android){
			return num + androidDesignShift;
		}
		else{
			return num;
		}
	}

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}