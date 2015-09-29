package com.tz.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;
/**
 * 关键字提取排序技术
 * @author xiaoer
 * @email xuchengfeifei@!163.com
 * 
 */
public class TmKeywordDescriptionUtil {
   public static final String PREFIX_HTML = "";
   public static final String END_HTML = "";
//   public static final String PREFIX_HTML = "<label class=exmay-label>";
//   public static final String END_HTML = "</label>";
   
   
   public static  List<String> getKeywords(String content, Analyzer analyzer,boolean isMatchNumber) {
		List<String> keys = new ArrayList<String>();
		List<String> keyes = new ArrayList<String>();
		try {
			TokenStream ifilter = analyzer.reusableTokenStream(null,new StringReader(content));
			while (ifilter.incrementToken()) {
				TermAttribute ta = ifilter.getAttribute(TermAttribute.class);
				String key = ta.term();
				if(!isMatchNumber){
					if (key.length() >= 2 && !key.matches("\\d*")){
						keys.add(ta.term());
					}
				}else{
					if (key.length() >= 2){
						keys.add(ta.term());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<String, Integer> keyMap = new HashMap<String, Integer>();
		Integer $ = null;
		for (String key : keys) {
			keyMap.put(key, ($=keyMap.get(key)) == null ? 1 : $ + 1);
		}
		List<Map.Entry<String, Integer>> keyList = new ArrayList<Map.Entry<String, Integer>>(keyMap.entrySet());
		Collections.sort(keyList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});

		for (int i = 0; i < keyList.size() && i < 3; i++) {
			keyes.add(keyList.get(i).getKey());//这里要做热门词的靠前算法。后续完成，如果他们的次数出现一样多，
		}

		return keyes;
	}
   
   //摘要提取
   //第一步：根据lucene的高亮中的提取技术我们提取大概250个关键字作为一篇文章的摘要。
   //第二步:组织一段完整的句子。
       //a:因为lucene的高亮插件在提取文章内容的时候，是没有规律的，如果一段话不是以空白开头，那么我们必须截取，开头必须不是【标点|数字|字母|非法字符开头等】
   	   //b:找到最后一个以句号结尾的位置作为结束位置。
   public static String getAbstract(String content,int showNum){
	   StringBuffer buffer = new StringBuffer();
	   //获取关键字
	   char e ;
	   List<String> keys = TmKeywordDescriptionUtil.getKeywords(content, new IKAnalyzer(),false);
	   String keywords = keys.toString().replaceAll("\\[|\\]", "");
	   try {			
		  if(TmStringUtils.isNotEmpty(content)){
			  Query query = IKQueryParser.parse("content", keywords);
			  Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter(PREFIX_HTML,END_HTML), new QueryScorer(query));
		      highlighter.setTextFragmenter(new SimpleFragmenter(showNum));
		      //将内容序列化，转换成ikanalyzer格式的字符流中。这里你的关键字才能和内容进行交互。
		      //提醒的是：如果不这样，你必须和索引进行交互。在发布新闻的时候可能你的内容还在审核还没有发布。如果没有发布你的内容是还不能索引化的。
		      TokenStream tokenStream = new IKAnalyzer().tokenStream("content", new StringReader(content));
		      String tmp = highlighter.getBestFragment(tokenStream, content);
		      if(TmStringUtils.isNotEmpty(tmp)){
		    	  content = TmStringUtils.trim(tmp);
		      }
		      int start = 0; int end = 0;
		      boolean startFlag = true;
		      for (int i = 0; i < content.length(); ++i) {
		        e = content.charAt(i);
		        if (startFlag) {
		          if (Character.isWhitespace(e)) continue; if (Character.isISOControl(e)) {
		            continue;
		          }
		          if ((e == ',') || (e == 65292) || (e == 8221) || (e == 8217) || (e == '.') || (e == 12290) || (e == '>') || (e == '?') || 
		            (e == 65311) || (e == ' ') || (e == 12288)) continue; if (e == ' ') {
		            continue;
		          }
		          if ((e == '!') || (e == 65281) || (e == ';') || (e == 65307) || (e == ':') || (e == 65306) || (e == ']')) continue; if (e == 65341) {
		            continue;
		          }
		         
		          start = i;
		          startFlag = false;
		        }
		        if (!(startFlag)) {
		          if ((e == '.') || (e == 12290) || (e == '?') || (e == 65311) || (e == '!') || (e == 65281) || (e == ' ') || (e == 12288) || 
		            (e == ' ')) {
		            if (i < 8) {
		              start = i + 1;
		            }
		            end = i;
		            if ((i == content.length() - 1) || (
		              (content.charAt(i + 1) != 8221) && (content.charAt(i + 1) != 8217))) continue;
		            end = i + 1;
		          }
		          else
		          {
		            if ((((e == ',') || (e == 65292) || (e == '>') || (e == 12299) || (e == 12289))) && 
		              (i < 2)) {
		              start = i + 1;
		            }

		            if ((e == 8217) || (e == 8221)) {
		              if (i != content.length() - 1) {
		                char next = content.charAt(i + 1);
		                if ((next != ',') && (next == 65292) && (next == 12289) && (next == ';') && (next == 65307))
		                  end = i + 1;
		              }
		              else {
		                end = i;
		              }
		            }
		          }
		        }
		      }
		      if ((end != 0) && (end > start)) {
		        content = content.substring(start, end + 1).trim();
		        start = 0;
		        for (int i = 0; i < content.length(); ++i) {
		          e = content.charAt(i);
		          if (((e != '.') && (e != 12290) && (e != '?') && (e != 65311) && (e != '!') && (e != 65281) && (e != ' ') && (e != 12288) && 
		            (e != ' ')) || 
		            (i >= 8)) continue;
		          start = i + 1;
		        }

		        if (start != 0) {
		          content = content.substring(start);
		        }
		        end = 0;
		        if (TmStringUtils.isNotEmpty(content)) {
		          e = content.charAt(content.length() - 1);
		          if ((e != '.') && (e != 12290) && (e != '?') && (e != 65311) && (e != '!') && (e != 65281)) {
		            for (int i = content.length() - 2; i > 0; --i) {
		              e = content.charAt(i);
		              if ((e == ';') || (e == 65307) || (e == ',') || (e == 65292) || (e == '>') || (e == 12299)) {
		                end = i;
		                break;
		              }
		            }
		          }
		        }

		        if (end != 0) {
		          content = content.substring(0, end);
		        }
		      }
		  }  
		  buffer.append(content);
	  } catch (Exception ex) {
	  }
	  return buffer.toString();
   } 
   /**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println((char) 8221);
		//String testString =ExmayReadFile.readFileByLines("e://news/news_sohu_199_20100930141751.txt");
		// System.out.println(testString);
		String testString = "今天(10月3日)已经是国庆长假的第3天，全" +
				"国大部地区天气平稳，仅贵州、湖南、河北、辽宁和内蒙等" +
				"部分出现大到暴雨，海南东南部还降下大暴雨。预计，今天西南" +
				"和东北的部分地区仍有大到暴雨，青藏高原东部、云南、海南及内" +
				"蒙古东部偏南地区、东北地区大部还将有小到中雨(雪)。据气象资料" +
				"统计，10月1日14时至2日14时，海南及贵州南部、湖南北部、河北东部、辽" +
				"宁西部和内蒙东南部等地出现30～80毫米的大到暴雨，海南东南部及贵州南部和河北" +
				"东部局地出现100～220毫米的大暴雨，海南陵水降雨量达239毫米；北京大部地区出现小" +
				"到中雨，北京东部部分地区降大雨。受冷空气影响，今天早晨内蒙古东北部、黑龙江北部及青海" +
				"大部的最低气温低于0℃，其中内蒙古东北部和黑龙江西北部部分地区最低气温达零下8℃～零下13" +
				"℃，内蒙古图里河最低气温达零下14℃。　　据中央气象台预计，未来三天，青藏高原中东部、内蒙古东部的" +
				"部分地区、新疆北部东北大部、河北东北部、山东半岛、江南东南部、华南沿海及海南、台湾、四川大部、重" +
				"庆大部、贵州北部云南大部等地有降水，其中，东北东部、海南大部等地有大雨，局地有暴雨或大暴雨；上述地区局" +
				"部并伴有短时雷雨大风或冰雹等强对流天气。　另外，从今天开始，受冷空气影响，东北中南部、华" +
				"北东部、黄淮和江淮东部等地将会出现较明显降温。　　专家提醒，辽宁、吉林及海南等地需加强防范强降雨可能引发的局.";
		System.out.println(getAbstract(testString, 200));
		System.out.println(getKeywords(testString, new IKAnalyzer(),false));

	}


   
}
