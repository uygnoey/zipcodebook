package me.yeongyu.zipcode.model.type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class SearchLangType extends LangType {

    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date startDate;
    private Date endDate;
    Integer offset, limit;

}
