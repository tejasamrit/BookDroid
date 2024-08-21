package com.scrounger.countrycurrencypicker.library;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.iceteck.silicompressorr.FileUtils;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;
import java.util.ArrayList;

public class CountryCurrencyPicker extends DialogFragment {
    private static final String Bundle_Listener;
    private static final String Bundle_PickerType;
    public static final String DIALOG_NAME;
    private static final String logTAG;
    private String dialogTitle;
    private FilterListAsync filterListAsync;
    private CountryCurrencyAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private CountryCurrencyPickerListener mListener;
    /* access modifiers changed from: private */
    public PickerType mPickerType;
    private RecyclerView mRecyclerView;
    private Boolean mShowCodeOrCurrency = true;
    private Boolean mShowSubTitle = true;
    private View myView;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    /* access modifiers changed from: private */
    public EditText txtSearch;

    static {
        Class<CountryCurrencyPicker> cls = CountryCurrencyPicker.class;
        logTAG = cls.getName() + FileUtils.HIDDEN_PREFIX;
        DIALOG_NAME = cls.getName();
        Bundle_PickerType = cls.getName() + ".pickerType";
        Bundle_Listener = cls.getName() + ".listener";
    }

    public void setDialogTitle(String str) {
        this.dialogTitle = str;
    }

    public static CountryCurrencyPicker newInstance(PickerType pickerType, CountryCurrencyPickerListener countryCurrencyPickerListener) {
        CountryCurrencyPicker countryCurrencyPicker = new CountryCurrencyPicker();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Bundle_PickerType, pickerType);
        bundle.putSerializable(Bundle_Listener, countryCurrencyPickerListener);
        countryCurrencyPicker.setArguments(bundle);
        return countryCurrencyPicker;
    }

    public static CountryCurrencyPicker newInstance(PickerType pickerType, Boolean bool, Boolean bool2, CountryCurrencyPickerListener countryCurrencyPickerListener) {
        CountryCurrencyPicker newInstance = newInstance(pickerType, countryCurrencyPickerListener);
        newInstance.mShowSubTitle = bool;
        newInstance.mShowCodeOrCurrency = bool2;
        return newInstance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.dialogTitle != null) {
            setStyle(0, C2249R.style.countryCurrencyPicker_dialog);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.myView = layoutInflater.inflate(C2249R.layout.countrycurrencypicker_fragment, viewGroup, false);
        this.mListener = (CountryCurrencyPickerListener) getArguments().getSerializable(Bundle_Listener);
        this.mPickerType = (PickerType) getArguments().getSerializable(Bundle_PickerType);
        if (!(getDialog() == null || this.dialogTitle == null)) {
            getDialog().setTitle(this.dialogTitle);
        }
        return this.myView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.txtSearch = (EditText) this.myView.findViewById(C2249R.C2252id.txt_search);
        this.progressBar = (ProgressBar) this.myView.findViewById(C2249R.C2252id.progressbar);
        this.mRecyclerView = (RecyclerView) this.myView.findViewById(C2249R.C2252id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.mLinearLayoutManager = linearLayoutManager;
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        EventsListener();
    }

    public void onStart() {
        super.onStart();
        getData((String) null);
    }

    private void EventsListener() {
        this.mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ((InputMethodManager) CountryCurrencyPicker.this.getContext().getSystemService("input_method")).hideSoftInputFromWindow(CountryCurrencyPicker.this.txtSearch.getWindowToken(), 0);
                return false;
            }
        });
        this.txtSearch.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (CountryCurrencyPicker.this.txtSearch.hasFocus()) {
                    CountryCurrencyPicker.this.getData(editable.toString());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void getData(String str) {
        FilterListAsync filterListAsync2 = this.filterListAsync;
        if (filterListAsync2 == null) {
            this.filterListAsync = (FilterListAsync) new FilterListAsync().execute(new String[]{str});
            return;
        }
        filterListAsync2.cancel(true);
        this.filterListAsync = (FilterListAsync) new FilterListAsync().execute(new String[]{str});
    }

    /* access modifiers changed from: private */
    public void setRecyclerView(ArrayList<Country> arrayList, ArrayList<Currency> arrayList2) {
        if (arrayList == null && arrayList2 == null) {
            this.mAdapter = new CountryCurrencyAdapter(new ArrayList(), new ArrayList(), this.mShowSubTitle, this.mShowCodeOrCurrency, this.mPickerType, this.mListener, getDialog());
        } else {
            this.mAdapter = new CountryCurrencyAdapter(arrayList, arrayList2, this.mShowSubTitle, this.mShowCodeOrCurrency, this.mPickerType, this.mListener, getDialog());
        }
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    private class FilterListAsync extends AsyncTask<String, Void, Void> {
        private ArrayList<Country> mCountryList;
        private ArrayList<Currency> mCurrencyList;

        private FilterListAsync() {
            this.mCountryList = null;
            this.mCurrencyList = null;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            CountryCurrencyPicker.this.progressBar.setVisibility(0);
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(String... strArr) {
            for (String str : strArr) {
                if (CountryCurrencyPicker.this.mPickerType == PickerType.COUNTRY) {
                    this.mCountryList = Country.listAll(CountryCurrencyPicker.this.getActivity(), str);
                } else if (CountryCurrencyPicker.this.mPickerType == PickerType.COUNTRYandCURRENCY) {
                    this.mCountryList = Country.listAllWithCurrencies(CountryCurrencyPicker.this.getActivity(), str);
                } else if (CountryCurrencyPicker.this.mPickerType == PickerType.CURRENCY) {
                    this.mCurrencyList = Currency.listAll(CountryCurrencyPicker.this.getActivity(), str);
                } else if (CountryCurrencyPicker.this.mPickerType == PickerType.CURRENCYandCOUNTRY) {
                    this.mCurrencyList = Currency.listAllWithCountries(CountryCurrencyPicker.this.getActivity(), str);
                }
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            CountryCurrencyPicker.this.setRecyclerView(this.mCountryList, this.mCurrencyList);
            CountryCurrencyPicker.this.progressBar.setVisibility(8);
        }
    }
}
