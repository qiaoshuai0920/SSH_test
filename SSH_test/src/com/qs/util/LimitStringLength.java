package com.qs.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class LimitStringLength {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//String asd=limitStringLength("12345678912345678", 16, "...", 2);

		//System.out.println(asd);
	}
	
	
	/**
	 * 限制字符串长度（以字符长度为单位，不是字符个数。假设半角的长度为1，全角为2）
	 * @param src		源字符串
	 * @param maxByteCharLength		最大字符串长度
	 * @param endingString		结束字符串
	 * @param endingStringByteLength		结束字符串长度
	 * @return
	 * @throws IOException
	 */
	public static String limitStringLength(final String src, final int maxByteCharLength,
			final String endingString, final int endingStringByteLength) {
		

		StringReader sr = new StringReader(src);
		int read = 0;
		List<Character> arrayList = new ArrayList<Character>();
		int byteCharCount = 0, shortCharCount = 0;

		try {
			while ((read = sr.read()) != -1) {
				if (read < 256) {
					byteCharCount++;
				} else {
					shortCharCount++;
				}
				arrayList.add((char) read);
				if (byteCharCount + (shortCharCount << 1) >= maxByteCharLength) {
					read = sr.read();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		int charLen = byteCharCount + (shortCharCount << 1);
		if (read != -1) {
			if (charLen > maxByteCharLength) {
				arrayList.remove(arrayList.size() - 1);
				arrayList.remove(arrayList.size() - 1);
			} else if (charLen == maxByteCharLength) {
				if (arrayList.remove(arrayList.size() - 1) < 256) {
					arrayList.remove(arrayList.size() - 1);
				}
			}
			return displayArray(arrayList.toArray(), "") + endingString;
		} else {
			if (charLen > maxByteCharLength) {
				arrayList.remove(arrayList.size() - 1);
				arrayList.remove(arrayList.size() - 1);
				return displayArray(arrayList.toArray(), "") + endingString;
			} else {
				return displayArray(arrayList.toArray(), "");
			}
		}
	}
	
	
	/**
	 * 将数组转换成字符串，简单起见应该用Arrays.toString(Object[] arr)
	 * @param arrObjects	对象数组
	 * @param joinString	隔开每个内容的字符串
	 * @return	转换结果
	 */
	public static String displayArray(final Object[] arrObjects, final String joinString) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arrObjects.length; i++) {
			sb.append(arrObjects[i]);
			if (i + 1 < arrObjects.length) sb.append(joinString);
		}
		return sb.toString();
	}

}
