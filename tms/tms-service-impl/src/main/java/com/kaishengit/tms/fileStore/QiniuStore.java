package com.kaishengit.tms.fileStore;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ÆßÅ£ÎÄ¼þ´æ´¢Àà
 * @author Ñî¶¬¶¬
 */
@Component
public class QiniuStore {

    @Value("${qiniu.ak}")
    private String accessKey;
    @Value("${qiniu.sk}")
    private String secretKey;
    @Value("${qiniu.bucket}")
    private String bucket;

    public String getUploadToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }
}
