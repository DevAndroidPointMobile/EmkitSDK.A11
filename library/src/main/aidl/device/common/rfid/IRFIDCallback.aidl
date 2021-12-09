package device.common.rfid;

import device.common.rfid.RecvPacket;

oneway interface IRFIDCallback{
    // deprecated callback
    void onNotifyDataWriteFail();
    //void onBtDeviceConnected();
    //void onBtDeviceConnectFail();
    //
    void onNotifyReceivedPacket(in RecvPacket recvPacket);
    void onNotifyChangedState(int state);
}

