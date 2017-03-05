package com.qheeshow.eway.service.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.util.DateUtil;
import com.qheeshow.eway.common.util.ImageCompressUtil;
import com.qheeshow.eway.service.dao.FileMapper;
import com.qheeshow.eway.service.model.FileExample;
import com.qheeshow.eway.service.model.FileWithBLOBs;
import com.qheeshow.eway.service.service.FileService;

@Service("fileService")
@Transactional
public class FileServiceImpl implements FileService{
	
	@Autowired
	private FileMapper sysFileMapper;
	
	@Override
	public List<String> upload(MultipartFile[] files,FileWithBLOBs sysFile){
		List<String> fileIds = new ArrayList<String>();
		Date date = new Date();
		String dateString = DateUtil.getFormattedDate(date, "yyyyMMdd");
		for(MultipartFile file:files){
			try {
				String originalFilename = file.getOriginalFilename();
				String originalPath = Config.get("file.upload.path") + sysFile.getCompanyId() + File.separator + sysFile.getUserId() + File.separator + dateString;
				sysFile.setCreateTime(date);
				sysFile.setStatus(0);
				sysFile.setOriginalPath(originalPath);
				sysFile.setOriginalName(originalFilename);
				sysFile.setContentType(file.getContentType());
				sysFile.setFileSize(file.getSize());
				sysFileMapper.insertSelective(sysFile);
				ImageCompressUtil.writeFile(originalPath, sysFile.getId().toString(), file.getInputStream());
//				ImageCompressUtil.saveMinPhoto(Constants.FILE_UPLOAD_PATH+originalFilename, "E:/new"+originalFilename, 128, 1);
				fileIds.add(sysFile.getId().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fileIds;
	}
	
	@Override
	public List<FileWithBLOBs> getList(String fileId){
		FileExample example = new FileExample();
		example.createCriteria().andIdEqualTo(Integer.parseInt(fileId)).andIsOpenEqualTo(1);
		List<FileWithBLOBs> sysFiles = sysFileMapper.selectByExampleWithBLOBs(example);
		return sysFiles;
	}

}
