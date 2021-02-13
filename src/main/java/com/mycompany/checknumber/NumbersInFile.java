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

    private ArrayList<String> numbers = new ArrayList(),
            numbersCur = new ArrayList(),
            numbersErr = new ArrayList();
    String fileName, fileNameAnswer = "", fileNameAnswerErr = "";
    boolean ok = true;
    
    private void read(String filename) throws EReadWriteFile{
        this.fileName = filename;
        String s;
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            while ((s = in.readLine()) != null) {
                s = s.trim();
                if (!s.isEmpty()) numbers.add(s);
            }
        }catch (IOException e){
            throw new EReadWriteFile("Ошибка при чтении файла");
        }
    }
    
    //@return формирует массивы номеров (с КЧ) и массив ошибочных номеров
    private void checkup() {
        for (String num : numbers) {
            try{
                numbersCur.add(new Number().getAnswer(num));
            }catch (EInvalidParam ex){
                numbersErr.add(num + " - " + ex.getMessage());
            }      
        }
    }
    
    private String write() throws EReadWriteFile {
        String result = "";
        if (!numbersCur.isEmpty()) {
            fileNameAnswer = fileName + "_Answer_" + LocalDateTime.now();            
            if (new WriteFile().write(fileNameAnswer, numbersCur)) {
                result += "\nСформирован файл-ответ: " + fileNameAnswer + "\n";
                fileNameAnswer = "";
            }
        }
        if (!numbersErr.isEmpty()) {
            fileNameAnswerErr = fileName + "_Error_" + LocalDateTime.now();            
            if (new WriteFile().write(fileNameAnswerErr, numbersErr)) {
                result += "\nВ исходных данных есть ошибочные номера!\nСписок неверных номеров: '" + fileNameAnswerErr + "'\n";
                fileNameAnswerErr = "";
            }
        }
        return result;
    }  

    //@return возвращает имя файла-ответа и имя файла с ошибочными номерами
    public String getAnswer(String filename) throws EReadWriteFile {
        read(filename);
        checkup();
        return write();
    }
}
