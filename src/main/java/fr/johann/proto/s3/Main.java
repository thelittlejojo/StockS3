package fr.johann.proto.s3;

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
import fr.johann.proto.s3.om.Individu;

public class Main {


    public static void main(String[] args) {

        try {
            S3Dao s3;
            String bucketName = "toto";
            String bucketlocation = null;

            // Création de l'interface d'accès au stockage
            s3 = new S3Dao(bucketName,"johann","basseGou44","http://127.0.0.1:9000");

            // Connexion au stockage
            s3.connexion();

            // Création du bucket s'il n'existe pas
            s3.createBucket("toto");


            Individu ind = new Individu("Johann", "LeDeux");

        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
    }


}
