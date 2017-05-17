package com.qheeshow.eway.backstage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.model.FileWithBLOBs;
import com.qheeshow.eway.service.service.FileService;

@RestController
@RequestMapping("/zfile")
public class ZFileController {
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = "/upload" , method = RequestMethod.POST)
    public HaResponse upload(@RequestParam("file") MultipartFile[] files,HttpSession session){
		FileWithBLOBs sysFile = new FileWithBLOBs();
//		sysFile.setCompanyId(Integer.parseInt(session.getAttribute("companyId").toString()));
//		sysFile.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
		sysFile.setCompanyId(1);
		sysFile.setUserId(1);
		List<String> fileIds = fileService.upload(files, sysFile);
        return HaResponse.sussess(fileIds);
    }
	
	@RequestMapping(value = "/download/{fileId}",method= RequestMethod.GET)
	public void download(@PathVariable("fileId") String fileId, HttpServletResponse response) throws IOException{
		List<FileWithBLOBs> sysFiles = fileService.getList(fileId);
		for(FileWithBLOBs sysFile : sysFiles){
			response.setHeader("Content-Disposition", "attachment;filename=\"" 
					+ URLEncoder.encode(sysFile.getOriginalName(), "utf-8") + "\";filename*=utf-8''" + URLEncoder.encode(sysFile.getOriginalName(), "utf-8"));
			response.setHeader("Content-Length", String.valueOf(sysFile.getFileSize()));
			response.setContentType(sysFile.getContentType());
			response.setCharacterEncoding("UTF-8");
			response.flushBuffer();
			ServletOutputStream os = response.getOutputStream();
			InputStream is = null;
			try{
				is = new FileInputStream(sysFile.getOriginalPath() + File.separator + sysFile.getId());
				byte[] buffer = new byte[512 * 1024];
				int length;
				while ((length = is.read(buffer)) != -1){
					os.write(buffer, 0, length);
					os.flush();
				}
			}finally{
				if(is!=null)
					is.close();
				if(os!=null)
					os.close();
			}
			return;
		}
	}

}
