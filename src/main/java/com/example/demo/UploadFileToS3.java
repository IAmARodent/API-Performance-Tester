package com.example.demo;

//adopted from AWS Java SDK Documentation Example Code
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.CompletedDirectoryUpload;
import software.amazon.awssdk.transfer.s3.model.DirectoryUpload;
import software.amazon.awssdk.transfer.s3.model.UploadDirectoryRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.UUID;

public class UploadFileToS3 {
    public String uploadDirectory(S3TransferManager transferManager, URI sourceDirectory, String bucketName) {
        DirectoryUpload directoryUpload = transferManager.uploadDirectory(UploadDirectoryRequest.builder()
                .source(Paths.get(sourceDirectory))
                .bucket(bucketName)
                .build());
        return "hi";
    }

    public String upload(){
        // Replace 'your_access_key_id' and 'your_secret_access_key' with your actual access keys
        String accessKeyId = "your_access_key_id";
        String secretAccessKey = "your_secret_access_key";

        // Initialize AwsBasicCredentials with access keys
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        // Initialize S3Client with explicit credentials
        S3Client s3 = S3Client.builder()
                        .region(Region.US_EAST_1) // specify your desired region
                        .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                        .build();

        // Now you can use the s3 client object to perform operations on S3
        S3TransferManager transferManager = S3TransferManager.create();   
        URI uri = new URI("report-output");
        UploadFileToS3 hi = new UploadFileToS3();
        hi.uploadDirectory(transferManager, uri, "api-load-tester-html-reports");
    }
}
