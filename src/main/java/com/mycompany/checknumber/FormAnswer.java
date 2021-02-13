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
public class FormAnswer extends JPanel{
    JPanel jpA = new JPanel();
    private JTextArea text = new JTextArea("");

    public FormAnswer() {
        jpA.setLayout(new BorderLayout());
        jpA.setBorder(new TitledBorder("Результат:"));
        jpA.add(BorderLayout.CENTER, text);
        jpA.add(new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));        
    }

    public JPanel FormAnswerCreate(JPanel top) {
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        jp.add(BorderLayout.NORTH, top);
        jp.add(BorderLayout.CENTER, jpA);
        return jp;
    }

    public void setText(String str) {
        text.setText(str);
    }
    
    public void appendText(String str) {
        text.append(str);
    }    
}
