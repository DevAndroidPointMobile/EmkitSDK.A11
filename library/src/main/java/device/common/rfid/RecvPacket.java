package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class RecvPacket implements Parcelable {

    @UnsupportedAppUsage
	public String RecvString;

	public static final Parcelable.Creator<RecvPacket> CREATOR
	        = new Parcelable.Creator<RecvPacket>() {
        public RecvPacket createFromParcel(Parcel in) {
            return new RecvPacket(in);
        }

        public RecvPacket[] newArray(int size) {
            return new RecvPacket[size];
        }
    };

    /**
     * Constructor of StyleConfig.
     */
    @UnsupportedAppUsage
    public RecvPacket() {
    }

    private RecvPacket(Parcel in) {
        readFromParcel(in);
    }

    /**
     * Set the rectangle's coordinates from the data stored in the specified parcel.
     *
     * @param in  The parcel to read the rectangle's coordinate from
     *
     * @see   writeToParcel()
     */
    public void readFromParcel(Parcel in) {
        RecvString = in.readString();
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's marchalled representation.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Write this rectangle to the specified parcel.
     *
     * @param dest   The parcel to write the rectangle's coordinate into
     * @param flags  Additional flags about how the object should be written. Unused.
     *
     * @see   readFromParcel()
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(RecvString);
    }
}
