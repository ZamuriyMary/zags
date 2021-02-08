/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.checknumber;

import static com.mycompany.checknumber.SwingConsole.* ;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Mary <m.zamury@gmail.com>
 */
public class FormTab extends JFrame{
    private String 
            absoluteFileName = "";
    private JTabbedPane tab = new JTabbedPane();
    private JPanel 
            jpButtons = new JPanel(),
            jpOne = new JPanel(),
            jpFile = new JPanel();
    private JTextField 
            txtFile = new JTextField(21),
            txtNumber = new JTextField(21);
    private JTextArea 
            textF = new JTextArea(""),
            text = new JTextArea("");
    private JMenuBar mb = new JMenuBar();
    private JMenu 
            mCreate =  new JMenu("Формирование КЧ"), 
            mAbout = new JMenu("Справка");
    private JMenuItem 
            mItOne = new JMenuItem("для одного номера"),
            mItFile = new JMenuItem("для списка номеров"),
            mIt = new JMenuItem("о программе");
    
    public FormTab() {
        //menu
        mCreate.add(mItOne);
        mItOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab.setSelectedIndex(0);
            }
        });
        mCreate.add(mItFile);
        mItFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab.setSelectedIndex(1);
            }
        });
        mb.add(mCreate);
        mb.add(mAbout);
        mAbout.add(mIt);
        mIt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Dialog(null).setVisible(true);
            }
        });        
        setJMenuBar(mb);
        
        JButton bRun = new JButton("Выполнить"),
                bClear = new JButton("Очистить"),
                bClose = new JButton("Закрыть");
        
        //вкладка один номер
        jpOne.add(new Label("Введите номер:"));
        jpOne.add(txtNumber);
        JPanel answer = new FormAnswer().FormAnswerCreate(jpOne, text);

        //вкладка файл
        jpFile.setLayout(new GridLayout(3, 2));
        jpFile.add(new Label("Введите имя текстового файла (*.txt):"));
        jpFile.add(txtFile);
        jpFile.add(new Label("или выберите из списка"));
        JButton bOpenFile = new JButton("Выбрать");
        jpFile.add(bOpenFile);
        bOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fch = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовый документ (*.txt)", "txt");
                fch.setFileFilter(filter);
                int i = fch.showOpenDialog(FormTab.this);
                if (i == JFileChooser.APPROVE_OPTION) {
                    absoluteFileName = fch.getSelectedFile().getAbsolutePath();
                    textF.setText("\nВыбран файл - '" + absoluteFileName + "'\n");
                    txtFile.setText(absoluteFileName);
                }
            }
        });     
        JPanel answerFile = new FormAnswer().FormAnswerCreate(jpFile, textF);

        //панель кнопок
        jpButtons.add(bRun);
        bRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                String sa = "";
                if (tab.getSelectedIndex() == 0) {//вкладка 0 - работа с одним номером                    
                    try{
                        text.setText(new Number(txtNumber.getText()).getAnswer());
                    }catch (EInvalidParam ex) {
                        text.setText(txtNumber.getText() +" - " +ex.getMessage());
                }
                } else {//вкладка 1 - работа с файлом
                    if (!txtFile.getText().isEmpty()) {
                        textF.setText("\nВыбран файл - '" + txtFile.getText() + "'\n");
                        try{
                            sa = new NumbersInFile().getAnswer(txtFile.getText());
                        }catch (EReadWriteFile ex){
                            textF.append(ex.getMessage());
                            new Dialog(null, ex.getMessage()).setVisible(true);
                        }
                    } else {
                        new Dialog(null, "Выберете файл").setVisible(true);                        
                    }
                }
                    textF.append(sa);
                    absoluteFileName = "";                
            }
        }
        );
        jpButtons.add(bClear);
        bClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNumber.setText("");
                if (tab.getSelectedIndex() == 0) {
                    text.setText("");
                } else {
                    textF.setText("");
                    txtFile.setText("");
                }
            }
        });
        jpButtons.add(bClose);
        bClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });        
        
        //панель вкладок
        tab.addTab("Работа с одним номером", answer);
        tab.addTab("Выбор файла", answerFile);

        add(BorderLayout.SOUTH,jpButtons);
        add(tab);        
    }
       
    public static void main(String[] args) {
        run(new FormTab(), 550, 400);
    }
}

// 17020922000630134000
// /home/user/test.txt