package device.common;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class StyleConfig implements Parcelable {
    /**
     * Style order (priority). (0 base)
     */
    @UnsupportedAppUsage
	public int order;

    /**
     * Total count of actions.
     */
    @UnsupportedAppUsage
	public int totalCountAction;

    /**
     * Style name.
     */
    @UnsupportedAppUsage
	public String styleName;

	public static final Parcelable.Creator<StyleConfig> CREATOR
	        = new Parcelable.Creator<StyleConfig>() {
        public StyleConfig createFromParcel(Parcel in) {
            return new StyleConfig(in);
        }

        public StyleConfig[] newArray(int size) {
            return new StyleConfig[size];
        }
    };

    /**
     * Constructor of StyleConfig.
     */
    @UnsupportedAppUsage
    public StyleConfig() {
    }

    private StyleConfig(Parcel in) {
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
        order = in.readInt();
        totalCountAction = in.readInt();
        styleName = in.readString();
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
        dest.writeInt(order);
        dest.writeInt(totalCountAction);
        dest.writeString(styleName);
    }
}
