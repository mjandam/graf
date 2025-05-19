package views;

import javax.swing.*;
import java.awt.*;

public class TranslationDialog extends JDialog {
    JTextField dx = new JTextField("5");
    JTextField dy = new JTextField("5");

    public TranslationDialog(JFrame parent) {
        super(parent, "Przesunięcie", false);
        setSize(200, 115);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());


        JPanel panel = getMainPanel();
        add(panel, BorderLayout.CENTER);

    }

    private JPanel getMainPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("dx:"));
        panel.add(dx);
        panel.add(new JLabel("dy:"));
        panel.add(dy);

        return panel;
    }

    public double getDX() {
        return parseField(dx);
    }

    public double getDY() {
        return parseField(dy);
    }

    public Double parseField(JTextField field) {
        try {
            return Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
