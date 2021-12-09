package device.common;

import android.compat.annotation.UnsupportedAppUsage;

public class SamIndex {
    @UnsupportedAppUsage
	public final static int TDA8029_OK					= 0;
    @UnsupportedAppUsage
	public final static int TDA8029_ERROR				= 1;
    @UnsupportedAppUsage
	public final static int TDA8029_PACKETPATTERNNOK	= 2;
    @UnsupportedAppUsage
	public final static int TDA8029_PACKETPATTERNERROR	= 3;
    @UnsupportedAppUsage
	public final static int TDA8029_PACKETRESBADCMD		= 4;
    @UnsupportedAppUsage
	public final static int TDA8029_PACKETBADLENGTH		= 5;
    @UnsupportedAppUsage
	public final static int TDA8029_BUFFERTOOSMALL		= 6;
    @UnsupportedAppUsage
	public final static int TDA8029_COMMERROR			= 7;
    @UnsupportedAppUsage
	public final static int TDA8029_PACKETBADCHECKSUM	= 8;
    @UnsupportedAppUsage
	public final static int TDA8029_CARDDETECTFAILED	= 9;
    @UnsupportedAppUsage
	public final static int PKT_PATTERN_OK			= 0x60;
    @UnsupportedAppUsage
	public final static int PKT_PATTERN_NOK			= 0xE0;
    @UnsupportedAppUsage
	public final static int PKT_MAX_DATA_SIZE		= 506;
    @UnsupportedAppUsage
	public final static int MAX_PKT_BUF_SIZE		= (PKT_MAX_DATA_SIZE + 5);
    @UnsupportedAppUsage
	public final static int PKT_PATTERN_OFFSET		= 0;
    @UnsupportedAppUsage
	public final static int PKT_MSBLEN_OFFSET		= 1;
    @UnsupportedAppUsage
	public final static int PKT_LSBLEN_OFFSET		= 2;
    @UnsupportedAppUsage
	public final static int PKT_CMD_OFFSET			= 3;
    @UnsupportedAppUsage
	public final static int PKT_DATA_OFFSET			= 4;
    @UnsupportedAppUsage
	public final static int PKT_CMD_MASK			= 0x0A;
    @UnsupportedAppUsage
	public final static int PKT_CMD_GETREADERSTATUS	= 0xAA;
    @UnsupportedAppUsage
	public final static int PKT_CMD_CHECKCARDPRES	= 0x09;
    @UnsupportedAppUsage
	public final static int PKT_CMD_ATR			= 0x69;// modified 5V power up->iso power
    @UnsupportedAppUsage
	public final static int PKT_CMD_APDU			= 0x00;
    @UnsupportedAppUsage
	public final static int PKT_CMD_POWER_OFF		= 0x4d;

    // Receiver status
    @UnsupportedAppUsage
	public final static int PKT_RETRY = 4;
    @UnsupportedAppUsage
	public final static int STATE_NOTHING   = 0;
    @UnsupportedAppUsage
	public final static int STATE_PROCESS   = 1;
    @UnsupportedAppUsage
	public final static int STATE_RES_BEGIN = 2;
    @UnsupportedAppUsage
	public final static int STATE_RES_DONE  = 3;
    @UnsupportedAppUsage
	public final static int STATE_RES_LEN_ERROR = 4;
    @UnsupportedAppUsage
	public final static int STATE_RES_CRC_ERROR = 5;
    @UnsupportedAppUsage
	public final static int STATE_RES_TIMEOUT = 6;
}
