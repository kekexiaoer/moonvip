/**
 * Tm系统平台
 * moonhong
 * moonhong
 * TestApplication.java
 * 创建人:xuchengfei 
 * 时间：2015年5月12日-下午3:31:29 
 * 2015Tm公司-版权所有
 */
package moonhong;

import java.text.ParseException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tz.dao.category.IMusicCategoryDao;
import com.tz.model.MusicCategory;
import com.tz.model.TmParams;

/**
 * 
 * TestApplication
 * 创建人:xuchengfei 
 * 时间：2015年5月12日-下午3:31:29 
 * @version 1.0.0
 * 
 */
public class TestApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
//		IUserDao userDaoImpl = context.getBean(UserDaoImpl.class);
//		MoonUser moonUser = userDaoImpl.getUser(1);
//		System.out.println("============"+moonUser.getNickname());
		//userDaoImpl.updateUser("lsdfsdfsd", 1);
		//userDaoImpl.deleteUser(463);
//		moonUser.setAddress("kekekekekekek");
//		userDaoImpl.updateUser2(moonUser);
		
//		String[] beanNames = context.getBeanDefinitionNames();
//		for (String string : beanNames) {
//			System.out.println(string+"============"+context.getBean(string));
//		}
		
//		IAuthorDao authorDao = (IAuthorDao) context.getBean("IAuthorDao");
////		AuthorDaoImpl authorDaoImpl = context.getBean(AuthorDaoImpl.class);
//		Author author = new Author();
//		author.setUsername("士大夫似的");
//		author.setPassword("123456");
//		author.setEmail("153398644@qq.com");
//		author.setBirthday(new Date());
//		author.setFavourite("游泳，看书");
//		authorDao.save(author);
		
		IMusicCategoryDao categoryDao =  (IMusicCategoryDao) context.getBean("IMusicCategoryDao");
		TmParams params = new TmParams();
		params.setPageNo(0);
		params.setPageSize(3);
		List<MusicCategory> categories = categoryDao.findCategories(params);
		for (MusicCategory musicCategory : categories) {
			System.out.println(musicCategory.getName());
		}
		
//		IContentDao authorDao = (IContentDao) context.getBean("IContentDao");
//		TmParams params = new TmParams();
//		params.setIsDelete(0);
//		params.setStatus(1);
//		params.setChannelId(1);
//		params.setPageNo(0);
//		params.setPageSize(1);
//		params.setOrderSql(" create_time asc ");
//		List<Content> contents = authorDao.findContents(params);
//		for (Content content : contents) {
//			System.out.println(content.getId()+"=============="+content.getTitle());
//		}
//		
	}
}
