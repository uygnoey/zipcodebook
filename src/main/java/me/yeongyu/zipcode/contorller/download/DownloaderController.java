package me.yeongyu.zipcode.contorller.download;

import lombok.extern.slf4j.Slf4j;
import me.yeongyu.zipcode.constants.FileName;
import me.yeongyu.zipcode.service.download.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/download/zipcode")
public class DownloaderController {

    @Autowired
    DownloadService downloadService;

    @GetMapping("/kor")
    public void downloadKoreaZipcode(@Value("${zipcode.downloadurl.korea}") String url) {
        log.info("URL: {}", url);
        downloadService.downLoadZipcode(url, FileName.KOR_ALL);
    }

    @GetMapping("/jpn/english")
    public void downloadJpnEnglishZipcode(@Value("${zipcode.downloadurl.japan.english}") String url) {
        log.info("URL: {}", url);
        downloadService.downLoadZipcode(url, FileName.JPN_ENGLISH_ALL);
    }

    @GetMapping("/jpn/oogaki")
    public void downloadJpnOogakiZipcode(@Value("${zipcode.downloadurl.japan.oogaki}") String url) {
        log.info("URL: {}", url);
        downloadService.downLoadZipcode(url, FileName.JPN_OOGAKI_ALL);
    }

    @GetMapping("/jpn/kogaki")
    public void downloadJpnKogakiZipcode(@Value("${zipcode.downloadurl.japan.kogaki}") String url) {
        log.info("URL: {}", url);
        downloadService.downLoadZipcode(url, FileName.JPN_KOGAKI_ALL);
    }

}
