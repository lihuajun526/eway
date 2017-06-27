package com.qheeshow.eway.backstage.controller;

import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.backstage.base.Result;
import com.qheeshow.eway.common.exception.CryptoException;
import com.qheeshow.eway.common.util.AESCryptoUtil;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.util.ExcelReader;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.model.Xwcmclassinfo;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.service.service.XwcmclassinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;


/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/importer")
public class ImporterController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private XwcmclassinfoService xwcmclassinfoService;


    @RequestMapping(value = "/project/import")
    @ResponseBody
    public String importProject(HttpSession session) {
        Result result = new Result();

        /*if (session.getAttribute("loginUser") == null) {
            result.setMessage("对不起，您没有权限");
            return result.toString();
        }*/

        ExcelReader excelReader = new ExcelReader("C:\\import\\16-21期路演上线项目第一批-13个\\路演项目导入13个-yoyo.xlsx", "项目录入模板");
        excelReader.getSheetData();
        importUserAndPro(excelReader.mapData);
        return result.toString();
    }

    private void importUserAndPro(List<Map<String, String>> mapData) {
        for (Map<String, String> map : mapData) {
            User user = new User();
            try {
                user.setPassword(AESCryptoUtil.encrypt("password123"));
            } catch (CryptoException e) {
                e.printStackTrace();
            }
            user.setMobile(map.get("联系电话").trim());
            user.setName(map.get("联系人").trim());
            user.setRoleid(20);
            user.setStatus(2);

            Project project = new Project();
            project.setTitle(map.get("项目名称").trim());
            project.setDemand(map.get("解决需求").trim().replaceAll("\n", ""));
            project.setHighlights(map.get("项目亮点").trim().replaceAll("\n", ""));
            //project.setDescription("");
            project.setType(2);
            List<Xwcmclassinfo> industrys = xwcmclassinfoService.getByName(map.get("所属行业").trim());
            if (industrys.size() == 0 || industrys.size() > 1) {
                LOGGER.info("行业-" + industrys.size());
                LOGGER.error("所属行业[{}]不存在或存在多个", map.get("所属行业").trim());
                continue;
            }
            project.setIndustry(industrys.get(0).getClassinfoid());
            project.setIndustryName(industrys.get(0).getCname());
            List<Xwcmclassinfo> areas = xwcmclassinfoService.getByName(map.get("项目地点").trim());
            if (areas.size() == 0 || areas.size() > 1) {
                LOGGER.error("项目地点[{}]不存在或存在多个", map.get("项目地点").trim());
                continue;
            }
            project.setArea(areas.get(0).getClassinfoid());
            project.setAreaName(areas.get(0).getCname());
            String sf = map.get("融资金额");
            List<Xwcmclassinfo> financingLimits = xwcmclassinfoService.getByNameAndParent(sf.trim(), 31);
            if (financingLimits.size() == 0 || financingLimits.size() > 1) {
                LOGGER.error("融资金额[{}]不存在或存在多个", map.get("融资金额").trim());
                continue;
            }
            project.setFinancingLimit(financingLimits.get(0).getClassinfoid());
            project.setFinancingLimitName(financingLimits.get(0).getCname());
            List<Xwcmclassinfo> stages = xwcmclassinfoService.getByName(map.get("融资阶段").trim());
            if (stages.size() == 0 || stages.size() > 1) {
                LOGGER.error("融资阶段[{}]不存在或存在多个", map.get("融资阶段").trim());
                continue;
            }
            project.setStage(stages.get(0).getClassinfoid());
            project.setStageName(stages.get(0).getCname());
            //project.setContent();
            //project.setUserid();
            project.setUsername(map.get("联系人").trim());
            project.setStatus(2);
            String[] imageStrs = StrUtil.getFilePath("image");
            String[] fileStrs = StrUtil.getFilePath("file");
            File imageDir = new File(imageStrs[0]);
            if (!imageDir.exists())
                imageDir.mkdir();
            File fileDir = new File(fileStrs[0]);
            if (!fileDir.exists())
                fileDir.mkdir();
            //logo
            String logoFileName = System.currentTimeMillis() + "." + map.get("LOGO").split("\\.")[1];
            this.copyFile(Config.get("import.file.path") + "/" + map.get("LOGO"), imageStrs[0] + "/" + logoFileName);
            project.setLogo(imageStrs[1] + "/" + logoFileName);
            //bp
            String bpFileName = System.currentTimeMillis() + "." + map.get("BP").split("\\.")[1];
            this.copyFile(Config.get("import.file.path") + "/" + map.get("BP"), fileStrs[0] + "/" + bpFileName);
            project.setBp(fileStrs[1] + "/" + bpFileName);
            project.setBpName(map.get("BP"));
            //一页通
            String onePageFileName = System.currentTimeMillis() + "." + map.get("一页通").split("\\.")[1];
            this.copyFile(Config.get("import.file.path") + "/" + map.get("一页通"), imageStrs[0] + "/" + onePageFileName);
            project.setOnepage(imageStrs[1] + "/" + onePageFileName);

            String[] tags = map.get("项目标签").split("\n");
            StringBuffer sTags = new StringBuffer("");
            boolean isFirst = true;
            for (String tag : tags) {
                if (StringUtils.isEmpty(tag))
                    continue;
                List<Xwcmclassinfo> list = xwcmclassinfoService.getByName(tag.trim());
                if (list == null || list.size() > 1) {
                    LOGGER.error("[tag={}]不存在或存在多个", tag);
                    continue;
                }
                if (isFirst) {
                    sTags.append(tag);
                    isFirst = false;
                    continue;
                }
                sTags.append("#").append(tag);
            }
            project.setTags(sTags.toString());
            if (StringUtils.isEmpty(project.getTags())) {
                LOGGER.info("项目[{}]的tags为空", project.getTitle());
                project.setTags("其他");
            }
            userService.importUserAndPro(project, user);
        }

    }

    private void copyFile(String pathOld, String pathNew) {
        File fileOld = new File(pathOld);
        File fileNew = new File(pathNew);
        if (!fileOld.exists()) {
            LOGGER.error("[{}]文件不存在", pathOld);
            return;
        }
        try {
            FileInputStream fis = new FileInputStream(fileOld);
            FileOutputStream fos = new FileOutputStream(fileNew);
            int read = 0;
            while ((read = fis.read()) != -1) {
                fos.write(read);
                fos.flush();
            }
            fos.close();
            fis.close();
        } catch (Exception e) {
            LOGGER.error("error:", e);
        }
    }
}
