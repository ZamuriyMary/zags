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
    
    private boolean validate(String numCur) throws EInvalidParam{
        this.numCur = numCur;
        numCur.trim();
        if ((numCur.length() > 20) | (numCur.length() < 18)) {
            throw new EInvalidParam("неверное количество символов");
        }
        if (numCur.length() == 19) {numCur += "0";}
        if (numCur.length() == 18) {numCur += "00";}
        if (!Pattern.compile("\\d{20}").matcher(numCur).find()) {
            throw new EInvalidParam("в номере должны быть только цифры");
        } 
        //номер-строку переводим в массив цифр
        try {
            for (int i = 0; i < numCur.length(); i++) {
//                String s = numCur.substring(i, i + 1);
//                numericRead[i] = Integer.valueOf(s);
                int digit = numCur.charAt(i) - '0';
                //int digit = "0123456789".indexOf(numCur.charAt(i));
                if (digit < 0 || digit > 9) { throw new EInvalidParam("не цифра в позиции " + i); }
                numericRead[i] = digit;
            }
        } catch (NumberFormatException e) {throw new EInvalidParam("неверный номер");}
        
        //1 7 020 92200065 00698 00 8
        if ((numericRead[1] > 7)|(numericRead[1] == 0)) {
            throw new EInvalidParam("неверный номер");
        }
        if (numericRead[18] > 1) {
            throw new EInvalidParam("неверный номер");
        }
        return true;        
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
        validate(num);
        return numCur + checkNumber().toString();
    }
}
