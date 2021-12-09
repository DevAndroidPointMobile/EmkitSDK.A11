package device.sdk;

import android.compat.annotation.UnsupportedAppUsage;

import device.sdk.DeviceServer;

public class SPIManager {
    @UnsupportedAppUsage

    private static final String TAG = SPIManager.class.getSimpleName();
	private static SPIManager mThis = null;
    private String mAbsolutePath = "";

    @UnsupportedAppUsage
	public SPIManager() {}
    @UnsupportedAppUsage
    public static SPIManager get() {
        if (mThis == null) {
            mThis = new SPIManager();
        }
        return mThis;
	}

    /**
     * Opens the specified SPI port.
     * @param path The absolute path of the SPI port.
     * @param flags The flags for configuring the SPI port.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean open(String path, int flags) {
        try {
            if (DeviceServer.getISPIService().open(path, flags)) {
                mAbsolutePath = path;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Closes the open SPI port.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean close() {
        try {
            if (DeviceServer.getISPIService().close(mAbsolutePath)) {
                mAbsolutePath = "";
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Reads the data from the open SPI port into the buffer.
     * @param buf The buffer instance that will contain the contents.
     * @param len The number of bytes to get from the SPI port.
     * @return The length in bytes of the content in the buffer. Negative numbers are failures.
     */
    @UnsupportedAppUsage
    public int read(byte[] buf, int len) {
        if (buf != null && len > 0) {
            try {
                return DeviceServer.getISPIService().read(mAbsolutePath, buf, len);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * Writes the data to the open SPI port.
     * @param buf The buffer for forwarding to the SPI port.
     * @param len The number of bytes to set from the SPI port.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean write(byte[] buf, int len) {
        return write(buf, 0, len);
    }

    /**
     * Writes the data to the open SPI port.
     * @param buf The buffer for forwarding to the SPI port.
     * @param pos The starting position of the buffer to get bytes.
     * @param len The number of bytes to get from the SPI port.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean write(byte[] buf, int pos, int len) {
        if (buf != null && len > 0) {
            try {
                return DeviceServer.getISPIService().write(mAbsolutePath, buf, pos, len);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
