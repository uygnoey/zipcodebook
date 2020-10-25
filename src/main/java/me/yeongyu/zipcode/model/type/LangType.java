package me.yeongyu.zipcode.model.type;

import lombok.Data;
import lombok.ToString;
import me.yeongyu.zipcode.model.state.State;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class LangType {

    private Integer id;
    private String type;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date updateDate;

    List<State> stateList;
}
