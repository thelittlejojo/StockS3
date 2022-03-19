package com.company;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
public class Main {


    public static void main(String[] args) {

        try {
            // Contexte d'accès au stockage S3
            AWSCredentials credentials = new BasicAWSCredentials("johann", "basseGou44");

            ClientConfiguration clientConfig = new ClientConfiguration();
            clientConfig.setProtocol(Protocol.HTTP);

            AmazonS3 conn = new AmazonS3Client(credentials, clientConfig);
            conn.setEndpoint("http://127.0.0.1:9000");
            // Création d'un Bucket
            conn.createBucket("toto");
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
    }


}
