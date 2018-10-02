package id.web.wfebriadi.submissionkamus.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_ENGLISH_INDONESIA = "tbl_eng_ind";
    static String TABLE_INDONESIA_ENGLISH = "tbl_ind_eng";


    static final class KamusColumns implements BaseColumns {
        static String ID = "id";
        static String WORD = "word";
        static String TRANSLATE = "translate";
    }
}
