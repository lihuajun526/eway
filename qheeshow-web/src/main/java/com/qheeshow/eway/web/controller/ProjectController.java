package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    /**
     * 保存项目基本信息
     *
     * @param project
     * @return
     */
    @RequestMapping("/base/save")
    @ResponseBody
    public String saveBase(Project project, HttpServletRequest request) {

        LOGGER.debug("保存项目基本信息");
        Result<Integer> result = new Result<>();
        result.setCode(-1);

        if (StringUtils.isEmpty(project.getLogo())) {
            result.setMessage("请上传项目logo");
            return result.toString();
        }
        if (StringUtils.isEmpty(project.getTitle())) {
            result.setMessage("项目名称不能为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(project.getSummary())) {
            result.setMessage("项目简介不能为空");
            return result.toString();
        }
        if (project.getIndustry() == null || project.getIndustry().intValue() == 0) {
            result.setMessage("项目所属行业不能为空");
            return result.toString();
        }
        if (project.getArea() == null || project.getArea().intValue() == 0) {
            result.setMessage("项目所在城市不能为空");
            return result.toString();
        }
        if (project.getFinancingLimit() == null || project.getFinancingLimit().intValue() == 0) {
            result.setMessage("项目融资规模不能为空");
            return result.toString();
        }
        if (project.getPercent() == null) {
            result.setMessage("项目出让比例不能为空");
            return result.toString();
        }
        if (!ServletFileUpload.isMultipartContent(request)) {
            result.setMessage("项目BP不能为空");
            return result.toString();
        }

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
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("文件上传失败", e);
            result.setMessage("文件上传失败");
            return result.toString();
        }

        projectService.save(project);
        result.setData(project.getId());
        result.setCode(0);

        return result.toString();
    }

}
