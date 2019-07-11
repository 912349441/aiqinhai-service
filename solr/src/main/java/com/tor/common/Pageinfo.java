package com.tor.common;
/**
 * 分页bean
 */

import java.util.List;

public class Pageinfo<T> {
    // 当前页
    private Integer pageNo = 1;
    // 每页显示记录数
    private Integer pageSize = 10;
    // 总记录数
    private Integer totalCount;
    // 是否为首页
    private boolean firstPage;
    // 是否有下一页
    private Integer isMore;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public boolean isfirstPage() {
        return firstPage;
    }

    public void setfirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public List<T> getList() {
        return list;
    }

    // 总页数
    private Integer totalPage;
    // 开始索引
    private Integer startIndex;
    // 分页结果
    private List<T> list;

    public Pageinfo() {
        super();
    }

    public Pageinfo(Integer pageNo, Integer pageSize, Integer totalCount,List<T> list) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = (this.totalCount+this.pageSize-1)/this.pageSize;
        this.startIndex = (this.pageNo-1)*this.pageSize;
        this.isMore = this.pageNo >= this.totalPage?0:1;
        this.firstPage=this.pageNo==1?true:false;
        this.list=list;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public Integer getIsMore() {
        return isMore;
    }

    public void setIsMore(Integer isMore) {
        this.isMore = isMore;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}