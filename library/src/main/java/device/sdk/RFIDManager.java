package device.sdk;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import android.compat.annotation.UnsupportedAppUsage;
import android.util.Log;

import device.sdk.DeviceServer;
import device.common.rfid.IRFIDCallback;
import device.common.rfid.RFIDCallback;
import device.common.rfid.RFIDConst;
import device.common.rfid.StatusEvent;
import device.common.rfid.ModeOfInvent;
import device.common.rfid.ParamOfInvent;
import device.common.rfid.ReportFormatOfInvent;
import device.common.rfid.BattEvent;
import device.common.rfid.BattEvent_ext;
import device.common.rfid.SelConfig;
import device.common.rfid.ChannelState;
import device.common.rfid.TxCycle;
import device.common.rfid.ReportFormatOfInvent_ext;
import device.common.rfid.AntSelect;
import device.common.rfid.AntPower;
import device.common.rfid.CustomIntentConfig;
import device.common.rfid.AccessTag;


/**
  * This class enables user applications to connect, configure and control
  * RFID reader.<br />
  * The device may connect to RFID reader via Bluetooth, USB or UART interface.
  * <br />
  * This class is designed as a singleton.
  * Rather than using <code>new</code> keyword, use static method
  * {@link #getInstance() getInstance} to get (or create new) instance
  * of this class.<br />
  *
  */
public class RFIDManager {

    private static final String TAG = RFIDManager.class.getSimpleName();
    private static RFIDManager mInstance = null;
   /**
     * Returns (or creates) a singleton instance of RFID Manager.
     * Rather than using <code>new</code> keyword, use this method to get
     * instance of this class.
     *
     * @return an instance of this class.
     */
    @UnsupportedAppUsage
    public static RFIDManager getInstance() {
        if (mInstance == null) {
            mInstance = new RFIDManager();
        }
        return mInstance;
    }

    /**
      * Default constructor.
      * Rather than using <code>new</code> keyword,
      * use the static method {@link #getInstance() getInstance} to get instance
      * of this class.
      * @see device.sdk.RFIDManager#getInstance
      */
    @UnsupportedAppUsage
    public RFIDManager() {}

    /**
     * Registers a callback handler to be invoked when RFID reader state is changed.
     *
     * @param callback The handler class which implements callback methods defined by {@link device.common.rfid.RFIDCallback}.
     *
     * @return
     * <code>true</code> if registration is successful;
     * <code>false</code> otherwise
     */
    @UnsupportedAppUsage
    public boolean RegisterRFIDCallback(RFIDCallback callback) {
        try {
            return DeviceServer.getIRFIDService().RegisterRFIDCallback(callback.getRFIDCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Unregisters a callback handler that was registered.
     *
     * @param callback The handler class which implements callback methods
     * defined by {@link device.common.rfid.RFIDCallback}.
     *
     * @return
     * <code>true</code> if unregistration is successful;
     * <code>false</code> otherwise
     */
    @UnsupportedAppUsage
    public boolean UnregisterRFIDCallback(RFIDCallback callback) {
        try {
            return DeviceServer.getIRFIDService().UnregisterRFIDCallback(callback.getRFIDCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }			
        return false;
    }

    /**
     * Connects to the RFID reader in the Bluetooth mode.
     * Try to connect to the RFID reader (designated by <code>macAddress</code>
     * and <code>deviceName</code>) via Bluetooth.
     * To check whether the connection is finished in success or not,
     * register a handler which implements call back method
     * <code>RFIDCallback.onNotifyChangedState(int)</code> beforehand.
     * For successful connection, the state will be given as
     * <code>device.common.rfid.RFIDConst.DeviceState.BT_CONNECTED</code>.
     * Otherwise, the state <code>device.common.rfid.RFIDConst.DeviceState.BT_CONNECT_FAILED</code> will be given.
     * Warning: Calling this method closes Bluetooth connection
     * (currently establishing and already established).
     *
     * <p />NOTE: This method is incompatible with UART-based RFID readers,
     * such as PM500.
     *
     * @param macAddress the hardware address of the RFID reader to connect to
     * @param deviceName the device name of the RFID reader to connect to
     *
     * @return void
     *
     */
    @UnsupportedAppUsage
    public void ConnectBTDevice(String macAddress, String deviceName) {
        try {
            DeviceServer.getIRFIDService().connectBluetooth(macAddress, deviceName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnects from the RFID reader in the Bluetooth mode.
     *
     * <p />NOTE: This method is incompatible with UART-based RFID readers,
     * such as PM500.
     *
     * @return void
     *
     */
    @UnsupportedAppUsage
    public void DisconnectBTDevice() {
        try {
            DeviceServer.getIRFIDService().disconnectBluetooth();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens an interface of the RFID reader.&nbsp;(should be called explicitly before using any other RFID APIs).
     * <p />
     * Only one interface at a time is allowed.
     * In case of switching interface (for example, from Bluetooth to USB),
     * call {@link #Close} and close any current (existing) interface
     * before opening a new interface.
     * <p />
     * For Bluetooth type RFID reader:<br />
     * before calling this method, call {@link #ConnectBTDevice}
     * and make sure the connection is successfully established.
     *
     * @param openDevice the device type to connect.<br />
     * {@link device.common.rfid.RFIDConst.DeviceType#DEVICE_BT }
     *  for Bluetooth connection.<br />
     * {@link device.commond.rfid.RFIDConst.DeviceType#DEVICE_USB }
     *  for USB connection(NOTE: This type is supported for PM30 only).<br />
     * {@link device.common.rfid.RFIDConst.DeviceType#DEVICE_UART }
     *  for UART connection.<br />
     *
     * @return
     * {@link device.common.rfid.RFIDConst.CommandErr#SUCCESS }
     * for success in opening interface;
     * {@link device.common.rfid.RFIDConst.CommandErr#OPEN_FAILED }
     * otherwise.
     * @see  device.sdk.RFIDManager#IsOpened
     *
     */
    @UnsupportedAppUsage
    public int Open(int openDevice) {
        try {
            return DeviceServer.getIRFIDService().open(openDevice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Closes current interface of the RFID reader.
     *
     * @return void
     *
     */
    @UnsupportedAppUsage
    public void Close() {
		try {
            DeviceServer.getIRFIDService().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Queries whether the RFID reader is opened.
     * @return
     * true if RFID reader is opened,
     * false otherwise
     *
     * @see  device.sdk.RFIDManager#Open
     *
     */
    @UnsupportedAppUsage
    public boolean IsOpened() {
        try {
            return DeviceServer.getIRFIDService().isOpened();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @hide
     * Configures Bluetooth parameters for the RFID reader.
     * <p />
     * Before calling this method, the RFID reader must be connected and
     * opened an interface as Bluetooth.
     * <p />
     * After this method call, connection and opened interface to the RFID reader will be disconnected and closed.
     * <p />
     * If the RFID reader is connected with other than Bluetooth, COMM_ERR will
     * be returned.
     *
     * @param pinCode PIN code of the RFID reader to set
     * @param localName name of the RFID reader to set
     *
     * @return
     * {@link device.common.rfid.RFIDConst.CommandErr#SUCCESS }
     * for success in parameter configuration,
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR }
     * otherwise.
     *
     */
    @Deprecated
    @UnsupportedAppUsage
    public int SetBtConfig(int pinCode, String localName) {
		try {
            return DeviceServer.getIRFIDService().cmdSetBTConfig(pinCode, localName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Gets the name of the connected RFID reader.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @return the name of the RFID reader.
     *
     * @see  device.sdk.RFIDManager#GetBtDevice
     *
     */
    @UnsupportedAppUsage
    public String GetBtDevice() {
		try {
            return DeviceServer.getIRFIDService().cmdGetBTDeviceName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;        
    }

    /**
     * Gets the MAC address of the connected RFID reader.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @return the MAC address of the RFID reader.
     *
     * @see  device.sdk.RFIDManager#GetBtMacAddr
     *
     */
    @UnsupportedAppUsage
    public String GetBtMacAddr() {
        try {
            return DeviceServer.getIRFIDService().cmdGetBTMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;        
    }

    /**
     * @hide
     * Reads data from the memory of RFID reader.
     *
     * Fetches <code>dataCount</code> items, starting from
     * <code>memIndex</code>.
     * <p />
     * This method always returns
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED},
     * which means this function is not supported.
     *
     * @param memIndex index of an item to start from (0 to 999)
     * @param dataCount number of items to fetch (1 to 100)
     * @return always
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED}
     *
     */
    @Deprecated 
    @UnsupportedAppUsage
    public int UploadData(int memIndex, int dataCount) {
        // not supported function
        return RFIDConst.CommandErr.NOT_SUPPORTED;
    }

    /**
     * @hide
     * Clears all data in the memory of RFID reader.
     * <p />
     * This method always returns
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED},
     * which means this function is not supported.
     *
     * @return always
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED}
     */
    @Deprecated 
    @UnsupportedAppUsage
    public int ClearData() {
        // not supported function
        return RFIDConst.CommandErr.NOT_SUPPORTED;
    }

    /**
     * Initializes the RFID reader to the default configuration.
     * <p />
     * The default parameters are as below.
     * <table border='1' cellspacing='0' cellpadding='3'>
     * <tr><th>Parameter</th><th>Default value</th></tr>
     * <tr><td>Beep Volume</td><td>Max (2)</td></tr>
     * <tr><td>Vibrator</td><td>Disable (0)</td></tr>
     * <tr><td>Report State</td><td>Trigger / Low Battery</td></tr>
     * <tr><td>Report Battery</td><td>Disable</td></tr>
     * <tr><td>TX Cycle</td><td>On: 200ms, Off: 0ms (100%)</td></tr>
     * <tr><td>TX Power</td><td>MaxPower: 30dBm (0)</td></tr>
     * <tr><td>Session</td><td>Session_1 (1)</td></tr>
     * <tr><td>Q value</td><td>32 tags (5)</td></tr>
     * <tr><td>Target</td><td>Target_A (0)</td></tr>
     * <tr><td>MASK</td><td>Disable</td></tr>
     * <tr><td>LINK Profile</td><td>1</td></tr>
     * <tr><td>FastID</td><td>Disable</td></tr>
     * <tr><td>Tag Focus</td><td>Disable</td></tr>
     * <tr><td>Data Format</td><td>PC+EPC+CRC (0)</td></tr>
     * <tr><td>Transmission Format</td><td>Data As Is (0)</td></tr>
     * <tr><td>Prefix Data</td><td>NULL (0)</td></tr>
     * <tr><td>Suffix Data</td><td>NULL (0)</td></tr>
     * <tr><td>RFID Data Case</td><td>Lower (0)</td></tr>
     * </table>
     *
     * @return
     * {@link device.common.rfid.RFIDConst.CommandErr#SUCCESS }
     * for success in parameter configuration,
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR }
     * otherwise
     */
    @UnsupportedAppUsage
    public int SetDefaultConfig() {
        try {
           return DeviceServer.getIRFIDService().cmdSetDefaultParameter();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Gets the firmware version of RFID reader.
     *
     * @return a firmware version string, or null if error occurred
     */
    @UnsupportedAppUsage
    public String GetFwVersion() {
        try {
            return DeviceServer.getIRFIDService().cmdGetFwVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @hide
     * Turns off power of RFID reader.
     *
     * The host device may receive notification of power-off event if 
     * <code>StatusEvent.powerOff</code> is set to 1 (enabled).
     * <p />
     * This method always returns
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED},
     * which means this function is not supported.
     *
     * @return always
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED}
     *
     * @see #GetReaderStateEvent(StatusEvent)
     * @see #SetReaderStateEvent(StatusEvent)
     */
    @Deprecated 
    @UnsupportedAppUsage
    public int PowerOff() {
        // not supported function
        return RFIDConst.CommandErr.NOT_SUPPORTED;
    }

    /**
     * @hide
     * Gets the power-off time value of RFID reader if not used.
     * The RFID reader may automatically turn off its power if left idle for
     * speficied duration (unit: seconds).
     * The value may span between 0 (no automatic power-off) and 86400 (24 hrs).
     * <p />
     * This method always returns
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED},
     * which means this function is not supported.
     *
     * @return always
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED}
     *
     * @see #SetAutoPowerOffTimeout(void)
     */
    @Deprecated 
    @UnsupportedAppUsage
    public int GetAutoPowerOffTimeout() {
        // not supported function
        return RFIDConst.CommandErr.NOT_SUPPORTED;
    }

    /**
     * @hide
     * Sets the power-off time value of RFID reader if not used.
     * The RFID reader may automatically turn off its power if left idle for
     * speficied duration (unit: seconds).
     * The value may range between 0 (no automatic power-off) and 86400
     *  (24 hours).
     * <p />
     * This method always returns
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED},
     * which means this function is not supported.
     *
     * @return always
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED}
     *
     * @see #GetAutoPowerOffTimeout(void)
     */
    @Deprecated 
    @UnsupportedAppUsage
    public int SetAutoPowerOffTimeout() {
        // not supported function
        return RFIDConst.CommandErr.NOT_SUPPORTED;
    }

    /**
     * Gets the internal timer value of RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @return the timer value (between 0x0 and 0xFF FF FF FF, unit: msec), 
     * or {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR} for
     *  communication problem.
     *
     * @see  #SetTimer(int)
     *
     */
    @UnsupportedAppUsage
    public int GetTimer() {
        try {
            return DeviceServer.getIRFIDService().cmdGetTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Sets the internal timer value of RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param time initial timer value to set. Valid range is:
     * <pre>
     * 0 ~ 0xFFFFFFFF (unit: msec)
     * </pre>
     *
     * @return
     * {@link device.common.rfid.RFIDConst.CommandErr#SUCCESS }
     * for success in setting;
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR }
     * otherwise.
     *
     * @see  #GetTimer()
     *
     */
    @UnsupportedAppUsage
    public int SetTimer(int time) {
        try {
            return DeviceServer.getIRFIDService().cmdSetTimer(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }   

    /**
     * Gets the buzzer volume of RFID reader.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @return the buzzer volume.
     * {@link device.common.rfid.RFIDConst.DeviceConfig#BUZZER_HIGH}
     *  : buzzer volume is set to high.<br />
     * {@link device.common.rfid.RFIDConst.DeviceConfig#BUZZER_LOW}
     *  : buzzer volume is set to low.<br />
     * {@link device.common.rfid.RFIDConst.DeviceConfig#BUZZER_MUTE}
     *  : buzzer volume is set to muted.<br />
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR}
     *  : communication error.
     * @see  #SetBuzzerVol(int)
     *
     */
    @UnsupportedAppUsage
    public int GetBuzzerVol() {
        try {
            return DeviceServer.getIRFIDService().cmdGetBuzzerVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }   

    /**
     * This function sets the buzzer volume of RFID reader.
     * The setting value is stored in non-volatile internal memory.
     * The setting value will remain persistent after power-off and on.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @param volume the buzzer volume to set.<br />
     * {@link device.common.rfid.RFIDConst.DeviceConfig#BUZZER_HIGH}<br />
     * {@link device.common.rfid.RFIDConst.DeviceConfig#BUZZER_LOW}<br />
     * {@link device.common.rfid.RFIDConst.DeviceConfig#BUZZER_MUTE}
     *
     * @return whether set success. Return type is as below.
     * {@link device.common.rfid.RFIDConst.CommandErr#SUCCESS }
     * for success in setting.<br />
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR }
     * for communication error.<br />
     * {@link device.common.rfid.RFIDConst.CommandErr#WRONG_PARAM }
     * for invalid argument.
     *
     * @see  #GetBuzzerVol(void)
     *
     */
    @UnsupportedAppUsage
    public int SetBuzzerVol(int volume) {
        try {
            return DeviceServer.getIRFIDService().cmdSetBuzzerVolume(volume);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }   

    /**
     * Queries current battery level of RFID reader.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @return the battery level (0 - 100), or
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR }
     * if communication error.
     *
     */
    @UnsupportedAppUsage
    public int GetBattLevel() {
        try {
            return DeviceServer.getIRFIDService().cmdGetBatteryLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Queries the subscription status on each battery-related events
     * for callback notifications from RFID reader.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @param battEvent the container for query result.
     * <pre>
     * public class BattEvent {
     * public int batt;
     *     whether it receive the event of battery level
     *     1 means enabled and 0 means disabled.
     * public int charge;
     *     whether it receive the event of charge status
     *     1 means enabled and 0 means disabled.
     * public int cycle;
     *     the cycle of an event
     *     Range from 0 to 100. Real cycle is (cycle * 1.8) sec.
     * }
     * </pre>
     *
     * @return whether query was successful or not. Note that return value does 
     * not contain actual query results. Instead, look for battEvent argument
     * to check what returned from query. <br />
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * </pre>
     *
     * @see  #SetBattEvent(BattEvent)
     *
     */
    @Deprecated
    @UnsupportedAppUsage
    public int GetBattEvent(BattEvent battEvent) {
        try {
            return DeviceServer.getIRFIDService().cmdGetBatteryEvent(battEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }   

    /**
     * @hide
     * Modifies the subscription status on each battery-related events
     * for callback notifications from RFID reader.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @param battEvent defines subscription status on battery-related events.
     * BattEvent type is as below.
     * <pre>
     * public class BattEvent {
     * public int batt;
     *     whether it receive the event of battery level
     *     1 means enabled and 0 means disabled.
     * public int charge;
     *     whether it receive the event of charge status
     *     1 means enabled and 0 means disabled.
     * public int cycle;
     *     the cycle of an event
     *     Range from 0 to 100. Real cycle is (cycle * 1.8) sec.
     * }
     * </pre>
     *
     * @return whether modification was successful or not. 
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  #GetBattEvent(BattEvent)
     *
     */
    @Deprecated
    @UnsupportedAppUsage
    public int SetBattEvent(BattEvent battEvent) {
        try {
            return DeviceServer.getIRFIDService().cmdSetBatteryEvent(battEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Queries the subscription status on connection (link) related events for
     * callback notifications from RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @return callback notification status. Possible values are:
     * <pre>
     * 0 // callback notification is disabled
     * 1 // callback notification is enabled
     * RFIDConst.CommandErr.COMM_ERR = -1 // an error occurred while querying
     * </pre>
     *
     * @see  #SetConnectStateEvent(int)
     *
     */
    @UnsupportedAppUsage
    public int GetConnectStateEvent() {
        try {
            return DeviceServer.getIRFIDService().cmdGetConnectStateReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Modifies the subscription status on connection (link) related events for
     * callback notifications from RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param f_link callback notification status. Possible values are:
     * <pre>
     * 0 // disables callback notification
     * 1 // enables callback notification
     * </pre>
     *
     * @return whether modification was successful or not. 
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * </pre>

     *
     * @see  device.sdk.RFIDManager#GetConnectStateEvent
     *
     */
    @UnsupportedAppUsage
    public int SetConnectStateEvent(int f_link) {
        try {
            return DeviceServer.getIRFIDService().cmdSetConnectStateReport(f_link);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Queries current settings on receiving callback notifications for status
     * changes from RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param statusEvent the container for query result.
     * <pre>
     * public class StatusEvent {
     *     public int linkState;
     *         // flag for link status event.
     *         // 1 means enabled and 0 means disabled.
     *     public int triggerState;
     *         // flag for the trigger event.
     *         // 1 means enabled and 0 means disabled.
     *     public int lowBatt;
     *         // flag for low-battery event.
     *         1 means enabled and 0 means disabled.
     *     public int autoOff;
     *         // flag for auto power-off event
     *         // 1 means enabled and 0 means disabled.
     *         // Note: this event is not supported.
     *     public int powerOff;
     *         // flag for the power-off event.
     *         // 1 means enabled and 0 means disabled.
     *         // Note: this event is not supported.
     * }
     * </pre>
     *
     * @return whether query was success or not. Note that return value does
     * not contain actual query results. Instead, look for statusEvent argument
     * to check what returned from query. <br />
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetReaderStateEvent
     *
     */
    @UnsupportedAppUsage
    public int GetReaderStateEvent(StatusEvent statusEvent) {
        try {
            return DeviceServer.getIRFIDService().cmdGetReaderStateReport(statusEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Modifies current settings on receiving callback notifications for status
     * changes from RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param statusEvent defines settings on status events.
     * <pre>
     * public class StatusEvent {
     *     public int linkState;
     *         // flag for link status event.
     *         // 1 means enabled and 0 means disabled.
     *     public int triggerState;
     *         // flag for the trigger event.
     *         // 1 means enabled and 0 means disabled.
     *     public int lowBatt;
     *         // flag for low-battery event.
     *         1 means enabled and 0 means disabled.
     *     public int autoOff;
     *         // flag for auto power-off event
     *         // 1 means enabled and 0 means disabled.
     *         // Note: this event is not supported.
     *     public int powerOff;
     *         // flag for the power-off event.
     *         // 1 means enabled and 0 means disabled.
     *         // Note: this event is not supported.
     * }
     * </pre>
     *
     * @return whether modification was successful or not. 
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetReaderStateEvent
     *
     */
    @UnsupportedAppUsage
    public int SetReaderStateEvent(StatusEvent statusEvent) {
        try {
            return DeviceServer.getIRFIDService().cmdSetReaderStateReport(statusEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }   

    /**
     * Command RFID reader to starts inventory tagging.
     * A report on an identified tag will be notified to callback function
     * {@link device.common.rfid.RFIDCallback#onNotifyReceivedPacket}.
     * The report may include ID, time, and RSSI of the identified tag.
     * Use {@link #SetInventoryReportFormat} to configure format of the report.
     * <p />
     * This method uses existing (or default) options.
     * To override existing (or default) options, call {@link #SetOperationMode}
     * before invoking this method.
     * Alternatively, {@link #StartInventory_ext} not only starts inventory
     * tagging, but also provides several input arguments for option overriding.
     * For more information on the options, visit {@link #SetOperationMode}.
     *
     * @return whether command was successful or not. 
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetOperationMode
     * @see  device.sdk.RFIDManager#GetOperationMode
     * @see  #StartInventory_ext
     *
     */
    @UnsupportedAppUsage
    public int StartInventory() {
		try {
		    return DeviceServer.getIRFIDService().cmdStartInventory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Command RFID reader to starts inventory tagging with option overriding.
     * For more information on the options or parameters, visit 
     * {@link #SetOperationMode}.
     *
     * @param single tagging mode.
     * @param mode selects mode.
     * @param timeout inventory timeout.
     *
     * @return whether command was successful or not. 
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#StartInventory
     * @see  device.sdk.RFIDManager#SetOperationMode
     * @see  device.sdk.RFIDManager#GetOperationMode
     *
     */
    @Deprecated
    @UnsupportedAppUsage
    public int StartInventory_ext(int single, int mode, int timeout) {
		try {
		    return DeviceServer.getIRFIDService().cmdStartInventory_ext(single, mode, timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Command RFID reader to stop ongoing command.
     *
     * @return whether command was successful or not. 
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     */
    @UnsupportedAppUsage
    public int Stop() {
		try {
		    return DeviceServer.getIRFIDService().cmdStopOperation();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries the report format of RFID inventory.
     *
     * @param reportFormatOfInvent the container for query result.
     * <pre>
     * public class ReportFormatOfInvent implements Parcelable {
     *     public int time;
     *         // whether the report includes internal timer value.
     *         // 1 means enabled and 0 means disabled.
     *     public int rssi;
     *         // whether the report includes RSSI value.
     *         // 1 means enabled and 0 means disabled.
     * }
     * </pre>
     *
     * @return whether query was success or not. Note that return value does
     * not contain actual query results. Instead, look for
     * reportFormatOfInvent argument to check what returned from query. <br />
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetInventoryReportFormat
     *
     */
    @UnsupportedAppUsage
    public int GetInventoryReportFormat(ReportFormatOfInvent reportFormatOfInvent) {
        try {
            return DeviceServer.getIRFIDService().cmdGetInventoryReportFormat(reportFormatOfInvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies the report format of RFID inventory
     *
     * @param reportFormatOfInvent defines settings on report format of inventory.
     * <pre>
     * public class ReportFormatOfInvent implements Parcelable {
     *     public int time;
     *         // whether it include internal timer value.
     *         // 1 means enabled and 0 means disabled.
     *     public int rssi;
     *         // whether it include rssi value.
     *         // 1 means enabled and 0 means disabled.
     * }
     * </pre>
     *
     * @return whether modification was successful or not. 
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetInventoryReportFormat
     *
     */
    @UnsupportedAppUsage
    public int SetInventoryReportFormat(ReportFormatOfInvent reportFormatOfInvent) {
        try {
            return DeviceServer.getIRFIDService().cmdSetInventoryReportFormat(reportFormatOfInvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries tag selection filter.
     * A subset of tags which satisfy the condition of filter are marked as
     * "selected".
     * Calling {@link #StartInventory_ext} method with select mode 
     * (or exclusion select mode) results in responses from only tags marked
     * as "selected" (or doesn't marked as "selected"). 
     *
     * @param selConfig the container for query result.
     * <pre>
     * public class SelConfig {
     *     public int index;
     *         // index for the filter. Up to 8 filters can be saved.
     *         // value is from 0 to 7.
     *     public int length;
     *         // length of the selectData (unit: bit).
     *         // for example, if selectData="ABCD", then set length to 16 
     *         // (=4 bits &times; 4 characters).
     *         // value is from 0 to 255, where 0 disables current filter.
     *     public int memBank;
     *         // a memory bank in the tag where this filter will be applied to
     *         //  0 means RESERVED
     *         //  1 means EPC
     *         //  2 means TID
     *         //  3 means USER
     *     public int offset;
     *         // an offset from the beginning of the memory bank where this
     *         // filter will be applied to (unit: bit).
     *         // value is from 0 to 255
     *     public String selectData;
     *         // a filter value in a string of hexadecimal digits.
     *         // Note: common prefixes for hexadecimal notation, such as "0x",
     *         //  are not necessary.
     *     public int target;
     *         // a flag in a tag.
     *         // tags receiving select command invoked by {@link #SetSelMask} 
     *         // may modify its "inventoried" or "select" flag according to
     *         // action parameter.
     *         //   0, 1, 2 and 3 mean "inventoried" flag for session 0, 1, 2
     *         //    and 3, respectively
     *         //   4 means "select" flag
     *         // For more information, refer to section "6.3.2.12.1.1 Select" 
     *         // in EPC UHF RFID G2 Standard document, available at
     *         // https://www.gs1.org/standards/epc-rfid/uhf-air-interface-protocol
     *     public int action;
     *         // a rule to modify a flag (designated by target) in tags
     *         // value is from 0 to 7
     *         // For more information, refer to "Table 6-30: Tag response to Action parameter"
     *         // in EPC UHF RFID G2 Standard document, available at
     *         // https://www.gs1.org/standards/epc-rfid/uhf-air-interface-protocol
     * }
     * </pre> 
     *
     * @return whether query was successful or not. Note that return value does 
     * not contain actual query results. Instead, look for selConfig argument
     * to check what returned from query. <br />
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetSelMask
     *
     */
    @UnsupportedAppUsage
    public int GetSelMask(SelConfig selConfig) {
        try {
            return DeviceServer.getIRFIDService().cmdGetSelectMask(selConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Commands tags to mark themselves "selected" according to filter.
     * The latest filter setting will be stored at the RFID reader device
     * as well, which can be referred later by {@link #GetSelMask}.
     *
     * @param selConfig tag selection filter
     * <pre>
     * public class SelConfig {
     *     public int index;
     *         index for the select list that users set.
     *         available from 0 to 7.
     *     public int length;
     *         length of the data that users set(unit. bit).
     *         available from 0 to 7. 0 means disable.
     *     public int memBank;
     *         memory bank of the tag with data.
     *             0 means RESERVED bank
     *             1 means EPC bank
     *             2 means TID bank
     *             3 means USER bank
     *     public int offset;
     *         offset of the data in the memory bank(unit. bit).
     *         available from 0 to 255
     *     public String selectData;
     *         data that users set 
     *     public int target;
     *         default 4 to use select mode
     *     public int action;
     *         whether include or exclude tag
     *             0 means include single tag
     *             1 means include multi tag
     *             4 means exclude single tag
     * }
     * </pre> 
     *
     * @return whether command was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetSelMask
     *
     */
    @UnsupportedAppUsage
    public int SetSelMask(SelConfig selConfig) {
        try {
            return DeviceServer.getIRFIDService().cmdSetSelectMask(selConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Command RFID reader to read memory of tag.
     * Tag selection mask defined by {@link #SetSelMask} is used to define
     * which tag (or a group of tags) is affected by this command.
     * To check current selection mask, use {@link #GetSelMask}.
     * <p style='margin-bottom:0;'>
     * The return value of this method does NOT contain a read result.
     * The read result can be obtained via notifications, such as:
     * </p>
     * <ul>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CALLBACK
       callback},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CUSTOM_INTENT custom&nbsp;intent},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_KBDMSG
       keyboard&nbsp;event},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_COPYPASTE
       copy&nbsp;&amp;&nbsp;paste&nbsp;event}, or</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CTRLV
       ctrl&nbsp;+&nbsp;V&nbsp;event}.</li>
     * </ul>
     * The read result includes content in the memory, followed by a tag ID,
     * delimited by comma(",").
     * For example, if data "abcd" has been read from a tag which EPC ID is
     * "0x1234", then return format will be "abcd,e=1234".
     * <p />
     * To check or modify tag ID format for read result, use
     * {@link #GetDataFormat} or {@link #SetDataFormat}.
     * <p />
     * To check and modify type of notification for read result, use
     * {@link #GetResultType} or {@link #SetResultType}.
     *
     * @param acsTag the input parameters and returned results.
     * <pre>
     * // input parameters
     * acsTag.length // the number of data to read.
     *               // value is from 1 to 255 (unit: word).
     *
     * acsTag.membank // the memory bank of the tag to read.
     *                // 0  RESERVED bank
     *                // 1  EPC bank
     *                // 2  TID bank
     *                // 3  USER bank
     *
     * acsTag.offset // an offset from the beginning of the memory bank.
     *               // value is from 0 to 255 (unit: word).
     *
     * acsTag.acsPwd // the access password.
     *               // Both decimal or hexadecimal notation is allowed.
     *               // A string starting "0x" will be interpreted as
     *               // hexadecimal number.
     *
     * // return results
     * acsTag.tagId  // ID of an accessed tag.
     *
     * acsTag.errTag // UHF RFID EPC Global Gen2 Tag error code.
     *
     * acsTag.errOp  // UHF RF Transceiver error code.
     * </pre>
     *
     * @return whether the command success.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     * Please refer to {@link device.common.rfid.AccessTag#errTag acsTag.errTag}
     * or {@link device.common.rfid.AccessTag#errOp acsTag.errOp} to check
     * error codes.
     *
     * @see  device.sdk.RFIDManager#WriteTag
     * @see  #SetSelMask
     * @see  #GetSelMask
     * @see  #GetDataFormat
     * @see  #SetDataFormat
     * @see  #GetResultType
     * @see  #SetResultType
     *
     */
    @UnsupportedAppUsage
    public int ReadTag(AccessTag acsTag) {
        try {
            return DeviceServer.getIRFIDService().cmdReadTag(acsTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Command RFID reader to write memory of tag.
     *     
     * Tag selection mask defined by {@link #SetSelMask} is used to define
     * which tag (or a group of tags) is affected by this command.
     * To check current selection mask, use {@link #GetSelMask}.
     * <p style='margin-bottom:0;'>
     * The return value of this method does NOT contain a write result.
     * The write result can be obtained via notifications, such as:
     * </p>
     * <ul>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CALLBACK
       callback},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CUSTOM_INTENT custom&nbsp;intent},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_KBDMSG
       keyboard&nbsp;event},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_COPYPASTE
       copy&nbsp;&amp;&nbsp;paste&nbsp;event}, or</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CTRLV
       ctrl&nbsp;+&nbsp;V&nbsp;event}.</li>
     * </ul>
     * The write result from tag includes <code>"e="</code>, followed by a tag
     * ID from accessed tag.
     * <pre>     e=[tag ID]</pre>
     * To check or modify tag ID format for write result, use
     * {@link #GetDataFormat} or {@link #SetDataFormat}.
     * <p />
     * To check and modify type of notification for write result, use
     * {@link #GetResultType} or {@link #SetResultType}.
     *
     * @param acsTag the input parameters and returned results.
     * <pre>
     * // input parameters
     * acsTag.length // the number of data to write.
     *               // Value is from 1 to 255 (unit: word).
     *
     * acsTag.memBank // the memory bank of the tag to write.
     *                // 0  RESERVED bank
     *                // 1  EPC bank
     *                // 2  TID bank
     *                // 3  USER bank
     *
     * acsTag.offset // an offset from the beginning of the memory bank.
     *               // Value is from 0 to 255 (unit: word).
     *
     * acsTag.acsPwd // the access password.
     *               // Both decimal or hexadecimal notation is allowed.
     *               // A string starting "0x" will be interpreted as
     *               // hexadecimal number.
     *
     * acsTag.wTagData // the data to write. This value will be handled as
     *                 // hexadecimal and prefix "0x" is not necessary.
     *                 // The length of wTagData should be the quadruple of
     *                 // acsTag.length.
     *
     * // return results
     * acsTag.tagId  // ID of an accessed tag.
     *
     * acsTag.errTag // UHF RFID EPC Global Gen2 tag error code.
     *
     * acsTag.errOp  // UHF RF transceiver error code.
     * </pre>
     *
     * @return whether command success.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     * Please refer to {@link device.common.rfid.AccessTag#errTag acsTag.errTag}
     * or {@link device.common.rfid.AccessTag#errOp acsTag.errOp} to check
     * error codes.
     *
     * @see  device.sdk.RFIDManager#ReadTag
     * @see  #SetSelMask
     * @see  #GetSelMask
     * @see  #GetDataFormat
     * @see  #SetDataFormat
     * @see  #GetResultType
     * @see  #SetResultType
     *
     */
    @UnsupportedAppUsage
    public int WriteTag(AccessTag acsTag) {
        try {
            return DeviceServer.getIRFIDService().cmdWriteTag(acsTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Command RFID reader to kill (disable permanently) tag.
     *
     * Tag selection mask defined by {@link #SetSelMask} is used to define
     * which tag (or a group of tags) is affected by this command.
     * To check current selection mask, use {@link #GetSelMask}.
     * <p style='margin-bottom:0;'>
     * The return value of this method does NOT contain a kill result.
     * The kill result can be obtained via notifications, such as:
     * </p>
     * <ul>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CALLBACK
       callback},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CUSTOM_INTENT custom&nbsp;intent},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_KBDMSG
       keyboard&nbsp;event},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_COPYPASTE
       copy&nbsp;&amp;&nbsp;paste&nbsp;event}, or</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CTRLV
       ctrl&nbsp;+&nbsp;V&nbsp;event}.</li>
     * </ul>
     * The kill result from tag includes <code>"e="</code>, followed by a tag
     * ID from accessed tag.
     * <pre>     e=[tag ID]</pre>
     * To check or modify tag ID format for kill result, use
     * {@link #GetDataFormat} or {@link #SetDataFormat}.
     * <p />
     * To check and modify type of notification for kill result, use
     * {@link #GetResultType} or {@link #SetResultType}.
     *
     * @param acsTag the input parameters and returned results.
     * <pre>
     * // input parameters
     * acsTag.killPwd // the kill password. It must not 0.
     *
     * // return results
     * acsTag.tagId  // ID of an accessed tag.
     *
     * acsTag.errTag // UHF RFID EPC Global Gen2 Tag error code.
     *
     * acsTag.errOp  // UHF RF Transceiver error code.
     * </pre>
     *
     * @return whether command was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted
     * </pre>
     * Please refer to {@link device.common.rfid.AccessTag#errTag acsTag.errTag}
     * or {@link device.common.rfid.AccessTag#errOp acsTag.errOp} to check
     * error codes.
     *
     * @see  device.sdk.RFIDManager#KillTag
     * @see  #SetSelMask
     * @see  #GetSelMask
     * @see  #GetDataFormat
     * @see  #SetDataFormat
     * @see  #GetResultType
     * @see  #SetResultType
     *
     */
    @UnsupportedAppUsage
    public int KillTag(AccessTag acsTag) {
        try {
            return DeviceServer.getIRFIDService().cmdKillTag(acsTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Command RFID reader to lock or unlock memory in tag.
     * The parameters <b>lockmask</b> and <b>lockEnable</b> are interpreted as
     * bitmap.
     * Refer to the following table for mapping between bits and memory
     * locations.
     * <table cellspacing='0' border='1'>
     * <thead>
     * <tr> <th>bit location</th> <th>memory location</th> </tr>
     * </thead>
     * <tbody>
     * <tr> <td>9</td> <td>User</td> </tr>
     * <tr> <td>8</td> <td>N/A</td> </tr>
     * <tr> <td>7</td> <td>TID</td> </tr>
     * <tr> <td>6</td> <td>N/A</td> </tr>
     * <tr> <td>5</td> <td>EPC</td> </tr>
     * <tr> <td>4</td> <td>N/A</td> </tr>
     * <tr> <td>3</td> <td>Access password</td> </tr>
     * <tr> <td>2</td> <td>N/A</td> </tr>
     * <tr> <td>1</td> <td>Kill password</td> </tr>
     * <tr> <td>0</td> <td>N/A</td> </tr>
     * </tbody>
     * </table>
     * For example, if you want to lock User memory, and unlock EPC memory,
     * set <b><code>lockMask</code></b> as <code>0x220</code>,
     * and set <b><code>lockEnable</code></b> as <code>0x200</code>.
     * <p />
     * Tag selection mask defined by {@link #SetSelMask} is used to define
     * which tag (or a group of tags) is affected by this command.
     * To check current selection mask, use {@link #GetSelMask}.
     * <p style='margin-bottom:0;'>
     * The return value of this method does NOT contain a kill result.
     * The kill result can be obtained via notifications, such as:
     * </p>
     * <ul>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CALLBACK
       callback},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CUSTOM_INTENT custom&nbsp;intent},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_KBDMSG
       keyboard&nbsp;event},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_COPYPASTE
       copy&nbsp;&amp;&nbsp;paste&nbsp;event}, or</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CTRLV
       ctrl&nbsp;+&nbsp;V&nbsp;event}.</li>
     * </ul>
     * The kill result from tag includes <code>"e="</code>, followed by a tag
     * ID from accessed tag.
     * <pre>     e=[tag ID]</pre>
     * To check or modify tag ID format for kill result, use
     * {@link #GetDataFormat} or {@link #SetDataFormat}.
     * <p />
     * To check and modify type of notification for kill result, use
     * {@link #GetResultType} or {@link #SetResultType}.
     *
     * @param lockMask a bit mask to mark which memory to apply <b>lockEnable</b>.
     * A memory location marked as zero bit in the mask will be ignored.
     * A memory location marked as non-zero bit in the mask will be processed
     * as <b>lockEnable</b>.
     * @param lockEnable a bit mask to lock or unlock.
     * A zero bit at the valid bit location will unlock corresponding memory location.
     * A non-zero bit at the valid bit location will lock corresponding memory location.

     * @param acsTag the input parameters and returned results.
     * <pre>
     * // input parameters
     * acsTag.acsPwd // the access password.
     *               // Both decimal or hexadecimal notation is allowed.
     *               // A string starting "0x" will be interpreted as
     *               // hexadecimal number.
     * // return results
     * acsTag.tagId  // ID of an accessed tag.
     *
     * acsTag.errTag // UHF RFID EPC Global Gen2 Tag error code.
     *
     * acsTag.errOp  // UHF RF Transceiver error code.
     * </pre>
     *
     * @return whether command was successful or not. 
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     * Please refer to {@link device.common.rfid.AccessTag#errTag acsTag.errTag}
     * or {@link device.common.rfid.AccessTag#errOp acsTag.errOp} to check
     * error codes.
     *
     * @see  #SetSelMask
     * @see  #GetSelMask
     * @see  #GetDataFormat
     * @see  #SetDataFormat
     * @see  #GetResultType
     * @see  #SetResultType
     *
     */
    @UnsupportedAppUsage
    public int LockTag(int lockMask, int lockEnable, AccessTag acsTag) {
        try {
            return DeviceServer.getIRFIDService().cmdLockTag(lockMask, lockEnable, acsTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Command RFID reader to lock or unlock memory in tag permanently.
     * The parameters <b>lockmask</b> and <b>lockEnable</b> are interpreted as bitmap.
     * Refer to the following table for mapping between bits and memory
     * locations.
     * <table cellspacing='0' border='1'>
     * <thead>
     * <tr> <th>bit location</th> <th>memory location</th> </tr>
     * </thead>
     * <tbody>
     * <tr> <td>9</td> <td>User</td> </tr>
     * <tr> <td>8</td> <td>N/A</td> </tr>
     * <tr> <td>7</td> <td>TID</td> </tr>
     * <tr> <td>6</td> <td>N/A</td> </tr>
     * <tr> <td>5</td> <td>EPC</td> </tr>
     * <tr> <td>4</td> <td>N/A</td> </tr>
     * <tr> <td>3</td> <td>Access password</td> </tr>
     * <tr> <td>2</td> <td>N/A</td> </tr>
     * <tr> <td>1</td> <td>Kill password</td> </tr>
     * <tr> <td>0</td> <td>N/A</td> </tr>
     * </tbody>
     * </table>
     * For example, if you want to lock User memory, and unlock EPC memory,
     * set <b><code>lockMask</code></b> as <code>0x220</code>,
     * and set <b><code>lockEnable</code></b> as <code>0x200</code>.
     * <p />
     * Tag selection mask defined by {@link #SetSelMask} is used to define
     * which tag (or a group of tags) is affected by this command.
     * To check current selection mask, use {@link #GetSelMask}.
     * <p style='margin-bottom:0;'>
     * The return value of this method does NOT contain a kill result.
     * The kill result can be obtained via notifications, such as:
     * </p>
     * <ul>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CALLBACK
       callback},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CUSTOM_INTENT custom&nbsp;intent},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_KBDMSG
       keyboard&nbsp;event},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_COPYPASTE
       copy&nbsp;&amp;&nbsp;paste&nbsp;event}, or</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CTRLV
       ctrl&nbsp;+&nbsp;V&nbsp;event}.</li>
     * </ul>
     * The kill result from tag includes <code>"e="</code>, followed by a tag
     * ID from accessed tag.
     * <pre>     e=[tag ID]</pre>
     * To check or modify tag ID format for kill result, use
     * {@link #GetDataFormat} or {@link #SetDataFormat}.
     * <p />
     * To check and modify type of notification for kill result, use
     * {@link #GetResultType} or {@link #SetResultType}.
     *
     * @param lockMask a bit mask to mark which memory to apply <b>lockEnable</b>.
     * A memory location marked as zero bit in the mask will be ignored.
     * A memory location marked as non-zero bit in the mask will be processed
     * as <b>lockEnable</b>.
     * @param lockEnable a bit mask to lock or unlock.
     * A zero bit at the valid bit location will unlock corresponding memory location.
     * A non-zero bit at the valid bit location will lock corresponding memory location.
     * @param acsTag the input parameters and returned results.
     * <pre>
     * // input parameters
     * acsTag.acsPwd // the access password.
     *               // Both decimal or hexadecimal notation is allowed.
     *               // A string starting "0x" will be interpreted as
     *               // hexadecimal number.
     * // return results
     * acsTag.tagId  // ID of an accessed tag.
     *
     * acsTag.errTag // UHF RFID EPC Global Gen2 Tag error code.
     *
     * acsTag.errOp  // UHF RF Transceiver error code.
     * </pre>
     *
     * @return whether command was successful or not. 
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     * Please refer to {@link device.common.rfid.AccessTag#errTag acsTag.errTag}
     * or {@link device.common.rfid.AccessTag#errOp acsTag.errOp} to check
     * error codes.
     *
     * @see  #SetSelMask
     * @see  #GetSelMask
     * @see  #GetDataFormat
     * @see  #SetDataFormat
     * @see  #GetResultType
     * @see  #SetResultType
     *
     */
    @UnsupportedAppUsage
    public int PermaLockTag(int lockMask, int lockEnable, AccessTag acsTag) {
        try {
            return DeviceServer.getIRFIDService().cmdPermLockTag(lockMask, lockEnable, acsTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }   

    /**
     * Queries parameters used by {@link #StartInventory}.
     *
     * @param paramOfInvent the container for query result.
     * <pre>
     * public class ParamOfInvent {
     *     public int session;
     *         // Determines how long the flags in tags expires.
     *         // The flags includes inventoried flags, which is referred in
     *         // <b>inventoryFlag</b> parameter.
     *         // Refer to "Table 6-20. Tag flags and persistence values"
     *         // from UHF RFID standard[1] for more information.
     *             public static final int SESSION_0 = 0; // None
     *             public static final int SESSION_1 = 1; // from 500 msecs to 5 secs
     *             public static final int SESSION_2 = 2; // more than 2 secs
     *             public static final int SESSION_3 = 3; // more than 2 secs
     *     public int q;
     *         // Determines a tag response probability (=1/2<sup>Q</sup>).
     *         // Adjust this value in consideration of tag population.
     *         // Larger Q value is recommended for a large number of
     *         // (or dense) tags.
     *         // Smaller Q value is recommended for a few number of
     *         // (or sparse) tags.
     *         // Refer to "Inventorying Tag populations" section from UHF
     *         // RFID standard[1] for more information.
     *         available from 0 to 15.
     *     public int inventoryFlag;
     *         // Filter tags by inventoried flag at selected <b>session</b>.
     *         // A tag has four independent inventoried flags for each session.
     *         // Each flag can represent one of binary states "A" or "B".
     *         // Refer to "Sessions and inventoried flags" section from UHF
     *         // RFID standard[1] for more information.
     *             public static final int TARGET_A = 0;
     *             // only tags with inventoried flag "A" are identified.
     *             public static final int TARGET_B = 1;
     *             // only tags with inventoried flag "B" are identified.
     *             public static final int TARGET_AB = 2;
     *             // only tags with inventoried flag "A" or "B "are identified.
     * }
     * </pre>
     * [1] "EPC Radio-Frequency Identity Protocols Generation-2 UHF RFID,
     * Specification for RFID Air Interface Protocol for Communications at
     * 860 MHz - 960 MHz," Version 2.1, EPC Global, Jul. 2018.
     *
     * @return query was success or not. Note that return value does not
     * contain actual query results. Instead, look for paramOfInvent argument
     * to check what returned from query.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetInventoryParam
     *
     */
    @UnsupportedAppUsage
    public int GetInventoryParam(ParamOfInvent paramOfInvent) {
		try {
		    return DeviceServer.getIRFIDService().cmdGetInventParam(paramOfInvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies parameters used by {@link #StartInventory}.
     *
     * @param paramOfInvent parameters used during inventory.
     * <pre>
     * public class ParamOfInvent {
     *     public int session;
     *         // Determines how long the flags in tags expires.
     *         // The flags includes inventoried flags, which is referred in
     *         // <b>inventoryFlag</b> parameter.
     *         // Refer to "Table 6-20. Tag flags and persistence values"
     *         // from UHF RFID standard[1] for more information.
     *             public static final int SESSION_0 = 0; // None
     *             public static final int SESSION_1 = 1; // from 500 msecs to 5 secs
     *             public static final int SESSION_2 = 2; // more than 2 secs
     *             public static final int SESSION_3 = 3; // more than 2 secs
     *     public int q;
     *         // Determines a tag response probability (=1/2<sup>Q</sup>).
     *         // Adjust this value in consideration of tag population.
     *         // Larger Q value is recommended for a large number of
     *         // (or dense) tags.
     *         // Smaller Q value is recommended for a few number of
     *         // (or sparse) tags.
     *         // Refer to "Inventorying Tag populations" section from UHF
     *         // RFID standard[1] for more information.
     *         available from 0 to 15.
     *     public int inventoryFlag;
     *         // Filter tags by inventoried flag at selected <b>session</b>.
     *         // A tag has four independent inventoried flags for each session.
     *         // Each flag can represent one of binary states "A" or "B".
     *         // Refer to "Sessions and inventoried flags" section from UHF
     *         // RFID standard[1] for more information.
     *             public static final int TARGET_A = 0;
     *             // only tags with inventoried flag "A" are identified.
     *             public static final int TARGET_B = 1;
     *             // only tags with inventoried flag "B" are identified.
     *             public static final int TARGET_AB = 2;
     *             // only tags with inventoried flag "A" or "B "are identified.
     * }
     * </pre>
     * [1] "EPC Radio-Frequency Identity Protocols Generation-2 UHF RFID,
     * Specification for RFID Air Interface Protocol for Communications at
     * 860 MHz - 960 MHz," Version 2.1, EPC Global, Jul. 2018.
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetInventoryParam
     *
     */
    @UnsupportedAppUsage
    public int SetInventoryParam(ParamOfInvent paramOfInvent) {
		try {
		    return DeviceServer.getIRFIDService().cmdSetInventParam(paramOfInvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }   

    /**
     * Queries current Tx power modifier value of RFID reader.
     * TX power is determined by ({@link #GetMaxPower MaxPower} - modifier).
     * Increasing TX power also increases power consumption from battery.
     *
     * @return Tx power modifier value in dB scale.
     * The higher the modifier value is, the weaker the Tx power of RFID reader
     * will be. Value range is from 0 to {@link #GetMaxPower MaxPower}.<br />
     * COMM_ERR may be returned if communication has been interrupted by error
     * while querying.
     *
     * @see  device.sdk.RFIDManager#SetTxPower
     * @see  device.sdk.RFIDManager#GetMaxPower
     *
     */
    @UnsupportedAppUsage
    public int GetTxPower() {
		try {
		    return DeviceServer.getIRFIDService().cmdGetTxPower();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies Tx power modifier value of RFID reader.
     * TX power is determined by ({@link #GetMaxPower MaxPower} - pwr).<br />
     * Increasing TX power also increases power consumption from battery.
     *
     * @param pwr Tx power modifier value in dB scale.
     * The higher the pwr is, the weaker the Tx power of RFID reader will be.
     * Value range is from 0 to {@link #GetMaxPower MaxPower}.<br />
     *
     * @return whether modification was successful or not. 
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetTxPower
     * @see  device.sdk.RFIDManager#GetMaxPower
     *
     */
    @UnsupportedAppUsage
    public int SetTxPower(int pwr) {
		try {
		    return DeviceServer.getIRFIDService().cmdSetTxPower(pwr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries the Max.&nbsp;Tx power supported by RFID reader.
     *
     * @return max. power value in dBm.
     */
    @UnsupportedAppUsage
    public int GetMaxPower() {
		try {
		    return DeviceServer.getIRFIDService().cmdGetMaxPower();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries Tx ON and OFF durations.
     * To modify TX ON and OFF durations, use {@link #SetTxCycle}.
     *
     * @param txCycle the container for query result.
     * <pre>
     *     public class TxCycle implements Parcelable {
     *         public int onTime;
     *             // Tx ON duration
     *             // available from 40 to 400 (unit: msec)
     *         public int offTime;
     *             // Tx OFF duration
     *             // available from 0 to 255 (unit: msec)
     * }
     * </pre>
     *
     * @return whether query was successful or not. Note that return value does 
     * not contain actual query results. Instead, look for txCycle argument
     * to check what returned from query. <br />
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetTxCycle
     *
     */
    @UnsupportedAppUsage
    public int GetTxCycle(TxCycle txCycle) {
		try {
		    return DeviceServer.getIRFIDService().cmdGetTxCycle(txCycle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies Tx ON and OFF durations.
     * For the maximum performance, set OFF duration as 0 at the cost of reduced
     * battery lifetime.
     * To query current TX ON and OFF durations, use {@link #GetTxCycle}.
     *
     * @param txCycle Tx ON and OFF duration
     * <pre>
     *     public class TxCycle implements Parcelable {
     *         public int onTime;
     *             // Tx ON duration
     *             // available from 40 to 400 (unit: msec)
     *         public int offTime;
     *             // Tx OFF duration
     *             // available from 0 to 255 (unit: msec)
     * }
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error 
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetTxCycle
     *
     */
    @UnsupportedAppUsage
    public int SetTxCycle(TxCycle txCycle) {
		try {
		    return DeviceServer.getIRFIDService().cmdSetTxCycle(txCycle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries a list of enabled channels.
     * Channel indexes are enumerated as positive integers starting from 1.
     * <p />
     * To query availability of a channel, use {@link #GetChannelState}.
     * To enable or disable a channel, use {@link #SetChannelState}.
     *
     * @return a comma-separated string notation for list of enabled channel 
     * indexes.
     * For example, assume channel indexes 4, 7, 10, and 13 are enabled.
     * Then return value will be "4,7,10,13".
     * Note that in case of communication has been interrupted by error,
     * null is returned.
     *
     * @see  device.sdk.RFIDManager#GetChannelState
     * @see  device.sdk.RFIDManager#SetChannelState
     *
     */
    @UnsupportedAppUsage
    public String GetAllChannel() {
        try {
            return DeviceServer.getIRFIDService().cmdGetAllChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Queries availability of a channel.
     * To get a list of enabled channels, use {@link #GetAllChannel}.
     * To enable or disable a channel, use {@link #SetChannelState}.
     *
     * @param channelState the container for query result.
     * <pre>
     * public class ChannelState implements Parcelable {
     *         public int channelIndex;
     *             // channel index to check
     *         public int enable;
     *             // 1 means enabled and 0 means disabled.
     * }
     * </pre>
     *
     * @return whether query was successful or not. Note that return value does 
     * not contain actual query results. Instead, look for channelState argument
     * to check what returned from query. <br />
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * </pre>
     *
     * @see  #GetAllChannel
     * @see  #SetChannelState
     *
     */
    @UnsupportedAppUsage
    public int GetChannelState(ChannelState channelState) {
        try {
            return DeviceServer.getIRFIDService().cmdGetChannelState(channelState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Enables (or disables) a channel.
     * To get a list of enabled channels, use {@link #GetAllChannel}.
     * To check whether a channel is enabled, use {@link #GetChannelState}.
     *
     * @param channelState channel status.
     * <pre>
     * public class ChannelState implements Parcelable {
     *         public int channelIndex;
     *             // channel index to check
     *         public int enable;
     *             // 1 means enabled and 0 means disabled.
     * }
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetAllChannel
     * @see  device.sdk.RFIDManager#GetChannelState
     *
     */
    @UnsupportedAppUsage
    public int SetChannelState(ChannelState channelState) {
        try {
            return DeviceServer.getIRFIDService().cmdSetChannelState(channelState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Queries whether Listen-Before-Talk (LBT) mode is enabled.
     * This mode is for Japan-oriented products only.
     * <p />
     * To enable (or disable) LBT mode, use {@link #SetLBTState}.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @return 0 if LBT mode is diabled,
     * 1 if enabled,
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR}
     * if error occurred while querying.
     *
     * @see  device.sdk.RFIDManager#SetLBTState
     *
     */
    @Deprecated 
    @UnsupportedAppUsage
    public int GetLBTState() {
        try {
            return DeviceServer.getIRFIDService().cmdGetLBTState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Enables (or disables) Listen-Before-Talk (LBT) mode.
     * This mode is for Japan-oriented products only.
     * <p />
     * To check whether LBT mode is enabled, use {@link #GetLBTState}.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param lbt flag for LBT mode.
     * value is 0 (disable) or 1 (enable)
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetLBTState
     *
     */
    @Deprecated 
    @UnsupportedAppUsage
    public int SetLBTState(int lbt) {
        try {
            return DeviceServer.getIRFIDService().cmdSetLBTState(lbt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries the RF transceiver link profile of RFID reader.
     * <p />
     * To modify link profile, use {@link #SetLinkProfile}.
     *
     * @return on success, one of link profile indexes is returned.
     * <pre>
     * public static final int LINK_PROFILE_0 = 0;
     * public static final int LINK_PROFILE_1 = 1;
     * public static final int LINK_PROFILE_2 = 2;
     * public static final int LINK_PROFILE_3 = 3;
     * </pre>
     * <table border='2' cellspacing='0'>
     * <thead> <tr>
     *   <th>Profile index</th>
     *   <th>Modulation</th>
     *   <th>DeModulation</th>
     *   <th>Frequency(KHz)</th>
     *   <th>DataRate(kbps)</th>
     * </tr> </thead>
     * <tbody>
     * <tr>
     *   <td>{@value device.common.rfid.RFIDConst.RFIDConfig#LINK_PROFILE_0}</td>
     *   <td>DSB-ASK</td> <td>FM0</td> <td>40</td> <td>40</td>
     * </tr>
     * <tr>
     *   <td>{@value device.common.rfid.RFIDConst.RFIDConfig#LINK_PROFILE_1}</td>
     * <td>PR-ASK</td><td>Miller Subcarrier 4</td><td>250</td><td>32.5</td>
     * </tr>
     * <tr>
     *   <td>{@value device.common.rfid.RFIDConst.RFIDConfig#LINK_PROFILE_2}</td>
     *   <td>PR_ASK</td><td>Miller Subcarrier 4</td><td>300</td><td>75</td>
     * </tr>
     * <tr>
     *   <td>{@value device.common.rfid.RFIDConst.RFIDConfig#LINK_PROFILE_3}</td>
     *   <td>DSB-ASK</td><td>FM0</td><td>400</td><td>400</td>
     * </tr>
     * </tbody>
     * </table>
     * on communication error, {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR}
     * is returned.
     *
     * @see  device.sdk.RFIDManager#SetLinkProfile
     *
     */
    @UnsupportedAppUsage
    public int GetLinkProfile() {
        try {
            return DeviceServer.getIRFIDService().cmdGetLinkProfile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies the RF transceiver link profile of RFID reader.
     * <p />
     * To check current link profile, use {@link #GetLinkProfile}.
     *
     * @param profile link profile index.
     * <pre>
     * public static final int LINK_PROFILE_0 = 0;
     * public static final int LINK_PROFILE_1 = 1;
     * public static final int LINK_PROFILE_2 = 2;
     * public static final int LINK_PROFILE_3 = 3;
     * </pre>
     * <table border='2' cellspacing='0'>
     * <thead> <tr>
     *   <th>Profile index</th>
     *   <th>Modulation</th>
     *   <th>DeModulation</th>
     *   <th>Frequency(KHz)</th>
     *   <th>DataRate(kbps)</th>
     * </tr> </thead>
     * <tbody>
     * <tr>
     *   <td>{@value device.common.rfid.RFIDConst.RFIDConfig#LINK_PROFILE_0}</td>
     *   <td>DSB-ASK</td> <td>FM0</td> <td>40</td> <td>40</td>
     * </tr>
     * <tr>
     *   <td>{@value device.common.rfid.RFIDConst.RFIDConfig#LINK_PROFILE_1}</td>
     * <td>PR-ASK</td><td>Miller Subcarrier 4</td><td>250</td><td>32.5</td>
     * </tr>
     * <tr>
     *   <td>{@value device.common.rfid.RFIDConst.RFIDConfig#LINK_PROFILE_2}</td>
     *   <td>PR_ASK</td><td>Miller Subcarrier 4</td><td>300</td><td>75</td>
     * </tr>
     * <tr>
     *   <td>{@value device.common.rfid.RFIDConst.RFIDConfig#LINK_PROFILE_3}</td>
     *   <td>DSB-ASK</td><td>FM0</td><td>400</td><td>400</td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return whether modification was successful or not. Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetLinkProfile
     *
     */
    @UnsupportedAppUsage
    public int SetLinkProfile(int profile) {
        try {
            return DeviceServer.getIRFIDService().cmdSetLinkProfile(profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries the OEM info of RFID reader.
     *
     * @return a string notation of OEM info.<br />
     * For PM500 and RF851 reader, it is three-characters long.<br />
     * <pre>
     * "FCC"   // US-oriented device
     * "KOR"   // Korea-oriented device
     * "BRA"   // Brazil-oriented device
     * "RUS"   // Russia-oriented device
     * "EU1"   // Europe-oriented device
     * "ZAF","PHL","URY","PER","VNM","AUS","MYS","SGP","THA","TWN","IDN","NZL","HKG","ISR","IND","MAR"
     * </pre>
     * For RF850 reader, possible return values are:
     * <pre>
     * "KMIC"   // Korea-oriented device
     * "FCC"    // US-oriented device
     * "ETSI"   // Europe-oriented device
     * "CHINA"  // China-oriented device
     * "ARIBns" // Japan-oriented device with 0.25W
     * "ARIBn"  // Japan-oriented device with 1W
     * </pre>
     * null may be returned if an error occurred during communication to RFID
     * reader.
     *
     */
    @UnsupportedAppUsage
    public String GetOemInfo() {
        try {
            return DeviceServer.getIRFIDService().cmdGetOemInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @hide
     * Queries the number of data stored in RFID reader.
     * <p />
     * This method always returns
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED},
     * which means this function is not supported.
     *
     * @return always
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED}
     *
     */
    @Deprecated 
    @UnsupportedAppUsage
    public int GetLocalDataCnt() {
        // not supported function
        return RFIDConst.CommandErr.NOT_SUPPORTED;
    }

    /**
     * Queries the vibration setting value of RFID reader. 
     * <p />
     * To modify vibration setting, use {@link #SetVibState}.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @return vibration state (1 for enabled, 0 for disabled), or 
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR} if an
     * error occurred during communication to RFID reader.
     *
     * @see  device.sdk.RFIDManager#SetVibState
     *
     */
    @UnsupportedAppUsage
    public int GetVibState() {
        try {
            return DeviceServer.getIRFIDService().cmdGetVibration();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies the vibration setting value of RFID reader. 
     * <p />
     * To check current vibration setting, use {@link #GetVibState}.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @param on vibration setting value.
     * <pre>
     * 0 means vibrator is disable
     * 1 means vibrator is enable
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     * @see  device.sdk.RFIDManager#GetVibState
     *
     */
    @UnsupportedAppUsage
    public int SetVibState(int on) {
        try {
            return DeviceServer.getIRFIDService().cmdSetVibration(on);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Modifies the timeout value for stopping RFID reader when it remains
     * idle for a certain duration while operating in auto mode.
     * <p />
     * This method always returns
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED},
     * which means this function is not supported.
     *
     * @return always
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED}
     */
    @Deprecated
    @UnsupportedAppUsage
    public int SetAutomodeTimeout() {
        // not supported function
        return RFIDConst.CommandErr.NOT_SUPPORTED;
    }

    /**
     * Modifies the trigger mode.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @param mode the trigger mode
     * <pre>
     * 0 means RFID mode
     * 1 means Barcode Scanner mode
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     */
    @UnsupportedAppUsage
    public int SetTriggerMode(int mode) {
        try {
            return DeviceServer.getIRFIDService().cmdSetTriggerMode(mode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries the trigger mode.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @return the currenct trigger mode.
     * Possible return values are:
     * <pre>
     * 0 // means RFID mode
     * 1 // means Barcode Scanner mode
     * </pre>
     *
     */
    @UnsupportedAppUsage
    public int GetTriggerMode() {
        try {
            return DeviceServer.getIRFIDService().cmdGetTriggerMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries the performance state of battery in RFID reader.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @return the performance state of battery.
     * possible values are:
     * <pre>
     * 0  // battery replacement is necessary
     * 1  // battery is in good condition
     * </pre>
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR} is returned if
     * communication error is occurred.
     */
    @UnsupportedAppUsage
    public int GetBatteryState() {
        try {
            return DeviceServer.getIRFIDService().cmdGetBatteryState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries whether the battery of RFID reader is charging.
     * <p />
     * Note: If battery is fully (100%) charged, the return value may be
     * incorrect.
     *
     * <p />NOTE: This method is incompatible with PM500.
     *
     * @return the battery charging state.
     * <pre>
     * 0 // battery is NOT charging.
     * 1 // battery is charging.
     * </pre>
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR} is returned if
     * communication error is occurred.
     *
     */
    @UnsupportedAppUsage
    public int GetChargingState() {
        try {
            return DeviceServer.getIRFIDService().cmdGetChargingState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Commands RFID reader to search a tag constrained by tag ID.
     * <!-- NOT VERIFIED YET
     * Initially, RFID reader finds the tag with the 
     * {@link #GetMaxPower Maximum TX power} allowed.
     * When the RFID reader successfully finds a tag, reader finds the tag
     * again with TX power reduced by <b>step</b>.
     * This process is repeated until TX power goes below <b>threshold</b>.
     * -->
     * <p style='margin-bottom:0;'>
     * The return value of this method does NOT contain search result.
     * The result can be obtained via notifications, such as:
     * </p>
     * <ul>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CALLBACK
       callback},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CUSTOM_INTENT custom&nbsp;intent},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_KBDMSG
       keyboard&nbsp;event},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_COPYPASTE
       copy&nbsp;&amp;&nbsp;paste&nbsp;event}, or</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CTRLV
       ctrl&nbsp;+&nbsp;V&nbsp;event}.</li>
     * </ul>
     * The search result includes a tag ID, followed by TX power of the reader,
     * delimited by comma(",").
     * The tag ID is a joined string of PC and EPC.
     * <pre>  [tag ID],p=[power]</pre>
     * <p />
     * To check and modify type of notification for the search result, use
     * {@link #GetResultType} or {@link #SetResultType}.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param length the length of <b>tagId</b>.
     * @param tagId the ID (PC + EPC) of the targeted tag to find for.
     * @param threshold the lower bound of TX power (unit. dBm).
     * @param step the amount of TX power to be reduced 
     * <!-- NOT VERIFIED YET
     * whenever reader finds targeted targeted tag
     * -->
     * (unit. dBm).
     *
     * @return whether command was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     */
    @UnsupportedAppUsage
    public int SingleSearch(int length, String tagId, int threshold, int step) {
        try {
            return DeviceServer.getIRFIDService().cmdSetSingleTagSearch(length, tagId, threshold, step);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Commands RFID reader to search tags constrained by list of tag IDs.
     * To check or modify the list, use {@link #GetSearchList}
     * or {@link #SetSearchList}.
     *
     * <p style='margin-bottom:0;'>
     * The return value of this method does NOT contain a search result.
     * The search list can be obtained via notifications, such as:
     * </p>
     * <ul>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CALLBACK
       callback},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CUSTOM_INTENT custom&nbsp;intent},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_KBDMSG
       keyboard&nbsp;event},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_COPYPASTE
       copy&nbsp;&amp;&nbsp;paste&nbsp;event}, or</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CTRLV
       ctrl&nbsp;+&nbsp;V&nbsp;event}.</li>
     * </ul>
     * The search result includes an index of the list to which the found tag is
     * applied, followed by a tag ID, delimited by colon(":").
     * The tag ID is a joined string of PC and EPC.
     * <pre>  [index]:[tag ID]</pre>
     * <p />
     * To check and modify type of notification for the list of tag found, use
     * {@link #GetResultType} or {@link #SetResultType}.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @return whether command was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetSearchList
     * @see  device.sdk.RFIDManager#SetSearchList
     * @see  #GetSearchList_ext
     * @see  #ClearSearchList
     * @see  #ClearAllSearchList
     * @see  #GetResultType
     * @see  #SetResultType
     *
     */
    @UnsupportedAppUsage
    public int MultiSearch() {
        try {
            return DeviceServer.getIRFIDService().cmdSetMultiTagSearch();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Commands RFID reader to search tags constrained by wildcards
     * ('*' or '?') patterns.
     *
     * <p style='margin-bottom:0;'>
     * The return value of this method does NOT contain a search result.
     * The search result can be obtained via notifications, such as:
     * </p>
     * <ul>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CALLBACK
       callback},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CUSTOM_INTENT custom&nbsp;intent},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_KBDMSG
       keyboard&nbsp;event},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_COPYPASTE
       copy&nbsp;&amp;&nbsp;paste&nbsp;event}, or</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CTRLV
       ctrl&nbsp;+&nbsp;V&nbsp;event}.</li>
     * </ul>
     * The search result is a string notation of tag IDs, one tag per line.
     * The tag ID is a joined string of PC and EPC.
     * <p />
     * To check and modify type of notification for the list of tag found, use
     * {@link #GetResultType} or {@link #SetResultType}.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param length the length of pattern <b>tagId</b>
     * (unit: characters).
     * @param tagId the tag ID (PC + EPC) pattern to search for.
     * The pattern may includes wildcards ('*' and '?').
     * Filtering rule is as below.
     * <pre>
     * * matches any String
     * ? matches any single character
     * ex) 12345678*
     *     123?5678?
     * </pre>
     *
     * @return whether command was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#WildcardSearch
     * @see #GetResultType
     * @see #SetResultType
     *
     */
    @UnsupportedAppUsage
    public int WildcardSearch(int length, String tagId) {
        try {
            return DeviceServer.getIRFIDService().cmdWildcardTagSearch(length, tagId);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries an entire list of tags used by {@link #MultiSearch}.
     * <p style='margin-bottom:0;'>
     * The return value of this method does NOT contain the list.
     * The list can be obtained via notifications, such as:
     * </p>
     * <ul>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CALLBACK
       callback},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CUSTOM_INTENT custom&nbsp;intent},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_KBDMSG
       keyboard&nbsp;event},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_COPYPASTE
       copy&nbsp;&amp;&nbsp;paste&nbsp;event}, or</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CTRLV
       ctrl&nbsp;+&nbsp;V&nbsp;event}.</li>
     * </ul>
     * To check and modify type of notifications, use
     * {@link #GetResultType} or {@link #SetResultType}.
     * <p />
     * A notification includes an item index followed by a tag ID,
     * delimited by colon(":").
     * The tag ID is a joined string of PC and EPC.
     * <pre>  [index]:[tag ID]</pre>
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @return whether query was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     *
     * @see  device.sdk.RFIDManager#GetSearchList_ext
     * @see  device.sdk.RFIDManager#SetSearchList
     * @see  #GetSearchList_ext
     * @see  #SetSearchList
     * @see  #ClearSearchList
     * @see  #ClearAllSearchList
     * @see  #MultiSearch
     *
     */
    @UnsupportedAppUsage
    public int GetSearchList() {
        try {
            return DeviceServer.getIRFIDService().cmdGetMultiTagList();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries an item in the tag list used by {@link #MultiSearch}.
     * <p style='margin-bottom:0;'>
     * The return value of this method does NOT contain the list item.
     * The list item can be obtained via notifications, such as:
     * </p>
     * <ul>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CALLBACK
       callback},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CUSTOM_INTENT custom&nbsp;intent},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_KBDMSG
       keyboard&nbsp;event},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_COPYPASTE
       copy&nbsp;&amp;&nbsp;paste&nbsp;event}, or</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CTRLV
       ctrl&nbsp;+&nbsp;V&nbsp;event}.</li>
     * </ul>
     * To check and modify type of notifications, use
     * {@link #GetResultType} or {@link #SetResultType}.
     * <p />
     * A notification includes an item index followed by a tag ID,
     * delimited by colon(":").
     * The tag ID is a joined string of PC and EPC.
     * <pre>  [index]:[tag ID]</pre>
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param index the item index to query for.
     * <pre>
     *     available from 0 to 49
     * </pre>
     *
     * @return whether query was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetSearchList
     * @see  device.sdk.RFIDManager#SetSearchList
     * @see  #GetSearchList
     * @see  #SetSearchList
     * @see  #ClearSearchList
     * @see  #ClearAllSearchList
     * @see  #MultiSearch
     *
     */
    @UnsupportedAppUsage
    public int GetSearchList_ext(int index) {
        try {
            return DeviceServer.getIRFIDService().cmdGetMultiTagList_ext(index);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies a list of tags-of-interest used by {@link #MultiSearch}.
     * {@link #MultiSearch} 
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param index the item index to modify.
     * <pre>
     *     available from 0 to 49
     * </pre>
     * @param length the length of tagId.
     * <pre>
     *     available from 4 to 36 (unit: characters) 
     * </pre>
     * @param tagId the tag ID (PC and EPC) to look for.
     *
     * @return whether modification was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetSearchList
     * @see  device.sdk.RFIDManager#GetSearchList_ext
     * @see  #ClearSearchList
     * @see  #ClearAllSearchList
     * @see  #MultiSearch
     *
     */
    @UnsupportedAppUsage
    public int SetSearchList(int index, int length, String tagId) {
        try {
            return DeviceServer.getIRFIDService().cmdSetMultiTagList(index, length, tagId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Clears a series of list items used by {@link #MultiSearch}.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param index the starting index of list item.
     * <pre>
     *     available from 0 to 49 (default = 0)
     * </pre>
     * @param cnt the number of items to clear from the starting index.
     * <pre>
     *     available from 1 to 50 (default = 50)
     * </pre>
     * For example, <code>ClearSearchList(2, 3)</code> clears three items in 
     * a row from the second item. In other words, it clears the 2nd, 3rd,
     * and 4th item.
     *
     * @return whether the command success.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  #GetSearchList_ext
     * @see  #GetSearchList
     * @see  #SetSearchList
     * @see  #ClearAllSearchList
     * @see  #MultiSearch
     */
    @UnsupportedAppUsage
    public int ClearSearchList(int index, int cnt) {
        try {
            return DeviceServer.getIRFIDService().cmdClearMultiTagList(index, cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Clears all list items used by {@link #MultiSearch}.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @return whether the command success.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  #GetSearchList_ext
     * @see  #GetSearchList
     * @see  #SetSearchList
     * @see  #ClearSearchList
     * @see  #MultiSearch
     */
    @UnsupportedAppUsage
    public int ClearAllSearchList() {
        try {
            return DeviceServer.getIRFIDService().cmdClearAllMultiTagList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Commands tag(s) to write several blocks in memory bank.
     * Tag selection mask defined by {@link #SetSelMask} is used to define
     * which tag (or a group of tags) is affected by this command.
     * To check current selection mask, use {@link #GetSelMask}.
     * <p />
     * Use with caution and carefully design tag selection mask,
     * so as not to overwrite precious data in unwanted tag(s) by mistake.
     * <p style='margin-bottom:0;'>
     * The return value of this method does NOT contain responses from tags.
     * The responses from tags can be obtained via:
     * </p>
     * <ul>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CALLBACK
       callback},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CUSTOM_INTENT custom&nbsp;intent},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_KBDMSG
       keyboard&nbsp;event},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_COPYPASTE
       copy&nbsp;&amp;&nbsp;paste&nbsp;event}, or</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CTRLV
       ctrl&nbsp;+&nbsp;V&nbsp;event}.</li>
     * </ul>
     * To check and modify type of notification for tag responses, use
     * {@link #GetResultType} or {@link #SetResultType}.
     * <p />
     * A response from tag includes <code>"e="</code>, followed by a tag ID
     * from accessed tag.
     * <pre>     e=[tag ID]</pre>
     * To check or modify tag ID format for responses, use
     * {@link #GetDataFormat} or {@link #SetDataFormat}.
     *
     * @param acsTag the input parameters and returned results.
     * <pre>
     * // input parameters
     * acsTag.length // the number of blocks to write.
     *               // value is from 1 to 64 (unit: word).
     *
     * acsTag.membank // the memory bank of the tag to write
     *                // 0  RESERVED bank
     *                // 1  EPC bank
     *                // 2  TID bank
     *                // 3  USER bank
     *
     * acsTag.offset // an offset from the beginning of the memory bank.
     *               // value is from 0 to 255 (unit: word).
     *
     * acsTag.acsPwd // the access password.
     *               // Both decimal or hexadecimal notation is allowed.
     *               // A string starting "0x" will be interpreted as
     *               // hexadecimal number.
     *
     * acsTag.wTagData // the data to write. This value will be handled as
     *                 // hexadecimal and prefix "0x" is not necessary.
     *                 // The length of wTagData should be the quadruple of
     *                 // acsTag.length.
     *
     * acsTag.blkMode  // the block write unit.
     *                 // 0  auto detect (default)
     *                 // 1  force one word block write
     *                 // 2  force two word block write
     *
     * // return results
     * acsTag.tagId  // ID of an accessed tag.
     *
     * acsTag.errTag // UHF RFID EPC Global Gen2 Tag error code.
     *
     * acsTag.errOp  // UHF RF Transceiver error code.
     * </pre>
     *
     * @return whether the command success.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     * Please refer to {@link device.common.rfid.AccessTag#errTag acsTag.errTag}
     * or {@link device.common.rfid.AccessTag#errOp acsTag.errOp} to check
     * error codes.
     *
     * @see  device.sdk.RFIDManager#BlockErase
     * @see  #SetSelMask
     * @see  #GetSelMask
     * @see  #GetTxDataFormat
     * @see  #SetTxDataFormat
     * @see  #GetResultType
     * @see  #SetResultType
     */
    @UnsupportedAppUsage
    public int BlockWrite(AccessTag acsTag) {
        try {
            return DeviceServer.getIRFIDService().cmdBlockWrite(acsTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Commands tag(s) to erase (zero-fill) several blocks in memory bank.
     * Tag selection mask defined by {@link #SetSelMask} is used to define
     * which tag (or a group of tags) is affected by this command.
     * To check current selection mask, use {@link #GetSelMask}.
     * <p />
     * Use with caution and carefully design tag selection mask,
     * so as not to erase precious data in unwanted tag(s) by mistake.
     * <p style='margin-bottom:0;'>
     * The return value of this method does NOT contain responses from tags.
     * The responses from tags can be obtained via:
     * </p>
     * <ul>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CALLBACK
       callback},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CUSTOM_INTENT custom&nbsp;intent},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_KBDMSG
       keyboard&nbsp;event},</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_COPYPASTE
       copy&nbsp;&amp;&nbsp;paste&nbsp;event}, or</li>
     * <li>{@link device.common.rfid.RFIDConst.ResultType#RFID_RESULT_CTRLV
       ctrl&nbsp;+&nbsp;V&nbsp;event}.</li>
     * </ul>
     * To check and modify type of notification for tag responses, use
     * {@link #GetResultType} or {@link #SetResultType}.
     * <p />
     * A response from tag includes <code>"e="</code>, followed by a tag ID
     * from accessed tag.
     * <pre>     e=[tag ID]</pre>
     * To check or modify tag ID format for responses, use
     * {@link #GetDataFormat} or {@link #SetDataFormat}.
     *
     * @param acsTag the input parameters and returned results.
     * <pre>
     * // input parameters
     * acsTag.length // the number of blocks to delete.
     *               // Value is from 1 to 255 (unit: word)
     *
     * acsTag.memBank // the memory bank of the tag to delete.
     *                // 0  RESERVED bank
     *                // 1  EPC bank
     *                // 2  TID bank
     *                // 3  USER bank
     *
     * acsTag.offset // an offset from the beginning of the memory bank.
     *               // Value is from 0 to 255 (unit: word).
     *
     * acsTag.acsPwd // the access password.
     *               // Both decimal or hexadecimal notation is allowed.
     *               // A string starting "0x" will be interpreted as
     *               // hexadecimal number.
     *
     * acsTag.blkMode  // the block erase unit.
     *                 // 0  auto detect (default)
     *                 // 1  force one word block write
     *                 // 2  force two word block write
     *
     * // return results
     * acsTag.tagid  // ID of an accessed tag.
     *
     * acsTag.errtag // UHF RFID EPC Global Gen2 tag error code.
     *
     * acsTag.errop  // UHF RF transceiver error code.
     * </pre>
     *
     * @return whether the command success.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // command successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     * Please refer to {@link device.common.rfid.AccessTag#errTag acsTag.errTag}
     * or {@link device.common.rfid.AccessTag#errOp acsTag.errOp} to check
     * error codes.
     *
     * @see  device.sdk.RFIDManager#BlockWrite
     * @see  #SetSelMask
     * @see  #GetSelMask
     * @see  #GetTxDataFormat
     * @see  #SetTxDataFormat
     * @see  #GetResultType
     * @see  #SetResultType
     */
    @UnsupportedAppUsage
    public int BlockErase(AccessTag acsTag) {
        try {
            return DeviceServer.getIRFIDService().cmdBlockErase(acsTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries TagFocus&trade; feature setting of RFID reader.
     * TagFocus lets reader to focus on undiscovered tags, 
     * by preventing tags from responding to the same RFID reader again,
     * once they are discovered.
     * <p />
     * For optimal performance, both RFID reader and tag should support
     * TagFocus feature. Monza 4, 5, 6, and X series tags manufactured by Impinj
     * are known to support TagFocus feature.
     * <p />
     * To enable or disalbe TagFocus feature, use {@link #SetTagFocus}.
     * <p />
     * TagFocus is a proprietary chip technology made by Impinj.
     * TagFocus is a registered trademark of Impinj, Inc.
     * @return current TagFocus setting value, or
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR} if error
     * occurred.
     * <pre>
     *     0 // TagFocus feature is disabled
     *     1 // TagFocus feature is enabled
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetTagFocus
     */
    @UnsupportedAppUsage
    public int GetTagFocus() {
        try {
            return DeviceServer.getIRFIDService().cmdGetTagFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Enables or disables TagFocus&trade; feature of RFID reader.
     * TagFocus lets reader to focus on undiscovered tags, 
     * by preventing tags from responding to the same RFID reader again,
     * once they are discovered.
     * <p />
     * For optimal performance, both RFID reader and tag should support
     * TagFocus feature. Monza 4, 5, 6, and X series tags manufactured by Impinj
     * are known to support TagFocus feature.
     * <p />
     * To check if TagFocus feature is enabled, use {@link #GetTagFocus}.
     * <p />
     * TagFocus is a proprietary chip technology made by Impinj.
     * TagFocus is a registered trademark of Impinj, Inc.
     * @param enable flag.
     * <pre>
     *     0 // disables TagFocus feature
     *     1 // enables TagFocus feature
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetTagFocus
     *
     */
    @UnsupportedAppUsage
    public int SetTagFocus(int enable) {
        try {
            return DeviceServer.getIRFIDService().cmdSetTagFocus(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries FastID&trade; feature setting of RFID reader.
     * FastID may reduce time to acquire Tag Identifier (TID),
     * by embedding a process of fetching TIDs of tags to inventory process.
     * <p />
     * For optimal performance, both RFID reader and tag should support
     * FastID feature. Monza 4, 5, 6, and X series tags manufactured by Impinj
     * are known to support FastID feature.
     * <p />
     * To enable or disable FastID feature, use {@link #SetFastID}.
     * <p />
     * FastID is a proprietary chip technology made by Impinj.
     * FastID is a registered trademark of Impinj, Inc.
     * @return current FastID setting value, or
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR} if error
     * occurred.
     * <pre>
     *     0 // FastID feature is disabled
     *     1 // FastID feature is enabled
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetFastID
     *
     */
    @UnsupportedAppUsage
    public int GetFastID() {
        try {
            return DeviceServer.getIRFIDService().cmdGetFastID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Enables or disables FastID&trade; feature of RFID reader.
     * FastID may reduce time to acquire Tag Identifier (TID),
     * by embedding a process of fetching TIDs of tags to inventory process.
     * <p />
     * For optimal performance, both RFID reader and tag should support
     * FastID feature. Monza 4, 5, 6, and X series tags manufactured by Impinj
     * are known to support FastID feature.
     * <p />
     * To check if FastID feature is enabled, use {@link #GetFastID}.
     * <p />
     * FastID is a proprietary chip technology made by Impinj.
     * FastID is a registered trademark of Impinj, Inc.
     * @param enable flag.
     * <pre>
     *     0 // disables FastID feature
     *     1 // enables FastID feature
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetFastID
     *
     */
    @UnsupportedAppUsage
    public int SetFastID(int enable) {
        try {
            return DeviceServer.getIRFIDService().cmdSetFastID(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries tag ID format followed by {@link #StartInventory} or tag
     * accessing commands.
     * Tag accessing commands are:
     * <ul>
     * <li>{@link #ReadTag}</li>
     * <li>{@link #WriteTag}</li>
     * <li>{@link #KillTag}</li>
     * <li>{@link #LockTag}</li>
     * <li>{@link #PermaLockTag}</li>
     * <li>{@link #BlockWrite}</li>
     * <li>{@link #BlockErase}</li>
     * </ul>
     * <p />
     * To modify current tag ID format, use {@link #SetDataFormat}.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @return tag ID format, or 
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR} if
     * communication error.
     * <pre>
     * public static final int PC_EPC_CRC = 0;
     * // PC + EPC + CRC
     * public static final int PC_EPC = 1;
     * // PC + EPC
     * public static final int EPC_CRC = 2; 
     * // EPC + CRC
     * public static final int EPC_ONLY = 3;
     * // EPC
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetDataFormat
     */
    @UnsupportedAppUsage
    public int GetDataFormat() {
        try {
            return DeviceServer.getIRFIDService().cmdGetDataFormat();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies tag ID format followed by {@link #StartInventory} or tag
     * accessing commands.
     * Tag accessing commands are:
     * <ul>
     * <li>{@link #ReadTag}</li>
     * <li>{@link #WriteTag}</li>
     * <li>{@link #KillTag}</li>
     * <li>{@link #LockTag}</li>
     * <li>{@link #PermaLockTag}</li>
     * <li>{@link #BlockWrite}</li>
     * <li>{@link #BlockErase}</li>
     * </ul>
     * <p />
     * To query current tag ID format, use {@link #GetDataFormat}.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param index the tag ID format.
     * <pre>
     * public static final int PC_EPC_CRC = 0;
     * // PC + EPC + CRC
     * public static final int PC_EPC = 1;
     * // PC + EPC
     * public static final int EPC_CRC = 2; 
     * // EPC + CRC
     * public static final int EPC_ONLY = 3;
     * // EPC
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetDataFormat
     *
     */
    @UnsupportedAppUsage
    public int SetDataFormat(int index) {
        try {
            return DeviceServer.getIRFIDService().cmdSetDataFormat(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR; 
    }

    /**
     * Queries the report format followed by a {@link #StartInventory} command.
     * <p />
     * To modify current report format, use {@link #SetTxDataFormat}.<br />
     * To modify prefix or suffix, use {@link #SetPrefix} or {@link #SetSuffix}.
     * @return the report format.
     * <pre>
     * public static final int TAG_DATA = 0;
     * // no prefix, no suffix. Tag data only.
     * public static final int PREFIX_TAG_DATA = 1;
     * // prefix followed by tag data.
     * public static final int TAG_DATA_SUFFIX = 2;
     * // tag data followed by suffix.
     * public static final int PREFIX_TAG_DATA_SUFFIX = 3;
     * // tag data enclosed by both prefix and suffix.
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetTxDataFormat
     * @see  device.sdk.RFIDManager#GetPrefix
     * @see  device.sdk.RFIDManager#GetSuffix
     *
     */
    @UnsupportedAppUsage
    public int GetTxDataFormat() {
        try {
            return DeviceServer.getIRFIDService().cmdGetTxDataFormat();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies the report format followed by a {@link #StartInventory} command.
     * <p />
     * To check current report format, use {@link #GetTxDataFormat}.<br />
     * To modify prefix or suffix, use {@link #SetPrefix} or {@link #SetSuffix}.
     *
     * @param index the report format.
     * <pre>
     * public static final int TAG_DATA = 0;
     * // no prefix, no suffix. Tag data only.
     * public static final int PREFIX_TAG_DATA = 1;
     * // prefix followed by tag data.
     * public static final int TAG_DATA_SUFFIX = 2;
     * // tag data followed by suffix.
     * public static final int PREFIX_TAG_DATA_SUFFIX = 3;
     * // tag data enclosed by both prefix and suffix.
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetTxDataFormat
     * @see  device.sdk.RFIDManager#SetPrefix
     * @see  device.sdk.RFIDManager#SetSuffix
     *
     */
    @UnsupportedAppUsage
    public int SetTxDataFormat(int index) {
        try {
            return DeviceServer.getIRFIDService().cmdSetTxDataFormat(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries a prefix string.
     * A prefix may be added at the start of response messages from RFID reader,
     * if {@link #GetTxDataFormat} is equal to
{@link device.common.rfid.RFIDConst.RFIDConfig#TX_FORMAT_PREFIX_TAG_DATA_SUFFIX}
     * or
{@link device.common.rfid.RFIDConst.RFIDConfig#TX_FORMAT_PREFIX_TAG_DATA}.
     * <p />
     * To modify the current prefix string, use {@link #SetPrefix}.<br />
     * To check or modify data format, use {@link #GetTxDataFormat} or
     * {@link #SetTxDataFormat}.
     * @return the prefix string, or null if error occurred.
     * @see  #SetPrefix
     * @see  #GetTxDataFormat
     * @see  #SetTxDataFormat
     */
    @UnsupportedAppUsage
    public String GetPrefix() {
        try {
            return DeviceServer.getIRFIDService().cmdGetPrefix();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Modifies a prefix string.
     * A prefix may be added at the start of response messages from RFID reader,
     * if {@link #GetTxDataFormat} is equal to
{@link device.common.rfid.RFIDConst.RFIDConfig#TX_FORMAT_PREFIX_TAG_DATA_SUFFIX}
     * or
{@link device.common.rfid.RFIDConst.RFIDConfig#TX_FORMAT_PREFIX_TAG_DATA}.
     * <p />
     * To check the current prefix string, use {@link #GetPrefix}.<br />
     * To check or modify data format, use {@link #GetTxDataFormat} or
     * {@link #SetTxDataFormat}.
     * @param prefix a prefix string.
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     * @see  #GetPrefix
     * @see  #GetTxDataFormat
     * @see  #SetTxDataFormat
     */
    @UnsupportedAppUsage
    public int SetPrefix(String prefix) {
        try {
            return DeviceServer.getIRFIDService().cmdSetPrefix(prefix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries a suffix string.
     * A suffix may be added at the end of response messages from RFID reader,
     * if {@link #GetTxDataFormat} is equal to
{@link device.common.rfid.RFIDConst.RFIDConfig#TX_FORMAT_PREFIX_TAG_DATA_SUFFIX}
     * or
{@link device.common.rfid.RFIDConst.RFIDConfig#TX_FORMAT_TAG_DATA_SUFFIX}.
     * <p />
     * To modify the current suffix string, use {@link #SetSuffix}.<br />
     * To check or modify data format, use {@link #GetTxDataFormat} or
     * {@link #SetTxDataFormat}.
     * @return the suffix string, or null if error occurred.
     * @see  #SetSuffix
     * @see  #GetTxDataFormat
     * @see  #SetTxDataFormat
     */
    @UnsupportedAppUsage
    public String GetSuffix() {
        try {
            return DeviceServer.getIRFIDService().cmdGetSuffix();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Modifies a suffix string.
     * A suffix may be added at the end of response messages from RFID reader,
     * if {@link #GetTxDataFormat} is equal to
{@link device.common.rfid.RFIDConst.RFIDConfig#TX_FORMAT_PREFIX_TAG_DATA_SUFFIX}
     * or
{@link device.common.rfid.RFIDConst.RFIDConfig#TX_FORMAT_TAG_DATA_SUFFIX}.
     * <p />
     * To check the current suffix string, use {@link #GetSuffix}.<br />
     * To check or modify data format, use {@link #GetTxDataFormat} or
     * {@link #SetTxDataFormat}.
     * @param suffix a suffix string.
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     * @see  #GetSuffix
     * @see  #GetTxDataFormat
     * @see  #SetTxDataFormat
     */
    @UnsupportedAppUsage
    public int SetSuffix(String suffix) {
        try {
            return DeviceServer.getIRFIDService().cmdSetSuffix(suffix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Queries the 1st suffix string.
     * Depending on {@link #SetTxDataFormat} setting, the 1st suffix may be
     * added at the end of response messages from RFID reader.
     * Currently no format supports adding the 1st suffix.
     * <p />
     * To modify the current 1st suffix string, use {@link #SetSuffix1}.
     * @return the 1st suffix string, or null if error occurred.
     * @see  device.sdk.RFIDManager#SetSuffix1
     */
    @Deprecated
    @UnsupportedAppUsage
    public String GetSuffix1() {
        try {
            return DeviceServer.getIRFIDService().cmdGetSuffix1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @hide
     * Modifies the 1st suffix string.
     * Depending on {@link #SetTxDataFormat} setting, the 1st suffix may be
     * added at the end of response messages from RFID reader.
     * Currently no format supports adding the 1st suffix.
     * <p />
     * To check the current 1st suffix string, use {@link #GetSuffix1}.
     * @param suffix1 the 1st suffix string.
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     * @see  device.sdk.RFIDManager#GetSuffix1
     */
    @Deprecated
    @UnsupportedAppUsage
    public int SetSuffix1(String suffix1) {
        try {
            return DeviceServer.getIRFIDService().cmdSetSuffix1(suffix1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Queries the 2nd suffix string.
     * Depending on {@link #SetTxDataFormat} setting, the 2nd suffix may be
     * added at the end of response messages from RFID reader.
     * Currently no format supports adding the 2nd suffix.
     * <p />
     * To modify the current 2nd suffix string, use {@link #SetSuffix2}.
     * @return the 2nd suffix string, or null if error occurred.
     * @see  device.sdk.RFIDManager#SetSuffix2
     */
    @Deprecated
    @UnsupportedAppUsage
    public String GetSuffix2() {
        try {
            return DeviceServer.getIRFIDService().cmdGetSuffix2();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @hide
     * Modifies the 2nd suffix string.
     * Depending on {@link #SetTxDataFormat} setting, the 2nd suffix may be
     * added at the end of response messages from RFID reader.
     * Currently no format supports adding the 2nd suffix.
     * <p />
     * To check the current 2nd suffix string, use {@link #GetSuffix2}.
     * @param suffix2 the 2nd suffix string.
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     * @see  device.sdk.RFIDManager#GetSuffix2
     */
    @Deprecated
    @UnsupportedAppUsage
    public int SetSuffix2(String suffix2) {
        try {
            return DeviceServer.getIRFIDService().cmdSetSuffix2(suffix2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Queries if display of data in LCD screen during tag inventory is enabled.
     * Disabling display may speed up tag inventory process.
     * <p />
     * To modify current setting, use {@link #SetLcdOff}.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @return current data display setting, or
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR} if an error
     * occurred during communication to RFID reader.
     * <pre>
     * 0 // enable data display
     * 1 // disable data display
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetLcdOff
     *
     */
    @Deprecated
    @UnsupportedAppUsage
    public int GetLcdOff() {
        try {
            return DeviceServer.getIRFIDService().cmdGetLcdOff();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Enable/disable display of data in LCD screen during tag inventory.
     * Disabling display may speed up tag inventory process.
     * <p />
     * To check current setting, use {@link #GetLcdOff}.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param lcdOff enable or disable data display.
     * <pre>
     * 0 // enable data display
     * 1 // disable data display
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetLcdOff
     *
     */
    @Deprecated
    @UnsupportedAppUsage
    public int SetLcdOff(int lcdOff) {
        try {
            return DeviceServer.getIRFIDService().cmdSetLcdOff(lcdOff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies the letter case format when reading data from RFID tag.
     *
     * <p />NOTE: This method is only compatible with RF850.
     *
     * @param datacase letter case format.
     * <pre>
     *     0 // use lower case (default)
     *     1 // use upper case
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     */
    @UnsupportedAppUsage
    public int SetDataCase(int datacase) {
        try {
            return DeviceServer.getIRFIDService().cmdSetDataCase(datacase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries the inventory settings of RFID reader.
     *
     * @param moi the container for query result.
     * <pre>
     * public class ModeOfInvent {
     *     public int single;
     *         0 means continuous mode
     *         1 means single mode
     *     public int select;
     *         0 means non-select mode(default)
     *         2 means exclusion select mode
     *         3 means selece mode
     *     public int timeout;
     *         0 means infinite(default)
     *         available from 1 to 0xFFFFFFFF(unit. msec)
     * }
     * </pre>
     *
     * @return whether query was success or not. Note that return value does
     * not contain actual query results. Instead, look for moi argument to
     * check what returned from query.</br />
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetOperationMode
     *
     */
    @UnsupportedAppUsage
    public int GetOperationMode(ModeOfInvent moi) {
        try {
            return DeviceServer.getIRFIDService().cmdGetOpMode(moi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies the inventory settings of RFID reader.
     * <p />
     * To check current inventory settings, use {@link #GetOperationMode}.
     *
     * @param moi the inventory settings.
     * <pre>
     * public class ModeOfInvent {
     *     public int single;
     *         0 means continuous mode
     *         1 means single mode
     *     public int select;
     *         0 means non-select mode(default)
     *         2 means exclusion select mode
     *         3 means selece mode
     *     public int timeout;
     *         0 means infinite(default)
     *         available from 1 to 0xFFFFFFFF(unit. msec)
     * }
     * </pre>
     * <table border='2' cellspacing='0'>
     * <thead>
     * <tr> <th>Option name</th> <th>Description</th> <th>Default value</th> </tr>
     * </thead>
     * <tbody>
     * <tr>
     *   <td>Single</td>
     *   <td>{@link device.common.rfid.RFIDConst.RFIDConfig#INVENTORY_MODE_SINGLE Single}
     *    - inventory process stops as soon as the RFID reader identifies a tag.
     *   <p />
     *   {@link device.common.rfid.RFIDConst.RFIDConfig#INVENTORY_MODE_CONTINUOUS continuous}
     *   - inventory process continues until invocation of {@link #Stop} or
     *   timeout.</td>
     *   <td>{@link device.common.rfid.RFIDConst.RFIDConfig#INVENTORY_MODE_CONTINUOUS continuous}</td>
     * </tr>
     * <tr>
     *   <td>Select</td>
     *   <td>{@link device.common.rfid.RFIDConst.RFIDConfig#INVENTORY_SELECT_NONE Non-select mode} - no selection filter is applied. All tags respond.
     *   <p />
     *   {@link device.common.rfid.RFIDConst.RFIDConfig#INVENTORY_SELECT_EXCLUSION Exclusion select mode} - selection filter is applied negatively.
     *   Tags DOES NOT satisfying selection filter specified by 
     *   {@link #SetSelMask} will respond.
     *   <p />
     *   {@link device.common.rfid.RFIDConst.RFIDConfig#INVENTORY_SELECT_INCLUSION Select mode} - selection filter is applied positively. Only tags satisfying
     *   selection filter specified by {@link #SetSelMask} will respond.
     *   </td>
     *   <td>{@link device.common.rfid.RFIDConst.RFIDConfig#INVENTORY_SELECT_NONE Non-select mode}</td>
     * </tr>
     * <tr>
     *   <td>Timeout</td>
     *   <td>0 (=infinite)<br /> <code>1</code> to <code>0xFFFFFFFF (unit: ms)</code> </td>
     *   <td><code>0</code>(=infinite)</td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetOperationMode
     *
     */
    @UnsupportedAppUsage
    public int SetOperationMode(ModeOfInvent moi) {
        try {
            return DeviceServer.getIRFIDService().cmdSetOpMode(moi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries current result notification type.
     * <p />
     * To modify current result notification type, use {@link #SetResultType}.
     *
     * @return the result notification type, or 
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR} if an error
     * occurred during communication to RFID reader.
     * <pre>
     * public static final int RFID_RESULT_CALLBACK = 0;
     * public static final int RFID_RESULT_KBDMSG = 1;
     * public static final int RFID_RESULT_COPYPASTE = 2;
     * public static final int RFID_RESULT_EVENT = 3;
     * public static final int RFID_RESULT_CUSTOM_INTENT = 4;
     * public static final int RFID_RESULT_CTRLV = 6;
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetResultType
     * @see #GetCustomIntentConfig
     * @see #SetCustomIntentConfig
     *
     */
    @UnsupportedAppUsage
    public int GetResultType() {
        try {
            return DeviceServer.getIRFIDService().cmdGetResultType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies the result notification type.
     * <p />
     * To check current result notification type, use {@link #GetResultType}.
     *
     * @param resultType means the result notification type.
     * <pre>
     * public static final int RFID_RESULT_CALLBACK = 0;
     * public static final int RFID_RESULT_KBDMSG = 1;
     * public static final int RFID_RESULT_COPYPASTE = 2;
     * public static final int RFID_RESULT_EVENT = 3;
     * public static final int RFID_RESULT_CUSTOM_INTENT = 4;
     * public static final int RFID_RESULT_CTRLV = 6;
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are: 
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetResultType
     * @see #GetCustomIntentConfig
     * @see #SetCustomIntentConfig
     *
     */
    @UnsupportedAppUsage
    public void SetResultType(int resultType) {
        try {
            DeviceServer.getIRFIDService().cmdSetResultType(resultType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Queries current type of the terminator.
     * A terminator is used to declare the end of message from RFID reader to
     * a host device. Type of terminator varies according to RFID reader models.
     * Therefore, choose proper type of the terminator in accordance with RFID
     * reader model. Choosing improper type of the terminator may cause
     * error in reader-to-host communication.
     * <p />
     * Asynchronous commands, of which result is not given immediately as
     * return value, are affected by terminator setting.
     * Below is a list of such commands.
     * <ul>
     * <li>{@link #StartInventory }</li>
     * <li>{@link #ReadTag }</li>
     * <li>{@link #WriteTag }</li>
     * <li>{@link #LockTag }</li>
     * <li>{@link #KillTag }</li>
     * <li>{@link #PermaLockTag }</li>
     * <li>{@link #SingleSearch }</li>
     * <li>{@link #MultiSearch }</li>
     * <li>{@link #WildcardSearch }</li>
     * <li>{@link #GetSearchList }</li>
     * <li>{@link #GetSearchList_ext }</li>
     * <li>{@link #BlockWrite }</li>
     * <li>{@link #BlockErase }</li>
     * </ul>
     * <p />
     * To modify type of terminator, use {@link #SetTerminatorType}.
     *
     * @return type of the terminator, or
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR} if
     * communication error occurred.
     * <pre>
     * public static final int RFID_TERMINATOR_NONE = 0;
     * public static final int RFID_TERMINATOR_SPACE = 1;
     * public static final int RFID_TERMINATOR_TAB = 2;
     * public static final int RFID_TERMINATOR_LF = 3;
     * public static final int RFID_TERMINATOR_TAB_LF = 4;
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetTerminatorType
     *
     */
    @UnsupportedAppUsage
    public int GetTerminatorType() {
        try {
            return DeviceServer.getIRFIDService().cmdGetTerminatorType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies type of the terminator.
     * A terminator is used to declare the end of message from RFID reader to
     * a host device. Type of terminator varies according to RFID reader models.
     * Therefore, choose proper type of the terminator in accordance with RFID
     * reader model. Choosing improper type of the terminator may cause
     * error in reader-to-host communication.
     * <p />
     * Asynchronous commands, of which result is not given immediately as
     * return value, are affected by terminator setting.
     * Below is a list of such commands.
     * <ul>
     * <li>{@link #StartInventory }</li>
     * <li>{@link #ReadTag }</li>
     * <li>{@link #WriteTag }</li>
     * <li>{@link #LockTag }</li>
     * <li>{@link #KillTag }</li>
     * <li>{@link #PermaLockTag }</li>
     * <li>{@link #SingleSearch }</li>
     * <li>{@link #MultiSearch }</li>
     * <li>{@link #WildcardSearch }</li>
     * <li>{@link #GetSearchList }</li>
     * <li>{@link #GetSearchList_ext }</li>
     * <li>{@link #BlockWrite }</li>
     * <li>{@link #BlockErase }</li>
     * </ul>
     * <p />
     * To check current type of terminator, use {@link #GetTerminatorType}.
     *
     * @param teminatorType type of the teminator to be used in reader-to-host communication.
     * <pre>
     * public static final int RFID_TERMINATOR_NONE = 0;
     * public static final int RFID_TERMINATOR_SPACE = 1;
     * public static final int RFID_TERMINATOR_TAB = 2;
     * public static final int RFID_TERMINATOR_LF = 3;
     * public static final int RFID_TERMINATOR_TAB_LF = 4;
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetTerminatorType
     *
     */
    @UnsupportedAppUsage
    public void SetTerminatorType(int teminatorType) {
        try {
            DeviceServer.getIRFIDService().cmdSetTerminatorType(teminatorType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @hide
     * Resets the Bluetooth configuration of RFID reader to default.
     *
     */
    @Deprecated
    @UnsupportedAppUsage
    public void SetBtDefault() {
        try {
            DeviceServer.getIRFIDService().cmdSetBTDefault();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Queries the extension report format of RFID inventory.
     *
     * <p />NOTE: This method is incompatible with RF850.
     *
     * @param reportFormatOfInvent_ext the container for query result.
     * <pre>
     * public class ReportFormatOfInvent_ext implements Parcelable {
     *   public int time;
     *         // whether the report includes internal timer value.
     *         // 1 means enabled and 0 means disabled.
     *   public int rssi;
     *         // whether the report includes RSSI value.
     *         // 1 means enabled and 0 means disabled.
     *   public int channel;
     *         // whether the report includes channel value.
     *         // 1 means enabled and 0 means disabled.
     *   public int temp;
     *         // whether the report includes temperature value of the reader.
     *         // 1 means enabled and 0 means disabled.
     *   public int phase;
     *         // whether the report includes phase of the identified tag.
     *         // 1 means enabled and 0 means disabled.
     *   public int ant;
     *         // whether the report includes which antenna identified the tag.
     *         // 1 means enabled and 0 means disabled.
     * }
     * </pre>
     *
     * @return whether query was success or not. Note that return value does
     * not contain actual query results. Instead, look for
     * reportFormatOfInvent_ext argument to check what returned from query. <br />
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * public final static int NOT_SUPPORTED = -8;
     * // this function cannot work on devices other than PM500 and RF851 reader
     * </pre>
     *
     * @see  device.common.rfid.ReportFormatOfInvent_ext
     * @see  device.sdk.RFIDManager#SetInventoryReportFormat_ext
     *
     */
    @UnsupportedAppUsage
    public int GetInventoryReportFormat_ext(ReportFormatOfInvent_ext reportFormatOfInvent_ext) {
        try {
            return DeviceServer.getIRFIDService().cmdGetInventoryReportFormat_ext(reportFormatOfInvent_ext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Modifies the extension report format of RFID inventory.
     *
     * <p />NOTE: This method is incompatible with RF850.
     *
     * @param reportFormatOfInvent_ext defines settings on report format of inventory.
     * <pre>
     * public class ReportFormatOfInvent_ext implements Parcelable {
     *   public int time;
     *         // whether the report includes internal timer value.
     *         // 1 means enabled and 0 means disabled.
     *   public int rssi;
     *         // whether the report includes RSSI value.
     *         // 1 means enabled and 0 means disabled.
     *   public int channel;
     *         // whether the report includes channel value.
     *         // 1 means enabled and 0 means disabled.
     *   public int temp;
     *         // whether the report includes temperature value of the reader.
     *         // 1 means enabled and 0 means disabled.
     *   public int phase;
     *         // whether the report includes phase of the identified tag.
     *         // 1 means enabled and 0 means disabled.
     *   public int ant;
     *         // whether the report includes which antenna identified the tag.
     *         // 1 means enabled and 0 means disabled.
     * }
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * public final static int NOT_SUPPORTED = -8;
     * // this function cannot work on devices other than PM500 and RF851 reader
     * </pre>
     *
     * @see  device.common.rfid.ReportFormatOfInvent_ext
     * @see  device.sdk.RFIDManager#GetInventoryReportFormat_ext
     *
     */
    @UnsupportedAppUsage
    public int SetInventoryReportFormat_ext(ReportFormatOfInvent_ext reportFormatOfInvent_ext) {
        try {
            return DeviceServer.getIRFIDService().cmdSetInventoryReportFormat_ext(reportFormatOfInvent_ext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Modifies the baud rate for UART connection.
     * Changes will take effect after reset.
     *
     * <p />NOTE: This method is incompatible with RF850.
     *
     * @param baudrate the baud rate (unit: bps).
     * Supported baud rates are:
     * <ul>
     * <li>9600</li>
     * <li>19200</li>
     * <li>38400</li>
     * <li>57600</li>
     * <li>115200 (default)</li>
     * <li>230400</li>
     * <li>460800</li>
     * <li>921600</li>
     * </ul>
     * @return whether modification was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * public final static int NOT_SUPPORTED = -8;
     * // this function cannot work on devices other than PM500 and RF851 reader
     * </pre>
     */
    @Deprecated
    @UnsupportedAppUsage
    public int SetDeviceBaudrate(int baudrate) {
        try {
            return DeviceServer.getIRFIDService().cmdSetDeviceBaudrate(baudrate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Queries a configuration for multiple antennas.
     * Up to two antennas are supported.
     *
     * <p />NOTE: This method is incompatible with RF850.
     *
     * @param antSelect the container for query result.
     * <pre>
     * public class AntSelect {
     * public int ant_1;
     *     configuration for antenna #1.
     *     1 means enabled and 0 means disabled.
     * public int ant_2;
     *     configuration for antenna #2.
     *     1 means enabled and 0 means disabled.
     * }
     * </pre>
     * @return whether query was successful or not. Note that return value does
     * not contain actual query results. Instead, look for antSelect argument
     * to check what returned from query. <br />
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * public final static int NOT_SUPPORTED = -8;
     * // this function cannot work on devices other than PM500 and RF851 reader
     * </pre>
     * @see device.common.rfid.AntSelect
     * @see #SetAntSelect(AntSelect)
     */
    @Deprecated
    @UnsupportedAppUsage
    public int GetAntSelect(AntSelect antSelect) {
        try {
            return DeviceServer.getIRFIDService().cmdGetAntSelect(antSelect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Modifies a configuration for multiple antennas.
     * Each antenna can be enabled or disabled individually.
     * Up to two antennas are supported.
     *
     * <p />NOTE: This method is incompatible with RF850.
     *
     * @param antSelect defines configuration of each antennas.
     * AntSelect type is as below.
     * <pre>
     * public class AntSelect {
     * public int ant_1;
     *     configuration for antenna #1.
     *     1 means enabled and 0 means disabled.
     * public int ant_2;
     *     configuration for antenna #2.
     *     1 means enabled and 0 means disabled.
     * }
     * </pre>
     * @return whether modification was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * public final static int NOT_SUPPORTED = -8;
     * // this function cannot work on devices other than PM500 and RF851 reader
     * </pre>
     * @see device.common.rfid.AntSelect
     * @see #GetAntSelect(AntSelect)
     */
    @Deprecated
    @UnsupportedAppUsage
    public int SetAntSelect(AntSelect antSelect) {
        try {
            return DeviceServer.getIRFIDService().cmdSetAntSelect(antSelect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Queries a per-antenna TX power configuration.
     * For RFID readers with UART interface supporting multiple antennas,
     * such as PM500, this function supersedes {@link #GetTxPower}.
     * Up to two antennas are supported.
     *
     * <p />NOTE: This method is incompatible with RF850.
     *
     * @param antPower the container for query result.
     * <pre>
     * public class AntPower {
     * public int ant_1_power;
     *     TX power modifier value for antenna #1 (unit: dB).
     *     Value range is from 0 to -{@link #GetMaxPower MaxPower}.
     * public int ant_2_power;
     *     TX power modifier value for antenna #2 (unit: dB).
     *     Value range is from 0 to -{@link #GetMaxPower MaxPower}.
     * }
     * </pre>
     * @return whether query was successful or not. Note that return value does
     * not contain actual query results. Instead, look for antPower argument
     * to check what returned from query. <br />
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * public final static int NOT_SUPPORTED = -8;
     * // this function cannot work on devices other than PM500 and RF851 reader
     * </pre>
     * @see device.common.rfid.AntPower
     * @see #SetAntPower(AntPower)
     */
    @Deprecated
    @UnsupportedAppUsage
    public int GetAntPower(AntPower antPower) {
        try {
            return DeviceServer.getIRFIDService().cmdGetAntPower(antPower);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Modifies a per-antenna TX power configuration.
     * Each antenna can be configured individually.
     * For RFID readers with UART interface supporting multiple antennas,
     * such as PM500, this function supersedes {@link #SetTxPower}.
     * Up to two antennas are supported.
     *
     * <p />NOTE: This method is incompatible with RF850.
     *
     * @param antPower defines TX power modifier of each antennas.
     * AntPower type is as below.
     * <pre>
     * public class AntPower {
     * public int ant_1_power;
     *     TX power modifier value for antenna #1 (unit: dB).
     *     Value range is from 0 to -{@link #GetMaxPower MaxPower}.
     * public int ant_2_power;
     *     TX power modifier value for antenna #2 (unit: dB).
     *     Value range is from 0 to -{@link #GetMaxPower MaxPower}.
     * }
     * </pre>
     * @return whether modification was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * public final static int NOT_SUPPORTED = -8;
     * // this function cannot work on devices other than PM500 and RF851 reader
     * </pre>
     * @see device.common.rfid.AntPower
     * @see #GetAntPower(AntPower)
     */
    @Deprecated
    @UnsupportedAppUsage
    public int SetAntPower(AntPower antPower) {
        try {
            return DeviceServer.getIRFIDService().cmdSetAntPower(antPower);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries a set of available regions.
     * A result varies from model.
     * The device can be configured to a specific region using
     * {@link #SetRegion(String)}
     *
     * <p />NOTE: This method is incompatible with RF850.
     *
     * @return a comma-separated string notation for list of available regions.
     * For example, assume FCC, KOR, and AUS regions are available.
     * Then return value will be "FCC,KOR,AUS".
     * Note that in case of communication has been interrupted by error,
     * or using this method other than PM500 and RF851 RFID model,
     * null is returned.
     * @see #SetRegion(String)
     */
    @UnsupportedAppUsage
    public String GetAvailableRegion() {
        try {
            return DeviceServer.getIRFIDService().cmdGetAvailableRegion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Modifies region setting for a device.
     * In order to comply with rules and regulations governing the region,
     * the device shall be configured to a proper region.
     *
     * <p />NOTE: This method is incompatible with RF850.
     *
     * @param region a region should be one in the list of available regions,
     * which can be retrieved via {@link #GetAvailableRegion()}.
     * It uses upper case only.
     * @return whether modification was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * public final static int NOT_SUPPORTED = -8;
     * // this function cannot work on devices other than PM500 and RF851 reader
     * </pre>
     * @see #GetAvailableRegion()
     */
    @UnsupportedAppUsage
    public int SetRegion(String region) {
        try {
            return DeviceServer.getIRFIDService().cmdSetRegion(region);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries a configuration for custom intent.
     * When RFID reader identifies a tag, it reports a read result via
     * notification, among one of:
     * <ul>
     * <li>callback,</li>
     * <li>keyboard event,</li>
     * <li>copy&amp;paste, or</li>
     * <li>custom intent.</li>
     * </ul>
     * The read result will be delivered via custom intent, if the
     * notification type is set to custom intent
     * ({@link #GetResultType}, {@link #SetResultType}).
     * Use this method to know how to configure an intent filter for your app.
     *
     * @param customIntentConfig the container for query result.
     * <pre>
     * public class CustomIntentConfig {
     * public String action;
     * public String category;
     *     // action and category are used to define intent filter for your app.
     * public String extraRfidData;
     *     // a name (key) to get extra data from an intent received.
     *     // the extra data may contain EPC ID of a tag, RSSI, and more.
     * }
     * </pre>
     *
     * @see #SetCustomIntentConfig
     * @see #GetResultType
     * @see #SetResultType
     */
    @UnsupportedAppUsage
    public void GetCustomIntentConfig(CustomIntentConfig customIntentConfig) {
        try {
            DeviceServer.getIRFIDService().cmdGetCustomIntentConfig(customIntentConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifies a configuration for custom intent.
     * When RFID reader identifies a tag, it reports a read result via
     * notification, among one of:
     * <ul>
     * <li>callback,</li>
     * <li>keyboard event,</li>
     * <li>copy&amp;paste, or</li>
     * <li>custom intent.</li>
     * </ul>
     * The read result will be delivered via custom intent, if the
     * notification type is set to custom intent
     * ({@link #GetResultType}, {@link #SetResultType}).
     * Use this method to modify custom intent configuration.
     *
     * @param customIntentConfig defines custom intent configuration.
     * CustomIntentConfig type is as below.
     * <pre>
     * public class CustomIntentConfig {
     * public String action;
     * public String category;
     *     // action and category are used to define intent filter for your app.
     * public String extraRfidData;
     *     // a name (key) to get extra data from an intent received.
     *     // the extra data may contain EPC ID of a tag, RSSI, and more.
     * }
     * </pre>
     *
     * @see #GetCustomIntentConfig
     * @see #GetResultType
     * @see #SetResultType
     */
    @UnsupportedAppUsage
    public void SetCustomIntentConfig(CustomIntentConfig customIntentConfig) {
        try {
            DeviceServer.getIRFIDService().cmdSetCustomIntentConfig(customIntentConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates firmware for RFID reader.
     *
     * <p />NOTE: This method is only compatible with PM500.
     *
     * @param fwFile an firmware file to load.
     *
     * @return whether update was success or not.
     * possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // update successful
     * public final static int FW_NOT_EXISTED = -9;
     * // firmware file does not exist
     * public final static int NOT_SUPPORTED = -8;
     * // model does not support firmware update by this method
     * public final static int OPEN_FAILED = -2;
     * // failed to open UART communication to RFID module
     * public final static int FW_UPDATE_FAILED = -10;
     * // an error occurred while updating firmware
     * </pre>
     */
    @UnsupportedAppUsage
    public int UpdateFirmware(File fwFile) throws IOException, FileNotFoundException {
        int iRet = RFIDConst.CommandErr.SUCCESS;

        Log.d(TAG, "FW File : " + fwFile);
        try {
            if (fwFile == null || !fwFile.exists()) {
                Log.e(TAG, "RFID Firmware update:: file does not exist");
                return RFIDConst.CommandErr.FW_NOT_EXISTED;
            }

            String fileName = fwFile.getName();
            int fileSize = (int) fwFile.length();
            Log.d(TAG, "File name : " + fileName);
            Log.d(TAG, "File size : " + fileSize);
            FileInputStream fis = new FileInputStream(fwFile);
            byte[] fwData = new byte[fis.available()];
            fis.read(fwData, 0, fileSize);

            iRet = DeviceServer.getIRFIDService().updateFirmware(fwData, fileName, fileSize);
            fis.close();

            return iRet;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return iRet;
    }

    /**
     * Gets the Bluetooth firmware version of the connected RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF851 and RF300.
     *
     * @return the Bluetooth firmware version of the RFID reader.
     *
     * @see  device.sdk.RFIDManager#GetBTFwVer
     *
     */
    @UnsupportedAppUsage
    public String GetBTFwVer() {
        try {
            return DeviceServer.getIRFIDService().cmdGetBTFwVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the Bluetooth power class info of the connected RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF851 and RF300.
     *
     * @return the Bluetooth power class info of the RFID reader.
     * possible return values are:
     * <pre>
     * public final static int BT_POWER_CLASS_1 = 1;
     * // power class 1
     * public final static int BT_POWER_CLASS_1 = 2;
     * // power class 2
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetBTPwrClass
     *
     */
    @UnsupportedAppUsage
    public int GetBTPwrClass() {
        try {
            return DeviceServer.getIRFIDService().cmdGetBTPwrClass();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Sets the Bluetooth power class of the connected RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF851 and RF300.
     *
     * @param power defines the Bluetooth power class info.
     *
     * @return whether modification was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetBTPwrClass
     *
     */
    @UnsupportedAppUsage
    public int SetBTPwrClass(int power) {
        try {
            return DeviceServer.getIRFIDService().cmdSetBTPwrClass(power);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Gets the OS version of the connected RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF851 and RF300.
     *
     * @return the OS version of the RFID reader.
     * possible return values are:
     * <pre>
     * 93.xx xx (date)
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetOSVer
     *
     */
    @UnsupportedAppUsage
    public String GetOSVer() {
        try {
            return DeviceServer.getIRFIDService().cmdGetOSVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sets the flag for OS update of the connected RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF851 and RF300.
     *
     * @return whether modification was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetOSUpdate
     *
     */
    @UnsupportedAppUsage
    public int SetOSUpdate() {
        try {
            return DeviceServer.getIRFIDService().cmdSetOSUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Gets the suspend timeout value of the connected RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF851 and RF300.
     *
     * @return the timeout value(unit. secs) of the RFID reader.
     *
     * @see  device.sdk.RFIDManager#SetSuspendTimeout
     *
     */
    @UnsupportedAppUsage
    public int GetSuspendTimeout() {
        try {
            return DeviceServer.getIRFIDService().cmdGetSuspendTimeout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Sets the suspend timeout value of the connected RFID reader.
     *
     * <p />NOTE: This method is only compatible with RF851 and RF300.
     *
     * @param timeout defines timeout value to enter suspend mode(unit. secs and must set minimum 10's).
     *
     * @return whether modification was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetSuspendTimeout
     *
     */
    @UnsupportedAppUsage
    public int SetSuspendTimeout(int timeout) {
        try {
            return DeviceServer.getIRFIDService().cmdSetSuspendTimeout(timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Gets the filter setting of the connected RFID reader.
     *
     * <p />NOTE: This method is incompatible with RF850.
     *
     * @return the setting value of the RFID reader.
     * <pre>
     * 0 // disabled filter
     * 1 // enabled filter
     * </pre>
     *
     * @see  device.sdk.RFIDManager#SetFilterDuplicateTags
     *
     */
    @UnsupportedAppUsage
    public int GetFilterDuplicateTags() {
        try {
            return DeviceServer.getIRFIDService().cmdGetFilterDuplicateTags();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Sets the filter setting of the connected RFID reader.
     *
     * <p />NOTE: This method is incompatible with RF850.
     *
     * @param enable defines setting value to filter duplicate tags.
     * <pre>
     * 0 // disable
     * 1 // enable
     * </pre>
     *
     * @see  device.sdk.RFIDManager#GetFilterDuplicateTags
     *
     */
    @UnsupportedAppUsage
    public void SetFilterDuplicateTags(int enable) {
        try {
            DeviceServer.getIRFIDService().cmdSetFilterDuplicateTags(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @hide
     * Enter BT firmware update mode.
     *
     * <p />NOTE: This method is only compatible with RF851 and RF300.
     *
     * @return whether modification was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * </pre>
     *
     */
    @Deprecated
    @UnsupportedAppUsage
    public int BTFWUpdate() {
        try {
            return DeviceServer.getIRFIDService().cmdBTFWUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries current battery temperature of RFID reader.
     *
     * <p />NOTE: This method is incompatible with PM500 and RF850.
     *
     * @return the battery temperature(`c), or
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR }
     * if communication error.
     *
     */
    @UnsupportedAppUsage
    public double GetBattTemp() {
        try {
            return DeviceServer.getIRFIDService().cmdGetBatteryTemp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries current battery voltage of RFID reader.
     *
     * <p />NOTE: This method is incompatible with PM500 and RF850.
     *
     * @return the battery voltage(mV), or
     * {@link device.common.rfid.RFIDConst.CommandErr#COMM_ERR }
     * if communication error.
     *
     */
    @UnsupportedAppUsage
    public int GetBattVolt() {
        try {
            return DeviceServer.getIRFIDService().cmdGetBatteryVolt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Queries the subscription status on each battery-related events
     * for callback notifications from RFID reader.
     *
     * <p />NOTE: This method is incompatible with PM500, RF850 and RF851(an earlier than 93.06).
     *
     * @param battEvent_ext the container for query result.
     * <pre>
     * public class BattEvent_ext {
     * public int batt;
     *     whether it receive the event of battery level
     *     1 means enabled and 0 means disabled.
     * public int charge;
     *     whether it receive the event of charge status
     *     1 means enabled and 0 means disabled.
     * public int volt;
     *     whether it receive the event of voltage value
     *     1 means enabled and 0 means disabled.
     * public int temp;
     *     whether it receive the event of temperature value
     *     1 means enabled and 0 means disabled.
     * public int cycle;
     *     the cycle of an event
     *     Range from 0 to 100. Real cycle is (cycle * 1) sec.
     * }
     * </pre>
     *
     * @return whether query was successful or not. Note that return value does
     * not contain actual query results. Instead, look for battEvent_ext argument
     * to check what returned from query. <br />
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // query successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error while querying
     * </pre>
     *
     * @see  #SetBattEvent_ext(BattEvent_ext)
     *
     */
    @Deprecated
    @UnsupportedAppUsage
    public int GetBattEvent_ext(BattEvent_ext battEvent_ext) {
        try {
            return DeviceServer.getIRFIDService().cmdGetBatteryEvent_ext(battEvent_ext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * @hide
     * Modifies the subscription status on each battery-related events
     * for callback notifications from RFID reader.
     *
     * <p />NOTE: This method is incompatible with PM500, RF850 and RF851(an earlier than 93.06).
     *
     * @param battEvent_ext defines subscription status on battery-related events.
     * BattEvent_ext type is as below.
     * <pre>
     * public class BattEvent_ext {
     * public int batt;
     *     whether it receive the event of battery level
     *     1 means enabled and 0 means disabled.
     * public int charge;
     *     whether it receive the event of charge status
     *     1 means enabled and 0 means disabled.
     * public int volt;
     *     whether it receive the event of voltage value
     *     1 means enabled and 0 means disabled.
     * public int temp;
     *     whether it receive the event of temperature value
     *     1 means enabled and 0 means disabled.
     * public int cycle;
     *     the cycle of an event
     *     Range from 0 to 100. Real cycle is (cycle * 1) sec.
     * }
     * </pre>
     *
     * @return whether modification was successful or not.
     * Possible return values are:
     * <pre>
     * public final static int SUCCESS = 0;
     * // modification successful
     * public final static int COMM_ERR = -1;
     * // communication has been interrupted by error
     * </pre>
     *
     * @see  #GetBattEvent_ext(BattEvent_ext)
     *
     */
    @Deprecated
    @UnsupportedAppUsage
    public int SetBattEvent_ext(BattEvent_ext battEvent_ext) {
        try {
            return DeviceServer.getIRFIDService().cmdSetBatteryEvent_ext(battEvent_ext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RFIDConst.CommandErr.COMM_ERR;
    }

    /**
     * Queries whether the RFID reader is detected.
     *
     * <p />NOTE: This method is only compatible with RF300 and RF750.
     *
     * @return detect status. Possible values are:
     * <pre>
     * -1 // unknown status. Note that can't check whether the RFID reader is detected or not because already connected via other interface
     * 0 // the RFID reader is not detected
     * 1 // the RFID reader is detected
     * </pre>
     *
     */
    @UnsupportedAppUsage
    public int IsDetected() {
        try {
            return DeviceServer.getIRFIDService().isDetected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Queries the hardware version of RFID reader.
     *
     * <p />NOTE: This method is incompatible with RF850 and PM500.
     *
     * @return hardware version. Possible values are:
     * <pre>
     * 0 // Engineering Validation Test stage
     * 1 // Design Validation Test stage
     * 2 // Mass Production stage
     * </pre>
     *
     */
    @UnsupportedAppUsage
    public int GetHwVersion() {
        try {
            return DeviceServer.getIRFIDService().cmdGetHwVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Force reboot the RFID reader.
     *
     * <p />NOTE: This method is incompatible with RF850 and PM500.
     *
     * @return void
     *
     */
    @Deprecated
    @UnsupportedAppUsage
    public void DoReboot() {
        try {
            DeviceServer.getIRFIDService().cmdDoReboot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Queries whether the RFID reader is in suspended.
     *
     * <p />NOTE: This method is only compatible with RF900.
     *
     * @return suspend status. Possible values are:
     * <pre>
     * 1 // the RFID reader is in suspended
     * 0 // the RFID reader is in operation
     * If this method returns
     * {@link device.common.rfid.RFIDConst.CommandErr#NOT_SUPPORTED},
     * which means this function is not supported.
     * </pre>
     *
     */
    @UnsupportedAppUsage
    public int IsSuspended() {
        try {
            return DeviceServer.getIRFIDService().isSuspended();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

