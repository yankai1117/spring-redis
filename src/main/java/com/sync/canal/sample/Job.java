package com.sync.canal.sample;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName Job
 * @Description TODO
 * @Author yankai
 * @Date 2019/10/89:07
 * @Version 1.0.0
 */

public abstract class Job {


    /**
     * 数据库链接
     */
    protected JdbcTemplate jdbcTemplate;

    /**
     * 额外配置
     */
    protected JSONObject prop;

    /**
     * 校验目标是否为合适的数据库和表
     *
     * @param table
     * @param database
     * @return
     */
    abstract public boolean isMatches(String table, String database);

    /**
     * 实例化一个Runnable
     *
     * @param entry
     * @return
     */
    abstract public Runnable newTask(final CanalEntry.Entry entry);


    /**
     * 获取RowChange
     *
     * @param entry
     * @return
     */
    protected CanalEntry.RowChange getRowChange(CanalEntry.Entry entry) {
        try {
            return CanalEntry.RowChange.parseFrom(entry.getStoreValue());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return null;
    }

}