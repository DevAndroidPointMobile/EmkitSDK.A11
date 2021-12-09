package device.common;

oneway interface IMsrResultCallback {
///<BEGIN>[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata
	void onResult(int cmd, int status);
	void onResultData(int cmd, int status, in byte[] data, in byte[] track1Buf, in byte[] track2Buf, in byte[] track3Buf);
///< END >[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata
}
