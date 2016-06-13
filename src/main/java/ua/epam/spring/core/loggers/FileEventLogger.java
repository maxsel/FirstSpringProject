package ua.epam.spring.core.loggers;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import ua.epam.spring.core.Event;

import java.io.File;
import java.io.IOException;

/**
 * Created by Maksim_Sialiuk on 6/1/2016.
 */
@Component
public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void init() throws IOException {
        this.file = new File(filename);

        /*if (!file.canWrite()) {
            throw new IOException();
        }*/
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
