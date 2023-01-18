/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doxa.setrucdownloader.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author BlackSpider
 */
public class Unzip {
    
    private static final int BUFFER_SIZE = 4096;
    
    public static void extractFile(ZipInputStream Zip_Input_Stream, String File_Path) throws IOException {
             
        BufferedOutputStream Buffered_Output_Stream = new BufferedOutputStream(new FileOutputStream(File_Path));
        byte[] Bytes = new byte[BUFFER_SIZE];
        int Read_Byte = 0;
        while ((Read_Byte = Zip_Input_Stream.read(Bytes)) != -1) {
        	Buffered_Output_Stream.write(Bytes, 0, Read_Byte);
        }
        Buffered_Output_Stream.close();
    }
    
    public static void unzip(String ZipFilePath, String DestFilePath) throws IOException {
        File Destination_Directory = new File(DestFilePath);
        if (!Destination_Directory.exists()) {
        	Destination_Directory.mkdir();
        }
        
        URL url = new URL(ZipFilePath);
        ZipInputStream Zip_Input_Stream = new ZipInputStream(url.openStream());
        ZipEntry Zip_Entry = Zip_Input_Stream.getNextEntry();

        while (Zip_Entry != null) {
            String File_Path = DestFilePath + File.separator + Zip_Entry.getName();
            if (!Zip_Entry.isDirectory()) {

                extractFile(Zip_Input_Stream, File_Path);
            } else {

                File directory = new File(File_Path);
                directory.mkdirs();
            }
            Zip_Input_Stream.closeEntry();
            Zip_Entry = Zip_Input_Stream.getNextEntry();
        }
        Zip_Input_Stream.close();
    }
    
    
    
}
