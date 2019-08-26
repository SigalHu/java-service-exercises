package com.sigalhu.jse.rocksdb.dao;

import org.junit.BeforeClass;
import org.rocksdb.ColumnFamilyHandle;
import org.rocksdb.RocksDB;

import java.util.Arrays;

/**
 * @author huxujun
 * @date 2019/8/26
 */
public class RocksDaoTest {

    protected static RocksDB rocksDB;
    private static RocksDao rocksDao;

    @BeforeClass
    public static void init() {
        rocksDao = new RocksDao("tmp/rocksdb", Arrays.stream(ColumnFamily.values())
                .map(ColumnFamily::getName).toArray(String[]::new));
        rocksDB = rocksDao.getRocksDB();
    }

    protected static ColumnFamilyHandle columnFamilyHandle(ColumnFamily columnFamily) {
        return rocksDao.columnFamilyHandle(columnFamily.getName());
    }

    public enum ColumnFamily {

        STUDENT_INFO("student_info"),
        ;

        private String name;

        ColumnFamily(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}