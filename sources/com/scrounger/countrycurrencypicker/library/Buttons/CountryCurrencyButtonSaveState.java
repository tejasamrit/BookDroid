package com.scrounger.countrycurrencypicker.library.Buttons;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class CountryCurrencyButtonSaveState extends View.BaseSavedState {
    public static final Parcelable.Creator<CountryCurrencyButtonSaveState> CREATOR = new Parcelable.Creator<CountryCurrencyButtonSaveState>() {
        public CountryCurrencyButtonSaveState createFromParcel(Parcel parcel) {
            return new CountryCurrencyButtonSaveState(parcel);
        }

        public CountryCurrencyButtonSaveState[] newArray(int i) {
            return new CountryCurrencyButtonSaveState[i];
        }
    };
    private String countryCode;

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public CountryCurrencyButtonSaveState(Parcel parcel) {
        super(parcel);
        this.countryCode = parcel.readString();
    }

    public CountryCurrencyButtonSaveState(Parcelable parcelable) {
        super(parcelable);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.countryCode);
    }
}
