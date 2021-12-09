package device.sdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.compat.annotation.UnsupportedAppUsage;
import android.util.Log;
import android.media.AudioSystem;
import android.os.ServiceManager;
import android.content.Context;

import device.common.DevInfoIndex;
import device.common.MsrResult;
import device.common.MsrResultCallback;
import device.common.MsrIndex;
import device.common.IMsrResultCallback;

import device.sdk.DeviceServer;

import android.os.RemoteException;
import android.os.Binder;
import android.os.Process;

public class MsrManager {
    private static final String TAG = MsrManager.class.getSimpleName();
    private static MSR_Module mMsrModule = MSR_Module.MMD1000;
    private static MsrManager mInstance = null;
    private enum MSR_Module{
    	MMD1000,
    	PM1100
    }

    @UnsupportedAppUsage
    public static MsrManager getInstance() {
        if (mInstance == null) {
            mInstance = new MsrManager();
        }
        return mInstance;
    }
    @UnsupportedAppUsage
    public MsrManager() {
        try{
            if(DeviceServer.getIDeviceService().getMsrType() == DevInfoIndex.MSR_PM1100 ){
                mMsrModule = MSR_Module.PM1100;
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }

//        String currentModuleVersion = getFirmwareVersion();
//        if (currentModuleVersion != null
//            && currentModuleVersion.length() > 0
//            && currentModuleVersion.charAt(1) == '2') { //for ICCR
//        	mMsrModule = MSR_Module.PM1100;
//        }
    }

    private byte[] SendCommand(byte msgType, byte RC, byte[] Data,byte function) {
        try {
            byte[] returnArray = new byte[9+Data.length];
            int LLHL = Data.length + 2;
            returnArray[0]  =  0x02;                //STX
            returnArray[1]  =  0x4f;                //class
            returnArray[2]  =  function;           	//function
            returnArray[3]  =  (byte)LLHL;          //LL
            returnArray[4]  =  (byte) (LLHL>>8);    //HL
            returnArray[5]  =  msgType;             //msgType
            returnArray[6]  =  RC;                  //RC (AC)

            for(int i= 0; i< Data.length; i++)      //Data
                returnArray[i+7]  =  Data[i];

            returnArray[returnArray.length -2]  = 0x03; //ETX
            byte LRC  = returnArray[0];
            for(int i=1; i<returnArray.length-1; i++)
                LRC ^= returnArray[i];
            returnArray[returnArray.length -1]  = LRC; 	//LRC

            return DeviceServer.getIMsrService().msr_Command(returnArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] SendCommand(byte msgType, byte RC, byte[] Data){
        return SendCommand(msgType,RC,Data,(byte)0x30);
    }

    /**
     * This enum present the Atr Mode
     *
     * @see device.sdk.MsrManager#AtrOnMode
     */
    public enum AtrOnMode{
        @UnsupportedAppUsage
    	ISO,
        @UnsupportedAppUsage
    	EMV
    }

    /**
     * This function power on & ATR on msr service.
     *
     * @param byte 0x00 is ISO and 0x01 is EMV Mode
     *
     * @return ATR status.
     *
     * @see device.sdk.MsrManager#AtrOn
     */
    @UnsupportedAppUsage
    public boolean AtrOn(AtrOnMode atrMode){
        byte[] data;
        if (atrMode == AtrOnMode.ISO) {
            data = new byte[]{0x00};
        } else {
            data = new byte[]{0x01};
        }
        byte[] revArray = SendCommand((byte) 0x82, (byte) 0x00, data);
        if (revArray != null) {
            try {
                if (revArray[5] == (byte) 0x82 && revArray[6] == (byte) 0x00) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * This function ATR down & power down msr service.
     *
     * @see device.sdk.MsrManager#PowerDown
     */
    @UnsupportedAppUsage
    public void PowerDown()
    {
        SendCommand((byte)0x84,(byte) 0x00,new byte[]{});
    }

    /**
     * This function get information that card detected
     *
     * @return whether to insert a card.
     *
     * @see device.sdk.MsrManager#GetCardInserted
     */
    @UnsupportedAppUsage
    public CardInsertState GetCardInserted() {
        byte[] revArray = SendCommand((byte)0x80,(byte) 0x00,new byte[]{});
        if (revArray != null) {
            //check RC
        	try{
                if (revArray[5]== (byte)0x80 && revArray[7]== (byte)0x01) {
                    return CardInsertState.ICC_DETECT;
                }
        	} catch (Exception e) {
        		return CardInsertState.DETECT_FAIL;
        	}
        }
        return CardInsertState.DETECT_FAIL;
    }

    /**
     * This enum present the Card insertion state
     *
     * @see device.sdk.MsrManager#CardInsertState
     */
    public enum CardInsertState{
        @UnsupportedAppUsage
    	ICC_DETECT,
        @UnsupportedAppUsage
    	DETECT_FAIL,
        @UnsupportedAppUsage
    	USER_FAIL
    }

    /**
     * This function is polling fuction refer to card detection
     *
     * @return CardInsertState.
     *
     * @see device.sdk.MsrManager#cardDetectPolling
     */
    @UnsupportedAppUsage
    public CardInsertState cardDetectPolling(int pollingTime){
    	byte[] data = new byte[4];
    	data[0] = (byte) (pollingTime >> 24);
    	data[1] = (byte) (pollingTime >> 16);
    	data[2] = (byte) (pollingTime >> 8);
    	data[3] = (byte) (pollingTime);

        byte[] revArray = SendCommand((byte)0x81,(byte) 0x00, data);
        //SecretDeviceMsrStartRead();
        if(revArray != null)
        {
        	try{
                if(revArray[5]== (byte)0x81 && revArray[6]== (byte)0x00)
                    return CardInsertState.ICC_DETECT;
                else if(revArray[5]== (byte)0x81 && revArray[6]== (byte)0x01)
                    return CardInsertState.DETECT_FAIL;
                else if(revArray[5]== (byte)0x81 && revArray[6]== (byte)0x02)
                    return CardInsertState.USER_FAIL;
        	}catch(Exception e){
        		return CardInsertState.DETECT_FAIL;
        	}
        }
        return CardInsertState.DETECT_FAIL;
    }

    /**
     * This function set System Time
     *
     * current system time setting fuction,
     *
     * @param time informations
     *
     * @return sucess or fail.
     *
     * @see device.sdk.MsrManager#setSystemTime
     */
    @UnsupportedAppUsage
    public boolean setSystemTime(short year, short month, short dayOfWeek, short day,
    		short hour, short minute, short second ,short milliSeconds){
    	byte[] data = new byte[16];

    	data[0] = (byte)(year >> 8);				//year
    	data[1] = (byte) year;

    	data[2] = (byte)(month >> 8);				//month
    	data[3] = (byte) month;

    	data[4] = (byte)(dayOfWeek >> 8);			//dayOfWeek
    	data[5] = (byte) dayOfWeek;

    	data[6] = (byte)(day >> 8);					//day
    	data[7] = (byte) day;

    	data[8] = (byte)(hour >> 8);				//hour
    	data[9] = (byte) hour;

    	data[10] = (byte)(minute >> 8);				// minute
    	data[11] = (byte) minute;

    	data[12] = (byte)(second >> 8);				// second
    	data[13] = (byte) second;

    	data[14] = (byte)(milliSeconds >> 8);		//milliSeconds
    	data[15] = (byte) milliSeconds;

    	byte[] revArray = SendCommand((byte)0x03,(byte) 0x00, data);

        if(revArray != null)
        {
        	try{
                if(revArray[5]== (byte)0x03 && revArray[6]== (byte)0x01)
                    return true;
        	}catch(Exception e){
        		return false;
        	}
        }
        return false;
    }

    /**
    * This function Start payment Transaction
    *
    * @param payment information and time information.
    *
    * @return msrCallback
    *
    * @see device.sdk.MsrManager#paymentTransactionStart
    */
    @UnsupportedAppUsage
    public void paymentTransactionStart(byte firstbyte, int pay,
    		short year, short month, short dayOfWeek, short day,
    		short hour, short minute, short second ,short milliSeconds ){
	    byte[] data = new byte[21];

	    data[0] = firstbyte;
    	data[1] = (byte)(pay >> 24);
    	data[2] = (byte)(pay >> 16);
    	data[3] = (byte)(pay >> 8);
    	data[4] = (byte)(pay);

    	data[5] = (byte)(year >> 8);				//year
    	data[6] = (byte) year;

    	data[7] = (byte)(month >> 8);				//month
    	data[8] = (byte) month;

    	data[9] = (byte)(dayOfWeek >> 8);			//dayOfWeek
    	data[10] = (byte) dayOfWeek;

    	data[11] = (byte)(day >> 8);				//day
    	data[12] = (byte) day;

    	data[13] = (byte)(hour >> 8);				//hour
    	data[14] = (byte) hour;

    	data[15] = (byte)(minute >> 8);				// minute
    	data[16] = (byte) minute;

    	data[17] = (byte)(second >> 8);				// second
    	data[18] = (byte) second;

    	data[19] = (byte)(milliSeconds >> 8);		//milliSeconds
    	data[20] = (byte) milliSeconds;

    	byte[] revArray = SendCommand((byte) 0x85, (byte) 0x00, data);
        SecretDeviceMsrStartRead();
    }

    /*  [20170607 Anthony.bae Begin]   Not use yet.
    @UnsupportedAppUsage
    public boolean loadTerminalConfig (byte[] capabilityData){
    	byte[] revArray = SendCommand((byte) 0x87, (byte) 0x00, capabilityData);
    	if (revArray != null) {
    		try{
    	          if (revArray[5] == (byte) 0x87 && revArray[6] == (byte) 0x00){
    	        	   return true;
    	          }
    		}catch(Exception e){
    			return false;
    		}
          }
        return false;
    }

    @UnsupportedAppUsage
    public boolean loadCAPK(byte[] CapkData){
    	byte[] revArray = SendCommand((byte) 0x88, (byte) 0x00, CapkData);
    	if (revArray != null) {
    		try{
    	          if (revArray[5] == (byte) 0x88 && revArray[6] == (byte) 0x00){
    	        	   return true;
    	          }
    		}catch(Exception e){
    			return false;
    		}
          }
        return false;
    }

    @UnsupportedAppUsage
    public boolean loadAID(byte[] AidData){
    	byte[] revArray = SendCommand((byte) 0x89, (byte) 0x00, AidData);
    	if (revArray != null) {
    		try{
    	          if (revArray[5] == (byte) 0x89 && revArray[6] == (byte) 0x00){
    	        	   return true;
    	          }
    		}catch(Exception e){
    			return false;
    		}
          }
        return false;
    }

    @UnsupportedAppUsage
    public boolean loadETC(byte[] EtcData){
    	byte[] revArray = SendCommand((byte) 0x8a, (byte) 0x00, EtcData);
    	if (revArray != null) {
    		try{
    	          if (revArray[5] == (byte) 0x8a && revArray[6] == (byte) 0x00){
    	        	   return true;
    	          }
    		}catch(Exception e){
    			return false;
    		}
          }
        return false;
    }

    @UnsupportedAppUsage
    public boolean loadInitializeKey(byte[] initializeKeyData){
    	byte[] revArray = SendCommand((byte) 0x8b, (byte) 0x00, initializeKeyData);
    	if (revArray != null) {
    		try{
    	          if (revArray[5] == (byte) 0x8b && revArray[6] == (byte) 0x00){
    	        	   return true;
    	          }
    		}catch(Exception e){
    			return false;
    		}
          }
        return false;
    }

    @UnsupportedAppUsage
    public boolean loadKSN(byte[] serialNumber){
    	byte[] revArray = SendCommand((byte) 0x8c, (byte) 0x00, serialNumber);
    	if (revArray != null) {
    		try{
    	          if (revArray[5] == (byte) 0x8c && revArray[6] == (byte) 0x00){
    	        	   return true;
    	          }
    		}catch(Exception e){
    			return false;
    		}
          }
        return false;
    }
   [20170607 Anthony.bae End]
   */

    /**
     * This function open msr service.
     *
     * @param APDU command byte array
     *
     * @return the APDU return value from the module.
     *
     * @see device.sdk.MsrManager#SendAPDU
     */
    @UnsupportedAppUsage
    public byte[] SendAPDU(byte[] Data)
    {
        byte[] revArray = SendCommand((byte)0x83,(byte) 0x00, Data);

        if(revArray != null)
        {
        	try{
                if(revArray[5]== (byte)0x83 && revArray[6]== (byte)0x00)
                {
                    byte[] ReusultArray = new byte[revArray.length - 9];
                    for(int i=0; i< ReusultArray.length; i++)
                        ReusultArray[i] = revArray[7+i];
                    return 	ReusultArray;
                }
        	}catch(Exception e){
        		return null;
        	}
        }
        return null;
    }

    /**
     * This function open msr service.
     *
     * @param callback Result return function.
     *
     * @return msr service open status.
     *
     * @see device.sdk.MsrManager#DeviceMsrClose
     */
    @UnsupportedAppUsage
	public int DeviceMsrOpen(MsrResultCallback callback) {
		int ret = 0;
		MsrResultCallbackWrapper wrapper = new MsrResultCallbackWrapper(callback);
		try {
            //ADD [20160704 Anthony.bae #10669]
            if(DeviceServer.getIDeviceService().getMsrType() == DevInfoIndex.MSR_PM1100 ){
                AudioSystem.setMasterVolume(0.5f);
            }
			ret = DeviceServer.getIMsrService().msr_Open(wrapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

    /**
    * This function close msr service.
    *
    * @return msr service close status.
    *
    * @see device.sdk.MsrManager#DeviceMsrOpen
    */
    @UnsupportedAppUsage
	public int DeviceMsrClose() {
		int ret = 0;
		try {
            //ADD [20160704 Anthony.bae #10669]
            if(DeviceServer.getIDeviceService().getMsrType() == DevInfoIndex.MSR_PM1100 ){
                AudioSystem.setMasterVolume(1.0f);
            }
			ret = DeviceServer.getIMsrService().msr_Close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

    /**
    * This function read start msr service.
    *
    * @return msr service start status.
    *
    * @see device.sdk.MsrManager#DeviceMsrStopRead
    */
    @UnsupportedAppUsage
	public int DeviceMsrStartRead() {
		int ret = 0;
		try {
            //START[20160620 Anthony.bae #10718]
            if(mMsrModule == MSR_Module.PM1100){
        	  SendCommand((byte)0x85,(byte)0x00, new byte[]{},(byte)0x31);
            }
            //END[20160620 Anthony.bae #10718]

			ret = DeviceServer.getIMsrService().msr_StartRead();
		} catch (Exception e) {
			e.printStackTrace();
        }
		return ret;
	}

///<BEGIN>[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata
    /**
     * This function read start msr service.
     *
     * @param mode Read data mode setting. Use for PM1100 only
     *  MMD1000 (NT)
     *  PM1100 mode
     *  0  = default
     *  11 = MsrReady 0x01 | MSRBinary 0x10
     *  13 = MsrReady 0x01 | ICRReady 0x02 |MSRBinary 0x10
     *
     * @return msr service start status.
     *
     * @see device.sdk.MsrManager#DeviceMsrStopRead
     */
    @UnsupportedAppUsage
    public int DeviceMsrStartRead(int mode) {

        int ret = 0;
        try {
//            Log.e(TAG, "mMsrModule ::"+mMsrModule);
            if(mMsrModule == MSR_Module.PM1100){
                byte[] txmode =  new byte[]{};
                if(mode == 11){
                    txmode = new byte[]{(byte)0x11};
                }else if(mode == 13){
                    txmode = new byte[]{(byte)0x13};
                }

                SendCommand((byte)0x85,(byte)0x00, txmode,(byte)0x31);
            }
//            else if(mMsrModule == MSR_Module.MMD1000){
//                int deMode = getTxMode();
//                if(deMode != 2||deMode != 4||deMode != 6){
//                    deMode = 0;
//                }
//                if(mode != deMode){  //OTP Write Max 3!!! setTxMode
//                    setTxMode(mode,0,0);
//                }else{}
//            }
            ret = DeviceServer.getIMsrService().msr_StartRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
///< END >[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata

    private int SecretDeviceMsrStartRead() {
		int ret = 0;
		try {
			ret = DeviceServer.getIMsrService().msr_StartRead();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

    /**
    * This function read stop msr service.
    *
    * @return msr service stop status.
    *
    * @see device.sdk.MsrManager#DeviceMsrStartRead
    */
    @UnsupportedAppUsage
	public int DeviceMsrStopRead() {
		int ret = 0;
		try {
			ret = DeviceServer.getIMsrService().msr_StopRead();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

    /**
    * This function update the firmware, Use for PM1100 Only.
    *
    * @param fwFile firmware file.
    * @return true if success, false otherwise.
    */
    @UnsupportedAppUsage
    public boolean updateFirmware(File fwFile) throws IOException, FileNotFoundException {
        try {
            if (DeviceServer.getIDeviceService().getMsrType() != DevInfoIndex.MSR_PM1100) {
                Log.e(TAG, "This is not supported");
                return false;
            }

            if (fwFile == null || !fwFile.exists()) {
                Log.e(TAG, "MSR Firmware update:: file is not exists");
                return false;
            }

            if (!DeviceServer.getIMsrService().msr_startFirmwareUpdate()) {
                Log.e(TAG, "MSR Firmware update:: start fail!");
                return false;
            }

            int percent = 0;
            int transferred = 0;
            long fileSize = fwFile.length();

            FileInputStream fis = new FileInputStream(fwFile);
            int i = 0;
            byte[] buffer = new byte[4096];
            while ((i = fis.read(buffer, 0, 4096)) >= 0) {
                boolean result = DeviceServer.getIMsrService().msr_sendFirmwareData(buffer, i);
                if (!result) {
                    Log.e(TAG, "MSR Firmware update:: send data fail!");
                    break;
                }
            }
            fis.close();

            if (!DeviceServer.getIMsrService().msr_endFirmwareUpdate()) {
                Log.e(TAG, "MSR Firmware update:: end fail!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
    * This function get the firmware version of the MSR.
    *
    * @return firmware version of the MSR.
    */
    @UnsupportedAppUsage
    public String getFirmwareVersion() {
        String firmware = "";
        try {
            firmware = DeviceServer.getIMsrService().getFirmwareVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firmware;
    }

    /**
    * This function return msr result in msr service.
    *
    * @param read_track select reading track data.
    * <pre>
    *   track 1 (1 << 0).
    *   track 2 (1 << 1).
    *   track 3 (1 << 2).
    * </pre>
    *
    * @return MsrResult class value.
    */
    @UnsupportedAppUsage
	public MsrResult DeviceMsrGetData(int read_track)
	{
		MsrResult result = null;
		try {
			result = DeviceServer.getIMsrService().msr_GetData(read_track);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

    /**
    * This function set the public key in order to encrypt (RSA/ECB).
    * The second track is encrypted, if you complete the setting.
    *
    * @param pemKey pem key with String.
    *
    * @return true if the value was set, false otherwise.
    *
    * @see device.sdk.MsrManager#setPublicKey(File)
    */
    @UnsupportedAppUsage
    public boolean setPublicKey(String pemKey)  {
        try {
            return DeviceServer.getIMsrService().setPublicKey(pemKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
    * This function set the public key in order to encrypt (RSA/ECB).
    * The second track is encrypted, if you complete the setting.
    *
    * @param pemFile File of pem key.
    *
    * @return true if the value was set, false otherwise.
    *
    * @see device.sdk.MsrManager#setPublicKey(String)
    */
    @UnsupportedAppUsage
    public boolean setPublicKey(File pemFile) throws IOException {
        final int MAX_LENGTH = 256 * 1024;
        FileInputStream fis = new FileInputStream(pemFile);
        int length = fis.available();
        String pemKey = null;
        if (length <= MAX_LENGTH) {
            byte[] bytes = new byte[length];
            fis.read(bytes);
            pemKey = new String(bytes, "UTF-8");
            if (pemKey != null && !pemKey.isEmpty()) {
                pemKey = pemKey.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
            }
        }
        fis.close();
        if (pemKey != null && !pemKey.isEmpty()) {
            return setPublicKey(pemKey);
        }
        return false;
    }

    /**
     * This function set to use encryption of this unit itself, Use for MMD1000 Only.
     * Warning: This setting can only be used twice per unit.
     *
     * @param ksn key serial number to use encryption.
     * @param initKey initial key to use encryption.
     *
     * @return true if setting to use encryption is success, false otherwise.
     *
     * @see device.sdk.MsrManager#getEncryptionData
     */
    @UnsupportedAppUsage
    public boolean setUsedEncryption(byte ksn[], byte initKey[]) {
        try {
            if (DeviceServer.getIDeviceService().getMsrType() == DevInfoIndex.MSR_MMD1000) {
                return DeviceServer.getIMsrService().msr_UseEncryption(ksn, initKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This function get the encryption data, Use for MMD1000 Only.
     * You must first set {@link device.sdk.MsrManager#setUsedEncryption}
     *
     * @return encryption data with byte array.
     *
     * @see device.sdk.MsrManager#setUsedEncryption
     */
    @UnsupportedAppUsage
    public byte[] getEncryptionData() {
        try {
            if (DeviceServer.getIDeviceService().getMsrType() == DevInfoIndex.MSR_MMD1000) {
                return DeviceServer.getIMsrService().msr_getEncryptionData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	private static class MsrResultCallbackWrapper extends IMsrResultCallback.Stub {
		private MsrResultCallback mCallback;
        @UnsupportedAppUsage
		public MsrResultCallbackWrapper(MsrResultCallback callback) {
			mCallback = callback;
		}

        @Override
        @UnsupportedAppUsage
		public void onResult(int cmd, int status) {
			Log.v(TAG,"Msrmanager  onResult () ");
		}

		///<BEGIN>[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata
		@Override
        @UnsupportedAppUsage
		public void onResultData(int cmd, int status, byte[] data, byte[] track1Buf, byte[] track2Buf, byte[] track3Buf ) {
            mCallback.onResult(cmd, status);
			mCallback.onResultData(cmd, status, data);
            mCallback.onResultTrackData(cmd, status, track1Buf, track2Buf, track3Buf);
		}
		///< END >[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata

	}

    // [START][EMKIT][anthony.bae]  - 2017.10.27
    /**
     * This function get a status that RDi
     *
     * @return Whether it operates in RDI mode.
     *
     * @see device.sdk.MsrManager#rdiIsEnabled
     */
    @UnsupportedAppUsage
    public int rdiIsEnabled() {
        try {
             return DeviceServer.getIMsrService().msr_rdi_is_enabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * This function set a status that Rdi
     *
     * @return The success or failure of the function.
     *
     * @see device.sdk.MsrManager#rdiSetEnable
     */
    @UnsupportedAppUsage
    public int rdiSetEnable(int enable) {
        try {
             return DeviceServer.getIMsrService().msr_rdi_enable(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * This function write a command in Ring buffer
     *
     * @return The success or failure of the function.
     *
     * @see device.sdk.MsrManager#rdiWrite
     */
    @UnsupportedAppUsage
    public int rdiWrite(byte [] byteArrays, int length) {
        try {
             return DeviceServer.getIMsrService().msr_rdi_write(byteArrays, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * This function read a command in Ring buffer
     *
     * @return The length of the byte array read.
     *
     * @see device.sdk.MsrManager#rdiRead
     */
    @UnsupportedAppUsage
    public int rdiRead(byte[] data, int length) {
        try {
            return DeviceServer.getIMsrService().msr_rdi_read(data, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * This function returns the number of data accumulated in the ring buffer.
     *
     * @return Number of data.
     *
     * @see device.sdk.MsrManager#rdiNelem
     */
    @UnsupportedAppUsage
    public int rdiNelem() {
        try {
             return DeviceServer.getIMsrService().msr_rdi_nelem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * This function clear the RDI Ring buffer
     *
     * @see device.sdk.MsrManager#rdiClear
     */
    @UnsupportedAppUsage
    public int rdiClear() {
        try {
             return DeviceServer.getIMsrService().msr_rdi_clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

///<BEGIN>[RDI][olivia.gyeong]  - 2018.02.12  check system privileges for rdi pmpos app
    /**
     * This function powers the msr for RDI.
     *
     * @return The success or failure of the function.
     *
     * @see device.sdk.MsrManager#rdiOpen
     */
	// [START][RDI][olivia.gyeong]  - 2018.02.12  check system privileges for rdi pmpos app
    @UnsupportedAppUsage
    public int rdiOpenK() {
    	if(!checkSystemUid()){
		     return -3;
		}
	    try {
             return DeviceServer.getIMsrService().msr_rdi_open();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }
	// [END][RDI][olivia.gyeong]  - 2018.02.12  check system privileges for rdi pmpos app

    @UnsupportedAppUsage
    public int rdiOpen() {
        try {
            return DeviceServer.getIMsrService().msr_rdi_open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * This function turns off the msr for RDI.
     *
     * @return The success or failure of the function.
     *
     * @see device.sdk.MsrManager#rdiClose
     */
    @UnsupportedAppUsage
    public int rdiClose() {
        try {
             return DeviceServer.getIMsrService().msr_rdi_close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    // [ END ][EMKIT][anthony.bae]  - 2017.10.27

    // [START][RDI][olivia.gyeong]  - 2018.02.12  check system privileges for rdi pmpos app
    @UnsupportedAppUsage
    public boolean  checkSystemUid(){
        int uid = Binder.getCallingUid();
        boolean systemUidFlag = false;
        if(uid != Process.SYSTEM_UID){
            systemUidFlag= false;
            throw new SecurityException(" SecurityException ");

        }else{
            systemUidFlag = true;
        }
        return systemUidFlag;
    }
    // [END][RDI][olivia.gyeong]  - 2018.02.12  check system privileges for rdi pmpos app

	///<BEGIN>[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata
    /**
     * This function set read lowdata mode. Use for MMD1000 only.
     * @param txmode, allTerror, ksnRes
     * TX Mode
     *  2 = binary lowdata
     *  4 = encrypted without initial vector use
     *  6 = encrypted by using initial vector
     * All Track Error
     *  0 = all track error report : Enable  - defualt
     *  1 = all track error report : Disable
     * KSN
     *  0 = 10byte key sirial number         - defualt
     *  1 = 3 byte(21bit encryption count)
     *
     * @return The success or failure of the function.
     *
     * @see device.sdk.MsrManager#rdiClose
     */
    @UnsupportedAppUsage
    public int setTxMode(int txmode, int allTerror, int ksnRes) {
        try {
            return DeviceServer.getIMsrService().setTxMode(txmode, allTerror, ksnRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    /**
     * This function get read lowdata mode. Use for MMD1000 only.
     *
     * @return The success or failure of the function.
     * TX Mode
     *  2 = binary lowdata
     *  4 = encrypted without initial vector use
     *  6 = encrypted by using initial vector
     * @see device.sdk.MsrManager#rdiClose
     */
    @UnsupportedAppUsage
    public int getTxMode() {
        try {
            return DeviceServer.getIMsrService().getTxMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
   ///< END >[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata

    /**
     * This function reset msr module.
     *
     * @return The success or failure of the function.
     *
     */
    @UnsupportedAppUsage
    public int DeviceMsrReset(int on) {
        int ret = 0;
        try {
            ret = DeviceServer.getIMsrService().msr_reset(on);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * This function powers the msr for RDI with DLE-ACK timeout.
     *
     * @return The success or failure of the function.
     *
     * @see device.sdk.MsrManager#rdiOpen
     */
    @UnsupportedAppUsage
    public int rdiOpenKWithTimeout(int timeout) {
        if(!checkSystemUid()){
            return -3;
        }
        try {
            return DeviceServer.getIMsrService().msr_rdi_open_with_timeout(timeout);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * This function powers the msr for RDI with DLE-ACK timeout.
     *
     * @return The success or failure of the function.
     *
     * @see device.sdk.MsrManager#rdiOpen
     */
    @UnsupportedAppUsage
    public int rdiOpenWithTimeout(int timeout) {
        try {
            return DeviceServer.getIMsrService().msr_rdi_open_with_timeout(timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}

