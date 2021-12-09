package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class TxCycle implements Parcelable {

    @UnsupportedAppUsage
	public int onTime;
    @UnsupportedAppUsage
    public int offTime;

	public static final Parcelable.Creator<TxCycle> CREATOR
	        = new Parcelable.Creator<TxCycle>() {
        public TxCycle createFromParcel(Parcel in) {
            return new TxCycle(in);
        }

        public TxCycle[] newArray(int size) {
            return new TxCycle[size];
        }
    };

    /**
     * Constructor of TxCycle.
     */
    @UnsupportedAppUsage
    public TxCycle() {
    }

    private TxCycle(Parcel in) {
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
        onTime = in.readInt();
        offTime = in.readInt();
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
        dest.writeInt(onTime);
        dest.writeInt(offTime);
    }
}
