package com.scrounger.countrycurrencypicker.library.Listener;

import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Currency;
import java.io.Serializable;

public interface CountryCurrencyPickerListener extends Serializable {
    void onSelectCountry(Country country);

    void onSelectCurrency(Currency currency);
}
