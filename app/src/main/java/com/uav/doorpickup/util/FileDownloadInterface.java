package  com.uav.doorpickup.util;

import java.io.File;

public interface FileDownloadInterface {

    void downloadComplete(File file);
    void error(String error);
}
