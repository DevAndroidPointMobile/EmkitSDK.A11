package device.common;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class MsrResult implements Parcelable {
	/**
	* Track type
	*/
    @UnsupportedAppUsage
	public int msr_read_track_type;
    @UnsupportedAppUsage
	public int msr_read_track_error;
    @UnsupportedAppUsage
	public String msr_track1;
    @UnsupportedAppUsage
	public String msr_track2;
    @UnsupportedAppUsage
	public String msr_track3;
	private int readerType;
	private String displayCardNo;
	private int cardLength;
	///<BEGIN>[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata
    @UnsupportedAppUsage
    public int msr_track1_buf_Len;
    @UnsupportedAppUsage
    public int msr_track2_buf_Len;
    @UnsupportedAppUsage
    public int msr_track3_buf_Len;

    @UnsupportedAppUsage
    public byte[] msr_track1_buf;
    @UnsupportedAppUsage
    public byte[] msr_track2_buf;
    @UnsupportedAppUsage
    public byte[] msr_track3_buf;
	///< END >[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata

	public static final Parcelable.Creator<MsrResult> CREATOR
	        = new Parcelable.Creator<MsrResult>() {
        public MsrResult createFromParcel(Parcel in) {
            return new MsrResult(in);
        }

        public MsrResult[] newArray(int size) {
            return new MsrResult[size];
        }
    };

    @UnsupportedAppUsage
    public MsrResult() {
    }

    private MsrResult(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        msr_read_track_type = in.readInt();
        msr_read_track_error = in.readInt();
        msr_track1 = in.readString();
		msr_track2 = in.readString();
		msr_track3 = in.readString();
		readerType = in.readInt();
		displayCardNo = in.readString();
		cardLength = in.readInt();

///<BEGIN>[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata
        msr_track1_buf_Len =  in.readInt();
        msr_track2_buf_Len =  in.readInt();
        msr_track3_buf_Len =  in.readInt();

        if (msr_track1_buf_Len > 0) {
            msr_track1_buf = new byte[msr_track1_buf_Len];
            in.readByteArray(msr_track1_buf);
        }
        if (msr_track2_buf_Len > 0) {
            msr_track2_buf = new byte[msr_track2_buf_Len];
            in.readByteArray(msr_track2_buf);
        }
        if (msr_track3_buf_Len > 0) {
            msr_track3_buf = new byte[msr_track3_buf_Len];
            in.readByteArray(msr_track3_buf);
        }
    }
///< END >[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(msr_read_track_type);
        dest.writeInt(msr_read_track_error);
        dest.writeString(msr_track1);
		dest.writeString(msr_track2);
		dest.writeString(msr_track3);
		dest.writeInt(readerType);
		dest.writeString(displayCardNo);
		dest.writeInt(cardLength);

///<BEGIN>[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata
        dest.writeInt(msr_track1_buf_Len);
        dest.writeInt(msr_track2_buf_Len);
        dest.writeInt(msr_track3_buf_Len);

        if (msr_track1_buf != null) {
            dest.writeByteArray(msr_track1_buf);
        }
        if (msr_track2_buf != null) {
            dest.writeByteArray(msr_track2_buf);
        }
        if (msr_track3_buf != null) {
            dest.writeByteArray(msr_track3_buf);
        }
///< END >[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata
    }

    @UnsupportedAppUsage
	public int getMsrReadType() {
        return msr_read_track_type;
    }

    @UnsupportedAppUsage
	public void setMsrReadType(int MsrReadType) {
        msr_read_track_type = MsrReadType;
    }

    @UnsupportedAppUsage
	public int getMsrReadError() {
        return msr_read_track_error;
    }

    @UnsupportedAppUsage
	public void setMsrReadError(int MsrReadError) {
        msr_read_track_error = MsrReadError;
    }

    @UnsupportedAppUsage
	public String getMsrTrack1() {
        return msr_track1;
    }

    @UnsupportedAppUsage
	public void setMsrTrack1(String track) {
        msr_track1 = track;
    }

    @UnsupportedAppUsage
	public String getMsrTrack2() {
        return msr_track2;
    }

    @UnsupportedAppUsage
	public void setMsrTrack2(String track) {
        msr_track2 = track;
    }

    @UnsupportedAppUsage
	public String getMsrTrack3() {
        return msr_track3;
    }

    @UnsupportedAppUsage
	public void setMsrTrack3(String track) {
        msr_track3 = track;
    }

    @UnsupportedAppUsage
    public int getReaderType() {
        return readerType;
    }

    @UnsupportedAppUsage
    public void setReaderType(int type) {
        readerType = type;
    }

    @UnsupportedAppUsage
    public String getDisplayCardNo() {
        return displayCardNo;
    }

    @UnsupportedAppUsage
    public void setDisplayCardNo(String number) {
        displayCardNo = number;
    }

    @UnsupportedAppUsage
    public int getCardLength() {
        return cardLength;
    }

    @UnsupportedAppUsage
    public void setCardLength(int length) {
        cardLength = length;
    }

	///<BEGIN>[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata
    @UnsupportedAppUsage
    public void setMsrTrack1Buf(byte[] track) {
        msr_track1_buf = track;
    }
    @UnsupportedAppUsage
    public void setMsrTrack2Buf(byte[] track) {
        msr_track2_buf = track;
    }
    @UnsupportedAppUsage
    public void setMsrTrack3Buf(byte[] track) {
        msr_track3_buf = track;
    }
    @UnsupportedAppUsage
    public byte[] getMsrTrack1Buf() {
        return msr_track1_buf;
    }
    @UnsupportedAppUsage
    public byte[] getMsrTrack2Buf() {
        return msr_track2_buf;
    }
    @UnsupportedAppUsage
    public byte[] getMsrTrack3Buf() {
        return msr_track3_buf;
    }
    @UnsupportedAppUsage
    public void setMsrTrack1BufLen(int length) {
        msr_track1_buf_Len= length;
    }
    @UnsupportedAppUsage
    public void setMsrTrack2BufLen(int length) {
        msr_track2_buf_Len = length;
    }
    @UnsupportedAppUsage
    public void setMsrTrack3BufLen(int length) {
        msr_track3_buf_Len = length;
    }
    @UnsupportedAppUsage
    public int getMsrTrack1BufLen() {
        return msr_track1_buf_Len;
    }
    @UnsupportedAppUsage
    public int getMsrTrack2BufLen() {
        return msr_track2_buf_Len;
    }
    @UnsupportedAppUsage
    public int getMsrTrack3BufLen() {
        return msr_track3_buf_Len;
    }
	///<END>[lowdata][olivia.gyeong] - 2018.04.02 changed callback API for lowdata
}
