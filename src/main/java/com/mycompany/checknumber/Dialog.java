/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.checknumber;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Mary <m.zamury@gmail.com>
 */
public class Dialog extends JDialog {

    private String txt
            = "   Данная программа предназначена для вычисления контрольного числа (КЧ) \n"
            + "   номера записи акта гражданского состояния (ЗАГС).\n"
            + "\n"
            + "   Входные данные - номер из 20 цифр, сформированный в соответсвии с\n"
            + "   приказом Минфина России от 27.09.2018 N 202н (ред. от 15.04.2019)\n"
            + "   'О порядке присвоения номера записи акта гражданского состояния\n"
            + "   и сведениям о документе, выданном компетентным органом иностранного\n"
            + "   государства в удостоверение акта гражданского состояния, совершенного \n"
            + "   вне пределов территории Российской Федерации по законам соответствующего\n"
            + "   иностранного государства в отношение гражданина Российской Федерации,\n"
            + "   в Едином государственном реестре записей актов гражданского состояния,\n"
            + "   и структуре указанных номеров'.\n"
            + "\n"
            + "   Допускается передача номера из 18 или 19 цифр - в таком случае\n"
            + "   недостающие знаки будут заполнены нулями (как наиболее часто\n"
            + "   встречающийся вариант).\n"
            + "\n"
            + "   2-й и 19-й символы проверяются на корректность в соответствии\n"
            + "   с вышеуказанным приказом.\n"
            + "\n"
            + "   Входные данные могут приниматься в виде текстового файла (одна строка -\n"
            + "   один номер).\n"
            + "\n"
            + "   Выходными данными являются КЧ и/или полный номер ЗАГС\n"
            + "   или информация об ошибке.";
    private JPanel jpText = new JPanel(),
            jpBut = new JPanel();
    private JButton ok = new JButton("OK");
    private JTextArea tArea = new JTextArea();

    public Dialog(JFrame parent) {
        super(parent, "Информация о программе", true);
        jpText.setLayout(new BorderLayout());
        jpText.add(tArea);
        tArea.setText(txt);

        jpBut.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(BorderLayout.CENTER, jpText);
        add(BorderLayout.SOUTH, jpBut);
        setSize(580, 470);
    }

    public Dialog(JFrame parent, String error) {
        super(parent, "Ошибка", true);
        setLayout(new GridLayout(2, 1));
        add(new JLabel(error));
        JButton ok = new JButton("OK");
        JPanel jp = new JPanel();
        jp.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(jp);
        setSize(310, 125);
    }
}