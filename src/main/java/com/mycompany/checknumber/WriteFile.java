/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.checknumber;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Mary <m.zamury@gmail.com>
 */
public class WriteFile {
    //@return шаблон для записи массива в файл 
    public boolean write(String file, ArrayList<String> nums) throws EReadWriteFile{                
        try (PrintWriter out = new PrintWriter(file)) {
            for (String num : nums) {
                out.println(num);
            }
        }catch (IOException e){
            throw new EReadWriteFile("Ошибка при записи файла-ответа");
        }
        return true;
    }
}
