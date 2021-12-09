package device.sdk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.ParcelFileDescriptor;
import android.os.SystemProperties;
import android.os.ServiceManager;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;

public class UartManager {

    private static final String TAG = UartManager.class.getSimpleName();
	private static UartManager mThis = null;
    private String mAbsolutePath = "";

    @UnsupportedAppUsage
    public static final int CS5 = 0x01;
    @UnsupportedAppUsage
    public static final int CS6 = 0x02;
    @UnsupportedAppUsage
    public static final int CS7 = 0x04;
    @UnsupportedAppUsage
    public static final int CS8 = 0x08;
    @UnsupportedAppUsage
    public static final int CSTOPB = 0x10;
    @UnsupportedAppUsage
    public static final int PARENB = 0x20;
    @UnsupportedAppUsage
    public static final int PARODD = 0x40;
    @UnsupportedAppUsage
    public static final int INPCK = 0x100;
    @UnsupportedAppUsage
    public static final int IGNPAR = 0x200;
    @UnsupportedAppUsage
    public static final int PARMRK = 0x400;

    @UnsupportedAppUsage
	public UartManager() {}
    @UnsupportedAppUsage
    public static UartManager get() {
        if (mThis == null) {
            mThis = new UartManager();
        }
        return mThis;
	}

    /**
     * Opens the specified UART port.
     * @param path The absolute path of the UART port.
     * @param baudrate Specify the data transfer rate.
     * @param flags The flags for configuring the UART port.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean open(String path, int baudrate, int flags) {
        return open(path, baudrate, flags, false);
    }

    /**
     * Opens the specified UART port.
     * @param path The absolute path of the UART port.
     * @param baudrate Specify the data transfer rate.
     * @param flags The flags for configuring the UART port.
     * @param hwflow Specify the hardware flow.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean open(String path, int baudrate, int flags, boolean hwflow) {
        try {
            if (DeviceServer.getISerialService().open(path, baudrate, flags, hwflow)) {
                mAbsolutePath = path;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Closes the open UART port.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean close() {
        try {
            DeviceServer.getISerialService().close(mAbsolutePath);
            mAbsolutePath = "";
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Reads the data from the open UART port into the buffer.
     * @param buf The buffer instance that will contain the contents.
     * @param len The number of bytes to get from the UART port.
     * @return The length in bytes of the content in the buffer. Negative numbers are failures.
     */
    @UnsupportedAppUsage
    public int read(byte[] buf, int len) {
        if (buf != null && len > 0) {
            try {
                return DeviceServer.getISerialService().read(mAbsolutePath, buf, len, -1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * Reads the data from the open UART port into the buffer.
     * @param buf The buffer instance that will contain the contents.
     * @param len The number of bytes to get from the UART port.
     * @param timeout The number of msec to blocking time on read funstion.
     * @return The length in bytes of the content in the buffer. Negative numbers are failures.
     */
    @UnsupportedAppUsage
    public int read(byte[] buf, int len, int timeout) {
        if (buf != null && len > 0) {
            try {
                return DeviceServer.getISerialService().read(mAbsolutePath, buf, len, timeout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * Writes the data to the open UART port.
     * @param buf The buffer for forwarding to the UART port.
     * @param len The number of bytes to set from the UART port.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean write(byte[] buf, int len) {
        return write(buf, 0, len);
    }

    /**
     * Writes the data to the open UART port.
     * @param buf The buffer for forwarding to the UART port.
     * @param pos The starting position of the buffer to get bytes.
     * @param len The number of bytes to get from the UART port.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean write(byte[] buf, int pos, int len) {
        if (buf != null && len > 0) {
            try {
                return DeviceServer.getISerialService().write(mAbsolutePath, buf, pos, len);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Read current baud rate of opened UART port.
     * @return Current baud rate (if return value is -1, getting baud rate is fail)
     */
    @UnsupportedAppUsage
    public int getBaudrate() {
        int ret = 0;
        try {
            ret = DeviceServer.getISerialService().getBaudrate(mAbsolutePath);
        } catch (Exception e) {
            ret = -1;
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Set the new baud rate of opened UART port.
     * @param baudrate The new baud rate value that would be set
     * @return <code>true</code> if the setting new buad rate succeeds.
     */
    @UnsupportedAppUsage
    public boolean setBaudrate(int baudrate) {
        int ret = 0;
        try {
            ret = DeviceServer.getISerialService().setBaudrate(mAbsolutePath, baudrate);
        } catch (Exception e) {
            ret = -1;
            e.printStackTrace();
        }

        return ret == 0;
    }

    /**
     * Get a error count by sum frame, overrun and parity.
     * @return The counts when occurs error.
     */
    @UnsupportedAppUsage
    public int getIOCount() {
	int ret = 0;
	try {
            ret = DeviceServer.getISerialService().getIOCount(mAbsolutePath);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Sends a stream of zero valued bits for 0.25 to 0.5 seconds.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean sendBreak() {
        try {
            DeviceServer.getISerialService().sendBreak(mAbsolutePath);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}

