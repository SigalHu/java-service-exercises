package com.sigalhu.jse.rocksdb.dao;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2019/8/26
 */
public class BasicOperateTest extends RocksDaoTest {

    private static final byte[] defaultKey = "defaultKey".getBytes();
    private static final String defaultValue = "defaultValue";

    private static final byte[] student1 = "student1".getBytes();
    private static final String studentInfo = "student1 basic info";

    @Test
    public void writeAndRead() throws Exception {
        rocksDB.put(defaultKey, defaultValue.getBytes());
        rocksDB.put(columnFamilyHandle(ColumnFamily.STUDENT_INFO), student1, studentInfo.getBytes());

        Assert.assertEquals(defaultValue, new String(rocksDB.get(defaultKey)));
        Assert.assertNull(rocksDB.get(student1));
        Assert.assertEquals(studentInfo, new String(rocksDB.get(columnFamilyHandle(ColumnFamily.STUDENT_INFO), student1)));
    }

    @Test
    public void remove() throws Exception {
        rocksDB.put(defaultKey, defaultValue.getBytes());
        Assert.assertEquals(defaultValue, new String(rocksDB.get(defaultKey)));
        rocksDB.delete(defaultKey);
        Assert.assertNull(rocksDB.get(defaultKey));
    }
}
