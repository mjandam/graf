package views;

import controlers.TranformControler;
import models.MatrixModels;
import models.PointModel;
import models.TransformMatrixCalculator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MainFrame extends JFrame {
    //controlers
    private final TranformControler tranformControler;

    //Domyślne wymiary okna
    private final static Integer D_WIDTH = 1024;
    private final static Integer D_HEIGHT = 768;

    //Wspólne punkty
    public static ArrayList<PointModel> points = new ArrayList<>();

    //Panel graficzny
    private final DrawPanel drawPanel;

    //Panel boczny z listą punktów i macierzą
    private final ListPanel listPanel;
    private final MatrixPanel matrixPanel;

    //menubar
    private final MenuBar menuBar;


    public MainFrame() {

        super("Grafika komputerowa");
        setSize(D_WIDTH, D_HEIGHT);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.


                //Inicjalizacaj komponentów
                listPanel = new ListPanel("Lista punktów");
        matrixPanel = new MatrixPanel("Macierz", this);
        drawPanel = new DrawPanel(listPanel);
        menuBar = new MenuBar();

        //Inicjalizacja kontrolerów

        tranformControler = new TranformControler(this);

        //Dodawanie paneli
        JSplitPane sideContentPanel = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                listPanel,
                matrixPanel
        );
        sideContentPanel.setResizeWeight(0.90);

        JSplitPane contentPanel = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                drawPanel,
                sideContentPanel
        );
        contentPanel.setResizeWeight(1.0);
        add(contentPanel, BorderLayout.CENTER);

        setJMenuBar(menuBar); //Dodanie menu
        setMenuBarListeners(); // Ustawienie nasłuchu na zdarzenia

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }

    public ListPanel getListPanel() {
        return listPanel;
    }

    public MatrixPanel getMatrixPanel() {
        return matrixPanel;
    }

    private void setMenuBarListeners() {
        menuBar.getClearMenuItem().addActionListener(_ -> drawPanel.clearPoints());
        menuBar.getClearMenuItem().addActionListener(_ -> listPanel.clearPoints());
        menuBar.getZoomResetMenuItem().addActionListener(_ -> drawPanel.zoomReset());
        menuBar.getDrawLinesMenuItem().addActionListener(_ -> drawPanel.setdrawLines());
        menuBar.getDrawCurveMenuItem().addActionListener(_ -> showBezierSettingsDialog());
        menuBar.getRotate().addActionListener(_ -> showRotateDialog());
        menuBar.getScale().addActionListener(_ -> showScaleDialog());
        menuBar.getTranslation().addActionListener(_ -> showaTranslationDialog());
        menuBar.getReset().addActionListener(_ -> matrixReset());

    }

    //dialog okna z logiką przycisku poza modelem bo mam 15 iq
    private void showBezierSettingsDialog() {
        BezierSettingsDialog dialog = new BezierSettingsDialog(this, drawPanel);
        JCheckBox box = new JCheckBox("Pokaż krzywą", drawPanel.getIsDrawCurve());
        dialog.add(box, BorderLayout.SOUTH);
        box.addActionListener(e -> {
            drawPanel.setDrawCurve(box.isSelected());
        });
        dialog.setVisible(true);
    }

    private void showRotateDialog() {
        RotateDialog dialog = new RotateDialog(this);
        JButton btn = new JButton("Ustaw kąt");
        dialog.add(btn, BorderLayout.SOUTH);
        btn.addActionListener(_ -> tranformControler.applyRotation(MainFrame.points, dialog.getAngle()));
        dialog.setVisible(true);
    }

    private void showScaleDialog() {
        ScaleDialog dialog = new ScaleDialog(this);
        JButton btn = new JButton("Ustaw parametry skalowania");
        dialog.add(btn, BorderLayout.SOUTH);
        btn.addActionListener(_ -> tranformControler.applyScale(MainFrame.points, dialog.getSX(), dialog.getSY()));
        dialog.setVisible(true);
    }

    private void showaTranslationDialog() {
        TranslationDialog d = new TranslationDialog(this);
        JButton b = new JButton("Ustaw offset");
        d.add(b, BorderLayout.SOUTH);
        b.addActionListener(_ -> tranformControler.applyTranslation(MainFrame.points, d.getDX(), d.getDY()));
        d.setVisible(true);
    }

    public void matrixReset() {
        MatrixModels.reset();
        matrixPanel.refresh();
    }

    public void matrixSet() {
        MainFrame.points = TransformMatrixCalculator.calculate(MainFrame.points, MatrixModels.matrix);
        drawPanel.repaint();
        listPanel.refresh();
    }


}
