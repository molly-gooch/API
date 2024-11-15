import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Layout {
    private JFrame mainFrame;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JTextArea ta1;
    private JTextArea ta2;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;


    public Layout() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Layout layout = new Layout();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Pokedex");
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        ta1 = new JTextArea();
        ta2 = new JTextArea();
        label1 = new JLabel("Pokemon Name: ", JLabel.CENTER);
        label2 = new JLabel("Pokemon Info: ", JLabel.CENTER);
        label3 = new JLabel("Search New Pokemon: ", JLabel.CENTER);

        mainFrame.setLayout(new GridLayout(2, 1));
        panel2.setLayout(new GridLayout(2,1));
        panel3.setLayout(new GridLayout(1,2));
        panel4.setLayout(new BorderLayout());
        panel5.setLayout(new BorderLayout());

        mainFrame.add(panel1);
        panel2.add(panel3);
        panel3.add(panel4);
        panel3.add(panel5);
        panel5.add(ta2, BorderLayout.CENTER);
        panel5.add(label2, BorderLayout.NORTH);
        mainFrame.add(panel2);
        panel4.add(label1, BorderLayout.NORTH);
        panel4.add(ta1, BorderLayout.CENTER);


        mainFrame.setVisible(true);


}
}
