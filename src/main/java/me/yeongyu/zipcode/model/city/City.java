package me.yeongyu.zipcode.model.city;

import lombok.Data;
import lombok.ToString;
import me.yeongyu.zipcode.model.address.Address;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class City {

    private Integer id;
    private Integer typeId;
    private Integer stateId;
    private String city;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date updateDate;

    List<Address> addressList;
}
