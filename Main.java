import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculator extends JFrame {
    private final JTextField textfield;
    private boolean number = true;
    private String equalOp = "=";
    private final CalculatorOp op = new CalculatorOp();

    public Calculator() {

        textfield = new JTextField("", 12);
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        Font FONT = new Font("algerian", Font.PLAIN, 20);
        textfield.setFont(FONT);

        ActionListener numberListener = new NumberListener();

        String buttonOrder = "1234567890";
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 4, 4));

        for (int i = 0; i < buttonOrder.length(); i++) {
            String key = buttonOrder.substring(i, i+1);
            if (key.equals(" ")) {
                buttonPanel.add(new JLabel(""));
            } else {
                JButton button = new JButton(key);
                button.addActionListener(numberListener);
                button.setFont(FONT);
                buttonPanel.add(button);
            }
        }
        
        ActionListener operatorListener = new OperatorListener();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 4, 4));
        String[] opOrder = {"+", "-", "*", "/","=","C","sin","cos","log"};

        for (String s : opOrder) {
            JButton button = new JButton(s);
            button.setBackground(Color.ORANGE);
            button.setOpaque(true);
            button.addActionListener(operatorListener);
            button.setFont(FONT);
            panel.add(button);
        }
        
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout(4, 4));
        jpanel.add(textfield, BorderLayout.NORTH );
        jpanel.add(buttonPanel , BorderLayout.CENTER);
        jpanel.add(panel , BorderLayout.EAST);
        this.setContentPane(jpanel);
        this.pack();
        this.setTitle("Calculator");
        this.setResizable(false);
    }
    private void action() {
        number = true;
        textfield.setText("");
        equalOp = "=";
        op.setTotal("");
    }
    class OperatorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String displayText = textfield.getText();

            if (e.getActionCommand().equals("sin"))
            {
                textfield.setText("" + Math.sin(Double.parseDouble(displayText)));

            }else
            if (e.getActionCommand().equals("cos"))
            {
                textfield.setText("" + Math.cos(Double.parseDouble(displayText)));

            }
            else
            if (e.getActionCommand().equals("log"))
            {
                textfield.setText("" + Math.log(Double.parseDouble(displayText)));

            }
            else if (e.getActionCommand().equals("C"))
            {
                textfield.setText("");
            }

            else
            {
                if (number)
                {

                    action();
                    textfield.setText("");

                }
                else
                {
                    number = true;
                    switch (equalOp) {
                        case "=" -> op.setTotal(displayText);
                        case "+" -> op.add(displayText);
                        case "-" -> op.subtract(displayText);
                        case "*" -> op.multiply(displayText);
                        case "/" -> op.divide(displayText);
                    }

                    textfield.setText("" + op.getTotalString());
                    equalOp = e.getActionCommand();
                }
            }
        }
    }

    class NumberListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String digit = event.getActionCommand();
            if (number) {
                textfield.setText(digit);
                number = false;
            } else {
                textfield.setText(textfield.getText() + digit);
            }
        }
    }
    public static class CalculatorOp {
        private double total;
        public CalculatorOp() {
            total = 0;
        }
        public String getTotalString() {
            return ""+total;
        }
        public void setTotal(String n) {
            total = convertToNumber(n);
        }
        public void add(String n) {
            total += convertToNumber(n);
        }
        public void subtract(String n) {
            total -= convertToNumber(n);
        }
        public void multiply(String n) {
            total *= convertToNumber(n);
        }
        public void divide(String n) {
            total /= convertToNumber(n);
        }
        private double convertToNumber(String n) {
            return Double.parseDouble(n);
        }
    }
}
class FinishedCalculator {
    public static void main(String[] args) {
        JFrame frame = new Calculator();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}