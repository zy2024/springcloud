package com.example.demo.util;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;

public class Zip4JExample {
    public static void main(String[] args) {
        // 要压缩的文件路径
        String sourceFilePath = "D:\\张耀简历.docx";
        // 压缩后的 ZIP 文件路径
        String zipFilePath = "D:\\compressed_files.zip";

        // 创建 ZipParameters 对象，并设置文件在 ZIP 文件中的路径
        ZipParameters parameters = new ZipParameters();
       // parameters.setRootFolderNameInZip();
        parameters.setRootFolderNameInZip("测试");
        parameters.setFileNameInZip("hhhh.docx");

        try {
            // 创建 ZipFile 对象，并将文件添加到压缩文件中
            ZipFile zipFile = new ZipFile(zipFilePath);
            zipFile.addFile(new File(sourceFilePath), parameters);
            System.out.println("文件已成功压缩到：" + zipFilePath);
            String filePathInZip = "root_folder/sub_folder/file.txt";
            zipFile.addFile(new File(sourceFilePath), new ZipParameters(), filePathInZip);

        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}
