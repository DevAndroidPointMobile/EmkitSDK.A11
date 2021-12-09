package device.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.ParcelFileDescriptor;
import android.os.SystemProperties;
import android.os.ServiceManager;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;

public class SerialPort {
    private static final String TAG = SerialPort.class.getSimpleName();

    private ISerialService mSerialService;

    private String mPath;
    private int mBaudrate;
    private int mFlags;
    private boolean mHwFlow;
    private ParcelFileDescriptor mParcelFd;

    /**
     * Create a serial port object.
     *
     * @param device the file object of device.
     * @param baudrate the baudrate of serial port.
     * @param flags the flags of serial port.
     */
    @UnsupportedAppUsage
    public SerialPort(File device, int baudrate, int flags) throws IOException {
        this(device.getAbsolutePath(), baudrate, flags);
    }

    /**
     * Create a serial port object.
     *
     * @param path the absolute path of device.
     * @param baudrate the baudrate of serial port.
     * @param flags the flags of serial port.
     */
    @UnsupportedAppUsage
    public SerialPort(String path, int baudrate, int flags) throws IOException {
        this(path, baudrate, flags, false);
    }

    /**
     * Create a serial port object.
     *
     * @param device the file object of device.
     * @param baudrate the baudrate of serial port.
     * @param flags the flags of serial port.
     * @param hwflow the hardware flow of serial port.
     */
    @UnsupportedAppUsage
    public SerialPort(File device, int baudrate, int flags, boolean hwflow) throws IOException {
        this(device.getAbsolutePath(), baudrate, flags, hwflow);
    }

    /**
     * Create a serial port object.
     *
     * @param path the absolute path of device.
     * @param baudrate the baudrate of serial port.
     * @param flags the flags of serial port.
     * @param hwflow the hardware flow of serial port.
     */
    @UnsupportedAppUsage
    public SerialPort(String path, int baudrate, int flags, boolean hwflow) throws IOException {
        mSerialService = ISerialService.Stub.asInterface(ServiceManager.getService("SerialService"));
        mPath = path;
        mBaudrate = baudrate;
        mFlags = flags;
        mHwFlow = hwflow;

        try {
            boolean result = mSerialService.open(path, baudrate, flags, hwflow);
            if (!result) {
                Log.e(TAG, "Open fail");
                throw new IOException();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Open fail :: RemoteException");
            e.printStackTrace();
            throw new IOException();
        }
		if(mParcelFd != null) {
			try {
				mParcelFd.close();
				mParcelFd = null;
			} catch (IOException e) {
	            e.printStackTrace();
	        }
		}
    }

    /**
     * Close a serial port
     */
    @UnsupportedAppUsage
    public void closePort() {
        try {
            mSerialService.close(mPath);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
		if(mParcelFd != null) {
			try {
				mParcelFd.close();
				mParcelFd = null;
			} catch (IOException e) {
	            e.printStackTrace();
	        }
		}
    }

    /**
     * Return a file descriptor of serial port.
     * @return a FileDescriptor object.
     * @deprecated Don't supported since Android 6.0.1.
     */
    @UnsupportedAppUsage
    public FileDescriptor getFileDescriptor() {
	    if(mParcelFd == null) {
	        try {
	            mParcelFd = mSerialService.getParcelFileDescriptor(mPath);
	            if (mParcelFd != null) {
	                Log.i(TAG, "mParcelFd is not null");
					return mParcelFd.getFileDescriptor();
	            }
	        } catch (RemoteException e) {
	            e.printStackTrace();
	        }
	        Log.i(TAG, "mParcelFd is null");
	    }
		else {
			return mParcelFd.getFileDescriptor();
		}
        return null;
    }

    /**
     * Reads data from this serial port in the byte array {@code buffer}.
     *
     * @param buffer to read into
     * @return number of bytes read
     */
    @UnsupportedAppUsage
    public int read(byte[] buffer) throws IOException {
        if (buffer != null && buffer.length > 0) {
            try {
                return mSerialService.read(mPath, buffer, buffer.length, -1);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return -1;
        } else {
            throw new IOException("Invalid byte array");
        }
    }

    /**
     * Reads data from this serial port in the byte array {@code buffer}.
     *
     * @param buffer to read into
     * @param timeout read block timeout
     * @return number of bytes read
     */
    @UnsupportedAppUsage
    public int read(byte[] buffer, int timeout) throws IOException {
        if (buffer != null && buffer.length > 0) {
            try {
                return mSerialService.read(mPath, buffer, buffer.length, timeout);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return -1;
        } else {
            throw new IOException("Invalid byte array");
        }
    }

    /**
     * Equivalent to {@code write(buffer, 0, buffer.length)}.
     */
    @UnsupportedAppUsage
    public void write(byte[] buffer) throws IOException {
        write(buffer, 0, buffer.length);
    }

    /**
     * Writes {@code length} bytes from the byte array {@code buffer} starting at
     * position {@code offset} to this serial port.
     *
     * @param buffer the buffer to be written.
     * @param offset the start position in {@code buffer} from where to get bytes.
     * @param length the number of bytes from {@code buffer} to write to this serial port.
     */
    @UnsupportedAppUsage
    public void write(byte[] buffer, int offset, int length) throws IOException {
        try {
            if (!mSerialService.write(mPath, buffer, offset, length)) {
                throw new IOException("Failed the writing to this serial port : " + mPath);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a currently set baudrate for serial communication.
     * @return The currently set baudrate.
     */
    @UnsupportedAppUsage
    public int getBaudrate() {
	int ret = -1;
        try {
            ret = mSerialService.getBaudrate(mPath);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Set a baudrate for serial communication.
     * @return If the value 0, it's successed, if not 0 is failed.
     */
    @UnsupportedAppUsage
    public int setBaudrate(int baudrate) {
	int ret = -1;
        try {
            ret = mSerialService.setBaudrate(mPath, baudrate);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;

    }

    /**
     * Get a error count by sum frame, overrun and parity.
     * @return The counts when occurs error.
     */
    @UnsupportedAppUsage
    public int getIOCount() {
	int ret = -1;
	try {
            ret = mSerialService.getIOCount(mPath);
        } catch (RemoteException e) {
            e.printStackTrace();
	}
	return ret;
    }

    /**
     * Sends a stream of zero valued bits for 0.25 to 0.5 seconds
     */
    @UnsupportedAppUsage
    public void sendBreak() {
        try {
            mSerialService.sendBreak(mPath);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

