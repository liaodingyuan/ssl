package com.liaody.ssl.dto;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageSerializable;

import java.util.Collection;
import java.util.List;

/**
 * 自定义PageInfo
 */
public class  PageInfo<T> extends PageSerializable<T> {
    private int pageNum;
    private int pageSize;
    public PageInfo(List<T> list) {
        super(list);
        if (list instanceof Page) {
            Page page = (Page)list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();

        } else if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();
        }
    }

    public static <T> PageInfo<T> of(List<T> list) {
        return new PageInfo(list);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PageInfo{");
        sb.append("pageNum=").append(this.pageNum);
        sb.append(", pageSize=").append(this.pageSize);
        return sb.toString();
    }
}