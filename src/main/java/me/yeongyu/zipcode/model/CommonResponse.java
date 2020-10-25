package me.yeongyu.zipcode.model;

import lombok.Data;

import java.util.List;

@Data
public class CommonResponse<T> {

    private List<T> list;
    private Integer totalCount;
    private Integer limit;
    private Integer offset;

    public CommonResponse(List<T> list, Integer totalCount, Integer limit, Integer offset) {

        this.list = list;
        this.totalCount = totalCount;
        this.limit = limit;
        this.offset = offset;

    }
}
