package device.common;

import android.compat.annotation.UnsupportedAppUsage;

public class MsrIndex {
    @UnsupportedAppUsage
	public final static int MMD1000_READ_OK				= 0;
    @UnsupportedAppUsage
	public final static int MMD1000_READ_ERROR			= 1;
    @UnsupportedAppUsage
	public final static int MMD1000_CRC_ERROR			= 2;
    @UnsupportedAppUsage
	public final static int MMD1000_NOINFOSTORE			= 3;
    @UnsupportedAppUsage
	public final static int MMD1000_AES_INIT_NOT_SET	= 4;
    @UnsupportedAppUsage
	public final static int MMD1000_READ_PREAMBLE_ERROR = 5;
    @UnsupportedAppUsage
	public final static int MMD1000_READ_POSTAMBLE_ERROR = 6;
    @UnsupportedAppUsage
	public final static int MMD1000_READ_LRC_ERROR		= 7;
    @UnsupportedAppUsage
	public final static int MMD1000_READ_PARITY_ERROR	= 8;
    @UnsupportedAppUsage
	public final static int MMD1000_BLANK_TRACK			= 9;
    @UnsupportedAppUsage
	public final static int MMD1000_CMD_STXETX_ERROR	= 10;
    @UnsupportedAppUsage
	public final static int MMD1000_CMD_UNRECOGNIZABLE	= 11;
    @UnsupportedAppUsage
	public final static int MMD1000_CMD_BCC_ERROR		= 12;
    @UnsupportedAppUsage
	public final static int MMD1000_CMD_LENGTH_ERROR	= 13;
    @UnsupportedAppUsage
	public final static int MMD1000_READ_NO_DATA		= 14;
    @UnsupportedAppUsage
	public final static int MMD1000_OTP_NO_MORE_SPACE	= 15;
    @UnsupportedAppUsage
	public final static int MMD1000_OTP_WRITE_WITHOUT_DATA	= 16;
    @UnsupportedAppUsage
	public final static int MMD1000_OTP_CRC_ERROR		= 17;
    @UnsupportedAppUsage
	public final static int MMD1000_OTP_NO_DATA_STORED	= 18;
    @UnsupportedAppUsage
	public final static int MMD1000_READ_STOP           = 19;

    @UnsupportedAppUsage
	public final static int MMD1000_DEVICE_READ_TIMEOUT		= 20;
    @UnsupportedAppUsage
	public final static int MMD1000_DEVICE_POWER_DISABLE	= 21;
    @UnsupportedAppUsage
	public final static int MMD1000_DEVICE_NOT_OPENED		= 22;
    @UnsupportedAppUsage
	public final static int MMD1000_DEVICE_DATA_CLEARED		= 23;

    @UnsupportedAppUsage
	public final static int MMD1000_POWER_ON_CMD		= 0x11<<8;
    @UnsupportedAppUsage
	public final static int MMD1000_POWER_OFF_CMD		= 0x12<<8;
    @UnsupportedAppUsage
	public final static int MMD1000_READSTART_CMD		= 0x13<<8;
    @UnsupportedAppUsage
	public final static int MMD1000_READSTOP_CMD		= 0x14<<8;
}
