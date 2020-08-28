/**  
 * All rights Reserved 杭州海量
 * @Title:  ResourceUtil.java   
 * @Package cn.com.itsea.insurance.utils   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 姜辉晖     
 * @date:   2019年11月21日 下午1:50:32   
 * @version V1.0     
 */
package com.tor.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ResourceUtil {
	public static BufferedImage bytesToBufferedImage(byte[] bytes) throws IOException {
		if (bytes == null || bytes.length < 0) {
			return null;
		}
		ByteArrayInputStream byteArrayInputStream = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(bytes);
			return ImageIO.read(byteArrayInputStream);
		} finally {
			if (byteArrayInputStream != null) {
				byteArrayInputStream.close();
			}
		}
	}
	
	private static ByteArrayOutputStream imageToStream(BufferedImage image, String format) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, format, out);
		return out;
	}
	
	public static byte[] bufferedImageToBytes(BufferedImage bufferedImage) throws IOException {
		if (bufferedImage == null) {
			return null;
		}
		ByteArrayOutputStream out = null;
		try {
			out = imageToStream(bufferedImage, "jpg");
			return out.toByteArray();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	public static BufferedImage base64ToBufferedImage(String base64) throws IOException {
		if (StringUtils.isBlank(base64)) {
			return null;
		}
		byte[] bytes = Base64.decodeBase64(base64);
		return bytesToBufferedImage(bytes);
	}
	
	public static String bufferedImageToBase64(BufferedImage bufferedImage) throws IOException {
		if (bufferedImage == null) {
			return null;
		}
		byte[] bytes = bufferedImageToBytes(bufferedImage);
		return Base64.encodeBase64String(bytes);
	}
	
	public static void saveToLocal(InputStream inputStream, String filePath) throws FileNotFoundException, IOException {
		int lastPosOfSeparate = filePath.lastIndexOf(File.separator);
		String directoryString = filePath.substring(0, lastPosOfSeparate);
		File dir = new File(directoryString);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		BufferedInputStream bufferedInputStream = null;
		OutputStream outputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(inputStream);
			File file = new File(filePath);
			outputStream = new FileOutputStream(file);
			bufferedOutputStream = new BufferedOutputStream(outputStream);
			byte[] bf = new byte[1024];
			int c = -1;
			while ((c = bufferedInputStream.read(bf)) != -1) {
				bufferedOutputStream.write(bf, 0, c);
			}
			bufferedOutputStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bufferedOutputStream != null) {
				try {
					bufferedOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bufferedInputStream != null) {
				try {
					bufferedInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static byte[] fileToBytes(File file) throws IOException {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
	}
}
