package com.scrounger.countrycurrencypicker.library;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

public class Currency implements Parcelable {
    public static final Parcelable.Creator<Currency> CREATOR = new Parcelable.Creator<Currency>() {
        public Currency createFromParcel(Parcel parcel) {
            return new Currency(parcel);
        }

        public Currency[] newArray(int i) {
            return new Currency[i];
        }
    };
    private static ArrayList<Country> tmpCountries;
    private String code;
    private ArrayList<Country> countries;
    private ArrayList<String> countriesNames;
    private Integer flagId;
    private String name;
    private String symbol;

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

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public Integer getFlagId() {
        return this.flagId;
    }

    public ArrayList<Country> getCountries() {
        return this.countries;
    }

    public void setCountries(ArrayList<Country> arrayList) {
        this.countries = arrayList;
    }

    public ArrayList<String> getCountriesNames() {
        return this.countriesNames;
    }

    public void setCountriesNames(ArrayList<String> arrayList) {
        this.countriesNames = arrayList;
    }

    public Currency(String str, String str2, String str3, Integer num) {
        this.countriesNames = null;
        this.code = str;
        this.name = str2;
        this.symbol = str3;
        this.flagId = num;
    }

    public Currency(String str, String str2, String str3, Integer num, ArrayList<Country> arrayList) {
        this.countriesNames = null;
        this.code = str;
        this.name = str2;
        this.symbol = str3;
        this.flagId = num;
        this.countries = arrayList;
    }

    public static Currency getCurrency(String str, Context context) {
        java.util.Currency instance = java.util.Currency.getInstance(new Locale("", str));
        if (instance != null) {
            return new Currency(instance.getCurrencyCode(), instance.getDisplayName(), instance.getSymbol(), Helper.getFlagDrawableId(instance.getCurrencyCode(), context));
        }
        return null;
    }

    public static Currency getCurrency(java.util.Currency currency, Context context) {
        return new Currency(currency.getCurrencyCode(), currency.getDisplayName(), currency.getSymbol(), Helper.getFlagDrawableId(currency.getCurrencyCode(), context));
    }

    public static Currency getCurrencyWithCountries(java.util.Currency currency, Context context) {
        Currency currency2 = getCurrency(currency, context);
        if (tmpCountries == null) {
            tmpCountries = Country.listAllWithCurrencies(context, (String) null);
        }
        ArrayList arrayList = new ArrayList(Collections2.filter(tmpCountries, new Predicate<Country>(currency2) {
            final /* synthetic */ Currency val$myCurrency;

            {
                this.val$myCurrency = r1;
            }

            public boolean apply(Country country) {
                return country.getCurrency().getCode().equals(this.val$myCurrency.getCode());
            }
        }));
        if (arrayList.size() <= 0) {
            return null;
        }
        currency2.setCountries(arrayList);
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((Country) it.next()).getName());
        }
        currency2.setCountriesNames(arrayList2);
        return currency2;
    }

    public static ArrayList<Currency> listAll(Context context, final String str) {
        ArrayList<Currency> arrayList = new ArrayList<>();
        for (java.util.Currency currency : java.util.Currency.getAvailableCurrencies()) {
            arrayList.add(getCurrency(currency, context));
        }
        sortList(arrayList);
        return (str == null || str.length() <= 0) ? arrayList : new ArrayList<>(Collections2.filter(arrayList, new Predicate<Currency>() {
            public boolean apply(Currency currency) {
                return currency.getName().toLowerCase().contains(str.toLowerCase()) || currency.getSymbol().toLowerCase().contains(str.toLowerCase());
            }
        }));
    }

    public static ArrayList<Currency> listAllWithCountries(Context context, final String str) {
        ArrayList<Currency> arrayList = new ArrayList<>();
        for (java.util.Currency currencyWithCountries : java.util.Currency.getAvailableCurrencies()) {
            Currency currencyWithCountries2 = getCurrencyWithCountries(currencyWithCountries, context);
            if (currencyWithCountries2 != null) {
                arrayList.add(currencyWithCountries2);
            }
        }
        sortList(arrayList);
        return (str == null || str.length() <= 0) ? arrayList : new ArrayList<>(Collections2.filter(arrayList, new Predicate<Currency>() {
            public boolean apply(Currency currency) {
                return currency.getName().toLowerCase().contains(str.toLowerCase()) || currency.getSymbol().toLowerCase().contains(str.toLowerCase()) || Iterables.any(currency.getCountries(), new Predicate<Country>() {
                    public boolean apply(Country country) {
                        return country.getName().toLowerCase().contains(str.toLowerCase());
                    }
                });
            }
        }));
    }

    private static void sortList(ArrayList<Currency> arrayList) {
        Collections.sort(arrayList, new Comparator<Currency>() {
            public int compare(Currency currency, Currency currency2) {
                return Helper.removeAccents(currency.getName()).toLowerCase().compareTo(Helper.removeAccents(currency2.getName()).toLowerCase());
            }
        });
    }

    public String toString() {
        return "Currency{code='" + this.code + '\'' + ", name='" + this.name + '\'' + ", symbol='" + this.symbol + '\'' + ", flagId=" + this.flagId + ", countries=" + this.countries + ", countriesNames=" + this.countriesNames + '}';
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.code);
        parcel.writeString(this.name);
        parcel.writeString(this.symbol);
        parcel.writeValue(this.flagId);
        parcel.writeTypedList(this.countries);
        parcel.writeStringList(this.countriesNames);
    }

    private Currency(Parcel parcel) {
        this.countriesNames = null;
        this.code = parcel.readString();
        this.name = parcel.readString();
        this.symbol = parcel.readString();
        this.flagId = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.countries = parcel.createTypedArrayList(Country.CREATOR);
        this.countriesNames = parcel.createStringArrayList();
    }
}
