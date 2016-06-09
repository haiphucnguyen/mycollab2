package com.getlocalization;

import com.getlocalization.api.GLProject;
import com.getlocalization.api.files.FileFormat;
import com.getlocalization.api.files.GLMasterFile;

import java.io.File;

/**
 * @author MyCollab Ltd
 * @since 5.3.3
 */
public class SyncFiles {
    public static void main(String[] args) throws Exception {
        GLProject project = new GLProject("mycollab", "haiphucnguyen@gmail.com", "=5EqGRN5Y=<%`tbX");

        File parentFolder = new File("target/staging/i18n");
        File[] files = parentFolder.listFiles();
        for (File file : files) {
            System.out.println("File: " + file.getName());
            if (file.getName().endsWith("en-US.properties")) {
                GLMasterFile masterFile = new GLMasterFile(project, file.getAbsolutePath(), FileFormat
                        .javaproperties);
                masterFile.push();
            } else if (file.getName().endsWith("html")) {
//                GLMasterFile masterFile = new GLMasterFile(project, file.getAbsolutePath(), FileFormat.html);
//                masterFile.push();
            } else {
//                throw new Exception("Not valid file " + file.getName());
            }
        }
    }
}
