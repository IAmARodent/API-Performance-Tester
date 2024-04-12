package com.example.demo;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
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

import java.util.List;

public class MakeAllObjectPublic {

    public static void doIt(String link) {
        // Replace "your-bucket-name" with your actual bucket name
        String bucketName = "api-load-tester-html-reports";

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

        // Create a request to list objects under the folder prefix
        ListObjectsV2Request request = new ListObjectsV2Request()
                .withBucketName(bucketName)
                .withPrefix(link);

        // List objects under the folder prefix
        ListObjectsV2Result result = s3Client.listObjectsV2(request);
        List<S3ObjectSummary> objectSummaries = result.getObjectSummaries();

        for (S3ObjectSummary objectSummary : objectSummaries) {
            String objectKey = objectSummary.getKey();

            // Get the existing ACL for the object
            AccessControlList acl = s3Client.getObjectAcl(bucketName, objectKey);

            // Grant permission to the 'AllUsers' group to read the object
            acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);

            // Set the modified ACL back to the object
            s3Client.setObjectAcl(new SetObjectAclRequest(bucketName, objectKey, acl));

            System.out.println("Object " + objectKey + " in bucket " + bucketName + " is now public.");
        }

        System.out.println("All objects in bucket " + bucketName + " are now public.");
    }
}