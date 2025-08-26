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

import cn.hutool.core.util.StrUtil;

/**
 * <code>
 * <pre>
 * 空字符串("")转换成Integer的null
 *
 * </pre>
 * </code>
 * @author Li Jun
 * @since 2021-06-11
 */
public class StringToIntegerUtil {

	public static Integer convert(String source) {
		if (StrUtil.isBlank(source)){
			return null;
		}
		Integer i = Integer.parseInt(source);
		return i;
	}
}
