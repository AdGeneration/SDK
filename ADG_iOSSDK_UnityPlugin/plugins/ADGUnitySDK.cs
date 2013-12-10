using UnityEngine;
using System;
using System.Collections;
using System.Runtime.InteropServices;

public class ADGUnitySDK : MonoBehaviour {

	//ユーザー定義部分ここから
	const string IOS_LOCATIONID = "10643";
	const string ADTYPE = "SP";
	const float X = 0;
	const float Y = 0;
	const string MESSAGEOBJNAME = "MainText";
	//ユーザー定義部分ここまで

	#if UNITY_IPHONE
	private static IntPtr adgni;
	[DllImport ("__Internal")]
	private static extern IntPtr _initADG (string adid , string adtype , float x , float y , string objName);
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
				adgni = _initADG(IOS_LOCATIONID , ADTYPE, X , Y , MESSAGEOBJNAME);
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
		#endif
	}
	
	public static void pauseADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_pauseADG(adgni);
		}
		#endif
	}
	
	public static void showADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_showADG(adgni);
			_resumeADG(adgni);
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
	
	public static void setDefaultLocationADG(){
		if(noInstance)return;
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_changeLocationADG(adgni , X , Y);
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