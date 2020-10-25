package me.yeongyu.zipcode.constants;

public enum FileName {
    KOR_ALL ("korea_all.zip"),
    JPN_OOGAKI_ALL ("japan_oogaki_all.zip"),
    JPN_KOGAKI_ALL ("japan_kogaki_all.zip"),
    JPN_ENGLISH_ALL ("japan_kogaki_all.zip")
    ;

    private final String fileName;

    FileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
