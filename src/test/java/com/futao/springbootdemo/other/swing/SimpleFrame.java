package com.futao.springbootdemo.other.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @author futao
 * Created on 2019-02-12.
 */
public class SimpleFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MySimpleFrame mySimpleFrame = new MySimpleFrame();
            mySimpleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mySimpleFrame.setVisible(true);
        });
    }
}

class MySimpleFrame extends JFrame {
    @Override
    public void setSize(int width, int height) {
        super.setSize(300, 200);
    }
}