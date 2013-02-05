package com.esofthead.mycollab.usertracking;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class MybatisConfig {

    private static final String MYBATIS_PROPERTIES = "usertracking.mybatis.xml";
    private static final String RESOURCE_NAME = "/mycollab_extdb.h2.db";

    public static final String loadConfig() {
        try {
            URL location = MybatisConfig.class.getResource(RESOURCE_NAME);
            String className = "org.h2.Driver";
            File file = new File(location.getPath());
            String url = String.format("jdbc:h2:file:%s/%s", file.getParent(), "mycollab_extdb;MODE=MySQL");
            String userName = "";
            String password = "";

            String mybatisContent = getMybatisTemplateContent();
            if (null != mybatisContent) {
                return String.format(mybatisContent, className, url, userName,
                        password);
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static final String getMybatisTemplateContent() {
        try {
            InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream(MYBATIS_PROPERTIES);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int byteCount;
            while ((byteCount = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, byteCount);
            }
            inputStream.close();
            outStream.flush();
            outStream.close();

            return new String(outStream.toByteArray());
        } catch (Exception e) {
        }

        return null;
    }
}
