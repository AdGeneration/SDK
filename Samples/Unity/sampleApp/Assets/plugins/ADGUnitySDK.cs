using UnityEngine;
using System;
using System.Collections;
using System.Runtime.InteropServices;

public class ADGUnitySDK : MonoBehaviour {

	const string IOS_LOCATIONID = "10723";
	const string ANDROID_LOCATIONID = "10724";
	const string ADTYPE = "SP";
	const float X = 0;
	const float Y = 0;
	const string HORIZONTAL = "LEFT";
	const string VERTICAL = "TOP";
	const string MESSAGEOBJNAME = "MainCamera";

	const int WIDTH = 0;
	const int HEIGHT = 0;

	#if UNITY_IPHONE
	private static IntPtr adgni;
	[DllImport ("__Internal")]
	private static extern IntPtr _initADG (string adid , string adtype , float x , float y , string objName , int width , int height);
	[DllImport ("__Internal")]
	private static extern void _renewADG (IntPtr adgni , string adid , string adtype , float x , float y , string objName);
	[DllImport ("__Internal")]
	private static extern void _finishADG (IntPtr adgni);
	[DllImport ("__Internal")]
	private static extern void _resumeADG (IntPtr adgni);
	[DllImport ("__Internal")]
	private static extern void _pauseADG (IntPtr adgni);
	[DllImport ("__Internal")]
	private static extern void _showADG (IntPtr adgni);
	[DllImport ("__Internal")]
	private static extern void _hideADG (IntPtr adgni);
	[DllImport ("__Internal")]
	private static extern void _changeLocationADG (IntPtr adgni , float x , float y);
	#elif UNITY_ANDROID
	private static AndroidJavaObject androidPlugin;
	#endif
	
	private static ADGUnitySDK myInstance;

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
				adgni = _initADG(IOS_LOCATIONID , ADTYPE, X , Y , MESSAGEOBJNAME , WIDTH , HEIGHT);
			}
			#elif UNITY_ANDROID
			if(Application.platform == RuntimePlatform.Android){
				AndroidJavaClass manager = new AndroidJavaClass("com.socdm.d.adgeneration.plugin.unity.ADGNativeManager");
				androidPlugin = manager.CallStatic<AndroidJavaObject>("instance");
				androidPlugin.Call("initADG", ANDROID_LOCATIONID , ADTYPE , HORIZONTAL , VERTICAL ,  MESSAGEOBJNAME , WIDTH , HEIGHT);
			}
			#endif
		}
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
			_showADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("showADG");
		}
		#endif
	}
	
	public static void hideADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_hideADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("hideADG");
		}
		#endif
	}
	
	public static void changeLocationADG(float x , float y){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_changeLocationADG(adgni , x , y);
		}
		#endif
	}
	
	public static void changeLocationADG(string horizontal , string vertical){
		if(noInstance)return;
		#if UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("changeLocationADG" , horizontal , vertical);
		}
		#endif
	}
	
	public static void setDefaultLocationADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_changeLocationADG(adgni , X , Y);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("changeLocationADG" , HORIZONTAL , VERTICAL);
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