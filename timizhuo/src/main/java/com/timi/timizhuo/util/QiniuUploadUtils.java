package com.timi.timizhuo.util;

import cn.hutool.core.util.ObjectUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.ByteArrayInputStream;

/**
 * @Description 七牛云上传工具类
 * @Auther timi
 * @Date 2019/3/19 0019
 */
public class QiniuUploadUtils {

    private static final String ACCESS_KEY = "UpK4eYeeehxZYpW3Nt7zN0F5pjILYTExaSwFU73H";
    private static final String SECRET_KEY = "tL_yLv_mQUsxdRvla9F0K2MUnf9cffKEDhezOSBI";
    private static final String BUCKET = "timizhuo";
    private static final String DOMAIN = "http://timizhuo.cn/";

    /**
     * 七牛云图片上传
     * @param base64 图片的base64字符串
     * @param namespace 命名空间
     * @param name 图片名称
     * @return
     */
    public static String imgUpload(String base64, NamespaceEnums namespace, String name) throws QiniuException {
        base64 = base64.substring(base64.indexOf(",")+1);
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        String namespaceName = "";
        if (namespace != null) {
            namespaceName = namespace.getNamespace();
        }
        String key = namespaceName + "/" + name + ".jpg";
        byte[] uploadBytes = Base64Utils.base64ToBytes(base64);
        ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return DOMAIN + putRet.key;
    }

    /**
     * 图片命名空间枚举
     */
    @Getter
    @AllArgsConstructor
    public enum NamespaceEnums {
        /**
         * 用户头像
         */
        USER_PIC("user/pic", 1),
        /**
         * 论坛图片
         */
        FORUM("forum", 2),
        ;

        private final String namespace;
        private final Integer type;

        public static NamespaceEnums getNamespaceByType(Integer type) {
            for(NamespaceEnums namespaceEnums : NamespaceEnums.values()) {
                if (ObjectUtil.equal(namespaceEnums.type, type)) {
                    return namespaceEnums;
                }
            }
            return null;
        }
    }
}
