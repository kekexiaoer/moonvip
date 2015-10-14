package com.tz.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.tz.dao.content.IContentDao;
import com.tz.model.Content;
import com.tz.model.TmParams;
import com.tz.util.TmDateUtil;
import com.tz.util.TmFileUtil;
import com.tz.util.TmKeywordDescriptionUtil;
import com.tz.util.TmStringUtils;

/**
 * 
 * IndexController 
 * 创建人:xuchengfei 
 * 时间：2015年6月3日-下午7:47:24
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/content")
public class ContentController extends BaseController{

	@Autowired
	private IContentDao contentDao;
	
	/**
	 * 
	 * 更新页面
	 * com.tz.web 
	 * 方法名：save
	 * 创建人：xuchengfei 
	 * 时间：2015年6月10日-下午2:12:58 
	 * @param content
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Content save(Content content) {
		content.setUserId(getUserId());
		contentDao.saveContent(content);
		return content;
	}
	
	/**
	 * 
	 * 更新页面
	 * com.tz.web 
	 * 方法名：save
	 * 创建人：xuchengfei 
	 * 时间：2015年6月10日-下午2:12:58 
	 * @param content
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Content update(Content content) {
		content.setUpdateTime(new Date());//设置更新时间
		contentDao.updateContent(content);
		return content;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/codeUpdate/{id}",method=RequestMethod.POST)
	public Content codeUpdate(Content content,@PathVariable("id")Integer id) {
		TmParams params = new TmParams();
		params.setId(id);
		Content con = contentDao.getContent(params);
		if(con!=null && con.getUserId()==getUserId()){
			content.setUpdateTime(new Date());//设置更新时间
			content.setIsCode(1);
			contentDao.updateContent(content);
			return content;
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * 添加页面
	 * com.tz.web 
	 * 方法名：add
	 * 创建人：xuchengfei 
	 * 时间：2015年6月10日-下午2:12:43 
	 * @param content
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value="/add")
	public  ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("content/add");
		return modelAndView;
	}
	
	/**
	 * 
	 * 添加页面
	 * com.tz.web 
	 * 方法名：add
	 * 创建人：xuchengfei 
	 * 时间：2015年6月10日-下午2:12:43 
	 * @param content
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value="/edit/{id}")
	public  ModelAndView edit(@PathVariable("id")Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("content/edit");
		modelAndView.addObject("id", id);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/get/{id}",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> get(@PathVariable("id")Integer id){
		TmParams tmParams = new TmParams();
		tmParams.setId(id);
		HashMap<String, Object> map = contentDao.getHLCMessage(tmParams);
		return map;
	}
	
	
	/**
	 * 
	 * 添加页面
	 * com.tz.web 
	 * 方法名：add
	 * 创建人：xuchengfei 
	 * 时间：2015年6月10日-下午2:12:43 
	 * @param content
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value="/addMusic/{id}")
	public  ModelAndView addMusic(@PathVariable("id")Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addMusic");
		modelAndView.addObject("id", id);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/download",method=RequestMethod.POST)
	@ResponseBody
	public String downimg(HttpServletRequest request){
		String img = request.getParameter("img");
		return downNetImg(request,img);
	}
	
	private static String downNetImg(HttpServletRequest request, String src){
		try {
			URL url = new URL(src);
			URLConnection connection = url.openConnection();
			int totalSize = connection.getContentLength();//网络图片的大小,字节 跑道的100米
			InputStream inputStream = connection.getInputStream();
			HttpSession session = request.getSession();
			//获取服务器的路径
			String uploadPath = request.getRealPath("/");
			String rootDir = "resource/net/"+TmDateUtil.dateToString(new Date(),"yyyy/MM/dd");
			//如果upload不存在就创建
			File rootFile = new File(uploadPath+rootDir);
			if(!rootFile.exists())rootFile.mkdirs();
			//获取新的文件名
			String newname = TmFileUtil.generateFileName(src, 5, "yyyyMMddHHmmss");
			//获取磁盘输出流
			FileOutputStream outputStream = new FileOutputStream(new File(rootFile,newname));
			byte[] b = new byte[2048];
			//输入流
			BufferedInputStream in = new BufferedInputStream(inputStream);
			//io读写瓶颈--
			int len = 0;
			//开始读和写
			int length =0; 
			while((len = in.read(b))!=-1){//读
				length += len;//已经下载的长度,小涛跑25米， 25%
				Thread.sleep(30);
//				System.out.println("您当前下载了:"+TmStringUtils.getPercent(length, (float)totalSize,"#"));
				session.setAttribute(src, TmStringUtils.getPercent(length, (float)totalSize,"#"));
				outputStream.write(b, 0, len);//写
			}
			in.close();
			outputStream.close();//关闭
			inputStream.close();
			return rootDir+"/"+newname;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//ajax获取正在上传的进度条信息
	@RequestMapping(value="/percent",method=RequestMethod.POST)
	@ResponseBody
	public String imgPercent(HttpServletRequest request){
		HttpSession session = request.getSession();
		String img = request.getParameter("img");
		String result = (String) session.getAttribute(img);
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value="hlc/{cid}/{type}",method=RequestMethod.POST)
	public Integer updateHLC(@PathVariable("cid")Integer cid,@PathVariable("type")Integer type){
		Integer uid = getUserId();
		if(uid==null)return 2;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", getUserId());
		map.put("contentId", cid);
		map.put("type", type);
		Integer count = contentDao.updateHLC(map);
		return count;
	}
	
	@ResponseBody
	@RequestMapping(value="gethlc/{cid}",method=RequestMethod.POST)
	public List<HashMap<String, Object>>  gethlc(@PathVariable("cid")Integer cid){
		Integer uid = getUserId();
		if(uid==null)return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", getUserId());
		map.put("contentId", cid);
		List<HashMap<String, Object>> cmaps = contentDao.getHLC(map);
		return cmaps;
	}
	
	@ResponseBody
	@RequestMapping(value="/keyword",method=RequestMethod.POST)
	public String keyword(HttpServletRequest request){
		String message = request.getParameter("message");
        if(TmStringUtils.isNotEmpty(message)){
            List<String> keyStrings = TmKeywordDescriptionUtil.getKeywords(message, new IKAnalyzer(), false);
            System.out.println(keyStrings);
            message = TmStringUtils.listToString(keyStrings,",");
        }
        return message;
    }
    
	@ResponseBody
	@RequestMapping(value="/description",method=RequestMethod.POST)
    public String description(HttpServletRequest request){
		String message = request.getParameter("message");
        if(TmStringUtils.isNotEmpty(message)){
            message = TmKeywordDescriptionUtil.getAbstract(message, 150);
        }
        return message;
    }
}