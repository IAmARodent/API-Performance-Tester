package com.example.demo;
//adopted from AWS Java SDK Documentation Example Code

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.directory.model.RegionsInfo;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class UploadDirectory {
    public static void uploadDir(String dir_path, String bucket_name, String key_prefix, boolean recursive,
            boolean pause) {
        String accessKeyId = "DO00K9G63VXHVGDU3WU6";
        String secretAccessKey = "trpALBZA2AUxUBHssaPqm+vHN24U1zO23RAd1WYj83o";

        // Create BasicAWSCredentials object
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);

        // Specify the AWS region
        Regions region = Regions.US_EAST_1; // Replace with your desired region
        String endpoint = "nyc3.digitaloceanspaces.com"; // Replace with your Spaces endpoint


        AmazonS3ClientBuilder s3ClientBuilder = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, "us-east-1"));

        // Build the S3 client
        AmazonS3 s3Client = s3ClientBuilder.build();

        // Create TransferManager object
        TransferManager xfer_mgr = TransferManagerBuilder.standard()
                .withS3Client(s3Client)
                .build();
        try {
            MultipleFileUpload xfer = xfer_mgr.uploadDirectory(bucket_name,
                    key_prefix, new File(dir_path), recursive);
            // loop with Transfer.isDone()
            XferMgrProgress.showTransferProgress(xfer);
            // or block with Transfer.waitForCompletion()
            XferMgrProgress.waitForCompletion(xfer);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        xfer_mgr.shutdownNow();
    }

    
}
