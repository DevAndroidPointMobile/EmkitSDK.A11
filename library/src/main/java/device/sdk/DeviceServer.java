package device.sdk;

import android.content.Context;
import android.os.ServiceManager;

import device.common.ISerialService;
import device.common.IScannerService;
import device.common.IDeviceService;
import device.common.IHiJackService;
import device.common.IMsrService;
import device.common.ISamService;
import device.common.II2CService;
import device.common.ISPIService;
import device.common.IExGpioService;
import device.common.rfid.IRFIDService;

public class DeviceServer {
    /** @hide */ private static final String TAG = DeviceServer.class.getSimpleName();
    /** @hide */ private static IScannerService sScannerService;
    /** @hide */ private static ISerialService sSerialService;
    /** @hide */ private static IDeviceService sDeviceService;
    /** @hide */ private static IHiJackService sHiJackService;
    /** @hide */ private static IMsrService sMsrService;
    /** @hide */ private static ISamService sSamService;
    /** @hide */ private static II2CService sI2CService;
    /** @hide */ private static ISPIService sSPIService;
    /** @hide */ private static IExGpioService sExGpioService;
    /** @hide */ private static IRFIDService sRFIDService;
    /** @hide getIScannerService */
    public static IScannerService getIScannerService() {
        if (sScannerService == null) {
            sScannerService = IScannerService.Stub.asInterface(ServiceManager.getService("ScannerService"));
        }
        return sScannerService;
    }
    /** @hide getISerialService */
    public static ISerialService getISerialService() {
        if (sSerialService == null) {
            sSerialService = ISerialService.Stub.asInterface(ServiceManager.getService("SerialService"));
        }
        return sSerialService;
    }
    /** @hide getIDeviceService */
    public static IDeviceService getIDeviceService() {
        if (sDeviceService == null) {
            sDeviceService = IDeviceService.Stub.asInterface(ServiceManager.getService("DeviceService"));
        }
        return sDeviceService;
    }
    /** @hide getIHiJackService */
    public static IHiJackService getIHiJackService() {
        if (sHiJackService == null) {
            sHiJackService = IHiJackService.Stub.asInterface(ServiceManager.getService("HiJackService"));
        }
        return sHiJackService;
    }
    /** @hide getIMsrService */
    public static IMsrService getIMsrService() {
        if (sMsrService == null) {
            sMsrService = IMsrService.Stub.asInterface(ServiceManager.getService("MsrService"));
        }
        return sMsrService;
    }
    /** @hide getISamService */
    public static ISamService getISamService() {
        if (sSamService == null) {
            sSamService = ISamService.Stub.asInterface(ServiceManager.getService("SamService"));
        }
        return sSamService;
    }
    /** @hide getII2CService */
    public static II2CService getII2CService() {
        if (sI2CService == null) {
            sI2CService = II2CService.Stub.asInterface(ServiceManager.getService("I2CService"));
        }
        return sI2CService;
    }
    /** @hide getISPIService */
    public static ISPIService getISPIService() {
        if (sSPIService == null) {
            sSPIService = ISPIService.Stub.asInterface(ServiceManager.getService("SPIService"));
        }
        return sSPIService;
    }
    /** @hide getIExGpioService */
    public static IExGpioService getIExGpioService() {
        if (sExGpioService == null) {
            sExGpioService = IExGpioService.Stub.asInterface(ServiceManager.getService("ExGpioService"));
        }
        return sExGpioService;
    }
    /** @hide getIRFIDService */
    public static IRFIDService getIRFIDService() {
        if (sRFIDService == null) {
            sRFIDService = IRFIDService.Stub.asInterface(ServiceManager.getService("RFIDService"));
        }
        return sRFIDService;
    }
}
