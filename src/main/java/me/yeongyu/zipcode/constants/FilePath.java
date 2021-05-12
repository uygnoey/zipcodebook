package me.yeongyu.zipcode.constants;

public enum FilePath {
    KOR_ALL ("korean"),
    JPN_OOGAKI_ALL ("japanese_oogaki"),
    JPN_KOGAKI_ALL ("japanese_kogaki"),
    JPN_ENGLISH_ALL ("japanese_english")
    ;

    private final String filePath;

    FilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }
}
