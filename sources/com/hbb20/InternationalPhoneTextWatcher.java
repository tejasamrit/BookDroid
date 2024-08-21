package com.hbb20;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import p012io.michaelrocks.libphonenumber.android.AsYouTypeFormatter;
import p012io.michaelrocks.libphonenumber.android.PhoneNumberUtil;

public class InternationalPhoneTextWatcher implements TextWatcher {
    private static final String TAG = "Int'l Phone TextWatcher";
    private String countryNameCode;
    private int countryPhoneCode;
    private boolean internationalOnly;
    Editable lastFormatted;
    private AsYouTypeFormatter mFormatter;
    private boolean mSelfChange;
    private boolean mStopFormatting;
    private boolean needUpdateForCountryChange;
    PhoneNumberUtil phoneNumberUtil;

    public InternationalPhoneTextWatcher(Context context, String str, int i) {
        this(context, str, i, true);
    }

    public InternationalPhoneTextWatcher(Context context, String str, int i, boolean z) {
        this.mSelfChange = false;
        this.lastFormatted = null;
        this.needUpdateForCountryChange = false;
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException();
        }
        this.phoneNumberUtil = PhoneNumberUtil.createInstance(context);
        updateCountry(str, i);
        this.internationalOnly = z;
    }

    public void updateCountry(String str, int i) {
        this.countryNameCode = str;
        this.countryPhoneCode = i;
        AsYouTypeFormatter asYouTypeFormatter = this.phoneNumberUtil.getAsYouTypeFormatter(str);
        this.mFormatter = asYouTypeFormatter;
        asYouTypeFormatter.clear();
        Editable editable = this.lastFormatted;
        if (editable != null) {
            this.needUpdateForCountryChange = true;
            String normalizeDigitsOnly = PhoneNumberUtil.normalizeDigitsOnly(editable);
            Editable editable2 = this.lastFormatted;
            editable2.replace(0, editable2.length(), normalizeDigitsOnly, 0, normalizeDigitsOnly.length());
            this.needUpdateForCountryChange = false;
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!this.mSelfChange && !this.mStopFormatting && i2 > 0 && hasSeparator(charSequence, i, i2) && !this.needUpdateForCountryChange) {
            stopFormatting();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!this.mSelfChange && !this.mStopFormatting && i3 > 0 && hasSeparator(charSequence, i, i3)) {
            stopFormatting();
        }
    }

    public synchronized void afterTextChanged(Editable editable) {
        boolean z = true;
        if (this.mStopFormatting) {
            if (editable.length() == 0) {
                z = false;
            }
            this.mStopFormatting = z;
            return;
        } else if (!this.mSelfChange) {
            int selectionEnd = Selection.getSelectionEnd(editable);
            boolean z2 = selectionEnd == editable.length();
            String reformat = reformat(editable);
            if (!reformat.equals(editable.toString())) {
                if (!z2) {
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        if (i >= editable.length()) {
                            break;
                        } else if (i >= selectionEnd) {
                            break;
                        } else {
                            if (PhoneNumberUtils.isNonSeparator(editable.charAt(i))) {
                                i2++;
                            }
                            i++;
                        }
                    }
                    selectionEnd = 0;
                    int i3 = 0;
                    while (true) {
                        if (selectionEnd >= reformat.length()) {
                            selectionEnd = 0;
                            break;
                        } else if (i3 == i2) {
                            break;
                        } else {
                            if (PhoneNumberUtils.isNonSeparator(reformat.charAt(selectionEnd))) {
                                i3++;
                            }
                            selectionEnd++;
                        }
                    }
                } else {
                    selectionEnd = reformat.length();
                }
            }
            if (!z2) {
                while (true) {
                    int i4 = selectionEnd - 1;
                    if (i4 <= 0 || PhoneNumberUtils.isNonSeparator(reformat.charAt(i4))) {
                        break;
                    }
                    selectionEnd--;
                }
            }
            if (reformat != null) {
                try {
                    this.mSelfChange = true;
                    editable.replace(0, editable.length(), reformat, 0, reformat.length());
                    this.mSelfChange = false;
                    this.lastFormatted = editable;
                    Selection.setSelection(editable, selectionEnd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            return;
        }
        return;
    }

    private String reformat(CharSequence charSequence) {
        this.mFormatter.clear();
        String str = "+" + this.countryPhoneCode;
        if (this.internationalOnly || (charSequence.length() > 0 && charSequence.charAt(0) != '0')) {
            charSequence = str + charSequence;
        }
        int length = charSequence.length();
        String str2 = "";
        char c = 0;
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if (PhoneNumberUtils.isNonSeparator(charAt)) {
                if (c != 0) {
                    str2 = this.mFormatter.inputDigit(c);
                }
                c = charAt;
            }
        }
        if (c != 0) {
            str2 = this.mFormatter.inputDigit(c);
        }
        String trim = str2.trim();
        if (this.internationalOnly || charSequence.length() == 0 || charSequence.charAt(0) != '0') {
            if (trim.length() <= str.length()) {
                trim = "";
            } else if (trim.charAt(str.length()) == ' ') {
                trim = trim.substring(str.length() + 1);
            } else {
                trim = trim.substring(str.length());
            }
        }
        if (TextUtils.isEmpty(trim)) {
            return "";
        }
        return trim;
    }

    private void stopFormatting() {
        this.mStopFormatting = true;
        this.mFormatter.clear();
    }

    private boolean hasSeparator(CharSequence charSequence, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            if (!PhoneNumberUtils.isNonSeparator(charSequence.charAt(i3))) {
                return true;
            }
        }
        return false;
    }
}
