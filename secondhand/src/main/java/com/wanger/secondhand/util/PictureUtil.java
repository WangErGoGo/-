package com.wanger.secondhand.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

public class PictureUtil {

    static Log log = LogFactory.getLog(PictureUtil.class);





    public static String uploadImage(MultipartFile image)  {
        try {
            String name = image.getOriginalFilename();
            InputStream inputStream = image.getInputStream();

            String fileName = FileCreateNameUtils.toCreateName();
            String fileType = name.substring(name.indexOf("."));

            Path directory  = Paths.get("E:/image");
            if(!Files.exists(directory)){
                Files.createDirectories(directory);
            }

            File f = new File("E:/image/" + fileName + fileType);
            while (f.exists()){
                fileName = FileCreateNameUtils.toCreateName();
                f = new File("E:/image/" + fileName + fileType);
            }
            Files.copy(inputStream, directory.resolve(fileName + fileType));
            return StaticUtils.HOST+"/"+StaticUtils.PREFIX+"/"+fileName+fileType;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "";
        }

    }


    public static int  uploadPics(List<MultipartFile> file) {
        int index = 0;
        for (MultipartFile multipartFile : file) {
            if (file.isEmpty()) {
                continue;
            }
            try {
                //工具类生成文件名
                String fileName = FileCreateNameUtils.toCreateName();
                String originalName = multipartFile.getOriginalFilename();
                //获取文件后缀名
                String fileType = originalName.substring(originalName.indexOf("."));
                File f = new File("E:/image/" + fileName + fileType);
                if (f.exists()) {
                    //判断这个文件是否存在，若存在则变换文件名 防止覆盖
                    fileName = FileCreateNameUtils.toCreateName();
                }
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get("E:/image/" + fileName + fileType);
                //写入磁盘
                Files.write(path, bytes);
                index++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return index;
    }


    //使用流将图片输出

    public  static void  getImage(HttpServletResponse response, String name) throws IOException {
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(Files.readAllBytes(Paths.get("/").resolve(name)));
        outputStream.flush();
        outputStream.close();
    }
}
