package me.yeongyu.zipcode.service.type;

import lombok.extern.slf4j.Slf4j;
import me.yeongyu.zipcode.mapper.type.TypeMapper;
import me.yeongyu.zipcode.model.type.LangType;
import me.yeongyu.zipcode.model.type.SearchLangType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TypeService {

    @Autowired
    TypeMapper typeMapper;

    public List<LangType> getList (SearchLangType search) {
        return typeMapper.getList(search);
    }

    public Integer getCount (SearchLangType search) {
        return typeMapper.getCount(search);
    }

    public LangType getOne(LangType langType) {
        return typeMapper.getOne(langType);
    }

    public int insert(String type) {
        if (this.isOverlap(type)){
            return 0;
        }

        return typeMapper.insert(type);
    }

    public int update(LangType langType) {
        if (this.isOverlap(langType.getType())) {
            return 0;
        }

        return typeMapper.update(langType);
    }

    public int delete(Integer id) {
        return typeMapper.delete(id);
    }

    private Boolean isOverlap (String type) {
        LangType langType = new LangType();
        langType.setType(type);

        log.info("LangType OVERLAP CHECK: {}", langType);
        langType = this.getOne(langType);

        boolean result = null != langType && null != langType.getId();
        log.info("LangType OVERLAP CHECK RESULT: {}, {}", langType, result);

        return result;
    }

}
