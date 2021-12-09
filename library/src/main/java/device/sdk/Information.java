package device.sdk;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.RemoteException;

import device.common.DevInfoIndex;
import device.sdk.DeviceServer;

public class Information {
    private static final String TAG = Information.class.getSimpleName();
    private static Information mInstance = null;

    @UnsupportedAppUsage
    public static Information getInstance() {
        if (mInstance == null) {
            mInstance = new Information();
        }
        return mInstance;
    }
    @UnsupportedAppUsage
    public Information() {}

    /***********************
     * Version Information *
     ***********************
     */

    /**
     * This function reads hardware revision.
     *
     * @return hardware revision with String
     *
     * @see  device.sdk.Information#getAndroidVersion
     * @see  device.sdk.Information#getKernelVersion
     */
    @UnsupportedAppUsage
    public String getHardwareRevision() throws RemoteException {
        return DeviceServer.getIDeviceService().getHardwareRevision();
    }

    /**
     * This function reads Android version.
     *
     * @return Android version with String
     *
     * @see  device.sdk.Information#getHardwareRevision
     * @see  device.sdk.Information#getKernelVersion
     */
    @UnsupportedAppUsage
    public String getAndroidVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getAndroidVersion();
    }

    /**
     * This function reads linux kernel version.
     *
     * @return linux kernel version with String
     *
     * @see  device.sdk.Information#getHardwareRevision
     * @see  device.sdk.Information#getAndroidVersion
     */
    @UnsupportedAppUsage
    public String getKernelVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getKernelVersion();
    }

    /**********************
     * System Information *
     **********************
     */

    /**
     * This function reads build number.
     *
     * @return build number with String
     */
    @UnsupportedAppUsage
    public String getBuildNumber() throws RemoteException {
        return DeviceServer.getIDeviceService().getBuildNumber();
    }

    /**
     * This function reads SDK version.
     *
     * @return SDK version with String.
     */
    @UnsupportedAppUsage
    public String getSDKVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getSDKVersion();
    }

    /**
     * This function reads manufacturer name.
     *
     * @return manufacturer name with String
     */
    @UnsupportedAppUsage
    public String getManufacturer() throws RemoteException {
        return DeviceServer.getIDeviceService().getManufacturer();
    }

    /**
     * This function reads model name.
     *
     * @return model name with String
     */
    @UnsupportedAppUsage
    public String getModelName() throws RemoteException {
        return DeviceServer.getIDeviceService().getModelName();
    }

    /**
     * This function reads processor information.
     *
     * @return processor information with String
     */
    @UnsupportedAppUsage
    public String getProcessorInfo() throws RemoteException {
        return DeviceServer.getIDeviceService().getProcessorInfo();
    }

    /**
     * This function reads serial number.
     *
     * @return serial number with String
     */
    @UnsupportedAppUsage
    public String getSerialNumber() throws RemoteException {
        return DeviceServer.getIDeviceService().getSerialNumber();
    }

    /**
     * This function reads part number.
     *
     * @return part number with String
     */
    @UnsupportedAppUsage
    public String getPartNumber() throws RemoteException {
        return DeviceServer.getIDeviceService().getPartNumber();
    }

    /**
     * @deprecated
     */
    @Deprecated
    @UnsupportedAppUsage
    public String getModelNumber() throws RemoteException {
        return DeviceServer.getIDeviceService().getModelNumber();
    }

    /**
     * This function reads manufacture date.
     *
     * @return manufacture date with String
     */
    @UnsupportedAppUsage
    public String getManufactureDate() throws RemoteException {
        return DeviceServer.getIDeviceService().getManufactureDate();
    }

    /******************************
     * Imager Version Information *
     ******************************
     */

    private static String convertVersionFormat(byte[] version) {
        String months[] = { "", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

        byte[] buffer = new byte[24];

        byte[] majorBytes;
        byte[] minorBytes;

        byte month;
        byte[] monthBytes;
        byte[] dayBytes;
        byte[] yearBytes;

        int i = 0, j = 0;

        majorBytes = String.format("%02d", version[j++]).getBytes();
        j++;
        minorBytes = String.format("%02d", version[j++]).getBytes();
        j++;

        buffer[i++] = majorBytes[0]; // 8
        buffer[i++] = majorBytes[1]; // 8
        buffer[i++] = '.';
        buffer[i++] = minorBytes[0]; // 0
        buffer[i++] = minorBytes[1]; // 0
        buffer[i++] = ' ';

        if (version[j] != 0) { // it has distribution
            buffer[i++] = version[j++]; // L
            buffer[i++] = version[j++]; // B
            buffer[i++] = ' ';
        } else {
            j = j + 2;
        }

        buffer[i++] = '(';

        yearBytes = String.format("%04d", ((int) (version[j++] & 0xFF)) + (((int) (version[j++] & 0xFF)) << 8)).getBytes();
        month = version[j++];
        dayBytes = String.format("%02d", version[++j]).getBytes();

        if ((1 <= month) && (month <= 12)) {
            monthBytes = months[month].getBytes();
        } else {
            monthBytes = "XXX".getBytes();
        }

        buffer[i++] = monthBytes[0]; // O
        buffer[i++] = monthBytes[1]; // c
        buffer[i++] = monthBytes[2]; // t
        buffer[i++] = ' ';

        buffer[i++] = dayBytes[0]; // 2
        buffer[i++] = dayBytes[1]; // 0
        buffer[i++] = ' ';

        buffer[i++] = yearBytes[0]; // 2
        buffer[i++] = yearBytes[1]; // 0
        buffer[i++] = yearBytes[2]; // 1
        buffer[i++] = yearBytes[3]; // 4
        buffer[i++] = ')';
        buffer[i++] = 0;

        return new String(buffer); // return "88.00 (Oct 20 2014)" or "88.00 B1 (Oct 01 2014)"
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public String getXloaderImageVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getXloaderImageVersion();
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public String getBootloaderImageVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getBootloaderImageVersion();
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public String getKernelImageVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getKernelImageVersion();
    }

    /** @deprecated Don't supported since Android 5.0.2. */
	@Deprecated
    @UnsupportedAppUsage
    public String getRecoveryImageVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getRecoveryImageVersion();
    }

    /**
     * This function reads ANDROID image version.
     *
     * @return ANDROID image version with String
     */
    @UnsupportedAppUsage
    public String getAndroidImageVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getAndroidImageVersion();
    }

    /**
     * This function reads major number of device.
     *
     * @return Major number with int
     */
    @UnsupportedAppUsage
    public int getMajorNumber() throws RemoteException {
        return DeviceServer.getIDeviceService().getMajorNumber();
    }

    /*********************
     * Device Infomation *
     *********************
     */

    /**
     * This function reads device name.
     *
     * @return Device name with String
     */
    @UnsupportedAppUsage
    public String getDeviceName() throws RemoteException {
        return DeviceServer.getIDeviceService().getDeviceName();
    }

	/**
	 * This function reads module name by device id.
	 *
     * @param index of device type with integer
	 *
	 * @return Module name with String
	 */
    @UnsupportedAppUsage
	public String getModuleName(int index) throws RemoteException {
        return DeviceServer.getIDeviceService().getModuleName(index);
	}

    /**
     * This function reads camera type.
     *
     * @return camera type with int; Zero indicates none.
     */
    @UnsupportedAppUsage
    public int getCameraType() throws RemoteException {
        return DeviceServer.getIDeviceService().getCameraType();
    }

    /**
     * This function reads camera second type.
     *
     * @return camera type with int; Zero indicates none.
     */
    @UnsupportedAppUsage
    public int getCameraSecondType() throws RemoteException {
        return DeviceServer.getIDeviceService().getCameraSecondType();
    }

    /**
     * This function reads display type.
     *
     * @return display type with int; Zero indicates none.
     * <pre>
     * public static final int DISPLAY_VGA_TRULY  = 103;
     * public static final int DISPLAY_WVGA_TRULY = 105;
     * public static final int DISPLAY_FWVGA_TRULY = 107;
     * public static final int DISPLAY_FWVGA_TRULY = 107;
     * public static final int DISPLAY_HD_TNHD5040 = 108;
     * public static final int DISPLAY_TYPE_BYD    = 109;
     * public static final int DISPLAY_HX8394A0C   = 110;
     * public static final int DISPLAY_HX8379C     = 111;
     * public static final int DISPLAY_ILI9881D    = 112;
     * public static final int DISPLAY_HX8369B     = 113;
     * public static final int DISPLAY_HX8369A     = 114;
     * public static final int DISPLAY_FT8716U     = 115;
     * public static final int DISPLAY_HOB050H1018 = 116;
     * public static final int DISPLAY_HX8394F     = 118;
     * public static final int DISPLAY_ST7703      = 119;
     * public static final int DISPLAY_ST7701S     = 120;
     * public static final int DISPLAY_OTM8019A    = 121;
     * public static final int DISPLAY_HOT055H876  = 122;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getDisplayType() throws RemoteException {
        return DeviceServer.getIDeviceService().getDisplayType();
    }

    /**
     * This function reads keyboard type.
     *
     * @return keyboard type with int; Zero indicates none.
     * <pre>
     * public static final int KEYBOARD_NUMERIC            = 19;
	 * public static final int KEYBOARD_ALPHANUMERIC       = 20;
	 * public static final int KEYBOARD_QWERTY             = 21;
	 * public static final int KEYBOARD_NAVIGATION         = 22;
	 * public static final int KEYBOARD_PM251              = 34;
	 * public static final int KEYBOARD_NUMERIC_PM40       = 39;
	 * public static final int KEYBOARD_QWERTY_PM40        = 40;
     * public static final int KEYBOARD_NUMERIC_PM60       = 41;
     * public static final int KEYBOARD_QWERTY_PM60        = 42;
     * public static final int KEYBOARD_NUMERIC_MUTANT     = 43;
     * public static final int KEYBOARD_QWERTY_MUTANT      = 44;
     * public static final int KEYBOARD_NUMERIC_OCTANT     = 45;
     * public static final int KEYBOARD_NUMERIC_PM450      = 46;
     * public static final int KEYBOARD_ALPHANUMERIC_PM450 = 47;
     * public static final int KEYBOARD_NAVIGATION_SEXTANT = 48;
     * public static final int KEYBOARD_NUMERIC_XM5        = 49;
     * public static final int KEYBOARD_QWERTY_XM5         = 50;
     * public static final int KEYBOARD_NAVIGATION_PM80    = 51;
     * public static final int KEYBOARD_NAVIGATION_PM70    = 52;
     * public static final int KEYBOARD_NUMERIC_PM66       = 53;
     * public static final int KEYBOARD_NUMERIC_PM550      = 54;
     * public static final int KEYBOARD_NAVIGATION_PM45    = 55;
     * public static final int KEYBOARD_NAVIGATION_PM85    = 58;
     * public static final int KEYBOARD_NAVIGATION_XT200   = 59;
     * public static final int KEYBOARD_NAVIGATION_PM90    = 60;
     * public static final int KEYBOARD_FUNCNUMERIC_PM451  = 63;
     * public static final int KEYBOARD_ALPHANUMERIC_PM451 = 64;
     * public static final int KEYBOARD_NUMERIC_PM451      = 65;
     * public static final int KEYBOARD_ALDINUMERIC_PM451  = 66;
     * public static final int KEYBOARD_NAVIGATION_PM500   = 67;
     * public static final int KEYBOARD_NAVIGATION_PM30    = 68;
	 * public static final int KEYBOARD_NAVIGATION_PM75    = 69;
	 * public static final int KEYBOARD_NUMERIC_PM67       = 70;
	 * public static final int KEYBOARD_NUMERIC_PM351      = 71;
	 * public static final int KEYBOARD_NUMERIC_DTX450     = 72;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getKeyboardType() throws RemoteException {
        return DeviceServer.getIDeviceService().getKeyboardType();
    }

    /**
     * This function reads NAND type.
     *
     * @return NAND type with int; Zero indicates none.
     * <pre>
     * public static final int NAND_EMMC            = 0x0001;
     * public static final int NAND_K9F1G08         = 0x4B39;
     * public static final int NAND_K9F2G08         = 0x4B41;
     * public static final int NAND_NAND01GR3B2B    = 0x4E13;
     * public static final int NAND_NAND02GR3B2C    = 0x4E23;
     * public static final int NAND_NAND01GR4B2B    = 0x4E14;
     * public static final int NAND_NAND02GR4B2C    = 0x4E24;
     * public static final int NAND_NAND04GW3B2D    = 0x4E43;
     * public static final int NAND_TOSHIBA2GX16    = 0x5424;
     * public static final int NAND_TC58NYG0S3EBAI4 = 0x5413;
     * public static final int NAND_TC58NYG1S3EBAI5 = 0x5423;
     * public static final int NAND_TC58NYG2S3EBAI5 = 0x5443;
     * public static final int NAND_TC58NVG2S3EBAI5 = 0x5433;
     * public static final int NAND_MT29F4G16       = 0x4D49;
     * public static final int NAND_MT29F8G16       = 0x4D4A;
     * public static final int NAND_MICRON          = NAND_MT29F4G16;
     * public static final int NAND_KMQ7X000SA_B315 = 0x0002;
     * public static final int NAND_KMQ72000SM_B316 = 0x0003;
     * public static final int NAND_MT29TZZZ5D6YKFAH_125_W96N = 0x0004;
	 * public static final int NAND_TYPE_KMQ820013M_B419 = 0x05;
	 * public static final int NAND_TYPE_H9TQ17ABJTACUR_KUM = 0x06;
     * public static final int NAND_KMFNX0012M_B214 = 0x07;
     * public static final int NAND_MT29TZZZ5D6EKFRL_107_W96R = 0x08;
     * public static final int NAND_MT29TZZZ5D6DKFRL_107_W9A6 = 0x09;
     * public static final int NAND_TYPE_H9TQ17ABJTBCUR_KUM = 0x0A;
     * public static final int NAND_TYPE_H9TQ17ABJTCCUR_KUM = 0x0B;
     * public static final int NAND_TYPE_H9TQ26ADFTBCUR_KUM = 0x0C;
     * public static final int NAND_TYPE_KMQE60013M_B318 = 0x0D;
     * public static final int NAND_TYPE_KMRX60014M_B614 = 0x0E;
     * public static final int NAND_TYPE_KMGX6001BM_B514 = 0x0F;
     * public static final int NAND_TYPE_KMQE10013M_B318 = 0x10;
     * public static final int NAND_TYPE_KMGD6001BM_B421 = 0x11;
     * public static final int NAND_TYPE_KMRH60014A_B614 = 0x12;
     * public static final int NAND_TYPE_KMDH6001DA_B422 = 0x13;
     * public static final int NAND_TYPE_H9HP52ACPMMDAR_KMM = 0x14;
     * public static final int NAND_TYPE_KMRP60014M_B614 = 0x15;
     * public static final int NAND_TYPE_KMGX6001BA_B514 = 0x16;
     * public static final int NAND_TYPE_MT29TZZZAD8DKKFB_107_W9N8 = 0x17;
     * public static final int NAND_TYPE_KMDX60018M_B425 = 0x18;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getNandType() throws RemoteException {
        return DeviceServer.getIDeviceService().getNandType();
    }

    /**
     * This function reads barcode scanner type.
     *
     * @return barcode scanner type with int; Zero indicates none.
     * <pre>
     * public static final int SCANNER_IT4000  = 5;
     * public static final int SCANNER_IT4100  = 6;
     * public static final int SCANNER_IT4300  = 7;
     * public static final int SCANNER_IT5100  = 8;
     * public static final int SCANNER_IT5300  = 9;
     * public static final int SCANNER_N5603   = 12;
     * public static final int SCANNER_N5600   = 13;
     * public static final int SCANNER_IS4813  = 14;
     * public static final int SCANNER_N4313   = 16;
     * public static final int SCANNER_N6603   = 17;
     * public static final int SCANNER_EX25    = 18;
     * public static final int SCANNER_EX30    = 19;
     * public static final int SCANNER_SE955   = 20;
     * public static final int SCANNER_SE4500  = 21;
     * public static final int SCANNER_SE655   = 22;
     * public static final int SCANNER_SE965   = 23;
     * public static final int SCANNER_SE4710  = 24;
     * public static final int SCANNER_UE966   = 25;
     * public static final int SCANNER_CR8000  = 26;
     * public static final int SCANNER_SE4750  = 27;
     * public static final int SCANNER_MDL1500 = 28;
     * public static final int SCANNER_N6703   = 29;
     * public static final int SCANNER_N3601   = 31;
     * public static final int SCANNER_N3603   = 32;
     * public static final int SCANNER_N2600   = 33;
     * public static final int SCANNER_N4603   = 34;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getScannerType() throws RemoteException {
        return DeviceServer.getIDeviceService().getScannerType();
    }

    /**
     * This function reads touch device type.
     *
     * @return touch device type with int; Zero indicates none.
     * <pre>
     * public static final int TOUCH_REGISTIVE         = 1;
     * public static final int TOUCH_CAPACITIVE_S3202  = 2;
     * public static final int TOUCH_CAPACITIVE_S3508  = 3;
     * public static final int TOUCH_CAPACITIVE_ILITEK = 4;
     * public static final int TOUCH_CAPACITIVE_S3528  = 5;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getTouchType() throws RemoteException {
        return DeviceServer.getIDeviceService().getTouchType();
    }

    /**
     * This function reads RFID device type.
     *
     * @return RFID device type with int; Zero indicates none.
     * <pre>
     * public static final int RFID_MIPS_MTR_R900    = 49;
     * public static final int RFID_MINERVA          = 50;
     * public static final int RFID_ARCONTIA_ARC1300 = 51;
     * public static final int RFID_NFC_PM663        = 52;
     * public static final int RFID_NFC_PN547        = 53;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getRfidType() throws RemoteException {
        return DeviceServer.getIDeviceService().getRfidType();
    }

    /**
     * This function reads UHFRFID device type.
     *
     * @return UHFRFID device type with int; Zero indicates none.
     * <pre>
     * public static final int RFID_KCTM2000 = 1;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getUhfRfidType() throws RemoteException {
        return DeviceServer.getIDeviceService().getUhfRfidType();
    }

    /**
     * This function reads bluetooth type.
     *
     * @return bluetooth type with int; Zero indicates none.
     * <pre>
     * public static final int BLUETOOTH_CSRBLUECORE4ROM  = 3;
     * public static final int BLUETOOTH_TI_1273L         = 5;
     * public static final int BLUETOOTH_QC_WCN3660B      = 6;
     * public static final int BLUETOOTH_QC_WCN3680B      = 7;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getBluetoothType() throws RemoteException {
        return DeviceServer.getIDeviceService().getBluetoothType();
    }

    /**
     * This function reads GPS type.
     *
     * @return GPS type with int; Zero indicates none.
     * <pre>
     * public static final int GPS_HC25        = 1;
     * public static final int GPS_PH8         = 2;
     * public static final int GPS_UBLOX7      = 3;
     * public static final int GPS_OEM615      = 4;
     * public static final int GPS_QC_WCN3660B = 5;
     * public static final int GPS_UBLOX7P     = 6;
     * public static final int GPS_QC_WCN3680B = 7;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getGpsType() throws RemoteException {
        return DeviceServer.getIDeviceService().getGpsType();
    }

    /**
     * This function reads phone module type.
     *
     * @return phone module type with int; Zero indicates none.
     * <pre>
     * public static final int PHONE_HC25    = 1;
     * public static final int PHONE_PH8     = 2;
     * public static final int PHONE_WTR4905 = 3;
     * public static final int PHONE_WTR3925 = 4;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getPhoneType() throws RemoteException {
        return DeviceServer.getIDeviceService().getPhoneType();
    }

    /**
     * This function reads WiFi module type.
     *
     * @return WiFi module type with int; Zero indicates none.
     * <pre>
     * public static final int WIFI_USI_SD8686_SDIO = 3;
     * public static final int WIFI_TI_1273L        = 7;
     * public static final int WIFI_SUMMIT          = 20;
     * public static final int WIFI_QC_WCN3660B     = 21;
     * public static final int WIFI_QC_WCN3680B     = 22;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getWifiType() throws RemoteException {
        return DeviceServer.getIDeviceService().getWifiType();
    }

    /**
     * This function reads accelerometer type.
     *
     * @return accelerometer type with int; Zero indicates none.
     * <pre>
     * public static final int SENSOR_AK8963C  = 1;
     * public static final int SENSOR_BMI055   = 2;
     * public static final int SENSOR_APDS9900 = 3;
     * public static final int SENSOR_BMP180   = 4;
     * public static final int SENSOR_TMP102   = 5;
     * public static final int SENSOR_APDS9930 = 6;
     * public static final int SENSOR_BMA250   = 7;
     * public static final int SENSOR_BMA223   = 8;
     * public static final int SENSOR_STK3311  = 9;
     * public static final int SENSOR_BMI160   = 10;
     * public static final int SENSOR_LSM6DS3  = 11;
     * public static final int SENSOR_ST480    = 12;
     * public static final int SENSOR_AK09918  = 13;
     * public static final int SENSOR_BMA253   = 14;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getSensorAccelerometerType() throws RemoteException {
        return DeviceServer.getIDeviceService().getSensorAccelerometerType();
    }

    /**
     * This function reads ambient light sensor type.
     *
     * @return ambient light sensor type with int; Zero indicates none.
     * <pre>
     * public static final int SENSOR_AK8963C  = 1;
     * public static final int SENSOR_BMI055   = 2;
     * public static final int SENSOR_APDS9900 = 3;
     * public static final int SENSOR_BMP180   = 4;
     * public static final int SENSOR_TMP102   = 5;
     * public static final int SENSOR_APDS9930 = 6;
     * public static final int SENSOR_BMA250   = 7;
     * public static final int SENSOR_BMA223   = 8;
     * public static final int SENSOR_STK3311  = 9;
     * public static final int SENSOR_BMI160   = 10;
     * public static final int SENSOR_LSM6DS3  = 11;
     * public static final int SENSOR_ST480    = 12;
     * public static final int SENSOR_AK09918  = 13;
     * public static final int SENSOR_BMA253   = 14;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getSensorLightType() throws RemoteException {
        return DeviceServer.getIDeviceService().getSensorLightType();
    }

    /**
     * This function reads proximity sensor type.
     *
     * @return proximity sensor type with int; Zero indicates none.
     * <pre>
     * public static final int SENSOR_AK8963C  = 1;
     * public static final int SENSOR_BMI055   = 2;
     * public static final int SENSOR_APDS9900 = 3;
     * public static final int SENSOR_BMP180   = 4;
     * public static final int SENSOR_TMP102   = 5;
     * public static final int SENSOR_APDS9930 = 6;
     * public static final int SENSOR_BMA250   = 7;
     * public static final int SENSOR_BMA223   = 8;
     * public static final int SENSOR_STK3311  = 9;
     * public static final int SENSOR_BMI160   = 10;
     * public static final int SENSOR_LSM6DS3  = 11;
     * public static final int SENSOR_ST480    = 12;
     * public static final int SENSOR_AK09918  = 13;
     * public static final int SENSOR_BMA253   = 14;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getSensorProximityType() throws RemoteException {
        return DeviceServer.getIDeviceService().getSensorProximityType();
    }

    /**
     * Not Supported
     */
    @UnsupportedAppUsage
    public int getSensorCpuTemperatureType() throws RemoteException {
        return DeviceServer.getIDeviceService().getSensorCpuTemperatureType();
    }

    /**
     * This function reads gyroscope sensor type.
     *
     * @return gyroscope sensor type with int; Zero indicates none.
     * <pre>
     * public static final int SENSOR_AK8963C  = 1;
     * public static final int SENSOR_BMI055   = 2;
     * public static final int SENSOR_APDS9900 = 3;
     * public static final int SENSOR_BMP180   = 4;
     * public static final int SENSOR_TMP102   = 5;
     * public static final int SENSOR_APDS9930 = 6;
     * public static final int SENSOR_BMA250   = 7;
     * public static final int SENSOR_BMA223   = 8;
     * public static final int SENSOR_STK3311  = 9;
     * public static final int SENSOR_BMI160   = 10;
     * public static final int SENSOR_LSM6DS3  = 11;
     * public static final int SENSOR_ST480    = 12;
     * public static final int SENSOR_AK09918  = 13;
     * public static final int SENSOR_BMA253   = 14;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getSensorGyroscopeType() throws RemoteException {
        return DeviceServer.getIDeviceService().getSensorGyroscopeType();
    }

    /**
     * This function reads magnetic field sensor type.
     *
     * @return magnetic field sensor type with int; Zero indicates none.
     * <pre>
     * public static final int SENSOR_AK8963C  = 1;
     * public static final int SENSOR_BMI055   = 2;
     * public static final int SENSOR_APDS9900 = 3;
     * public static final int SENSOR_BMP180   = 4;
     * public static final int SENSOR_TMP102   = 5;
     * public static final int SENSOR_APDS9930 = 6;
     * public static final int SENSOR_BMA250   = 7;
     * public static final int SENSOR_BMA223   = 8;
     * public static final int SENSOR_STK3311  = 9;
     * public static final int SENSOR_BMI160   = 10;
     * public static final int SENSOR_LSM6DS3  = 11;
     * public static final int SENSOR_ST480    = 12;
     * public static final int SENSOR_AK09918  = 13;
     * public static final int SENSOR_BMA253   = 14;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getSensorMagneticFieldType() throws RemoteException {
        return DeviceServer.getIDeviceService().getSensorMagneticFieldType();
    }

    /**
     * This function reads pressure sensor type.
     *
     * @return pressure sensor type with int; Zero indicates none.
     * <pre>
     * public static final int SENSOR_AK8963C  = 1;
     * public static final int SENSOR_BMI055   = 2;
     * public static final int SENSOR_APDS9900 = 3;
     * public static final int SENSOR_BMP180   = 4;
     * public static final int SENSOR_TMP102   = 5;
     * public static final int SENSOR_APDS9930 = 6;
     * public static final int SENSOR_BMA250   = 7;
     * public static final int SENSOR_BMA223   = 8;
     * public static final int SENSOR_STK3311  = 9;
     * public static final int SENSOR_BMI160   = 10;
     * public static final int SENSOR_LSM6DS3  = 11;
     * public static final int SENSOR_ST480    = 12;
     * public static final int SENSOR_AK09918  = 13;
     * public static final int SENSOR_BMA253   = 14;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getSensorPressureType() throws RemoteException {
        return DeviceServer.getIDeviceService().getSensorPressureType();
    }

    /**
     * This function reads MSR module type.
     *
     * @return MSR module type with int; Zero indicates none.
     * <pre>
     * public static final int MSR_MMD1000  = 1;
     * public static final int MSR_PM1100   = 2;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getMsrType() throws RemoteException {
        return DeviceServer.getIDeviceService().getMsrType();
    }

    /**
     * This function reads FINGERPRINT module type.
     *
     * @return FINGERPRINT module type with int; Zero indicates none.
     * <pre>
     * public static final int FINGERPRINT_FPC1020  = 1;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getFingerprintType() throws RemoteException {
        return DeviceServer.getIDeviceService().getFingerprintType();
    }

    /**
     * This function reads ICCR module type.
     *
     * @return ICCR module type with int; Zero indicates none.
     * <pre>
     * public static final int ICCR_CUNI360S = 1;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getIccrType() throws RemoteException {
        return DeviceServer.getIDeviceService().getIccrType();
    }

    /**
     * This function reads SAM module type.
     *
     * @return SAM module type with int; Zero indicates none.
     * <pre>
     * public static final int SAM_CUNI360S = 1;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getSamType() throws RemoteException {
        return DeviceServer.getIDeviceService().getSamType();
    }

    /**
     * This function reads FISCAL module type.
     *
     * @return FISCAL module type with int; Zero indicates none.
     * <pre>
     * public static final int FISCAL_CHD = 1;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getFiscalType() throws RemoteException {
        return DeviceServer.getIDeviceService().getFiscalType();
    }

    /**
     * @deprecated
     */
    @Deprecated
    @UnsupportedAppUsage
    public int getBluetoothStatus() throws RemoteException {
        return DeviceServer.getIDeviceService().getBluetoothStatus();
    }

    /**
     * @deprecated
     */
    @Deprecated
    @UnsupportedAppUsage
    public int getGpsStatus() throws RemoteException {
        return DeviceServer.getIDeviceService().getGpsStatus();
    }

    /**
     * @deprecated
     */
    @Deprecated
    @UnsupportedAppUsage
    public int getPhoneStatus() throws RemoteException {
        return DeviceServer.getIDeviceService().getPhoneStatus();
    }

    /**
     * @deprecated
     */
    @Deprecated
    @UnsupportedAppUsage
    public int getWifiStatus() throws RemoteException {
        return DeviceServer.getIDeviceService().getWifiStatus();
    }

    /**
     * Not Supported
     */
    @UnsupportedAppUsage
    public String getTouchFirmwareVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getTouchFirmwareVersion();
    }

    /**
     * This function camera module firmware version
     *
     * @parameter camera Module String,  ex) "HI1332".
     *
     * @return camera module firmware version string.
     */
    @UnsupportedAppUsage
    public String getCameraFirmwareVersion(String cameraModule) throws RemoteException {
        return DeviceServer.getIDeviceService().getCameraFirmwareVersion(cameraModule);
    }

    /**
     * Not Supported
     */
    @UnsupportedAppUsage
    public String getRfidFirmwareVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getRfidFirmwareVersion();
    }

    /**
     * Not Supported
     */
    @UnsupportedAppUsage
    public String getRfidSerialNumber() throws RemoteException {
        return DeviceServer.getIDeviceService().getRfidSerialNumber();
    }

    /**
     * Not Supported
     */
    @UnsupportedAppUsage
    public String getRfidSamInfo(int index) throws RemoteException {
        return DeviceServer.getIDeviceService().getRfidSamInfo(index);
    }

    /**
     * Not Supported
     */
    @UnsupportedAppUsage
    public String getBluetoothDriverVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getBluetoothDriverVersion();
    }

    /**
     * This function reads bluetooth MAC address.
     *
     * @return bluetooth MAC address with String
     */
    @UnsupportedAppUsage
    public String getBluetoothMacAddress() throws RemoteException {
        return DeviceServer.getIDeviceService().getBluetoothMacAddress();
    }

    /**
     * This function reads WiFi driver version.
     *
     * @return WiFi driver version with String
     */
    @UnsupportedAppUsage
    public String getWifiDriverVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getWifiDriverVersion();
    }

    /**
     * This function reads WiFi firmware version.
     *
     * @return WiFi firmware version with String
     */
    @UnsupportedAppUsage
    public String getWifiFirmwareVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getWifiFirmwareVersion();
    }

    /**
     * This function reads WiFi configuration version
     *
     * @return WiFi configuration version
     */
    @UnsupportedAppUsage
    public String getWifiConfigurationDataVersion() throws RemoteException {
        return DeviceServer.getIDeviceService().getWifiConfigurationDataVersion();
    }

    /**
     * This function reads WiFi MAC address.
     *
     * @return WiFi MAC address with String
     */
    @UnsupportedAppUsage
    public String getWifiMacAddress() throws RemoteException {
        return DeviceServer.getIDeviceService().getWifiMacAddress();
    }

    /**
     * This function reads WiFi IP address.
     *
     * @return WiFi IP address with String
     */
    @UnsupportedAppUsage
    public String getWifiIpAddress() throws RemoteException {
        return DeviceServer.getIDeviceService().getWifiIpAddress();
    }

	/***********************
     * Battery Information *
     ***********************
     */

    /**
     * This function reads main battery charging status.
     *
     * @return main battery charging status with String
     */
    @UnsupportedAppUsage
    public String getMainBatteryStatus() throws RemoteException {
        return DeviceServer.getIDeviceService().getMainBatteryStatus();
    }

    /**
     * This function reads backup battery charging status.
     *
     * @return backup battery charging status with String
     */
    @UnsupportedAppUsage
    public String getBackupBatteryStatus() throws RemoteException {
        return DeviceServer.getIDeviceService().getBackupBatteryStatus();
    }

    /**
     * @deprecated
     */
    @Deprecated
    @UnsupportedAppUsage
    public String getBatterySerialNumber() throws RemoteException {
        return DeviceServer.getIDeviceService().getBatterySerialNumber();
    }

    /**
     * This function reads 'ChargingMainBatteryFromUsb' flag.
     *
     * @return 'ChargingMainBatteryFromUsb' flag with String: "Enabled" or "Disabled"
     */
    @UnsupportedAppUsage
    public String getChargingMainBatteryFromUsbFlag() throws RemoteException {
        return DeviceServer.getIDeviceService().getChargingMainBatteryFromUsbFlag();
    }

    /**
     * This function reads 'ChargingBackupBatteryFromMainBattery' flag.
     *
     * @return 'ChargingBackupBatteryFromMainBattery' flag with String: "enabled" or "disabled"
     */
    @UnsupportedAppUsage
    public String getChargingBackupBatteryFromMainBatteryFlag() throws RemoteException {
        return DeviceServer.getIDeviceService().getChargingBackupBatteryFromMainBatteryFlag();
    }

    /**
     * This function reads low battery warning level.
     *
     * @return low battery warning level with String
     */
    @UnsupportedAppUsage
    public String getLowBatteryWarningLevel() throws RemoteException {
        return DeviceServer.getIDeviceService().getLowBatteryWarningLevel();
    }

    /**
     * This function reads critical battery warning level.
     *
     * @return critical battery warning level with String
     */
    @UnsupportedAppUsage
    public String getCriticalBatteryWarningLevel() throws RemoteException {
        return DeviceServer.getIDeviceService().getCriticalBatteryWarningLevel();
    }

    /**
     * This function reads barcode scanner class.
     *
     * @param scanner scanner type with int
     * <pre>
     * public static final int SCANNER_IT4000  = 5;
     * public static final int SCANNER_IT4100  = 6;
     * public static final int SCANNER_IT4300  = 7;
     * public static final int SCANNER_IT5100  = 8;
     * public static final int SCANNER_IT5300  = 9;
     * public static final int SCANNER_N5603   = 12;
     * public static final int SCANNER_N5600   = 13;
     * public static final int SCANNER_IS4813  = 14;
     * public static final int SCANNER_N4313   = 16;
     * public static final int SCANNER_N6603   = 17;
     * public static final int SCANNER_EX25    = 18;
     * public static final int SCANNER_EX30    = 19;
     * public static final int SCANNER_SE955   = 20;
     * public static final int SCANNER_SE4500  = 21;
     * public static final int SCANNER_SE655   = 22;
     * public static final int SCANNER_SE965   = 23;
     * public static final int SCANNER_SE4710  = 24;
     * public static final int SCANNER_UE966   = 25;
     * public static final int SCANNER_CR8000  = 26;
     * public static final int SCANNER_SE4750  = 27;
     * public static final int SCANNER_MDL1500 = 28;
     * public static final int SCANNER_N6703   = 29;
     * public static final int SCANNER_N3601   = 31;
     * public static final int SCANNER_N3603   = 32;
     * public static final int SCANNER_N2600   = 33;
     * public static final int SCANNER_N4603   = 34;
     * </pre>
     *
     * @return barcode scanner class with int; Zero indicates none or unknown.
     * <pre>
     * public static final int SCANNER_CLASS_LASER  = 1;
     * public static final int SCANNER_CLASS_IMAGER = 2;
     * </pre>
     */
    @UnsupportedAppUsage
    public int getScannerClass(int scanner) throws RemoteException {
        return DeviceServer.getIDeviceService().getScannerClass(scanner);
    }

    /**
     * This function reads barcode scanner name.
     *
     * @param scanner scanner type with int
     * <pre>
     * public static final int SCANNER_IT4000  = 5;
     * public static final int SCANNER_IT4100  = 6;
     * public static final int SCANNER_IT4300  = 7;
     * public static final int SCANNER_IT5100  = 8;
     * public static final int SCANNER_IT5300  = 9;
     * public static final int SCANNER_N5603   = 12;
     * public static final int SCANNER_N5600   = 13;
     * public static final int SCANNER_IS4813  = 14;
     * public static final int SCANNER_N4313   = 16;
     * public static final int SCANNER_N6603   = 17;
     * public static final int SCANNER_EX25    = 18;
     * public static final int SCANNER_EX30    = 19;
     * public static final int SCANNER_SE955   = 20;
     * public static final int SCANNER_SE4500  = 21;
     * public static final int SCANNER_SE655   = 22;
     * public static final int SCANNER_SE965   = 23;
     * public static final int SCANNER_SE4710  = 24;
     * public static final int SCANNER_UE966   = 25;
     * public static final int SCANNER_CR8000  = 26;
     * public static final int SCANNER_SE4750  = 27;
     * public static final int SCANNER_MDL1500 = 28;
     * public static final int SCANNER_N6703   = 29;
     * public static final int SCANNER_N3601   = 31;
     * public static final int SCANNER_N3603   = 32;
     * public static final int SCANNER_N2600   = 33;
     * public static final int SCANNER_N4603   = 34;
     * </pre>
     *
     * @return barcode scanner name with String; "Unknown" for unknown scanner type
     */
    @UnsupportedAppUsage
    public String getScannerName(int scanner) throws RemoteException {
        return DeviceServer.getIDeviceService().getScannerName(scanner);
    }

    /**
     * This function reads barcode scanner class name.
     *
     * @param scanner scanner type with int
     * <pre>
     * public static final int SCANNER_IT4000  = 5;
     * public static final int SCANNER_IT4100  = 6;
     * public static final int SCANNER_IT4300  = 7;
     * public static final int SCANNER_IT5100  = 8;
     * public static final int SCANNER_IT5300  = 9;
     * public static final int SCANNER_N5603   = 12;
     * public static final int SCANNER_N5600   = 13;
     * public static final int SCANNER_IS4813  = 14;
     * public static final int SCANNER_N4313   = 16;
     * public static final int SCANNER_N6603   = 17;
     * public static final int SCANNER_EX25    = 18;
     * public static final int SCANNER_EX30    = 19;
     * public static final int SCANNER_SE955   = 20;
     * public static final int SCANNER_SE4500  = 21;
     * public static final int SCANNER_SE655   = 22;
     * public static final int SCANNER_SE965   = 23;
     * public static final int SCANNER_SE4710  = 24;
     * public static final int SCANNER_UE966   = 25;
     * public static final int SCANNER_CR8000  = 26;
     * public static final int SCANNER_SE4750  = 27;
     * public static final int SCANNER_MDL1500 = 28;
     * public static final int SCANNER_N6703   = 29;
     * public static final int SCANNER_N3601   = 31;
     * public static final int SCANNER_N3603   = 32;
     * public static final int SCANNER_N2600   = 33;
     * public static final int SCANNER_N4603   = 34;
     * </pre>
     *
     * @return barcode scanner class name with String; "Unknown" for unknown scanner type
     */
    @UnsupportedAppUsage
    public String getScannerClassName(int scanner) throws RemoteException {
        return DeviceServer.getIDeviceService().getScannerClassName(scanner);
    }

    /**
     * This method checks if the function of each model is supported.
     */
    @UnsupportedAppUsage
    public boolean isSupported(String name) throws RemoteException {
        return DeviceServer.getIDeviceService().isSupported(name);
    }

    /**
     * This method gets constants defined for each model.
     */
    @UnsupportedAppUsage
    public String getDefines(String name) throws RemoteException {
        return DeviceServer.getIDeviceService().getDefines(name);
    }

    /**
     * This method gets the prebuilt system resource ID for each model.
     */
    @UnsupportedAppUsage
    public int getSysResId(String name) throws RemoteException {
        return DeviceServer.getIDeviceService().getSysResId(name);
    }

    /**
     * This method checks if the feature of each model is supported.
     */
    @UnsupportedAppUsage
    public boolean hasFeature(String name) throws RemoteException {
        return DeviceServer.getIDeviceService().hasFeature(name);
    }
}
