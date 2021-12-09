package device.sdk;

import android.compat.annotation.UnsupportedAppUsage;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Environment;
import android.os.RemoteException;
import android.content.res.Configuration;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.provider.Settings;
import android.net.wifi.WifiManager;
/* TODO: Commented out for not including WifiConfiguration
import android.net.wifi.WifiConfiguration;
*/

import device.common.DevInfoIndex;
import device.common.IDeviceService;

import device.sdk.DeviceServer;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Control {
    @UnsupportedAppUsage
    public static final int DELETE_KEEP_DATA = PackageManager.DELETE_KEEP_DATA;
    @UnsupportedAppUsage
    public static final int DELETE_ALL_USERS = PackageManager.DELETE_ALL_USERS;
    @UnsupportedAppUsage
    public static final int DELETE_SYSTEM_APP = PackageManager.DELETE_SYSTEM_APP;
    @UnsupportedAppUsage
    public static final int DONT_KILL_APP = PackageManager.DONT_KILL_APP;
    @UnsupportedAppUsage
    public static final int DELETE_SUCCEEDED = PackageManager.DELETE_SUCCEEDED;
    @UnsupportedAppUsage
    public static final int DELETE_FAILED_INTERNAL_ERROR = PackageManager.DELETE_FAILED_INTERNAL_ERROR;
    @UnsupportedAppUsage
    public static final int DELETE_FAILED_DEVICE_POLICY_MANAGER = PackageManager.DELETE_FAILED_DEVICE_POLICY_MANAGER;
    @UnsupportedAppUsage
    public static final int DELETE_FAILED_USER_RESTRICTED = PackageManager.DELETE_FAILED_USER_RESTRICTED;
    @UnsupportedAppUsage
    public static final int DELETE_FAILED_OWNER_BLOCKED = PackageManager.DELETE_FAILED_OWNER_BLOCKED;
    @UnsupportedAppUsage
    public static final int DELETE_FAILED_ABORTED = PackageManager.DELETE_FAILED_ABORTED;
    @UnsupportedAppUsage
    public static final int COMPONENT_ENABLED_STATE_DEFAULT = PackageManager.COMPONENT_ENABLED_STATE_DEFAULT;
    @UnsupportedAppUsage
    public static final int COMPONENT_ENABLED_STATE_ENABLED = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
    @UnsupportedAppUsage
    public static final int COMPONENT_ENABLED_STATE_DISABLED = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
    @UnsupportedAppUsage
    public static final int COMPONENT_ENABLED_STATE_DISABLED_USER = PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER;
    @UnsupportedAppUsage
    public static final int COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED = PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED;

    /**
     * Whether the audible DTMF tones are played by the dialer when dialing. The value is
     * boolean (1 or 0).
     */
    @UnsupportedAppUsage
    public static final int SOUND_DIALPAD = 0;

    /**
     * Whether to play sounds when the keyguard is shown and dismissed.
     */
    @UnsupportedAppUsage
    public static final int SOUND_SCREEN_LOCKING = 1;

    /**
     * Whether to play a sound for charging events.
     */
    @UnsupportedAppUsage
    public static final int SOUND_CHARGING = 2;

    /**
     * Whether to play a sound for dock events.
     */
    @UnsupportedAppUsage
    public static final int SOUND_DOCKING = 3;

    /**
     * Whether the sounds effects (key clicks, lid open ...) are enabled. The value is
     * boolean (1 or 0).
     */
    @UnsupportedAppUsage
    public static final int SOUND_TOUCH = 4;

    /**
     * Whether the haptic feedback (long presses, ...) are enabled. The value is
     * boolean (1 or 0).
     */
    @UnsupportedAppUsage
    public static final int SOUND_VIBRATE_ON_TOUCH = 5;

    /**
     * Use Dock audio output for media:
     *      {@link #DOCK_AUDIO_MEDIA_DISABLED} : disable
     *      {@link #DOCK_AUDIO_MEDIA_ENABLED} : enable
     */
    @UnsupportedAppUsage
    public static final int SOUND_DOCK_AUDIO_MEDIA = 6;

    /**
     * CDMA only Settings
     * Emergency tone:
     *      {@link #EMERGENCY_TONE_SILENT} : Off
     *      {@link #EMERGENCY_TONE_ALERT} : Alert
     *      {@link #EMERGENCY_TONE_VIBRATE} : Vibrate
     */
    @UnsupportedAppUsage
    public static final int SOUND_EMERGENCY = 7;


    @UnsupportedAppUsage
    public static final int EMERGENCY_TONE_SILENT = 0;
    @UnsupportedAppUsage
    public static final int EMERGENCY_TONE_ALERT = 1;
    @UnsupportedAppUsage
    public static final int EMERGENCY_TONE_VIBRATE = 2;

    @UnsupportedAppUsage
    public static final int DOCK_AUDIO_MEDIA_DISABLED = 0;
    @UnsupportedAppUsage
    public static final int DOCK_AUDIO_MEDIA_ENABLED = 1;

    private static final String TAG = Control.class.getSimpleName();
    private static Control mInstance = null;

    @UnsupportedAppUsage
    public static Control getInstance() {
        if (mInstance == null) {
            mInstance = new Control();
        }
        return mInstance;
    }
    @UnsupportedAppUsage
    public Control() {}

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public String getExpansion5vPower() throws RemoteException {
        return DeviceServer.getIDeviceService().getExpansion5vPower();
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public void setExpansion5vPower(boolean enabled) throws RemoteException {
        DeviceServer.getIDeviceService().setExpansion5vPower(enabled);
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public String getExpansion3p3vPower() throws RemoteException {
        return DeviceServer.getIDeviceService().getExpansion3p3vPower();
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public void setExpansion3p3vPower(boolean enabled) throws RemoteException {
        DeviceServer.getIDeviceService().setExpansion3p3vPower(enabled);
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public String getExpansionGpio() throws RemoteException {
        return DeviceServer.getIDeviceService().getExpansionGpio();
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public void setExpansionGpio(boolean enabled) throws RemoteException {
        DeviceServer.getIDeviceService().setExpansionGpio(enabled);
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public String getGpsPower() throws RemoteException {
        return DeviceServer.getIDeviceService().getGpsPower();
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public void setGpsPower(boolean enabled) throws RemoteException {
        DeviceServer.getIDeviceService().setGpsPower(enabled);
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public String getStyluspenMode() throws RemoteException {
        return DeviceServer.getIDeviceService().getStyluspenMode();
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public void setStyluspenMode(boolean enabled) throws RemoteException {
        DeviceServer.getIDeviceService().setStyluspenMode(enabled);
    }

    /**
     * This function gets whether the navigation bar is hidden.
     * It support only the device what navigation bar exist.
     * See {@link device.sdk.Control#setNavigationBarHide(boolean)}
     *
     * @return Gets the current status with boolean whether the navigation is is hidden.
     * True if the navigation bar is hidden. Or false.
     */
    @UnsupportedAppUsage
    public boolean getNavigationBarHide() throws RemoteException {
        return DeviceServer.getIDeviceService().getNavigationBarHide();
    }

    /**
     * This function can hide the navigation bar forcibly.
     * It support only the device what navigation bar exist.
     * See {@link device.sdk.Control#getNavigationBarHide()}
     *
     * @param enabled Sets the navigation bar to hide forcibly with boolean
     * <pre>
     * true : Invisible the navigation bar.
     * false : Visible the navigation bar.
     * </pre>
     */
    @UnsupportedAppUsage
    public void setNavigationBarHide(boolean enabled) throws RemoteException {
        DeviceServer.getIDeviceService().setNavigationBarHide(enabled);
    }

    /**
     * This function gets OnlyHardKeyBoard flag.
     * @return OnlyHardKeyBoard flag with boolean : true OR false
     */
    @UnsupportedAppUsage
    public boolean getOnlyHardKeyboardEnabled() throws RemoteException {
        return DeviceServer.getIDeviceService().getOnlyHardKeyboardEnabled();
    }

    /**
     * This function sets OnlyHardKeyBoard flag.
     * @param enabled OnlyHardKeyBoard flag with boolean
     *
     * <pre>
     * true : Invisible SoftKeyboard
     * false : Visible SoftKeyboard
     * </pre>
     */
    @UnsupportedAppUsage
    public void setOnlyHardKeyboardEnabled(boolean enabled) throws RemoteException {
        DeviceServer.getIDeviceService().setOnlyHardKeyboardEnabled(enabled);
    }

    /**
     * @hide
     * This function reads customer mfg data.
     * @return customer mfg data with byte array
     * @see  device.sdk.Control#setCustomerMfgData
     */
    @UnsupportedAppUsage
    public byte[] getCustomerMfgData() throws RemoteException {
        return DeviceServer.getIDeviceService().getCustomerMfgData();
    }

    /**
     * @hide
     * This function writes customer mfg data.
     * @param data customer mfg data to write. (The length of the data should be 64 or less.)
     * @return true if the data write successful, Or false if failure.
     * @see  device.sdk.Control#getCustomerMfgData
     */
    @UnsupportedAppUsage
    public boolean setCustomerMfgData(byte[] data) throws RemoteException {
        return DeviceServer.getIDeviceService().setCustomerMfgData(data);
    }

    /**
     * @hide
     * This function reads customer mfg data.
     * @return customer mfg data with String
     * @see  device.sdk.Control#setCustomerMfgStringData
     */
    @UnsupportedAppUsage
    public String getCustomerMfgStringData() throws RemoteException {
        return DeviceServer.getIDeviceService().getCustomerMfgStringData();
    }

    /**
     * @hide
     * This function writes customer mfg data.
     * @param data customer mfg data to write. (The length of the data should be 64 or less.)
     * @return true if the data write successful, Or false if failure.
     * @see  device.sdk.Control#getCustomerMfgStringData
     */
    @UnsupportedAppUsage
    public boolean setCustomerMfgStringData(String data) throws RemoteException {
        return DeviceServer.getIDeviceService().setCustomerMfgStringData(data);
    }

    /**
     * This function reads ADB(USB Debugging) flag.
     * @return ADB(USB Debugging) flag with boolean: "true" or "false"
     * @see  device.sdk.Control#setAdbEnabled
     */
    @UnsupportedAppUsage
    public boolean getAdbEnabled() throws RemoteException {
        return DeviceServer.getIDeviceService().getAdbEnabled();
    }

    /**
     * This function write ADB(USB Debugging) flag.
     * @param enabled ADB(USB Debugging) flag with boolean
	 * 
     * <pre>
     * "true" : Enable ADB(USB Debugging)
     * "false" : Disable ADB(USB Debugging)
     * </pre>
	 *
     * @see device.sdk.Control#getAdbEnabled
     */
    @UnsupportedAppUsage
    public void setAdbEnabled(boolean enabled) throws RemoteException {
        DeviceServer.getIDeviceService().setAdbEnabled(enabled);
    }

    /**
     * This function reads level of vibrate intensity. (not supported in PM85/XT3)
     * @return level of vibrate intensity with integer (MIN : 0, MAX : 7)
     * @see device.sdk.Control#setVibrateIntensity
     */
    @UnsupportedAppUsage
    public int getVibrateIntensity() {
        try {
            return DeviceServer.getIDeviceService().getVibrateIntensity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * This function write level of vibrate intensity. (not supported in PM85/XT3)
     * @param level level of vibrate intensity with integer (MIN : 0, MAX : 7)
     * @see device.sdk.Control#getVibrateIntensity
     */
    @UnsupportedAppUsage
    public void setVibrateIntensity(int level) {
        try {
            DeviceServer.getIDeviceService().setVibrateIntensity(level);
        } catch (RemoteException e) {
			e.printStackTrace();
        }
    }

    /**
     * This function reads the keyboard backlight flag.
     * @return The keyboard backlight flag with boolean: "true" or "false"
     * @see device.sdk.Control#setKeyboardBacklightEnabled
     */
    @UnsupportedAppUsage
    public boolean getKeyboardBacklightEnabled() {
        try {
            return DeviceServer.getIDeviceService().getKeyboardBacklightEnabled();
        } catch (RemoteException e) {
			e.printStackTrace();
        }
        return false;
    }

    /**
     * This function writes the keyboard backlight flag.
     * @param enabled Keyboard backlight flag with boolean
	 * 
     * <pre>
     * "true" : Enable the keyboard backlight
     * "false" : Disable the keyboard backlight
     * </pre>
     *
     * @see  device.sdk.Control#getKeyboardBacklightEnabled
     */
    @UnsupportedAppUsage
    public void setKeyboardBacklightEnabled(boolean enabled) {
        try {
            DeviceServer.getIDeviceService().setKeyboardBacklightEnabled(enabled);
        } catch (RemoteException e) {
			e.printStackTrace();
        }
    }

    /**
    * This function reads the touch sensitivity flag.
    * @return The touch sensitivity flag with integer: "0" or "1" or "2"
    * @see device.sdk.Control#setTouchSensitivityValue
    */
    @UnsupportedAppUsage
    public int getTouchSensitivityValue() {
        try {
          return DeviceServer.getIDeviceService().getTouchSensitivityValue();
        } catch (RemoteException e) {
			e.printStackTrace();
        }
        return 1;
    }
    /**
    * This function writes the touch sensitivity flag.
    * @param enabled touch sensitivity flag with integer
	*
    * <pre>
    * "0" : the touch sensitivity low
    * "1" : the touch sensitivity medium
    * "2" : the touch sensitivity high
    * </pre>
    *
    * @see  device.sdk.Control#getTouchSensitivityValue
     */
    @UnsupportedAppUsage
    public void setTouchSensitivityValue(int sensitivity) {
        try {
            DeviceServer.getIDeviceService().setTouchSensitivityValue(sensitivity);
        } catch (RemoteException e) {
			e.printStackTrace();
        }
    }

    /**
     * This function reads the vibrate on touch flag of Settings.
     * @return The vibrate on touch flag with boolean: "true" or "false"
     * @see device.sdk.Control#setVibrateOnTouchEnabled
     */
    @UnsupportedAppUsage
    public boolean getVibrateOnTouchEnabled() {
        try {
            return DeviceServer.getIDeviceService().getVibrateOnTouchEnabled();
        } catch (RemoteException e) {
			e.printStackTrace();
        }
        return false;
    }

    /**
     * This function stores the vibrate on touch flag of Settings.
     * @param enabled Vibrate on touch flag with boolean
	 *
     * <pre>
     * "true" : the vibrate on touch status of Settings is enabled
     * "false" : the vibrate on touch status of Settings is disabled
     * </pre>
     *
     * @see  device.sdk.Control#getVibrateOnTouchEnabled
     */
    @UnsupportedAppUsage
    public void setVibrateOnTouchEnabled(boolean enabled) {
        try {
            DeviceServer.getIDeviceService().setVibrateOnTouchEnabled(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // START 20180104 tommy.kim [#10927] To notify GPS always on feature
    /**
     * This function writes the gps alwayson flag.
     * If enabled parameter is true and GpsAlwaysOff setting is on, turn off the GpsAlwaysOff setting first
     * to prevent turning on the GpsAlwaysOn and GpsAlwaysOff simultaniously.
     *
     * @param enabled Gps alwayson flag with boolean
     * <pre>
     * "true" : Enable the Gps always on
     * "false" : Disable the Gps always on
     * </pre>
     *
     * @see  device.sdk.Control#setGpsAlwaysOn
     * @see  device.sdk.Control#setGpsAlwaysOff
     */
    @UnsupportedAppUsage
    public void setGpsAlwaysOn(boolean enabled) {
        try {
            DeviceServer.getIDeviceService().setGpsAlwaysOn(enabled);
        } catch (RemoteException e) {
			e.printStackTrace();
        }
    }

    /**
     * This function writes the gps alwaysoff flag.
     * If enabled parameter is true and GpsAlwaysOn setting is on, turn off the GpsAlwaysOn setting first
     * to prevent turning on the GpsAlwaysOn and GpsAlwaysOff simultaniously.
     * @param enabled Gps alwaysoff flag with boolean
	 *
     * <pre>
     * "true" : Enable the Gps always off
     * "false" : Disable the Gps always off
     * </pre>
     *
     * @see  device.sdk.Control#setGpsAlwaysOn
     * @see  device.sdk.Control#setGpsAlwaysOff
     */
    @UnsupportedAppUsage
    public void setGpsAlwaysOff(boolean enabled) {
        try {
            DeviceServer.getIDeviceService().setGpsAlwaysOff(enabled);
        } catch (RemoteException e) {
			e.printStackTrace();
        }
    }
    // END 20180104 tommy.kim [#10927] To notify GPS always on feature
    // START [#12680] 2018.08.10 sijoo
    // Add data enable/disable function by customer requirement
    /**
     * This function can control mobile data enable or disable status.
     * If enabled parameter is true, data can enabled
     * @param enabled mobile data enable flag with boolean
     *
     * <pre>
     * "true" : Enable the mobile data
     * "false" : Disable the mobile data
     * </pre>
     *
     * @see  device.sdk.Control#setDataEnabled
     */
    @UnsupportedAppUsage
    public void setDataEnabled(boolean enabled) {
        try {
            DeviceServer.getIDeviceService().setDataEnabled(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // ENS [#12680] 2018.08.10 sijoo

    /**
     * This function reads the currently configured NTP server.
     * @return {@code String} The currently configured NTP server's domain name or IP address
     * @see #setNtpServer
     */
    @UnsupportedAppUsage
    public String getNtpServer() {
        try {
            return DeviceServer.getIDeviceService().getNtpServer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * This function writes the NTP server address into Settings.
     * @param address {@code String} Domain name or IP address about NTP server
     * @see #getNtpServer
     */
    @UnsupportedAppUsage
    public void setNtpServer(String address) {
        try {
            DeviceServer.getIDeviceService().setNtpServer(address);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function locks the device immediately.
     * @param go2sleep {@code boolean} Whether or not the device should be sleep.
     */
    @UnsupportedAppUsage
    public void lockNow(boolean go2sleep) {
        try {
            DeviceServer.getIDeviceService().lockNow(go2sleep);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    // START 2019424 sijoo Merged hostname UI from PM85
    /**
     * This function returns the configured hostname.
     * @return {@code String} The currently configured hostname
     * @see #setHostname
     */
    @UnsupportedAppUsage
    public String getHostname() {
        try {
            return DeviceServer.getIDeviceService().getHostname();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * This function writes hostname into Settings.
     * @param hostname {@code String} hostname
     * @see #getHostname
     */
    @UnsupportedAppUsage
    public boolean setHostname(String hostname) {
        try {
            return DeviceServer.getIDeviceService().setHostname(hostname);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
    // END 20190220 sijoo Merged hostname UI from PM85

    /**
     * This function change to brightness extension setting on LCD backlight.
     * See {@link device.sdk.Control#getExtensionBrightness()}
     *
     * @param enabled Sets to 25mA the brightness current limit with boolean
     */
    @UnsupportedAppUsage
    public void setExtensionBrightness(boolean enabled) throws RemoteException {
        DeviceServer.getIDeviceService().setExtensionBrightness(enabled);
    }

    /**
     * This function gets brightness extension setting on LCD backlight.
     * @return LCD Backlight brightness extension setting on boolean : true OR false
     */
    @UnsupportedAppUsage
    public boolean getExtensionBrightness() throws RemoteException {
        return DeviceServer.getIDeviceService().getExtensionBrightness();
    }

    /**
     * [ MDM Support ]
     *
     * Enable/disable 802.11d setting.
     * <p><strong>NOTE:</strong> The rebooting is required to affect this setting.</p>
     * @param enable {@code true} if enable 802.11d setting
     * @see #getEnable11d()
     * @deprecated This API is deprecated
     */
    @UnsupportedAppUsage
    public void setEnable11d(boolean enable) {
        try {
            DeviceServer.getIDeviceService().setEnable11d(enable);
        } catch (RemoteException e) { }
    }

    /**
     * [ MDM Support ]
     *
     * Get 802.11d setting
     * @return whether the 802.11d is enabled or not
     * @see #setEnable11d(boolean)
     * @deprecated This API is deprecated
     */
    @UnsupportedAppUsage
    public boolean getEnable11d() {
        try {
            return DeviceServer.getIDeviceService().getEnable11d();
        } catch (RemoteException e) { }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Set 2.4GHz channel mask
     * <p>Available channel list is as follows:
     *   1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13</p>
     * <pre>
     * int list[] = new int[3];
     * list[0] = 3; list[1] = 5; list[2] = 9;
     * Control control = new Control();
     * control.set24GHzChannelList(list);
     * </pre>
     * @param channelList a list of channel which user need to enable
     *        <p>Possible values : 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13</p>
     * @see #get24GHzChannelList()
     */
    @UnsupportedAppUsage
    public void set24GHzChannelList(int[] channelList) {
        try {
            DeviceServer.getIDeviceService().set24GHzChannelList(channelList);
        } catch (RemoteException e) { }
    }

    /**
     * [ MDM Support ]
     *
     * Get 2.4GHz channel mask
     * <pre>
     * int list[];
     * Control control = new Control();
     * list = control.get24GHzChannelList();
     * </pre>
     * @return a list of channel which is enabled
     * @see #set24GHzChannelList(int[])
     */
    @UnsupportedAppUsage
    public int[] get24GHzChannelList() {
        try {
            return DeviceServer.getIDeviceService().get24GHzChannelList();
        } catch (RemoteException e) { }
        return null;
    }

    /**
     * [ MDM Support ]
     *
     * Set 5GHz channel mask
     * <p>Available channel list is as follows:
     *   36, 40, 44, 48, 52, 56, 60, 64,
     *   100, 104, 108, 112, 116, 120, 124, 128,
     *   132, 136, 140, 144, 149, 153, 157, 161, 165</p>
     * <pre>
     * int list[] = new int[3];
     * list[0] = 44; list[1] = 104; list[2] = 149;
     * Control control = new Control();
     * control.set5GHzChannelList(list);
     * </pre>
     * @param channelList a list of channel which user need to enable
     *        <p>Possible values : 36, 40, 44, 48, 52, 56, 60, 64,
     *              100, 104, 108, 112, 116, 120, 124, 128,
     *              132, 136, 140, 144, 149, 153, 157, 161, 165</p>
     * @see #get5GHzChannelList()
     */
    @UnsupportedAppUsage
    public void set5GHzChannelList(int[] channelList) {
        try {
            DeviceServer.getIDeviceService().set5GHzChannelList(channelList);
        } catch (RemoteException e) { }
    }

    /**
     * [ MDM Support ]
     *
     * Get 5GHz channel mask
     * <pre>
     * int list[];
     * Control control = new Control();
     * list = control.get24GHzChannelList();
     * </pre>
     * @return a list of channel which is enabled
     * @see #set5GHzChannelList(int[])
     */
    @UnsupportedAppUsage
    public int[] get5GHzChannelList() {
        try {
            return DeviceServer.getIDeviceService().get5GHzChannelList();
        } catch (RemoteException e) {
            return null;
        }
    }

    /**
     * [ MDM Support ]
     *
     * Set the country code.
     * <p>NOTE : This API only relates to Wi-Fi</p>
     * @param countryCode country code in ISO 3166 format.
     * @param persist {@code true} if this needs to be remembered
     * @see getCountryCode()
     */
    @UnsupportedAppUsage
    public void setCountryCode(String country, boolean persist) {
        try {
            DeviceServer.getIDeviceService().setCountryCode(country, persist);
        } catch (RemoteException e) {
            return ;
        }
    }

    /**
     * [ MDM Support ]
     *
     * get the country code.
     * <p>NOTE : This API only relates to Wi-Fi</p>
     * @return the country code in ISO 3166 format.
     * @see setCountryCode(String, boolean)
     */
    @UnsupportedAppUsage
    public String getCountryCode() {
        try {
            return DeviceServer.getIDeviceService().getCountryCode();
        } catch (RemoteException e) {
            return "";
        }
    }

    /**
     * [ MDM Support ]
     *
     * Enable/Disable PowerSaveMode of WLAN
     * <p><strong>NOTE:</strong> The rebooting is required to affect this setting.</p>
     * @param enable {@code true}
     * @see #getWLANPowerSaveMode()
     */
    @UnsupportedAppUsage
    public void setWLANPowerSaveMode(boolean enable) {
        try {
            DeviceServer.getIDeviceService().setWLANPowerSaveMode(enable);
        } catch (RemoteException e) {
            return ;
        }
    }

    /**
     * [ MDM Support ]
     *
     * get PowerSaveMode of WLAN
     * @return whether the 802.11d is enabled or not
     * @see #setWLANPowerSaveMode()
     */
    @UnsupportedAppUsage
    public boolean getWLANPowerSaveMode() {
        try {
            return DeviceServer.getIDeviceService().getWLANPowerSaveMode();
        } catch (RemoteException e) {
            return false;
        }
    }

    // START 20171204 tommy.kim [#10925] Add CCX supporing API
    /**
     * [ MDM Support ]
     *
     * Enable/Disable CCX Supporting
     * <p><strong>NOTE:</strong> The rebooting is required to affect this setting.</p>
     * @param enable {@code true}
     * @see #getCCXSupporting()
     * @deprecated This API is deprecated
     */
    @UnsupportedAppUsage
    public void setCCXSupporting(boolean enable) {
        try {
            DeviceServer.getIDeviceService().setCCXSupporting(enable);
        } catch (RemoteException e) {
            return ;
        }
    }

    /**
     * [ MDM Support ]
     *
     * get CCX supporting
     * @return whether the CCX is enabled or not
     * @see #setCCXSupporting()
     * @deprecated This API is deprecated
     */
    @UnsupportedAppUsage
    public boolean getCCXSupporting() {
        try {
            return DeviceServer.getIDeviceService().getCCXSupporting();
        } catch (RemoteException e) {
            return false;
        }
    }
    // END 20171204 tommy.kim [#10925] Add CCX supporing API

    /**
     * [ MDM Support ]
     *
     * Send Deauth before connect
     * <p><strong>NOTE:</strong> The rebooting is required to affect this setting.</p>
     * @param enable {@code true}
     * @see #getSendDeauthBeforeConnect()
     * @deprecated This API is deprecated
     */
    @UnsupportedAppUsage
    public void setSendDeauthBeforeConnect(boolean enable) {
        try {
            DeviceServer.getIDeviceService().setSendDeauthBeforeConnect(enable);
        } catch (RemoteException e) {
            return ;
        }
    }

    /**
     * [ MDM Support ]
     *
     * get whether sending Deauth before connect
     * @return whether sending Deauth before connect
     * @see #setSendDeauthBeforeConnect()
     * @deprecated This API is deprecated
     */
    @UnsupportedAppUsage
    public boolean getSendDeauthBeforeConnect() {
        try {
            return DeviceServer.getIDeviceService().getSendDeauthBeforeConnect();
        } catch (RemoteException e) {
            return false;
        }
    }

    /**
     * [ MDM Support ]
     *
     * set background scan trigger
     * Valid values of level : -50, -55, -60, -65, -70, -75, -80, -85, -90
     * <p><strong>NOTE:</strong> The rebooting is required to affect this setting.</p>
     * @param level Background scan trigger
     * @see #getBackgroundScanTrigger()
     * @deprecated This API is deprecated
     */
    @UnsupportedAppUsage
    public void setBackgroundScanTrigger(int level) {

        try {
            DeviceServer.getIDeviceService().setBackgroundScanTrigger(level);
        } catch (RemoteException e) {
            return ;
        }
    }

    /**
     * [ MDM Support ]
     *
     * get background scan trigger
     * @return Background scan trigger level
     * @see #setBackgroundScanTrigger()
     * @deprecated This API is deprecated
     */
    @UnsupportedAppUsage
    public int getBackgroundScanTrigger() {
        try {
            return DeviceServer.getIDeviceService().getBackgroundScanTrigger();
        } catch (RemoteException e) {
            return 0;
        }
    }

    /**
     * [ MDM Support ]
     *
     * set roaming trigger
     * Valid values of level : -50, -55, -60, -65, -70, -75, -80, -85, -90
     * <p><strong>NOTE:</strong> The rebooting is required to affect this setting.</p>
     * @param level roaming trigger level
     * @see #getRoamingTrigger()
     */
    @UnsupportedAppUsage
    public void setRoamingTrigger(int level) {

        try {
            DeviceServer.getIDeviceService().setRoamingTrigger(level);
        } catch (RemoteException e) {
            return ;
        }
    }

    /**
     * [ MDM Support ]
     *
     * get roaming trigger
     * @return roaming trigger level
     * @see #setRoamingTrigger()
     */
    @UnsupportedAppUsage
    public int getRoamingTrigger() {
        try {
            return DeviceServer.getIDeviceService().getRoamingTrigger();
        } catch (RemoteException e) {
            return 0;
        }
    }
    // END 20171227 Tommy.kim [#10925] Implement WiFi setting API for MDM solution

    /**
     * [ MDM Support ]
     *
     * get WiFi Polivy
     * @return Policy of WiFi Radio
     * @see #setWiFiRadioPolicy()
     */
    @UnsupportedAppUsage
    public int getWiFiRadioPolicy() {
        try {
            return DeviceServer.getIDeviceService().getWiFiRadioPolicy();
        } catch (RemoteException e) {
            return 0;
        }
    }

    /**
     * [ MDM Support ]
     *
     * set WiFi Policy
     * Valid values of policy : 0 - Auto 1 - 802.11a 2 - 802.11b 3 - 802.11g 4 - 802.11ac
     * <p><strong>NOTE:</strong> The rebooting is required to affect this setting.</p>
     * @param policy Policy of WiFi Radio
     * @see #getWiFiRadioPolicy()
     */
    @UnsupportedAppUsage
    public boolean setWiFiRadioPolicy(int policy) {
        try {
            return DeviceServer.getIDeviceService().setWiFiRadioPolicy(policy);
        } catch (RemoteException e) {
            return false;
        }
    }

    /**
     * [ MDM Support ]
     *
     * set Internet Access Checking
     * Valid values of level : true, false
     * @param enable Enable/Disable Internet access checking
     * @see #getInternetAccessCheck()
     */
    @UnsupportedAppUsage
    public void setInternetAccessCheck(boolean enable) {
        try {
            DeviceServer.getIDeviceService().setInternetAccessCheck(enable);
        } catch (RemoteException e) {
            return ;
        }
    }

    /**
     * [ MDM Support ]
     *
     * get Internet Access Checking
     * @return Enabled/Disabled internet access checking
     * @see #setInternetAccessCheck()
     */
    @UnsupportedAppUsage
    public boolean getInternetAccessCheck() {
        try {
            return DeviceServer.getIDeviceService().getInternetAccessCheck();
        } catch (RemoteException e) {
            return false;
        }
    }

    @UnsupportedAppUsage
    public boolean addWifiSsidsToWhiteList(List<String> ssid) {
        try {
            return DeviceServer.getIDeviceService().addWifiSsidsToWhiteList(ssid);
        } catch (RemoteException e) {
            return false;
        }
    }

    // START 20191017 mik.lee [#15115][WLAN] Ping is not working after screen off
    /**
      * [ MDM Support ]
      *
     * set Keep Alive Mode
     * Valid values of level : true, false
     * @param enable Enable/Disable Keep Alive Mode
     * @see #getKeepAliveMode()
     */
    @UnsupportedAppUsage
    public void setKeepAliveMode(boolean enable) {
        try {
            DeviceServer.getIDeviceService().setKeepAliveMode(enable);
        } catch (RemoteException e) {
            return ;
        }
    }

    /**
     * [ MDM Support ]
     *
     * get Keep Alive Mode
     * @return Enabled/Disabled Keep Alive Mode
     * @see #setKeepAliveMode()
     */
    @UnsupportedAppUsage
    public boolean getKeepAliveMode() {
        try {
            return DeviceServer.getIDeviceService().getKeepAliveMode();
        } catch (RemoteException e) {
            return false;
        }
    }
    // END 20191017 mik.lee [#15115][WLAN] Ping is not working after screen off

    // START 20200601 mik.lee [WLAN]Added Inter-Subnet Roaming function(IP_REACHABILITY_LOST Control)
    /** [ MDM Support ]
     *
     * set InterSubnetRoaming
     * Valid values of level : true, false
     * @param enable Enable/Disable InterSubnetRoaming
     * @see #getInterSubnetRoaming()
     */
    @UnsupportedAppUsage
    public void setInterSubnetRoaming(boolean enable) {
        try {
            DeviceServer.getIDeviceService().setInterSubnetRoaming(enable);
        } catch (RemoteException e) {
            return ;
        }
    }

    /**
     * [ MDM Support ]
     *
     * get InterSubnetRoaming
     * @return Enabled/Disabled InterSubnetRoaming
     * @see #setInterSubnetRoaming()
     */
    @UnsupportedAppUsage
    public boolean getInterSubnetRoaming() {
        try {
            return DeviceServer.getIDeviceService().getInterSubnetRoaming();
        } catch (RemoteException e) {
            return false;
        }
    }
    // END 20200601 mik.lee [WLAN]Added Inter-Subnet Roaming function(IP_REACHABILITY_LOST Control)

    // START 20210729 mik.lee [WLAN] Added Wi-Fi Scan throttling function
    /**
     * [ MDM Support ]
     *
     * set WifiScanThrottling
     * @return Enabled/Disabled WifiScanThrottling
     * @see #getWifiScanThrottling()
     */
    @UnsupportedAppUsage
    public void setWifiScanThrottling(boolean enable) {
        try {
            DeviceServer.getIDeviceService().setWifiScanThrottling(enable);
        } catch (RemoteException e) {
            return ;
        }
    }

    /**
     * [ MDM Support ]
     *
     * get WifiScanThrottling
     * @return Enabled/Disabled WifiScanThrottling
     * @see #setWifiScanThrottling()
     */
    @UnsupportedAppUsage
    public boolean getWifiScanThrottling() {
        try {
            return DeviceServer.getIDeviceService().getWifiScanThrottling();
        } catch (RemoteException e) {
            return false;
        }
    }
    // END 20210729 mik.lee [WLAN] Added Wi-Fi Scan throttling function

    @UnsupportedAppUsage
    public boolean clearWifiSsidsFromWhiteList() {
        try {
            return DeviceServer.getIDeviceService().clearWifiSsidsFromWhiteList();
        } catch (RemoteException e) {
            return false;
        }
    }

    @UnsupportedAppUsage
    public List<String> getWifiSsidsFromWhiteLists() {
        try {
            return DeviceServer.getIDeviceService().getWifiSsidsFromWhiteLists();
        } catch (RemoteException e) {
            List<String> temp = null;
            return temp;
        }
    }

    @UnsupportedAppUsage
    public boolean removeWifiSsidsFromWhiteList(List<String> ssid) {
        try {
            return DeviceServer.getIDeviceService().removeWifiSsidsFromWhiteList(ssid);
        } catch (RemoteException e) {
            return false;
        }
    }

    /**
     * [ MDM Support ]
     *
     * Called by an application that is administering the device to enable all capturing screen
     * on the device, for all users. After setting this, no applications running as all users
     * will be able to access any cameras on the device.
     * @param enabled Whether or not the screen capture should be enabled.
     * @see #isEnabledScreenCapture()
     */
    @UnsupportedAppUsage
    public void setEnabledScreenCapture(boolean enabled) {
        try {
            DeviceServer.getIDeviceService().setEnabledScreenCapture(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

     /**
     * [ MDM Support ]
     *
     * Determine whether or not the device's screen capture have been enabled for all users,
     * either by the current admin, if specified, or all admins.
     * @see #setEnabledScreenCapture(boolean)
     */
    @UnsupportedAppUsage
    public boolean isEnabledScreenCapture() {
        try {
            return DeviceServer.getIDeviceService().isEnabledScreenCapture();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * [ MDM Support ]
     *
     * Indicates whether this system is restricted.
     * @return {@code true} if this system is restricted, {@code false} otherwise.
     */
    @UnsupportedAppUsage
    public boolean isRestricted() {
        try {
            return DeviceServer.getIDeviceService().isRestricted();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Sets the value of a specific restriction.
     * @param key the key of the restriction
     * @param value the value for the restriction
     */
    @UnsupportedAppUsage
    public void setUserRestriction(String key, boolean value) {
        try {
            DeviceServer.getIDeviceService().setUserRestriction(key, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Returns whether this system has been disallowed from performing certain actions
     * or setting certain settings.
     * @param restrictionKey The string key representing the restriction.
     * @return {@code true} if this system has the given restriction, {@code false} otherwise.
     */
    @UnsupportedAppUsage
    public boolean hasUserRestriction(String restrictionKey) {
        try {
            return DeviceServer.getIDeviceService().hasUserRestriction(restrictionKey);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Sets the value to allow for installing non market app.
     * @param enabled {@code true} permit app installation via the system package installer intent, {@code false} do not allow use of the package installer.
     * @return {@code true} if this option sets successfully, {@code false} otherwise.
     * @see  #isNonMarketAppsAllowed()
     */
    @UnsupportedAppUsage
    public boolean setNonMarketAppsAllowed(boolean enabled) {
        try {
            return DeviceServer.getIDeviceService().setNonMarketAppsAllowed(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Whether applications can be installed for this user via the system's
     * {@link Intent#ACTION_INSTALL_PACKAGE} mechanism.
     * @return {@code true} if this system has allowed app installation via the system package installer intent, {@code false} otherwise.
     * @see  #setNonMarketAppsAllowed(boolean)
     */
    @UnsupportedAppUsage
    public boolean isNonMarketAppsAllowed() {
        try {
            return DeviceServer.getIDeviceService().isNonMarketAppsAllowed();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Sets the value to activate the installing non market app option.
     * @param enabled {@code true} If activates the installing non market app option, {@code false} otherwise.
     * @see #isNonMarketAppsAllowedOption()
     */
    @UnsupportedAppUsage
    public void setNonMarketAppsAllowedOption(boolean enabled) {
        try {
            DeviceServer.getIDeviceService().setNonMarketAppsAllowedOption(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Whether the installing non market app option is activated.
     * @return {@code true} If activates the installing non market app option, {@code false} otherwise.
     * @see #setNonMarketAppsAllowedOption(boolean)
     */
    @UnsupportedAppUsage
    public boolean isNonMarketAppsAllowedOption() {
        try {
            return DeviceServer.getIDeviceService().isNonMarketAppsAllowedOption();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * [ MDM Support ]
     *
     * Enable NFC hardware.
     *
     * <p>This call is asynchronous. Listen for
     * {@link #ACTION_ADAPTER_STATE_CHANGED} broadcasts to find out when the
     * operation is complete.
     *
     * <p>If this returns true, then either NFC is already on, or
     * a {@link #ACTION_ADAPTER_STATE_CHANGED} broadcast will be sent
     * to indicate a state transition. If this returns false, then
     * there is some problem that prevents an attempt to turn
     * NFC on (for example we are in airplane mode and NFC is not
     * toggleable in airplane mode on this platform).
     * @see #getNfcEnabled()
     */
    @UnsupportedAppUsage
    public int setNfcEnabled(boolean enabled) {
        try {
            return DeviceServer.getIDeviceService().setNfcEnabled(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * [ MDM Support ]
     *
     * Disable NFC hardware.
     *
     * <p>No NFC features will work after this call, and the hardware
     * will not perform or respond to any NFC communication.
     *
     * <p>This call is asynchronous. Listen for
     * {@link #ACTION_ADAPTER_STATE_CHANGED} broadcasts to find out when the
     * operation is complete.
     *
     * <p>If this returns true, then either NFC is already off, or
     * a {@link #ACTION_ADAPTER_STATE_CHANGED} broadcast will be sent
     * to indicate a state transition. If this returns false, then
     * there is some problem that prevents an attempt to turn
     * NFC off.
     * @see #setNfcEnabled(boolean)
     */
    @UnsupportedAppUsage
    public int getNfcEnabled() {
        try {
            return DeviceServer.getIDeviceService().getNfcEnabled();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * [ MDM Support ]
     *
     * Sets the value to activate the NFC enabled option.
     * @param enabled {@code true} If activates the NFC enabled option, {@code false} otherwise.
     * @see #isNfcEnabledOption()
     */
    @UnsupportedAppUsage
    public void setNfcEnabledOption(boolean enabled) {
        try {
            DeviceServer.getIDeviceService().setNfcEnabledOption(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Whether the NFC enabled option is activated.
     * @return {@code true} If activates the NFC enabled option, {@code false} otherwise.
     * @see #setNfcEnabledOption(boolean)
     */
    @UnsupportedAppUsage
    public boolean isNfcEnabledOption() {
        try {
            return DeviceServer.getIDeviceService().isNfcEnabledOption();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * [ MDM Support ]
     *
     * Gets the maximum adjusting resolution of the screen backlight brightness.
     * @return {@code int} The maximum resolution value up to 255.
     * @see #getScreenBrightness()
     */
    @UnsupportedAppUsage
    public int getScreenBrightnessMax() {
        try {
            return DeviceServer.getIDeviceService().getScreenBrightnessMax();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * [ MDM Support ]
     *
     * Gets the current value of the screen backlight brightness.
     * @return {@code int} The current brightness value between 0 and 255.
     * @see #setScreenBrightness(boolean, int)
     */
    @UnsupportedAppUsage
    public int getScreenBrightness() {
        try {
            return DeviceServer.getIDeviceService().getScreenBrightness();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * [ MDM Support ]
     *
     * Sets the value of the screen backlight brightness.
     * @param tracking {@code true} If it's performing a continuous action.
     * @param value The screen backlight brightness between 0 and 255.
     */
    @UnsupportedAppUsage
    public void setScreenBrightness(boolean tracking, int value) {
        try {
            DeviceServer.getIDeviceService().setScreenBrightness(tracking, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Returns true if this device is allowed all rotations.
     */
    @UnsupportedAppUsage
    public boolean areAllRotationsAllowed() {
        try {
            return DeviceServer.getIDeviceService().areAllRotationsAllowed();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * [ MDM Support ]
     *
     * Returns true if the rotation-lock toggle should be shown in system UI.
     */
    @UnsupportedAppUsage
    public boolean isRotationLockToggleVisible() {
        try {
            return DeviceServer.getIDeviceService().isRotationLockToggleVisible();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Returns the orientation that will be used when locking the orientation from system UI
     * with {@link #setRotationLock}.
     *
     * If the device only supports locking to its natural orientation, this will be either
     * Configuration.ORIENTATION_PORTRAIT or Configuration.ORIENTATION_LANDSCAPE,
     * otherwise Configuration.ORIENTATION_UNDEFINED if any orientation is lockable.
     */
    @UnsupportedAppUsage
    public int getRotationLockOrientation() {
        try {
            return DeviceServer.getIDeviceService().getRotationLockOrientation();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return Configuration.ORIENTATION_UNDEFINED;
    }

    /**
     * [ MDM Support ]
     *
     * Returns true if rotation lock is enabled.
     */
    @UnsupportedAppUsage
    public boolean isRotationLocked() {
        try {
            return DeviceServer.getIDeviceService().isRotationLocked();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Enables or disables rotation lock from the system UI toggle.
     */
    @UnsupportedAppUsage
    public void setRotationLock(boolean locked) {
        try {
            DeviceServer.getIDeviceService().setRotationLock(locked);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Gets the timeout period to turn the screen off.
     *
     * The amount of time in milliseconds before the device goes to sleep or begins
     * to dream after a period of inactivity.  This value is also known as the
     * user activity timeout period since the screen isn't necessarily turned off
     * when it expires.
     */
    @UnsupportedAppUsage
    public int getScreenOffTimeout() {
        try {
            return DeviceServer.getIDeviceService().getScreenOffTimeout();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * [ MDM Support ]
     *
     * Sets the expiry time to turn the screen off by automatic.
     * @param timeout The amount of time in milliseconds.
     */
    @UnsupportedAppUsage
    public boolean setScreenOffTimeout(int timeout) {
        try {
            return DeviceServer.getIDeviceService().setScreenOffTimeout(timeout);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Whether we keep the device on while the device is plugged in.
     * @return {@code true} if it keeps state to stay awake with plug, {@code false} otherwise.
     */
    @UnsupportedAppUsage
    public boolean isEnabledStayAwake() {
        try {
            return DeviceServer.getIDeviceService().isEnabledStayAwake();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Sets the state whether to keep to stay awake with plug.
     * @param enabled {@code true} to enable, {@code false} to disable.
     */
    @UnsupportedAppUsage
    public void setEnabledStayAwake(boolean enabled) {
        try {
            DeviceServer.getIDeviceService().setEnabledStayAwake(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Gets the package name of the mock location accepted application.
     * @return {@code String} if it has the mock location application.
     */
    @UnsupportedAppUsage
    public String getMockLocationApp() {
        try {
            return DeviceServer.getIDeviceService().getMockLocationApp();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * [ MDM Support ]
     *
     * Sets the mock location application package name one by one.
     * If there is already an existing application, it'll disable the previous
     * application mock location.
     */
    @UnsupportedAppUsage
    public boolean setMockLocationApp(String packageName) {
        try {
            return DeviceServer.getIDeviceService().setMockLocationApp(packageName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Check whether the current user has been blocked by device policy from uninstalling a package.
     *
     * @param packageName package to check.
     * @return true if uninstallation is blocked.
     */
    @UnsupportedAppUsage
    public boolean isUninstallBlocked(String packageName) {
        try {
            return DeviceServer.getIDeviceService().isUninstallBlocked(packageName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Called by profile or device owners to change whether a user can uninstall a package.
     *
     * @param packageName package to change.
     * @param uninstallBlocked true if the user shouldn't be able to uninstall the package.
     */
    @UnsupportedAppUsage
    public void setUninstallBlocked(String packageName, boolean uninstallBlocked) {
        try {
            DeviceServer.getIDeviceService().setUninstallBlocked(packageName, uninstallBlocked);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Called by profile or device owners to determine if a package is hidden.
     *
     * @param packageName The name of the package to retrieve the hidden status of.
     * @return boolean {@code true} if the package is hidden, {@code false} otherwise.
     */
    @UnsupportedAppUsage
    public boolean isApplicationHidden(String packageName) {
        try {
            return DeviceServer.getIDeviceService().isApplicationHidden(packageName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Called by profile or device owners to hide or unhide packages. When a package is hidden it
     * is unavailable for use, but the data and actual package file remain.
     *
     * @param packageName The name of the package to hide or unhide.
     * @param hidden {@code true} if the package should be hidden, {@code false} if it should be unhidden.
     * @return boolean Whether the hidden setting of the package was successfully updated.
     */
    @UnsupportedAppUsage
    public boolean setApplicationHidden(String packageName, boolean hidden) {
        try {
            return DeviceServer.getIDeviceService().setApplicationHidden(packageName, hidden);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Set the enabled setting for an application
     * This setting will override any enabled state which may have been set by the application in
     * its manifest.  It also overrides the enabled state set in the manifest for any of the
     * application's components.  It does not override any enabled state set by
     * {@link #setComponentEnabledSetting} for any of the application's components.
     *
     * @param packageName The package name of the application to enable
     * @param newState The new enabled state for the component.  The legal values for this state
     *                 are:
     *                   {@link #COMPONENT_ENABLED_STATE_ENABLED},
     *                   {@link #COMPONENT_ENABLED_STATE_DISABLED}
     *                   and
     *                   {@link #COMPONENT_ENABLED_STATE_DEFAULT}
     *                 The last one removes the setting, thereby restoring the applications's state to
     *                 whatever was set in its manifest (or enabled, by default).
     */
    @UnsupportedAppUsage
    public void setApplicationEnabledSetting(String packageName, int newState) {
        try {
            DeviceServer.getIDeviceService().setApplicationEnabledSetting(packageName, newState);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    /**
     * [ MDM Support ]
     *
     * Attempts to delete a package.  Since this may take a little while, the result will
     * be posted back to the given observer.
     *
     * @param packageName The name of the package to delete
     * @param flags - possible values: {@link #DELETE_KEEP_DATA},
     * {@link #DELETE_ALL_USERS}.
     */
    @UnsupportedAppUsage
    public void deletePackage(String packageName, int flags) {
        try {
            DeviceServer.getIDeviceService().deletePackage(packageName, flags);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Only useful for volume controllers.
     */
    @UnsupportedAppUsage
    public boolean isStreamAffectedByRingerMode(int streamType) {
        try {
            return DeviceServer.getIDeviceService().isStreamAffectedByRingerMode(streamType);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Only useful for volume controllers.
     */
    @UnsupportedAppUsage
    public int getRingerModeInternal() {
        try {
            return DeviceServer.getIDeviceService().getRingerModeInternal();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return AudioManager.RINGER_MODE_NORMAL;
    }

    /**
     * [ MDM Support ]
     *
     * Returns the maximum volume index for a particular stream.
     *
     * @param streamType The stream type whose maximum volume index is returned.
     * @return The maximum valid volume index for the stream.
     * @see #getStreamVolume(int)
     */
    @UnsupportedAppUsage
    public int getStreamMaxVolume(int streamType) {
        try {
            return DeviceServer.getIDeviceService().getStreamMaxVolume(streamType);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * [ MDM Support ]
     *
     * Returns the current volume index for a particular stream.
     *
     * @param streamType The stream type whose volume index is returned.
     * @return The current volume index for the stream.
     * @see #getStreamMaxVolume(int)
     * @see #setStreamVolume(int, int, int)
     */
    @UnsupportedAppUsage
    public int getStreamVolume(int streamType) {
        try {
            return DeviceServer.getIDeviceService().getStreamVolume(streamType);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * [ MDM Support ]
     *
     * Get last audible volume before stream was muted.
     */
    @UnsupportedAppUsage
    public int getLastAudibleStreamVolume(int streamType) {
        try {
            return DeviceServer.getIDeviceService().getLastAudibleStreamVolume(streamType);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * [ MDM Support ]
     *
     * Returns the current mute state for a particular stream.
     *
     * @param streamType The stream to get mute state for.
     * @return The mute state for the given stream.
     * @see #adjustStreamVolume(int, int, int)
     */
    @UnsupportedAppUsage
    public boolean isStreamMute(int streamType) {
        try {
            return DeviceServer.getIDeviceService().isStreamMute(streamType);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Adjusts the volume of a particular stream by one step in a direction.
     * <p>
     * This method should only be used by applications that replace the platform-wide
     * management of audio settings or the main telephony application.
     *
     * @param streamType The stream type to adjust. One of {@link #STREAM_VOICE_CALL},
     * {@link #STREAM_SYSTEM}, {@link #STREAM_RING}, {@link #STREAM_MUSIC} or
     * {@link #STREAM_ALARM}
     * @param direction The direction to adjust the volume. One of
     *            {@link #ADJUST_LOWER}, {@link #ADJUST_RAISE}, or
     *            {@link #ADJUST_SAME}.
     * @param flags One or more flags.
     * @see #setStreamVolume(int, int, int)
     */
    @UnsupportedAppUsage
    public void adjustStreamVolume(int streamType, int direction, int flags) {
        try {
            DeviceServer.getIDeviceService().adjustStreamVolume(streamType, direction, flags);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Sets the volume index for a particular stream.
     * <p>This method has no effect if the device implements a fixed volume policy
     * as indicated by {@link #isVolumeFixed()}.
     * @param streamType The stream whose volume index should be set.
     * @param index The volume index to set. See
     *            {@link #getStreamMaxVolume(int)} for the largest valid value.
     * @param flags One or more flags.
     * @see #getStreamMaxVolume(int)
     * @see #getStreamVolume(int)
     * @see #isVolumeFixed()
     */
    @UnsupportedAppUsage
    public void setStreamVolume(int streamType, int index, int flags) {
        try {
            DeviceServer.getIDeviceService().setStreamVolume(streamType, index, flags);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Indicates if the device implements a fixed volume policy.
     * <p>Some devices may not have volume control and may operate at a fixed volume,
     * and may not enable muting or changing the volume of audio streams.
     * This method will return true on such devices.
     */
    @UnsupportedAppUsage
    public boolean isVolumeFixed() {
        try {
            return DeviceServer.getIDeviceService().isVolumeFixed();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Gets the status to turn on and off or something the sound for each type.
     *
     * @param soundType The sound type. One of {@link #SOUND_DIALPAD},
     * {@link #SOUND_SCREEN_LOCKING}, {@link #SOUND_CHARGING}, {@link #SOUND_DOCKING}
     * {@link #SOUND_TOUCH}, {@link #SOUND_VIBRATE_ON_TOUCH}, {@link #SOUND_DOCK_AUDIO_MEDIA} or
     * {@link #SOUND_EMERGENCY}
     * @return Whether each type of sound is active or something.
     * @see #setEnabledOtherSounds(int, int)
     */
    @UnsupportedAppUsage
    public int getEnabledOtherSounds(int soundType) {
        try {
            return DeviceServer.getIDeviceService().getEnabledOtherSounds(soundType);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return DevInfoIndex.NOT_SUPPORTED;
    }

    /**
     * [ MDM Support ]
     *
     * Sets the value to turn on and off or something the sound for each type.
     *
     * @param soundType The sound type. One of {@link #SOUND_DIALPAD},
     * {@link #SOUND_SCREEN_LOCKING}, {@link #SOUND_CHARGING}, {@link #SOUND_DOCKING}
     * {@link #SOUND_TOUCH}, {@link #SOUND_VIBRATE_ON_TOUCH}, {@link #SOUND_DOCK_AUDIO_MEDIA} or
     * {@link #SOUND_EMERGENCY}
     * @param value Whether each type of sound is active or something.
     * @see #getEnabledOtherSounds(int)
     */
    @UnsupportedAppUsage
    public void setEnabledOtherSounds(int soundType, int value) {
        try {
            DeviceServer.getIDeviceService().setEnabledOtherSounds(soundType, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     *  Returns true if airplane mode is currently on
     */
    @UnsupportedAppUsage
    public boolean isAirplaneModeOn() {
        try {
            return DeviceServer.getIDeviceService().isAirplaneModeOn();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Change the airplane mode system setting
     */
    @UnsupportedAppUsage
    public void setAirplaneModeOn(boolean enabling) {
        try {
            DeviceServer.getIDeviceService().setAirplaneModeOn(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * The policy for deciding when Wi-Fi should go to sleep (which will in
     * turn switch to using the mobile data as an Internet connection).
     * <p>
     * Set to one of {@link #WIFI_SLEEP_POLICY_DEFAULT},
     * {@link #WIFI_SLEEP_POLICY_NEVER_WHILE_PLUGGED}, or
     * {@link #WIFI_SLEEP_POLICY_NEVER}.
     */
    @UnsupportedAppUsage
    public int getWifiSleepPolicy() {
        try {
            return DeviceServer.getIDeviceService().getWifiSleepPolicy();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return Settings.Global.WIFI_SLEEP_POLICY_NEVER;
    }

    /**
     * [ MDM Support ]
     *
     * Sets wifi sleep policy to never on while in sleep
     */
    @UnsupportedAppUsage
    public void setWifiSleepPolicy(int sleepPolicy) {
        try {
            DeviceServer.getIDeviceService().setWifiSleepPolicy(sleepPolicy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Gets the Wi-Fi enabled state.
     * @return One of {@link #WIFI_STATE_DISABLED},
     *         {@link #WIFI_STATE_DISABLING}, {@link #WIFI_STATE_ENABLED},
     *         {@link #WIFI_STATE_ENABLING}, {@link #WIFI_STATE_UNKNOWN}
     * @see #isWifiEnabled()
     */
    @UnsupportedAppUsage
    public int getWifiState() {
        try {
            return DeviceServer.getIDeviceService().getWifiState();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return WifiManager.WIFI_STATE_UNKNOWN;
    }

    /**
     * [ MDM Support ]
     *
     * Enable or disable Wi-Fi.
     * @param enabled {@code true} to enable, {@code false} to disable.
     * @return {@code true} if the operation succeeds (or if the existing state
     *         is the same as the requested state).
     */
    @UnsupportedAppUsage
    public boolean setWifiEnabled(boolean enabled) {
        try {
            return DeviceServer.getIDeviceService().setWifiEnabled(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Gets the Wi-Fi enabled state.
     * @return One of {@link #WIFI_AP_STATE_DISABLED},
     *         {@link #WIFI_AP_STATE_DISABLING}, {@link #WIFI_AP_STATE_ENABLED},
     *         {@link #WIFI_AP_STATE_ENABLING}, {@link #WIFI_AP_STATE_FAILED}
     * @see #isWifiApEnabled()
     */
    @UnsupportedAppUsage
    public int getWifiApState() {
        try {
            return DeviceServer.getIDeviceService().getWifiApState();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return WifiManager.WIFI_AP_STATE_FAILED;
    }

    /**
     * [ MDM Support ]
     *
     * Return whether Wi-Fi AP is enabled or disabled.
     * @return {@code true} if Wi-Fi AP is enabled
     * @see #getWifiApState()
     */
    @UnsupportedAppUsage
    public boolean isWifiApEnabled() {
        try {
            return DeviceServer.getIDeviceService().isWifiApEnabled();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Start AccessPoint mode with the specified
     * configuration. If the radio is already running in
     * AP mode, update the new configuration
     * Note that starting in access point mode disables station
     * mode operation
     * @param wifiConfig SSID, security and channel details as
     *        part of WifiConfiguration
     * @return {@code true} if the operation succeeds, {@code false} otherwise
     */
    // TODO: Commented out for not including WifiConfiguration
    // 20210817 mik.lee [#19425][SDK] SDK Checklist
    // Deprecated after Android 9
    @UnsupportedAppUsage
    public boolean setWifiApEnabled(boolean enabled) {
        try {
            return DeviceServer.getIDeviceService().setWifiApEnabled(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @hide
     * This function copy data to the file in the system area. (For internal use only)
     */
    @UnsupportedAppUsage
    public boolean writeBytesToSystemFile(String dest, byte[] buffer) {
        try {
            return DeviceServer.getIDeviceService().writeBytesToSystemFile(dest, buffer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    // START 20180117 irene.choi [#11288] Implement OSImageUpgrade API for MDM solution
    /**
     * [ MDM Support ]
     *
     * get os image upgrade result code
     * @return os image upgrade result code
     * <p><strong>NOTE:</strong> The rebooting is required to affect this setting.</p>
     * @param pathType PFU file path
     * @param filename PFU file name
     */
    @UnsupportedAppUsage
    public int osImageUpgrade(Context context, int pathType, String filename) {
        final int RESULT_CODE_ERROR_INVALID_PARAMETER = -1;
        final int RESULT_CODE_SUCCESS = 0;
        final int RESULT_CODE_ERROR_SDCARD_NOT_MOUNT = 6;
        final int RESULT_CODE_ERROR_NO_VALID_FILENAME = 7;
        final int RESULT_CODE_ERROR_MULTIPLE_VALID_FILENAMES = 8;
        final int RESULT_CODE_ERROR_BATTERY_CAPACITY_NOT_ENOUGH = 9;
        final int RESULT_CODE_ERROR_FAILED_COPY_FILES = 10; //to be done
        final int RESULT_CODE_ERROR_FREE_SPACE_NOT_ENOUGH = 11; //to be done

        final String PHONE_STORAGE = "/storage/emulated/0/";
        final String SD_CARD_STORAGE = "/storage/sdcard1/";

        final int FILE_PATH_PHONE_STORAGE = 0;
        final int FILE_PATH_SDCARD_STORAGE = 1;

        if (context == null) {
            return RESULT_CODE_ERROR_INVALID_PARAMETER;
        }

        int resultCode = RESULT_CODE_SUCCESS;

        String path;
        switch (pathType) {
            case FILE_PATH_PHONE_STORAGE:
                path = new String(PHONE_STORAGE);
                break;
            case FILE_PATH_SDCARD_STORAGE:
                path = new String(SD_CARD_STORAGE);
                if (!isMountedExternalSdCard()) {
                    resultCode = RESULT_CODE_ERROR_SDCARD_NOT_MOUNT;
                }
                break;
            default:
                return RESULT_CODE_ERROR_INVALID_PARAMETER;
        }

        if (filename.isEmpty() || filename == null) {
            int numberOfUpgradableFile = getNumberOfValidFile(new File(path));

            if (numberOfUpgradableFile <= 0) {
                resultCode = RESULT_CODE_ERROR_NO_VALID_FILENAME;
            } else if (numberOfUpgradableFile >= 2) {
                resultCode = RESULT_CODE_ERROR_MULTIPLE_VALID_FILENAMES;
            } else if (numberOfUpgradableFile == 1) {
                filename = getValidFile(new File(path));
            }
        } else {
            if (!isFileExists(new File(path), filename)) {
                resultCode = RESULT_CODE_ERROR_NO_VALID_FILENAME;
            }
        }

        if (!isEnoughBatteryCapacity(context)) {
            resultCode = RESULT_CODE_ERROR_BATTERY_CAPACITY_NOT_ENOUGH;
        }

        Intent intent = new Intent("android.settings.SDCARD_UPGRADE_SETTINGS");
        intent.putExtra("path", new String(path+filename));
        intent.putExtra("bypass", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return resultCode;
    }

    private boolean isMountedExternalSdCard() {
        File externalPath = new File("/storage/sdcard1/");
        if (externalPath.exists() && Environment.getStorageState(externalPath).equals("mounted")) {
            return true;
        } else {
            return false;
        }
    }

    private String getMajorNumber() {
        try {
            return String.valueOf(DeviceServer.getIDeviceService().getMajorNumber());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "";
    }

    private int getNumberOfValidFile(File filepath) {
        int index = 0;
        String keyword = getMajorNumber();
        if (keyword.isEmpty() || keyword == null) {
            return index;
        }
        File[] files = filepath.listFiles();
        if (files == null) {
            return index;
        }
        if (files.length > 0) {
            for (File file : files) {
                try {
                    String fileNamePrefix= file.getName().substring(0, keyword.length());
                    if (fileNamePrefix.equalsIgnoreCase(keyword) && file.getName().indexOf(".PFU") > -1) {
                        index++;
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return index;
    }

    private String getValidFile(File filepath) {
        String validFilename = null;
        String keyword = getMajorNumber();
        File[] files = filepath.listFiles();

        if (files.length > 0) {
            for (File file : files) {
                try {
                    String fileNamePrefix= file.getName().substring(0, keyword.length());
                    if (fileNamePrefix.equalsIgnoreCase(keyword) && file.getName().indexOf(".PFU") > -1) {
                        validFilename = file.getName();
                        break;
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return validFilename;
    }

    private boolean isFileExists(File filepath, String filename) {
        boolean isExists = false;

        File[] files = filepath.listFiles();
        if (files == null) {
            return isExists;
        }
        if (files.length > 0) {
            for (File file : files) {
                try {
                    if (file.getName().equals(filename) && file.getName().indexOf(".PFU") > -1) {
                        filename = file.getName();
                        isExists = true;
                        break;
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return isExists;
    }

    private boolean isEnoughBatteryCapacity(Context context) {
        Intent batteryStatus = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int plugged = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);

        int batteryPercent = level*100/scale;

        if (batteryPercent > 20 || (plugged == BatteryManager.BATTERY_PLUGGED_AC)) {
            return true;
        } else {
            return false;
        }
    }
    // END 20180117 irene.choi [#11288] Implement OSImageUpgrade API for MDM solution

    /**
     * [ MDM Support ]
     *
     * Gets the status whether the sound for all type is enabled.
     * All of {@link #SOUND_DIALPAD}, {@link #SOUND_SCREEN_LOCKING},
     * {@link #SOUND_CHARGING}, {@link #SOUND_DOCKING},
     * {@link #SOUND_TOUCH}, {@link #SOUND_VIBRATE_ON_TOUCH},
     * {@link #SOUND_DOCK_AUDIO_MEDIA} and {@link #SOUND_EMERGENCY}
     *
     * @return Whether each type of sound is active. If any type is disabled, it returns false.
     * @see #getEnabledOtherSounds(int, int)
     */
    @UnsupportedAppUsage
    public boolean isEnabledOtherSoundsAll() {
        try {
            return DeviceServer.getIDeviceService().isEnabledOtherSoundsAll();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Sets the status whether the sound for all type is enabled.
     * All of {@link #SOUND_DIALPAD}, {@link #SOUND_SCREEN_LOCKING},
     * {@link #SOUND_CHARGING}, {@link #SOUND_DOCKING},
     * {@link #SOUND_TOUCH}, {@link #SOUND_VIBRATE_ON_TOUCH},
     * {@link #SOUND_DOCK_AUDIO_MEDIA} and {@link #SOUND_EMERGENCY}
     *
     * {@link #SOUND_EMERGENCY} can't set {@link #EMERGENCY_TONE_VIBRATE}.
     *
     * @param value Whether each type of sound is active.
     * @see #getEnabledOtherSounds(int)
     */
    @UnsupportedAppUsage
    public void setEnabledOtherSoundsAll(boolean enabled) {
        try {
            DeviceServer.getIDeviceService().setEnabledOtherSoundsAll(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

	/**
     * enable Auto Join feature of WLAN
     * @param enable {@code true}
     * @see #isAutoJoin()
     */
    @UnsupportedAppUsage
    public void setAutoJoin(boolean enable){
      try {
        DeviceServer.getIDeviceService().setAutoJoin(enable);
      } catch (RemoteException e) {
          e.printStackTrace();
      }
    }

    /**
     * get Auto Join setting of WLAN
     * @return whether the auto join setting is enabled
     * @see #setAutoJoin()
     */
    @UnsupportedAppUsage
    public boolean isAutoJoin() {
        try {
          return DeviceServer.getIDeviceService().isAutoJoin();
        } catch (RemoteException e) {
            e.printStackTrace();
            //Default value is true.
            return true;
        }
    }

    /**
     * [ MDM Support ]
     *
     * Reboots the device.
     *
     * @param confirm If true, shows a reboot confirmation dialog.
     * @param reason The reason for the reboot, or null if none.
     * @param wait If true, this call waits for the reboot to complete and does not return.
     */
    @UnsupportedAppUsage
    public void reboot(boolean confirm, String reason, boolean wait) {
        try {
            DeviceServer.getIDeviceService().reboot(confirm, reason, wait);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * get Reconnection Interval setting for WLAN
     * @return return Reconnection periodic single interval for WLAN. Zero is failure.
     * @see #getReconnectionInterval()
     */
    @UnsupportedAppUsage
    public int getReconnectionInterval() {
        try {
            return DeviceServer.getIDeviceService().getReconnectionInterval();
          } catch (RemoteException e) {
              e.printStackTrace();
              //Default value is true.
              return 0;
          }
    }

    /**
     * Set Reconnection Interval setting for WLAN
     * Valid values of Interval : 5, 10, 15, 20, 25, 30, 60, 120, 160
     * @param reconnectionInterval Reconnection periodic single interval for WLAN.
     * @return return true if Interval is set correctly.
     * @see #setReconnectionInterval()
     */
    @UnsupportedAppUsage
    public boolean setReconnectionInterval(int reconnectionInterval){
        try {
          return DeviceServer.getIDeviceService().setReconnectionInterval(reconnectionInterval);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * [ MDM Support ]
     * Save the given network in the supplicant config. If the network already
     * exists, the configuration is updated. A new network is enabled
     * by default.
     *
     * For a new network, this function is used instead of a
     * sequence of addNetwork(), enableNetwork() and saveConfiguration().
     *
     * For an existing network, it accomplishes the task of updateNetwork()
     * and saveConfiguration()
     *
     * @param config the set of variables that describe the configuration,
     *            contained in a {@link WifiConfiguration} object.
     * @param listener for callbacks on success or failure. Can be null.
     * @throws IllegalStateException if the WifiManager instance needs to be
     * initialized again
     */
/* TODO: Commented out for not including WifiConfiguration
    @UnsupportedAppUsage
    public void saveWifiConfig(WifiConfiguration config) {
        try {
            DeviceServer.getIDeviceService().saveWifiConfig(config);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
*/

    /**
     * [ MDM Support ]
     *
     * FOTA Client menu of System Update in Settings set enabled / disabled.
     * @param enabled if enabled is 1, show the menu.
     */
    @UnsupportedAppUsage
    public void setEnableFOTAClientMenu(int enabled) {
        try {
            String model = DeviceServer.getIDeviceService().getModelName();
            if (model.equalsIgnoreCase("PM45")) {
                // Not supported
                return;
            }
            DeviceServer.getIDeviceService().setEnableFOTAClientMenu(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Whether FOTA Client menu of System Update in Settings is set enabled or not.
     * @return return 1 if fota client menu is enabled.
     */
    @UnsupportedAppUsage
    public int getEnableFOTAClientMenu() {
        int enabled = 1;
        try {
            String model = DeviceServer.getIDeviceService().getModelName();
            if (model.equalsIgnoreCase("PM45")) {
                // Not supported
                return enabled;
            }
            enabled = DeviceServer.getIDeviceService().getEnableFOTAClientMenu();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return enabled;
    }

    /**
     * [ MDM Support ]
     *
     * Airplane mode menu of power menu set enabled / disabled.
     * @param enabled if enabled is 1, show the menu.
     */
    @UnsupportedAppUsage
    public void setEnableAirplaneModeMenu(int enabled) {
        try {
            DeviceServer.getIDeviceService().setEnableAirplaneModeMenu(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * [ MDM Support ]
     *
     * Whether Airplane mode menu of power menu is set enabled or not.
     * @return return 1 if Airplane mode menu is enabled.
     */
    @UnsupportedAppUsage
    public int getEnableAirplaneModeMenu() {
        try {
            return DeviceServer.getIDeviceService().getEnableAirplaneModeMenu();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
    * This function write the USB priority.
    * @param usbType {@code String} USB type
    * <pre>
    * "0" : C-Type
    * "1" : Cradle
    * </pre>
    * @see #setUsbPriority
    */
    @UnsupportedAppUsage
    public boolean setUsbPriority(int usbType) {
        try {
            return DeviceServer.getIDeviceService().setUsbPriority(usbType);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
    * This function read the USB priority.
    * @return  type of USB with integer:
    * <pre>
    * "-1" : None
    *  "0" : C-Type
    *  "1" : Cradle
    * </pre>
    * @see #getUsbPriority
    */
    @UnsupportedAppUsage
    public int getUsbPriority() {
        try {
            return DeviceServer.getIDeviceService().getUsbPriority();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    // END 20180713 sam.kim [#12348] API

    /**
     * This function write the JeitaTestMode.
     * @param mode {@code integer} mode type
     * <pre>
     * "true" : Enable the JeitaTestMode
     * "false" : Disable the JeitaTestMode
     * </pre>
     * @see #getJeitaTestMode
     */
    @UnsupportedAppUsage
    public boolean setJeitaTestMode(int mode) {
        try {
            //DeviceServer.getIDeviceService().setJeitaTestMode(mode);
            return  DeviceServer.getIDeviceService().setJeitaTestMode(mode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
       return false;
    }

    /**
     * This function read JeitaTestMode.
     * @return  type of JeitaTestMode with integer:
     * <pre>
     * "-1" : None
     * "0" : TEST_MODE_RESTORE
     * "1" : TEST_MODE_B
     * "2" : TEST_MODE_G
     * </pre>
     * @see #setJeitaTestMode
     */
    @UnsupportedAppUsage
    public int getJeitaTestMode() {
        try {
            return DeviceServer.getIDeviceService().getJeitaTestMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * [ MDM Support ]
     *
     * Indicates whether the MTP function is restricted.
     * @return {@code true} if the MTP function is restricted, {@code false} otherwise.
     */
    @UnsupportedAppUsage
    public boolean isRestrictedMtp() {
        try {
            return DeviceServer.getIDeviceService().isRestrictedMtp();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Sets the value of the MTP function restriction.
     * @param enabled the MTP function with boolean
     */
    @UnsupportedAppUsage
    public boolean setRestrictedMtp(boolean enabled) {
        try {
            return DeviceServer.getIDeviceService().setRestrictedMtp(enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * DHCP NTP Server is set enabled / disabled.
     * @param enabled if enabled is true, using DHCP NTP Server.
     * @see #getDhcpNtpServer
     */
    @UnsupportedAppUsage
    public void setDhcpNtpServer(boolean enabled) {
        try {
            DeviceServer.getIDeviceService().setDhcpNtpServer(enabled ? 1 : 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Whether DHCP NTP Server is set enabled or not.
     * @return true if DHCP NTP server is enabled.
     * @see #setDhcpNtpServer
     */
    @UnsupportedAppUsage
    public boolean getDhcpNtpServer() {
        try {
            return (DeviceServer.getIDeviceService().getDhcpNtpServer() == 1) ? true : false;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    // START 20200527 mik.lee [#16420][Mobile] Modified Implemented ECT
    /**
     * [ MDM Support ]
     * Connects the two calls and disconnects the subscriber from both calls.
     * (3GPP 22.091)
     * @param phoneId Phone ID (0 : first sim slot, 1 : second sim slot)
     */
    @UnsupportedAppUsage
    public void explicitCallTransfer(int phoneId) {
        try {
            DeviceServer.getIDeviceService().explicitCallTransfer(phoneId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    // END 20200527 mik.lee [#16420][Mobile] Modified Implemented ECT

    // START 20210702 mik.lee [Emkit][WLAN] Add Hotspot API
    /**
    * This API applies the hotspot configuration on the device.
    * When enabled, the device acts as an internet access point for nearby devices, sharing its cellurar connection.
    * @param config {@code WifiConfiguration} Wificonfiguration for hotspot profile. 
    * Please refer to android document for WifiConfiguration
    * @return {@code true} if the operation succeeds, {@code false} otherwise
    */
    @UnsupportedAppUsage
    public boolean setHotspot() {
        try {
            return DeviceServer.getIDeviceService().setHotspot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
    * Used to enter the Wi-Fi hotspot Service Set Identifier (SSID).
    * @param ssid {@code string} from 1–32 characters containing the desired SSID (case sensitive)
    * @return {@code true} if the operation succeeds, {@code false} otherwise
    */
    @UnsupportedAppUsage
    public boolean setHotspotSSID(String ssid) {
        try {
            return DeviceServer.getIDeviceService().setHotspotSSID(ssid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
    * Controls whether user credentials is required on a device before allowing it to connect to the Wi-Fi hotspot.
    * @param SecurityMode {@code integer} SecurityMode type
    * <pre>
    * "0" : Open
    * "1" : WPA2-PSK
    * </pre>
    * @return {@code true} if the operation succeeds, {@code false} otherwise
    */
    @UnsupportedAppUsage
    public boolean setHotspotSecurityMode(int securityMode) {
        try {
            return DeviceServer.getIDeviceService().setHotspotSecurityMode(securityMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
    * Used to provide a password for accessing the hotspot and to store it in encrypted form.
    * @param passPhrase {@code string} String with a minimum of 8 characters and a maximum of 63 characters (case sensitive)
    * <pre>
    * "0" : Open
    * "1" : WPA2-PSK
    * </pre>
    * @return {@code true} if the operation succeeds, {@code false} otherwise
    */
    @UnsupportedAppUsage
    public boolean setHotspotPassphraseEncrypted(String passPhrase) {
        try {
            return DeviceServer.getIDeviceService().setHotspotPassphraseEncrypted(passPhrase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
    * Used to specific which RF band the device will use for Hotspot.
    * @param band {@code interger} specific RF band
    * <pre>
    * "0" : 2.4 GHz
    * "1" : 5 GHz
    * </pre>
    * @return {@code true} if the operation succeeds, {@code false} otherwise
    */
    @UnsupportedAppUsage
    public boolean setHotspotBand(int band) {
        try {
            return DeviceServer.getIDeviceService().setHotspotBand(band);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
    * This API makes stop the hotspot on the device.
    * @return {@code true} if the operation succeeds, {@code false} otherwise
    */
    @UnsupportedAppUsage
    public boolean stopHotspot() {
        try {
            return DeviceServer.getIDeviceService().stopHotspot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
    * * This API gets the current status of hotspot on the device.
    * @return {@code true} if the operation succeeds, {@code false} otherwise.
    */
    @UnsupportedAppUsage
    public boolean getHotspotStatus() {
        try {
            return DeviceServer.getIDeviceService().getHotspotStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // END 20210702 mik.lee [Emkit][WLAN] Add Hotspot API

    /**
     * This API control WFC(WiFi Calling) status.
     * @param enable {@code boolean} status of WFC.
     * <pre>
     * "true" : Set enable
     * "false" : Set disable
     * </pre>
     */
    @UnsupportedAppUsage
    public void setWFCmode(boolean enable){
        try {
            DeviceServer.getIDeviceService().setWFCmode(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * * This API gets the current status of WFC.
     * @return {@code true} if the WFC is enabling, {@code false} otherwise.
     */
    @UnsupportedAppUsage
    public boolean getWFCMode(){
        try {
            return DeviceServer.getIDeviceService().getWFCMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * * This API control WFC(WiFi Calling) preferrednce status
     * @param preferredMode {@code int} current preferred mode
     * <pre>
     * "0" : WIFI_ONLY
     * "1" : CELLULAR_PREFERRED
     * "2" : WIFI_PREFERRED
     * </pre>
     */
    @UnsupportedAppUsage
    public void setWFCPreferredMode(int preferredMode){
        try {
            DeviceServer.getIDeviceService().setWFCPreferredMode(preferredMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ;
    }

    /**
     * * This API get current status WFC(WiFi Calling).
     * @return Current WFC Preferred Mode,
     * <pre>
     * "0" : WIFI_ONLY
     * "1" : CELLULAR_PREFERRED
     * "2" : WIFI_PREFERRED
     * </pre>
     */
    @UnsupportedAppUsage
    public int getWFCPreferredMode(){
        try {
            return DeviceServer.getIDeviceService().getWFCPreferredMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * * This API controls Enhanced 4G LTE Mode.
     * @param enable {@code boolean} status
     * <pre>
     * "true" : Enable Enhanced 4G LTE Mode
     * "false" : Disable Enhanced 4G LTE Mode
     * </pre>
     */
    @UnsupportedAppUsage
    public void setEnhanced4GLTEMode(boolean enabled){
        try {
            DeviceServer.getIDeviceService().setEnhanced4GLTEMode(enabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ;
    }

    /**
     * * This API get current status of Enhanced 4G LTE Mode.
     * @return {@code true} if the Enhanced 4G LTE Mode is enalbed, {@code false} otherwise.
     */
    @UnsupportedAppUsage
    public boolean getEnhanced4GLTEMode(){
        try {
            return DeviceServer.getIDeviceService().getEnhanced4GLTEMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [ MDM Support ]
     *
     * Does a factory reset.
     * @param eraseSdCard {@code boolean} wipe the data on SD-Card during factory reset
     * @param eraseEsims {@code boolean}  wipe the data on eSIM during factory reset for the device with eSIM.
     */
    public void doMasterClear(boolean eraseSdCard, boolean eraseEsims) {
        try {
            DeviceServer.getIDeviceService().doMasterClear(eraseSdCard, eraseEsims);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
