package io.card.payment.i18n.locales;

import java.util.HashMap;
import java.util.Map;

import io.card.payment.i18n.StringKey;
import io.card.payment.i18n.SupportedLocale;

// Class autogenerated!  Do not modify.
// Generated on 2014-12-10 11:29:25.162694 via script:
// /Users/twhipple/Documents/buffalo/lib-i18n/i18n/script/generate_android_i18n.py -java_src_path src/ -java_gen_path gen/ -stringkey_path io/card/payment/i18n/ -strings_path ../strings/projects/card.io/strings/ --strict

public class LocalizedStringsPL implements SupportedLocale<StringKey> {

    private static Map<StringKey, String> mDisplay = new HashMap<StringKey, String>();
    private static Map<String, String> mAdapted = new HashMap<String, String>();

    @Override
    public String getName() {
        return "pl";
    }

    @Override
    public String getAdaptedDisplay(StringKey key, String country) {
        String adaptedKey = key.toString() + "|" + country;
        if (mAdapted.containsKey(adaptedKey)) {
            return mAdapted.get(adaptedKey);
        } else {
            return mDisplay.get(key);
        }
    }

    public LocalizedStringsPL() {
        mDisplay.put(StringKey.CANCEL, "Anuluj");
        mDisplay.put(StringKey.CARDTYPE_AMERICANEXPRESS, "American Express");
        mDisplay.put(StringKey.CARDTYPE_DISCOVER, "Discover");
        mDisplay.put(StringKey.CARDTYPE_JCB, "JCB");
        mDisplay.put(StringKey.CARDTYPE_MASTERCARD, "MasterCard");
        mDisplay.put(StringKey.CARDTYPE_VISA, "Visa");
        mDisplay.put(StringKey.DONE, "Gotowe");
        mDisplay.put(StringKey.ENTRY_CVV, "Kod CVV2/CVC2");
        mDisplay.put(StringKey.ENTRY_POSTAL_CODE, "Kod pocztowy");
        mDisplay.put(StringKey.ENTRY_EXPIRES, "Wygasa");
        mDisplay.put(StringKey.EXPIRES_PLACEHOLDER, "MM/RR");
        mDisplay.put(StringKey.SCAN_GUIDE, "Przytrzymaj kartę tutaj.\nZostanie ona zeskanowana automatycznie.");
        mDisplay.put(StringKey.KEYBOARD, "Klawiatura…");
        mDisplay.put(StringKey.ENTRY_CARD_NUMBER, "Numer karty");
        mDisplay.put(StringKey.MANUAL_ENTRY_TITLE, "Dane karty");
        mDisplay.put(StringKey.ERROR_NO_DEVICE_SUPPORT, "Na tym urządzeniu nie można odczytać numeru karty za pomocą aparatu.");
        mDisplay.put(StringKey.ERROR_CAMERA_CONNECT_FAIL, "Aparat na tym urządzeniu jest niedostepny.");
        mDisplay.put(StringKey.ERROR_CAMERA_UNEXPECTED_FAIL, "Przy otwieraniu aparatu na tym urządzeniu wystąpił nieoczekiwany błąd.");

        // no adapted_translations found
    }
}
