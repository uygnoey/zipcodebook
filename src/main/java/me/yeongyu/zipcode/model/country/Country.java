package me.yeongyu.zipcode.model.country;

import lombok.Data;
import lombok.ToString;
import me.yeongyu.zipcode.model.type.LangType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class Country {

    private Integer id;
    private String code;
    private String country;
    private String countryExt;
    private String countryEng;
    private String countryEngExt;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date updateDate;

    List<LangType> langTypeList;
}
