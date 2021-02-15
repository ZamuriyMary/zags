/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.checknumber;


import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author Mary <m.zamury@gmail.com>
 */
public class NumbersInFile {
    String fileName = "", fileNameAnswer = "", fileNameAnswerErr = "";
    boolean flagAns = false, flagErr = false;
    
    private void readWrite() throws EReadWriteFile {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            PrintWriter out = new PrintWriter(fileNameAnswer);
            PrintWriter outErr = new PrintWriter(fileNameAnswerErr);
            Number num = new Number();
            String s;
            while ((s = in.readLine()) != null) {
                s = s.trim();
                if (!s.isEmpty()) {
                    try {
                        s = num.getAnswer(s);
                        out.println(s);
                        flagAns = true;
                    } catch (EInvalidParam ex) {
                        s = s + " - " + ex.getMessage();
                        outErr.println(s);
                        flagErr = true;
                    }
                }
            }
            out.close();
            outErr.close();
        } catch (IOException e) {
            throw new EReadWriteFile("Ошибка при чтении файла");
        }
    }
    
    private String[] createAnswer(String fileName) throws EReadWriteFile {
        this.fileName = fileName;
        fileNameAnswer = fileName + "_Answer_" + LocalDateTime.now() + ".txt"; 
        fileNameAnswerErr = fileName + "_Error_" + LocalDateTime.now() + ".txt"; 
        readWrite();
        String[] s = new String[2];        
        if (flagAns) {s[0] = fileNameAnswer;}
        if (flagErr) {s[1] = fileNameAnswerErr;}
        return s;
    }

    public String[] getAnswer(String file) throws EReadWriteFile {
        return createAnswer(file);
    }
}