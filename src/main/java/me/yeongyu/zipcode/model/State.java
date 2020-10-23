package me.yeongyu.zipcode.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class State {

    private Integer id;
    private Integer typeId;
    private Integer countryId;
    private String state;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date updateDate;

    List<State> stateList;
}
