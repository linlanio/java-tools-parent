/**
 * Copyright 2020-2023 the original author or Linlan authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.linlan.datas.core.utils;

/**
 * 
 * Filename:Functions.java
 * Desc: 结果渲染函数类，对返回的结果进行转换处理
 *
 * @author Linlan
 * CreateTime:2020/12/20 22:10
 *
 * @version 1.0
 * @since 1.0
 *
 */
public class ResultFunctions {

    public static double parseStr2Double(String str) {
        if (null == str) {
            return 0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return 0d;
        }
    }
}
