package rltw.awards.common.util;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.micronaut.context.env.Environment;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import rltw.awards.error.model.BadPayloadException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@Singleton
public class S3Service {
    AmazonS3 s3;

    private String bucketName;

    @Inject
    Environment environment;

    public void initS3() {
        String awsAccessKey = environment.get("AWS_ACCESS_KEY", String.class).get();
        String awsSecretKey = environment.get("AWS_SECRET_KEY", String.class).get();
        this.bucketName = environment.get("AWS_BUCKET_NAME", String.class).get();

        this.s3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
                        )
                )
                .withRegion(Regions.AP_SOUTH_1)
                .build();
    }

    public String uploadFile(CompletedFileUpload fileUpload) {
        if (this.s3 == null) {
            initS3();
        }

        try {
            if (fileUpload.getSize() > 1000 * 1024) {
                throw new BadPayloadException(CollectionUtils.mapOf("message", "File too large"));
            }

            var fileName = String.format("%s.%s", UUID.randomUUID(), fileUpload.getContentType().get().getExtension());
            var metadata = new ObjectMetadata();

            metadata.setContentType(fileUpload.getContentType().get().getType());

            var putObjectRequest = new PutObjectRequest(
                    bucketName, fileName, new ByteArrayInputStream(fileUpload.getBytes()), metadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            s3.putObject(putObjectRequest);

            var url = this.s3.getUrl(bucketName, fileName);

            return url.toString();
        } catch (IOException exception) {
            throw new RuntimeException("500 error");
        }
    }
}
