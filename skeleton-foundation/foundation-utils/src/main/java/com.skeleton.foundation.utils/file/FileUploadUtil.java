package com.skeleton.foundation.utils.file;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.skeleton.foundation.utils.constent.StringPool;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * 文件上传
 **/
public class FileUploadUtil {

    @Value("${aliyun.oss.endpoint}")
    protected String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    protected String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    protected String accessKeySecret;

    @Value("${aliyun.oss.bucket}")
    protected String bucket;

    @Value("${aliyun.oss.key}")
    protected String key;

    @Value("${aliyun.oss.domain}")
    protected String domain;

    /**
     * 文件上传
     *
     * @throws Exception
     */
    public String uploadFile(MultipartFile multipartFile) throws Exception {
        StringBuilder filePath = new StringBuilder();
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(StringPool.DOT));
        // 用uuid作为文件名，防止生成的临时文件名重复
        fileName = UUID.randomUUID().toString();
        File file = File.createTempFile(fileName, suffix);
        fileName = fileName.concat(suffix);
        // MultipartFile to File
        multipartFile.transferTo(file);
        file.deleteOnExit();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = null;
        try {
            // 上传文件流。
            inputStream = new FileInputStream(file);
            ossClient.putObject(bucket, key.concat(fileName), inputStream);
        } finally {
            IOUtils.closeQuietly(inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        //获取文件 url
        return filePath.append(domain).append(key).append(fileName).toString();
    }

}
