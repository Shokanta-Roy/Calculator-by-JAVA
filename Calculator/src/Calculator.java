import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class Calculator {
    int borderWidth = 360;
    int borderHeight = 540;

    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray= new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color CustomOrange = new Color (255, 165, 0);

    String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};


    JFrame frame=new JFrame("Calculator");
    JLabel displayLabel= new JLabel();
    JPanel displaypPanel= new JPanel();
    JPanel buttonsPanel= new JPanel();

    String A="0";
    String B=null;
    String operator=null;



    Calculator(){
        frame.setVisible(true);
        frame.setSize(borderWidth, borderHeight);   
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        //frame.setBackground(Color.white);

        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);   



        displaypPanel.setLayout(new BorderLayout());    
        displaypPanel.add(displayLabel);
        //frame.add(displaypPanel);
        frame.add(displaypPanel, BorderLayout.NORTH);


        buttonsPanel.setLayout(new GridLayout(5, 4));
        //buttonsPanel.setBackground(CustomOrange);
        buttonsPanel.setBackground(customBlack);    
        frame.add(buttonsPanel);

        for(int i=0; i<buttonValues.length; i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));

            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(customLightGray);  
                button.setForeground(customBlack);
            }
            else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(CustomOrange);
                button.setForeground(Color.white);
            }
            else {
                button.setBackground(customDarkGray);
                button.setForeground(Color.white);
            }
            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton)e.getSource();
                    String buttonValue = button.getText();
                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                        if (buttonValue == "=") {
                            if (A != null) {
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if (operator == "+") {
                                    //A = (numA+numB).toString();
                                    displayLabel.setText(removeZeroDecimal(numA+numB));
                                }
                                else if (operator == "-") {
                                    displayLabel.setText(removeZeroDecimal(numA-numB));
                                }
                                else if (operator == "×") {
                                    displayLabel.setText(removeZeroDecimal(numA*numB));
                                }
                                else if (operator == "÷") {
                                    displayLabel.setText(removeZeroDecimal(numA/numB));
                                }
                                clearAll();
                            }
                        }
                        else if ("+-×÷".contains(buttonValue)) {
                            if (operator == null) {
                                A = displayLabel.getText();
                                displayLabel.setText("0");

                            }
                            operator = buttonValue; 
                        }                    }
                    else if(Arrays.asList(topSymbols).contains(buttonValue)){
                        if (buttonValue == "AC") {
                            clearAll();
                            displayLabel.setText("0");
                        }
                        else if (buttonValue== "+/-") {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay = numDisplay * -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                        else if (buttonValue== "%") {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay = numDisplay /= 100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                    }
                    else{
                        if (buttonValue == ".") {
                            if (!displayLabel.getText().contains(".")) {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                                
                            }
                        }
                        else if ("01234546789".contains(buttonValue)) {
                            if (displayLabel.getText() == "0") {
                                displayLabel.setText(buttonValue);
                            }
                            else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                        // Add this block for square root
                        else if (buttonValue.equals("√")) {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            if (numDisplay >= 0) {
                                numDisplay = Math.sqrt(numDisplay);
                                displayLabel.setText(removeZeroDecimal(numDisplay));
                            } else {
                                displayLabel.setText("Error");
                            }
                        }
                    }
                }
            });
        }

    }

    void clearAll() {
        A = "0";
        B = null;
        operator = null;
        
    }

    String removeZeroDecimal(double numDisplay) {
        if (numDisplay % 1 ==0) {
            return Integer.toString((int)numDisplay);
        }
        return Double.toString(numDisplay);
    }
}
