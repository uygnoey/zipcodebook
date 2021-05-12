package me.yeongyu.zipcode.service.download;

import lombok.extern.slf4j.Slf4j;
import me.yeongyu.zipcode.constants.FileName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Slf4j
@Service
public class DownloadService {

    @Value("${download.root.path}")
    private String DOWNLOAD_DIR_PATH;

    public void downLoadZipcode(String url, FileName fileName) {
        log.info("- Downloading from: " + url);

        String FileName = DOWNLOAD_DIR_PATH + fileName.getFileName();

        File destination = new File(FileName);
        if (!destination.getParentFile().exists()) {
            if (!destination.getParentFile().mkdirs()) {
                log.error("- ERROR creating destination directory '" + destination.getParentFile().getAbsolutePath() + "'");
            }
        }

        log.info("- Downloading to: " + destination.getAbsolutePath());
        try {
            downloadFileFromURL(url, destination);
            log.info("- Download done");
        } catch (Throwable e) {
            log.error("- Error downloading", e);
        }
    }

    public InputStream streamFromURL(String urlString) throws IOException {
        URL website = new URL(urlString);
        return website.openStream();
    }

    private static void downloadFileFromURL(String urlString, File destination) throws Exception {
        URL website = new URL(urlString);
        ReadableByteChannel rbc;
        rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(destination);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }


}
