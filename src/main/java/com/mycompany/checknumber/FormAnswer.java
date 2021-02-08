/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.checknumber;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Mary <m.zamury@gmail.com>
 */
public class FormAnswer {
        public JPanel FormAnswerCreate(JPanel top, JTextArea txt){
        JPanel jp = new JPanel(), jpA = new JPanel();
        jpA.setLayout(new BorderLayout());
        jpA.setBorder(new TitledBorder("Результат:"));        
        jpA.add(BorderLayout.CENTER, txt); 
        jpA.add(new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));  
        jp.setLayout(new BorderLayout());
        jp.add(BorderLayout.NORTH, top);
        jp.add(BorderLayout.CENTER, jpA);        
        return jp;
        }
}
