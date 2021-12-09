package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class BattEvent implements Parcelable {

    @UnsupportedAppUsage
	public int batt;
    @UnsupportedAppUsage
    public int charge;
    @UnsupportedAppUsage
    public int cycle;

	public static final Parcelable.Creator<BattEvent> CREATOR
	        = new Parcelable.Creator<BattEvent>() {
        public BattEvent createFromParcel(Parcel in) {
            return new BattEvent(in);
        }

        public BattEvent[] newArray(int size) {
            return new BattEvent[size];
        }
    };

    /**
     * Constructor of BattEvent.
     */
    @UnsupportedAppUsage
    public BattEvent() {
    }

    private BattEvent(Parcel in) {
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
        batt = in.readInt();
        charge = in.readInt();
        cycle = in.readInt();
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
        dest.writeInt(batt);
        dest.writeInt(charge);
        dest.writeInt(cycle);
    }
}
