package com.liaody.ssl.base.java8;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * 流操作
 * @author yuanhaha
 */
@Slf4j
public class StreamTest {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\key.txt");
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        // 用5M的缓冲读取文本文件
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8),100*1024*1024);
        String line;
        while((line = reader.readLine()) != null){
            System.out.println(line);
        }
        reader.close();
       // readBigFile();
//        String tmp = "kk";
//        String  filePath = "D:\\key.txt";
//        try {
//            //构造函数中的第二个参数true表示以追加形式写文件
//            FileWriter fw = new FileWriter(filePath,true);
//            for (int index=0;index<100000000;index++){
//                fw.write("hjghghghgjhghjghjghjgjhgjhghjgjhgjgjh");
//            }
//
//            fw.close();
//        } catch (IOException e) {
//            System.out.println("文件写入失败！" + e);
//        }
//
//        ;

    }

    /**
     * 使用Files工具类读取文件
     * @throws IOException
     */
    private static void readFile() throws IOException {
        // 读取小文件，大文件会爆掉内存
        String contents = new String(
                Files.readAllBytes(Paths.get("C:\\Users\\yuanhaha\\Desktop\\iptables.txt")),
                StandardCharsets.UTF_8);
        List<String> worlds = Arrays.asList(contents.split("\\PL+"));
        long count = worlds.stream().filter(data->data.length()>10).count();
        log.info("wolrds count:{}",count);
    }

    /**
     * 读取超大文件
     * @throws IOException
     */
    public static void readBigFile() throws IOException {

        File file = new File("D:\\key.txt");
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        // 用5M的缓冲读取文本文件
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8),100*1024*1024);
        String line;
        while((line = reader.readLine()) != null){
        System.out.println(line);
        }
        reader.close();
    }
}
