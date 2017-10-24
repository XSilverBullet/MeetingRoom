package com.siemens.cms.message.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Simon.Lau on 2016/8/22.
 */
public class FileUtil {

    private FileUtil() {

    }

    public static String loadContentFromFile(String fileName) {
        if (fileName == null) {
            return null;
        }

        String textContent = null;
        try {
            InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                return null;
            }

            StringBuilder stringBuilder = new StringBuilder("");

            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buf)) > 0) {
                String tmp = new String(buf, 0, len, "UTF-8");
                stringBuilder.append(tmp);
            }

            inputStream.close();

            textContent = stringBuilder.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textContent;
    }

}
