package device.common;

import android.compat.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class OCRProperty implements Parcelable {

    /**
	* One or more template patterns for the OCR decode.
	*/
    @UnsupportedAppUsage
    public String template;

    /**
	* The set of characters matching group "g" in a template.
	*/
    @UnsupportedAppUsage
    public String groupG;

    /**
	* The set of characters matching group "h" in a template.
	*/
    @UnsupportedAppUsage
    public String groupH;

    /**
	* The legal characters for checksum computation in a decoded message.
	*/
    @UnsupportedAppUsage
    public String checkChar;

    @UnsupportedAppUsage
	public int quietZone;
    @UnsupportedAppUsage
	public int minQuietZone;
    @UnsupportedAppUsage
	public int maxQuietZone;
    @UnsupportedAppUsage
	public int checkDigitModulus;
    @UnsupportedAppUsage
	public int minCheckDigitModulus;
    @UnsupportedAppUsage
	public int maxCheckDigitModulus;

    public static final Parcelable.Creator<OCRProperty> CREATOR
            = new Parcelable.Creator<OCRProperty>() {
        public OCRProperty createFromParcel(Parcel in) {
            return new OCRProperty(in);
        }

        public OCRProperty[] newArray(int size) {
            return new OCRProperty[size];
        }
    };

    @UnsupportedAppUsage
    public OCRProperty() {
    }

    private OCRProperty(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        template = in.readString();
        groupG = in.readString();
        groupH = in.readString();
        checkChar = in.readString();
		quietZone = in.readInt();
		minQuietZone = in.readInt();
		maxQuietZone = in.readInt();
		checkDigitModulus = in.readInt();
		minCheckDigitModulus = in.readInt();
		maxCheckDigitModulus = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(template);
        dest.writeString(groupG);
        dest.writeString(groupH);
        dest.writeString(checkChar);
		dest.writeInt(quietZone);
		dest.writeInt(minQuietZone);
		dest.writeInt(maxQuietZone);
		dest.writeInt(checkDigitModulus);
		dest.writeInt(minCheckDigitModulus);
		dest.writeInt(maxCheckDigitModulus);
    }
}
