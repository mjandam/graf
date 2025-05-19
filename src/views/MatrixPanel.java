package views;

import models.MatrixModels;

import javax.swing.*;
import java.awt.*;

import static javax.swing.border.TitledBorder.DEFAULT_POSITION;

public class MatrixPanel extends JPanel {

    private JTextArea textArea;
    private JButton button1;
    private JButton button2;

    public MatrixPanel(String title, MainFrame mainFrame) {
        setBorder(BorderFactory.createTitledBorder(null, title, 0, DEFAULT_POSITION, null, Color.WHITE)); // Ustawienie obramowania z tytułem
        setBackground(Color.GRAY);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        textArea.setBackground(Color.darkGray);
        textArea.setForeground(Color.WHITE);
        add(textArea, BorderLayout.CENTER);

        button1 = new JButton("Reset Macierzy");
        add(button1, BorderLayout.SOUTH);
        button1.addActionListener(_ -> mainFrame.matrixReset());

        button2 = new JButton("Zastosuj Macierz");
        button2.addActionListener(_ -> mainFrame.matrixSet());
        add(button2, BorderLayout.EAST);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(2, 1));
        add(bottom, BorderLayout.SOUTH);
        bottom.add(button2, BorderLayout.SOUTH);
        bottom.add(button1, BorderLayout.NORTH);

        refresh();
    }

    public void refresh() {
        String matrix = MatrixModels.matrixToString(MatrixModels.matrix);
        textArea.setText(matrix);
    }


}
