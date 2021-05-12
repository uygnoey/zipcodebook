package me.yeongyu.zipcode.service.type;

import lombok.extern.slf4j.Slf4j;
import me.yeongyu.zipcode.mapper.type.TypeMapper;
import me.yeongyu.zipcode.model.type.LangType;
import me.yeongyu.zipcode.model.type.SearchLangType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class TypeService {

    @Autowired
    TypeMapper typeMapper;

    public List<LangType> getList(SearchLangType search) {
        return typeMapper.getList(search);
    }

    public Integer getCount(SearchLangType search) {
        return typeMapper.getCount(search);
    }

    public LangType getOne(LangType langType) {
        return typeMapper.getOne(langType);
    }

    public int insert(List<String> typeList) {
        return typeList.stream()
                .filter(type -> !StringUtils.isBlank(type))
                .mapToInt(type -> {
                    if (this.isOverlap(type)) {
                        return 0;
                    }

                    log.info("INSERT LangType: {}", type);
                    return typeMapper.insert(type);
                }).sum();

    }

    public int update(List<LangType> langTypeList) {
        return langTypeList.stream()
                .filter(Objects::nonNull)
                .filter(langType -> langType.getId() != null && !StringUtils.isBlank(langType.getType()))
                .mapToInt(langType -> {
                    if (this.isOverlap(langType.getType())) {
                        return 0;
                    }

                    log.info("UPDATE LangType: {}", langType);
                    return typeMapper.update(langType);
                }).sum();
    }

    public int delete(Integer id) {
        return typeMapper.delete(id);
    }

    private Boolean isOverlap(String type) {
        LangType langType = new LangType();
        langType.setType(type);

        log.info("LangType OVERLAP CHECK: {}", langType);
        langType = this.getOne(langType);

        boolean result = null != langType && null != langType.getId();
        log.info("LangType OVERLAP CHECK RESULT: {}, {}", langType, result);

        return result;
    }

}
