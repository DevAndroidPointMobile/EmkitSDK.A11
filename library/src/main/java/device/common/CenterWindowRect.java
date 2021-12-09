package device.common;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * Defines the centering window rectangle based on the scan engine.
 */
public class CenterWindowRect implements Parcelable {
    /**
     * Left coordinate of the rectangle.
     */
    @UnsupportedAppUsage
	public int left;

    /**
     * Top coordinate of the rectangle.
     */
    @UnsupportedAppUsage
	public int top;

    /**
     * Right coordinate of the rectangle.
     */
    @UnsupportedAppUsage
	public int right;

    /**
     * Bottom coordinate of the rectangle.
     */
    @UnsupportedAppUsage
	public int bottom;

	public static final Parcelable.Creator<CenterWindowRect> CREATOR
	        = new Parcelable.Creator<CenterWindowRect>() {
        public CenterWindowRect createFromParcel(Parcel in) {
            return new CenterWindowRect(in);
        }

        public CenterWindowRect[] newArray(int size) {
            return new CenterWindowRect[size];
        }
    };

    /**
     * Constructor of CenterWindowRect.
     */
    @UnsupportedAppUsage
    public CenterWindowRect() {
    }

    private CenterWindowRect(Parcel in) {
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
        left = in.readInt();
        top = in.readInt();
        right = in.readInt();
        bottom = in.readInt();
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
        dest.writeInt(left);
        dest.writeInt(top);
        dest.writeInt(right);
        dest.writeInt(bottom);
    }
}
