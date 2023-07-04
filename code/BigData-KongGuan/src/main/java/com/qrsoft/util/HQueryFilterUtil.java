package com.qrsoft.util;

import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;

public class HQueryFilterUtil {
    public static final int subStringFilter=1;
    public static Filter newFilter(int type, String family, String column, String value){
        switch (type) {
            case subStringFilter:
                return newSubStringFilter(family, column, value);
            default:
                throw new RuntimeException("filter type not exists");
        }
    }
    //family:column中包含字符串value
    public static Filter newSubStringFilter(String family, String column, String value){
        SubstringComparator comp = new SubstringComparator(value);
        return new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(column), CompareFilter.CompareOp.EQUAL,comp);
    }
    //查询最多返回行数
    public static Filter rowLimitFilter(int rowcount){
        return new PageFilter(rowcount);
    }
    public  static FilterList or(List<Filter> filterList){
        return newList(FilterList.Operator.MUST_PASS_ONE,filterList);
    }
    public  static FilterList and(List<Filter> filterList){
        return newList(FilterList.Operator.MUST_PASS_ALL,filterList);
    }
    private static FilterList newList(FilterList.Operator oper, List<Filter> filterList){
        FilterList list = new FilterList(oper);
        for(Filter f:filterList){
            list.addFilter(f);
        }
        return list;
    }
}
