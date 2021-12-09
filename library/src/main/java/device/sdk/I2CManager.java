package device.sdk;

import android.compat.annotation.UnsupportedAppUsage;

import device.sdk.DeviceServer;

public class I2CManager {

    private static final String TAG = I2CManager.class.getSimpleName();
	private static I2CManager mThis = null;
    private String mAbsolutePath = "";

    @UnsupportedAppUsage
	public I2CManager() {}
    @UnsupportedAppUsage
    public static I2CManager get() {
        if (mThis == null) {
            mThis = new I2CManager();
        }
        return mThis;
	}

    /**
     * Opens the specified I2C port.
     * @param path The absolute path of the I2C port.
     * @param flags The flags for configuring the I2C port.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean open(String path, int flags) {
        try {
            if (DeviceServer.getII2CService().open(path, flags)) {
                mAbsolutePath = path;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Closes the open I2C port.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean close() {
        try {
            if (DeviceServer.getII2CService().close(mAbsolutePath)) {
                mAbsolutePath = "";
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Reads the data from the open I2C port into the buffer.
     * @param buf The buffer instance that will contain the contents.
     * @param len The number of bytes to get from the I2C port.
     * @return The length in bytes of the content in the buffer. Negative numbers are failures.
     */
    @UnsupportedAppUsage
    public int read(byte[] buf, int len) {
        if (buf != null && len > 0) {
            try {
                return DeviceServer.getII2CService().read(mAbsolutePath, buf, len);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * Writes the data to the open I2C port.
     * @param buf The buffer for forwarding to the I2C port.
     * @param len The number of bytes to set from the I2C port.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean write(byte[] buf, int len) {
        return write(buf, 0, len);
    }

    /**
     * Writes the data to the open I2C port.
     * @param buf The buffer for forwarding to the I2C port.
     * @param pos The starting position of the buffer to get bytes.
     * @param len The number of bytes to get from the I2C port.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean write(byte[] buf, int pos, int len) {
        if (buf != null && len > 0) {
            try {
                return DeviceServer.getII2CService().write(mAbsolutePath, buf, pos, len);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Sets the slave address on the I2C port.
     * @param address The slave address of type Integer.
     * @return <code>true</code> if the setting call succeeds.
     */
    @UnsupportedAppUsage
    public boolean setSlaveAddress(int address) {
        try {
            return DeviceServer.getII2CService().setSlaveAddress(mAbsolutePath, address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

