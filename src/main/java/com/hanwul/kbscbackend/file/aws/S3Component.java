package com.hanwul.kbscbackend.file.aws;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter @Setter
@ConfigurationProperties(prefix = "cloud.aws.s3")
@Component
public class S3Component {
    private String Bucket;
}
