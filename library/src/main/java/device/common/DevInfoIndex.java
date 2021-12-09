package device.common;

import android.compat.annotation.UnsupportedAppUsage;

public class DevInfoIndex {
    /* Device Name */
    @UnsupportedAppUsage
    public static final int OCTANT_MAJOR    = 88;
    @UnsupportedAppUsage
    public static final int PM80_MAJOR      = 80;
    @UnsupportedAppUsage
    public static final int CHD8_MAJOR      = 82;
    @UnsupportedAppUsage
    public static final int XT2_MAJOR       = 87;
    @UnsupportedAppUsage
    public static final int CR4900_MAJOR    = 89;
    @UnsupportedAppUsage
    public static final int PM70_MAJOR      = 70;
    @UnsupportedAppUsage
    public static final int XT2P_MAJOR      = 71;
    @UnsupportedAppUsage
    public static final int PM66_MAJOR      = 76;
    @UnsupportedAppUsage
    public static final int PM80P_MAJOR     = 81;
    @UnsupportedAppUsage
    public static final int XG200_MAJOR     = 54;
    @UnsupportedAppUsage
    public static final int PM550_MAJOR     = 55;
    @UnsupportedAppUsage
    public static final int PM550R_MAJOR    = 56;
    @UnsupportedAppUsage
    public static final int PM45_MAJOR      = 45;
    @UnsupportedAppUsage
    public static final int DTX400_MAJOR    = 40;
    @UnsupportedAppUsage
    public static final int PM85_MAJOR      = 85;
    @UnsupportedAppUsage
    public static final int XT200_MAJOR     = 84;
    @UnsupportedAppUsage
    public static final int PM90_MAJOR      = 90;
    @UnsupportedAppUsage
    public static final int XT3_MAJOR       = 91;
    @UnsupportedAppUsage
    public static final int ITG600_MAJOR    = 61;
    @UnsupportedAppUsage
    public static final int ITG650_MAJOR    = 66;
    @UnsupportedAppUsage
    public static final int PM451_MAJOR     = 41;
    @UnsupportedAppUsage
    public static final int XG4_MAJOR       = 42;
    @UnsupportedAppUsage
    public static final int PM500_MAJOR     = 50;
    @UnsupportedAppUsage
    public static final int PM30_MAJOR      = 30;
    @UnsupportedAppUsage
    public static final int PM75_MAJOR      = 75;
    @UnsupportedAppUsage
    public static final int PM67_MAJOR      = 67;
    @UnsupportedAppUsage
    public static final int XM75P_MAJOR     = 68;
    @UnsupportedAppUsage
    public static final int PM351_MAJOR     = 35;
    @UnsupportedAppUsage
    public static final int DTX450_MAJOR    = 46;

    public static enum DEVICE_NAME {
        @UnsupportedAppUsage
        octant(OCTANT_MAJOR),
        @UnsupportedAppUsage
        sextant(OCTANT_MAJOR),
        @UnsupportedAppUsage
        pm80(PM80_MAJOR),
        @UnsupportedAppUsage
        cr4900(CR4900_MAJOR),
        @UnsupportedAppUsage
        pm70(PM70_MAJOR),
        @UnsupportedAppUsage
        pm66(PM66_MAJOR),
        @UnsupportedAppUsage
        pm80plus(PM80P_MAJOR),
        @UnsupportedAppUsage
        pm550(PM550_MAJOR),
        @UnsupportedAppUsage
        xg200(XG200_MAJOR),
        @UnsupportedAppUsage
        pm45(PM45_MAJOR),
        @UnsupportedAppUsage
        dtx400(DTX400_MAJOR),
        @UnsupportedAppUsage
        pm85(PM85_MAJOR),
        @UnsupportedAppUsage
        xt200(XT200_MAJOR),
        @UnsupportedAppUsage
        pm90(PM90_MAJOR),
        @UnsupportedAppUsage
        xt3(XT3_MAJOR),
        @UnsupportedAppUsage
        itg600(ITG600_MAJOR),
        @UnsupportedAppUsage
        itg650(ITG650_MAJOR),
        @UnsupportedAppUsage
        pm451(PM451_MAJOR),
        @UnsupportedAppUsage
        xg4(XG4_MAJOR),
        @UnsupportedAppUsage
        pm500(PM500_MAJOR),
        @UnsupportedAppUsage
        pm30(PM30_MAJOR),
        @UnsupportedAppUsage
        pm75(PM75_MAJOR),
        @UnsupportedAppUsage
        pm67(PM67_MAJOR),
        @UnsupportedAppUsage
        xm75plus(XM75P_MAJOR),
        @UnsupportedAppUsage
        pm351(PM351_MAJOR),
        @UnsupportedAppUsage
        dtx450(DTX450_MAJOR);
        private int mMajorNumber;
        @UnsupportedAppUsage
        public int getMajorNumber() {return mMajorNumber;}
        @UnsupportedAppUsage
        DEVICE_NAME(int majorNumber) {mMajorNumber=majorNumber;}
    }

	/* Global */
    @UnsupportedAppUsage
	public static final int NONE                    = 0;
    @UnsupportedAppUsage
    public static final int UNKNOWN                 = -1;
    @UnsupportedAppUsage
    public static final int DEPRECATED              = -1;
    @UnsupportedAppUsage
	public static final int NOT_SUPPORTED           = -1;
    @UnsupportedAppUsage
    public static final int ON                      = 1;
    @UnsupportedAppUsage
    public static final int OFF                     = 0;
    @UnsupportedAppUsage
    public static final int ENABLED                 = 1;
    @UnsupportedAppUsage
    public static final int DISABLED                = 0;
    @UnsupportedAppUsage
    public static final String STRING_NOT_SUPPORTED = "Not Supported";
    @UnsupportedAppUsage
    public static final String STRING_DEPRECATED    = "Deprecated";
    @UnsupportedAppUsage
    public static final String STRING_UNKNOWN       = "Unknown";
    @UnsupportedAppUsage
    public static final String STRING_UNIMPLEMENTED = "Unimplemented";

    /* Device Type */
    @UnsupportedAppUsage
    public static final int DEVICE_KEYBOARD             = 0;
    @UnsupportedAppUsage
    public static final int DEVICE_TOUCH                = 1;
    @UnsupportedAppUsage
    public static final int DEVICE_DISPLAY              = 2;
    @UnsupportedAppUsage
    public static final int DEVICE_BLUETOOTH            = 3;
    @UnsupportedAppUsage
    public static final int DEVICE_WLAN                 = 4;
    @UnsupportedAppUsage
    public static final int DEVICE_WWAN                 = 5;
    @UnsupportedAppUsage
    public static final int DEVICE_GPS                  = 6;
    @UnsupportedAppUsage
    public static final int DEVICE_NAND                 = 7;
    @UnsupportedAppUsage
    public static final int DEVICE_CAMERA               = 8;
    @UnsupportedAppUsage
    public static final int DEVICE_SCANNER              = 9;
    @UnsupportedAppUsage
    public static final int DEVICE_RFID                 = 10;
    @UnsupportedAppUsage
    public static final int DEVICE_ACCELEROMETER        = 11;
    @UnsupportedAppUsage
    public static final int DEVICE_AMBIENT_LIGHT        = 12;
    @UnsupportedAppUsage
    public static final int DEVICE_AMBIENT_TEMPERATURE  = 13;
    @UnsupportedAppUsage
    public static final int DEVICE_GYROSCOPE            = 14;
    @UnsupportedAppUsage
    public static final int DEVICE_MAGNETIC_FIELD       = 15;
    @UnsupportedAppUsage
    public static final int DEVICE_PRESSURE             = 16;
    @UnsupportedAppUsage
    public static final int DEVICE_PROXIMITY            = 17;
    @UnsupportedAppUsage
    public static final int DEVICE_CPU_TEMPERATURE      = 18;
    @UnsupportedAppUsage
    public static final int DEVICE_FLASH_LED            = 19;
    @UnsupportedAppUsage
    public static final int DEVICE_CAMERA_SECOND        = 20;
    @UnsupportedAppUsage
    public static final int DEVICE_MSR                  = 21;
    @UnsupportedAppUsage
    public static final int DEVICE_FINGERPRINT          = 22;
    @UnsupportedAppUsage
    public static final int DEVICE_COUNT                = 23;
    @UnsupportedAppUsage
    public static final int DEVICE_MAX_COUNT            = 40;

    /* Keyboard Type */
    @UnsupportedAppUsage
	public static final int KEYBOARD_D6100_28           = 16; // (D6100) Numeric-key keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_D6500_28           = 17; // (D6500) Numberic 28-key keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_D6500_52           = 18; // (D6500) Alpha-numeric 52-key keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_NUMERIC            = 19; // Numberic keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_ALPHANUMERIC       = 20; // Alpha-Numeric keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_QWERTY             = 21; // Qwerty keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_NAVIGATION         = 22; // Navigation keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_PM251              = 34; // (PM251) Only one keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_NUMERIC_PM40       = 39; // (PM40) Numeric keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_QWERTY_PM40        = 40; // (PM40) Qwerty keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_NUMERIC_PM60       = 41; // (PM60) Numeric keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_QWERTY_PM60        = 42; // (PM60) Qwerty keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_NUMERIC_MUTANT     = 43; // (MUTANT) Numeric keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_QWERTY_MUTANT      = 44; // (MUTANT) Qwerty keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_NUMERIC_OCTANT     = 45; // (OCTANT) Numeric keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_NUMERIC_PM450      = 46; // (PM450) Numeric keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_ALPHANUMERIC_PM450 = 47; // (PM450) Alpha-Numeric keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_NAVIGATION_SEXTANT = 48; // (SEXTANT) Navigation keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_NUMERIC_XM5        = 49; // (XM5) Numeric keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_QWERTY_XM5         = 50; // (XM5) Qwerty keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_NAVIGATION_PM80    = 51; // (PM80) Navigation keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_NAVIGATION_PM70    = 52; // (PM70) Navigation keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_NUMERIC_PM66       = 53; // (PM66) Numeric keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_NUMERIC_PM550      = 54; // (PM550) Numeric keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_NAVIGATION_PM45    = 55; // (PM45) Navigation keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_ALPHANUMERIC_PM550 = 56; // (PM550) Alpha-numeric keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_NUMERIC_DTX400     = 57; // (DTX400) Numeric keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_NAVIGATION_PM85    = 58; // (PM85) Navigation keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_NAVIGATION_XT200   = 59; // (XT200) Navigation keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_NAVIGATION_PM90    = 60; // (PM90) Navigation keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_NUMERIC_ITG600     = 61; // (ITG600) Numeric keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_NAVIGATION_ITG650  = 62; // (ITG650) Navigation keyboard
    @UnsupportedAppUsage
    public static final int KEYBOARD_FUNCNUMERIC_PM451  = 63;
    @UnsupportedAppUsage
    public static final int KEYBOARD_ALPHANUMERIC_PM451 = 64;
    @UnsupportedAppUsage
    public static final int KEYBOARD_NUMERIC_PM451      = 65;
    @UnsupportedAppUsage
    public static final int KEYBOARD_ALDINUMERIC_PM451  = 66;
    @UnsupportedAppUsage
	public static final int KEYBOARD_NAVIGATION_PM500   = 67; // (PM500) Navigation keyboard
    @UnsupportedAppUsage
	public static final int KEYBOARD_NAVIGATION_PM30    = 68;
    @UnsupportedAppUsage
	public static final int KEYBOARD_NAVIGATION_PM75    = 69;
    @UnsupportedAppUsage
	public static final int KEYBOARD_NUMERIC_PM67       = 70;
    @UnsupportedAppUsage
	public static final int KEYBOARD_NUMERIC_PM351      = 71;
    @UnsupportedAppUsage
    public static final int KEYBOARD_NUMERIC_DTX450     = 72; // (DTX450) Numeric keyboard

    /* Touch Type */
    @UnsupportedAppUsage
	public static final int TOUCH_REGISTIVE             = 1;
    @UnsupportedAppUsage
	public static final int TOUCH_CAPACITIVE_S3202      = 2;
    @UnsupportedAppUsage
	public static final int TOUCH_CAPACITIVE_S3508      = 3;
    @UnsupportedAppUsage
	public static final int TOUCH_CAPACITIVE_ILITEK     = 4;
    @UnsupportedAppUsage
	public static final int TOUCH_CAPACITIVE_S3528      = 5;
    @UnsupportedAppUsage
	public static final int TOUCH_CAPACITIVE_S3708      = 6;
    @UnsupportedAppUsage
    public static final int TOUCH_CAPACITIVE_FT8716U    = 7;
    @UnsupportedAppUsage
    public static final int TOUCH_RESISTIVE_BU21029     = 8;
    @UnsupportedAppUsage
    public static final int TOUCH_CAPACITIVE_TD4300     = 9;
    @UnsupportedAppUsage
    public static final int TOUCH_CAPACITIVE_GT1151Q    = 10;
    @UnsupportedAppUsage
    public static final int TOUCH_CAPACITIVE_GT1151QM   = 11;

    /* Display Type */
    @UnsupportedAppUsage
	public static final int DISPLAY_VGA_TRULY	        = 103;
    @UnsupportedAppUsage
	public static final int DISPLAY_WVGA_TRULY	        = 105;
    @UnsupportedAppUsage
	public static final int DISPLAY_FWVGA_TRULY	        = 107;
    @UnsupportedAppUsage
	public static final int DISPLAY_HD_TNHD5040	        = 108;
    @UnsupportedAppUsage
	public static final int DISPLAY_TYPE_BYD	        = 109;
    @UnsupportedAppUsage
	public static final int DISPLAY_HX8394A01           = 110;
    @UnsupportedAppUsage
	public static final int DISPLAY_HX8379C             = 111;
    @UnsupportedAppUsage
	public static final int DISPLAY_ILI9881D            = 112;
    @UnsupportedAppUsage
	public static final int DISPLAY_HX8369B             = 113;
    @UnsupportedAppUsage
	public static final int DISPLAY_HX8369A             = 114;
    @UnsupportedAppUsage
    public static final int DISPLAY_FT8716U             = 115;
    @UnsupportedAppUsage
    public static final int DISPLAY_HOB050H1018         = 116;
    @UnsupportedAppUsage
	public static final int DISPLAY_TTDLM050064101A 	= 117;
    @UnsupportedAppUsage
	public static final int DISPLAY_HX8394F             = 118;
    @UnsupportedAppUsage
    public static final int DISPLAY_ST7703              = 119;
    @UnsupportedAppUsage
    public static final int DISPLAY_ST7701S             = 120;
    @UnsupportedAppUsage
    public static final int DISPLAY_OTM8019A            = 121;
    @UnsupportedAppUsage
    public static final int DISPLAY_HOT055H876          = 122;
    @UnsupportedAppUsage
    public static final int DISPLAY_FC11910             = 123;
    @UnsupportedAppUsage
    public static final int DISPLAY_ILI9881P            = 124;
    @UnsupportedAppUsage
    public static final int DISPLAY_TD4300              = 125;
    @UnsupportedAppUsage
    public static final int DISPLAY_JD9365DA            = 126;
    @UnsupportedAppUsage
    public static final int DISPLAY_XM91080             = 127;
    @UnsupportedAppUsage
    public static final int DISPLAY_HX8399C             = 128;

    /* LCD Resolution */
    @UnsupportedAppUsage
	public static final int LCD_RESOLUTION_QVGA         = 0;
    @UnsupportedAppUsage
	public static final int LCD_RESOLUTION_VGA          = 1;
    @UnsupportedAppUsage
	public static final int LCD_RESOLUTION_WVGA         = 2;
    @UnsupportedAppUsage
	public static final int LCD_RESOLUTION_HD           = 3;

    /* Bluetooth Type */
    @UnsupportedAppUsage
	public static final int BLUETOOTH_CSRBLUECORE4ROM   = 3; // CSR Bluetooth module
    @UnsupportedAppUsage
	public static final int BLUETOOTH_TI_1273L          = 5; // TI 1273 WLAN/BT module.
    @UnsupportedAppUsage
	public static final int BLUETOOTH_QC_WCN3660B       = 6; // QUALCOMM WLAN/BT module.
    @UnsupportedAppUsage
	public static final int BLUETOOTH_QC_WCN3680B       = 7; // QUALCOMM WLAN/BT module.
    @UnsupportedAppUsage
	public static final int BLUETOOTH_QC_WCN3990        = 8; // Qualcomm WLAN/BT module
    @UnsupportedAppUsage
	public static final int BLUETOOTH_QC_WCN3988        = 9; // Qualcomm WLAN/BT module

    /* WIFI Type */
    @UnsupportedAppUsage
	public static final int WIFI_USI_SD8686_SDIO        = 3;  // USI SDIO 802.11g radio
    @UnsupportedAppUsage
	public static final int WIFI_TI_1273L               = 7;  // TI 1273 WLAN/BT a/b/g/n module.
    @UnsupportedAppUsage
	public static final int WIFI_SUMMIT                 = 20; // SUMMIT WLAN module.
    @UnsupportedAppUsage
	public static final int WIFI_QC_WCN3660B            = 21; // QUALCOMM WLAN/BT module.
    @UnsupportedAppUsage
	public static final int WIFI_QC_WCN3680B            = 22; // QUALCOMM WLAN/BT module.
    @UnsupportedAppUsage
	public static final int WIFI_QC_WCN3990             = 23; // Qualcomm WLAN/BT module
    @UnsupportedAppUsage
	public static final int WIFI_QC_WCN3988             = 24; // Qualcomm WLAN/BT module

    /* Phone Type */
    @UnsupportedAppUsage
	public static final int PHONE_HC25                  = 1; // Cinterion HC25
    @UnsupportedAppUsage
	public static final int PHONE_PH8                   = 2; // Cinterion PH8
    @UnsupportedAppUsage
    public static final int PHONE_WTR4905               = 3; // Qualcomm MSM8916
    @UnsupportedAppUsage
    public static final int PHONE_WTR3925               = 4; // Qualcomm SDM450
    @UnsupportedAppUsage
	public static final int PHONE_SDR660                = 5; // Qualcomm SDM660
    @UnsupportedAppUsage
	public static final int PHONE_EG25                  = 6; // Queltel EG25
    @UnsupportedAppUsage
	public static final int PHONE_WTR2965               = 7; // Qualcomm SM4250
    @UnsupportedAppUsage
	public static final int PHONE_WAVECOM               = PHONE_HC25;
    @UnsupportedAppUsage
	public static final int PHONE_SIEMENS               = PHONE_PH8;
    @UnsupportedAppUsage
	public static final int PHONE_QUALCOMM              = PHONE_WTR3925;

    /* GPS Type */
    @UnsupportedAppUsage
	public static final int GPS_HC25                    = 1; // AGPS
    @UnsupportedAppUsage
	public static final int GPS_PH8                     = 2; // AGPS
    @UnsupportedAppUsage
	public static final int GPS_UBLOX7                  = 3; // U-BLOX 7
    @UnsupportedAppUsage
	public static final int GPS_OEM615                  = 4; // OEM615
    @UnsupportedAppUsage
	public static final int GPS_QC_WCN3660B             = 5; // OEM615
    @UnsupportedAppUsage
	public static final int GPS_UBLOX7P                 = 6; // U-BLOX 7P
    @UnsupportedAppUsage
	public static final int GPS_QC_WCN3680B             = 7; // OEM615
    @UnsupportedAppUsage
	public static final int GPS_QC_SDM660               = 8; // Qualcomm SDM660
    @UnsupportedAppUsage
	public static final int GPS_QC_SDA660               = 9; // Qualcomm SDA660
    @UnsupportedAppUsage
	public static final int GPS_EG25                    = 10; // EG25
    @UnsupportedAppUsage
	public static final int GPS_QC_WCN3988              = 11; // Qualcomm SM4250

    /* NAND Type */
    @UnsupportedAppUsage
	public static final int NAND_EMMC                   = 0x0001;
    @UnsupportedAppUsage
	public static final int NAND_K9F1G08                = ('K'<<8)+'9'; // Samsung K9F1G08, 128X8 NAND flash
    @UnsupportedAppUsage
	public static final int NAND_K9F2G08                = ('K'<<8)+'A'; // Samsung K9F2G08, 256X8 NAND flash
    @UnsupportedAppUsage
	public static final int NAND_NAND01GR3B2B           = 0x4E13;       // Numonyx NAND01GR3B2B, 128x8 NAND flash
    @UnsupportedAppUsage
	public static final int NAND_NAND02GR3B2C           = 0x4E23;       // Numonyx NAND02GR3B2C, 256x8 NAND flash
    @UnsupportedAppUsage
	public static final int NAND_NAND01GR4B2B           = 0x4E14;       // Numonyx NAND01GR4B2B, 128x16 NAND flash
    @UnsupportedAppUsage
	public static final int NAND_NAND02GR4B2C           = 0x4E24;       // Numonyx NAND02GR4B2C, 256x16 NAND flash
    @UnsupportedAppUsage
	public static final int NAND_NAND04GW3B2D           = 0x4E43;       // Numonyx NAND04GW3B2D, 512x8 NAND flash
    @UnsupportedAppUsage
	public static final int NAND_TOSHIBA2GX16           = 0x5424;       // Toshiba TC58DVM82F1XB00 256x16 NAND flash
    @UnsupportedAppUsage
	public static final int NAND_TC58NYG0S3EBAI4        = 0x5413;       // Toshiba TC58NYG0S3EBAI4 128x8 NAND flash
    @UnsupportedAppUsage
	public static final int NAND_TC58NYG1S3EBAI5        = 0x5423;       // Toshiba TC58NYG1S3EBAI5 256x8 NAND flash
    @UnsupportedAppUsage
	public static final int NAND_TC58NYG2S3EBAI5        = 0x5443;       // Toshiba TC58NYG2S3EBAI5 512x8 NAND flash
    @UnsupportedAppUsage
	public static final int NAND_TC58NVG2S3EBAI5        = 0x5433;       // Toshiba TC58NVG2S3EBAI5 512x8 NAND flash(3.3V)
    @UnsupportedAppUsage
	public static final int NAND_MT29F4G16              = ('M'<<8)+'I'; // MICRON 4Gb x16b SLC NAND Flash (MT29F4G16)
    @UnsupportedAppUsage
	public static final int NAND_MT29F8G16              = ('M'<<8)+'J'; // MICRON 8Gb x16b SLC NAND Flash (MT29F8G16)
    @UnsupportedAppUsage
	public static final int NAND_MICRON                 = NAND_MT29F4G16;
    @UnsupportedAppUsage
    public static final int NAND_KMQ7X000SA_B315        = 0x0002;       // SAMSUNG KMQ7X000SA-B315 / 1G RAM / 8G EMMC
    @UnsupportedAppUsage
    public static final int NAND_KMQ72000SM_B316        = 0x0003;       // SAMSUNG KMQ72000SM-B316 / 1G RAM / 8G EMMC
    @UnsupportedAppUsage
    public static final int NAND_MT29TZZZ5D6YKFAH_125_W96N  = 0x0004;   // MICRON MT29TZZZ5D6YKFAH_125_W96N / 2G RAM / 16G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_KMQ820013M_B419       = 0x05;     // SAMSUNG KMQ820013M-B419 / 2G RAM / 16G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_H9TQ17ABJTACUR_KUM    = 0x06;     // HYNIX H9TQ17ABJTACUR-KUM / 2G RAM / 16G EMMC
    @UnsupportedAppUsage
    public static final int NAND_KMFNX0012M_B214            = 0x07;     // SAMSUNG KMFNX0012M-B214 / 1G RAM / 8G EMMC
    @UnsupportedAppUsage
    public static final int NAND_MT29TZZZ5D6EKFRL_107_W96R  = 0x08;     // MICRON MT29TZZZ5D6EKFRL-107 W.96R / 2G RAM / 16G EMMC
    @UnsupportedAppUsage
	public static final int NAND_MT29TZZZ5D6DKFRL_107_W9A6  = 0x09;		// MICRON MT29TZZZ5D6EKFRL-107 W.9A6 / 2G RAM / 16G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_H9TQ17ABJTBCUR_KUM    = 0x0A;     // HYNIX H9TQ17ABJTBCUR-KUM / 2G RAM / 16G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_H9TQ17ABJTCCUR_KUM    = 0x0B;     // HYNIX H9TQ17ABJTCCUR-KUM / 2G RAM / 16G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_H9TQ26ADFTBCUR_KUM    = 0x0C;     // HYNIX H9TQ26ADFTBCUR-KUM / 3G RAM / 32G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_KMQE60013M_B318    = 0x0D;        // SAMSUNG KMQE60013M-B318  / 2G RAM / 16G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_KMRX60014M_B614    = 0x0E;        // SAMSUNG KMRX60014M-B614  / 4G RAM / 32G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_KMGX6001BM_B514    = 0x0F;        // SAMSUNG KMGX6001BM_B514  / 4G RAM / 32G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_KMQE10013M_B318    = 0x10;        // SAMSUNG KMQE10013M-B318  / 2G RAM / 16G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_KMGD6001BM_B421    = 0x11;        // SAMSUNG KMGD6001BM-B421  / 3G RAM / 32G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_KMRH60014A_B614    = 0x12;        // SAMSUNG KMRH60014A-B614  / 4G RAM / 64G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_KMDH6001DA_B422    = 0x13;        // SAMSUNG KMDH6001DA_B422  / 4G RAM / 64G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_H9HP52ACPMMDAR_KMM = 0x14;        // HYNIX H9HP52ACPMMDAR-KMM / 4G RAM / 64G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_KMRP60014M_B614    = 0x15;        // SAMSUNG KMRP60014M-B614  / 4G RAM / 64G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_KMGX6001BA_B514    = 0x16;        // SAMSUNG KMGX6001BA_B514  / 3G RAM / 32G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_MT29TZZZAD8DKKFB_107_W9N8 = 0x17;  // MICRON MT29TZZZAD8DKKFB-107 W.9N8 / 4G RAM / 64G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_KMDX60018M_B425    = 0x18;        // SAMSUNG KMDX60018M_B425  / 3G RAM / 32G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_MT29VZZZAD8GQFSL_046_W9R8 = 0x19; // MICRON MT29VZZZAD8GQFSL_046 W.9R8 / 4G RAM / 64G EMMC
    @UnsupportedAppUsage
    public static final int NAND_TYPE_KMDP6001DA_B425    = 0x1A;        // SAMSUNG KMDP6001DA_B425  / 4G RAM / 64G EMMC

    /* Camera Type */
    @UnsupportedAppUsage
	public static final int CAMERA_OV3640               = 1;
    @UnsupportedAppUsage
	public static final int CAMERA_MT9T111              = 2;
    @UnsupportedAppUsage
	public static final int CAMERA_S5K4ECGX             = 3; // For PM60, MUTANT
    @UnsupportedAppUsage
	public static final int CAMERA_MT9E013              = 4; // APTINA(FROM OCTANT EVT)
    @UnsupportedAppUsage
	public static final int CAMERA_AR0833               = 5; // APTINA(FROM OCTANT DVT2)
    @UnsupportedAppUsage
    public static final int CAMERA_GC2355               = 6;
    @UnsupportedAppUsage
    public static final int CAMERA_S5KH5YA              = 7;
    @UnsupportedAppUsage
    public static final int CAMERA_HI1332               = 8;
    @UnsupportedAppUsage
    public static final int CAMERA_GC2375               = 9;
    @UnsupportedAppUsage
    public static final int CAMERA_HI843B               = 10;
    @UnsupportedAppUsage
    public static final int CAMERA_HI846                = 11;
    @UnsupportedAppUsage
    public static final int CAMERA_HI556                = 12;
    @UnsupportedAppUsage
    public static final int CAMERA_S5K3L8               = 13;
    @UnsupportedAppUsage
    public static final int CAMERA_GC5025               = 14;
    @UnsupportedAppUsage
    public static final int CAMERA_GC5035               = 15;
    @UnsupportedAppUsage
    public static final int CAMERA_OV13855              = 16;
    @UnsupportedAppUsage
    public static final int CAMERA_IMX258               = 17;
    @UnsupportedAppUsage
	public static final int CAMERA_GC8034   			= 18;
    @UnsupportedAppUsage
	public static final int CAMERA_GC2385   			= 19;
    @UnsupportedAppUsage
    public static final int CAMERA_OV13B10              = 20;
    @UnsupportedAppUsage
    public static final int CAMERA_OV13B10_1D            = 21;
    @UnsupportedAppUsage
    public static final int CAMERA_S5K4H7                = 22;
    @UnsupportedAppUsage
    public static final int CAMERA_S5K5E9YX              = 23;
    @UnsupportedAppUsage
    public static final int CAMERA_S5K4H7YX              = 24;
    /* Scanner Type */
    @UnsupportedAppUsage
	public static final int SCANNER_IT4000              = 5;
    @UnsupportedAppUsage
	public static final int SCANNER_IT4100              = 6;
    @UnsupportedAppUsage
	public static final int SCANNER_IT4300              = 7;
    @UnsupportedAppUsage
	public static final int SCANNER_IT5100              = 8;  // With Honeywell
    @UnsupportedAppUsage
	public static final int SCANNER_IT5300              = 9;  // With Honeywell
    @UnsupportedAppUsage
	public static final int SCANNER_N5603               = 12; // With Honeywell
    @UnsupportedAppUsage
	public static final int SCANNER_N5600               = 13; // With Honeywell
    @UnsupportedAppUsage
	public static final int SCANNER_IS4813              = 14;
    @UnsupportedAppUsage
	public static final int SCANNER_HI704A              = 15; // With Honeywell
    @UnsupportedAppUsage
	public static final int SCANNER_N4313               = 16;
    @UnsupportedAppUsage
	public static final int SCANNER_N6603               = 17;
    @UnsupportedAppUsage
	public static final int SCANNER_EX25                = 18;
    @UnsupportedAppUsage
	public static final int SCANNER_EX30                = 19;
    @UnsupportedAppUsage
	public static final int SCANNER_SE955               = 20;
    @UnsupportedAppUsage
	public static final int SCANNER_SE4500              = 21;
    @UnsupportedAppUsage
	public static final int SCANNER_SE655               = 22;
    @UnsupportedAppUsage
	public static final int SCANNER_SE965               = 23;
    @UnsupportedAppUsage
	public static final int SCANNER_SE4710              = 24;
    @UnsupportedAppUsage
	public static final int SCANNER_UE966               = 25;
    @UnsupportedAppUsage
	public static final int SCANNER_CR8000              = 26; // With CODE
    @UnsupportedAppUsage
	public static final int SCANNER_SE4750              = 27;
    @UnsupportedAppUsage
	public static final int SCANNER_MDL1500             = 28;
    @UnsupportedAppUsage
	public static final int SCANNER_N6703               = 29;
    @UnsupportedAppUsage
	public static final int SCANNER_N3601               = 31;
    @UnsupportedAppUsage
	public static final int SCANNER_N3603               = 32;
    @UnsupportedAppUsage
	public static final int SCANNER_N2600               = 33;
    @UnsupportedAppUsage
	public static final int SCANNER_N4603               = 34;
    @UnsupportedAppUsage
	public static final int SCANNER_SE4100              = 35;

    /* Scanner Class */
    @UnsupportedAppUsage
	public static final int SCANNER_CLASS_LASER         = 1;
    @UnsupportedAppUsage
	public static final int SCANNER_CLASS_IMAGER        = 2;

    /* RFID Type */
    @UnsupportedAppUsage
	public static final int RFID_MIPS_MTR_R900          = 49;
    @UnsupportedAppUsage
	public static final int RFID_MINERVA                = 50;
    @UnsupportedAppUsage
	public static final int RFID_ARCONTIA_ARC1300       = 51;
    @UnsupportedAppUsage
	public static final int RFID_NFC_PM663              = 52;
    @UnsupportedAppUsage
	public static final int RFID_NFC_PN547              = 53;
    @UnsupportedAppUsage
	public static final int RFID_NFC_PN5482D2EV         = 54;
    @UnsupportedAppUsage
	public static final int RFID_NFC_NQ210              = 55;
    @UnsupportedAppUsage
	public static final int RFID_NFC_NQ220              = 56;
    @UnsupportedAppUsage
	public static final int RFID_NFC_NQ310              = 57;
    @UnsupportedAppUsage
	public static final int RFID_NFC_NQ330              = 58;
    @UnsupportedAppUsage
	public static final int RFID_NFC_PN553              = 59;
    @UnsupportedAppUsage
	public static final int RFID_NFC_PN7150             = 60;	// 2019.01.07 phill.kim
    @UnsupportedAppUsage
	public static final int RFID_NFC_PN5180             = 61;
    @UnsupportedAppUsage
	public static final int RFID_NFC_PN557              = 62;

    /* Sensor Type */
    @UnsupportedAppUsage
	public static final int SENSOR_AK8963C              = 1;
    @UnsupportedAppUsage
	public static final int SENSOR_BMI055               = 2;
    @UnsupportedAppUsage
	public static final int SENSOR_APDS9900             = 3;
    @UnsupportedAppUsage
	public static final int SENSOR_BMP180               = 4;
    @UnsupportedAppUsage
	public static final int SENSOR_TMP102               = 5;
    @UnsupportedAppUsage
	public static final int SENSOR_APDS9930             = 6;
    @UnsupportedAppUsage
	public static final int SENSOR_BMA250               = 7;
    @UnsupportedAppUsage
	public static final int SENSOR_BMA223               = 8;
    @UnsupportedAppUsage
	public static final int SENSOR_STK3311              = 9;
    @UnsupportedAppUsage
	public static final int SENSOR_BMI160               = 10;
    @UnsupportedAppUsage
	public static final int SENSOR_LSM6DS3              = 11;
    @UnsupportedAppUsage
	public static final int SENSOR_ST480                = 12;
    @UnsupportedAppUsage
	public static final int SENSOR_AK09918              = 13;
    @UnsupportedAppUsage
	public static final int SENSOR_BMA253               = 14;
    @UnsupportedAppUsage
	public static final int SENSOR_MC3413               = 15;
    @UnsupportedAppUsage
	public static final int SENSOR_RPR0521              = 16;
    @UnsupportedAppUsage
	public static final int SENSOR_MN25713E             = 17;
    @UnsupportedAppUsage
	public static final int SENSOR_CM36283				= 18;
    @UnsupportedAppUsage
	public static final int SENSOR_LIS3DH				= 19;
    @UnsupportedAppUsage
	public static final int SENSOR_STK3332				= 20;
    @UnsupportedAppUsage
	public static final int SENSOR_STK33562				= 21;
    @UnsupportedAppUsage
	public static final int SENSOR_LSM6DSM				= 22;
    @UnsupportedAppUsage
	public static final int SENSOR_STK8BA58				= 23;
    @UnsupportedAppUsage
	public static final int SENSOR_MMC5603NJ			= 24;
    @UnsupportedAppUsage
	public static final int SENSOR_ICM4X6XX				= 25;

    /* Flash Type */
    @UnsupportedAppUsage
	public static final int FLASH_AP2061                = 1;
    @UnsupportedAppUsage
	public static final int FLASH_LM3559                = 2;

    /* MSR Type */
    @UnsupportedAppUsage
    public static final int MSR_MMD1000                 = 1;
    @UnsupportedAppUsage
    public static final int MSR_PM1100                  = 2;

    /* FINGERPRINT Type */
    @UnsupportedAppUsage
    public static final int FINGERPRINT_FPC1020         = 1;
}
