package com.mycollab.web.optional;

import com.squareup.javawriter.JavaWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.EnumSet;

import static javax.lang.model.element.Modifier.*;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class WebResourceGenerator {
    private static String ROOT_PATH = "src/main/resources/assets/icons";

    private static void emitFieldResources(JavaWriter jw, File baseFolder) throws IOException {
        File[] files = baseFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String filePath = file.getPath();
                    filePath = filePath.substring(ROOT_PATH.length() + 1);
                    int index = filePath.lastIndexOf('.');
                    if (index < 0) {
                        throw new RuntimeException("File resource " + filePath + " is not valid");
                    }
                    String resourceName = "_" + filePath.substring(0, index).replace('/', '_').replace('-', '_');
                    jw.emitField("String", resourceName, EnumSet.of(PUBLIC, STATIC, FINAL), "\"icons/" + filePath + "\"");
                } else {
                    emitFieldResources(jw, file);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File imagesFolder = new File(ROOT_PATH);

        StringWriter w = new StringWriter();
        JavaWriter jw = new JavaWriter(w);
        jw.emitPackage("com.mycollab.vaadin.ui").beginType(
                "com.mycollab.vaadin.ui.WebResourceIds", "class",
                EnumSet.of(PUBLIC, FINAL));

        emitFieldResources(jw, imagesFolder);
        jw.endType();

        FileWriter fileWriter = new FileWriter(new File("src/main/java/com.mycollab/vaadin/ui/WebResourceIds.java"));
        fileWriter.write(w.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}
