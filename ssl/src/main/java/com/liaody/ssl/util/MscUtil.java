package com.liaody.ssl.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yuanhaha
 */
public class MscUtil {

    /**
     * 转换sql关键字
     * @param keyword 查询关键字
     * @return 转换后的关键字
     */
    public static String  caseSqlKeyword(String keyword){
        if(StringUtils.isBlank(keyword)){
            return keyword;
        }
        return keyword.replace("\\","\\\\")
                .replace("%","\\%")
                .replace("_","\\_");
    }
}
