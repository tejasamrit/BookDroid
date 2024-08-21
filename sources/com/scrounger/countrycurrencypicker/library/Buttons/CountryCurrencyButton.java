package com.scrounger.countrycurrencypicker.library.Buttons;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import com.iceteck.silicompressorr.FileUtils;
import com.scrounger.countrycurrencypicker.library.C2249R;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.CountryCurrencyAdapter;
import com.scrounger.countrycurrencypicker.library.CountryCurrencyPicker;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;
import com.scrounger.countrycurrencypicker.library.PickerType;

public class CountryCurrencyButton extends AppCompatButton implements CountryCurrencyPickerListener {
    private final String logTAG = (CountryCurrencyAdapter.class.getName() + FileUtils.HIDDEN_PREFIX);
    private CountryCurrencyPickerListener mClickListener;
    private Country mCountry;
    private Boolean mShowCurrency = false;

    public Country getCountry() {
        return this.mCountry;
    }

    public void setCountry(Country country) {
        this.mCountry = country;
        invalidate();
    }

    public void setCountry(String str) {
        if (!isShowCurrency().booleanValue()) {
            this.mCountry = Country.getCountry(str, getContext());
        } else {
            this.mCountry = Country.getCountryWithCurrency(str, getContext());
        }
        invalidate();
    }

    public Boolean isShowCurrency() {
        return this.mShowCurrency;
    }

    public void setShowCurrency(Boolean bool) {
        this.mShowCurrency = bool;
    }

    public CountryCurrencyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setSaveEnabled(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2249R.styleable.countryCurrencyPicker);
        try {
            setCountry(obtainStyledAttributes.getString(C2249R.styleable.countryCurrencyPicker_country_code));
            setShowCurrency(Boolean.valueOf(obtainStyledAttributes.getBoolean(C2249R.styleable.countryCurrencyPicker_show_currency, false)));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mCountry != null) {
            setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), this.mCountry.getFlagId().intValue()), (Drawable) null, (Drawable) null, (Drawable) null);
            if (!isShowCurrency().booleanValue() || this.mCountry.getCurrency() == null) {
                setText(this.mCountry.getName());
                return;
            }
            setText(String.format("%s (%s)", new Object[]{this.mCountry.getName(), this.mCountry.getCurrency().getSymbol()}));
        }
    }

    public boolean performClick() {
        PickerType pickerType;
        if (!isShowCurrency().booleanValue()) {
            pickerType = PickerType.COUNTRY;
        } else {
            pickerType = PickerType.COUNTRYandCURRENCY;
        }
        CountryCurrencyPicker newInstance = CountryCurrencyPicker.newInstance(pickerType, this);
        newInstance.setDialogTitle(getContext().getString(C2249R.string.countryCurrencyPicker_select_country));
        newInstance.show(((AppCompatActivity) getContext()).getSupportFragmentManager(), CountryCurrencyPicker.DIALOG_NAME);
        return super.performClick();
    }

    public Parcelable onSaveInstanceState() {
        CountryCurrencyButtonSaveState countryCurrencyButtonSaveState = new CountryCurrencyButtonSaveState(super.onSaveInstanceState());
        countryCurrencyButtonSaveState.setCountryCode(this.mCountry.getCode());
        return countryCurrencyButtonSaveState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        CountryCurrencyButtonSaveState countryCurrencyButtonSaveState = (CountryCurrencyButtonSaveState) parcelable;
        super.onRestoreInstanceState(countryCurrencyButtonSaveState.getSuperState());
        setCountry(countryCurrencyButtonSaveState.getCountryCode());
    }

    public void setOnClickListener(CountryCurrencyPickerListener countryCurrencyPickerListener) {
        this.mClickListener = countryCurrencyPickerListener;
    }

    public void onSelectCountry(Country country) {
        this.mClickListener.onSelectCountry(country);
        setCountry(country);
    }

    public void onSelectCurrency(Currency currency) {
        this.mClickListener.onSelectCurrency(currency);
    }
}
