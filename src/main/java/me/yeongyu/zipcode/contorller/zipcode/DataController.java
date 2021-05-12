package me.yeongyu.zipcode.contorller.zipcode;

import lombok.extern.slf4j.Slf4j;
import me.yeongyu.zipcode.constants.FileName;
import me.yeongyu.zipcode.model.CommonResponse;
import me.yeongyu.zipcode.service.download.DownloadService;
import me.yeongyu.zipcode.service.zipcode.DataService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Reader;

@Slf4j
@RestController
@RequestMapping("/api/zipcode/data")
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping("korean")
    public String dataKoreanZipcode(@Value("${zipcode.downloadurl.korea}") String url) {
        return dataService.koreanZipCode(url);
    }

}
