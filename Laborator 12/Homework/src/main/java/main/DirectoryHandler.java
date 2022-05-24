package main;

import com.google.common.io.Files;

import java.io.File;

public class DirectoryHandler {
    private File directory;
    public DirectoryHandler(){
        this.directory=new File("C:\\PA_2022\\FII_PA_LeaganDan_TablanAndrei\\Laborator 12\\Compulsory\\target\\classes\\compulsory");
    }
    public void run(){
        File[] files = directory.listFiles();
        for(File file1: files){
            if(Files.getFileExtension(file1.getName()).equals("class")||Files.getFileExtension(file1.getName()).equals("jar"))
               System.out.println(file1.getName());
        }
    }
}
