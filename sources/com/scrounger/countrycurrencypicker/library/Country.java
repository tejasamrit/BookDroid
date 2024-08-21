package com.scrounger.countrycurrencypicker.library;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class Country implements Parcelable {
    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
        public Country createFromParcel(Parcel parcel) {
            return new Country(parcel);
        }

        public Country[] newArray(int i) {
            return new Country[i];
        }
    };
    private String code;
    private Currency currency;
    private Integer flagId;
    private Locale locale;
    private String name;

    public int describeContents() {
        return 0;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Integer getFlagId() {
        return this.flagId;
    }

    public void setFlagId(Integer num) {
        this.flagId = num;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency2) {
        this.currency = currency2;
    }

    public Locale getLocale() {
        Locale locale2 = this.locale;
        return locale2 == null ? new Locale("", this.code) : locale2;
    }

    public Country(String str, String str2, Integer num) {
        this.code = str;
        this.name = str2;
        this.flagId = num;
    }

    public static Country getCountry(String str, Context context) {
        try {
            return new Country(str, new Locale("", str).getDisplayName(), Helper.getFlagDrawableId(str, context));
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static Country getCountryByName(final String str, Context context) {
        try {
            Locale locale2 = (Locale) Iterables.find(Arrays.asList(Locale.getAvailableLocales()), new Predicate<Locale>() {
                public boolean apply(Locale locale) {
                    return locale.getDisplayCountry(Locale.US).equals(str);
                }
            });
            return new Country(locale2.getCountry(), locale2.getDisplayName(), Helper.getFlagDrawableId(locale2.getCountry(), context));
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static Country getCountryWithCurrency(String str, Context context) {
        Currency currency2;
        Country country = getCountry(str, context);
        if (country == null || (currency2 = Currency.getCurrency(str, context)) == null) {
            return null;
        }
        country.setCurrency(currency2);
        return country;
    }

    public static ArrayList<Country> listAll(Context context, final String str) {
        ArrayList<Country> arrayList = new ArrayList<>();
        for (String country : Locale.getISOCountries()) {
            arrayList.add(getCountry(country, context));
        }
        sortList(arrayList);
        return (str == null || str.length() <= 0) ? arrayList : new ArrayList<>(Collections2.filter(arrayList, new Predicate<Country>() {
            public boolean apply(Country country) {
                return country.getName().toLowerCase().contains(str.toLowerCase());
            }
        }));
    }

    public static ArrayList<Country> listAllWithCurrencies(Context context, final String str) {
        ArrayList<Country> arrayList = new ArrayList<>();
        for (String countryWithCurrency : Locale.getISOCountries()) {
            Country countryWithCurrency2 = getCountryWithCurrency(countryWithCurrency, context);
            if (countryWithCurrency2 != null) {
                arrayList.add(countryWithCurrency2);
            }
        }
        sortList(arrayList);
        return (str == null || str.length() <= 0) ? arrayList : new ArrayList<>(Collections2.filter(arrayList, new Predicate<Country>() {
            public boolean apply(Country country) {
                return country.getName().toLowerCase().contains(str.toLowerCase()) || country.getCurrency().getName().toLowerCase().contains(str.toLowerCase()) || country.getCurrency().getSymbol().toLowerCase().contains(str.toLowerCase());
            }
        }));
    }

    private static void sortList(ArrayList<Country> arrayList) {
        Collections.sort(arrayList, new Comparator<Country>() {
            public int compare(Country country, Country country2) {
                return Helper.removeAccents(country.getName()).toLowerCase().compareTo(Helper.removeAccents(country2.getName()).toLowerCase());
            }
        });
    }

    public String toString() {
        return "Country{code='" + this.code + '\'' + ", name='" + this.name + '\'' + ", flagId=" + this.flagId + ", currency=" + this.currency + ", locale=" + this.locale + '}';
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.code);
        parcel.writeString(this.name);
        parcel.writeValue(this.flagId);
        parcel.writeParcelable(this.currency, i);
        parcel.writeSerializable(this.locale);
    }

    private Country(Parcel parcel) {
        this.code = parcel.readString();
        this.name = parcel.readString();
        this.flagId = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.currency = (Currency) parcel.readParcelable(Currency.class.getClassLoader());
        this.locale = (Locale) parcel.readSerializable();
    }
}
