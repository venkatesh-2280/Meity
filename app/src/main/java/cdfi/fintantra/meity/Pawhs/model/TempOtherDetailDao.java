package cdfi.fintantra.meity.Pawhs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TempOtherDetailDao implements Parcelable {

    private String name;
    private String amount;


    public TempOtherDetailDao(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    protected TempOtherDetailDao(Parcel in) {
        name = in.readString();
        amount = in.readString();
    }

    public static final Creator<TempOtherDetailDao> CREATOR = new Creator<TempOtherDetailDao>() {
        @Override
        public TempOtherDetailDao createFromParcel(Parcel in) {
            return new TempOtherDetailDao(in);
        }

        @Override
        public TempOtherDetailDao[] newArray(int size) {
            return new TempOtherDetailDao[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(amount);
    }
}
