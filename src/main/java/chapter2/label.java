package chapter2;

/**
 * Created by coolk on 11-02-2017.
 */
import javax.swing.JFrame;
import java.awt.FlowLayout;
import swidgets.STextField;
import swidgets.SLabel;

public class label {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("label");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        STextField msg = new STextField("Hello");
        SLabel lbl = new SLabel(msg.text);
        frame.add(msg);
        frame.add(lbl);
        frame.setSize(400, 160);
        frame.setVisible(true);
    }
}
