package device.common;

interface II2CService {
    boolean open(String path, int flags);
    boolean close(String path);
    int read(String path, out byte[] buffer, int length);
    boolean write(String path, in byte[] buffer, int offset, int length);
    boolean setSlaveAddress(String path, int address);
}
