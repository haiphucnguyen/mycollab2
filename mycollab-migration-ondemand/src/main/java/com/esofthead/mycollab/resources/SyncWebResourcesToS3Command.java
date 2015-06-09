package com.esofthead.mycollab.resources;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.esofthead.mycollab.core.utils.MimeTypesUtil;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author MyCollab Ltd
 * @since 5.0.7
 */
public class SyncWebResourcesToS3Command {

    public static void syncLocalResourcesToS3(String localPath, String s3Path) {
        AWSCredentials myCredentials = new BasicAWSCredentials("AKIAJCB7FQHPAUMBVQDA", "tbHk88+C9AAEg9fgCpiMZdGp10YaJ5RFTINkb1Qc");
        AmazonS3 s3client = new AmazonS3Client(myCredentials);
        File localFolder = new File(localPath);
        if (!localFolder.isDirectory()) {
            throw new RuntimeException(localPath + " must point to a folder");
        }
        syncFoldersToS3(s3client, localFolder, localFolder.getAbsolutePath(), s3Path);
    }

    private static void syncFoldersToS3(AmazonS3 s3client, File file, String baseFolderPath, String s3Path) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            if (files != null) {
                for (File subFile : files) {
                    if (subFile.isDirectory()) {
                        syncFoldersToS3(s3client, subFile, baseFolderPath, s3Path);
                    } else {
                        try {
                            ObjectMetadata metaData = new ObjectMetadata();
                            metaData.setCacheControl("max-age=8640000"); // we set object cache life cycle is 10 days
                            metaData.setContentType(MimeTypesUtil.detectMimeType(subFile.getAbsolutePath()));
                            metaData.setContentLength(subFile.length());

                            String objectPath = s3Path + subFile.getAbsolutePath().substring(baseFolderPath.length() + 1).replace('\\', '/');
                            PutObjectRequest request = new PutObjectRequest("mycollab_assets", objectPath, new FileInputStream(subFile), metaData);
                            s3client.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead)); // We set S3 object has public read permission
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        syncLocalResourcesToS3("D:\\Documents\\mycollab2\\mycollab-web\\src\\main\\resources\\assets", "assets/");
    }
}
