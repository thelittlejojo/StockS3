package fr.johann.proto.s3;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

/**
 * Cette permet d'accéder au stockage Objet en encapsulant la gestion des accès
 * au S3 (Sécurité, gestion des erreurs et de la configuration)
 */
public class S3Dao {
    /** Connexion au S3 permettant d'exécuter les actions sur l'API de stockage */
    protected AmazonS3 connexionS3 = null;
    /** Nom du bucket */
    protected String bucketName = null;
    /** Identifiant de connexon */
    protected String accesKey = null;
    /** Mot de passe */
    protected String secretKey = null;
    /** Adresse du serveur de stockage http://url:port */
    protected String urlSO = null;

    /**
     * Instanciation de l'interface d'accès au S3
     *
     * @param pBucketName nom du bucket
     * @param pAccesKey Identifiant permettant d'accéder au namespace
     * @param pSecretKey Mot de passe de connexion
     * @param pUrlSO Adresse de stockage
     */
    public S3Dao( String pBucketName, String pAccesKey, String pSecretKey, String pUrlSO) {
        this.bucketName = pBucketName;
        this.accesKey = pAccesKey;
        this.secretKey = pSecretKey;
        this.urlSO = pUrlSO;
    }

    /**
     * Méthode permettant de créer un bucket
     *
     * @param pBucketName nom du bucket
     */
    public void createBucket(String pBucketName) {
        bucketName = pBucketName;
        createBucket();
    }
    /**
     * Méthode permettant de créer un bucket
     */
    public void createBucket() {
        if (connexionS3 != null) {
            if (!connexionS3.doesBucketExistV2(bucketName)) {
                // Création d'un Bucket
                connexionS3.createBucket(bucketName);
            } else {
                System.out.println("Bucket Existant : " + bucketName);
            }
        } else {
            System.out.println("Pas de connexion valide");
        }
    }

    /**
     * Connexion au système de stockage
     */

    public void connexion() {
        AWSCredentials credentials = new BasicAWSCredentials(accesKey, secretKey);

        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);

        connexionS3 = new AmazonS3Client(credentials, clientConfig);
        connexionS3.setEndpoint(urlSO);
    }

    /**
     * Publier un objet de type chaine de caractères
     *
     * @param pCle Clé de stockage
     * @param pObject Objet à stocker de format String
     */
    void putObjectAsString(String pCle, String pObject) {
        connexionS3.putObject(bucketName, pCle,pObject);
    }

    /**
     * Publier un objet de type binaire
     *
     * @param pCle Clé associé au fichier
     * @param nomFichier Nomfichier à stocker
     * @param pMeta Metadonnée associé à l'objet
     */
    void putObjectAsString(String pCle, String nomFichier,ObjectMetadata pMeta) {
        PutObjectRequest request = new PutObjectRequest(bucketName, pCle, new File(nomFichier));
        request.setMetadata(pMeta);
        connexionS3.putObject(request);
    }
}
