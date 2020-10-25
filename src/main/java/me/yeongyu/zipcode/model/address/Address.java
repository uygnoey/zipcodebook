package me.yeongyu.zipcode.model.address;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class Address {

    private Integer id;
    private Integer typeId;
    private Integer cityId;
    private String zipcode;
    private String oldZipcode;
    private String address;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date updateDate;

}
