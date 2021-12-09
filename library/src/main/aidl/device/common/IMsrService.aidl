package device.common;

import device.common.MsrResult;
import device.common.IMsrResultCallback;

interface IMsrService {
    int msr_Open(IMsrResultCallback callback);
    int msr_Close();
	int msr_StartRead();
	int msr_StopRead();
	MsrResult msr_GetData(in int read_track);
    boolean setPublicKey(String pemKey);
    boolean msr_startFirmwareUpdate();
    boolean msr_sendFirmwareData(in byte[] data, int length);
    boolean msr_endFirmwareUpdate();
    String getFirmwareVersion();

    boolean msr_UseEncryption(in byte [] ksn, in byte[] initKey);
    byte[] msr_getEncryptionData();
    byte[] msr_Command(in byte[] byteArray);

    int msr_rdi_is_enabled();
    int msr_rdi_enable(int enable);
    int msr_rdi_write(in byte[] byteArrays, int length);
    int msr_rdi_read(out byte[] data, int length);
    int msr_rdi_nelem();
    int msr_rdi_clear();
    int msr_rdi_open();
    int msr_rdi_close();

    ///<BEGIN>[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata only mmd 1000
    int setTxMode(int txmode, int allTerror, int ksnRes);
    int getTxMode();
    ///< END >[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata

    int msr_reset(int on);
    int msr_rdi_open_with_timeout(int timeout);
}
