package controlers;

import com.sun.tools.javac.Main;
import models.MatrixModels;
import models.PointModel;
import models.TransformMatrixCalculator;
import views.DrawPanel;
import views.ListPanel;
import views.MainFrame;
import views.MatrixPanel;

import java.util.ArrayList;

public class TranformControler {

    private final MainFrame mainFrame;
    private final ListPanel listPanel;
    private final DrawPanel drawPanel;

    private final MatrixPanel matrixPanel;


    public TranformControler(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.listPanel = mainFrame.getListPanel();
        this.drawPanel = mainFrame.getDrawPanel();
        this.matrixPanel = mainFrame.getMatrixPanel();
    }

    public void applyRotation(ArrayList<PointModel> points, double x) {
        MainFrame.points = TransformMatrixCalculator.calculate(points, MatrixModels.multiply(MatrixModels.matrix, MatrixModels.rotation(x))); //tak długie i bez sensu, ale działa. Idealny kod do skopoiwania 4 razy w całym programie.
        drawPanel.repaint();
        listPanel.refresh();
        matrixPanel.refresh();
    }

    public void applyScale(ArrayList<PointModel> points, double x, double y) {
        MainFrame.points = TransformMatrixCalculator.calculate(points, MatrixModels.multiply(MatrixModels.matrix, MatrixModels.scaling(x, y)));
        drawPanel.repaint();
        listPanel.refresh();
        matrixPanel.refresh();
    }

    public void applyTranslation(ArrayList<PointModel> points, double x, double y) {
        MainFrame.points = TransformMatrixCalculator.calculate(points, MatrixModels.multiply(MatrixModels.matrix, MatrixModels.translation(x, y)));
        drawPanel.repaint();
        listPanel.refresh();
        matrixPanel.refresh();
    }


}
