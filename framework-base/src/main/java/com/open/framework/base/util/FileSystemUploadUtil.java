package com.open.framework.base.util;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileSystemUploadUtil {

	private final static Log LOGGER = LogFactory.getLog(FileSystemUploadUtil.class);

	private final static String PATH_SEPARATOR = "/";
	
	private static final Map<String,String> FILE_SUFFIX = new HashMap();
	
	private static final Random RANDOM = new Random();

	static {
		FILE_SUFFIX.put("image/gif", "gif");
		FILE_SUFFIX.put("image/ief", "ief");
		FILE_SUFFIX.put("image/jpeg", "jpg");
		FILE_SUFFIX.put("image/png", "png");
		FILE_SUFFIX.put("image/tiff", "tif");
	}

	/**
	 * 上传图片到图片服务器
	 * @param file
	 * @param userId
	 * @return
	 */
	public static String upload(String dir, MultipartFile file, String userId) {
		Date d = new Date();
		String currentTime = String.valueOf(d.getTime());
		String fileName = currentTime + file.getOriginalFilename();
		
		//图片路径
		String filePath = PATH_SEPARATOR +
				DateUtil.get4yMd(d) + PATH_SEPARATOR +
				userId.hashCode()%1024 + PATH_SEPARATOR +
				userId.hashCode() + PATH_SEPARATOR + fileName;
				
		
		File targetFile = new File(dir, filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			LOGGER.error("上传图片到服务器异常", e);
		}
		
		return filePath;
	}
	
}
