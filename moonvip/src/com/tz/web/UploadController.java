package com.tz.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.farng.mp3.TagException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tz.util.MP3Info;
import com.tz.util.MP3Util;
import com.tz.util.TmDateUtil;
import com.tz.util.TmFileUtil;
import com.tz.util.TmStringUtils;
import com.tz.util.images.TmImageUtil;

@Controller
@RequestMapping("/admin/upload")
public class UploadController {
	
	public static void main(String[] args) {
		System.out.println(TmFileUtil.getNotExtName("ccccccccccc.jpg"));
	}

	@RequestMapping(value = "/file")
	@ResponseBody
	public String tzupload(
			@RequestParam("doc") MultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException, JSONException {
		String oldName = null,ext=null;
		String newName = null;
		String smallName = null;
		File dirPath  = null;
		String url = null;
		String oldFileName = request.getParameter("oldName");
		String zip = request.getParameter("zip");
		if (TmStringUtils.isNotEmpty(oldFileName)) {
			newName = oldFileName;
			String realPath = request.getRealPath("/");
			dirPath = new File(realPath);
//			File paFile = new File(dirPath, newName);
			ext = TmFileUtil.getExtNoPoint(newName);
			url = oldFileName;
		} else {
			String directory = request.getParameter("dir");
			String selfDir = request.getParameter("selfDir");
			String rootDir = null;
			if(TmStringUtils.isEmpty(selfDir)){
				if(TmStringUtils.isEmpty(directory))directory = "content";
				rootDir = "resource/"+directory+"/"+TmDateUtil.dateToString(new Date(),"yyyy/MM/dd");
			}else{
				rootDir = "resource/"+selfDir;
			}
			String realPath = request.getRealPath(rootDir);
			dirPath = new File(realPath);
			// 自动创建上传的upload目录
			if (!dirPath.exists())dirPath.mkdirs();
			oldName = file.getOriginalFilename();
			ext = TmFileUtil.getExtNoPoint(oldName);
			newName = TmFileUtil.generateFileName(oldName, 5, "yyyyMMddHHmmss");
			url = rootDir+"/"+newName; 
		}
		File targetFile = new File(dirPath, newName);
		File pfile = new File(targetFile.getPath());
		if(!pfile.exists())pfile.mkdirs();
		file.transferTo(targetFile);// 文件上传
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", oldName);// 文件的原始名称
		map.put("newName", newName);// 文件的新名称
		map.put("ext", ext);// 文件的后缀
		map.put("size", file.getSize());// 文件的真实大小
		map.put("sizeString", TmFileUtil.countFileSize(file.getSize()));// 获取文件转换以后的大写
		map.put("url", url);// 获取文件的具体服务器的目录
		map.put("sizeString", TmFileUtil.countFileSize(file.getSize()));
		if(TmStringUtils.isNotEmpty(ext)){
			if(ext.equalsIgnoreCase("mp3")){
				try {
					MP3Info mp3Info = MP3Util.getMP3Info(targetFile.getAbsolutePath());
					map.put("author", mp3Info.getArtist());
					map.put("title", mp3Info.getAlbumTitle());
					map.put("name", mp3Info.getSongTitle());
					map.put("timer", mp3Info.getTimeSecond());
					map.put("timeline", mp3Info.getTimeLine());
				} catch (Exception e) {
				}
			}else if(TmStringUtils.isImage(ext)){
				smallName = TmFileUtil.getNotExtName(newName)+"_small."+ext;
				HashMap<String, Object> cmap =TmImageUtil.getImageWH(targetFile.getAbsolutePath());
				File smallFile = new File(targetFile.getParentFile().getAbsolutePath(),smallName);
				TmImageUtil.createThumbnail(targetFile.getAbsolutePath(),smallFile.getAbsolutePath(),540,600);
				map.put("width", cmap.get("width"));
				map.put("height", cmap.get("height"));
				map.put("thumnail", smallName);
			}
		}
		
		if(TmStringUtils.isNotEmpty(zip)){
			TmImageUtil.createThumbnail(targetFile.getAbsolutePath(),targetFile.getAbsolutePath(),120,120);
		}
		return JSONUtil.serialize(map);
	}

	@RequestMapping("/parse/mp3")
	@ResponseBody
	public MP3Info parse(HttpServletRequest request) {
		String realPath = request.getRealPath("resource");
		String path = request.getParameter("path");
		String mp3Path = realPath + "/" + path;
		File file = new File(mp3Path);
		try {
			if (file.exists()) {
				MP3Info mp3Info = MP3Util.getMP3Info(mp3Path);
				return mp3Info;
			} else {
				return null;
			}
		} catch (IOException | TagException | UnsupportedAudioFileException e) {
			e.printStackTrace();
			return null;
		}
	}
}
