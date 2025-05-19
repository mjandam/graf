package views;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private final JMenu panelMenu;
    private final JMenu matrixMenu;


    //panel menu
    private final JMenuItem zoomReset;
    private final JMenuItem clearMenuItem;
    private final JMenuItem drawLinesMenuItem;
    private final JMenuItem drawCurveMenuItem;

    //matrix menu
    private final JMenuItem reset;
    private final JMenuItem translation;
    private final JMenuItem scale;
    private final JMenuItem rotate;


    public MenuBar() {
        panelMenu = new JMenu("Panel opcje");
        matrixMenu = new JMenu("Operacje elemenatne");

        //Menu Panelu
        zoomReset = new JMenuItem("Zresetuj zoom");
        clearMenuItem = new JMenuItem("Wyczyść panel");
        drawLinesMenuItem = new JMenuItem("Narysuj linie między punktami");
        drawCurveMenuItem = new JMenuItem("Narysuj krzywą Beziera");

        //Menu operacji

        reset = new JMenuItem("Zresetuj macierz");
        translation = new JMenuItem("Przesunięcie");
        scale = new JMenuItem("Skalowanie");
        rotate = new JMenuItem("Obracanie");

        //Dodanie do menu Panel
        panelMenu.add(zoomReset);
        panelMenu.add(clearMenuItem);
        panelMenu.add(drawLinesMenuItem);
        panelMenu.add(drawCurveMenuItem);

        //Dodanie do menu operacji

        matrixMenu.add(reset);
        matrixMenu.add(translation);
        matrixMenu.add(scale);
        matrixMenu.add(rotate);

        //Dodanie do paska
        add(panelMenu);
        add(matrixMenu);
    }

    public JMenuItem getClearMenuItem() {
        return clearMenuItem;
    }

    public JMenuItem getZoomResetMenuItem() {
        return zoomReset;
    }

    public JMenuItem getDrawLinesMenuItem() {
        return drawLinesMenuItem;
    }

    public JMenuItem getDrawCurveMenuItem() {
        return drawCurveMenuItem;
    }

    public JMenuItem getRotate() {
        return rotate;
    }

    public JMenuItem getScale() {
        return scale;
    }

    public JMenuItem getTranslation() {
        return translation;
    }

    public JMenuItem getReset() {
        return reset;
    }
}
