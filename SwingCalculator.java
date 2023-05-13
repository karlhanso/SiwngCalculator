import java.awt.*;
import java.awt.event.*;
import java.util.function.DoubleConsumer;

import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class SwingCalculator extends JFrame {

    private JTextField tfDisplay;
    private int result = 0;
    private int prevNumber = 0;
    private int doblecount = 0;
    private String numberInStr = "";
    private char previousOpr = ' ';
    private char currentOpr = ' ';
    private JButton[] btnNumers;
    private JButton cbtn;
    private JButton zerobtn;
    private JButton equalBtn;
    private JButton plusBtn;
    private JButton minusbtn;
    private JButton multiplicationBtn;
    private JButton divisonbtn;

    public SwingCalculator() {

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        tfDisplay = new JTextField(10);
        tfDisplay.setEditable(false);
        tfDisplay.setHorizontalAlignment(JTextField.RIGHT);
        cp.add(tfDisplay, BorderLayout.NORTH);

        JPanel displayPane = new JPanel(new GridLayout(4, 4, 3, 3));

        btnNumers = new JButton[10];
        NumberBtnListener numberslis = new NumberBtnListener();
        OprBtnListener oprListener = new OprBtnListener();

        for (int i = 0; i < btnNumers.length; i++) {
            btnNumers[i] = new JButton(i + "");
            btnNumers[i].addActionListener(numberslis);
        }

        for (int i = 1; i <= 4; i++) {
            displayPane.add(btnNumers[i]);
        }
        plusBtn = new JButton("+");
        plusBtn.addActionListener(oprListener);
        displayPane.add(plusBtn);

        for (int i = 4; i <= 7; i++) {
            displayPane.add(btnNumers[i]);
        }

        minusbtn = new JButton("-");
        minusbtn.addActionListener(oprListener);
        displayPane.add(minusbtn);

        for (int i = 7; i <= 9; i++) {
            displayPane.add(btnNumers[i]);
        }

        multiplicationBtn = new JButton("*");
        multiplicationBtn.addActionListener(oprListener);
        displayPane.add(multiplicationBtn);

        cbtn = new JButton("C");
        cbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                numberInStr = "";
                tfDisplay.setText(numberInStr);
            }
        });
        displayPane.add(cbtn);

        zerobtn = new JButton("0");
        zerobtn.addActionListener(numberslis);
        displayPane.add(zerobtn);

        equalBtn = new JButton("=");
        equalBtn.addActionListener(oprListener);
        displayPane.add(equalBtn);

        divisonbtn = new JButton("/");
        divisonbtn.addActionListener(oprListener);
        displayPane.add(divisonbtn);

        cp.add(displayPane, BorderLayout.CENTER);

        setTitle("Swing Calculator");
        setSize(300, 250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public class NumberBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            numberInStr += evt.getActionCommand();
            tfDisplay.setText(numberInStr);
        }
    }

    public class OprBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            previousOpr = currentOpr;
            currentOpr = evt.getActionCommand().charAt(0);
            if (currentOpr == '+' && doblecount == 0) {
                prevNumber = Integer.parseInt(numberInStr);
                numberInStr = "";
                tfDisplay.setText(numberInStr);
                doblecount++;
            } else if (currentOpr == '+' && doblecount > 0) {
                tfDisplay.setText("error");
                numberInStr = "";
                result = 0;
                doblecount = 0;
            } else if (previousOpr == '+' && currentOpr == '=') {
                result = prevNumber + Integer.parseInt(numberInStr);
                numberInStr = "" + result;
                tfDisplay.setText(result + "");
                doblecount = 0;
            } else if (currentOpr == '-' && doblecount == 0) {
                prevNumber = Integer.parseInt(numberInStr);
                numberInStr = "";
                tfDisplay.setText(numberInStr);
                doblecount++;
            } else if (previousOpr == '-' && currentOpr == '=') {
                result = prevNumber - Integer.parseInt(numberInStr);
                numberInStr = "" + result;
                tfDisplay.setText(result + "");
                doblecount = 0;
            } else if (currentOpr == '-' && doblecount > 0) {
                tfDisplay.setText("error");
                numberInStr = "";
                result = 0;
                doblecount = 0;
            } else if (currentOpr == '*' && doblecount == 0) {
                prevNumber = Integer.parseInt(numberInStr);
                numberInStr = "";
                tfDisplay.setText(numberInStr);
                doblecount++;
            } else if (previousOpr == '*' && currentOpr == '=') {
                result = prevNumber * Integer.parseInt(numberInStr);
                numberInStr = "" + result;
                tfDisplay.setText(result + "");
                doblecount = 0;
            } else if (currentOpr == '*' && doblecount > 0) {
                tfDisplay.setText("error");
                numberInStr = "";
                result = 0;
                doblecount = 0;
            } else if (currentOpr == '/' && doblecount == 0) {
                prevNumber = Integer.parseInt(numberInStr);
                numberInStr = "";
                tfDisplay.setText(numberInStr);
                doblecount++;
            } else if (previousOpr == '/' && currentOpr == '=') {
                result = prevNumber / Integer.parseInt(numberInStr);
                numberInStr = "" + result;
                tfDisplay.setText(result + "");
                doblecount = 0;
            } else if (currentOpr == '/' && doblecount > 0) {
                tfDisplay.setText("error");
                numberInStr = "";
                result = 0;
                doblecount = 0;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingCalculator();
            }
        });
    }
}
