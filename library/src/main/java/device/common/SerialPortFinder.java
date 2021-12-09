package device.common;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.ServiceManager;
import android.os.RemoteException;

public class SerialPortFinder {
    private ISerialService mService;

    @UnsupportedAppUsage
    public SerialPortFinder() {
        mService = ISerialService.Stub.asInterface(ServiceManager.getService("SerialService"));
    }

    /**
     * Get device name / driver name of ports.
     * <pre>
     * exmpale : ttyHSL0 (msm_serial_hs)
     * </pre>
     * 
     * @return String array of device name / driver name.
     */
    @UnsupportedAppUsage
    public String[] getAllDevices() {
        try {
            return mService.getSerialPortAllDevices();
        } catch (RemoteException e) {
        }
        return null;
    }

    /**
     * Get device path of ports.
     * <pre>
     * example : /dev/ttyHSL0
     * </pre>
     *
     * @return String array of device path.
     */
    @UnsupportedAppUsage
    public String[] getAllDevicesPath() {
        try {
            return mService.getSerialPortAllDevicesPath();
        } catch (RemoteException e) {
        }
        return null;
    }
}
