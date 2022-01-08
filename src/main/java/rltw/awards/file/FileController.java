package rltw.awards.file;

import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import rltw.awards.common.util.S3Service;

import java.util.Map;

@Controller("/file")
@Secured(SecurityRule.IS_ANONYMOUS)
public class FileController {
    @Inject
    private S3Service s3Service;

    @Post
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Map<String, String> uploadFile(@Body CompletedFileUpload file) {
        return CollectionUtils.mapOf("link", s3Service.uploadFile(file));
    }
}
