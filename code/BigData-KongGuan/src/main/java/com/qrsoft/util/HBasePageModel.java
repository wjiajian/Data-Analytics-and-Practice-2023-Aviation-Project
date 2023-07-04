package com.qrsoft.util;

import org.apache.hadoop.hbase.client.Result;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HBasePageModel implements Serializable {
    private static final long serialVersionUID = 330410716100946538L;
    private int pageSize = 100;
    private int pageIndex = 0;
    private int prevPageIndex = 1;
    private int nextPageIndex = 1;
    private int pageCount = 0;
    private int pageFirstRowIndex = 1;
    private byte[] pageStartRowKey = null;
    private byte[] pageEndRowKey = null;
    private boolean hasNextPage = true;
    private int queryTotalCount = 0;
    private long startTime = System.currentTimeMillis();
    private long endTime = System.currentTimeMillis();
    private List<Result> resultList = new ArrayList<Result>();
    private List<Map<String,String>> list= new ArrayList<Map<String,String>>();

    public HBasePageModel(int pageSize) {
        this.pageSize = pageSize;
    }
    //获取分页记录数量
    public int getPageSize() {
        return pageSize;
    }
    //设置分页记录数量
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    //获取当前页序号
    public int getPageIndex() {
        return pageIndex;
    }
    //设置当前页序号
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    //获取分页总数
    public int getPageCount() {
        return pageCount;
    }
    //设置分页总数
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    //获取每页的第一行序号
    public int getPageFirstRowIndex() {
        this.pageFirstRowIndex = (this.getPageIndex() - 1) * this.getPageSize() + 1;
        return pageFirstRowIndex;
    }
    //获取每页起始行键
    public byte[] getPageStartRowKey() {
        return pageStartRowKey;
    }
    //设置每页起始行键
    public void setPageStartRowKey(byte[] pageStartRowKey) {
        this.pageStartRowKey = pageStartRowKey;
    }
    //获取每页结束行键
    public byte[] getPageEndRowKey() {
        return pageEndRowKey;
    }
    //设置每页结束行键
    public void setPageEndRowKey(byte[] pageEndRowKey) {
        this.pageEndRowKey = pageEndRowKey;
    }
    //获取上一页序号
    public int getPrevPageIndex() {
        if(this.getPageIndex() > 1) {
            this.prevPageIndex = this.getPageIndex() - 1;
        } else {
            this.prevPageIndex = 1;
        }
        return prevPageIndex;
    }
    //获取下一页序号
    public int getNextPageIndex() {
        this.nextPageIndex = this.getPageIndex() + 1;
        return nextPageIndex;
    }
    //获取是否有下一页
    public boolean isHasNextPage() {
//这个判断是不严谨的，因为很有可能剩余的数据刚好够一页。
        if(this.getResultList().size() == this.getPageSize()) {
            this.hasNextPage = true;
        } else {
            this.hasNextPage = false;
        }
        return hasNextPage;
    }
    //获取已检索总记录数
    public int getQueryTotalCount() {
        return queryTotalCount;
    }
    //获取已检索总记录数
    public void setQueryTotalCount(int queryTotalCount) {
        this.queryTotalCount = queryTotalCount;
    }
    //初始化起始时间（毫秒）
    public void initStartTime() {
        this.startTime = System.currentTimeMillis();
    }
    //初始化截止时间（毫秒）
    public void initEndTime() {
        this.endTime = System.currentTimeMillis();
    }
    //获取毫秒格式的耗时信息
    public String getTimeIntervalByMilli() {
        return String.valueOf(this.endTime - this.startTime) + "毫秒";
    }
    //获取秒格式的耗时信息
    public String getTimeIntervalBySecond() {
        double interval = (this.endTime - this.startTime)/1000.0;
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(interval) + "秒";
    }
    //获取HBase检索结果集合
    public List<Result> getResultList() {
        return resultList;
    }
    //设置HBase检索结果集合
    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }
    public List<Map<String, String>> getList() {
        return list;
    }
    public void setList(List<Map<String, String>> list) {
        this.list = list;
    }
}