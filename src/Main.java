import javax.swing.*;
import views.MainFrame;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(MainFrame::new);

    }
}