/**
 * 
 */
package com.tz.util.images;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author Administrator
 * 
 */
public class TmImageUtil {
    /**
     * 创建图片缩略图(等比缩放)
     * @param src源图片文件完整路径
     * @param dist 目标图片文件完整路径
     * @param width 缩放的宽度
     * @param height 缩放的高度
     */
    public static void createThumbnail(String src, String dist, float width, float height) {
        try {
            File srcfile = new File(src);
            if (!srcfile.exists()) return;
            BufferedImage image = ImageIO.read(srcfile);
            // 获得缩放的比例
            double ratio = 1.0;
            // 判断如果高、宽都不大于设定值，则不处理
            if (image.getHeight() > height || image.getWidth() > width) {
                if (image.getHeight() > image.getWidth()) {
                    ratio = height / image.getHeight();
                } else {
                    ratio = width / image.getWidth();
                }
            }
            // 计算新的图面宽度和高度
            int newWidth = (int) (image.getWidth() * ratio);
            int newHeight = (int) (image.getHeight() * ratio);

            BufferedImage bfImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            bfImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0,
                    null);

            FileOutputStream os = new FileOutputStream(dist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(bfImage);
            os.close();
        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
        }
    }
    
    /**
     * 创建图片缩略图(等比缩放)
     * @param src源图片文件完整路径
     * @param dist 目标图片文件完整路径
     * @param width 缩放的宽度
     * @param height 缩放的高度
     */
    public static void createThumbnailNo(String src, String dist, int width, int height) {
        try {
            File srcfile = new File(src);
            if (!srcfile.exists()) return;
            BufferedImage image = ImageIO.read(srcfile);
            BufferedImage bfImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bfImage.getGraphics().drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0,null);

            FileOutputStream os = new FileOutputStream(dist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(bfImage);
            os.close();
        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
        }
    }
    
    /**
     * 获取图片的宽度和高度
     * com.tz.util.images 
     * 方法名：getImageWH
     * 创建人：xuchengfei 
     * 时间：2015年8月14日-下午1:35:08 
     * @param src
     * @return HashMap<String,Object>
     * @exception 
     * @since  1.0.0
     */
    public static HashMap<String, Object> getImageWH(String src) {
        try {
            File srcfile = new File(src);
            if (!srcfile.exists()) return null;
            BufferedImage image = ImageIO.read(srcfile);
            HashMap<String, Object> map = new HashMap<String,Object>();
            map.put("width",image.getWidth());
            map.put("height",image.getHeight());
            return map;
        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
    	
    	createThumbnail(
    			"D:/1.jpg",
    			"D:/111.jpg",
    			240,460
    	);
//        createThumbnail("D:\\test\\1.jpg", "D:\\a.png", 100, 100);
//        createThumbnail("D:\\test\\1.jpg", "D:\\b.png", 2000, 2000);
//        createThumbnail("D:\\test\\1.jpg", "D:\\c.jpg", 800, 600);
    	File file = new File("d://mysql/4.jpg");
    	System.out.println(file.getAbsolutePath());
    	System.out.println(file.getParentFile().getAbsolutePath());
    	System.out.println(getImageWH("D:/keke.png"));
    }

}
