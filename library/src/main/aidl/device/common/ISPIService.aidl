package device.common;

import android.os.ParcelFileDescriptor;

interface ISPIService {
    boolean open(String path, int flags);
    boolean close(String path);
    int read(String path, out byte[] buffer, int length);
    boolean write(String path, in byte[] buffer, int offset, int length);
}
