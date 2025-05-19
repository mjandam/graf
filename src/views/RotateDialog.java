package views;

import models.MatrixModels;
import models.TransformMatrixCalculator;

import javax.swing.*;
import java.awt.*;

public class RotateDialog extends JDialog {

    JTextField text = new JTextField("90");

    public RotateDialog(JFrame parent) {
        super(parent, "Obrót", false);
        setSize(200, 115);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel panel = getMainPanel();
        add(panel, BorderLayout.CENTER);
    }

    private JPanel getMainPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Kąt \u03B1:"));
        panel.add(text);

        return panel;
    }

    public double getAngle() {
        return parseField(text);
    }

    public Double parseField(JTextField field) {
        try {
            return Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }


}
