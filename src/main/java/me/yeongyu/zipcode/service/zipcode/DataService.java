package me.yeongyu.zipcode.service.zipcode;

import lombok.extern.slf4j.Slf4j;
import me.yeongyu.zipcode.constants.FilePath;
import me.yeongyu.zipcode.service.download.DownloadService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
@Service
public class DataService {


    @Autowired
    DownloadService downloadService;

    @Value("${download.root.path}")
    private String parentPath;

    public String koreanZipCode(String url) {
        try {
            File output = new File(parentPath, FilePath.KOR_ALL.getFilePath());
            List<File> files = unzip(downloadService.streamFromURL(url), Charset.forName("EUC-KR"), output);

            for (File file : files) {

                if (!file.getName().contains(".txt") && !file.getName().contains(".csv")) {
                    continue;
                }

                Reader reader = new InputStreamReader(new FileInputStream(file));
                try(CSVParser parser = new CSVParser(reader, CSVFormat.newFormat('|')
                        .withRecordSeparator("\r\n")
                        .withIgnoreSurroundingSpaces(true)
                        .withIgnoreSurroundingSpaces(true)
                        .withIgnoreEmptyLines(true)
                        .withHeader()
                        .withNullString("")
                )) {

                    for (CSVRecord record : parser) {
                        // 도로명 주소
                        log.info("우편번호: {}", record.get("\uFEFF우편번호"));
                        log.info("시도: {}", record.get("시도"));
                        log.info("시군구: {}", record.get("시군구"));
                        log.info("읍면: {}", record.get("읍면"));
                        log.info("도로명: {}", record.get("도로명"));
                        log.info("건물번호본번: {}", record.get("건물번호본번"));
                        log.info("건물번호부번: {}", record.get("건물번호부번"));
                        log.info("법정동명: {}", record.get("법정동명"));
                        log.info("시군구용건물명: {}", record.get("시군구용건물명"));


                        // 구주소
                        log.info("우편번호: {}", record.get("\uFEFF우편번호"));
                        log.info("시도: {}", record.get("시도"));
                        log.info("시군구: {}", record.get("시군구"));
                        log.info("법정동명: {}", record.get("법정동명"));
                        log.info("읍면: {}", record.get("읍면"));
                        log.info("리명: {}", record.get("리명"));
                        log.info("지번본번: {}", record.get("지번본번"));
                        log.info("지번부번: {}", record.get("지번부번"));
                        log.info("시군구용건물명: {}", record.get("시군구용건물명"));

                        // 영문주소
                        log.info("우편번호: {}", record.get("\uFEFF우편번호"));
                        log.info("건물번호본번: {}", record.get("건물번호본번"));
                        log.info("건물번호부번: {}", record.get("건물번호부번"));
                        log.info("도로명영문: {}", record.get("도로명영문"));
                        log.info("읍면영문: {}", record.get("읍면영문"));
                        log.info("시군구영문: {}", record.get("시군구영문"));
                        log.info("시도영문: {}", record.get("시도영문"));

                        log.info("도로명코드: {}", record.get("도로명코드"));
                        log.info("지하여부: {}", record.get("지하여부"));
                        log.info("건물관리번호: {}", record.get("건물관리번호"));
                        log.info("다량배달처명: {}", record.get("다량배달처명"));
                        log.info("시군구용건물명: {}", record.get("시군구용건물명"));
                        log.info("법정동코드: {}", record.get("법정동코드"));
                        log.info("행정동명: {}", record.get("행정동명"));
                        log.info("산여부: {}", record.get("산여부"));
                        log.info("읍면동일련번호: {}", record.get("읍면동일련번호"));
                        log.info("구우편번호: {}", record.get("구우편번호"));
                        log.info("우편번호일련번호: {}", record.get("우편번호일련번호"));
                    }
                }

                file.deleteOnExit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    private List<File> unzip(InputStream inputStream, File output) {
        return unzip(inputStream, StandardCharsets.UTF_8, output);
    }

    private List<File> unzip(InputStream inputStream, Charset charset, File output) {
        List<File> list = new ArrayList<>();
        ZipInputStream zis = new ZipInputStream(inputStream, charset);
        try {

            if(!output.exists()){
                output.mkdirs();
            }

            log.info("Zip Input Stream: {}", zis);
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                String fileName = ze.getName();

                log.info("FileName: {}, {}", fileName, ze.getName().contains("__MACOSX"));

                if (!ze.getName().contains("__MACOSX")) {
                    File zipcodeFile = new File(output, fileName);
                    log.info("FileName_: {}", fileName);

                    FileOutputStream fos = new FileOutputStream(zipcodeFile);
                    IOUtils.copy(zis, fos);
                    fos.close();

                    list.add(zipcodeFile);
                }
            }

            zis.closeEntry();
            zis.close();
        } catch (IOException e) {
            log.error("ERROR: ", e);
            throw new RuntimeException("Error unzip: " + e.getMessage());
        }

        return list;
    }
}
