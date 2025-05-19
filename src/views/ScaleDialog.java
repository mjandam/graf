package views;

import javax.swing.*;
import java.awt.*;

public class ScaleDialog extends JDialog {

    JTextField sx = new JTextField("2");
    JTextField sy = new JTextField("2");

    public ScaleDialog(JFrame parent) {
        super(parent, "Skalowanie", false);
        setSize(200, 115);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel panel = getMainPanel();
        add(panel, BorderLayout.CENTER);

    }

    private JPanel getMainPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("sx:"));
        panel.add(sx);
        panel.add(new JLabel("sy:"));
        panel.add(sy);

        return panel;
    }

    public double getSX() {
        return parseField(sx);
    }

    public double getSY() {
        return parseField(sy);
    }

    public Double parseField(JTextField field) {
        try {
            return Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
