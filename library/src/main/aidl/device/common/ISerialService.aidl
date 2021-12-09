package device.common;

import android.os.ParcelFileDescriptor;

interface ISerialService {
    boolean open(String path, int baudrate, int flags, boolean hwflow);
    void close(String path);
    int read(String path, out byte[] buffer, int length, int timeout);
    boolean write(String path, in byte[] buffer, int offset, int length);
    int setBaudrate(String path, int baudrate);
    int getBaudrate(String path);
    int getIOCount(String path);
    void sendBreak(String path);
    ParcelFileDescriptor getParcelFileDescriptor(String path);
    String[] getSerialPortAllDevices();
    String[] getSerialPortAllDevicesPath();
}
