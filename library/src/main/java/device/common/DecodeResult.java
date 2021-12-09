package device.common;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Defines the
 */
public class DecodeResult implements Parcelable {

	/**
	 * Length of barcode data
	 */
    @UnsupportedAppUsage
	public int     decodeLength;

	/**
	 * Barcode data
	 */
    @UnsupportedAppUsage
	public byte[]  decodeValue;

	/**
	 * Symbology name of the barcode data
	 */
    @UnsupportedAppUsage
	public String  symName;

	/**
	 * Symbology ID of the barcode data
	 *
	 * @see  ScanConst.SymbologyID
	 */
    @UnsupportedAppUsage
	public byte    symId;

	/**
	 * Symbology type of the barcode data
	 */
    @UnsupportedAppUsage
	public int     symType;

	/**
	 * Unique characters defined by AIM(Association for Automatic Identification and Mobility) to identity symbologies.
	 * We call it 'AIM code identifier' or 'AIM ID' in a short.
	 * Please refer AIM code identifier table.
	 */
    @UnsupportedAppUsage
	public byte    letter;

	/**
	 * AIM ID's modifier
	 * Please refer AIM code identifier table.
	 */
    @UnsupportedAppUsage
	public byte    modifier;

	/**
	 * The decode time in milliseconds
	 */
    @UnsupportedAppUsage
	public int     decodeTimeMillisecond;

	public static final Parcelable.Creator<DecodeResult> CREATOR
	        = new Parcelable.Creator<DecodeResult>() {
        public DecodeResult createFromParcel(Parcel in) {
            return new DecodeResult(in);
        }

        public DecodeResult[] newArray(int size) {
            return new DecodeResult[size];
        }
    };

    /**
     * Constructor of DecodeResult.
     */
    @UnsupportedAppUsage
    public DecodeResult() {
    }

    private DecodeResult(Parcel in) {
        readFromParcel(in);
    }

    /**
     * Set the decode result from the data stored in the specified parcel.
     *
     * @param in  The parcel to read the decode result from
     *
     * @see   writeToParcel()
     */
    public void readFromParcel(Parcel in) {
		decodeLength = in.readInt();
		if (decodeLength > 0) {
			decodeValue = new byte[decodeLength];
			in.readByteArray(decodeValue);
		}
        symName = in.readString();
        symId = in.readByte();
        symType = in.readInt();
        letter = in.readByte();
        modifier = in.readByte();
        decodeTimeMillisecond = in.readInt();
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's marchalled representation.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Write this decode result to the specified parcel.
     *
     * @param dest   The parcel to write the decode result into
     * @param flags  Additional flags about how the object should be written. Unused.
     *
     * @see   readFromParcel()
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(decodeValue == null ? 0 : decodeValue.length);
		if (decodeValue != null) {
			dest.writeByteArray(decodeValue);
		}
        dest.writeString(symName);
        dest.writeByte(symId);
        dest.writeInt(symType);
        dest.writeByte(letter);
        dest.writeByte(modifier);
        dest.writeInt(decodeTimeMillisecond);
    }

	/**
	 * Converts the barcode data to UTF-8 string.
	 *
	 * @return  Converted string of the barcode data
	 */
	@Override
	public String toString() {
		return toString("UTF-8");
	}

	/**
	 * Converts the barcode data to a string with the specified charset.
	 *
	 * @param   charsetName  The name of the charset.
	 * @return  Converted string of the barcode data
	 */
    @UnsupportedAppUsage
	public String toString(String charsetName) {
		try {
			if (decodeValue != null) {
				String stringValue = new String(decodeValue, charsetName);
				return stringValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Clears all member valiables.
	 * We should call this one explictly before new decode action to avoid unexpected decode result.
	 */
    @UnsupportedAppUsage
	public DecodeResult recycle() {
		decodeLength = 0;
        decodeValue = null;
        symName = null;
        symId = 0;
        symType = 0;
        letter = 0;
        modifier = 0;
        decodeTimeMillisecond = 0;
		return this;
	}
}
