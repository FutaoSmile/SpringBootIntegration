package com.futao.springbootdemo.controller.business;

import com.futao.springbootdemo.model.entity.SingleValueResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作
 *
 * @author futao
 * Created on 2018/9/26.
 */
@Api("文件操作")
@RestController
@RequestMapping(path = "file")
public class FileController {
    /**
     * 上传文件
     *
     * @param file 文件
     * @return
     */
    @ApiOperation("上传文件")
    @PostMapping(path = "upload")
    public SingleValueResult upload(
            @RequestParam(value = "file") MultipartFile file
    ) {
        try {
            System.out.println(file.getSize());
            FileUtils.writeByteArrayToFile(new File("/Users/futao/src/backend/fun/" + file.getOriginalFilename()), file.getBytes());
            return new SingleValueResult("上传成功!!!");
        } catch (IOException e) {
            e.printStackTrace();
            return new SingleValueResult("上传失败!!!" + e.getMessage());
        }

    }
}
