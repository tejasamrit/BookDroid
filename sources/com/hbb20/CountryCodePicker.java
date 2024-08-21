package com.hbb20;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import p012io.michaelrocks.libphonenumber.android.NumberParseException;
import p012io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import p012io.michaelrocks.libphonenumber.android.Phonenumber;

public class CountryCodePicker extends RelativeLayout {
    private static String ANDROID_NAME_SPACE = "http://schemas.android.com/apk/res/android";
    static String BUNDLE_SELECTED_CODE = "selectedCode";
    static final int DEFAULT_UNSET = -99;
    static int LIB_DEFAULT_COUNTRY_CODE = 91;
    static String TAG = "CCP";
    private static int TEXT_GRAVITY_CENTER = 0;
    private static int TEXT_GRAVITY_LEFT = -1;
    private static int TEXT_GRAVITY_RIGHT = 1;
    String CCP_PREF_FILE = "CCP_PREF_FILE";
    TextWatcher areaCodeCountryDetectorTextWatcher;
    int arrowColor = DEFAULT_UNSET;
    boolean autoDetectCountryEnabled = false;
    boolean autoDetectLanguageEnabled = false;
    int borderFlagColor;
    boolean ccpClickable = true;
    boolean ccpDialogInitialScrollToSelection = false;
    boolean ccpDialogShowFlag = true;
    boolean ccpDialogShowNameCode = true;
    boolean ccpDialogShowPhoneCode = true;
    boolean ccpDialogShowTitle = true;
    int ccpPadding;
    int ccpTextgGravity = TEXT_GRAVITY_CENTER;
    boolean ccpUseDummyEmojiForPreview = false;
    boolean ccpUseEmoji = false;
    CountryCodePicker codePicker;
    int contentColor = DEFAULT_UNSET;
    Context context;
    boolean countryChangedDueToAreaCode = false;
    View.OnClickListener countryCodeHolderClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (CountryCodePicker.this.customClickListener != null) {
                CountryCodePicker.this.customClickListener.onClick(view);
            } else if (!CountryCodePicker.this.isCcpClickable()) {
            } else {
                if (CountryCodePicker.this.ccpDialogInitialScrollToSelection) {
                    CountryCodePicker countryCodePicker = CountryCodePicker.this;
                    countryCodePicker.launchCountrySelectionDialog(countryCodePicker.getSelectedCountryNameCode());
                    return;
                }
                CountryCodePicker.this.launchCountrySelectionDialog();
            }
        }
    };
    boolean countryDetectionBasedOnAreaAllowed;
    String countryPreference;
    /* access modifiers changed from: private */
    public CCPCountryGroup currentCountryGroup;
    TextGravity currentTextGravity;
    /* access modifiers changed from: private */
    public View.OnClickListener customClickListener;
    Language customDefaultLanguage = Language.ENGLISH;
    private CustomDialogTextProvider customDialogTextProvider;
    List<CCPCountry> customMasterCountriesList;
    String customMasterCountriesParam;
    CCPCountry defaultCCPCountry;
    int defaultCountryCode;
    String defaultCountryNameCode;
    boolean detectCountryWithAreaCode = true;
    private int dialogBackgroundColor;
    private int dialogBackgroundResId;
    private DialogEventsListener dialogEventsListener;
    boolean dialogKeyboardAutoPopup = true;
    private int dialogSearchEditTextTintColor;
    private int dialogTextColor;
    Typeface dialogTypeFace;
    int dialogTypeFaceStyle;
    EditText editText_registeredCarrierNumber;
    String excludedCountriesParam;
    private FailureListener failureListener;
    int fastScrollerBubbleColor = 0;
    private int fastScrollerBubbleTextAppearance = 0;
    private int fastScrollerHandleColor = 0;
    InternationalPhoneTextWatcher formattingTextWatcher;
    boolean hintExampleNumberEnabled = false;
    PhoneNumberType hintExampleNumberType = PhoneNumberType.MOBILE;
    RelativeLayout holder;
    View holderView;
    ImageView imageViewArrow;
    ImageView imageViewFlag;
    boolean internationalFormattingOnly = true;
    Language languageToApply = Language.ENGLISH;
    String lastCheckedAreaCode = null;
    int lastCursorPosition = 0;
    LinearLayout linearFlagBorder;
    LinearLayout linearFlagHolder;
    LayoutInflater mInflater;
    boolean numberAutoFormattingEnabled = true;
    private OnCountryChangeListener onCountryChangeListener;
    String originalHint = "";
    /* access modifiers changed from: private */
    public PhoneNumberValidityChangeListener phoneNumberValidityChangeListener;
    PhoneNumberUtil phoneUtil;
    List<CCPCountry> preferredCountries;
    RelativeLayout relativeClickConsumer;
    boolean rememberLastSelection = false;
    boolean reportedValidity;
    boolean searchAllowed = true;
    AutoDetectionPref selectedAutoDetectionPref = AutoDetectionPref.SIM_NETWORK_LOCALE;
    CCPCountry selectedCCPCountry;
    String selectionMemoryTag = "ccp_last_selection";
    boolean showArrow = true;
    boolean showCloseIcon = false;
    boolean showFastScroller = true;
    boolean showFlag = true;
    boolean showFullName = false;
    boolean showNameCode = true;
    boolean showPhoneCode = true;
    TextView textView_selectedCountry;
    TextWatcher validityTextWatcher;
    String xmlWidth = "notSet";

    public interface CustomDialogTextProvider {
        String getCCPDialogNoResultACK(Language language, String str);

        String getCCPDialogSearchHintText(Language language, String str);

        String getCCPDialogTitle(Language language, String str);
    }

    public interface DialogEventsListener {
        void onCcpDialogCancel(DialogInterface dialogInterface);

        void onCcpDialogDismiss(DialogInterface dialogInterface);

        void onCcpDialogOpen(Dialog dialog);
    }

    public interface FailureListener {
        void onCountryAutoDetectionFailed();
    }

    public interface OnCountryChangeListener {
        void onCountrySelected();
    }

    public enum PhoneNumberType {
        MOBILE,
        FIXED_LINE,
        FIXED_LINE_OR_MOBILE,
        TOLL_FREE,
        PREMIUM_RATE,
        SHARED_COST,
        VOIP,
        PERSONAL_NUMBER,
        PAGER,
        UAN,
        VOICEMAIL,
        UNKNOWN
    }

    public interface PhoneNumberValidityChangeListener {
        void onValidityChanged(boolean z);
    }

    public CountryCodePicker(Context context2) {
        super(context2);
        this.context = context2;
        init((AttributeSet) null);
    }

    public CountryCodePicker(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        this.context = context2;
        init(attributeSet);
    }

    public CountryCodePicker(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        this.context = context2;
        init(attributeSet);
    }

    private boolean isNumberAutoFormattingEnabled() {
        return this.numberAutoFormattingEnabled;
    }

    public void setNumberAutoFormattingEnabled(boolean z) {
        this.numberAutoFormattingEnabled = z;
        if (this.editText_registeredCarrierNumber != null) {
            updateFormattingTextWatcher();
        }
    }

    private boolean isInternationalFormattingOnlyEnabled() {
        return this.internationalFormattingOnly;
    }

    public void setInternationalFormattingOnly(boolean z) {
        this.internationalFormattingOnly = z;
        if (this.editText_registeredCarrierNumber != null) {
            updateFormattingTextWatcher();
        }
    }

    private void init(AttributeSet attributeSet) {
        String str;
        this.mInflater = LayoutInflater.from(this.context);
        if (attributeSet != null) {
            this.xmlWidth = attributeSet.getAttributeValue(ANDROID_NAME_SPACE, "layout_width");
        }
        removeAllViewsInLayout();
        if (attributeSet == null || (str = this.xmlWidth) == null || (!str.equals("-1") && !this.xmlWidth.equals("-1") && !this.xmlWidth.equals("fill_parent") && !this.xmlWidth.equals("match_parent"))) {
            this.holderView = this.mInflater.inflate(C2219R.layout.layout_code_picker, this, true);
        } else {
            this.holderView = this.mInflater.inflate(C2219R.layout.layout_full_width_code_picker, this, true);
        }
        this.textView_selectedCountry = (TextView) this.holderView.findViewById(C2219R.C2222id.textView_selectedCountry);
        this.holder = (RelativeLayout) this.holderView.findViewById(C2219R.C2222id.countryCodeHolder);
        this.imageViewArrow = (ImageView) this.holderView.findViewById(C2219R.C2222id.imageView_arrow);
        this.imageViewFlag = (ImageView) this.holderView.findViewById(C2219R.C2222id.image_flag);
        this.linearFlagHolder = (LinearLayout) this.holderView.findViewById(C2219R.C2222id.linear_flag_holder);
        this.linearFlagBorder = (LinearLayout) this.holderView.findViewById(C2219R.C2222id.linear_flag_border);
        this.relativeClickConsumer = (RelativeLayout) this.holderView.findViewById(C2219R.C2222id.rlClickConsumer);
        this.codePicker = this;
        if (attributeSet != null) {
            applyCustomProperty(attributeSet);
        }
        this.relativeClickConsumer.setOnClickListener(this.countryCodeHolderClickListener);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x01d7 A[Catch:{ Exception -> 0x0322, all -> 0x0320 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x01f7 A[ADDED_TO_REGION, Catch:{ Exception -> 0x0322, all -> 0x0320 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0214 A[Catch:{ Exception -> 0x0322, all -> 0x0320 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x024a A[Catch:{ Exception -> 0x0322, all -> 0x0320 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0289 A[Catch:{ Exception -> 0x0322, all -> 0x0320 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0290 A[Catch:{ Exception -> 0x0322, all -> 0x0320 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x02a4 A[Catch:{ Exception -> 0x0322, all -> 0x0320 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x02ad A[Catch:{ Exception -> 0x0322, all -> 0x0320 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x02b4 A[Catch:{ Exception -> 0x0322, all -> 0x0320 }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x02c8 A[Catch:{ Exception -> 0x0322, all -> 0x0320 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x02f7 A[Catch:{ Exception -> 0x0322, all -> 0x0320 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x030b A[Catch:{ Exception -> 0x0322, all -> 0x0320 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyCustomProperty(android.util.AttributeSet r8) {
        /*
            r7 = this;
            java.lang.String r0 = ""
            android.content.Context r1 = r7.context
            android.content.res.Resources$Theme r1 = r1.getTheme()
            int[] r2 = com.hbb20.C2219R.styleable.CountryCodePicker
            r3 = 0
            android.content.res.TypedArray r8 = r1.obtainStyledAttributes(r8, r2, r3, r3)
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_showNameCode     // Catch:{ Exception -> 0x0322 }
            r2 = 1
            boolean r1 = r8.getBoolean(r1, r2)     // Catch:{ Exception -> 0x0322 }
            r7.showNameCode = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_autoFormatNumber     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r2)     // Catch:{ Exception -> 0x0322 }
            r7.numberAutoFormattingEnabled = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_showPhoneCode     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r2)     // Catch:{ Exception -> 0x0322 }
            r7.showPhoneCode = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_showPhoneCode     // Catch:{ Exception -> 0x0322 }
            boolean r4 = r7.showPhoneCode     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r4)     // Catch:{ Exception -> 0x0322 }
            r7.ccpDialogShowPhoneCode = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_showNameCode     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r2)     // Catch:{ Exception -> 0x0322 }
            r7.ccpDialogShowNameCode = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_showTitle     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r2)     // Catch:{ Exception -> 0x0322 }
            r7.ccpDialogShowTitle = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_useFlagEmoji     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r3)     // Catch:{ Exception -> 0x0322 }
            r7.ccpUseEmoji = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_useDummyEmojiForPreview     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r3)     // Catch:{ Exception -> 0x0322 }
            r7.ccpUseDummyEmojiForPreview = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_showFlag     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r2)     // Catch:{ Exception -> 0x0322 }
            r7.ccpDialogShowFlag = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_initialScrollToSelection     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r3)     // Catch:{ Exception -> 0x0322 }
            r7.ccpDialogInitialScrollToSelection = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_showFullName     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r3)     // Catch:{ Exception -> 0x0322 }
            r7.showFullName = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_showFastScroller     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r2)     // Catch:{ Exception -> 0x0322 }
            r7.showFastScroller = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_fastScroller_bubbleColor     // Catch:{ Exception -> 0x0322 }
            int r1 = r8.getColor(r1, r3)     // Catch:{ Exception -> 0x0322 }
            r7.fastScrollerBubbleColor = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_fastScroller_handleColor     // Catch:{ Exception -> 0x0322 }
            int r1 = r8.getColor(r1, r3)     // Catch:{ Exception -> 0x0322 }
            r7.fastScrollerHandleColor = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_fastScroller_bubbleTextAppearance     // Catch:{ Exception -> 0x0322 }
            int r1 = r8.getResourceId(r1, r3)     // Catch:{ Exception -> 0x0322 }
            r7.fastScrollerBubbleTextAppearance = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_autoDetectLanguage     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r3)     // Catch:{ Exception -> 0x0322 }
            r7.autoDetectLanguageEnabled = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_areaCodeDetectedCountry     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r2)     // Catch:{ Exception -> 0x0322 }
            r7.detectCountryWithAreaCode = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_rememberLastSelection     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r3)     // Catch:{ Exception -> 0x0322 }
            r7.rememberLastSelection = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_hintExampleNumber     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r3)     // Catch:{ Exception -> 0x0322 }
            r7.hintExampleNumberEnabled = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_internationalFormattingOnly     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r2)     // Catch:{ Exception -> 0x0322 }
            r7.internationalFormattingOnly = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_padding     // Catch:{ Exception -> 0x0322 }
            android.content.Context r4 = r7.context     // Catch:{ Exception -> 0x0322 }
            android.content.res.Resources r4 = r4.getResources()     // Catch:{ Exception -> 0x0322 }
            int r5 = com.hbb20.C2219R.dimen.ccp_padding     // Catch:{ Exception -> 0x0322 }
            float r4 = r4.getDimension(r5)     // Catch:{ Exception -> 0x0322 }
            float r1 = r8.getDimension(r1, r4)     // Catch:{ Exception -> 0x0322 }
            int r1 = (int) r1     // Catch:{ Exception -> 0x0322 }
            r7.ccpPadding = r1     // Catch:{ Exception -> 0x0322 }
            android.widget.RelativeLayout r4 = r7.relativeClickConsumer     // Catch:{ Exception -> 0x0322 }
            r4.setPadding(r1, r1, r1, r1)     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_hintExampleNumberType     // Catch:{ Exception -> 0x0322 }
            int r1 = r8.getInt(r1, r3)     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CountryCodePicker$PhoneNumberType[] r4 = com.hbb20.CountryCodePicker.PhoneNumberType.values()     // Catch:{ Exception -> 0x0322 }
            r1 = r4[r1]     // Catch:{ Exception -> 0x0322 }
            r7.hintExampleNumberType = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_selectionMemoryTag     // Catch:{ Exception -> 0x0322 }
            java.lang.String r1 = r8.getString(r1)     // Catch:{ Exception -> 0x0322 }
            r7.selectionMemoryTag = r1     // Catch:{ Exception -> 0x0322 }
            if (r1 != 0) goto L_0x00e8
            java.lang.String r1 = "CCP_last_selection"
            r7.selectionMemoryTag = r1     // Catch:{ Exception -> 0x0322 }
        L_0x00e8:
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_countryAutoDetectionPref     // Catch:{ Exception -> 0x0322 }
            r4 = 123(0x7b, float:1.72E-43)
            int r1 = r8.getInt(r1, r4)     // Catch:{ Exception -> 0x0322 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CountryCodePicker$AutoDetectionPref r1 = com.hbb20.CountryCodePicker.AutoDetectionPref.getPrefForValue(r1)     // Catch:{ Exception -> 0x0322 }
            r7.selectedAutoDetectionPref = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_autoDetectCountry     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r3)     // Catch:{ Exception -> 0x0322 }
            r7.autoDetectCountryEnabled = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_showArrow     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r2)     // Catch:{ Exception -> 0x0322 }
            r7.showArrow = r1     // Catch:{ Exception -> 0x0322 }
            r7.refreshArrowViewVisibility()     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_showCloseIcon     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r3)     // Catch:{ Exception -> 0x0322 }
            r7.showCloseIcon = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_showFlag     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r2)     // Catch:{ Exception -> 0x0322 }
            r7.showFlag(r1)     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_keyboardAutoPopup     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.getBoolean(r1, r2)     // Catch:{ Exception -> 0x0322 }
            r7.setDialogKeyboardAutoPopup(r1)     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_defaultLanguage     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CountryCodePicker$Language r4 = com.hbb20.CountryCodePicker.Language.ENGLISH     // Catch:{ Exception -> 0x0322 }
            int r4 = r4.ordinal()     // Catch:{ Exception -> 0x0322 }
            int r1 = r8.getInt(r1, r4)     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CountryCodePicker$Language r1 = r7.getLanguageEnum(r1)     // Catch:{ Exception -> 0x0322 }
            r7.customDefaultLanguage = r1     // Catch:{ Exception -> 0x0322 }
            r7.updateLanguageToApply()     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_customMasterCountries     // Catch:{ Exception -> 0x0322 }
            java.lang.String r1 = r8.getString(r1)     // Catch:{ Exception -> 0x0322 }
            r7.customMasterCountriesParam = r1     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_excludedCountries     // Catch:{ Exception -> 0x0322 }
            java.lang.String r1 = r8.getString(r1)     // Catch:{ Exception -> 0x0322 }
            r7.excludedCountriesParam = r1     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r7.isInEditMode()     // Catch:{ Exception -> 0x0322 }
            if (r1 != 0) goto L_0x0155
            r7.refreshCustomMasterList()     // Catch:{ Exception -> 0x0322 }
        L_0x0155:
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_countryPreference     // Catch:{ Exception -> 0x0322 }
            java.lang.String r1 = r8.getString(r1)     // Catch:{ Exception -> 0x0322 }
            r7.countryPreference = r1     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r7.isInEditMode()     // Catch:{ Exception -> 0x0322 }
            if (r1 != 0) goto L_0x0166
            r7.refreshPreferredCountries()     // Catch:{ Exception -> 0x0322 }
        L_0x0166:
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_textGravity     // Catch:{ Exception -> 0x0322 }
            boolean r1 = r8.hasValue(r1)     // Catch:{ Exception -> 0x0322 }
            if (r1 == 0) goto L_0x0178
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_textGravity     // Catch:{ Exception -> 0x0322 }
            int r4 = TEXT_GRAVITY_CENTER     // Catch:{ Exception -> 0x0322 }
            int r1 = r8.getInt(r1, r4)     // Catch:{ Exception -> 0x0322 }
            r7.ccpTextgGravity = r1     // Catch:{ Exception -> 0x0322 }
        L_0x0178:
            int r1 = r7.ccpTextgGravity     // Catch:{ Exception -> 0x0322 }
            r7.applyTextGravity(r1)     // Catch:{ Exception -> 0x0322 }
            int r1 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_defaultNameCode     // Catch:{ Exception -> 0x0322 }
            java.lang.String r1 = r8.getString(r1)     // Catch:{ Exception -> 0x0322 }
            r7.defaultCountryNameCode = r1     // Catch:{ Exception -> 0x0322 }
            java.lang.String r4 = "IN"
            if (r1 == 0) goto L_0x01e5
            int r1 = r1.length()     // Catch:{ Exception -> 0x0322 }
            if (r1 == 0) goto L_0x01e5
            boolean r1 = r7.isInEditMode()     // Catch:{ Exception -> 0x0322 }
            if (r1 != 0) goto L_0x01bc
            android.content.Context r1 = r7.getContext()     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CountryCodePicker$Language r5 = r7.getLanguageToApply()     // Catch:{ Exception -> 0x0322 }
            java.lang.String r6 = r7.defaultCountryNameCode     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CCPCountry r1 = com.hbb20.CCPCountry.getCountryForNameCodeFromLibraryMasterList(r1, r5, r6)     // Catch:{ Exception -> 0x0322 }
            if (r1 == 0) goto L_0x01d4
            android.content.Context r1 = r7.getContext()     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CountryCodePicker$Language r5 = r7.getLanguageToApply()     // Catch:{ Exception -> 0x0322 }
            java.lang.String r6 = r7.defaultCountryNameCode     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CCPCountry r1 = com.hbb20.CCPCountry.getCountryForNameCodeFromLibraryMasterList(r1, r5, r6)     // Catch:{ Exception -> 0x0322 }
            r7.setDefaultCountry(r1)     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CCPCountry r1 = r7.defaultCCPCountry     // Catch:{ Exception -> 0x0322 }
            r7.setSelectedCountry(r1)     // Catch:{ Exception -> 0x0322 }
            goto L_0x01d2
        L_0x01bc:
            java.lang.String r1 = r7.defaultCountryNameCode     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CCPCountry r1 = com.hbb20.CCPCountry.getCountryForNameCodeFromEnglishList(r1)     // Catch:{ Exception -> 0x0322 }
            if (r1 == 0) goto L_0x01d4
            java.lang.String r1 = r7.defaultCountryNameCode     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CCPCountry r1 = com.hbb20.CCPCountry.getCountryForNameCodeFromEnglishList(r1)     // Catch:{ Exception -> 0x0322 }
            r7.setDefaultCountry(r1)     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CCPCountry r1 = r7.defaultCCPCountry     // Catch:{ Exception -> 0x0322 }
            r7.setSelectedCountry(r1)     // Catch:{ Exception -> 0x0322 }
        L_0x01d2:
            r1 = 1
            goto L_0x01d5
        L_0x01d4:
            r1 = 0
        L_0x01d5:
            if (r1 != 0) goto L_0x01e6
            com.hbb20.CCPCountry r1 = com.hbb20.CCPCountry.getCountryForNameCodeFromEnglishList(r4)     // Catch:{ Exception -> 0x0322 }
            r7.setDefaultCountry(r1)     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CCPCountry r1 = r7.defaultCCPCountry     // Catch:{ Exception -> 0x0322 }
            r7.setSelectedCountry(r1)     // Catch:{ Exception -> 0x0322 }
            r1 = 1
            goto L_0x01e6
        L_0x01e5:
            r1 = 0
        L_0x01e6:
            int r5 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_defaultPhoneCode     // Catch:{ Exception -> 0x0322 }
            r6 = -1
            int r5 = r8.getInteger(r5, r6)     // Catch:{ Exception -> 0x0322 }
            if (r1 != 0) goto L_0x0244
            if (r5 == r6) goto L_0x0244
            boolean r1 = r7.isInEditMode()     // Catch:{ Exception -> 0x0322 }
            if (r1 != 0) goto L_0x0214
            if (r5 == r6) goto L_0x020b
            android.content.Context r0 = r7.getContext()     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CountryCodePicker$Language r1 = r7.getLanguageToApply()     // Catch:{ Exception -> 0x0322 }
            java.util.List<com.hbb20.CCPCountry> r6 = r7.preferredCountries     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CCPCountry r0 = com.hbb20.CCPCountry.getCountryForCode((android.content.Context) r0, (com.hbb20.CountryCodePicker.Language) r1, (java.util.List<com.hbb20.CCPCountry>) r6, (int) r5)     // Catch:{ Exception -> 0x0322 }
            if (r0 != 0) goto L_0x020b
            int r5 = LIB_DEFAULT_COUNTRY_CODE     // Catch:{ Exception -> 0x0322 }
        L_0x020b:
            r7.setDefaultCountryUsingPhoneCode(r5)     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CCPCountry r0 = r7.defaultCCPCountry     // Catch:{ Exception -> 0x0322 }
            r7.setSelectedCountry(r0)     // Catch:{ Exception -> 0x0322 }
            goto L_0x0244
        L_0x0214:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0322 }
            r1.<init>()     // Catch:{ Exception -> 0x0322 }
            r1.append(r5)     // Catch:{ Exception -> 0x0322 }
            r1.append(r0)     // Catch:{ Exception -> 0x0322 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CCPCountry r1 = com.hbb20.CCPCountry.getCountryForCodeFromEnglishList(r1)     // Catch:{ Exception -> 0x0322 }
            if (r1 != 0) goto L_0x023e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0322 }
            r1.<init>()     // Catch:{ Exception -> 0x0322 }
            int r5 = LIB_DEFAULT_COUNTRY_CODE     // Catch:{ Exception -> 0x0322 }
            r1.append(r5)     // Catch:{ Exception -> 0x0322 }
            r1.append(r0)     // Catch:{ Exception -> 0x0322 }
            java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CCPCountry r1 = com.hbb20.CCPCountry.getCountryForCodeFromEnglishList(r0)     // Catch:{ Exception -> 0x0322 }
        L_0x023e:
            r7.setDefaultCountry(r1)     // Catch:{ Exception -> 0x0322 }
            r7.setSelectedCountry(r1)     // Catch:{ Exception -> 0x0322 }
        L_0x0244:
            com.hbb20.CCPCountry r0 = r7.getDefaultCountry()     // Catch:{ Exception -> 0x0322 }
            if (r0 != 0) goto L_0x025c
            com.hbb20.CCPCountry r0 = com.hbb20.CCPCountry.getCountryForNameCodeFromEnglishList(r4)     // Catch:{ Exception -> 0x0322 }
            r7.setDefaultCountry(r0)     // Catch:{ Exception -> 0x0322 }
            com.hbb20.CCPCountry r0 = r7.getSelectedCountry()     // Catch:{ Exception -> 0x0322 }
            if (r0 != 0) goto L_0x025c
            com.hbb20.CCPCountry r0 = r7.defaultCCPCountry     // Catch:{ Exception -> 0x0322 }
            r7.setSelectedCountry(r0)     // Catch:{ Exception -> 0x0322 }
        L_0x025c:
            boolean r0 = r7.isAutoDetectCountryEnabled()     // Catch:{ Exception -> 0x0322 }
            if (r0 == 0) goto L_0x026b
            boolean r0 = r7.isInEditMode()     // Catch:{ Exception -> 0x0322 }
            if (r0 != 0) goto L_0x026b
            r7.setAutoDetectedCountry(r2)     // Catch:{ Exception -> 0x0322 }
        L_0x026b:
            boolean r0 = r7.rememberLastSelection     // Catch:{ Exception -> 0x0322 }
            if (r0 == 0) goto L_0x0278
            boolean r0 = r7.isInEditMode()     // Catch:{ Exception -> 0x0322 }
            if (r0 != 0) goto L_0x0278
            r7.loadLastSelectedCountryInCCP()     // Catch:{ Exception -> 0x0322 }
        L_0x0278:
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_arrowColor     // Catch:{ Exception -> 0x0322 }
            r1 = -99
            int r0 = r8.getColor(r0, r1)     // Catch:{ Exception -> 0x0322 }
            r7.setArrowColor(r0)     // Catch:{ Exception -> 0x0322 }
            boolean r0 = r7.isInEditMode()     // Catch:{ Exception -> 0x0322 }
            if (r0 == 0) goto L_0x0290
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_contentColor     // Catch:{ Exception -> 0x0322 }
            int r0 = r8.getColor(r0, r1)     // Catch:{ Exception -> 0x0322 }
            goto L_0x02a2
        L_0x0290:
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_contentColor     // Catch:{ Exception -> 0x0322 }
            android.content.Context r4 = r7.context     // Catch:{ Exception -> 0x0322 }
            android.content.res.Resources r4 = r4.getResources()     // Catch:{ Exception -> 0x0322 }
            int r5 = com.hbb20.C2219R.C2220color.defaultContentColor     // Catch:{ Exception -> 0x0322 }
            int r4 = r4.getColor(r5)     // Catch:{ Exception -> 0x0322 }
            int r0 = r8.getColor(r0, r4)     // Catch:{ Exception -> 0x0322 }
        L_0x02a2:
            if (r0 == r1) goto L_0x02a7
            r7.setContentColor(r0)     // Catch:{ Exception -> 0x0322 }
        L_0x02a7:
            boolean r0 = r7.isInEditMode()     // Catch:{ Exception -> 0x0322 }
            if (r0 == 0) goto L_0x02b4
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_flagBorderColor     // Catch:{ Exception -> 0x0322 }
            int r0 = r8.getColor(r0, r3)     // Catch:{ Exception -> 0x0322 }
            goto L_0x02c6
        L_0x02b4:
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_flagBorderColor     // Catch:{ Exception -> 0x0322 }
            android.content.Context r1 = r7.context     // Catch:{ Exception -> 0x0322 }
            android.content.res.Resources r1 = r1.getResources()     // Catch:{ Exception -> 0x0322 }
            int r4 = com.hbb20.C2219R.C2220color.defaultBorderFlagColor     // Catch:{ Exception -> 0x0322 }
            int r1 = r1.getColor(r4)     // Catch:{ Exception -> 0x0322 }
            int r0 = r8.getColor(r0, r1)     // Catch:{ Exception -> 0x0322 }
        L_0x02c6:
            if (r0 == 0) goto L_0x02cb
            r7.setFlagBorderColor(r0)     // Catch:{ Exception -> 0x0322 }
        L_0x02cb:
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_backgroundColor     // Catch:{ Exception -> 0x0322 }
            int r0 = r8.getColor(r0, r3)     // Catch:{ Exception -> 0x0322 }
            r7.setDialogBackgroundColor(r0)     // Catch:{ Exception -> 0x0322 }
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_background     // Catch:{ Exception -> 0x0322 }
            int r0 = r8.getResourceId(r0, r3)     // Catch:{ Exception -> 0x0322 }
            r7.setDialogBackground(r0)     // Catch:{ Exception -> 0x0322 }
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_textColor     // Catch:{ Exception -> 0x0322 }
            int r0 = r8.getColor(r0, r3)     // Catch:{ Exception -> 0x0322 }
            r7.setDialogTextColor(r0)     // Catch:{ Exception -> 0x0322 }
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_searchEditTextTint     // Catch:{ Exception -> 0x0322 }
            int r0 = r8.getColor(r0, r3)     // Catch:{ Exception -> 0x0322 }
            r7.setDialogSearchEditTextTintColor(r0)     // Catch:{ Exception -> 0x0322 }
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_textSize     // Catch:{ Exception -> 0x0322 }
            int r0 = r8.getDimensionPixelSize(r0, r3)     // Catch:{ Exception -> 0x0322 }
            if (r0 <= 0) goto L_0x0303
            android.widget.TextView r1 = r7.textView_selectedCountry     // Catch:{ Exception -> 0x0322 }
            float r4 = (float) r0     // Catch:{ Exception -> 0x0322 }
            r1.setTextSize(r3, r4)     // Catch:{ Exception -> 0x0322 }
            r7.setFlagSize(r0)     // Catch:{ Exception -> 0x0322 }
            r7.setArrowSize(r0)     // Catch:{ Exception -> 0x0322 }
        L_0x0303:
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_arrowSize     // Catch:{ Exception -> 0x0322 }
            int r0 = r8.getDimensionPixelSize(r0, r3)     // Catch:{ Exception -> 0x0322 }
            if (r0 <= 0) goto L_0x030e
            r7.setArrowSize(r0)     // Catch:{ Exception -> 0x0322 }
        L_0x030e:
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccpDialog_allowSearch     // Catch:{ Exception -> 0x0322 }
            boolean r0 = r8.getBoolean(r0, r2)     // Catch:{ Exception -> 0x0322 }
            r7.searchAllowed = r0     // Catch:{ Exception -> 0x0322 }
            int r0 = com.hbb20.C2219R.styleable.CountryCodePicker_ccp_clickable     // Catch:{ Exception -> 0x0322 }
            boolean r0 = r8.getBoolean(r0, r2)     // Catch:{ Exception -> 0x0322 }
            r7.setCcpClickable(r0)     // Catch:{ Exception -> 0x0322 }
            goto L_0x0326
        L_0x0320:
            r0 = move-exception
            goto L_0x032a
        L_0x0322:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0320 }
        L_0x0326:
            r8.recycle()
            return
        L_0x032a:
            r8.recycle()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hbb20.CountryCodePicker.applyCustomProperty(android.util.AttributeSet):void");
    }

    private void refreshArrowViewVisibility() {
        if (this.showArrow) {
            this.imageViewArrow.setVisibility(0);
        } else {
            this.imageViewArrow.setVisibility(8);
        }
    }

    private void loadLastSelectedCountryInCCP() {
        String string = this.context.getSharedPreferences(this.CCP_PREF_FILE, 0).getString(this.selectionMemoryTag, (String) null);
        if (string != null) {
            setCountryForNameCode(string);
        }
    }

    /* access modifiers changed from: package-private */
    public void storeSelectedCountryNameCode(String str) {
        SharedPreferences.Editor edit = this.context.getSharedPreferences(this.CCP_PREF_FILE, 0).edit();
        edit.putString(this.selectionMemoryTag, str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public boolean isCcpDialogShowPhoneCode() {
        return this.ccpDialogShowPhoneCode;
    }

    public void setCcpDialogShowPhoneCode(boolean z) {
        this.ccpDialogShowPhoneCode = z;
    }

    public boolean getCcpDialogShowNameCode() {
        return this.ccpDialogShowNameCode;
    }

    public void setCcpDialogShowNameCode(boolean z) {
        this.ccpDialogShowNameCode = z;
    }

    public boolean getCcpDialogShowTitle() {
        return this.ccpDialogShowTitle;
    }

    public void setCcpDialogShowTitle(boolean z) {
        this.ccpDialogShowTitle = z;
    }

    public boolean getCcpDialogShowFlag() {
        return this.ccpDialogShowFlag;
    }

    public void setCcpDialogShowFlag(boolean z) {
        this.ccpDialogShowFlag = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isShowPhoneCode() {
        return this.showPhoneCode;
    }

    public void setShowPhoneCode(boolean z) {
        this.showPhoneCode = z;
        setSelectedCountry(this.selectedCCPCountry);
    }

    /* access modifiers changed from: protected */
    public DialogEventsListener getDialogEventsListener() {
        return this.dialogEventsListener;
    }

    public void setDialogEventsListener(DialogEventsListener dialogEventsListener2) {
        this.dialogEventsListener = dialogEventsListener2;
    }

    /* access modifiers changed from: package-private */
    public int getFastScrollerBubbleTextAppearance() {
        return this.fastScrollerBubbleTextAppearance;
    }

    public void setFastScrollerBubbleTextAppearance(int i) {
        this.fastScrollerBubbleTextAppearance = i;
    }

    /* access modifiers changed from: package-private */
    public int getFastScrollerHandleColor() {
        return this.fastScrollerHandleColor;
    }

    public void setFastScrollerHandleColor(int i) {
        this.fastScrollerHandleColor = i;
    }

    /* access modifiers changed from: package-private */
    public int getFastScrollerBubbleColor() {
        return this.fastScrollerBubbleColor;
    }

    public void setFastScrollerBubbleColor(int i) {
        this.fastScrollerBubbleColor = i;
    }

    /* access modifiers changed from: package-private */
    public TextGravity getCurrentTextGravity() {
        return this.currentTextGravity;
    }

    public void setCurrentTextGravity(TextGravity textGravity) {
        this.currentTextGravity = textGravity;
        applyTextGravity(textGravity.enumIndex);
    }

    private void applyTextGravity(int i) {
        if (i == TextGravity.LEFT.enumIndex) {
            this.textView_selectedCountry.setGravity(3);
        } else if (i == TextGravity.CENTER.enumIndex) {
            this.textView_selectedCountry.setGravity(17);
        } else {
            this.textView_selectedCountry.setGravity(5);
        }
    }

    private void updateLanguageToApply() {
        if (isInEditMode()) {
            Language language = this.customDefaultLanguage;
            if (language != null) {
                this.languageToApply = language;
            } else {
                this.languageToApply = Language.ENGLISH;
            }
        } else if (isAutoDetectLanguageEnabled()) {
            Language cCPLanguageFromLocale = getCCPLanguageFromLocale();
            if (cCPLanguageFromLocale != null) {
                this.languageToApply = cCPLanguageFromLocale;
            } else if (getCustomDefaultLanguage() != null) {
                this.languageToApply = getCustomDefaultLanguage();
            } else {
                this.languageToApply = Language.ENGLISH;
            }
        } else if (getCustomDefaultLanguage() != null) {
            this.languageToApply = this.customDefaultLanguage;
        } else {
            this.languageToApply = Language.ENGLISH;
        }
    }

    private Language getCCPLanguageFromLocale() {
        Locale locale = this.context.getResources().getConfiguration().locale;
        for (Language language : Language.values()) {
            if (language.getCode().equalsIgnoreCase(locale.getLanguage()) && (language.getCountry() == null || language.getCountry().equalsIgnoreCase(locale.getCountry()) || (Build.VERSION.SDK_INT >= 21 && (language.getScript() == null || language.getScript().equalsIgnoreCase(locale.getScript()))))) {
                return language;
            }
        }
        return null;
    }

    private CCPCountry getDefaultCountry() {
        return this.defaultCCPCountry;
    }

    private void setDefaultCountry(CCPCountry cCPCountry) {
        this.defaultCCPCountry = cCPCountry;
    }

    public TextView getTextView_selectedCountry() {
        return this.textView_selectedCountry;
    }

    public void setTextView_selectedCountry(TextView textView) {
        this.textView_selectedCountry = textView;
    }

    public ImageView getImageViewFlag() {
        return this.imageViewFlag;
    }

    public void setImageViewFlag(ImageView imageView) {
        this.imageViewFlag = imageView;
    }

    /* access modifiers changed from: private */
    public CCPCountry getSelectedCountry() {
        if (this.selectedCCPCountry == null) {
            setSelectedCountry(getDefaultCountry());
        }
        return this.selectedCCPCountry;
    }

    /* access modifiers changed from: package-private */
    public void setSelectedCountry(CCPCountry cCPCountry) {
        this.countryDetectionBasedOnAreaAllowed = false;
        String str = "";
        this.lastCheckedAreaCode = str;
        if (cCPCountry != null || (cCPCountry = CCPCountry.getCountryForCode(getContext(), getLanguageToApply(), this.preferredCountries, this.defaultCountryCode)) != null) {
            this.selectedCCPCountry = cCPCountry;
            if (this.showFlag && this.ccpUseEmoji) {
                if (!isInEditMode()) {
                    str = str + CCPCountry.getFlagEmoji(cCPCountry) + "  ";
                } else if (this.ccpUseDummyEmojiForPreview) {
                    str = str + "🏁​ ";
                } else {
                    str = str + CCPCountry.getFlagEmoji(cCPCountry) + "​ ";
                }
            }
            if (this.showFullName) {
                str = str + cCPCountry.getName();
            }
            if (this.showNameCode) {
                if (this.showFullName) {
                    str = str + " (" + cCPCountry.getNameCode().toUpperCase() + ")";
                } else {
                    str = str + " " + cCPCountry.getNameCode().toUpperCase();
                }
            }
            if (this.showPhoneCode) {
                if (str.length() > 0) {
                    str = str + "  ";
                }
                str = str + "+" + cCPCountry.getPhoneCode();
            }
            this.textView_selectedCountry.setText(str);
            if (!this.showFlag && str.length() == 0) {
                this.textView_selectedCountry.setText(str + "+" + cCPCountry.getPhoneCode());
            }
            this.imageViewFlag.setImageResource(cCPCountry.getFlagID());
            OnCountryChangeListener onCountryChangeListener2 = this.onCountryChangeListener;
            if (onCountryChangeListener2 != null) {
                onCountryChangeListener2.onCountrySelected();
            }
            updateFormattingTextWatcher();
            updateHint();
            if (!(this.editText_registeredCarrierNumber == null || this.phoneNumberValidityChangeListener == null)) {
                boolean isValidFullNumber = isValidFullNumber();
                this.reportedValidity = isValidFullNumber;
                this.phoneNumberValidityChangeListener.onValidityChanged(isValidFullNumber);
            }
            this.countryDetectionBasedOnAreaAllowed = true;
            if (this.countryChangedDueToAreaCode) {
                try {
                    this.editText_registeredCarrierNumber.setSelection(this.lastCursorPosition);
                    this.countryChangedDueToAreaCode = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            updateCountryGroup();
        }
    }

    private void updateCountryGroup() {
        this.currentCountryGroup = CCPCountryGroup.getCountryGroupForPhoneCode(getSelectedCountryCodeAsInt());
    }

    private void updateHint() {
        String str;
        if (this.editText_registeredCarrierNumber != null && this.hintExampleNumberEnabled) {
            Phonenumber.PhoneNumber exampleNumberForType = getPhoneUtil().getExampleNumberForType(getSelectedCountryNameCode(), getSelectedHintNumberType());
            String str2 = "";
            if (exampleNumberForType != null) {
                String str3 = exampleNumberForType.getNationalNumber() + str2;
                if (Build.VERSION.SDK_INT >= 21) {
                    str = PhoneNumberUtils.formatNumber(getSelectedCountryCodeWithPlus() + str3, getSelectedCountryNameCode());
                } else {
                    str = PhoneNumberUtils.formatNumber(getSelectedCountryCodeWithPlus() + str3);
                }
                str2 = str;
                if (str2 != null) {
                    str2 = str2.substring(getSelectedCountryCodeWithPlus().length()).trim();
                }
            }
            if (str2 == null) {
                str2 = this.originalHint;
            }
            this.editText_registeredCarrierNumber.setHint(str2);
        }
    }

    /* renamed from: com.hbb20.CountryCodePicker$4 */
    static /* synthetic */ class C22184 {
        static final /* synthetic */ int[] $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.hbb20.CountryCodePicker$PhoneNumberType[] r0 = com.hbb20.CountryCodePicker.PhoneNumberType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType = r0
                com.hbb20.CountryCodePicker$PhoneNumberType r1 = com.hbb20.CountryCodePicker.PhoneNumberType.MOBILE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.hbb20.CountryCodePicker$PhoneNumberType r1 = com.hbb20.CountryCodePicker.PhoneNumberType.FIXED_LINE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.hbb20.CountryCodePicker$PhoneNumberType r1 = com.hbb20.CountryCodePicker.PhoneNumberType.FIXED_LINE_OR_MOBILE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.hbb20.CountryCodePicker$PhoneNumberType r1 = com.hbb20.CountryCodePicker.PhoneNumberType.TOLL_FREE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.hbb20.CountryCodePicker$PhoneNumberType r1 = com.hbb20.CountryCodePicker.PhoneNumberType.PREMIUM_RATE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.hbb20.CountryCodePicker$PhoneNumberType r1 = com.hbb20.CountryCodePicker.PhoneNumberType.SHARED_COST     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.hbb20.CountryCodePicker$PhoneNumberType r1 = com.hbb20.CountryCodePicker.PhoneNumberType.VOIP     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.hbb20.CountryCodePicker$PhoneNumberType r1 = com.hbb20.CountryCodePicker.PhoneNumberType.PERSONAL_NUMBER     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType     // Catch:{ NoSuchFieldError -> 0x006c }
                com.hbb20.CountryCodePicker$PhoneNumberType r1 = com.hbb20.CountryCodePicker.PhoneNumberType.PAGER     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.hbb20.CountryCodePicker$PhoneNumberType r1 = com.hbb20.CountryCodePicker.PhoneNumberType.UAN     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.hbb20.CountryCodePicker$PhoneNumberType r1 = com.hbb20.CountryCodePicker.PhoneNumberType.VOICEMAIL     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.hbb20.CountryCodePicker$PhoneNumberType r1 = com.hbb20.CountryCodePicker.PhoneNumberType.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hbb20.CountryCodePicker.C22184.<clinit>():void");
        }
    }

    private PhoneNumberUtil.PhoneNumberType getSelectedHintNumberType() {
        switch (C22184.$SwitchMap$com$hbb20$CountryCodePicker$PhoneNumberType[this.hintExampleNumberType.ordinal()]) {
            case 1:
                return PhoneNumberUtil.PhoneNumberType.MOBILE;
            case 2:
                return PhoneNumberUtil.PhoneNumberType.FIXED_LINE;
            case 3:
                return PhoneNumberUtil.PhoneNumberType.FIXED_LINE_OR_MOBILE;
            case 4:
                return PhoneNumberUtil.PhoneNumberType.TOLL_FREE;
            case 5:
                return PhoneNumberUtil.PhoneNumberType.PREMIUM_RATE;
            case 6:
                return PhoneNumberUtil.PhoneNumberType.SHARED_COST;
            case 7:
                return PhoneNumberUtil.PhoneNumberType.VOIP;
            case 8:
                return PhoneNumberUtil.PhoneNumberType.PERSONAL_NUMBER;
            case 9:
                return PhoneNumberUtil.PhoneNumberType.PAGER;
            case 10:
                return PhoneNumberUtil.PhoneNumberType.UAN;
            case 11:
                return PhoneNumberUtil.PhoneNumberType.VOICEMAIL;
            case 12:
                return PhoneNumberUtil.PhoneNumberType.UNKNOWN;
            default:
                return PhoneNumberUtil.PhoneNumberType.MOBILE;
        }
    }

    public Language getLanguageToApply() {
        if (this.languageToApply == null) {
            updateLanguageToApply();
        }
        return this.languageToApply;
    }

    /* access modifiers changed from: package-private */
    public void setLanguageToApply(Language language) {
        this.languageToApply = language;
    }

    private void updateFormattingTextWatcher() {
        EditText editText = this.editText_registeredCarrierNumber;
        if (editText != null && this.selectedCCPCountry != null) {
            String normalizeDigitsOnly = PhoneNumberUtil.normalizeDigitsOnly(getEditText_registeredCarrierNumber().getText().toString());
            InternationalPhoneTextWatcher internationalPhoneTextWatcher = this.formattingTextWatcher;
            if (internationalPhoneTextWatcher != null) {
                this.editText_registeredCarrierNumber.removeTextChangedListener(internationalPhoneTextWatcher);
            }
            TextWatcher textWatcher = this.areaCodeCountryDetectorTextWatcher;
            if (textWatcher != null) {
                this.editText_registeredCarrierNumber.removeTextChangedListener(textWatcher);
            }
            if (this.numberAutoFormattingEnabled) {
                InternationalPhoneTextWatcher internationalPhoneTextWatcher2 = new InternationalPhoneTextWatcher(this.context, getSelectedCountryNameCode(), getSelectedCountryCodeAsInt(), this.internationalFormattingOnly);
                this.formattingTextWatcher = internationalPhoneTextWatcher2;
                this.editText_registeredCarrierNumber.addTextChangedListener(internationalPhoneTextWatcher2);
            }
            if (this.detectCountryWithAreaCode) {
                TextWatcher countryDetectorTextWatcher = getCountryDetectorTextWatcher();
                this.areaCodeCountryDetectorTextWatcher = countryDetectorTextWatcher;
                this.editText_registeredCarrierNumber.addTextChangedListener(countryDetectorTextWatcher);
            }
            this.editText_registeredCarrierNumber.setText("");
            this.editText_registeredCarrierNumber.setText(normalizeDigitsOnly);
            EditText editText2 = this.editText_registeredCarrierNumber;
            editText2.setSelection(editText2.getText().length());
        } else if (editText == null) {
            String str = TAG;
            Log.v(str, "updateFormattingTextWatcher: EditText not registered " + this.selectionMemoryTag);
        } else {
            String str2 = TAG;
            Log.v(str2, "updateFormattingTextWatcher: selected country is null " + this.selectionMemoryTag);
        }
    }

    private TextWatcher getCountryDetectorTextWatcher() {
        if (this.editText_registeredCarrierNumber != null && this.areaCodeCountryDetectorTextWatcher == null) {
            this.areaCodeCountryDetectorTextWatcher = new TextWatcher() {
                String lastCheckedNumber = null;

                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    CCPCountry access$100 = CountryCodePicker.this.getSelectedCountry();
                    if (access$100 != null) {
                        String str = this.lastCheckedNumber;
                        if ((str == null || !str.equals(charSequence.toString())) && CountryCodePicker.this.countryDetectionBasedOnAreaAllowed) {
                            if (CountryCodePicker.this.currentCountryGroup != null) {
                                String obj = CountryCodePicker.this.getEditText_registeredCarrierNumber().getText().toString();
                                if (obj.length() >= CountryCodePicker.this.currentCountryGroup.areaCodeLength) {
                                    String normalizeDigitsOnly = PhoneNumberUtil.normalizeDigitsOnly(obj);
                                    if (normalizeDigitsOnly.length() >= CountryCodePicker.this.currentCountryGroup.areaCodeLength) {
                                        String substring = normalizeDigitsOnly.substring(0, CountryCodePicker.this.currentCountryGroup.areaCodeLength);
                                        if (!substring.equals(CountryCodePicker.this.lastCheckedAreaCode)) {
                                            CCPCountry countryForAreaCode = CountryCodePicker.this.currentCountryGroup.getCountryForAreaCode(CountryCodePicker.this.context, CountryCodePicker.this.getLanguageToApply(), substring);
                                            if (!countryForAreaCode.equals(access$100)) {
                                                CountryCodePicker.this.countryChangedDueToAreaCode = true;
                                                CountryCodePicker.this.lastCursorPosition = Selection.getSelectionEnd(charSequence);
                                                CountryCodePicker.this.setSelectedCountry(countryForAreaCode);
                                            }
                                            CountryCodePicker.this.lastCheckedAreaCode = substring;
                                        }
                                    }
                                }
                            }
                            this.lastCheckedNumber = charSequence.toString();
                        }
                    }
                }
            };
        }
        return this.areaCodeCountryDetectorTextWatcher;
    }

    /* access modifiers changed from: package-private */
    public Language getCustomDefaultLanguage() {
        return this.customDefaultLanguage;
    }

    private void setCustomDefaultLanguage(Language language) {
        this.customDefaultLanguage = language;
        updateLanguageToApply();
        setSelectedCountry(CCPCountry.getCountryForNameCodeFromLibraryMasterList(this.context, getLanguageToApply(), this.selectedCCPCountry.getNameCode()));
    }

    private View getHolderView() {
        return this.holderView;
    }

    private void setHolderView(View view) {
        this.holderView = view;
    }

    public RelativeLayout getHolder() {
        return this.holder;
    }

    private void setHolder(RelativeLayout relativeLayout) {
        this.holder = relativeLayout;
    }

    /* access modifiers changed from: package-private */
    public boolean isAutoDetectLanguageEnabled() {
        return this.autoDetectLanguageEnabled;
    }

    /* access modifiers changed from: package-private */
    public boolean isAutoDetectCountryEnabled() {
        return this.autoDetectCountryEnabled;
    }

    /* access modifiers changed from: package-private */
    public boolean isDialogKeyboardAutoPopup() {
        return this.dialogKeyboardAutoPopup;
    }

    public void setDialogKeyboardAutoPopup(boolean z) {
        this.dialogKeyboardAutoPopup = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isShowFastScroller() {
        return this.showFastScroller;
    }

    public void setShowFastScroller(boolean z) {
        this.showFastScroller = z;
    }

    /* access modifiers changed from: protected */
    public boolean isShowCloseIcon() {
        return this.showCloseIcon;
    }

    public void showCloseIcon(boolean z) {
        this.showCloseIcon = z;
    }

    /* access modifiers changed from: package-private */
    public EditText getEditText_registeredCarrierNumber() {
        return this.editText_registeredCarrierNumber;
    }

    /* access modifiers changed from: package-private */
    public void setEditText_registeredCarrierNumber(EditText editText) {
        this.editText_registeredCarrierNumber = editText;
        if (editText.getHint() != null) {
            this.originalHint = this.editText_registeredCarrierNumber.getHint().toString();
        }
        updateValidityTextWatcher();
        updateFormattingTextWatcher();
        updateHint();
    }

    private void updateValidityTextWatcher() {
        try {
            this.editText_registeredCarrierNumber.removeTextChangedListener(this.validityTextWatcher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean isValidFullNumber = isValidFullNumber();
        this.reportedValidity = isValidFullNumber;
        PhoneNumberValidityChangeListener phoneNumberValidityChangeListener2 = this.phoneNumberValidityChangeListener;
        if (phoneNumberValidityChangeListener2 != null) {
            phoneNumberValidityChangeListener2.onValidityChanged(isValidFullNumber);
        }
        C22173 r0 = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                boolean isValidFullNumber;
                if (CountryCodePicker.this.phoneNumberValidityChangeListener != null && (isValidFullNumber = CountryCodePicker.this.isValidFullNumber()) != CountryCodePicker.this.reportedValidity) {
                    CountryCodePicker.this.reportedValidity = isValidFullNumber;
                    CountryCodePicker.this.phoneNumberValidityChangeListener.onValidityChanged(CountryCodePicker.this.reportedValidity);
                }
            }
        };
        this.validityTextWatcher = r0;
        this.editText_registeredCarrierNumber.addTextChangedListener(r0);
    }

    private LayoutInflater getmInflater() {
        return this.mInflater;
    }

    private View.OnClickListener getCountryCodeHolderClickListener() {
        return this.countryCodeHolderClickListener;
    }

    /* access modifiers changed from: package-private */
    public int getDialogBackgroundColor() {
        return this.dialogBackgroundColor;
    }

    public void setDialogBackgroundColor(int i) {
        this.dialogBackgroundColor = i;
    }

    /* access modifiers changed from: package-private */
    public int getDialogBackgroundResId() {
        return this.dialogBackgroundResId;
    }

    public void setDialogBackground(int i) {
        this.dialogBackgroundResId = i;
    }

    /* access modifiers changed from: package-private */
    public int getDialogSearchEditTextTintColor() {
        return this.dialogSearchEditTextTintColor;
    }

    public void setDialogSearchEditTextTintColor(int i) {
        this.dialogSearchEditTextTintColor = i;
    }

    /* access modifiers changed from: package-private */
    public int getDialogTextColor() {
        return this.dialogTextColor;
    }

    public void setDialogTextColor(int i) {
        this.dialogTextColor = i;
    }

    /* access modifiers changed from: package-private */
    public int getDialogTypeFaceStyle() {
        return this.dialogTypeFaceStyle;
    }

    /* access modifiers changed from: package-private */
    public Typeface getDialogTypeFace() {
        return this.dialogTypeFace;
    }

    public void setDialogTypeFace(Typeface typeface) {
        try {
            this.dialogTypeFace = typeface;
            this.dialogTypeFaceStyle = DEFAULT_UNSET;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshPreferredCountries() {
        String str = this.countryPreference;
        if (str == null || str.length() == 0) {
            this.preferredCountries = null;
        } else {
            ArrayList arrayList = new ArrayList();
            for (String countryForNameCodeFromCustomMasterList : this.countryPreference.split(",")) {
                CCPCountry countryForNameCodeFromCustomMasterList2 = CCPCountry.getCountryForNameCodeFromCustomMasterList(getContext(), this.customMasterCountriesList, getLanguageToApply(), countryForNameCodeFromCustomMasterList);
                if (countryForNameCodeFromCustomMasterList2 != null && !isAlreadyInList(countryForNameCodeFromCustomMasterList2, arrayList)) {
                    arrayList.add(countryForNameCodeFromCustomMasterList2);
                }
            }
            if (arrayList.size() == 0) {
                this.preferredCountries = null;
            } else {
                this.preferredCountries = arrayList;
            }
        }
        List<CCPCountry> list = this.preferredCountries;
        if (list != null) {
            for (CCPCountry log : list) {
                log.log();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshCustomMasterList() {
        String str = this.customMasterCountriesParam;
        if (str == null || str.length() == 0) {
            String str2 = this.excludedCountriesParam;
            if (str2 == null || str2.length() == 0) {
                this.customMasterCountriesList = null;
            } else {
                this.excludedCountriesParam = this.excludedCountriesParam.toLowerCase();
                List<CCPCountry> libraryMasterCountryList = CCPCountry.getLibraryMasterCountryList(this.context, getLanguageToApply());
                ArrayList arrayList = new ArrayList();
                for (CCPCountry next : libraryMasterCountryList) {
                    if (!this.excludedCountriesParam.contains(next.getNameCode().toLowerCase())) {
                        arrayList.add(next);
                    }
                }
                if (arrayList.size() > 0) {
                    this.customMasterCountriesList = arrayList;
                } else {
                    this.customMasterCountriesList = null;
                }
            }
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (String countryForNameCodeFromLibraryMasterList : this.customMasterCountriesParam.split(",")) {
                CCPCountry countryForNameCodeFromLibraryMasterList2 = CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), countryForNameCodeFromLibraryMasterList);
                if (countryForNameCodeFromLibraryMasterList2 != null && !isAlreadyInList(countryForNameCodeFromLibraryMasterList2, arrayList2)) {
                    arrayList2.add(countryForNameCodeFromLibraryMasterList2);
                }
            }
            if (arrayList2.size() == 0) {
                this.customMasterCountriesList = null;
            } else {
                this.customMasterCountriesList = arrayList2;
            }
        }
        List<CCPCountry> list = this.customMasterCountriesList;
        if (list != null) {
            for (CCPCountry log : list) {
                log.log();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List<CCPCountry> getCustomMasterCountriesList() {
        return this.customMasterCountriesList;
    }

    /* access modifiers changed from: package-private */
    public void setCustomMasterCountriesList(List<CCPCountry> list) {
        this.customMasterCountriesList = list;
    }

    /* access modifiers changed from: package-private */
    public String getCustomMasterCountriesParam() {
        return this.customMasterCountriesParam;
    }

    public void setCustomMasterCountries(String str) {
        this.customMasterCountriesParam = str;
    }

    public void setExcludedCountries(String str) {
        this.excludedCountriesParam = str;
        refreshCustomMasterList();
    }

    /* access modifiers changed from: package-private */
    public boolean isCcpClickable() {
        return this.ccpClickable;
    }

    public void setCcpClickable(boolean z) {
        this.ccpClickable = z;
        if (!z) {
            this.relativeClickConsumer.setOnClickListener((View.OnClickListener) null);
            this.relativeClickConsumer.setClickable(false);
            this.relativeClickConsumer.setEnabled(false);
            return;
        }
        this.relativeClickConsumer.setOnClickListener(this.countryCodeHolderClickListener);
        this.relativeClickConsumer.setClickable(true);
        this.relativeClickConsumer.setEnabled(true);
    }

    private boolean isAlreadyInList(CCPCountry cCPCountry, List<CCPCountry> list) {
        if (cCPCountry == null || list == null) {
            return false;
        }
        for (CCPCountry nameCode : list) {
            if (nameCode.getNameCode().equalsIgnoreCase(cCPCountry.getNameCode())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000b, code lost:
        r0 = r3.indexOf(r4.getPhoneCode());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String detectCarrierNumber(java.lang.String r3, com.hbb20.CCPCountry r4) {
        /*
            r2 = this;
            if (r4 == 0) goto L_0x0024
            if (r3 == 0) goto L_0x0024
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L_0x000b
            goto L_0x0024
        L_0x000b:
            java.lang.String r0 = r4.getPhoneCode()
            int r0 = r3.indexOf(r0)
            r1 = -1
            if (r0 != r1) goto L_0x0017
            goto L_0x0024
        L_0x0017:
            java.lang.String r4 = r4.getPhoneCode()
            int r4 = r4.length()
            int r0 = r0 + r4
            java.lang.String r3 = r3.substring(r0)
        L_0x0024:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hbb20.CountryCodePicker.detectCarrierNumber(java.lang.String, com.hbb20.CCPCountry):java.lang.String");
    }

    private Language getLanguageEnum(int i) {
        if (i < Language.values().length) {
            return Language.values()[i];
        }
        return Language.ENGLISH;
    }

    /* access modifiers changed from: package-private */
    public String getDialogTitle() {
        String dialogTitle = CCPCountry.getDialogTitle(this.context, getLanguageToApply());
        CustomDialogTextProvider customDialogTextProvider2 = this.customDialogTextProvider;
        return customDialogTextProvider2 != null ? customDialogTextProvider2.getCCPDialogTitle(getLanguageToApply(), dialogTitle) : dialogTitle;
    }

    /* access modifiers changed from: package-private */
    public String getSearchHintText() {
        String searchHintMessage = CCPCountry.getSearchHintMessage(this.context, getLanguageToApply());
        CustomDialogTextProvider customDialogTextProvider2 = this.customDialogTextProvider;
        return customDialogTextProvider2 != null ? customDialogTextProvider2.getCCPDialogSearchHintText(getLanguageToApply(), searchHintMessage) : searchHintMessage;
    }

    /* access modifiers changed from: package-private */
    public String getNoResultACK() {
        String noResultFoundAckMessage = CCPCountry.getNoResultFoundAckMessage(this.context, getLanguageToApply());
        CustomDialogTextProvider customDialogTextProvider2 = this.customDialogTextProvider;
        return customDialogTextProvider2 != null ? customDialogTextProvider2.getCCPDialogNoResultACK(getLanguageToApply(), noResultFoundAckMessage) : noResultFoundAckMessage;
    }

    @Deprecated
    public void setDefaultCountryUsingPhoneCode(int i) {
        CCPCountry countryForCode = CCPCountry.getCountryForCode(getContext(), getLanguageToApply(), this.preferredCountries, i);
        if (countryForCode != null) {
            this.defaultCountryCode = i;
            setDefaultCountry(countryForCode);
        }
    }

    public void setDefaultCountryUsingNameCode(String str) {
        CCPCountry countryForNameCodeFromLibraryMasterList = CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), str);
        if (countryForNameCodeFromLibraryMasterList != null) {
            this.defaultCountryNameCode = countryForNameCodeFromLibraryMasterList.getNameCode();
            setDefaultCountry(countryForNameCodeFromLibraryMasterList);
        }
    }

    public String getDefaultCountryCode() {
        return this.defaultCCPCountry.phoneCode;
    }

    public int getDefaultCountryCodeAsInt() {
        try {
            return Integer.parseInt(getDefaultCountryCode());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getDefaultCountryCodeWithPlus() {
        return "+" + getDefaultCountryCode();
    }

    public String getDefaultCountryName() {
        return getDefaultCountry().name;
    }

    public String getDefaultCountryNameCode() {
        return getDefaultCountry().nameCode.toUpperCase();
    }

    public void resetToDefaultCountry() {
        CCPCountry countryForNameCodeFromLibraryMasterList = CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), getDefaultCountryNameCode());
        this.defaultCCPCountry = countryForNameCodeFromLibraryMasterList;
        setSelectedCountry(countryForNameCodeFromLibraryMasterList);
    }

    public String getSelectedCountryCode() {
        return getSelectedCountry().phoneCode;
    }

    public String getSelectedCountryCodeWithPlus() {
        return "+" + getSelectedCountryCode();
    }

    public int getSelectedCountryCodeAsInt() {
        try {
            return Integer.parseInt(getSelectedCountryCode());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getSelectedCountryName() {
        return getSelectedCountry().name;
    }

    public String getSelectedCountryEnglishName() {
        return getSelectedCountry().getEnglishName();
    }

    public String getSelectedCountryNameCode() {
        return getSelectedCountry().nameCode.toUpperCase();
    }

    public int getSelectedCountryFlagResourceId() {
        return getSelectedCountry().flagResID;
    }

    public void setCountryForPhoneCode(int i) {
        CCPCountry countryForCode = CCPCountry.getCountryForCode(getContext(), getLanguageToApply(), this.preferredCountries, i);
        if (countryForCode == null) {
            if (this.defaultCCPCountry == null) {
                this.defaultCCPCountry = CCPCountry.getCountryForCode(getContext(), getLanguageToApply(), this.preferredCountries, this.defaultCountryCode);
            }
            setSelectedCountry(this.defaultCCPCountry);
            return;
        }
        setSelectedCountry(countryForCode);
    }

    public void setCountryForNameCode(String str) {
        CCPCountry countryForNameCodeFromLibraryMasterList = CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), str);
        if (countryForNameCodeFromLibraryMasterList == null) {
            if (this.defaultCCPCountry == null) {
                this.defaultCCPCountry = CCPCountry.getCountryForCode(getContext(), getLanguageToApply(), this.preferredCountries, this.defaultCountryCode);
            }
            setSelectedCountry(this.defaultCCPCountry);
            return;
        }
        setSelectedCountry(countryForNameCodeFromLibraryMasterList);
    }

    public void registerCarrierNumberEditText(EditText editText) {
        setEditText_registeredCarrierNumber(editText);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:2|3|4|5|6|8) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deregisterCarrierNumberEditText() {
        /*
            r2 = this;
            android.widget.EditText r0 = r2.editText_registeredCarrierNumber
            if (r0 == 0) goto L_0x001a
            android.text.TextWatcher r1 = r2.validityTextWatcher     // Catch:{ Exception -> 0x0009 }
            r0.removeTextChangedListener(r1)     // Catch:{ Exception -> 0x0009 }
        L_0x0009:
            android.widget.EditText r0 = r2.editText_registeredCarrierNumber     // Catch:{ Exception -> 0x0010 }
            com.hbb20.InternationalPhoneTextWatcher r1 = r2.formattingTextWatcher     // Catch:{ Exception -> 0x0010 }
            r0.removeTextChangedListener(r1)     // Catch:{ Exception -> 0x0010 }
        L_0x0010:
            android.widget.EditText r0 = r2.editText_registeredCarrierNumber
            java.lang.String r1 = ""
            r0.setHint(r1)
            r0 = 0
            r2.editText_registeredCarrierNumber = r0
        L_0x001a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hbb20.CountryCodePicker.deregisterCarrierNumberEditText():void");
    }

    private Phonenumber.PhoneNumber getEnteredPhoneNumber() throws NumberParseException {
        EditText editText = this.editText_registeredCarrierNumber;
        return getPhoneUtil().parse(editText != null ? PhoneNumberUtil.normalizeDigitsOnly(editText.getText().toString()) : "", getSelectedCountryNameCode());
    }

    public String getFullNumber() {
        try {
            return getPhoneUtil().format(getEnteredPhoneNumber(), PhoneNumberUtil.PhoneNumberFormat.E164).substring(1);
        } catch (NumberParseException unused) {
            Log.e(TAG, "getFullNumber: Could not parse number");
            return getSelectedCountryCode() + PhoneNumberUtil.normalizeDigitsOnly(this.editText_registeredCarrierNumber.getText().toString());
        }
    }

    public void setFullNumber(String str) {
        CCPCountry countryForNumber = CCPCountry.getCountryForNumber(getContext(), getLanguageToApply(), this.preferredCountries, str);
        if (countryForNumber == null) {
            countryForNumber = getDefaultCountry();
        }
        setSelectedCountry(countryForNumber);
        String detectCarrierNumber = detectCarrierNumber(str, countryForNumber);
        if (getEditText_registeredCarrierNumber() != null) {
            getEditText_registeredCarrierNumber().setText(detectCarrierNumber);
            updateFormattingTextWatcher();
            return;
        }
        Log.w(TAG, "EditText for carrier number is not registered. Register it using registerCarrierNumberEditText() before getFullNumber() or setFullNumber().");
    }

    public String getFormattedFullNumber() {
        try {
            Phonenumber.PhoneNumber enteredPhoneNumber = getEnteredPhoneNumber();
            return "+" + getPhoneUtil().format(enteredPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL).substring(1);
        } catch (NumberParseException unused) {
            Log.e(TAG, "getFullNumber: Could not parse number");
            return getFullNumberWithPlus();
        }
    }

    public String getFullNumberWithPlus() {
        return "+" + getFullNumber();
    }

    public int getContentColor() {
        return this.contentColor;
    }

    public void setContentColor(int i) {
        this.contentColor = i;
        this.textView_selectedCountry.setTextColor(i);
        if (this.arrowColor == DEFAULT_UNSET) {
            this.imageViewArrow.setColorFilter(this.contentColor, PorterDuff.Mode.SRC_IN);
        }
    }

    public void setArrowColor(int i) {
        this.arrowColor = i;
        if (i == DEFAULT_UNSET) {
            int i2 = this.contentColor;
            if (i2 != DEFAULT_UNSET) {
                this.imageViewArrow.setColorFilter(i2, PorterDuff.Mode.SRC_IN);
                return;
            }
            return;
        }
        this.imageViewArrow.setColorFilter(i, PorterDuff.Mode.SRC_IN);
    }

    public void setFlagBorderColor(int i) {
        this.borderFlagColor = i;
        this.linearFlagBorder.setBackgroundColor(i);
    }

    public void setTextSize(int i) {
        if (i > 0) {
            this.textView_selectedCountry.setTextSize(0, (float) i);
            setArrowSize(i);
            setFlagSize(i);
        }
    }

    public void setArrowSize(int i) {
        if (i > 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.imageViewArrow.getLayoutParams();
            layoutParams.width = i;
            layoutParams.height = i;
            this.imageViewArrow.setLayoutParams(layoutParams);
        }
    }

    public void showNameCode(boolean z) {
        this.showNameCode = z;
        setSelectedCountry(this.selectedCCPCountry);
    }

    public void showArrow(boolean z) {
        this.showArrow = z;
        refreshArrowViewVisibility();
    }

    public void setCountryPreference(String str) {
        this.countryPreference = str;
    }

    public void changeDefaultLanguage(Language language) {
        setCustomDefaultLanguage(language);
    }

    public void setTypeFace(Typeface typeface) {
        try {
            this.textView_selectedCountry.setTypeface(typeface);
            setDialogTypeFace(typeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDialogTypeFace(Typeface typeface, int i) {
        try {
            this.dialogTypeFace = typeface;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTypeFace(Typeface typeface, int i) {
        try {
            this.textView_selectedCountry.setTypeface(typeface, i);
            setDialogTypeFace(typeface, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnCountryChangeListener(OnCountryChangeListener onCountryChangeListener2) {
        this.onCountryChangeListener = onCountryChangeListener2;
    }

    public void setFlagSize(int i) {
        this.imageViewFlag.getLayoutParams().height = i;
        this.imageViewFlag.requestLayout();
    }

    public void showFlag(boolean z) {
        this.showFlag = z;
        refreshFlagVisibility();
        if (!isInEditMode()) {
            setSelectedCountry(this.selectedCCPCountry);
        }
    }

    private void refreshFlagVisibility() {
        if (!this.showFlag) {
            this.linearFlagHolder.setVisibility(8);
        } else if (this.ccpUseEmoji) {
            this.linearFlagHolder.setVisibility(8);
        } else {
            this.linearFlagHolder.setVisibility(0);
        }
    }

    public void useFlagEmoji(boolean z) {
        this.ccpUseEmoji = z;
        refreshFlagVisibility();
        setSelectedCountry(this.selectedCCPCountry);
    }

    public void showFullName(boolean z) {
        this.showFullName = z;
        setSelectedCountry(this.selectedCCPCountry);
    }

    public boolean isSearchAllowed() {
        return this.searchAllowed;
    }

    public void setSearchAllowed(boolean z) {
        this.searchAllowed = z;
    }

    public void setPhoneNumberValidityChangeListener(PhoneNumberValidityChangeListener phoneNumberValidityChangeListener2) {
        this.phoneNumberValidityChangeListener = phoneNumberValidityChangeListener2;
        if (this.editText_registeredCarrierNumber != null && phoneNumberValidityChangeListener2 != null) {
            boolean isValidFullNumber = isValidFullNumber();
            this.reportedValidity = isValidFullNumber;
            phoneNumberValidityChangeListener2.onValidityChanged(isValidFullNumber);
        }
    }

    public void setAutoDetectionFailureListener(FailureListener failureListener2) {
        this.failureListener = failureListener2;
    }

    public void setCustomDialogTextProvider(CustomDialogTextProvider customDialogTextProvider2) {
        this.customDialogTextProvider = customDialogTextProvider2;
    }

    public void launchCountrySelectionDialog() {
        launchCountrySelectionDialog((String) null);
    }

    public void launchCountrySelectionDialog(String str) {
        CountryCodeDialog.openCountryCodeDialog(this.codePicker, str);
    }

    public boolean isValidFullNumber() {
        try {
            if (getEditText_registeredCarrierNumber() == null || getEditText_registeredCarrierNumber().getText().length() == 0) {
                if (getEditText_registeredCarrierNumber() == null) {
                    Toast.makeText(this.context, "No editText for Carrier number found.", 0).show();
                }
                return false;
            }
            PhoneNumberUtil phoneUtil2 = getPhoneUtil();
            return getPhoneUtil().isValidNumber(phoneUtil2.parse("+" + this.selectedCCPCountry.getPhoneCode() + getEditText_registeredCarrierNumber().getText().toString(), this.selectedCCPCountry.getNameCode()));
        } catch (NumberParseException unused) {
        }
    }

    private PhoneNumberUtil getPhoneUtil() {
        if (this.phoneUtil == null) {
            this.phoneUtil = PhoneNumberUtil.createInstance(this.context);
        }
        return this.phoneUtil;
    }

    public void setAutoDetectedCountry(boolean z) {
        int i = 0;
        boolean z2 = false;
        while (true) {
            try {
                if (i < this.selectedAutoDetectionPref.representation.length()) {
                    switch (this.selectedAutoDetectionPref.representation.charAt(i)) {
                        case '1':
                            z2 = detectSIMCountry(false);
                            break;
                        case '2':
                            z2 = detectNetworkCountry(false);
                            break;
                        case '3':
                            z2 = detectLocaleCountry(false);
                            break;
                    }
                    if (!z2) {
                        FailureListener failureListener2 = this.failureListener;
                        if (failureListener2 != null) {
                            failureListener2.onCountryAutoDetectionFailed();
                        }
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                String str = TAG;
                Log.w(str, "setAutoDetectCountry: Exception" + e.getMessage());
                if (z) {
                    resetToDefaultCountry();
                    return;
                }
                return;
            }
        }
        if (!z2 && z) {
            resetToDefaultCountry();
        }
    }

    public boolean detectSIMCountry(boolean z) {
        try {
            String simCountryIso = ((TelephonyManager) this.context.getSystemService("phone")).getSimCountryIso();
            if (simCountryIso != null) {
                if (!simCountryIso.isEmpty()) {
                    setSelectedCountry(CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), simCountryIso));
                    return true;
                }
            }
            if (z) {
                resetToDefaultCountry();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            if (z) {
                resetToDefaultCountry();
            }
            return false;
        }
    }

    public boolean detectNetworkCountry(boolean z) {
        try {
            String networkCountryIso = ((TelephonyManager) this.context.getSystemService("phone")).getNetworkCountryIso();
            if (networkCountryIso != null) {
                if (!networkCountryIso.isEmpty()) {
                    setSelectedCountry(CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), networkCountryIso));
                    return true;
                }
            }
            if (z) {
                resetToDefaultCountry();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            if (z) {
                resetToDefaultCountry();
            }
            return false;
        }
    }

    public boolean detectLocaleCountry(boolean z) {
        try {
            String country = this.context.getResources().getConfiguration().locale.getCountry();
            if (country != null) {
                if (!country.isEmpty()) {
                    setSelectedCountry(CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), country));
                    return true;
                }
            }
            if (z) {
                resetToDefaultCountry();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            if (z) {
                resetToDefaultCountry();
            }
            return false;
        }
    }

    public void setCountryAutoDetectionPref(AutoDetectionPref autoDetectionPref) {
        this.selectedAutoDetectionPref = autoDetectionPref;
    }

    /* access modifiers changed from: protected */
    public void onUserTappedCountry(CCPCountry cCPCountry) {
        CountryCodePicker countryCodePicker = this.codePicker;
        if (countryCodePicker.rememberLastSelection) {
            countryCodePicker.storeSelectedCountryNameCode(cCPCountry.getNameCode());
        }
        setSelectedCountry(cCPCountry);
    }

    public void setDetectCountryWithAreaCode(boolean z) {
        this.detectCountryWithAreaCode = z;
        updateFormattingTextWatcher();
    }

    public void setHintExampleNumberEnabled(boolean z) {
        this.hintExampleNumberEnabled = z;
        updateHint();
    }

    public void setHintExampleNumberType(PhoneNumberType phoneNumberType) {
        this.hintExampleNumberType = phoneNumberType;
        updateHint();
    }

    public boolean isDialogInitialScrollToSelectionEnabled() {
        return this.ccpDialogInitialScrollToSelection;
    }

    public void enableDialogInitialScrollToSelection(boolean z) {
        this.ccpDialogInitialScrollToSelection = this.ccpDialogInitialScrollToSelection;
    }

    public void overrideClickListener(View.OnClickListener onClickListener) {
        this.customClickListener = onClickListener;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        CountryCodeDialog.clear();
        super.onDetachedFromWindow();
    }

    public enum Language {
        AFRIKAANS("af"),
        ARABIC("ar"),
        BENGALI("bn"),
        CHINESE_SIMPLIFIED("zh", "CN", "Hans"),
        CHINESE_TRADITIONAL("zh", "TW", "Hant"),
        CZECH("cs"),
        DANISH("da"),
        DUTCH("nl"),
        ENGLISH("en"),
        FARSI("fa"),
        FRENCH("fr"),
        GERMAN("de"),
        GREEK("el"),
        GUJARATI("gu"),
        HEBREW("iw"),
        HINDI("hi"),
        INDONESIA("in"),
        ITALIAN("it"),
        JAPANESE("ja"),
        KAZAKH("kk"),
        KOREAN("ko"),
        MARATHI("mr"),
        POLISH("pl"),
        PORTUGUESE("pt"),
        PUNJABI("pa"),
        RUSSIAN("ru"),
        SLOVAK("sk"),
        SLOVENIAN("si"),
        SPANISH("es"),
        SWEDISH("sv"),
        TAGALOG("tl"),
        TURKISH("tr"),
        UKRAINIAN("uk"),
        URDU("ur"),
        UZBEK("uz"),
        VIETNAMESE("vi");
        
        private String code;
        private String country;
        private String script;

        private Language(String str, String str2, String str3) {
            this.code = str;
            this.country = str2;
            this.script = str3;
        }

        private Language(String str) {
            this.code = str;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public String getCountry() {
            return this.country;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public String getScript() {
            return this.script;
        }

        public void setScript(String str) {
            this.script = str;
        }
    }

    public enum AutoDetectionPref {
        SIM_ONLY("1"),
        NETWORK_ONLY(ExifInterface.GPS_MEASUREMENT_2D),
        LOCALE_ONLY(ExifInterface.GPS_MEASUREMENT_3D),
        SIM_NETWORK("12"),
        NETWORK_SIM("21"),
        SIM_LOCALE("13"),
        LOCALE_SIM("31"),
        NETWORK_LOCALE("23"),
        LOCALE_NETWORK("32"),
        SIM_NETWORK_LOCALE("123"),
        SIM_LOCALE_NETWORK("132"),
        NETWORK_SIM_LOCALE("213"),
        NETWORK_LOCALE_SIM("231"),
        LOCALE_SIM_NETWORK("312"),
        LOCALE_NETWORK_SIM("321");
        
        String representation;

        private AutoDetectionPref(String str) {
            this.representation = str;
        }

        public static AutoDetectionPref getPrefForValue(String str) {
            for (AutoDetectionPref autoDetectionPref : values()) {
                if (autoDetectionPref.representation.equals(str)) {
                    return autoDetectionPref;
                }
            }
            return SIM_NETWORK_LOCALE;
        }
    }

    public enum TextGravity {
        LEFT(-1),
        CENTER(0),
        RIGHT(1);
        
        int enumIndex;

        private TextGravity(int i) {
            this.enumIndex = i;
        }
    }
}
