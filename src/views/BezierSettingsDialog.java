package views;

import javax.swing.*;
import java.awt.*;

public class BezierSettingsDialog extends JDialog {
    public BezierSettingsDialog(JFrame parent, DrawPanel drawPanel) {

        super(parent, "Ustawienia krzywej Beziera", false);
        setLayout(new BorderLayout());
        JSlider slider = new JSlider(0, 100, drawPanel.getSteps());
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        //aktualizowanie
        slider.addChangeListener(_ -> {
            int value = slider.getValue();
            drawPanel.setSteps(value);
        });

        add(new JLabel("Dokładność krzywej (liczba kroków):"), BorderLayout.NORTH);
        add(slider, BorderLayout.CENTER);

        setSize(600, 200);
        setLocationRelativeTo(parent);

    }
}
