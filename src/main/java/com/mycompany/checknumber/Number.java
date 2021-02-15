/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.checknumber;

import java.util.*;
import java.util.regex.Pattern;

/**
 *
 * @author Mary <m.zamury@gmail.com>
 */
public class Number {

    private Integer[] numericRead = new Integer[20];
    private String numCur;

    public Number() {        
        Arrays.fill(numericRead, 0);
    }
    
    private String validate(String num) throws EInvalidParam{
        numCur = num;
        numCur.trim();
        if ((numCur.length() > 20) | (numCur.length() < 18)) {
            throw new EInvalidParam("неверное количество символов");
        }
        if (numCur.length() == 19) {numCur += "0";}
        if (numCur.length() == 18) {numCur += "00";}

        //номер-строку переводим в массив цифр
            for (int i = 0; i < numCur.length(); i++) {
                int digit = numCur.charAt(i) - '0';
                //int digit = "0123456789".indexOf(numCur.charAt(i));
                if (digit < 0 || digit > 9) { throw new EInvalidParam("не цифра в позиции " + (i+1)); }
                numericRead[i] = digit;
            }
       
        //1 7 020 92200065 00698 00 8
        if (numericRead[1] > 7) { throw new EInvalidParam("неверный номер: 2-я цифра не может быть больше 7"); }
        if (numericRead[1] == 0) { throw new EInvalidParam("неверный номер: 2-я цифра не может быть равна 0"); }
        if (numericRead[18] > 1) { throw new EInvalidParam("неверный номер: \n19-я (предпоследняя) цифра должна быть 0 или 1"); }
        if (numericRead[19] != 0) { throw new EInvalidParam("неверный номер: \n20-я (последняя) цифра должна быть 0"); }
        return numCur;        
    }  
  
    //@return возвращает контрольное число
    private Integer checkNumber(){
        Integer chNum = 0;
        for(int i = 0; i < numericRead.length; i++){
            int temp;
            if ((i % 2) == 0) {
                temp = numericRead[i];}
                else {
                temp = (numericRead[i] * 2);}              
            if (temp > 9){temp-=9;}
            chNum+=temp;
        }
        chNum = chNum % 10;
        if (chNum != 0) chNum = 10 - chNum;
        return chNum;
    }  
    
    public String getAnswer(String num) throws EInvalidParam {
            return validate(num) + checkNumber().toString();
    }
}
