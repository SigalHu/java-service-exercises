package com.sigalhu.jse.rocksdb.dao;

import lombok.Getter;
import org.rocksdb.*;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huxujun
 * @date 2019/8/26
 */
public class RocksDao implements Closeable {

    @Getter
    private RocksDB rocksDB;
    private List<ColumnFamilyHandle> columnFamilyHandles = new ArrayList<>();

    static {
        RocksDB.loadLibrary();
    }

    public RocksDao(String path, String... columnFamilyNames) {
        try (
                ColumnFamilyOptions cfOpts = new ColumnFamilyOptions().optimizeUniversalStyleCompaction();
                DBOptions options = new DBOptions().setCreateIfMissing(true).setCreateMissingColumnFamilies(true)
        ) {
            List<ColumnFamilyDescriptor> cfDescriptors = new ArrayList<>();
            cfDescriptors.add(new ColumnFamilyDescriptor(RocksDB.DEFAULT_COLUMN_FAMILY, cfOpts));
            for (String columnFamilyName : columnFamilyNames) {
                cfDescriptors.add(new ColumnFamilyDescriptor(columnFamilyName.getBytes(), cfOpts));
            }

            rocksDB = RocksDB.open(options, path, cfDescriptors, columnFamilyHandles);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() throws IOException {
        for (ColumnFamilyHandle columnFamilyHandle : columnFamilyHandles) {
            columnFamilyHandle.close();
        }
        rocksDB.close();
    }
}
