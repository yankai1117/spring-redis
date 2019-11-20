package com.sync.canal.sample;
import	java.util.ArrayList;

import java.net.InetSocketAddress;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.client.*;
import com.sync.redis.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName ClientSample
 * @Description TODO
 * @Author yankai
 * @Date 2019/10/716:01
 * @Version 1.0.0
 */
public class ClientSample {
    //日志
    private  final Logger logger = LoggerFactory.getLogger(ClientSample.class);

    public  void syn() {

        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("10.185.162.101",
                11111), "example", "", "");//IP和端口为上文中 canal.properties中的IP和端口，“example”为默认，用户名密码不填

        logger.info("正在连接...");
        System.out.println("正在连接...");
        System.out.println(connector);
        int batchSize = 1000;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            logger.info("连接成功");
            System.out.println("连接成功");
            connector.rollback();
            while (true) {
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    printEntry(message.getEntries());
                }

                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }

        } finally {
            connector.disconnect();
            logger.info("连接释放成功");
            System.out.println("连接释放成功");
        }
    }

    static{
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("139.186.66.219",
                11111), "example", "", "");
    }
    public static void main(String args[]) {


        new Thread(ClientSample::receiver).start();
        new Thread(ClientSample::ack).start();
    }

    private static void printEntry( List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }

            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));
            System.out.println("操作类型=="+eventType);
            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    redisDelete(rowData.getBeforeColumnsList());
                } else if (eventType == EventType.INSERT) {
                    redisInsert(rowData.getAfterColumnsList());
                } else {
                    System.out.println("-------> before");
                    printColumn(rowData.getBeforeColumnsList());
                    System.out.println("-------> after");
                    redisUpdate(rowData.getAfterColumnsList());
                }
            }
        }
    }

    private static void printColumn( List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }

    private static void redisInsert( List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if(columns.size()>0){
            RedisUtil.stringSet("user:"+ columns.get(0).getValue(),json.toJSONString());
        }
    }

    private static  void redisUpdate( List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        System.out.println("columns.size()=="+columns.size());
        if(columns.size()>0){
            RedisUtil.stringSet("user:"+ columns.get(0).getValue(),json.toJSONString());
            System.out.println("user:id="+ columns.get(0).getValue() + " value=" + json.toJSONString());
        }
    }

    private static  void redisDelete( List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if(columns.size()>0){
            RedisUtil.delKey("user:"+ columns.get(0).getValue());
        }
    }
    private static void receiver() {
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("139.186.66.219",
                11111), "example", "", "");
//        int batchSize = 1;
//        int count = 0;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
//            int total = 20;
            //停的从CanalConnector中获取消息
            while (true) {
//                count++;
//                System.out.println("循环");
                //获取一定数量的消息，这里为线程池数量×3
                Message message = connector.getWithoutAck(3); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    try {
                        System.out.println(message.toString());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    printEntry(message.getEntries());
                    System.out.println(message.toString());
                }

                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }

        } finally {
            connector.disconnect();
        }
    }
    private static void ack() {
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("139.186.66.219",
                11111), "example", "", "");
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            while (true) {
                Long batchId = 20L;
                if(batchId!=null){
                    connector.ack(batchId);
                    System.out.println("batchId "+batchId+" ack");
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
//            System.out.println("ack exit");
        } finally {
            connector.disconnect();
        }
    }
    static void process(Message message) {
        List<Entry> entries = message.getEntries();
        if (entries.size() <= 0) {
            return;
        }
        System.out.println("process message.entries.size:{} ==" + entries.size());
        for (Entry entry : entries) {
            CanalEntry.Header header = entry.getHeader();
            String tableName = header.getTableName();
            String schemaName = header.getSchemaName();

            //这里判断是否可以取出数据库名称和表名称，如果不行，跳过循环

            if (StringUtils.isEmpty(tableName) && StringUtils.isEmpty(schemaName)) {
                continue;
            }
            List<Job> jobList = new ArrayList<> ();
            //创建新的线程，并执行
//            jobList.stream()
//                    .filter(job -> job.isMatches(tableName, schemaName))
//                    .forEach(job -> executorService.execute(job.newTask(entry)));
        }
    }
}
