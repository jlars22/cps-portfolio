package org.cpsportfolio.backend.util;

import java.util.HashMap;
import java.util.Map;

public class CountryCodes {

    /*
     * I know this is cursed, but it seems to be the most reliable way to do this
     * Since my external API sometimes returns the full country name and sometimes an alpha2 or alpha3 countrycode,
     * I could not think of a better way to do this. Maybe I could use an external API to convert,
     * but that would be a lot of HTTP requests and I don't want to rely on an external API for this.
     *
     * Found original map here: https://stackoverflow.com/questions/14155049/iso2-country-code-from-country-name
     */

    final Map<String, String> map = new HashMap<>();

    public CountryCodes() {
        map.put("United Arab Emirates", "AE");
        map.put("UAE", "AE");
        map.put("Afghanistan, Islamic State Of", "AF");
        map.put("Argentina", "AR");
        map.put("Austria", "AT");
        map.put("Australia", "AU");
        map.put("Azerbaijan", "AZ");
        map.put("Bangladesh", "BD");
        map.put("Belgium", "BE");
        map.put("Bulgaria", "BG");
        map.put("Bahrain", "BH");
        map.put("Brazil", "BR");
        map.put("Canada", "CA");
        map.put("Switzerland", "CH");
        map.put("Chile", "CL");
        map.put("Cameroon", "CM");
        map.put("China", "CN");
        map.put("Colombia", "CO");
        map.put("Costa Rica", "CR");
        map.put("Cuba", "CU");
        map.put("Cyprus", "CY");
        map.put("Czech Republic", "CZ");
        map.put("Germany", "DE");
        map.put("Denmark", "DK");
        map.put("Dominica", "DM");
        map.put("Dominican Republic", "DO");
        map.put("Egypt", "EG");
        map.put("Spain", "ES");
        map.put("Finland", "FI");
        map.put("France", "FR");
        map.put("Great Britain", "GB");
        map.put("Hungary", "HU");
        map.put("Indonesia", "ID");
        map.put("Ireland", "IE");
        map.put("Israel", "IL");
        map.put("India", "IN");
        map.put("Iraq", "IQ");
        map.put("Iran", "IR");
        map.put("Iceland", "IS");
        map.put("Italy", "IT");
        map.put("Jamaica", "JM");
        map.put("Jordan", "JO");
        map.put("Japan", "JP");
        map.put("Kenya", "KE");
        map.put("North Korea", "KP");
        map.put("South Korea", "KR");
        map.put("Kazakhstan", "KZ");
        map.put("Lebanon", "LB");
        map.put("Saint Lucia", "LC");
        map.put("Liechtenstein", "LI");
        map.put("Sri Lanka", "LK");
        map.put("Liberia", "LR");
        map.put("Lesotho", "LS");
        map.put("Lithuania", "LT");
        map.put("Luxembourg", "LU");
        map.put("Latvia", "LV");
        map.put("Libya", "LY");
        map.put("Morocco", "MA");
        map.put("Monaco", "MC");
        map.put("Moldavia", "MD");
        map.put("Madagascar", "MG");
        map.put("Macedonia", "MK");
        map.put("Malta", "MT");
        map.put("Mexico", "MX");
        map.put("Nigeria", "NG");
        map.put("Netherlands", "NL");
        map.put("Norway", "NO");
        map.put("New Zealand", "NZ");
        map.put("Philippines", "PH");
        map.put("Poland", "PL");
        map.put("Puerto Rico", "PR");
        map.put("Portugal", "PT");
        map.put("Paraguay", "PY");
        map.put("Qatar", "QA");
        map.put("Romania", "RO");
        map.put("Saudi Arabia", "SA");
        map.put("Sweden", "SE");
        map.put("Singapore", "SG");
        map.put("Slovenia", "SI");
        map.put("Slovak Republic", "SK");
        map.put("Senegal", "SN");
        map.put("Thailand", "TH");
        map.put("Turkey", "TR");
        map.put("Taiwan", "TW");
        map.put("Tanzania", "TZ");
        map.put("Ukraine", "UA");
        map.put("Uganda", "UG");
        map.put("United Kingdom", "GB");
        map.put("UK", "GB");
        map.put("United States", "US");
        map.put("USA", "US");
        map.put("Uruguay", "UY");
        map.put("Vietnam", "VN");
        map.put("South Africa", "ZA");
    }

    public String getCode(String country) {
        String countryFound = map.get(country);
        if (countryFound == null) {
            return country;
        }
        return countryFound;
    }
}
