package com.scrounger.countrycurrencypicker.library;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.iceteck.silicompressorr.FileUtils;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;
import java.util.ArrayList;

public class CountryCurrencyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String logTAG = (CountryCurrencyAdapter.class.getName() + FileUtils.HIDDEN_PREFIX);
    private ViewHolderCountryItem mCountryHolder;
    private ArrayList<Country> mCountryList;
    private ViewHolderCurrencyItem mCurrencyHolder;
    private ArrayList<Currency> mCurrencyList;
    /* access modifiers changed from: private */
    public Dialog mDialog;
    /* access modifiers changed from: private */
    public CountryCurrencyPickerListener mListener;
    /* access modifiers changed from: private */
    public PickerType mPickerType;
    /* access modifiers changed from: private */
    public Boolean mShowCodeOrCurrency;
    /* access modifiers changed from: private */
    public Boolean mShowSubTitle;

    public CountryCurrencyAdapter(ArrayList<Country> arrayList, ArrayList<Currency> arrayList2, Boolean bool, Boolean bool2, PickerType pickerType, CountryCurrencyPickerListener countryCurrencyPickerListener, Dialog dialog) {
        this.mCountryList = arrayList;
        this.mCurrencyList = arrayList2;
        this.mShowSubTitle = bool;
        this.mShowCodeOrCurrency = bool2;
        this.mPickerType = pickerType;
        this.mListener = countryCurrencyPickerListener;
        this.mDialog = dialog;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.mCountryList != null) {
            return new ViewHolderCountryItem(LayoutInflater.from(viewGroup.getContext()).inflate(C2249R.layout.countrycurrencypicker_row, viewGroup, false));
        }
        return new ViewHolderCurrencyItem(LayoutInflater.from(viewGroup.getContext()).inflate(C2249R.layout.countrycurrencypicker_row, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ArrayList<Country> arrayList = this.mCountryList;
        if (arrayList != null) {
            ViewHolderCountryItem viewHolderCountryItem = (ViewHolderCountryItem) viewHolder;
            this.mCountryHolder = viewHolderCountryItem;
            viewHolderCountryItem.setItem(arrayList.get(i));
            return;
        }
        ViewHolderCurrencyItem viewHolderCurrencyItem = (ViewHolderCurrencyItem) viewHolder;
        this.mCurrencyHolder = viewHolderCurrencyItem;
        viewHolderCurrencyItem.setItem(this.mCurrencyList.get(i));
    }

    public int getItemCount() {
        ArrayList<Country> arrayList = this.mCountryList;
        if (arrayList != null) {
            return arrayList.size();
        }
        return this.mCurrencyList.size();
    }

    public class ViewHolderCountryItem extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView flag;
        private final String logTAG = (ViewHolderCountryItem.class.getName() + FileUtils.HIDDEN_PREFIX);
        private Country myCountry;
        private TextView txtCodeOrSymbol;
        private TextView txtSubTitle;
        private TextView txtTitle;

        public ViewHolderCountryItem(View view) {
            super(view);
            this.itemView.setOnClickListener(this);
            this.flag = (ImageView) this.itemView.findViewById(C2249R.C2252id.flag);
            this.txtTitle = (TextView) this.itemView.findViewById(C2249R.C2252id.title);
            this.txtSubTitle = (TextView) this.itemView.findViewById(C2249R.C2252id.subtitle);
            this.txtCodeOrSymbol = (TextView) this.itemView.findViewById(C2249R.C2252id.code_or_symbol);
        }

        public void setItem(Country country) {
            this.myCountry = country;
            if (country != null) {
                if (country.getFlagId().intValue() != 0) {
                    this.flag.setImageDrawable(ContextCompat.getDrawable(this.itemView.getContext(), this.myCountry.getFlagId().intValue()));
                } else {
                    this.flag.setImageDrawable((Drawable) null);
                }
                this.txtTitle.setText(this.myCountry.getName());
                if (!CountryCurrencyAdapter.this.mShowSubTitle.booleanValue()) {
                    this.txtSubTitle.setVisibility(8);
                }
                if (!CountryCurrencyAdapter.this.mShowCodeOrCurrency.booleanValue()) {
                    this.txtCodeOrSymbol.setVisibility(8);
                }
                if (CountryCurrencyAdapter.this.mPickerType == PickerType.COUNTRY) {
                    this.txtCodeOrSymbol.setText(this.myCountry.getCode());
                    this.txtSubTitle.setVisibility(8);
                } else if (CountryCurrencyAdapter.this.mPickerType == PickerType.COUNTRYandCURRENCY) {
                    this.txtSubTitle.setText(this.myCountry.getCurrency().getName());
                    this.txtCodeOrSymbol.setText(this.myCountry.getCurrency().getSymbol());
                }
            }
        }

        public void onClick(View view) {
            CountryCurrencyAdapter.this.mListener.onSelectCountry(this.myCountry);
            if (CountryCurrencyAdapter.this.mDialog != null) {
                CountryCurrencyAdapter.this.mDialog.dismiss();
            }
        }
    }

    public class ViewHolderCurrencyItem extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView flag = ((ImageView) this.itemView.findViewById(C2249R.C2252id.flag));
        private Currency myCurrency;
        private TextView txtCodeOrSymbol = ((TextView) this.itemView.findViewById(C2249R.C2252id.code_or_symbol));
        private TextView txtSubTitle = ((TextView) this.itemView.findViewById(C2249R.C2252id.subtitle));
        private TextView txtTitle = ((TextView) this.itemView.findViewById(C2249R.C2252id.title));

        public ViewHolderCurrencyItem(View view) {
            super(view);
            this.itemView.setOnClickListener(this);
        }

        public void setItem(Currency currency) {
            this.myCurrency = currency;
            if (currency != null) {
                if (currency.getFlagId().intValue() != 0) {
                    this.flag.setImageDrawable(ContextCompat.getDrawable(this.itemView.getContext(), this.myCurrency.getFlagId().intValue()));
                } else {
                    this.flag.setImageDrawable((Drawable) null);
                }
                this.txtTitle.setText(this.myCurrency.getName());
                this.txtCodeOrSymbol.setText(this.myCurrency.getSymbol());
                if (!CountryCurrencyAdapter.this.mShowSubTitle.booleanValue()) {
                    this.txtSubTitle.setVisibility(8);
                }
                if (!CountryCurrencyAdapter.this.mShowCodeOrCurrency.booleanValue()) {
                    this.txtCodeOrSymbol.setVisibility(8);
                }
                if (CountryCurrencyAdapter.this.mPickerType == PickerType.CURRENCY) {
                    this.txtSubTitle.setVisibility(8);
                } else if (CountryCurrencyAdapter.this.mPickerType == PickerType.CURRENCYandCOUNTRY) {
                    this.txtSubTitle.setText(TextUtils.join(", ", this.myCurrency.getCountriesNames()));
                }
            }
        }

        public void onClick(View view) {
            CountryCurrencyAdapter.this.mListener.onSelectCurrency(this.myCurrency);
            if (CountryCurrencyAdapter.this.mDialog != null) {
                CountryCurrencyAdapter.this.mDialog.dismiss();
            }
        }
    }
}
