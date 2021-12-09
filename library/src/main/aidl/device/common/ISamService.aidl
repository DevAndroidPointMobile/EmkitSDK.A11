package device.common;

interface ISamService {
    int samSetPowerOn(boolean on);
    boolean samGetPowerStatus();
    int samOpenPort();
    int samClosePort();
    int sam_CommandAtr();
    int sam_get_atr_response(inout byte[]  req_buf);
    int sam_CommandApdu(in byte[] pAPDU, int apduLen, inout byte[] pRESP, int pRespLen);
    int sam_CommandPowerdown();
    int sam_direct_command(byte cmd ,in byte[] pdata, int datalen, inout byte[] pRESP, int pRespLen);
    int get_timeout_delay();
    void set_timeout_delay(int delay);
    int set_sam_slot(int slot);
    int get_sam_slot();
}

