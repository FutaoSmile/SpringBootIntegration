package com.futao.springmvcdemo.controller.business;

import com.futao.springmvcdemo.model.entity.SingleValueResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author futao
 * Created on 2018/9/26.
 */
@Api("文件操作")
@RestController
@RequestMapping(path = "file")
public class FileController {

//    @PostMapping(path = "upload")
//    public SingleValueResult upload(
//            HttpServletRequest request,
//            @RequestParam("description") String description,
//            @RequestParam("file") MultipartFile file
//    ) throws IOException {
//        //如果问价不为空，写入上传路径
//        if (!file.isEmpty()) {
//            String savePath = request.getServletContext().getRealPath("/images/");
//            //上传文件名
//            String fileName = file.getOriginalFilename();
//            File filePath = new File(savePath, fileName);
//            //判断路径是否存在，不存在就创建一个
//            if (!filePath.getParentFile().exists()) {
//                filePath.getParentFile().mkdir();
//            }
//            //将上传文件保存到一个目标文件中
//            file.transferTo(new File(filePath + File.separator + fileName));
//            return new SingleValueResult("上传成功");
//        } else {
//            return new SingleValueResult("上传失败");
//        }
//    }

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
