using UnityEngine;
using System;
using System.Collections;
using System.Runtime.InteropServices;

public class ADGMonoBehaviour : MonoBehaviour {
	#if UNITY_IPHONE
	[DllImport ("__Internal")]
	protected static extern IntPtr _initADG (IntPtr adgni , string adid , string adtype , float x , float y , string objName , int width , int height , float scale , string horizontal , string vertical, bool enableTest);
	[DllImport ("__Internal")]
	protected static extern void _finishADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _loadADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _resumeADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _pauseADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _showADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _hideADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _changeLocationADG (IntPtr adgni , float x , float y);
	[DllImport ("__Internal")]
	protected static extern void _changeLocationEasyADG (IntPtr adgni , string vertical , string horizontal);
	[DllImport ("__Internal")]
	protected static extern void _setBackgroundColorADG (IntPtr adgni , int red , int green , int blue , int alpha);
	[DllImport ("__Internal")]
	protected static extern IntPtr _initInterADG (IntPtr adgni , string adid , string objName , int backgroundType , int closeButtonType , int span , bool isPercentage , bool isPreventAccidentClick, bool enableTest);
	[DllImport ("__Internal")]
	protected static extern void _loadInterADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _showInterADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _dismissInterADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _finishInterADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern float _getNativeWidthADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern float _getNativeHeightADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _addFANTestDeviceADG(string deviceHash);
	#endif
}