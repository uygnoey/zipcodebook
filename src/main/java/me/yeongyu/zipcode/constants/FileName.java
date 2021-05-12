package me.yeongyu.zipcode.constants;

public enum FileName {
    KOR_ALL ("korean_all.zip"),
    JPN_OOGAKI_ALL ("japanese_oogaki_all.zip"),
    JPN_KOGAKI_ALL ("japanese_kogaki_all.zip"),
    JPN_ENGLISH_ALL ("japanese_english_all.zip")
    ;

    private final String fileName;

    FileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
