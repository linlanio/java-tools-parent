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

package io.linlan.commons.core.io.image;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件名：ImageUtils.java
 * 文件说明：核心管理部分图片处理图片辅助工具类
 * Createtime 2020/7/12 10:13 PM
 *
 * @version 1.0
 * @since 1.0
 */
public abstract class ImageUtils {

    /** 图片的后缀. */
    public static final String[] ALL_IMAGE_EXT = new String[] { "jpg", "jpeg",
            "gif", "png", "bmp" };

    /** The Constant IMAGE_CUT_EXT. 图片裁剪后的文件后缀*/
    public static final String DEFAULT_IMAGE_EXT ="jpeg";

    /**
     * 是否是图片.
     *
     * @param ext
     *            the ext
     * @return "jpg", "jpeg", "gif", "png", "bmp" 为文件后缀名者为图片
     */
    public static boolean isValidImageExt(String ext) {
        ext = ext.toLowerCase(Locale.ENGLISH);
        for (String s : ALL_IMAGE_EXT) {
            if (s.equalsIgnoreCase(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the underlying input stream contains an image.
     *
     * @param in
     *            input stream of an image
     * @return <code>true</code> if the underlying input stream contains an
     *         image, else <code>false</code>
     */
    public static boolean isImage(final InputStream in) {
        ImageInfo ii = new ImageInfo();
        ii.setInput(in);
        return ii.check();
    }

    /**
     * 获得水印位置.
     *
     * @param width
     *            原图宽度
     * @param height
     *            原图高度
     * @param p
     *            水印位置 1-5，其他值为随机。1：左上；2：右上；3：左下；4：右下；5：中央。
     * @param offsetx
     *            水平偏移。
     * @param offsety
     *            垂直偏移。
     * @return 水印位置
     */
    public static Position markPosition(int width, int height, int p,
            int offsetx, int offsety) {
        if (p < 1 || p > 5) {
            p = (int) (Math.random() * 5) + 1;
        }
        int x, y;
        switch (p) {
        // 左上
        case 1:
            x = offsetx;
            y = offsety;
            break;
        // 右上
        case 2:
            x = width + offsetx;
            y = offsety;
            break;
        // 左下
        case 3:
            x = offsetx;
            y = height + offsety;
            break;
        // 右下
        case 4:
            x = width + offsetx;
            y = height + offsety;
            break;
        // 中央
        case 5:
            x = (width / 2) + offsetx;
            y = (height / 2) + offsety;
            break;
        default:
            throw new RuntimeException("never reach ...");
        }
        return new Position(x, y);
    }

    /**
     * 水印位置
     *
     * 包含左边偏移量，右边偏移量。.
     *
     * @author abatemp1
     */
    public static class Position {

        /** The x. */
        private int x;

        /** The y. */
        private int y;

        /**
         * Instantiates a new position.
         *
         * @param x
         *            the x
         * @param y
         *            the y
         */
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Gets the，获取方法 the x.
         *
         * @return the x
         */
        public int getX() {
            return x;
        }

        /**
         * Sets the，设置方法 the x.
         *
         * @param x
         *            the new x
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * Gets the，获取方法 the y.
         *
         * @return the y
         */
        public int getY() {
            return y;
        }

        /**
         * Sets the，设置方法 the y.
         *
         * @param y
         *            the new y
         */
        public void setY(int y) {
            this.y = y;
        }
    }

    public static  List<String> getImageSrc(String htmlCode) {
        List<String> imageSrcList = new ArrayList<String>();  
        String regular="<img(.*?)src=\"(.*?)\"";  
        String img_pre="(?i)<img(.*?)src=\"";
        String img_sub="\"";
        Pattern p=Pattern.compile(regular,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);  
        String src = null;  
        while (m.find()) {  
            src=m.group();
            src=src.replaceAll(img_pre, "").replaceAll(img_sub, "").trim();
            imageSrcList.add(src);  
        }  
        return imageSrcList;  
    }
}
