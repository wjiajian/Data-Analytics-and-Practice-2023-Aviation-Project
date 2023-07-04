package com.qrsoft.util;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.*;

public class HBaseUtils {
    private static Configuration conf = null;
    private static Connection conn = null;
    static { getInstance(); }
    public static Connection getInstance() {
        try {
            Properties properties =
                    PropertiesLoaderUtils.loadAllProperties("hbase.properties");
            String hbase = properties.get("hbase.zookeeper.quorum").toString();
            String zookeeper = properties.get("zookeeper.znode.parent").toString();
            if (null == conn) {
                conf = HBaseConfiguration.create();
                conf.setInt("hbase.rpc.timeout", 24000);
                conf.setInt("hbase.client.operation.timeout", 300000);
                conf.setInt("hbase.client.scanner.timeout.period", 240000);
                conf.set("hbase.zookeeper.quorum", hbase);
                conf.set("zookeeper.znode.parent", zookeeper);
                conn = ConnectionFactory.createConnection(conf);
                return conn;
            } else {
                return conn;
            }
        } catch (Exception e) {
            System.out.println("Hbase连接异常：" + e.getMessage());
        }
        return conn;
    }
// … scanResultByPageFilter实现分页检索表数据 …
    /**
     * （如果在创建表时为此表指定了非默认的命名空间，则需拼写上命名空间名称，格式为【namespace:tablename】）。
     * @param tableName   表名称(*)。
     * @param startRowKey 起始行键(可以为空，如果为空，则从表中第一行开始检索)。
     * @param endRowKey   结束行键(可以为空)。
     * @param filterList  检索条件过滤器集合(不包含分页过滤器；可以为空)。
     * @param maxVersions 指定最大版本数【如果为最大整数值，则检索所有版本；如果为最小整数值，则检索最新版本；否则只检索指定的版本数】。
     * @param pageModel   分页模型(*)。
     * @return 返回HBasePageModel分页对象。
     */
    public static HBasePageModel scanResultByPageFilter(String tableName, byte[] startRowKey, byte[] endRowKey, FilterList filterList, int maxVersions, HBasePageModel pageModel) {
        if (pageModel == null) {
            pageModel = new HBasePageModel(10);
        }
        if (maxVersions <= 0) {
            // 默认只检索数据的最新版本
            maxVersions = Integer.MIN_VALUE;
        }
        pageModel.initStartTime();
        pageModel.initEndTime();
        if (StringUtils.isBlank(tableName)) {
            return pageModel;
        }
        HTable table = null;

        try {
            // 根据HBase表名称，得到HTable表对象
            table = (HTable) getInstance().getTable(TableName.valueOf(tableName.getBytes()));
            //getInstance().getTable(TableName.valueOf(tableName));
            //HBaseTableManageUtil.getHBaseTable(tableName);
            int tempPageSize = pageModel.getPageSize();
            boolean isEmptyStartRowKey = false;
            if (startRowKey == null) {
                // 则读取表的第一行记录，这里用到了笔者本人自己构建的一个表数据操作类。
                Result firstResult = selectFirstResultRow(tableName, filterList);
                if (firstResult == null) {
                    return pageModel;
                }
                startRowKey = firstResult.getRow();
            }
            if (pageModel.getPageStartRowKey() == null) {
                isEmptyStartRowKey = true;
                pageModel.setPageStartRowKey(startRowKey);
            } else {
                if (pageModel.getPageEndRowKey() != null) {
                    pageModel.setPageStartRowKey(pageModel.getPageEndRowKey());
                }
                // 从第二页开始，每次都多取一条记录，因为第一条记录是要删除的。
                tempPageSize += 1;
            }

            Scan scan = new Scan();
            scan.setStartRow(pageModel.getPageStartRowKey());
            if (endRowKey != null) {
                scan.setStopRow(endRowKey);
            }
            PageFilter pageFilter = new PageFilter(pageModel.getPageSize() + 1);
            if (filterList != null) {
                filterList.addFilter(pageFilter);
                scan.setFilter(filterList);
            } else {
                scan.setFilter(pageFilter);
            }
            if (maxVersions == Integer.MAX_VALUE) {
                scan.setMaxVersions();
            } else if (maxVersions == Integer.MIN_VALUE) {

            } else {
                scan.setMaxVersions(maxVersions);
            }
            ResultScanner scanner = table.getScanner(scan);
            List<Result> resultList = new ArrayList<Result>();
            int index = 0;

            Map<String, String> rowMap;
            List<Map<String, String>> rowList = new ArrayList<Map<String, String>>();
            for (Result rs : scanner.next(tempPageSize)) {
                if (isEmptyStartRowKey == false && index == 0) {
                    index += 1;
                    continue;
                }
                if (!rs.isEmpty()) {
                    //默认存入集合
                    resultList.add(rs);
                    //制作每一行数据集合
                    rowMap = new HashMap<String, String>();
                    //存入主键
                    rowMap.put("rowKey", Bytes.toString(rs.getRow()));
                    //存入各字段
                    NavigableMap<byte[], NavigableMap<byte[], byte[]>> rowMap1 = rs.getNoVersionMap();
                    for (byte[] f : rowMap1.keySet()) {
                        NavigableMap<byte[], byte[]> colMap = rowMap1.get(f);
                        for (byte[] c : colMap.keySet()) {
                            rowMap.put(Bytes.toString(c), Bytes.toString(colMap.get(c)));
                        }
                    }
                    rowList.add(rowMap);
                }
                index += 1;
            }
            scanner.close();
            pageModel.setResultList(resultList);
            pageModel.setList(rowList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int pageIndex = pageModel.getPageIndex() + 1;
        pageModel.setPageIndex(pageIndex);
        if (pageModel.getResultList().size() > 0) {
            // 获取本次分页数据首行和末行的行键信息
            byte[] pageStartRowKey = pageModel.getResultList().get(0).getRow();
            byte[] pageEndRowKey = pageModel.getResultList().get(pageModel.getResultList().size() - 1).getRow();
            pageModel.setPageStartRowKey(pageStartRowKey);
            pageModel.setPageEndRowKey(pageEndRowKey);
        }
        int queryTotalCount = pageModel.getQueryTotalCount() + pageModel.getResultList().size();
        pageModel.setQueryTotalCount(queryTotalCount);
        pageModel.initEndTime();
        //pageModel.printTimeInfo();
        return pageModel;
    }

// … selectFirstResultRow 实现检索指定表的第一行记录 …
    /**
     * 检索指定表的第一行记录。<br>
     * （如果在创建表时为此表指定了非默认的命名空间，则需拼写上命名空间名称，格式为【namespace:tablename】）。
     *
     * @param tableName  表名称(*)。
     * @param filterList 过滤器集合，可以为null。
     * @return
     */
    public static Result selectFirstResultRow(String tableName, FilterList filterList) {
        if (StringUtils.isBlank(tableName)) return null;
        HTable table = null;
        try {
            table = (HTable) getInstance().getTable(TableName.valueOf(tableName.getBytes()));
            //getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            //scan.setCaching(20);
            if (filterList != null && filterList.getFilters().size() != 0) {
                scan.setFilter(filterList);
            }
            ResultScanner scanner = table.getScanner(scan);
            Iterator<Result> iterator = scanner.iterator();
            int index = 0;
            if(iterator.hasNext()){
                Result next = iterator.next();
                return  next;
            }
            scanner.close();
            return  null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
