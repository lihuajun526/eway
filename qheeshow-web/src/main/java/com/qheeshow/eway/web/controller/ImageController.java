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
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/image")
public class ImageController extends BaseController {

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(HttpServletRequest request) throws UnsupportedEncodingException {
        Result<String> result = new Result<>();
        result.setCode(-1);
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
                        if (fileItem.getSize() > 1024 * Config.getInt("image.max.size") * 1024) {
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
                        result.set(1, "logo上传成功", strs[1] + "/" + fileName);
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
}
