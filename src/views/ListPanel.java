package views;

import com.sun.management.HotSpotDiagnosticMXBean;
import models.PointModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


import static javax.swing.border.TitledBorder.DEFAULT_POSITION;

public class ListPanel extends JPanel {
    private final JTextArea textArea;
    private boolean doubleFlag = true; //czy wyświetlać double czy inty

    public ListPanel(String title) {
        setBorder(BorderFactory.createTitledBorder(null, title, 0, DEFAULT_POSITION, null, Color.white));
        setBackground(Color.GRAY);
        setLayout(new BorderLayout());

        textArea = new JTextArea(10, 1);
        JButton btn = new JButton("Pokaż/Ukryj precyzje");

        textArea.setBackground(Color.darkGray);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(btn, BorderLayout.NORTH);

        btn.addActionListener(_ -> setDoubleFlag());

    }

    //odświerzanie wyświetlanej listy punktów
    public void refresh() {
        StringBuilder sb = new StringBuilder();
        for (PointModel p : MainFrame.points) {
            sb.append(p.toString(doubleFlag)).append("\n");
        }
        textArea.setText(sb.toString());
    }

    public void setDoubleFlag() {
        doubleFlag = !doubleFlag;
        refresh();
    }

    //czyścioch
    public void clearPoints() {
        MainFrame.points.clear();
        repaint();
        refresh();
    }


}
