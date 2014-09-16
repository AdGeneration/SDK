/* AdGeneration UnityPlugin Ver.1.2.1 */

using UnityEngine;
using System;
using System.Collections;
using System.Runtime.InteropServices;

public class ADGUnitySDK : ADGMonoBehaviour {

	//パラメータ
	public static string iosLocationID = "";
	public static string androidLocationID = "";
	public static string adType = "SP";
	public static float x = 0;
	public static float y = 0;
	public static string horizontal = "CENTER";
	public static string vertical = "TOP";
	public static string messageObjName = "";
	public static int width = 0;
	public static int height = 0;
	public static int[] margin = {0};
	//パラメータ

	#if UNITY_IPHONE
	private static IntPtr adgni;
	#elif UNITY_ANDROID
	private static AndroidJavaObject androidPlugin;
	#endif
	
	private static ADGUnitySDK myInstance;

	public static string IOSLocationID{
    	set{iosLocationID = value;}
    	get{return iosLocationID;}
	}

	public static string AndroidLocationID{
    	set{androidLocationID = value;}
    	get{return androidLocationID;}
	}

	public static string AdType{
    	set{adType = value;}
    	get{return adType;}
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

	private static bool isEditor{
		get{
			return Application.isEditor;
		}
	}

	public static void initADG(){
		if(myInstance == null){
			GameObject gameObject = new GameObject("ADGForUnity");
			DontDestroyOnLoad(gameObject);//Makes the object target not be destroyed automatically when loading a new scene.
			gameObject.hideFlags = HideFlags.HideAndDontSave;//A combination of not shown in the hierarchy and not saved to to scenes.
			myInstance = gameObject.AddComponent<ADGUnitySDK>();
			#if UNITY_IPHONE
			if(Application.platform == RuntimePlatform.IPhonePlayer){
				adgni = _initADG(iosLocationID , adType, x , y , messageObjName , width , height);
			}
			#elif UNITY_ANDROID
			if(Application.platform == RuntimePlatform.Android){
				AndroidJavaClass manager = new AndroidJavaClass("com.socdm.d.adgeneration.plugin.unity.ADGNativeManager");
				androidPlugin = manager.CallStatic<AndroidJavaObject>("instance");
				androidPlugin.Call("initADG", androidLocationID , adType , horizontal , vertical ,  messageObjName , width , height , margin);
			}
			#endif
		}
	}

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
		#if UNITY_ANDROID
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

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}