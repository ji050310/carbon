/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.carbon.common.config.jackson.converter;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Date;

/**
 * <code>
 * <pre>
 * 日期转换器,将请求参数的日期字符串转换成java.util.Date类型
 * 日期格式顺序:
 * 	1.yyyy-MM-dd HH:mm:ss:S
 * 	2.yyyy-MM-dd HH:mm:ss
 * 	3.yyyy-MM-dd HH:mm
 * 	4.yyyy-MM-dd HH
 *	5.yyyy-MM-dd
 * </pre>
 * </code>
 * @author Li Jun
 * @since 2021-06-11
 */
public class StringToDateUtil{
	/**
	 * 日期格式化数组
	 */
	private static String[] dateFormats = {
			("yyyy-MM-dd HH:mm:ss"),
			("yyyy-MM-dd HH:mm"),
			("yyyy-MM-dd"),
			("HH:mm"),
			("yyyy-MM-dd HH:mm:ss:S"),
			 // 添加更多格式支持
			 ("yyyy-MM-dd'T'HH:mm:ss"),
			 ("yyyy-MM-dd'T'HH:mm:ss.SSS"),
			 ("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
			 ("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	};

	/**
	 * <code>
	 * <pre>
	 * 1.如果日期字符串为空,则直接返回空
	 * 2.使用格式化组进行格式化,如果解析成功,则直接返回
	 * 4.否则,抛出非法参数异常
	 * @param source 请求的日期参数
	 * @return 解析后的日期类型:java.util.Date
	 * @exception IllegalArgumentException 非法参数异常
	 * </pre>
	 * </code>
	 */
	public static Date convert(String source) {
		if (StrUtil.isBlank(source)){
			return null;
		}
		source = source.trim();

		try{
			int timeLength = source.length();
			Long time = Long.parseLong(source);
			if (timeLength == 10){
				time = time * 1000;
			}
			Date date = new Date(time);
			return date;
		}catch (Exception e){

		}

		Date date = null;
		boolean flag = false;
		for (String dateFormat : dateFormats) {
			try {
				date= DateUtil.parse(source,dateFormat);
				flag = true;
				break;
			}catch (Exception e){

			}
		}

		if (flag) {
			return date;
		}else{
			throw new IllegalArgumentException("不能解析日期:" + source);
		}

	}
}
