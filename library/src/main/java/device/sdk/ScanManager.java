package device.sdk;

import android.compat.annotation.UnsupportedAppUsage;
import android.graphics.Rect;
import android.util.Log;
import java.io.FileInputStream;

import device.common.ScanConst;
import device.common.DecodeResult;
import device.common.DecodeStateCallback;
import device.common.ExposureSettings;
import device.common.OCRProperty;
import device.common.EngineConfig;
import device.common.StyleConfig;
import device.common.ConditionConfig;
import device.common.ActionConfig;
import device.common.CustomIntentConfig;
import device.common.IScannerService;

import device.sdk.DeviceServer;

public class ScanManager {
    private static final String TAG = ScanManager.class.getSimpleName();
    private static ScanManager mInstance = null;

    @UnsupportedAppUsage
    public static ScanManager getInstance() {
        if (mInstance == null) {
            mInstance = new ScanManager();
        }
        return mInstance;
    }
    @UnsupportedAppUsage
    public ScanManager() {}

	/**
	* This function should be called explicitly before using any other DecoderAPIs.
	* @deprecated No need to call it on Android.
	* @see  device.sdk.ScanManager#aDecodeAPIInit
	*/
	@Deprecated
    @UnsupportedAppUsage
	public void aDecodeAPIInit() {
        try {
		    DeviceServer.getIScannerService().aDecodeAPIInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function deinitializes the DecoderAPIs.
	* @deprecated No need to call it on Android.
	* @see  device.sdk.ScanManager#aDecodeAPIDeinit
	*/
	@Deprecated
    @UnsupportedAppUsage
	public void aDecodeAPIDeinit() {
        try {
            DeviceServer.getIScannerService().aDecodeAPIDeinit();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function returns DecodeApi version.
	* @return  <code>0xMMmmBBrr</code> - (MM=major, mm=minor, BB=bugfix, rr=reserved)
	*/
    @UnsupportedAppUsage
	public int aDecodeAPIGetVersion() {
        try {
		    return DeviceServer.getIScannerService().aDecodeAPIGetVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get the module type of decoder.
	* @return module type values are as below
	* <pre>
	* public final static class ScannerType {
	*	private ScannerType() {
	*	}
	*
	*	public static final int DCD_MODULE_TYPE_UNKNOWN = -1;
	*	public static final int DCD_MODULE_TYPE_NONE    = 0;
	*	public static final int DCD_MODULE_TYPE_IT4000  = 5;
	*	public static final int DCD_MODULE_TYPE_IT4100  = 6;
	*	public static final int DCD_MODULE_TYPE_IT4300  = 7;
	*	public static final int DCD_MODULE_TYPE_IT5100  = 8;
	*	public static final int DCD_MODULE_TYPE_IT5300  = 9;
	*	public static final int DCD_MODULE_TYPE_N5603   = 12;
	*	public static final int DCD_MODULE_TYPE_N5600   = 13;
	*	public static final int DCD_MODULE_TYPE_IS4813  = 14;
	*	public static final int DCD_MODULE_TYPE_HI704A  = 15;
	*	public static final int DCD_MODULE_TYPE_N4313   = 16;
	*	public static final int DCD_MODULE_TYPE_N6603   = 17;
	*	public static final int DCD_MODULE_TYPE_EX25    = 18;
	*	public static final int DCD_MODULE_TYPE_EX30    = 19;
	*	public static final int DCD_MODULE_TYPE_SE955   = 20;
	*	public static final int DCD_MODULE_TYPE_SE4500  = 21;
	*	public static final int DCD_MODULE_TYPE_SE655   = 22;
	*	public static final int DCD_MODULE_TYPE_SE965   = 23;
	*	public static final int DCD_MODULE_TYPE_SE4710  = 24;
	*	public static final int DCD_MODULE_TYPE_UE966   = 25;
	*	public static final int DCD_MODULE_TYPE_CR8000  = 26;
	*	public static final int DCD_MODULE_TYPE_SE4750  = 27;
	*	public static final int DCD_MODULE_TYPE_MDL1500 = 28;
	*	public static final int DCD_MODULE_TYPE_N6703   = 29;
	*	public static final int DCD_MODULE_TYPE_N3601   = 31;
	*	public static final int DCD_MODULE_TYPE_N3603   = 32;
	*	public static final int DCD_MODULE_TYPE_N2600   = 33;
	*	public static final int DCD_MODULE_TYPE_N4603   = 34;
	*}
	* </pre>
	*/
    @UnsupportedAppUsage
	public int aDecodeGetModuleType() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetModuleType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get the module name of decoder.
	* @return  module's name.
	*/
    @UnsupportedAppUsage
	public String aDecodeGetModuleName() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetModuleName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

	/**
	* This function get the module version of decoder.
	* @return  module's version.
	*/
    @UnsupportedAppUsage
	public String aDecodeGetModuleVersion() {
        try {
        	return DeviceServer.getIScannerService().aDecodeGetModuleVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

	/**
	* This function get the API version of decoder, Use for 2D(N660X, N670X, EX30, N2601) Only.
	* @return  API's version.
	*/
    @UnsupportedAppUsage
	public String aDecodeGetAPIVersion() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetAPIVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

	/**
	* This function get the driver version of decoder.
	* @return  driver's version.
	*/
    @UnsupportedAppUsage
	public String aDecodeGetDriverVersion() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetDriverVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

	/**
	* This function get the state Whether 2D barcode support or not.
	* @return  state of Wether 2D barcode support or not, If Nonzero, decoder support 2D barcode.
	*/
    @UnsupportedAppUsage
	public int aDecodeGetSupport2D() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetSupport2D();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get serial number of decoder.
	* @return  scanner's serial.
	*/
    @UnsupportedAppUsage
	public String aDecodeGetSerialNumber() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetSerialNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

	/**
	* This function get focus type of decoder.
	* @return  scanner's focus type.
	*/
    @UnsupportedAppUsage
	public String aDecodeGetFocusType() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetFocusType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

	//----------------------------------------
	// DECODEAPI_CONFIG
	//----------------------------------------

	/**
	* This function do change the setting by OEM default status.(This function mostly use with aDecodeSetDefaultAll function.)
	* @deprecated No need to call it, because this function has been integrated into the aDecodeSetDefaultAll() function.
	* @see device.sdk.ScanManager#aDecodeSetDefaultAll
	*/
	@Deprecated
    @UnsupportedAppUsage
	public void aDecodeOemInit() {
        try {
		    DeviceServer.getIScannerService().aDecodeOemInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function do change the setting by Factory default status.
	*/
    @UnsupportedAppUsage
	public void aDecodeSetDefaultAll() {
        try {
		    DeviceServer.getIScannerService().aDecodeSetDefaultAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function can select the decoder use or not.
	* Actually, some delay is required until ready after scanner enable.
	* If you want to check scanner enable is complete, use DecodeStateCallback.
	* @param enable mean decoder use or not, If Nonzero, decoder is enable.
	* @see device.sdk.ScanManager#aDecodeGetDecodeEnable
	* @see device.common.DecodeStateCallback
	*/
    @UnsupportedAppUsage
	public void aDecodeSetDecodeEnable(int enable) {
        try {
	        DeviceServer.getIScannerService().aDecodeSetDecodeEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function can select the decoder use or not.
	* @param enable mean decoder use or not, If Nonzero, decoder is enable.
	* @param caller means a subject that will be called. Caller enumeration is as below.
	* <pre>
	*	public static final int BY_CAMERA	= 0;
	*	public static final int BY_USER		= 1;
	*	public static final int BY_WEDGE	= 2;
	* </pre>
	* @see device.sdk.ScanManager#aDecodeGetDecodeEnable
	* @hide Internal use only
	*/
    @UnsupportedAppUsage
	public void aDecodeSetDecodeEnableEx(int enable, int caller) {
        try {
	        DeviceServer.getIScannerService().aDecodeSetDecodeEnableEx(enable, caller);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the decoder use or not.
	* @return decoder use state, If greater than 0, enable; If 0, disable; if -2(device.common.ScanConst.STATE_REJECTED_GETTING_VALUE), It is returned when state can not decide during enabling/disabling and suspending/resuming.
	* If you want to check detail state information, use DecodeStateCallback.
	* @see device.sdk.ScanManager#aDecodeSetDecodeEnable
	* @see device.common.DecodeStateCallback
	*/
    @UnsupportedAppUsage
	public int aDecodeGetDecodeEnable() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetDecodeEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to decode result notification type after decoding.
	* @param resultType mean result notification type. Result notification type is as below.
	* <pre>
	* public final static class ResultType {
	*	private ResultType() {
	*	}
	*
	*	public static final int DCD_RESULT_USERMSG   = 0;
	*	public static final int DCD_RESULT_KBDMSG    = 1;
	*	public static final int DCD_RESULT_COPYPASTE = 2;
	*	public static final int DCD_RESULT_EVENT     = 3;
	*	public static final int DCD_RESULT_CUSTOM_INTENT = 4;
	*}
	* </pre>
	* @see device.sdk.ScanManager#aDecodeGetResultType
	*/
    @UnsupportedAppUsage
	public void aDecodeSetResultType(int resultType) {
        try {
		    DeviceServer.getIScannerService().aDecodeSetResultType(resultType);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function is get the decode result notification type after decoding.
	* @return result notification type. Result notification type is as below.
	* <pre>
	* public final static class ResultType {
	*	private ResultType() {
	*	}
	*
	*	public static final int DCD_RESULT_USERMSG   = 0;
	*	public static final int DCD_RESULT_KBDMSG    = 1;
	*	public static final int DCD_RESULT_COPYPASTE = 2;
	*	public static final int DCD_RESULT_EVENT     = 3;
	*	public static final int DCD_RESULT_CUSTOM_INTENT = 4;
	*}
	* </pre>
	* @see device.sdk.ScanManager#aDecodeSetResultType
	*/
    @UnsupportedAppUsage
	public int aDecodeGetResultType() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetResultType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to transmit symbology ID after decoding.
	* @param enable mean transmit symbology ID. If Nonzero, decode result include symbology ID.
	* @see device.sdk.ScanManager#aDecodeGetResultSymIdEnable
	*/
    @UnsupportedAppUsage
	public void aDecodeSetResultSymIdEnable(int enable) {
        try {
		    DeviceServer.getIScannerService().aDecodeSetResultSymIdEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get state of transmit symbology ID option use or not.
	* @return transmit symbology ID option's state.
	* @see device.sdk.ScanManager#aDecodeSetResultSymIdEnable
	*/
    @UnsupportedAppUsage
	public int aDecodeGetResultSymIdEnable() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetResultSymIdEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get the decode result.
	* @param decodeResult mean class of DecodeResult.
	*/
    @UnsupportedAppUsage
	public void aDecodeGetResult(DecodeResult decodeResult) {
        try {
		    DeviceServer.getIScannerService().aDecodeGetResult(decodeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function do decision to prefix use or not.
	* @param enable mean prefix use or not, If prefix is enable then decode result include prefix data.
	* @see device.sdk.ScanManager#aDecodeGetPrefixEnable
	*/
    @UnsupportedAppUsage
	public void aDecodeSetPrefixEnable(int enable) {
        try {
		    DeviceServer.getIScannerService().aDecodeSetPrefixEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get state of prefix option use or not.
	* @return status of prefix option use or not.
	* @see device.sdk.ScanManager#aDecodeSetPrefixEnable
	*/
    @UnsupportedAppUsage
	public int aDecodeGetPrefixEnable() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetPrefixEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to prefix data.
	* @param prefix mean prefix data.
	* @see device.sdk.ScanManager#aDecodeGetPrefix
	*/
    @UnsupportedAppUsage
	public void aDecodeSetPrefix(String prefix) {
        try {
		    DeviceServer.getIScannerService().aDecodeSetPrefix(prefix);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get data of prefix.
	* @return prefix data.
	* @see device.sdk.ScanManager#aDecodeSetPrefix
	*/
    @UnsupportedAppUsage
	public String aDecodeGetPrefix() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetPrefix();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

	/**
	* This function do decision to postfix use or not.
	* @param enable mean postfix use or not, If postfix is enable then decode result include postfix data.
	* @see device.sdk.ScanManager#aDecodeGetPostfixEnable
	*/
    @UnsupportedAppUsage
	public void aDecodeSetPostfixEnable(int enable) {
        try {
		    DeviceServer.getIScannerService().aDecodeSetPostfixEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get state of postfix option use or not.
	* @return status of postfix option use or not.
	* @see device.sdk.ScanManager#aDecodeSetPostfixEnable
	*/
    @UnsupportedAppUsage
	public int aDecodeGetPostfixEnable() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetPostfixEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to postfix data.
	* @param postfix mean postfix data.
	* @see device.sdk.ScanManager#aDecodeGetPostfix
	*/
    @UnsupportedAppUsage
	public void aDecodeSetPostfix(String postfix) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetPostfix(postfix);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get data of postfix.
	* @return postfix data.
	* @see device.sdk.ScanManager#aDecodeSetPostfix
	*/
    @UnsupportedAppUsage
	public String aDecodeGetPostfix() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetPostfix();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

    // [#9934] Added by irene.choi start
    /**
     * This function set string value of charset, Use for 2D scanner only.
     * @param charset means string value of charset.
     * @see device.sdk.ScanManager#aDecodeGetCharset.
     */
    @UnsupportedAppUsage
    public void aDecodeSetCharset(String charset) {
        try {
            DeviceServer.getIScannerService().aDecodeSetCharset(charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function get string value of charset, Use for 2D scanner only.
     * @return string value of charset.
     * @see device.sdk.ScanManager#aDecodeSetCharset.
     */
    @UnsupportedAppUsage
    public String aDecodeGetCharset() {
        try {
            return DeviceServer.getIScannerService().aDecodeGetCharset();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
    }
    // [#9934] Added by irene.choi end

	/**
	* This function do decision to terminator value.
	* @param terminator mean terminator value, Terminator value is as below.
	* <pre>
	* public final static class Terminator {
	*	private Terminator() {
	*	}
	*
	*	public static final int DCD_TERMINATOR_NONE     = 0;
	*	public static final int DCD_TERMINATOR_CRLF     = 1; // @deprecated use DCD_TERMINATOR_LF instead.
	*	public static final int DCD_TERMINATOR_SPACE    = 2;
	*	public static final int DCD_TERMINATOR_TAB      = 3;
	*	public static final int DCD_TERMINATOR_CR       = 4; // @deprecated use DCD_TERMINATOR_LF instead.
	*	public static final int DCD_TERMINATOR_LF       = 5;
	*	public static final int DCD_TERMINATOR_TAB_CRLF = 6; // @deprecated use DCD_TERMINATOR_TAB_LF instead.
	*	public static final int DCD_TERMINATOR_TAB_LF = DCD_TERMINATOR_TAB_CRLF;
	* }
	* </pre>
	* @see device.sdk.ScanManager#aDecodeGetTerminator
	*/
    @UnsupportedAppUsage
	public void aDecodeSetTerminator(int terminator) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetTerminator(terminator);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function is get the terminator value.
	* @return terminator value, Terminator value is as below.
	* <pre>
	* public final static class Terminator {
	*	private Terminator() {
	*	}
	*
	*	public static final int DCD_TERMINATOR_NONE     = 0;
	*	public static final int DCD_TERMINATOR_CRLF     = 1; // @deprecated use DCD_TERMINATOR_LF instead.
	*	public static final int DCD_TERMINATOR_SPACE    = 2;
	*	public static final int DCD_TERMINATOR_TAB      = 3;
	*	public static final int DCD_TERMINATOR_CR       = 4; // @deprecated use DCD_TERMINATOR_LF instead.
	*	public static final int DCD_TERMINATOR_LF       = 5;
	*	public static final int DCD_TERMINATOR_TAB_CRLF = 6; // @deprecated use DCD_TERMINATOR_TAB_LF instead.
	*	public static final int DCD_TERMINATOR_TAB_LF = DCD_TERMINATOR_TAB_CRLF;
	* }
	* </pre>
	* @see device.sdk.ScanManager#aDecodeSetTerminator
	*/
    @UnsupportedAppUsage
	public int aDecodeGetTerminator() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetTerminator();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function can select the extended wedge use or not.
	* @param dwEnable mean extended wedge use or not.
	* @see device.sdk.ScanManager#aDecodeGetPEWEnable.
	*/
    @UnsupportedAppUsage
	public void aDecodeSetPEWEnable(int enable) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetPEWEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get state of the extended wedge use or not.
	* @return status of extended wedge use or not.
	* @see device.sdk.ScanManager#aDecodeSetPEWEnable
	*/
    @UnsupportedAppUsage
	public int aDecodeGetPEWEnable() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetPEWEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get count of styles.
	* @return count of styles.
	*/
    @UnsupportedAppUsage
	public int aDecodeGetStyleTotalCount() {
		try {
    		return DeviceServer.getIScannerService().aDecodeGetStyleTotalCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function modify the style configuration.
	* @param styleInfo mean class of style configuration.
	* @param styleOrder mean style order that you want to modify.
	* @see device.sdk.ScanManager#aDecodeGetStyle.
	*/
    @UnsupportedAppUsage
	public void aDecodeSetStyle(StyleConfig styleInfo, int styleOrder){
		try {
    		DeviceServer.getIScannerService().aDecodeSetStyle(styleInfo, styleOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the style configuration.
	* @param styleInfo mean class of style configuration.
	* @param styleOrder mean style order that you want to get.
	* @see device.sdk.ScanManager#aDecodeSetStyle.
	*/
    @UnsupportedAppUsage
	public void aDecodeGetStyle(StyleConfig styleInfo, int styleOrder) {
		try {
    		DeviceServer.getIScannerService().aDecodeGetStyle(styleInfo, styleOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function add the style configuration to the last order.
	* @param styleInfo mean class of style configuration.
	* @see device.sdk.ScanManager#aDecodeDeleteStyle.
	*/
    @UnsupportedAppUsage
	public void aDecodeAddStyle(StyleConfig styleInfo) {
		try {
    		DeviceServer.getIScannerService().aDecodeAddStyle(styleInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function delete the style configuration.
	* @param dwStyleOrder mean style order that you want to delete.
	* @see device.sdk.ScanManager#aDecodeAddStyle.
	*/
    @UnsupportedAppUsage
	public void aDecodeDeleteStyle(int styleOrder) {
		try {
    		DeviceServer.getIScannerService().aDecodeDeleteStyle(styleOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function modify the condition configuration.
	* @param conditionInfo mean class of condition configuration.
	* @param styleOrder mean style order that you want to modify.
	* @see device.sdk.ScanManager#aDecodeGetCondition.
	*/
    @UnsupportedAppUsage
	public void aDecodeSetCondition(ConditionConfig conditionInfo, int styleOrder) {
		try {
    		DeviceServer.getIScannerService().aDecodeSetCondition(conditionInfo, styleOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the condition configuration.
	* @param conditionInfo mean class of condition configuration.
	* @param styleOrder mean style order that you want to get.
	* @see device.sdk.ScanManager#aDecodeSetCondition.
	*/
    @UnsupportedAppUsage
	public void aDecodeGetCondition(ConditionConfig conditionInfo, int styleOrder) {
		try {
    		DeviceServer.getIScannerService().aDecodeGetCondition(conditionInfo, styleOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function modify the action configuration.
	* @param actionInfo mean class of action configuration.
	* @param styleOrder mean style order that you want to modify.
	* @param actionOrder mean action order that you want to modify.
	* @see device.sdk.ScanManager#aDecodeGetAction.
	*/
    @UnsupportedAppUsage
	public void aDecodeSetAction(ActionConfig actionInfo, int styleOrder, int actionOrder) {
		try {
    		DeviceServer.getIScannerService().aDecodeSetAction(actionInfo, styleOrder, actionOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the action configuration.
	* @param actionInfo mean class of action configuration.
	* @param styleOrder mean style order that you want to get.
	* @param actionOrder mean action order that you want to get.
	* @see device.sdk.ScanManager#aDecodeSetAction.
	*/
    @UnsupportedAppUsage
	public void aDecodeGetAction(ActionConfig actionInfo, int styleOrder, int actionOrder) {
		try {
    		DeviceServer.getIScannerService().aDecodeGetAction(actionInfo, styleOrder, actionOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function add the action configuration to the last order.
	* @param actionInfo mean class of action configuration.
	* @param styleOrder mean style order that you want to add.
	* @see device.sdk.ScanManager#aDecodeDeleteAction.
	*/
    @UnsupportedAppUsage
	public void aDecodeAddAction(ActionConfig actionInfo, int styleOrder) {
		try {
    		DeviceServer.getIScannerService().aDecodeAddAction(actionInfo, styleOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function delete the action configuration.
	* @param styleOrder mean style order that you want to delete.
	* @param actionOrder mean action order that you want to delete.
	* @see device.sdk.ScanManager#aDecodeAddAction.
	*/
    @UnsupportedAppUsage
	public void aDecodeDeleteAction(int styleOrder, int actionOrder) {
		try {
    		DeviceServer.getIScannerService().aDecodeDeleteAction(styleOrder, actionOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the action name corresponding to the action index.
	* @param actionIndex mean action index that you want to get the name.
	*/
    @UnsupportedAppUsage
	public String aDecodeGetActionName(int actionIndex) {
		try {
            return DeviceServer.getIScannerService().aDecodeGetActionName(actionIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

	/**
	* This function do decision to trigger mode.
	* @param triggerMode mean trigger mode, trigger mode is as below.
	* <pre>
	* public final static class TriggerMode {
	*	private TriggerMode() {
	*	}
	*
	*	public static final int DCD_TRIGGER_MODE_ONESHOT    = 0;
	*	public static final int DCD_TRIGGER_MODE_AUTO       = 1;
	*	public static final int DCD_TRIGGER_MODE_CONTINUOUS = 2;
	* }
	* </pre>
	* @see device.sdk.ScanManager#aDecodeGetTriggerMode
	*/
    @UnsupportedAppUsage
	public void aDecodeSetTriggerMode(int triggerMode) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetTriggerMode(triggerMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function is get the trigger mode.
	* @return trigger mode, trigger mode is as below.
	* <pre>
	* public final static class TriggerMode {
	*	private TriggerMode() {
	*	}
	*
	*	public static final int DCD_TRIGGER_MODE_ONESHOT    = 0;
	*	public static final int DCD_TRIGGER_MODE_AUTO       = 1;
	*	public static final int DCD_TRIGGER_MODE_CONTINUOUS = 2;
	* }
	* </pre>
	* @see device.sdk.ScanManager#aDecodeSetTriggerMode
	*/
    @UnsupportedAppUsage
	public int aDecodeGetTriggerMode() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetTriggerMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to auto scan trigger interval time.
	* @param triggerInterval mean auto scan trigger interval time, Base is milli-second.
	* @see device.sdk.ScanManager#aDecodeGetTriggerInterval
	*/
    @UnsupportedAppUsage
	public void aDecodeSetTriggerInterval(int triggerInterval) {
        try {
		    DeviceServer.getIScannerService().aDecodeSetTriggerInterval(triggerInterval);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function is get the auto scan trigger interval time.
	* @return auto scan trigger interval time, Base is milli-second.
	* @see device.sdk.ScanManager#aDecodeSetTriggerInterval
	*/
    @UnsupportedAppUsage
	public int aDecodeGetTriggerInterval() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetTriggerInterval();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do trigger on or off.
	* @param triggerOn mean trigger on or off, If set 1, trigger on.
	*/
    @UnsupportedAppUsage
	public void aDecodeSetTriggerOn(int triggerOn) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetTriggerOn(triggerOn);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function do decision to trigger use or not.
	* @param enable mean trigger use or not, If Nonzero, can control trigger.
	* @see device.sdk.ScanManager#aDecodeGetTriggerEnable
	*/
    @UnsupportedAppUsage
	public void aDecodeSetTriggerEnable(int enable) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetTriggerEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the trigger use or not.
	* @return trigger use or not, If Nonzero, can control trigger.
	* @see device.sdk.ScanManager#aDecodeSetTriggerEnable
	*/
    @UnsupportedAppUsage
	public int aDecodeGetTriggerEnable() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetTriggerEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to trigger timeout value.
	* @param triggerTimeout mean trigger timeout value, Base is milli-second.
	* @see device.sdk.ScanManager#aDecodeGetTriggerTimeout
	*/
    @UnsupportedAppUsage
	public void aDecodeSetTriggerTimeout(int triggerTimeout) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetTriggerTimeout(triggerTimeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the trigger timeout value.
	* @return trigger timeout value, Base is milli-second.
	* @see device.sdk.ScanManager#aDecodeSetTriggerTimeout
	*/
    @UnsupportedAppUsage
	public int aDecodeGetTriggerTimeout() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetTriggerTimeout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to LED notification use or not.
	* @param enable mean LED notification use or not.
	* @see device.sdk.ScanManager#aDecodeGetLedEnable
	*/
    @UnsupportedAppUsage
	public void aDecodeSetLedEnable(int enable) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetLedEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the LED notification use or not.
	* @return LED notification use or not.
	* @see device.sdk.ScanManager#aDecodeSetTriggerTimeout
	*/
    @UnsupportedAppUsage
	public int aDecodeGetLedEnable() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetLedEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to Beep notification use or not.
	* @param enable mean Beep notification use or not.
	* @see device.sdk.ScanManager#aDecodeGetBeepEnable
	*/
    @UnsupportedAppUsage
	public void aDecodeSetBeepEnable(int enable) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetBeepEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the Beep notification use or not.
	* @return Beep notification use or not.
	* @see device.sdk.ScanManager#aDecodeSetBeepEnable
	*/
    @UnsupportedAppUsage
	public int aDecodeGetBeepEnable() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetBeepEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to Beep success file name.
	* @param beepSuccessFile mean Beep success file name.
	* @see device.sdk.ScanManager#aDecodeGetBeepSuccessFile
	*/
    @UnsupportedAppUsage
	public void aDecodeSetBeepSuccessFile(String beepSuccessFile) {
        FileInputStream input = null;
        try {
            if (beepSuccessFile != null && !beepSuccessFile.isEmpty()) {
                input = new FileInputStream(beepSuccessFile);
                DeviceServer.getIScannerService().aDecodeOpenBeepFile(ScanConst.FINAL_GOOD_BEEP_FILE_PATH);
                byte[] buffer = new byte[5120];
                while (input.read(buffer, 0, buffer.length) != -1) {
                    DeviceServer.getIScannerService().aDecodeWriteBeepFile(buffer);
                }
            }
    		DeviceServer.getIScannerService().aDecodeSetBeepSuccessFile(beepSuccessFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) input.close();
                DeviceServer.getIScannerService().aDecodeCloseBeepFile();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
	}

	/**
	* This function get the Beep success file name.
	* @return Beep success file name.
	* @see device.sdk.ScanManager#aDecodeSetBeepSuccessFile
	*/
    @UnsupportedAppUsage
	public String aDecodeGetBeepSuccessFile() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetBeepSuccessFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

	/**
	* This function do decision to Beep fail file name.
	* @param beepFailFile mean Beep fail file name.
	* @see device.sdk.ScanManager#aDecodeGetBeepFailFile
	*/
    @UnsupportedAppUsage
	public void aDecodeSetBeepFailFile(String beepFailFile) {
        FileInputStream input = null;
        try {
            if (beepFailFile != null && !beepFailFile.isEmpty()) {
                input = new FileInputStream(beepFailFile);
                DeviceServer.getIScannerService().aDecodeOpenBeepFile(ScanConst.FINAL_BAD_BEEP_FILE_PATH);
                byte[] buffer = new byte[5120];
                while (input.read(buffer, 0, buffer.length) != -1) {
                    DeviceServer.getIScannerService().aDecodeWriteBeepFile(buffer);
                }
            }
    		DeviceServer.getIScannerService().aDecodeSetBeepFailFile(beepFailFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) input.close();
                DeviceServer.getIScannerService().aDecodeCloseBeepFile();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
	}

	/**
	* This function get the Beep fail file name.
	* @return Beep fail file name.
	* @see device.sdk.ScanManager#aDecodeSetBeepFailFile
	*/
    @UnsupportedAppUsage
	public String aDecodeGetBeepFailFile() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetBeepFailFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

	/**
	* This function do decision to Vibrator notification use or not.
	* @param enable mean Vibrator notification use or not.
	* @see device.sdk.ScanManager#aDecodeGetVibratorEnable
	*/
    @UnsupportedAppUsage
	public void aDecodeSetVibratorEnable(int enable) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetVibratorEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the Vibrator notification use or not.
	* @return Vibrator notification use or not.
	* @see device.sdk.ScanManager#aDecodeSetVibratorEnable
	*/
    @UnsupportedAppUsage
	public int aDecodeGetVibratorEnable() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetVibratorEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to Vibration time after decode success.
	* @param vibratorSuccessInterval mean Vibration time after decode success.(Base is milli-second)
	* @see device.sdk.ScanManager#aDecodeGetVibratorSuccessInterval
	*/
    @UnsupportedAppUsage
	public void aDecodeSetVibratorSuccessInterval(int vibratorSuccessInterval) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetVibratorSuccessInterval(vibratorSuccessInterval);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the Vibration time after decode success.
	* @return Vibration time after decode success.(Base is milli-second)
	* @see device.sdk.ScanManager#aDecodeSetVibratorSuccessInterval
	*/
    @UnsupportedAppUsage
	public int aDecodeGetVibratorSuccessInterval() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetVibratorSuccessInterval();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to Vibration time after decode fail.
	* @param vibratorFailInterval mean Vibration time after decode fail.(Base is milli-second)
	* @see device.sdk.ScanManager#aDecodeGetVibratorFailInterval
	*/
    @UnsupportedAppUsage
	public void aDecodeSetVibratorFailInterval(int vibratorFailInterval) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetVibratorFailInterval(vibratorFailInterval);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the Vibration time after decode fail.
	* @return Vibration time after decode fail.(Base is milli-second)
	* @see device.sdk.ScanManager#aDecodeSetVibratorFailInterval
	*/
    @UnsupportedAppUsage
	public int aDecodeGetVibratorFailInterval() {
        try {
	    	return DeviceServer.getIScannerService().aDecodeGetVibratorFailInterval();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	//----------------------------------------
	// DECODEAPI_SYM_PROP
	//----------------------------------------

	/**
	* This function do decision to each symbology use or not.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @param enable mean symbology use or not.
	* @see device.common.ScanConst.SymbologyID
	* @see device.sdk.ScanManager#aDecodeSymGetEnable
	*/
    @UnsupportedAppUsage
	public void aDecodeSymSetEnable(int symIndex, int enable) {
        try {
    		DeviceServer.getIScannerService().aDecodeSymSetEnable(symIndex, enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the each symbology use or not.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @return symbology use or not
	* @see device.common.ScanConst.SymbologyID
	* @see device.sdk.ScanManager#aDecodeSymSetEnable
	*/
    @UnsupportedAppUsage
	public int aDecodeSymGetEnable(int symIndex) {
        try {
    		return DeviceServer.getIScannerService().aDecodeSymGetEnable(symIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get the name of each symbology.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @return name of symbology.
	* @see device.common.ScanConst.SymbologyID
	*/
    @UnsupportedAppUsage
	public String aDecodeSymGetName(int symIndex) {
        try {
    		return DeviceServer.getIScannerService().aDecodeSymGetName(symIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

	/**
	* This function get the each symbology use range or not.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @return symbology use range or not, If Nonzero, this symbology support range.
	* @see device.common.ScanConst.SymbologyID
	*/
    @UnsupportedAppUsage
	public int aDecodeSymGetMinMaxEnable(int symIndex) {
        try {
    		return DeviceServer.getIScannerService().aDecodeSymGetMinMaxEnable(symIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to range of each symbology.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @param min mean min value of range.
	* @see device.common.ScanConst.SymbologyID
	* @see device.sdk.ScanManager#aDecodeSymGetMin
	*/
    @UnsupportedAppUsage
	public void aDecodeSymSetMin(int symIndex, int min) {
        try {
    		DeviceServer.getIScannerService().aDecodeSymSetMin(symIndex, min);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the each range of each symbology
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @return min value of range.
	* @see device.common.ScanConst.SymbologyID
	* @see device.sdk.ScanManager#aDecodeSymSetMin
	*/
    @UnsupportedAppUsage
	public int aDecodeSymGetMin(int symIndex) {
        try {
    		return DeviceServer.getIScannerService().aDecodeSymGetMin(symIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to range of each symbology.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @param max mean max value of range.
	* @see device.common.ScanConst.SymbologyID
	* @see device.sdk.ScanManager#aDecodeSymGetMax
	*/
    @UnsupportedAppUsage
	public void aDecodeSymSetMax(int symIndex, int max) {
        try {
    		DeviceServer.getIScannerService().aDecodeSymSetMax(symIndex, max);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the each range of each symbology
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @return max value of range.
	* @see device.common.ScanConst.SymbologyID
	* @see device.sdk.ScanManager#aDecodeSymSetMax
	*/
    @UnsupportedAppUsage
	public int aDecodeSymGetMax(int symIndex) {
        try {
    		return DeviceServer.getIScannerService().aDecodeSymGetMax(symIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get the each symbology's default range
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @return default min value of range.
	* @see device.common.ScanConst.SymbologyID
	*/
    @UnsupportedAppUsage
	public int aDecodeSymGetDefaultMin(int symIndex) {
        try {
    		return DeviceServer.getIScannerService().aDecodeSymGetDefaultMin(symIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get the each symbology's default range
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @return default max value of range.
	* @see device.common.ScanConst.SymbologyID
	*/
    @UnsupportedAppUsage
	public int aDecodeSymGetDefaultMax(int symIndex) {
        try {
    		return DeviceServer.getIScannerService().aDecodeSymGetDefaultMax(symIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get the each symbology's range
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @return min value of range.
	* @see device.common.ScanConst.SymbologyID
	*/
    @UnsupportedAppUsage
	public int aDecodeSymGetRangeMin(int symIndex) {
        try {
    		return DeviceServer.getIScannerService().aDecodeSymGetRangeMin(symIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get the each symbology's range
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @return max value of range.
	* @see device.common.ScanConst.SymbologyID
	*/
    @UnsupportedAppUsage
	public int aDecodeSymGetRangeMax(int symIndex) {
        try {
    		return DeviceServer.getIScannerService().aDecodeSymGetRangeMax(symIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to ID of each symbology.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @param symId mean ID of each symbology.
	* @see device.common.ScanConst.SymbologyID
	* @see device.sdk.ScanManager#aDecodeSymGetSymId
	*/
    @UnsupportedAppUsage
	public void aDecodeSymSetSymId(int symIndex, char symId) {
        try {
    		DeviceServer.getIScannerService().aDecodeSymSetSymId(symIndex, symId);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the ID of each symbology.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @return ID of each symbology.
	* @see device.common.ScanConst.SymbologyID
	* @see device.sdk.ScanManager#aDecodeSymSetSymId
	*/
    @UnsupportedAppUsage
	public char aDecodeSymGetSymId(int symIndex) {
        try {
		    return DeviceServer.getIScannerService().aDecodeSymGetSymId(symIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
	}

	/**
	* This function get the propertys count of each symbology.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @return propertys count of each symbology.
	* @see device.common.ScanConst.SymbologyID
	*/
    @UnsupportedAppUsage
	public int aDecodeSymGetLocalPropCount(int symIndex) {
        try {
    		return DeviceServer.getIScannerService().aDecodeSymGetLocalPropCount(symIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get the name of each property.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @param localPropIndex mean index of each property.
	* @return name of each property.
	* @see device.common.ScanConst.SymbologyID
	*/
    @UnsupportedAppUsage
	public String aDecodeSymGetLocalPropName(int symIndex, int localPropIndex) {
        try {
		    return DeviceServer.getIScannerService().aDecodeSymGetLocalPropName(symIndex, localPropIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
	}

	/**
	* This function get the chaining number of each property.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @param localPropIndex mean index of each property.
	* @return name of each property.
	* @see device.common.ScanConst.SymbologyID
	*/
    @UnsupportedAppUsage
	public int aDecodeSymGetLocalPropChain(int symIndex, int localPropIndex) {
        try {
		    return DeviceServer.getIScannerService().aDecodeSymGetLocalPropChain(symIndex, localPropIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
	}

	/**
	* This function do decision to each property use or not.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @param localPropIndex mean index of each property.
	* @param enable mean each property use or not.
	* @see device.common.ScanConst.SymbologyID
	* @see device.sdk.ScanManager#aDecodeSymGetLocalPropEnable
	*/
    @UnsupportedAppUsage
	public void aDecodeSymSetLocalPropEnable(int symIndex, int localPropIndex, int enable) {
        try {
    		DeviceServer.getIScannerService().aDecodeSymSetLocalPropEnable(symIndex, localPropIndex, enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the each each property use or not.
	* @param symIndex mean symbology index. See the SymbologyID class.
	* @param localPropIndex mean index of each property.
	* @return each property use or not.
	* @see device.common.ScanConst.SymbologyID
	* @see device.sdk.ScanManager#aDecodeSymSetLocalPropEnable
	*/
    @UnsupportedAppUsage
	public int aDecodeSymGetLocalPropEnable(int symIndex, int localPropIndex) {
        try {
    		return DeviceServer.getIScannerService().aDecodeSymGetLocalPropEnable(symIndex, localPropIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to clock mode of 2D scanner(N560X), Use for 2D(N560X) Only.
	* @deprecated Not supported on Andoird.
	* @param enable mean clock mode is 24MHz.
	* @see device.sdk.ScanManager#aDecodeGetClockMode24Mhz
	*/
	@Deprecated
    @UnsupportedAppUsage
	public void aDecodeSetClockMode24Mhz(int enable) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetClockMode24Mhz(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function do decision to clock mode of 2D scanner(N560X), Use for 2D(N560X) Only.
	* @deprecated Not supported on Andoird.
	* @return clock mode of 2D scanner(N560X), IF Nonzero, clock mode is 24MHz.
	* @see device.sdk.ScanManager#aDecodeSetClockMode24Mhz
	*/
	@Deprecated
    @UnsupportedAppUsage
	public int aDecodeGetClockMode24Mhz() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetClockMode24Mhz();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to exposure settings, Use for 2D(N660X, N670X, EX30, N2601) Only.
	* @param exposureSetting mean class of ExposureSettings.
	* @see device.sdk.ScanManager#aDecodeGetExposureSettings
	*/
    @UnsupportedAppUsage
	public void aDecodeSetExposureSettings(ExposureSettings exposureSetting) {
        try {
        	DeviceServer.getIScannerService().aDecodeSetExposureSettings(exposureSetting);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the exposure settings, Use for 2D(N660X, N670X, EX30, N2601) Only.
	* @param exposureSetting mean class of ExposureSettings.
	* @see device.sdk.ScanManager#aDecodeSetExposureSettings
	*/
    @UnsupportedAppUsage
	public void aDecodeGetExposureSettings(ExposureSettings exposureSetting) {
        try {
    		DeviceServer.getIScannerService().aDecodeGetExposureSettings(exposureSetting);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function do decision to OCR Templates, Use for 2D scanner Only.
	* @param OCRTemplates mean class of OCRProperty.
	* @see device.sdk.ScanManager#aDecodeGetOCRTemplates
	*/
    @UnsupportedAppUsage
	public void aDecodeSetOCRTemplates(OCRProperty OCRTemplates) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetOCRTemplates(OCRTemplates);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the OCR Templates, Use for 2D scanner Only.
	* @param OCRTemplates mean class of OCRProperty.
	* @see device.sdk.ScanManager#aDecodeGetOCRTemplates
	*/
    @UnsupportedAppUsage
	public void aDecodeGetOCRTemplates(OCRProperty OCRTemplates) {
        try {
    		DeviceServer.getIScannerService().aDecodeGetOCRTemplates(OCRTemplates);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	//----------------------------------------
	// DECODEAPI_IMAGE
	//----------------------------------------

	/**
	* This function is ready for image preview, Use for 2D scanner only.
	* @return Whether succeed or not.
	*/
    @UnsupportedAppUsage
	public boolean aDecodeImageStreamInit() {
        try {
    		return DeviceServer.getIScannerService().aDecodeImageStreamInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	/**
	* This function is ready for image preview with expansion options.
	* Supported scanner types : N660X, N670x, EX30, N2601
	* @param imgRect mean region of the preview image.
	* @param subsampleNumber mean number of interpolate pixel for every other line.
	* This value available 1(all resolution), 2(1/4 resolution), 4(1/16 resolution) only.
	* @return Whether succeed or not.
	*/
    @UnsupportedAppUsage
	public boolean aDecodeImageStreamInitEx(Rect imgRect, int subsampleNumber) {
        try {
    		return DeviceServer.getIScannerService().aDecodeImageStreamInitEx(imgRect, subsampleNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	/**
	* This function is start of image preview, Use for 2D scanner only.
	* @return Whether succeed or not.
	*/
    @UnsupportedAppUsage
	public boolean aDecodeImageStreamStart() {
        try {
    		return DeviceServer.getIScannerService().aDecodeImageStreamStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	/**
	* This function is maintenance to continuous image preview, Use for 2D scanner only.
	* @param buffer mean buffer for image data.
	* @return size of image data. If return value is -1, It means buffer size mismatch.
	*/
    @UnsupportedAppUsage
	public int aDecodeImageStreamRead(byte[] buffer) {
        try {
		    return DeviceServer.getIScannerService().aDecodeImageStreamRead(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function is stop the image preview, Use for 2D scanner Only.
	* @return Whether succeed or not.
	*/
    @UnsupportedAppUsage
	public boolean aDecodeImageStreamStop() {
        try {
    		return DeviceServer.getIScannerService().aDecodeImageStreamStop();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	/** @deprecated Not supported on Andoird.	*/
	@Deprecated
    @UnsupportedAppUsage
	public int aDecodeImageStreamGetWidth() {
        try {
    		return DeviceServer.getIScannerService().aDecodeImageStreamGetWidth();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/** @deprecated Not supported on Andoird.	*/
	@Deprecated
    @UnsupportedAppUsage
	public int aDecodeImageStreamGetHeight() {
        try {
    		return DeviceServer.getIScannerService().aDecodeImageStreamGetHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get the image data, Use for 2D scanner Only.
	* @param buffer mean buffer for captured image data.
	* @return size of captured image data. If return value is -1, It means buffer size mismatch.
	*/
    @UnsupportedAppUsage
	public int aDecodeImageCapture(byte[] buffer) {
        try {
    		return DeviceServer.getIScannerService().aDecodeImageCapture(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/** @deprecated Not supported on Andoird.	*/
	@Deprecated
    @UnsupportedAppUsage
	public int aDecodeImageCaptureGetWidth() {
        try {
    		return DeviceServer.getIScannerService().aDecodeImageCaptureGetWidth();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/** @deprecated Not supported on Andoird.	*/
	@Deprecated
    @UnsupportedAppUsage
	public int aDecodeImageCaptureGetHeight() {
        try {
    		return DeviceServer.getIScannerService().aDecodeImageCaptureGetHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
     * @hide
     * Internal use only
     */
    @UnsupportedAppUsage
    public void aDecodeImageTestMode(boolean enabled) {
		try {
			DeviceServer.getIScannerService().aDecodeImageTestMode(enabled);
        } catch (Exception e) {
            e.printStackTrace();
		}
	}

	/**
	* This function do decision to image mode use or not, Use for 2D(N660X, N670X, EX30, N2601) Only.
	* @param imageMode mean image mode use or not.
	* @return Whether succeed or not.
	* @see device.sdk.ScanManager#aDecodeGetScanImageMode
	*/
    @UnsupportedAppUsage
	public boolean aDecodeSetScanImageMode(int imageMode) {
        try {
		    return DeviceServer.getIScannerService().aDecodeSetScanImageMode(imageMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	/**
	* This function get the image mode use or not, Use for 2D(N660X, N670X, EX30, N2601) Only.
	* @return image mode use or not. If Nonzero, image mode use.
	* @see device.sdk.ScanManager#aDecodeGetScanImageMode
	*/
    @UnsupportedAppUsage
	public int aDecodeGetScanImageMode() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetScanImageMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to center window mode use or not, Use for 2D scanner Only.
	* @param enable mean center window mode use or not.
	* @see device.sdk.ScanManager#aDecodeGetCenterWindowEnable
	*/
    @UnsupportedAppUsage
	public void aDecodeSetCenterWindowEnable(int enable) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetCenterWindowEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the center window mode use or not, Use for 2D scanner Only.
	* @return center window mode use or not. If Nonzero, center window mode use.
	* @see device.sdk.ScanManager#aDecodeSetCenterWindowEnable
	*/
    @UnsupportedAppUsage
	public int aDecodeGetCenterWindowEnable() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetCenterWindowEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	 * This function set the center window tolerance. Use for 2D(N660X, N670X, EX30, N2601) Only.
	 * @param tolerance means center window tolerance level (Range : 0 ~ 100).
	 * @see device.sdk.ScanManager#aDecodeGetCenterWindowTolerance
	 */
    @UnsupportedAppUsage
	public void aDecodeSetCenterWindowTolerance(int tolerance) {
		try {
    		DeviceServer.getIScannerService().aDecodeSetCenterWindowTolerance(tolerance);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * This function get the center window tolerance. Use for 2D(N660X, N670X, EX30, N2601) Only.
	 * @return Center window tolerance level.
	 * @see device.sdk.ScanManager#aDecodeSetCenterWindowTolerance
	 */
    @UnsupportedAppUsage
	public int aDecodeGetCenterWindowTolerance() {
		try {
    		return DeviceServer.getIScannerService().aDecodeGetCenterWindowTolerance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do select to decode mode, Use for 2D(N660X, N670X, EX30, N2601) Only.
	* @param decodeMode mean decode mode value, decode mode value is as below.
	* <pre>
	* public final static class DecodeMode {
	*	private DecodeMode() {
	*	}
	*
	*	public static final int DCD_DECODE_MODE_STANDARD   = 0;
	*	public static final int DCD_DECODE_MODE_QUICK_OMNI = 1; // @deprecated
	*	public static final int DCD_DECODE_MODE_PRIORITY_2D = 1;
	*	public static final int DCD_DECODE_MODE_FIXED_FOCUS = 2;
	*}
	* </pre>
	* @see device.sdk.ScanManager#aDecodeGetDecodeMode
	*/
    @UnsupportedAppUsage
	public void aDecodeSetDecodeMode(int decodeMode) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetDecodeMode(decodeMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the decode mode value, Use for 2D(N660X, N670X, EX30, N2601) Only.
	* @return decode mode value, decode mode value is as below.
	* <pre>
	* public final static class DecodeMode {
	*	private DecodeMode() {
	*	}
	*
	*	public static final int DCD_DECODE_MODE_STANDARD   = 0;
	*	public static final int DCD_DECODE_MODE_QUICK_OMNI = 1; // @deprecated
	*	public static final int DCD_DECODE_MODE_PRIORITY_2D = 1;
	*	public static final int DCD_DECODE_MODE_FIXED_FOCUS = 2;
	*}
	* </pre>
	* @see device.sdk.ScanManager#aDecodeSetDecodeMode
	*/
    @UnsupportedAppUsage
	public int aDecodeGetDecodeMode() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetDecodeMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to decode delay use or not, Use for 2D(N660X, N670X, EX30, N2601, SE47XX) Only.
	* @param delayMillisecond mean decode delay time, IF dwDelayMillisecond is 0, not use decode delay.
	* @see device.sdk.ScanManager#aDecodeGetDecodeDelay
	*/
    @UnsupportedAppUsage
	public void aDecodeSetDecodeDelay(int delayMillisecond) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetDecodeDelay(delayMillisecond);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the decode delay use or not, Use for 2D(N660X, N670X, EX30, N2601, SE47XX)) Only.
	* @return delayMillisecond mean decode delay time, IF dwDelayMillisecond is 0, not use decode delay.
	* @see device.sdk.ScanManager#aDecodeSetDecodeDelay
	*/
    @UnsupportedAppUsage
	public int aDecodeGetDecodeDelay() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetDecodeDelay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to Imager light mode, Use for 2D scanner Only.
	* @param lightMode mean Imager light mode. Light mode value is as below.
	* <pre>
	* public final static class LightMode {
	*	private LightMode() {
	*	}
	*
	*	public static final int DCD_LIGHT_MODE_OFF      = 0;
	*	public static final int DCD_LIGHT_MODE_ILLUM_ON = 1;
	*	public static final int DCD_LIGHT_MODE_AIM_ON   = 2;
	*	public static final int DCD_LIGHT_MODE_ON       = 3;
	*}
	* </pre>
	* @see device.sdk.ScanManager#aDecodeImageGetLightMode
	*/
    @UnsupportedAppUsage
	public void aDecodeImageSetLightMode(int lightMode) {
        try {
    		DeviceServer.getIScannerService().aDecodeImageSetLightMode(lightMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the Imager light mode, Use for 2D scanner Only.
	* @return Imager light mode. light mode value is as below.
	* <pre>
	* public final static class LightMode {
	*	private LightMode() {
	*	}
	*
	*	public static final int DCD_LIGHT_MODE_OFF      = 0;
	*	public static final int DCD_LIGHT_MODE_ILLUM_ON = 1;
	*	public static final int DCD_LIGHT_MODE_AIM_ON   = 2;
	*	public static final int DCD_LIGHT_MODE_ON       = 3;
	*}
	* </pre>
	* @see device.sdk.ScanManager#aDecodeImageSetLightMode
	*/
    @UnsupportedAppUsage
	public int aDecodeImageGetLightMode() {
        try {
    		return DeviceServer.getIScannerService().aDecodeImageGetLightMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

    /**
    * This function do decision to aimer blink use or not when decoding or preview, Use for N3603 scanner Only.
    * @param enable mean aimer blink use or not.
    * @see device.sdk.ScanManager#aDecodeGetAimerBlinkEnable
    */
    @UnsupportedAppUsage
    public void aDecodeSetAimerBlinkEnable(int enable) {
        try {
            DeviceServer.getIScannerService().aDecodeSetAimerBlinkEnable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * This function get the aimer blink use or not when decoding or preview, Use for N3603 scanner Only.
    * @return aimer blink use or not. If Nonzero, aimer blink use.
    * @see device.sdk.ScanManager#aDecodeSetAimerBlinkEnable
    */
    @UnsupportedAppUsage
    public int aDecodeGetAimerBlinkEnable() {
        try {
            return DeviceServer.getIScannerService().aDecodeGetAimerBlinkEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

	/**
	* This function do decision to Aimer use or not, Use for 2D scanner Only.
	* @param aimOn mean Aimer use or not.
	* @see device.sdk.ScanManager#aDecodeGetAimOn
	*/
    @UnsupportedAppUsage
	public void aDecodeSetAimOn(int aimOn) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetAimOn(aimOn);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the Aimer use or not, Use for 2D scanner Only.
	* @return Aimer use or not. IF Nonzero, aimer use.
	* @see device.sdk.ScanManager#aDecodeSetAimOn
	* @deprecated Use {@link #aDecodeImageGetLightMode()} instead
	*/
	@Deprecated
    @UnsupportedAppUsage
	public int aDecodeGetAimOn() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetAimOn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function do decision to Illumination use or not, Use for 2D scanner Only.
	* @param illumOn mean Illumination use or not.
	* @see device.sdk.ScanManager#aDecodeGetIllumOn
	*/
    @UnsupportedAppUsage
	public void aDecodeSetIllumOn(int illumOn) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetIllumOn(illumOn);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the Illumination use or not, Use for 2D scanner Only.
	* @return Illumination use or not. IF Nonzero, illumination use.
	* @see device.sdk.ScanManager#aDecodeSetIllumOn
	* @deprecated Use {@link #aDecodeImageGetLightMode()} instead
	*/
	@Deprecated
    @UnsupportedAppUsage
	public int aDecodeGetIllumOn() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetIllumOn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function set the illumination level, Use for 2D scanner only. (SE47XX)
	* @param level mean illumination level. Level value is from 0 to 10.
	* @see device.sdk.ScanManager#aDecodeGetIllumLevel
	* @deprecated
	*/
	@Deprecated
    @UnsupportedAppUsage
	public void aDecodeSetIllumLevel(int level) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetIllumLevel(level);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the illumination level, Use for 2D scanner only. (SE47XX)
	* @return illumination level value. Default is 5. (0..10)
	* @see device.sdk.ScanManager#aDecodeSetIllumLevel
	* @deprecated
	*/
	@Deprecated
    @UnsupportedAppUsage
	public int aDecodeGetIllumLevel() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetIllumLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function get the scan engine configure, Use for 2D scanner only. (N660X, N670X, EX30, N2601)
	* @param enginConfig class of EngineConfig.
	*/
    @UnsupportedAppUsage
	public void aDecodeGetEngineConfig(EngineConfig enginConfig) {
        try {
    		DeviceServer.getIScannerService().aDecodeGetEngineConfig(enginConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * This function can set the linear security level value.
	 * Supported scanner types : SE965, SE965HP, MDL1500
	 * @param linearSecurityLevel mean linear security level value.
	 * <pre>
	 * public final static class LinearSecurityLevel {
	 *	private LinearSecurityLevel() {}
	 *	public static final int LEVEL_1 = 1;
	 *	public static final int LEVEL_2 = 2;
	 *	public static final int LEVEL_3 = 3;
	 *	public static final int LEVEL_4 = 4;
	 * }
	 * </pre>
	 * @see device.sdk.ScanManager#aDecodeGetLinearSecurityLevel
	 */
    @UnsupportedAppUsage
	public void aDecodeSetLinearSecurityLevel(int linearSecurityLevel) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetLinearSecurityLevel(linearSecurityLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * This function get the linear security level value.
	 * Supported scanner types : SE965, SE965HP, MDL1500
	 * @return Linear security level value, values are as below.
	 * <pre>
	 * public final static class LinearSecurityLevel {
	 *	private LinearSecurityLevel() {}
	 *	public static final int LEVEL_1 = 1;
	 *	public static final int LEVEL_2 = 2;
	 *	public static final int LEVEL_3 = 3;
	 *	public static final int LEVEL_4 = 4;
	 * }
	 * </pre>
	 * @see device.sdk.ScanManager#aDecodeSetLinearSecurityLevel
	 */
    @UnsupportedAppUsage
	public int aDecodeGetLinearSecurityLevel() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetLinearSecurityLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	 * This function can set Bi-directional mode.
	 * Supported scanner types : SE965, SE965HP, MDL1500
	 * @param bidirectionalMode mean Bi-directional mode use or not.
	 * @see device.sdk.ScanManager#aDecodeGetBidirectionalMode
	 */
    @UnsupportedAppUsage
	public void aDecodeSetBidirectionalMode(int bidirectionalMode) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetBidirectionalMode(bidirectionalMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * This function get Bi-directional mode.
	 * Supported scanner types : SE965, SE965HP, MDL1500
	 * @return Bi-directional mode use or not. IF Nonzero, Bi-directional mode use.
	 * @see device.sdk.ScanManager#aDecodeSetBidirectionalMode
	 */
    @UnsupportedAppUsage
	public int aDecodeGetBidirectionalMode() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetBidirectionalMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
     * This function sets power save mode.
     * Supported scanner types : N4313, SE965, SE965HP, N660X, N670X, EX30, N2601, EX25, MDL1500
     * @param powerSaveMode mean power save mode value, power save mode value is as below.(N4313 Only)
     * <pre>
     * public final static class PowerSaveMode {
     *  private PowerSaveMode() {}
     *  public static final int POWER_SAVE_OFF = 0;
     *  public static final int POWER_SAVE_SLEEP = 1;
     *  public static final int POWER_SAVE_HIBERNATE = 2;
     * }
     * </pre>
     * powerSaveMode mean power save mode use or not. If Nonzero, power save mode use.(Other scanners)
     * @see device.sdk.ScanManager#aDecodeGetPowerSaveMode
     */
    @UnsupportedAppUsage
	public void aDecodeSetPowerSaveMode(int powerSaveMode) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetPowerSaveMode(powerSaveMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
     * This function gets power save mode.
     * Supported scanner types : N4313, SE965, SE965HP, N660X, N670X, EX30, N2601, EX25, MDL1500
     * @return Power save mode value, power save mode value is as below. (N4313 Only)
     * <pre>
     * public final static class PowerSaveMode {
     *  private PowerSaveMode() {}
     *  public static final int POWER_SAVE_OFF = 0;
     *  public static final int POWER_SAVE_SLEEP = 1;
     *  public static final int POWER_SAVE_HIBERNATE = 2;
     * }
     * </pre>
     * powerSaveMode mean power save mode use or not. If Nonzero, power save mode use.(Other scanners)
     * @see device.sdk.ScanManager#aDecodeSetPowerSaveMode
     */
    @UnsupportedAppUsage
	public int aDecodeGetPowerSaveMode() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetPowerSaveMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
     * This function sets the power save timeout value.
     * Supported scanner types : N4313, SE965, SE965HP, N660X, N670X, EX30, N2601
     * @param powerSaveTimeOut mean power save timeout value, Base is second.
     * N4313 & N660X & N670X can be set from 1 to 65553, but SE965 & SE965HP can be set 1s,10s,1m,5m,15m,30m,45m,1h,3h,6h,9h only.
     * @see device.sdk.ScanManager#aDecodeGetPowerSaveTimeOut
     */
    @UnsupportedAppUsage
	public void aDecodeSetPowerSaveTimeOut(int powerSaveTimeOut) {
		try {
			DeviceServer.getIScannerService().aDecodeSetPowerSaveTimeOut(powerSaveTimeOut);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
     * This function gets the power save timeout value.
     * Supported scanner types : N4313, SE965, SE965HP, N660X, N670X, EX30, N2601
     * @return Power save timeout value, Base is second.
     * @see device.sdk.ScanManager#aDecodeSetPowerSaveTimeOut
     */
    @UnsupportedAppUsage
	public int aDecodeGetPowerSaveTimeOut() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetPowerSaveTimeOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function set to each symbology's redundancy value.
	* Supported scanner types : N4313, N660X, N670X, EX30, N2601
	* @param symIndex mean symbology index. See the SymbologyID class. (N660X, N670X supports only Codabar, Code39, Code128, PDF417 and QR symbology.)
	* @param redundancy mean symbology's redundancy value.
	* @see device.common.ScanConst.SymbologyID
	* @see device.sdk.ScanManager#aDecodeSymGetRedundancy
	*/
    @UnsupportedAppUsage
	public void aDecodeSymSetRedundancy(int symIndex, int redundancy) {
        try {
    		DeviceServer.getIScannerService().aDecodeSymSetRedundancy(symIndex, redundancy);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the each symbology's redundancy value.
	* Supported scanner types : N4313, N660X, N670X, EX30, N2601
	* @param symIndex mean symbology index. See the SymbologyID class. (N660X, N670X, EX30, N2601 supports only Codabar, Code39, Code128, PDF417 and QR symbology.)
	* @return each symbology's redundancy value.
	* @see device.common.ScanConst.SymbologyID
	* @see device.sdk.ScanManager#aDecodeSymSetRedundancy
	*/
    @UnsupportedAppUsage
	public int aDecodeSymGetRedundancy(int symIndex) {
        try {
    		return DeviceServer.getIScannerService().aDecodeSymGetRedundancy(symIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* This function can turn on/off scan power, Use for 2D(N660X, N670X, EX30, N2601) Only.
	* @param enable mean scan power on or off, If TRUE, scan power turn on.
	* @see device.sdk.ScanManager#aDecodeGetImagerPower
	* @hide Internal use only
	*/
    @UnsupportedAppUsage
	public void aDecodeSetImagerPower(int enable) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetImagerPower(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the imager power state, Use for 2D(N660X, N670X, EX30, N2601) Only.
	* @return imager power state, If Nonzero, imager power on.
	* @see device.sdk.ScanManager#aDecodeSetImagerPower
	* @hide Internal use only
	*/
    @UnsupportedAppUsage
	public int aDecodeGetImagerPower() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetImagerPower();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	* @deprecated Supported scanner is not installed.
	*/
	@Deprecated
    @UnsupportedAppUsage
	public void aDecodeSetMultipleConfirm(int multiConfirm) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetMultipleConfirm(multiConfirm);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* @deprecated Supported scanner is not installed.
	*/
	@Deprecated
    @UnsupportedAppUsage
	public int aDecodeGetMultipleConfirm() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetMultipleConfirm();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	 * This function can set the scan angle of laser scanner.
	 * Supported scanner types : SE965, SE965HP, MDL1500
	 * @param scanAngle mean scan angle of laser scanner.
	 * SE965 / SE965HP can be set 10, 35, 47 degree only.
	 * MDL1500 can be set as below value.
	 * <pre>
     * public final static class ScanAngle {
     *  private ScanAngle() {}
     *  public static final int DISABLE = 0;
     *  public static final int NARROW = 1;
     *  public static final int MIDDLE = 2;
     *  public static final int WIDE = 3;
     * }
     * </pre>
	 * @see device.sdk.ScanManager#aDecodeGetScanAngle
	 */
    @UnsupportedAppUsage
	public void aDecodeSetScanAngle(int scanAngle) {
		try {
			DeviceServer.getIScannerService().aDecodeSetScanAngle(scanAngle);
		} catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * This function get the scan angle of laser scanner.
	 * Supported scanner types : SE965, SE965HP, MDL1500
	 * @return scan angle of laser scanner.
	 * SE965 / SE965HP returns 10, 35, 47 degree only.
	 * MDL1500 returns as below value.
	 * <pre>
     * public final static class ScanAngle {
     *  private ScanAngle() {}
     *  public static final int DISABLE = 0;
     *  public static final int NARROW = 1;
     *  public static final int MIDDLE = 2;
     *  public static final int WIDE = 3;
     * }
     * </pre>
	 * @see device.sdk.ScanManager#aDecodeSetScanAngle
	 */
    @UnsupportedAppUsage
	public int aDecodeGetScanAngle() {
		try {
			return DeviceServer.getIScannerService().aDecodeGetScanAngle();
		} catch (Exception e) {
            e.printStackTrace();
        }
		return -1;
	}

	/**
	 * This function can set the adaptive scanning mode. Use for 1D scanner only. (SE965HP)
	 * @param adaptiveScanning mean adaptive scanning mode. 0 means do not use adaptive scanning mode.
	 * @see device.sdk.ScanManager#aDecodeGetAdaptiveScanningMode
	 */
    @UnsupportedAppUsage
	public void aDecodeSetAdaptiveScanningMode(int adaptiveScanning) {
		try {
			DeviceServer.getIScannerService().aDecodeSetAdaptiveScanningMode(adaptiveScanning);
		} catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * This function get the adaptive scanning mode. Use for 1D scanner only. (SE965HP)
	 * @return Adaptive scanning mode, 0 means do not use adaptive scanning mode.
	 * @see device.sdk.ScanManager#aDecodeSetAdaptiveScanningMode
	 */
    @UnsupportedAppUsage
	public int aDecodeGetAdaptiveScanningMode() {
		try {
			return DeviceServer.getIScannerService().aDecodeGetAdaptiveScanningMode();
		} catch (Exception e) {
            e.printStackTrace();
        }
		return -1;
	}

	/**
	 * This function set the aimer mode.
	 * Supported scanner types : SE965, SE965HP, EX25, MDL1500
	 * @param enable means the aimer mode state, If true, aimer mode is on.
	 * @see device.sdk.ScanManager#aDecodeGetAimerEnable
	 */
    @UnsupportedAppUsage
	public void aDecodeSetAimerEnable(int enable) {
		try {
			DeviceServer.getIScannerService().aDecodeSetAimerEnable(enable);
		} catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * This function get the aimer mode.
	 * Supported scanner types : SE965, SE965HP, EX25, MDL1500
	 * @return The aimer mode state, If true, aimer mode is on.
	 * @see device.sdk.ScanManager#aDecodeSetAimerEnable
	 */
    @UnsupportedAppUsage
	public int aDecodeGetAimerEnable() {
		try {
			return DeviceServer.getIScannerService().aDecodeGetAimerEnable();
		} catch (Exception e) {
            e.printStackTrace();
        }
		return -1;
	}

	/**
	 * This function set the aimer mode timeout.
	 * Supported scanner types : SE965, SE965HP, EX25, MDL1500
	 * @param timeout means the aimer mode timeout, (milli-second)
	 * @see device.sdk.ScanManager#aDecodeGetAimerTimeOut
	 */
    @UnsupportedAppUsage
	public void aDecodeSetAimerTimeOut(int timeout) {
		try {
			DeviceServer.getIScannerService().aDecodeSetAimerTimeOut(timeout);
		} catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * This function get the aimer mode timeout.
	 * Supported scanner types : SE965, SE965HP, EX25, MDL1500
	 * @return The aimer mode timeout, (milli-second)
	 * @see device.sdk.ScanManager#aDecodeSetAimerTimeOut
	 */
    @UnsupportedAppUsage
	public int aDecodeGetAimerTimeOut() {
		try {
			return DeviceServer.getIScannerService().aDecodeGetAimerTimeOut();
		} catch (Exception e) {
            e.printStackTrace();
        }
		return -1;
	}


	/** @deprecated	*/
	@Deprecated
    @UnsupportedAppUsage
	public void aDecodeSetDefaultEnable(int defaultEnable) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetDefaultEnable(defaultEnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/** @deprecated	*/
	@Deprecated
    @UnsupportedAppUsage
	public int aDecodeGetDefaultEnable() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetDefaultEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	 * This function do decision to transmit aimer ID after decoding.
	 * @param enable means transmit aimer ID. If nonzero, decode result include aimer ID.
	 * @see device.sdk.ScanManager#aDecodeGetResultAimIdEnable
	 */
    @UnsupportedAppUsage
	public void aDecodeSetResultAimIdEnable(int enable) {
		try {
			DeviceServer.getIScannerService().aDecodeSetResultAimIdEnable(enable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function get state of transmit aimer ID option use or not.
	 * @return transmit aimer ID option's state.
	 * @see device.sdk.ScanManager#aDecodeSetResultAimIdEnable
	 */
    @UnsupportedAppUsage
	public int aDecodeGetResultAimIdEnable() {
		try {
			return DeviceServer.getIScannerService().aDecodeGetResultAimIdEnable();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * This function set integer value of group separator.
	 * @param separator means integer value of group separator.
	 * @see device.sdk.ScanManager#aDecodeGetGroupSeparator
	 */
    @UnsupportedAppUsage
	public void aDecodeSetGroupSeparator(int separator) {
		try {
			DeviceServer.getIScannerService().aDecodeSetGroupSeparator(separator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function get integer value of group separator.
	 * @return integer value of group separator.
	 * @see device.sdk.ScanManager#aDecodeSetGroupSeparator
	 */
    @UnsupportedAppUsage
	public int aDecodeGetGroupSeparator() {
		try {
			return DeviceServer.getIScannerService().aDecodeGetGroupSeparator();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
     * @hide
     * Internal use only
     */
    @UnsupportedAppUsage
	public void aDecodeSwitchPower(int isSuspend) {
        try {
    		DeviceServer.getIScannerService().aDecodeSwitchPower(isSuspend);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * This function set levels of decode redundancy, Use for 2D scanner only. (SE47XX)
	 * @param level means redundancy level value. level value is as below.
	 * <pre>
	 * public final static class RedundancyLevel {
	 *	private RedundancyLevel() {}
	 *	public static final int LEVEL_1 = 1;
	 *	public static final int LEVEL_2 = 2;
	 *	public static final int LEVEL_3 = 3;
	 *	public static final int LEVEL_4 = 4;
	 * }
	 * </pre>
	 * @see device.sdk.ScanManager#aDecodeGetRedundancyLevel
	 */
    @UnsupportedAppUsage
	public void aDecodeSetRedundancyLevel(int level) {
		try {
			DeviceServer.getIScannerService().aDecodeSetRedundancyLevel(level);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function get levels of decode redundancy, Use for 2D scanner only. (SE47XX)
	 * @return Redundancy level value. level value is as below.
	 * <pre>
	 * public final static class RedundancyLevel {
	 *	private RedundancyLevel() {}
	 *	public static final int LEVEL_1 = 1;
	 *	public static final int LEVEL_2 = 2;
	 *	public static final int LEVEL_3 = 3;
	 *	public static final int LEVEL_4 = 4;
	 * }
	 * </pre>
	 * @see device.sdk.ScanManager#aDecodeSetRedundancyLevel
	 */
    @UnsupportedAppUsage
	public int aDecodeGetRedundancyLevel() {
		try {
    		return DeviceServer.getIScannerService().aDecodeGetRedundancyLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	 * This function set levels of decode security, Use for 2D scanner only. (SE47XX)
	 * @param level means security level value. level value is as below.
	 * <pre>
	 * public final static class SecurityLevel {
	 *	private SecurityLevel() {}
	 *	public static final int LEVEL_0 = 0;
	 *	public static final int LEVEL_1 = 1;
	 *	public static final int LEVEL_2 = 2;
	 *	public static final int LEVEL_3 = 3;
	 * }
	 * </pre>
	 * @see device.sdk.ScanManager#aDecodeGetSecurityLevel
	 */
    @UnsupportedAppUsage
	public void aDecodeSetSecurityLevel(int level) {
		try {
			DeviceServer.getIScannerService().aDecodeSetSecurityLevel(level);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function get levels of decode security, Use for 2D scanner only. (SE47XX)
	 * @return Security level value. level value is as below.
	 * <pre>
	 * public final static class SecurityLevel {
	 *	private SecurityLevel() {}
	 *	public static final int LEVEL_0 = 0;
	 *	public static final int LEVEL_1 = 1;
	 *	public static final int LEVEL_2 = 2;
	 *	public static final int LEVEL_3 = 3;
	 * }
	 * </pre>
	 * @see device.sdk.ScanManager#aDecodeSetSecurityLevel
	 */
    @UnsupportedAppUsage
	public int aDecodeGetSecurityLevel() {
		try {
    		return DeviceServer.getIScannerService().aDecodeGetSecurityLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	 * This function set mode about inverse 1D, Use for 2D scanner Only.
	 * @param mode means inverse 1D mode. mode value is as below.
	 * <pre>
	 * public final static class Inverse1DMode {
	 *	private Inverse1DMode() {}
	 *	public static final int REGULAR_ONLY = 0;
	 *	public static final int INVERSE_ONLY = 1;
	 *	public static final int INVERSE_AUTODETECT = 2;
	 * }
	 * </pre>
	 * @see device.sdk.ScanManager#aDecodeGetInverse1DMode
	 */
    @UnsupportedAppUsage
	public void aDecodeSetInverse1DMode(int mode) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetInverse1DMode(mode);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * This function get mode about inverse 1D, Use for 2D scanner Only.
	 * @return Inverse 1D mode. mode value is as below.
	 * <pre>
	 * public final static class Inverse1DMode {
	 *	private Inverse1DMode() {}
	 *	public static final int REGULAR_ONLY = 0;
	 *	public static final int INVERSE_ONLY = 1;
	 *	public static final int INVERSE_AUTODETECT = 2;
	 * }
	 * </pre>
	 * @see device.sdk.ScanManager#aDecodeSetInverse1DMode
	 */
    @UnsupportedAppUsage
	public int aDecodeGetInverse1DMode() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetInverse1DMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	 * This function set levels of aggressiveness in decoding bar codes with a reduced quiet zone, Use for 2D scanner only. (SE47XX)
	 * @param level means level value of 1D quiet zone. level value is as below.
	 * <pre>
	 * public final static class QuietZone1DLevel {
	 *	private QuietZone1DLevel() {}
	 *	public static final int LEVEL_0 = 0;
	 *	public static final int LEVEL_1 = 1;
	 *	public static final int LEVEL_2 = 2;
	 *	public static final int LEVEL_3 = 3;
	 * }
	 * </pre>
	 * @see device.sdk.ScanManager#aDecodeGet1DQuietZoneLevel
	 */
    @UnsupportedAppUsage
	public void aDecodeSet1DQuietZoneLevel(int level) {
		try {
			DeviceServer.getIScannerService().aDecodeSet1DQuietZoneLevel(level);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function get levels of aggressiveness in decoding bar codes with a reduced quiet zone, Use for 2D scanner only. (SE47XX)
	 * @return Level value of 1D quiet zone. level value is as below.
	 * <pre>
	 * public final static class QuietZone1DLevel {
	 *	private QuietZone1DLevel() {}
	 *	public static final int LEVEL_0 = 0;
	 *	public static final int LEVEL_1 = 1;
	 *	public static final int LEVEL_2 = 2;
	 *	public static final int LEVEL_3 = 3;
	 * }
	 * </pre>
	 * @see device.sdk.ScanManager#aDecodeSet1DQuietZoneLevel
	 */
    @UnsupportedAppUsage
	public int aDecodeGet1DQuietZoneLevel() {
		try {
    		return DeviceServer.getIScannerService().aDecodeGet1DQuietZoneLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	 * This function set size of intercharacter gap for code39 and codabar, Use for 2D(SE47XX) Only.
	 * @param size means size of intercharacter gap. size value is as below.
	 * <pre>
	 * public final static class IntercharacterGapSize {
	 *	private IntercharacterGapSize() {}
	 *	public static final int NORMAL = 6;
	 *	public static final int LARGE = 10;
	 * }
	 * </pre>
	 * @see device.sdk.ScanManager#aDecodeGetIntercharacterGapSize
	 */
    @UnsupportedAppUsage
	public void aDecodeSetIntercharacterGapSize(int size) {
		try {
			DeviceServer.getIScannerService().aDecodeSetIntercharacterGapSize(size);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function get size of intercharacter gap for code39 and codabar, Use for 2D scanner only. (SE47XX)
	 * @return Intercharacter gap size. size value is as below.
	 * <pre>
	 * public final static class IntercharacterGapSize {
	 *	private IntercharacterGapSize() {}
	 *	public static final int NORMAL = 6;
	 *	public static final int LARGE = 10;
	 * }
	 * </pre>
	 * @see device.sdk.ScanManager#aDecodeSetIntercharacterGapSize
	 */
    @UnsupportedAppUsage
	public int aDecodeGetIntercharacterGapSize() {
		try {
    		return DeviceServer.getIScannerService().aDecodeGetIntercharacterGapSize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
	 * This function do decision to mobile phone/display mode use or not, Use for 2D scanner only. (SE47XX)
	 * @param enable means mobile phone/display mode use or not.
	 * @see device.sdk.ScanManager#aDecodeGetPhoneDisplayMode
	 */
    @UnsupportedAppUsage
	public void aDecodeSetPhoneDisplayMode(int enable) {
		try {
			DeviceServer.getIScannerService().aDecodeSetPhoneDisplayMode(enable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function get state of mobile phone/display mode option use or not, Use for 2D scanner only. (SE47XX)
	 * @return mobile phone/display mode use or not.
	 * @see device.sdk.ScanManager#aDecodeSetPhoneDisplayMode
	 */
    @UnsupportedAppUsage
	public int aDecodeGetPhoneDisplayMode() {
		try {
			return DeviceServer.getIScannerService().aDecodeGetPhoneDisplayMode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * This function set the same read data validation. Use for EX25 Only.
	 * @param level means number of repeated reads give the same results (Range : 0 ~ 10),
	 * @see device.sdk.ScanManager#aDecodeGetSameReadDataValidation
	 */
    @UnsupportedAppUsage
	public void aDecodeSetSameReadDataValidation(int level) {
		try {
			DeviceServer.getIScannerService().aDecodeSetSameReadDataValidation(level);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function get the same read data validation. Use for EX25 Only.
	 * @return Number of repeated reads give the same results.
	 * @see device.sdk.ScanManager#aDecodeSetSameReadDataValidation
	 */
    @UnsupportedAppUsage
	public int aDecodeGetSameReadDataValidation() {
		try {
			return DeviceServer.getIScannerService().aDecodeGetSameReadDataValidation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * This function allows direct access to set any of the decoder library parameters. Use for N660x Only.
	 * @param tag means the decoder parameter that needs to be set.
	 * @param value means the value to set the parameter to.
	 * @see device.sdk.ScanManager#aDecodeGetParameter
	 */
    @UnsupportedAppUsage
	public void aDecodeSetParameter(int tag, int value) {
		try {
			DeviceServer.getIScannerService().aDecodeSetParameter(tag, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function get value of the decoder library parameters. Use for N660x, N670X, EX30, N2601 Only.
	 * @return Value of the decoder library parameters.
	 * @param tag means the decoder parameter that needs to be get.
	 * @see device.sdk.ScanManager#aDecodeSetParameter
	 */
    @UnsupportedAppUsage
	public int aDecodeGetParameter(int tag) {
		try {
			return DeviceServer.getIScannerService().aDecodeGetParameter(tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * This function allows direct access to set any of the decoder library parameters. Use for N660x, N670X, EX30, N2601 Only.
	 * If use this function, do not need to reset parameter values when the scanner is turned off and on.
	 * @param number means the number of parameter values to set. (can be set up to 10)
	 * @param tags means the array of decoder parameter that needs to be set.
	 * @param values means the array of value to set to the parameter.
	 * @see device.sdk.ScanManager#aDecodeGetParameterEx
	 */
    @UnsupportedAppUsage
	public void aDecodeSetParameterEx(int number, int tags[], int values[]) {
		try {
			DeviceServer.getIScannerService().aDecodeSetParameterEx(number, tags, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function get value of the decoder library parameters. Use for N660x, N670X, EX30, N2601 Only.
	 * @return Number of parameter values that have been set.
	 * @param tags means the array of decoder parameter that was set.
	 * @param values means the array of parameter value that was set.
	 * @see device.sdk.ScanManager#aDecodeSetParameterEx
	 */
    @UnsupportedAppUsage
	public int aDecodeGetParameterEx(int tags[], int values[]) {
		try {
			return DeviceServer.getIScannerService().aDecodeGetParameterEx(tags, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * This function can send arbitrary commands to the scanner module. Use for MDL1500 Only.
	 * @param command means byte array data send to the engine.
	 * @return Whether commands transfer succeed or not.
	 */
    @UnsupportedAppUsage
	public boolean aDecodeSendArbitraryCommand(byte[] command) {
		try {
			return DeviceServer.getIScannerService().aDecodeSendArbitraryCommand(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
     * @hide
     * Internal use only
     */
    @UnsupportedAppUsage
    public boolean aDecodeGetCameraBusy() {
        try {
            return DeviceServer.getIScannerService().aDecodeGetCameraBusy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	/**
	 * Register a callback to be invoked when scanner state is changed.
	 *
	 * @param callback The callback that will run. (device.common.DecodeStateCallback)
	 * @return Whether registration success.
	 */
    @UnsupportedAppUsage
	public boolean aRegisterDecodeStateCallback(DecodeStateCallback callback) {
		try {
			return DeviceServer.getIScannerService().aRegisterDecodeStateCallback(callback.getDecodeStateCallback());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Unregister a callback that was registered.
	 *
	 * @param callback The callback that was registered. (device.common.DecodeStateCallback)
	 * @return Whether unregistration success.
	 */
    @UnsupportedAppUsage
	public boolean aUnregisterDecodeStateCallback(DecodeStateCallback callback) {
		try {
			return DeviceServer.getIScannerService().aUnregisterDecodeStateCallback(callback.getDecodeStateCallback());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	* This function get the number of last error.
	* @return number of last error.
	* <pre>
	* public final static class LastError {
	*    private LastError() {
	*    }
	*
	*    public static final int ERROR_NO_ERROR             = 0;
	*    public static final int ERROR_NOT_SUPPORTED        = 1;
	*    public static final int ERROR_INVALID_PARAMETER    = 2;
	*    public static final int ERROR_CANCELLED            = 3;
	* }
	* </pre>
	*/
    @UnsupportedAppUsage
	public int aDecodeGetLastError() {
        try {
    		return DeviceServer.getIScannerService().aDecodeGetLastError();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}

	/**
     * @hide
     * This function restores 1D scanner settings based on the registry file. (For internal use only)
     */
    @UnsupportedAppUsage
    public void aDecodeRestore1DSetting() {
        try {
            DeviceServer.getIScannerService().aDecodeRestore1DSetting();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

	/**
	* This function modify the custom intent configuration.
	* @param customIntent mean class of custom intent configuration.
	* @see device.sdk.ScanManager#aDecodeGetCustomIntentConfig.
	*/
    @UnsupportedAppUsage
	public void aDecodeSetCustomIntentConfig(CustomIntentConfig customIntentInfo) {
        try {
            DeviceServer.getIScannerService().aDecodeSetCustomIntentConfig(customIntentInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get the custom intent configuration.
	* @param customIntent mean class of custom intent configuration.
	* @see device.sdk.ScanManager#aDecodeSetCustomIntentConfig.
	*/
    @UnsupportedAppUsage
	public void aDecodeGetCustomIntentConfig(CustomIntentConfig customIntentInfo) {
        try {
            DeviceServer.getIScannerService().aDecodeGetCustomIntentConfig(customIntentInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * This function starts laser calibration operation.
	 * Supported scanner types : MDL1500
	 * @see device.sdk.ScanManager#aDecodeEndLaserCalibration
	 */
    @UnsupportedAppUsage
	public void aDecodeStartLaserCalibration() {
		try {
			DeviceServer.getIScannerService().aDecodeStartLaserCalibration();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function ends laser calibration operation
	 * Supported scanner types : MDL1500
	 * @see device.sdk.ScanManager#aDecodeStartLaserCalibration
	 */
    @UnsupportedAppUsage
	public void aDecodeEndLaserCalibration() {
		try {
			DeviceServer.getIScannerService().aDecodeEndLaserCalibration();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function do decision to laser focus function use or not.
	 * Supported scanner types : MDL1500
	 * @param enable means laser focus function use state. If 1, it means enabled; If 0, it means disabled.
	 * @see device.sdk.ScanManager#aDecodeGetLaserFocusEnable
	 */
    @UnsupportedAppUsage
	public void aDecodeSetLaserFocusEnable(int enable) {
		try {
			DeviceServer.getIScannerService().aDecodeSetLaserFocusEnable(enable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function get state of laser focus function use or not.
	 * Supported scanner types : MDL1500
	 * @return laser focus function use state. If 1, it means enabled; If 0, it means disabled.
	 * @see device.sdk.ScanManager#aDecodeSetLaserFocusEnable
	 */
    @UnsupportedAppUsage
	public int aDecodeGetLaserFocusEnable() {
		try {
			return DeviceServer.getIScannerService().aDecodeGetLaserFocusEnable();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * This function do decision to number of barcode to scan in multi-scan mode.
	 * Supported scanner types : N660X, N670X, EX30, N2601, MDL1500
	 * @param number means number of barcode to scan in multi-scan mode. This value available from 2 to 10.
	 * @see device.sdk.ScanManager#aDecodeGetMultiScanNumber
	 */
    @UnsupportedAppUsage
	public void aDecodeSetMultiScanNumber(int number) {
		try {
			DeviceServer.getIScannerService().aDecodeSetMultiScanNumber(number);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function get number of barcode to scan in multi-scan mode.
	 * Supported scanner types : N660X, N670X, EX30, N2601, MDL1500
	 * @return number of barcode to scan in multi-scan mode.
	 * @see device.sdk.ScanManager#aDecodeSetMultiScanNumber
	 */
    @UnsupportedAppUsage
	public int aDecodeGetMultiScanNumber() {
		try {
			return DeviceServer.getIScannerService().aDecodeGetMultiScanNumber();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	* This function set separator character for identify the barcode in multi-scan at once mode.
	* Supported scanner types : N660X, N670x, EX30, N2601
	* @param separator mean separator character for identify the barcode in multi-scan at once mode.
	* This value available from 0x00 to 0x1F. If 0x00, it means none separator.
	* @see device.sdk.ScanManager#aDecodeGetMultiScanSeparator
	*/
    @UnsupportedAppUsage
	public void aDecodeSetMultiScanSeparator(char separator) {
        try {
    		DeviceServer.getIScannerService().aDecodeSetMultiScanSeparator(separator);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	* This function get separator character for identify the barcode in multi-scan at once mode.
	* Supported scanner types : N660X, N670x, EX30, N2601
	* @return separator character for identify the barcode in multi-scan at once mode.
	* This value available from 0x00 to 0x1F. If 0x00, it means none separator.
	* @see device.sdk.ScanManager#aDecodeSetMultiScanSeparator
	*/
    @UnsupportedAppUsage
	public char aDecodeGetMultiScanSeparator() {
        try {
		    return DeviceServer.getIScannerService().aDecodeGetMultiScanSeparator();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
	}

	/**
	 * This function can switch the scanner's power status when left and right scan keys are pressed simultaneously for special times.
	 * @param enable means whether to switch the scanner's power status when left and right scan keys to press simultaneously for special times.
	 * @see device.sdk.ScanManager#aDecodeGetScanKeyStartUp
	 * @see device.sdk.ScanManager#aDecodeSetScanKeyStartUpTime
	 * @see device.sdk.ScanManager#aDecodeGetScanKeyStartUpTime
	 */
    @UnsupportedAppUsage
	public void aDecodeSetScanKeyStartUp(int enable) {
		try {
			DeviceServer.getIScannerService().aDecodeSetScanKeyStartUp(enable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function can get status that switch the scanner's power status when press left and right scan keys simultaneously for special times.
	 * @return enable indicates whether to switch the scanner's power status when left and right scan keys to press simultaneously for special times.
	 * @see device.sdk.ScanManager#aDecodeSetScanKeyStartUp
	 */
    @UnsupportedAppUsage
	public int aDecodeGetScanKeyStartUp() {
		try {
			return DeviceServer.getIScannerService().aDecodeGetScanKeyStartUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * This function can set the time that need to press key simultaneously in the ScanKeyStartUp feature.
	 * @param scanKeyStartUpTime means time to press the left and right scan keys simultaneously.(Base : milli-second, Range : 1000 ~ 10000)
	 * @see device.sdk.ScanManager#aDecodeGetScanKeyStartUpTime
	 * @see device.sdk.ScanManager#aDecodeSetScanKeyStartUp
	 * @see device.sdk.ScanManager#aDecodeGetScanKeyStartUp
	 */
    @UnsupportedAppUsage
	public void aDecodeSetScanKeyStartUpTime(int scanKeyStartUpTime) {
		try {
			DeviceServer.getIScannerService().aDecodeSetScanKeyStartUpTime(scanKeyStartUpTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function can get the time that need to press key simultaneously in the ScanKeyStartUp feature.
	 * @return Time to press the left and right scan keys simultaneously.(Base : milli-second, Range : 1000 ~ 10000)
	 * @see device.sdk.ScanManager#aDecodeSetScanKeyStartUpTime
	 */
    @UnsupportedAppUsage
	public int aDecodeGetScanKeyStartUpTime() {
		try {
			return DeviceServer.getIScannerService().aDecodeGetScanKeyStartUpTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
