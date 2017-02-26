package com.qheeshow.eway.service.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.qheeshow.eway.service.model.FileWithBLOBs;

public interface FileService {
	
	public List<String> upload(MultipartFile[] files,FileWithBLOBs sysFile);
	
	public List<FileWithBLOBs> getList(String fileId);

}
