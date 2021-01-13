package com.tor.project.utils;

import cn.hutool.core.lang.Console;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class PinyinUtils {
	private static final PinyinUtils instance = new PinyinUtils();
	
	private PinyinUtils(){}
	
	public static PinyinUtils getInstance() {
		return instance;
	}
	
	public String getFirstLetterStringForMultiple(String inputString) {
		if(inputString == null){
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		char[] array = inputString.toCharArray();
		for (int i = 0; i < array.length; i ++) {
			stringBuilder.append(getFirstLetterStringForSingle(array[i]));
			if (i != array.length - 1) {
				stringBuilder.append(",");
			}
		}
		return stringBuilder.toString();
	}
	
	public String getFirstLetterStringForSingle(char chineseWord) {
		StringBuilder stringBuilder = new StringBuilder();
		Set<Character> set = new HashSet<Character>();
		String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(chineseWord);
		if (null != pinyin && pinyin.length > 0) {
			for (String string : pinyin) {
				if (StringUtils.isNotBlank(string)) {
					set.add(string.charAt(0));
				}
			}
			for (Character character : set) {
				stringBuilder.append(character);
			}
		} else {
			//字母或数字或拼音识别不出来的
			stringBuilder.append(chineseWord);
		}
		return stringBuilder.toString();
	}

}