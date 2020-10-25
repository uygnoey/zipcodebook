package me.yeongyu.zipcode.contorller.type;

import lombok.extern.slf4j.Slf4j;
import me.yeongyu.zipcode.model.CommonResponse;
import me.yeongyu.zipcode.model.type.LangType;
import me.yeongyu.zipcode.model.type.SearchLangType;
import me.yeongyu.zipcode.service.type.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("/")
    public CommonResponse<LangType> getList(SearchLangType search) {
        log.info("GET LangType list w/ search options: {}", search);
        return new CommonResponse<>(typeService.getList(search), typeService.getCount(search), search.getLimit(), search.getOffset());
    }

    @GetMapping("/{id}")
    public LangType getOne(@PathVariable Integer id) {
        log.info("GET LangType ID: {}", id);

        LangType langType = new LangType();
        langType.setId(id);

        return typeService.getOne(langType);
    }

    @PostMapping("/")
    public int insert(@RequestBody List<String> typeList) {
        log.info("INSERT Type List: {}", typeList);

        return typeList.stream()
                .filter(type-> !StringUtils.isBlank(type))
                .mapToInt(type -> {
                    log.info("INSERT LangType: {}", type);
                    return typeService.insert(type);
                }).sum();
    }

    @PutMapping("/")
    public int update(@RequestBody List<LangType> langTypeList) {
        log.info("UPDATE Type List: {}", langTypeList);


        return langTypeList.stream()
                .filter(Objects::nonNull)
                .filter(langType -> langType.getId() != null && !StringUtils.isBlank(langType.getType()))
                .mapToInt(langType -> {
                    log.info("UPDATE LangType: {}", langType);
                    return typeService.update(langType);
                }).sum();
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {
        log.info("DELETE LangType ID: {}", id);
        return typeService.delete(id);
    }
}
