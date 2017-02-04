package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
public class FileController extends BaseController {

    @RequestMapping("/image/upload")
    @ResponseBody
    public String uploadImage(HttpServletRequest request) throws UnsupportedEncodingException {
        Result<Map<String, String>> result = new Result<>();
        result.setCode(-1);

        if (!ServletFileUpload.isMultipartContent(request)) {
            result.setMessage("请选择上传的文件");
            return result.toString();
        }

        request.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem fileItem : items) {
                if (!fileItem.isFormField()) {
                    if (!StringUtils.isEmpty(fileItem.getName())) {
                        if (!fileItem.getName().contains(".")) {
                            result.setMessage("上传的图片类型错误");
                            return result.toString();
                        }
                        if (fileItem.getSize() > 1024 * Config.getInt("image.max.size")) {
                            result.setMessage("最大可上传" + Config.getInt("image.max.size") + "k的图片");
                            return result.toString();
                        }
                        boolean isAllow = false;
                        String suffix = fileItem.getName().split("\\.")[1];
                        String[] suffixs = Config.get("image.allow.type").split(",");
                        for (String str : suffixs) {
                            if (str.equalsIgnoreCase(suffix)) {
                                isAllow = true;
                                break;
                            }
                        }
                        if (!isAllow) {
                            result.setMessage("上传的图片类型错误");
                            return result.toString();
                        }
                        LOGGER.debug("上传文件的大小:" + fileItem.getSize());
                        LOGGER.debug("上传文件的类型:" + fileItem.getContentType());
                        LOGGER.debug("上传文件的名称:" + fileItem.getName());
                        //上传文件的保存路径
                        String[] strs = StrUtil.getFilePath("image");
                        String fileName = System.currentTimeMillis() + "." + fileItem.getName().split("\\.")[1];
                        File dir = new File(strs[0]);
                        if (!dir.exists())
                            dir.mkdir();
                        File f = new File(dir, fileName);
                        if (f.exists()) {
                            f.delete();
                        }
                        f.createNewFile();
                        fileItem.write(f);
                        Map<String, String> data = new HashMap<>();
                        data.put("name", fileItem.getName());
                        data.put("path", strs[1] + "/" + fileName);
                        result.set(1, "logo上传成功", data);
                        return result.toString();
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("logo上传失败", e);
            result.setMessage("logo上传失败");
        }
        return result.toString();
    }

    @RequestMapping("/file/upload")
    @ResponseBody
    public String uploadFile(HttpServletRequest request) throws UnsupportedEncodingException {
        Result<Map<String, String>> result = new Result<>();
        result.setCode(-1);

        if (!ServletFileUpload.isMultipartContent(request)) {
            result.setMessage("请选择上传的文件");
            return result.toString();
        }

        request.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem fileItem : items) {
                if (!fileItem.isFormField()) {
                    if (!StringUtils.isEmpty(fileItem.getName())) {
                        if (!fileItem.getName().contains(".")) {
                            result.setMessage("上传的文件类型错误");
                            return result.toString();
                        }
                        if (fileItem.getSize() > 1024 * Config.getInt("file.max.size")) {
                            result.setMessage("最大可上传" + Config.getInt("file.max.size") + "k的文件");
                            return result.toString();
                        }
                        boolean isAllow = false;
                        String suffix = fileItem.getName().split("\\.")[1];
                        String[] suffixs = Config.get("file.allow.type").split(",");
                        for (String str : suffixs) {
                            if (str.equalsIgnoreCase(suffix)) {
                                isAllow = true;
                                break;
                            }
                        }
                        if (!isAllow) {
                            result.setMessage("上传的文件类型错误");
                            return result.toString();
                        }
                        LOGGER.debug("上传文件的大小:" + fileItem.getSize());
                        LOGGER.debug("上传文件的类型:" + fileItem.getContentType());
                        LOGGER.debug("上传文件的名称:" + fileItem.getName());
                        //上传文件的保存路径
                        String[] strs = StrUtil.getFilePath("file");

                        String fileName = System.currentTimeMillis() + "." + fileItem.getName().split("\\.")[1];
                        File dir = new File(strs[0]);
                        if (!dir.exists())
                            dir.mkdir();
                        File f = new File(dir, fileName);
                        if (f.exists()) {
                            f.delete();
                        }
                        f.createNewFile();
                        fileItem.write(f);
                        Map<String, String> data = new HashMap<>();
                        data.put("name", fileItem.getName());
                        data.put("path", strs[1] + "/" + fileName);
                        result.set(1, "文件上传成功", data);
                        return result.toString();
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("文件上传失败", e);
            result.setMessage("文件上传失败");
        }
        return result.toString();
    }
}
