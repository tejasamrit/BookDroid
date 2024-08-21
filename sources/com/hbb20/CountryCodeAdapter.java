package com.hbb20;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.futuremind.recyclerviewfastscroll.SectionTitleProvider;
import java.util.ArrayList;
import java.util.List;

class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeViewHolder> implements SectionTitleProvider {
    CountryCodePicker codePicker;
    Context context;
    Dialog dialog;
    EditText editText_search;
    List<CCPCountry> filteredCountries = null;
    ImageView imgClearQuery;
    LayoutInflater inflater;
    List<CCPCountry> masterCountries = null;
    int preferredCountriesCount = 0;
    RelativeLayout rlQueryHolder;
    TextView textView_noResult;

    CountryCodeAdapter(Context context2, List<CCPCountry> list, CountryCodePicker countryCodePicker, RelativeLayout relativeLayout, EditText editText, TextView textView, Dialog dialog2, ImageView imageView) {
        this.context = context2;
        this.masterCountries = list;
        this.codePicker = countryCodePicker;
        this.dialog = dialog2;
        this.textView_noResult = textView;
        this.editText_search = editText;
        this.rlQueryHolder = relativeLayout;
        this.imgClearQuery = imageView;
        this.inflater = LayoutInflater.from(context2);
        this.filteredCountries = getFilteredCountries("");
        setSearchBar();
    }

    private void setSearchBar() {
        if (this.codePicker.isSearchAllowed()) {
            this.imgClearQuery.setVisibility(8);
            setTextWatcher();
            setQueryClearListener();
            return;
        }
        this.rlQueryHolder.setVisibility(8);
    }

    private void setQueryClearListener() {
        this.imgClearQuery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CountryCodeAdapter.this.editText_search.setText("");
            }
        });
    }

    private void setTextWatcher() {
        EditText editText = this.editText_search;
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    CountryCodeAdapter.this.applyQuery(charSequence.toString());
                    if (charSequence.toString().trim().equals("")) {
                        CountryCodeAdapter.this.imgClearQuery.setVisibility(8);
                    } else {
                        CountryCodeAdapter.this.imgClearQuery.setVisibility(0);
                    }
                }
            });
            this.editText_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i != 3) {
                        return false;
                    }
                    ((InputMethodManager) CountryCodeAdapter.this.context.getSystemService("input_method")).hideSoftInputFromWindow(CountryCodeAdapter.this.editText_search.getWindowToken(), 0);
                    return true;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void applyQuery(String str) {
        this.textView_noResult.setVisibility(8);
        String lowerCase = str.toLowerCase();
        if (lowerCase.length() > 0 && lowerCase.charAt(0) == '+') {
            lowerCase = lowerCase.substring(1);
        }
        List<CCPCountry> filteredCountries2 = getFilteredCountries(lowerCase);
        this.filteredCountries = filteredCountries2;
        if (filteredCountries2.size() == 0) {
            this.textView_noResult.setVisibility(0);
        }
        notifyDataSetChanged();
    }

    private List<CCPCountry> getFilteredCountries(String str) {
        ArrayList arrayList = new ArrayList();
        this.preferredCountriesCount = 0;
        if (this.codePicker.preferredCountries != null && this.codePicker.preferredCountries.size() > 0) {
            for (CCPCountry next : this.codePicker.preferredCountries) {
                if (next.isEligibleForQuery(str)) {
                    arrayList.add(next);
                    this.preferredCountriesCount++;
                }
            }
            if (arrayList.size() > 0) {
                arrayList.add((Object) null);
                this.preferredCountriesCount++;
            }
        }
        for (CCPCountry next2 : this.masterCountries) {
            if (next2.isEligibleForQuery(str)) {
                arrayList.add(next2);
            }
        }
        return arrayList;
    }

    public CountryCodeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CountryCodeViewHolder(this.inflater.inflate(C2219R.layout.layout_recycler_country_tile, viewGroup, false));
    }

    public void onBindViewHolder(CountryCodeViewHolder countryCodeViewHolder, final int i) {
        countryCodeViewHolder.setCountry(this.filteredCountries.get(i));
        if (this.filteredCountries.size() <= i || this.filteredCountries.get(i) == null) {
            countryCodeViewHolder.getMainView().setOnClickListener((View.OnClickListener) null);
        } else {
            countryCodeViewHolder.getMainView().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (CountryCodeAdapter.this.filteredCountries != null && CountryCodeAdapter.this.filteredCountries.size() > i) {
                        CountryCodeAdapter.this.codePicker.onUserTappedCountry(CountryCodeAdapter.this.filteredCountries.get(i));
                    }
                    if (view != null && CountryCodeAdapter.this.filteredCountries != null && CountryCodeAdapter.this.filteredCountries.size() > i && CountryCodeAdapter.this.filteredCountries.get(i) != null) {
                        ((InputMethodManager) CountryCodeAdapter.this.context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                        CountryCodeAdapter.this.dialog.dismiss();
                    }
                }
            });
        }
    }

    public int getItemCount() {
        return this.filteredCountries.size();
    }

    public String getSectionTitle(int i) {
        CCPCountry cCPCountry = this.filteredCountries.get(i);
        if (this.preferredCountriesCount > i) {
            return "★";
        }
        return cCPCountry != null ? cCPCountry.getName().substring(0, 1) : "☺";
    }

    class CountryCodeViewHolder extends RecyclerView.ViewHolder {
        View divider = this.relativeLayout_main.findViewById(C2219R.C2222id.preferenceDivider);
        ImageView imageViewFlag = ((ImageView) this.relativeLayout_main.findViewById(C2219R.C2222id.image_flag));
        LinearLayout linearFlagHolder = ((LinearLayout) this.relativeLayout_main.findViewById(C2219R.C2222id.linear_flag_holder));
        RelativeLayout relativeLayout_main;
        TextView textView_code = ((TextView) this.relativeLayout_main.findViewById(C2219R.C2222id.textView_code));
        TextView textView_name;

        public CountryCodeViewHolder(View view) {
            super(view);
            RelativeLayout relativeLayout = (RelativeLayout) view;
            this.relativeLayout_main = relativeLayout;
            this.textView_name = (TextView) relativeLayout.findViewById(C2219R.C2222id.textView_countryName);
            if (CountryCodeAdapter.this.codePicker.getDialogTextColor() != 0) {
                this.textView_name.setTextColor(CountryCodeAdapter.this.codePicker.getDialogTextColor());
                this.textView_code.setTextColor(CountryCodeAdapter.this.codePicker.getDialogTextColor());
                this.divider.setBackgroundColor(CountryCodeAdapter.this.codePicker.getDialogTextColor());
            }
            try {
                if (CountryCodeAdapter.this.codePicker.getDialogTypeFace() == null) {
                    return;
                }
                if (CountryCodeAdapter.this.codePicker.getDialogTypeFaceStyle() != -99) {
                    this.textView_code.setTypeface(CountryCodeAdapter.this.codePicker.getDialogTypeFace(), CountryCodeAdapter.this.codePicker.getDialogTypeFaceStyle());
                    this.textView_name.setTypeface(CountryCodeAdapter.this.codePicker.getDialogTypeFace(), CountryCodeAdapter.this.codePicker.getDialogTypeFaceStyle());
                    return;
                }
                this.textView_code.setTypeface(CountryCodeAdapter.this.codePicker.getDialogTypeFace());
                this.textView_name.setTypeface(CountryCodeAdapter.this.codePicker.getDialogTypeFace());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void setCountry(CCPCountry cCPCountry) {
            if (cCPCountry != null) {
                this.divider.setVisibility(8);
                this.textView_name.setVisibility(0);
                this.textView_code.setVisibility(0);
                if (CountryCodeAdapter.this.codePicker.isCcpDialogShowPhoneCode()) {
                    this.textView_code.setVisibility(0);
                } else {
                    this.textView_code.setVisibility(8);
                }
                String str = "";
                if (CountryCodeAdapter.this.codePicker.getCcpDialogShowFlag() && CountryCodeAdapter.this.codePicker.ccpUseEmoji) {
                    str = str + CCPCountry.getFlagEmoji(cCPCountry) + "   ";
                }
                String str2 = str + cCPCountry.getName();
                if (CountryCodeAdapter.this.codePicker.getCcpDialogShowNameCode()) {
                    str2 = str2 + " (" + cCPCountry.getNameCode().toUpperCase() + ")";
                }
                this.textView_name.setText(str2);
                this.textView_code.setText("+" + cCPCountry.getPhoneCode());
                if (!CountryCodeAdapter.this.codePicker.getCcpDialogShowFlag() || CountryCodeAdapter.this.codePicker.ccpUseEmoji) {
                    this.linearFlagHolder.setVisibility(8);
                    return;
                }
                this.linearFlagHolder.setVisibility(0);
                this.imageViewFlag.setImageResource(cCPCountry.getFlagID());
                return;
            }
            this.divider.setVisibility(0);
            this.textView_name.setVisibility(8);
            this.textView_code.setVisibility(8);
            this.linearFlagHolder.setVisibility(8);
        }

        public RelativeLayout getMainView() {
            return this.relativeLayout_main;
        }
    }
}
