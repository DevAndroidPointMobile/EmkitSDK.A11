package device.common.rfid;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class ParamOfInvent implements Parcelable {

    @UnsupportedAppUsage
	public int session;
    @UnsupportedAppUsage
    public int q;
    @UnsupportedAppUsage
    public int inventoryFlag;

	public static final Parcelable.Creator<ParamOfInvent> CREATOR
	        = new Parcelable.Creator<ParamOfInvent>() {
        public ParamOfInvent createFromParcel(Parcel in) {
            return new ParamOfInvent(in);
        }

        public ParamOfInvent[] newArray(int size) {
            return new ParamOfInvent[size];
        }
    };

    /**
     * Constructor of ParamOfInvent.
     */
    @UnsupportedAppUsage
    public ParamOfInvent() {
    }

    private ParamOfInvent(Parcel in) {
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
        session = in.readInt();
        q = in.readInt();
        inventoryFlag = in.readInt();
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
        dest.writeInt(session);
        dest.writeInt(q);
        dest.writeInt(inventoryFlag);
    }
}
