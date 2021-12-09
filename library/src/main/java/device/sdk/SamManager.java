package device.sdk;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.RemoteException;

import device.sdk.DeviceServer;

public class SamManager {
    private static final String TAG = SamManager.class.getSimpleName();
    private static SamManager mInstance = null;

    @UnsupportedAppUsage
    public static SamManager getInstance() {
        if (mInstance == null) {
            mInstance = new SamManager();
        }
        return mInstance;
    }
    @UnsupportedAppUsage
    public SamManager() {}

    /**
     * Set the enabled state of SAM. Control the power and COM port of SAM.
     *
     * @param enabled True if power on and COM port open of SAM, false otherwise.
     *
     */
    @UnsupportedAppUsage
    public void setEnabled(boolean enabled) {
        try {
            DeviceServer.getISamService().samSetPowerOn(enabled);
            if (enabled) {
                DeviceServer.getISamService().samOpenPort();
            } else {
                DeviceServer.getISamService().samClosePort();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the power status for SAM.
     *
     * @return True if power of SAM is turned on. false otherwise.
     */
    @UnsupportedAppUsage
    public boolean isEnabled() {
        boolean ret = false;
        try {
            ret = DeviceServer.getISamService().samGetPowerStatus();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Send the ATR command through the COM port. The card power up and answer to reset.
     * To activate the card at a VCC of 5V
     *
     * @return Error code.
     *
     * @see device.sdk.SamManager#sendApduCommand
     * @see device.sdk.SamManager#sendPowerDownCommand
     */
    @UnsupportedAppUsage
    public int sendAtrCommand()	{
        int ret = 0;
        try {
            ret = DeviceServer.getISamService().sam_CommandAtr();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * if sucess send ATR command API,
     * you can get response data from CARD
     *
     * @param req_buf  enought buffer size to get ATR responce
     * @return datalength  reponse data length
     */
    @UnsupportedAppUsage
    public int GetAtrResponse(byte req_buf[]) {
        int ret = 0;
        try {
            ret = DeviceServer.getISamService().sam_get_atr_response(req_buf);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Send the APDU command through the COM port.
     * Be used to transmit card commands under APDU format.
     *
     * @return Error code.
     *
     * @see device.sdk.SamManager#sendAtrCommand
     * @see device.sdk.SamManager#sendPowerDownCommand
     */
    @UnsupportedAppUsage
    public int sendApduCommand(byte[] apdu, int apduLength, byte[] response, int responseLength) {
        int ret = 0;
        try {
            ret = DeviceServer.getISamService().sam_CommandApdu(apdu, apduLength, response, responseLength);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Send the PowerDown command through the COM port.
     * Be used to deactivate the card whatever it has been activated for 3V or 5V operation.
     *
     * @return Error code.
     *
     * @see device.sdk.SamManager#sendAtrCommand
     * @see device.sdk.SamManager#sendApduCommand
     */
    @UnsupportedAppUsage
    public int sendPowerDownCommand() {
        int ret = 0;
        try {
            ret = DeviceServer.getISamService().sam_CommandPowerdown();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Send the Direct command through the COM port.
     *
     * @param  cmd  TDA command.
     * @param  pdata  data buffer . if do not need data,this set by null;
     * @param  datalen  pdata size. if pdata is null,this param must set 0.
     * @param  pRESP  respones buffer. this is enought to get response)
     * @param  pRespLen pRESP buffer size
     * @return ret & 0xFF = SamIndex. ret >> 0x8 = read length.(If SamIndex is TDA8029_OK)
     *
     */
    @UnsupportedAppUsage
    public int sendDirectCommand(byte cmd,byte[] pdata, int datalen, byte[] pRESP, int pRespLen) {
        int ret = 0;
        try {
            ret = DeviceServer.getISamService().sam_direct_command(cmd ,pdata,  datalen, pRESP, pRespLen);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Get Samservice time out delay(ms)
     * - this feature only support android 5.x,6.x
     * @return current sam service timeout delay(ms)
     */
    @UnsupportedAppUsage
    public int getTimeoutDelay(){
        int ret =0;
        try {
            ret = DeviceServer.getISamService().get_timeout_delay();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     *Set Samservice time out delay(ms)
     * - this feature only support android 5.x,6.x
     * @param delay time out delay(ms)
     *
     **/
    @UnsupportedAppUsage
    public void setTimeoutDelay(int delay){
        try {
            DeviceServer.getISamService().set_timeout_delay(delay);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * To swtich SAM slot (0/1)
     *  - This feature supports only PM85
     * @param  slot number (0 or 1)
     *
     **/
    @UnsupportedAppUsage
    public int set_sam_slot(int slot){
        int ret =0;
        try {
            ret= DeviceServer.getISamService().set_sam_slot(slot);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * To get current switched SAM slot
     *  - This feature supports only PM85
     * @return current slot number (0 or 1) if -1 is wrong value
     *
     **/
    @UnsupportedAppUsage
    public int get_sam_slot(){
        int ret =0;
        try {
            ret = DeviceServer.getISamService().get_sam_slot();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * This function power on sam device.
     *
     * @param on Sam power on or off.
     *
     * @return sam device power control status.
     *
     * @see device.sdk.SamManager#DeviceSamGetPowerStatus
     *
     * @deprecated Use {@link #setEnabled(boolean)} instead
     */
    @Deprecated
    @UnsupportedAppUsage
    public int DeviceSamSetPowerOn(boolean on)
    {
        int ret = 0;
        try {
            ret = DeviceServer.getISamService().samSetPowerOn(on);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * This function power status at sam device.
     *
     * @return sam device power on or off status.
     *
     * @see device.sdk.SamManager#DeviceSamSetPowerOn
     *
     * @deprecated Use {@link #isEnabled()} instead
     */
    @Deprecated
    @UnsupportedAppUsage
    public boolean DeviceSamGetPowerStatus()
    {
        boolean ret = false;
        try {
            ret = DeviceServer.getISamService().samGetPowerStatus();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * This function is to open com port in sam service.
     *
     * @return Com port status at sam device.
     *
     * @see device.sdk.SamManager#DeviceSamCloseFort
     *
     * @deprecated Use {@link #setEnabled(boolean)} instead
     */
    @Deprecated
    @UnsupportedAppUsage
    public int DeviceSamOpenFort() {
        int ret = 0;
        try {
            ret = DeviceServer.getISamService().samOpenPort();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }


    /**
     * This function is to close com port in sam service.
     *
     * @return Com port status at sam device.
     *
     * @see device.sdk.SamManager#DeviceSamOpenFort
     *
     * @deprecated Use {@link #setEnabled(boolean)} instead
     */
    @Deprecated
    @UnsupportedAppUsage
    public int DeviceSamCloseFort() {
        int ret = 0;
        try {
            ret = DeviceServer.getISamService().samClosePort();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * This function is internal sam power on and  com port in sam service.
     *
     * @return ret SamIndex.
     *
     * @see device.sdk.SamManager#DeviceSam_CommandApdu
     * @see device.sdk.SamManager#DeviceSam_CommandPowerdown
     *
     * @deprecated Use {@link #sendAtrCommand()} instead
     */
    @Deprecated
    @UnsupportedAppUsage
    public int DeviceSamCommandAtr()
    {
        int ret = 0;
        try {
            ret = DeviceServer.getISamService().sam_CommandAtr();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * This function is internal sam power on and  com port in sam service.
     *
     * @param pAPDU Sam apdu send data buffer.
     * @param apduLen Sam apdu send data buffer length.
     * @param pRESP Sam apdu response data buffer.
     * @param respLen Sam apdu response data buffer size.
     *
     * @return ret & 0xFF = SamIndex.
     *         ret >> 0x8 = read length.(If SamIndex is TDA8029_OK)
     *
     * @see device.sdk.SamManager#DeviceSamCommandAtr
     * @see device.sdk.SamManager#DeviceSam_CommandApdu
     *
     * @deprecated Use {@link #sendApduCommand(byte[], int, byte[], int)} instead
     */
    @Deprecated
    @UnsupportedAppUsage
    public int DeviceSam_CommandApdu(byte pAPDU[], int apduLen, byte pRESP[], int respLen)
    {
        int ret = 0;
        try {
            ret = DeviceServer.getISamService().sam_CommandApdu(pAPDU, apduLen, pRESP, respLen);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * This function is internal sam power on and  com port in sam service.
     *
     * @return ret SamIndex.
     *
     * @see device.sdk.SamManager#DeviceSamCommandAtr
     * @see device.sdk.SamManager#DeviceSam_CommandPowerdown
     *
     * @deprecated Use {@link #sendPowerDownCommand()} instead
     */
    @Deprecated
    @UnsupportedAppUsage
    public int DeviceSam_CommandPowerdown()
    {
        int ret = 0;
        try {
            ret = DeviceServer.getISamService().sam_CommandPowerdown();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }
}

