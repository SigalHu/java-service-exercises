package com.sigalhu.jse.rocksdb.dao;

import lombok.Getter;
import org.rocksdb.*;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huxujun
 * @date 2019/8/26
 */
public class RocksDao implements Closeable {

    @Getter
    private RocksDB rocksDB;
    private List<ColumnFamilyHandle> columnFamilyHandles = new ArrayList<>();
    private Map<String, ColumnFamilyHandle> handleMap = new HashMap<>();

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

            if (Files.notExists(Paths.get(path))) {
                Files.createDirectories(Paths.get(path));
            }
            rocksDB = RocksDB.open(options, path, cfDescriptors, columnFamilyHandles);
            for (ColumnFamilyHandle handle : columnFamilyHandles) {
                handleMap.put(new String(handle.getName()), handle);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public ColumnFamilyHandle columnFamilyHandle(String columnFamilyName) {
        return handleMap.get(columnFamilyName);
    }

    @Override
    public void close() throws IOException {
        for (ColumnFamilyHandle columnFamilyHandle : columnFamilyHandles) {
            columnFamilyHandle.close();
        }
        rocksDB.close();
    }
}
