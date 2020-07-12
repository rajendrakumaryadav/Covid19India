package in.co.dreamsdoor.covid19india.utils;

public class Config {
    private final String CASE_COUNT_STATE_WISE =
        "https://api.rootnet.in/covid19-in/stats/latest";
    private final String ICMR_BASE_URL =
        "https://icmr.nic.in/sites/default/files/whats_new/";
    private final String BEDS_AVAILABILITY_STATE_WISE =
        "https://api.rootnet.in/covid19-in/hospitals/beds";
    private final String MEDCOLLEGE_BEDS_AVAILABILITY_STATECITY_WISE =
        "https://api.rootnet.in/covid19-in/hospitals/medical-colleges";
    private final String CONTACTS_AND_HELPLINES =
        "https://api.rootnet.in/covid19-in/contacts";
    private final String OFFICIAL_NOTIFICATION_BY_ICMR =
        "https://api.rootnet.in/covid19-in/notifications";
    private final String DEVELOPERS_WEBSITE =
        "https://github.com/rajendrakumaryadav/";

    public String getCASE_COUNT_STATE_WISE() {
        return CASE_COUNT_STATE_WISE;
    }

    public String getICMR_BASE_URL() {
        return ICMR_BASE_URL;
    }

    public String getBEDS_AVAILABILITY_STATE_WISE() {
        return BEDS_AVAILABILITY_STATE_WISE;
    }

    public String getMEDCOLLEGE_BEDS_AVAILABILITY_STATECITY_WISE() {
        return MEDCOLLEGE_BEDS_AVAILABILITY_STATECITY_WISE;
    }

    public String getCONTACTS_AND_HELPLINES() {
        return CONTACTS_AND_HELPLINES;
    }

    public String getOFFICIAL_NOTIFICATION_BY_ICMR() {
        return OFFICIAL_NOTIFICATION_BY_ICMR;
    }

    public String getDEVELOPERS_WEBSITE() {
        return DEVELOPERS_WEBSITE;
    }

}
