package org.suncreate;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-08 11:00
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
public class FileUtil extends cn.hutool.core.io.FileUtil{
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static String readText(String filePath) {
        return readText(filePath, s -> true);
    }

    public static String readText(String filePath, Function<String,Boolean> filter) {
        String buffer = null;
        try (BufferedReader readerSugar = new BufferedReader(new FileReader(filePath))) {
            buffer = readerSugar.lines().filter(filter::apply).collect(Collectors.joining());
        } catch (IOException e) {
            logger.error("read file {} error", filePath, e);
        } finally {
            return buffer;
        }
    }
}