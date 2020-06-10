package com.skeleton.foundation.utils.file;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.skeleton.foundation.utils.constent.StringPool;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件下载
 */
@Component
public class FileDownloadUtil {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucket}")
    private String bucket;

    /**
     * 通过浏览器下载文件
     * @param fileName 例：starfish/032b6fc5-3be8-40ea-8cc5-77cee026966b.jpg
     * @param response
     * @throws Exception
     */
    public void downloadFileOnBrowser(String fileName, HttpServletResponse response)
            throws Exception {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(bucket, fileName);
            //告知浏览器下载文件，而不是直接打开，浏览器默认为打开
            response.setContentType("application/x-download");
            //下载文件的名称
            response.addHeader("Content-Disposition", "attachment;filename=\""
                    + fileName.substring(fileName.indexOf(StringPool.SLASHSTRING) + 1, fileName.length()) + "\"");
            inputStream = ossObject.getObjectContent();
            outputStream = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
                outputStream.flush();
            }
        } finally {
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }

    /**
     * 下载文件到指定目录
     * @param fileName 例：starfish/032b6fc5-3be8-40ea-8cc5-77cee026966b.jpg
     * @param path     例：d:/032b6fc5-3be8-40ea-8cc5-77cee026966b.jpg
     * @throws Exception
     */
    public void downloadFileOnPath(String fileName, String path) throws Exception{
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
            ossClient.getObject(new GetObjectRequest(bucket, fileName), new File(path));
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }

}
