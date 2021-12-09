package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;

public class RFIDConst {
        @UnsupportedAppUsage
    public static final String TAG = "RFIDService";
        @UnsupportedAppUsage
    public static final boolean DEBUG = false;

    public final static class DeviceType {
        private DeviceType() {}
        @UnsupportedAppUsage
        public static final int DEVICE_UNKNOWN = 0;
        @UnsupportedAppUsage
        public static final int DEVICE_BT = 1;
        @UnsupportedAppUsage
        public static final int DEVICE_USB = 2;
        @UnsupportedAppUsage
        public static final int DEVICE_UART = 3;
    }

    public static class DeviceState {
        private DeviceState() {}
        @UnsupportedAppUsage
        public final static int BT_CONNECTED = 1;
        @UnsupportedAppUsage
        public final static int BT_DISCONNECTED = 2;
        @UnsupportedAppUsage
        public final static int BT_OPENED = 3;
        @UnsupportedAppUsage
        public final static int BT_CLOSED = 4;
        @UnsupportedAppUsage
        public final static int USB_OPENED = 5;
        @UnsupportedAppUsage
        public final static int USB_CLOSED = 6;
        @UnsupportedAppUsage
        public final static int UART_OPENED = 7;
        @UnsupportedAppUsage
        public final static int UART_CLOSED = 8;
        @UnsupportedAppUsage
        public final static int TRIGGER_MODE_RFID = 9;
        @UnsupportedAppUsage
        public final static int TRIGGER_MODE_SCAN = 10;
        @UnsupportedAppUsage
        public final static int TRIGGER_RFID_KEYDOWN = 11;
        @UnsupportedAppUsage
        public final static int TRIGGER_RFID_KEYUP = 12;
        @UnsupportedAppUsage
        public final static int TRIGGER_SCAN_KEYDOWN = 13;
        @UnsupportedAppUsage
        public final static int TRIGGER_SCAN_KEYUP = 14;
        @UnsupportedAppUsage
        public final static int LOW_BATT = 15;
        @UnsupportedAppUsage
        public final static int POWER_OFF = 16;
        @UnsupportedAppUsage
        public final static int USB_CONNECTED = 17;
        @UnsupportedAppUsage
        public final static int USB_DISCONNECTED = 18;
        @UnsupportedAppUsage
        public final static int UART_CONNECTED = 19;
        @UnsupportedAppUsage
        public final static int UART_DISCONNECTED = 20;
    }

    public final static class DeviceConfig {
        private DeviceConfig() {}
        // Buzzer volume
        @UnsupportedAppUsage
        public static final int BUZZER_HIGH = 2;
        @UnsupportedAppUsage
        public static final int BUZZER_LOW = 1;
        @UnsupportedAppUsage
        public static final int BUZZER_MUTE = 0;

        // Vibrator
        @UnsupportedAppUsage
        public static final int VIBRATOR_ON = 1;
        @UnsupportedAppUsage
        public static final int VIBRATOR_OFF = 0;

        // Battery life
        @UnsupportedAppUsage
        public static final int BATT_LIFE_OFF = 0;
        @UnsupportedAppUsage
        public static final int BATT_LIFE_ON = 1;

        // Baudrate
        @UnsupportedAppUsage
        public static final int BAUDRATE_9600 = 9600;
        @UnsupportedAppUsage
        public static final int BAUDTATE_19200 = 19200;
        @UnsupportedAppUsage
        public static final int BAUDTATE_38400 = 38400;
        @UnsupportedAppUsage
        public static final int BAUDTATE_57600 = 57600;
        @UnsupportedAppUsage
        public static final int BAUDTATE_115200 = 115200;
        @UnsupportedAppUsage
        public static final int BAUDTATE_230400 = 230400;
        @UnsupportedAppUsage
        public static final int BAUDTATE_460800 = 460800;
        @UnsupportedAppUsage
        public static final int BAUDTATE_921600 = 921600;

        // BT power class
        @UnsupportedAppUsage
        public static final int BT_POWER_CLASS_1 = 1;
        @UnsupportedAppUsage
        public static final int BT_POWER_CLASS_2 = 2;
    }

    public final static class RFIDConfig {
        private RFIDConfig() {}
        // Inventory mode
        @UnsupportedAppUsage
        public static final int INVENTORY_MODE_CONTINUOUS = 0;
        @UnsupportedAppUsage
        public static final int INVENTORY_MODE_SINGLE = 1;
        @UnsupportedAppUsage
        public static final int INVENTORY_SELECT_NONE = 0;
        @UnsupportedAppUsage
        public static final int INVENTORY_SELECT_NONE_1 = 1; // same as 0
        @UnsupportedAppUsage
        public static final int INVENTORY_SELECT_EXCLUSION = 2;
        @UnsupportedAppUsage
        public static final int INVENTORY_SELECT_INCLUSION = 3;
        @UnsupportedAppUsage
        public static final int INVENTORY_TIMEOUT_INFINITE = 0;
        // Inventory parameter
        @UnsupportedAppUsage
        public static final int INVENTORY_SESSION_0 = 0;
        @UnsupportedAppUsage
        public static final int INVENTORY_SESSION_1 = 1;
        @UnsupportedAppUsage
        public static final int INVENTORY_SESSION_2 = 2;
        @UnsupportedAppUsage
        public static final int INVENTORY_SESSION_3 = 3;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_0 = 0;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_1 = 1;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_2 = 2;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_3 = 3;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_4 = 4;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_5 = 5;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_6 = 6;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_7 = 7;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_8 = 8;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_9 = 9;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_10 = 10;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_11 = 11;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_12 = 12;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_13 = 13;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_14 = 14;
        @UnsupportedAppUsage
        public static final int INVENTORY_Q_15 = 15;
        @UnsupportedAppUsage
        public static final int INVENTORY_TARGET_A = 0;
        @UnsupportedAppUsage
        public static final int INVENTORY_TARGET_B = 1;
        @UnsupportedAppUsage
        public static final int INVENTORY_TARGET_AB = 2;
        // Link profile
        @UnsupportedAppUsage
        public static final int LINK_PROFILE_0 = 0;
        @UnsupportedAppUsage
        public static final int LINK_PROFILE_1 = 1;
        @UnsupportedAppUsage
        public static final int LINK_PROFILE_2 = 2;
        @UnsupportedAppUsage
        public static final int LINK_PROFILE_3 = 3;
        // Data format
        @UnsupportedAppUsage
        public static final int DATA_FORMAT_PC_EPC_CRC = 0;
        @UnsupportedAppUsage
        public static final int DATA_FORMAT_PC_EPC = 1;
        @UnsupportedAppUsage
        public static final int DATA_FORMAT_EPC_CRC = 2;
        @UnsupportedAppUsage
        public static final int DATA_FORMAT_EPC_ONLY = 3;
        // Transmission data format
        @UnsupportedAppUsage
        public static final int TX_FORMAT_TAG_DATA = 0;
        @UnsupportedAppUsage
        public static final int TX_FORMAT_PREFIX_TAG_DATA = 1;
        @UnsupportedAppUsage
        public static final int TX_FORMAT_TAG_DATA_SUFFIX = 2;
        @UnsupportedAppUsage
        public static final int TX_FORMAT_PREFIX_TAG_DATA_SUFFIX = 3;
        // Filter
        @UnsupportedAppUsage
        public static final int FILTER_ON = 1;
        @UnsupportedAppUsage
        public static final int FILTER_OFF = 0;
    }

    public final static class ResultType {
        private ResultType() {}
        @UnsupportedAppUsage
        public static final String INTENT_EVENT = "device.rfid.EVENT";
        @UnsupportedAppUsage
        public static final String EXTRA_EVENT_ACTION = "EXTRA_EVENT_ACTION";
        @UnsupportedAppUsage
        public static final String EXTRA_EVENT_CATEGORY = "EXTRA_EVENT_CATEGORY";
        @UnsupportedAppUsage
        public static final String EXTRA_EVENT_RFID_DATA = "EXTRA_EVENT_RFID_DATA";
        @UnsupportedAppUsage
        public static final String EXTRA_EVENT_RFID_TIME = "EXTRA_EVENT_RFID_TIME";
        @UnsupportedAppUsage
        public static final String EXTRA_EVENT_RFID_RSSI = "EXTRA_EVENT_RFID_RSSI";
        @UnsupportedAppUsage
        public static final String EXTRA_EVNET_RFID_CHANNEL = "EXTRA_EVNET_RFID_CHANNEL";
        @UnsupportedAppUsage
        public static final String EXTRA_EVENT_RFID_TEMP = "EXTRA_EVENT_RFID_TEMP";
        @UnsupportedAppUsage
        public static final String EXTRA_EVENT_RFID_PHASE = "EXTRA_EVENT_RFID_PHASE";
        @UnsupportedAppUsage
        public static final String EXTRA_EVENT_RFID_ANT = "EXTRA_EVENT_RFID_ANT";

        @UnsupportedAppUsage
        public static final int RFID_RESULT_CALLBACK = 0;
        @UnsupportedAppUsage
        public static final int RFID_RESULT_KBDMSG = 1;
        @UnsupportedAppUsage
        public static final int RFID_RESULT_COPYPASTE = 2;
        @UnsupportedAppUsage
        public static final int RFID_RESULT_EVENT = 3;
        @UnsupportedAppUsage
        public static final int RFID_RESULT_CUSTOM_INTENT = 4;
        @UnsupportedAppUsage
        public static final int RFID_RESULT_USERMSG = 5;
        @UnsupportedAppUsage
        public static final int RFID_RESULT_CTRLV = 6;
    }

    public final static class TerminatorType {
        private TerminatorType() {}
        @UnsupportedAppUsage
        public static final int RFID_TERMINATOR_NONE = 0;
        @UnsupportedAppUsage
        public static final int RFID_TERMINATOR_SPACE = 1;
        @UnsupportedAppUsage
        public static final int RFID_TERMINATOR_TAB = 2;
        @UnsupportedAppUsage
        public static final int RFID_TERMINATOR_LF = 3;
        @UnsupportedAppUsage
        public static final int RFID_TERMINATOR_TAB_LF = 4;
    }

    public final static class CommandErr {
        private CommandErr() {}
        @UnsupportedAppUsage
        public final static int SUCCESS = 0;
        @UnsupportedAppUsage
        public final static int COMM_ERR = -1;
        @UnsupportedAppUsage
        public final static int OPEN_FAILED = -2;
        @UnsupportedAppUsage
        public final static int OTHER_CMD_RUNNING = -3;
        @UnsupportedAppUsage
        public final static int WRITE_FAILED = -4;
        @UnsupportedAppUsage
        public final static int WRONG_DEVICE_STATE = -5;
        @UnsupportedAppUsage
        public final static int WRONG_READ_PACKET = -6;
        @UnsupportedAppUsage
        public final static int WRONG_PARAM = -7;
        @UnsupportedAppUsage
        public final static int NOT_SUPPORTED = -8;
        @UnsupportedAppUsage
        public final static int FW_NOT_EXISTED = -9;
        @UnsupportedAppUsage
        public final static int FW_UPDATE_FAILED = -10;
        @UnsupportedAppUsage
        public final static int TIMEOUT = -100;
    }

    // RF Transceiver errors : return err_op=xx
    public final static class TransErr {
        private TransErr() {}
        @UnsupportedAppUsage
        public final static int HANDLE_MISMATCH = 1;
        @UnsupportedAppUsage
        public final static int CRC_TAG_RESPONSE = 2;
        @UnsupportedAppUsage
        public final static int NO_TAG_REPLY = 3;
        @UnsupportedAppUsage
        public final static int INVALID_PASSWORD = 4;
        @UnsupportedAppUsage
        public final static int ZERO_KILL_PASSWORD = 5;
        @UnsupportedAppUsage
        public final static int TAG_LOST = 6;
        @UnsupportedAppUsage
        public final static int CMD_FORMAT = 7;
        @UnsupportedAppUsage
        public final static int INVALID_READ_COUNT = 8;
        @UnsupportedAppUsage
        public final static int OUT_OF_RETRY = 9;
    }

    // EPC Global Gen2 Tag errors : return err_tag=xx
    public static class TagErr {
        private TagErr() {}
        @UnsupportedAppUsage
        public final static int UNKNOWN = 0;
        @UnsupportedAppUsage
        public final static int NOT_SUPPORTED = 1;
        @UnsupportedAppUsage
        public final static int INSUFF_PRIV = 2;
        @UnsupportedAppUsage
        public final static int MEM_OVERRUN = 3;
        @UnsupportedAppUsage
        public final static int MEM_LOCKED = 4;
        @UnsupportedAppUsage
        public final static int CRYPT_SUITE = 5;
        @UnsupportedAppUsage
        public final static int CMD_NOT_ENCAP = 6;
        @UnsupportedAppUsage
        public final static int RESP_BUF_OVERFLOW = 7;
        @UnsupportedAppUsage
        public final static int SECU_TIMEOUT = 8;
        @UnsupportedAppUsage
        public final static int INSUFF_PWR = 11;
        @UnsupportedAppUsage
        public final static int NON_SPEC = 15;
    }
}
