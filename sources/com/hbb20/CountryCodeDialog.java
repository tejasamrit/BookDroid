package com.hbb20;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.futuremind.recyclerviewfastscroll.FastScroller;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

class CountryCodeDialog {
    static Context context;
    static Dialog dialog;
    private static final Field sCursorDrawableField;
    private static final Field sCursorDrawableResourceField;
    private static final Field sEditorField;

    CountryCodeDialog() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004c  */
    static {
        /*
            r0 = 1
            r1 = 0
            java.lang.Class<android.widget.TextView> r2 = android.widget.TextView.class
            java.lang.String r3 = "mCursorDrawableRes"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch:{ Exception -> 0x003d }
            r2.setAccessible(r0)     // Catch:{ Exception -> 0x003b }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x003b }
            r4 = 16
            if (r3 >= r4) goto L_0x0017
            java.lang.Class<android.widget.TextView> r3 = android.widget.TextView.class
            r4 = r1
            goto L_0x0029
        L_0x0017:
            java.lang.Class<android.widget.TextView> r3 = android.widget.TextView.class
            java.lang.String r4 = "mEditor"
            java.lang.reflect.Field r3 = r3.getDeclaredField(r4)     // Catch:{ Exception -> 0x003b }
            r3.setAccessible(r0)     // Catch:{ Exception -> 0x0039 }
            java.lang.Class r4 = r3.getType()     // Catch:{ Exception -> 0x0039 }
            r6 = r4
            r4 = r3
            r3 = r6
        L_0x0029:
            java.lang.String r5 = "mCursorDrawable"
            java.lang.reflect.Field r3 = r3.getDeclaredField(r5)     // Catch:{ Exception -> 0x0038 }
            r3.setAccessible(r0)     // Catch:{ Exception -> 0x0034 }
            r0 = 0
            goto L_0x0043
        L_0x0034:
            r6 = r4
            r4 = r3
            r3 = r6
            goto L_0x0040
        L_0x0038:
            r3 = r4
        L_0x0039:
            r4 = r1
            goto L_0x0040
        L_0x003b:
            r3 = r1
            goto L_0x003f
        L_0x003d:
            r2 = r1
            r3 = r2
        L_0x003f:
            r4 = r3
        L_0x0040:
            r6 = r4
            r4 = r3
            r3 = r6
        L_0x0043:
            if (r0 == 0) goto L_0x004c
            sEditorField = r1
            sCursorDrawableField = r1
            sCursorDrawableResourceField = r1
            goto L_0x0052
        L_0x004c:
            sEditorField = r4
            sCursorDrawableField = r3
            sCursorDrawableResourceField = r2
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hbb20.CountryCodeDialog.<clinit>():void");
    }

    public static void openCountryCodeDialog(CountryCodePicker countryCodePicker) {
        openCountryCodeDialog(countryCodePicker, (String) null);
    }

    public static void openCountryCodeDialog(CountryCodePicker countryCodePicker, String str) {
        boolean z;
        final CountryCodePicker countryCodePicker2 = countryCodePicker;
        String str2 = str;
        context = countryCodePicker.getContext();
        dialog = new Dialog(context);
        countryCodePicker.refreshCustomMasterList();
        countryCodePicker.refreshPreferredCountries();
        List<CCPCountry> customMasterCountryList = CCPCountry.getCustomMasterCountryList(context, countryCodePicker2);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setContentView(C2219R.layout.layout_picker_dialog);
        if (!countryCodePicker.isSearchAllowed() || !countryCodePicker.isDialogKeyboardAutoPopup()) {
            dialog.getWindow().setSoftInputMode(2);
        } else {
            dialog.getWindow().setSoftInputMode(4);
        }
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(C2219R.C2222id.recycler_countryDialog);
        TextView textView = (TextView) dialog.findViewById(C2219R.C2222id.textView_title);
        RelativeLayout relativeLayout = (RelativeLayout) dialog.findViewById(C2219R.C2222id.rl_query_holder);
        ImageView imageView = (ImageView) dialog.findViewById(C2219R.C2222id.img_clear_query);
        EditText editText = (EditText) dialog.findViewById(C2219R.C2222id.editText_search);
        TextView textView2 = (TextView) dialog.findViewById(C2219R.C2222id.textView_noresult);
        RelativeLayout relativeLayout2 = (RelativeLayout) dialog.findViewById(C2219R.C2222id.rl_holder);
        ImageView imageView2 = (ImageView) dialog.findViewById(C2219R.C2222id.img_dismiss);
        try {
            if (countryCodePicker.getDialogTypeFace() != null) {
                if (countryCodePicker.getDialogTypeFaceStyle() != -99) {
                    textView2.setTypeface(countryCodePicker.getDialogTypeFace(), countryCodePicker.getDialogTypeFaceStyle());
                    editText.setTypeface(countryCodePicker.getDialogTypeFace(), countryCodePicker.getDialogTypeFaceStyle());
                    textView.setTypeface(countryCodePicker.getDialogTypeFace(), countryCodePicker.getDialogTypeFaceStyle());
                } else {
                    textView2.setTypeface(countryCodePicker.getDialogTypeFace());
                    editText.setTypeface(countryCodePicker.getDialogTypeFace());
                    textView.setTypeface(countryCodePicker.getDialogTypeFace());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (countryCodePicker.getDialogBackgroundColor() != 0) {
            relativeLayout2.setBackgroundColor(countryCodePicker.getDialogBackgroundColor());
        }
        if (countryCodePicker.getDialogBackgroundResId() != 0) {
            relativeLayout2.setBackgroundResource(countryCodePicker.getDialogBackgroundResId());
        }
        if (countryCodePicker.isShowCloseIcon()) {
            imageView2.setVisibility(0);
            imageView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CountryCodeDialog.dialog.dismiss();
                }
            });
        } else {
            imageView2.setVisibility(8);
        }
        if (!countryCodePicker.getCcpDialogShowTitle()) {
            textView.setVisibility(8);
        }
        if (countryCodePicker.getDialogTextColor() != 0) {
            int dialogTextColor = countryCodePicker.getDialogTextColor();
            imageView.setColorFilter(dialogTextColor);
            imageView2.setColorFilter(dialogTextColor);
            textView.setTextColor(dialogTextColor);
            textView2.setTextColor(dialogTextColor);
            editText.setTextColor(dialogTextColor);
            editText.setHintTextColor(Color.argb(100, Color.red(dialogTextColor), Color.green(dialogTextColor), Color.blue(dialogTextColor)));
        }
        if (countryCodePicker.getDialogSearchEditTextTintColor() != 0 && Build.VERSION.SDK_INT >= 21) {
            editText.setBackgroundTintList(ColorStateList.valueOf(countryCodePicker.getDialogSearchEditTextTintColor()));
            setCursorColor(editText, countryCodePicker.getDialogSearchEditTextTintColor());
        }
        textView.setText(countryCodePicker.getDialogTitle());
        editText.setHint(countryCodePicker.getSearchHintText());
        textView2.setText(countryCodePicker.getNoResultACK());
        if (!countryCodePicker.isSearchAllowed()) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
            layoutParams.height = -2;
            recyclerView.setLayoutParams(layoutParams);
        }
        CountryCodeAdapter countryCodeAdapter = new CountryCodeAdapter(context, customMasterCountryList, countryCodePicker, relativeLayout, editText, textView2, dialog, imageView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(countryCodeAdapter);
        FastScroller fastScroller = (FastScroller) dialog.findViewById(C2219R.C2222id.fastscroll);
        fastScroller.setRecyclerView(recyclerView);
        if (countryCodePicker.isShowFastScroller()) {
            if (countryCodePicker.getFastScrollerBubbleColor() != 0) {
                fastScroller.setBubbleColor(countryCodePicker.getFastScrollerBubbleColor());
            }
            if (countryCodePicker.getFastScrollerHandleColor() != 0) {
                fastScroller.setHandleColor(countryCodePicker.getFastScrollerHandleColor());
            }
            if (countryCodePicker.getFastScrollerBubbleTextAppearance() != 0) {
                try {
                    fastScroller.setBubbleTextAppearance(countryCodePicker.getFastScrollerBubbleTextAppearance());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            fastScroller.setVisibility(8);
        }
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                CountryCodeDialog.hideKeyboard(CountryCodeDialog.context);
                if (countryCodePicker2.getDialogEventsListener() != null) {
                    countryCodePicker2.getDialogEventsListener().onCcpDialogDismiss(dialogInterface);
                }
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                CountryCodeDialog.hideKeyboard(CountryCodeDialog.context);
                if (countryCodePicker2.getDialogEventsListener() != null) {
                    countryCodePicker2.getDialogEventsListener().onCcpDialogCancel(dialogInterface);
                }
            }
        });
        if (str2 != null) {
            if (countryCodePicker2.preferredCountries != null) {
                Iterator<CCPCountry> it = countryCodePicker2.preferredCountries.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().nameCode.equalsIgnoreCase(str2)) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            z = false;
            if (!z) {
                int size = (countryCodePicker2.preferredCountries == null || countryCodePicker2.preferredCountries.size() <= 0) ? 0 : countryCodePicker2.preferredCountries.size() + 1;
                int i = 0;
                while (true) {
                    if (i >= customMasterCountryList.size()) {
                        break;
                    } else if (customMasterCountryList.get(i).nameCode.equalsIgnoreCase(str2)) {
                        recyclerView.scrollToPosition(i + size);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        dialog.show();
        if (countryCodePicker.getDialogEventsListener() != null) {
            countryCodePicker.getDialogEventsListener().onCcpDialogOpen(dialog);
        }
    }

    /* access modifiers changed from: private */
    public static void hideKeyboard(Context context2) {
        if (context2 instanceof Activity) {
            Activity activity = (Activity) context2;
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus == null) {
                currentFocus = new View(activity);
            }
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    static void setCursorColor(EditText editText, int i) {
        Field field = sCursorDrawableField;
        if (field != null) {
            try {
                Drawable drawable = getDrawable(editText.getContext(), sCursorDrawableResourceField.getInt(editText));
                drawable.setColorFilter(i, PorterDuff.Mode.SRC_IN);
                field.set(Build.VERSION.SDK_INT < 16 ? editText : sEditorField.get(editText), new Drawable[]{drawable, drawable});
            } catch (Exception unused) {
            }
        }
    }

    static void clear() {
        Dialog dialog2 = dialog;
        if (dialog2 != null) {
            dialog2.dismiss();
        }
        dialog = null;
        context = null;
    }

    private static Drawable getDrawable(Context context2, int i) {
        if (Build.VERSION.SDK_INT < 21) {
            return context2.getResources().getDrawable(i);
        }
        return context2.getDrawable(i);
    }
}
