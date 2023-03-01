package com.example.youtubeclone.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService{
    public static final String BUCKET_NAME = "my-aws-bucket-youtube-clone";

    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String filenameExtension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String key = UUID.randomUUID() + "." + filenameExtension;
        var metaData = new ObjectMetadata();
        metaData.setContentLength(multipartFile.getSize());
        metaData.setContentType(multipartFile.getContentType());

        amazonS3.putObject(new PutObjectRequest(BUCKET_NAME, key, multipartFile.getInputStream(), metaData));
        amazonS3.setObjectAcl(BUCKET_NAME, key, CannedAccessControlList.PublicRead);

        return String.valueOf(amazonS3.getUrl(BUCKET_NAME, key));
    }
}
