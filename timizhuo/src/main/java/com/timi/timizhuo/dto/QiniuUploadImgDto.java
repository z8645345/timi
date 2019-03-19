package com.timi.timizhuo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 七牛上传图片dto
 * @Auther timi
 * @Date 2019/3/19 0019
 */
@Data
public class QiniuUploadImgDto implements Serializable {

    private String base64;

    private Integer namespaceType;
}
