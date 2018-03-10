package chapter2;

/**
 * Created by coolk on 11-02-2017.
 */
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;

import swidgets.SButton;
import swidgets.STextField;
import nz.sodium.Stream;
public class clearfield {

    public static void main(String[] args) throws Exception
    {
        SwingUtilities.invokeAndWait(() -> {
            JFrame frame = new JFrame("clearfield");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            SButton clear = new SButton("Clear");
            Stream<String> sClearIt = clear.sClicked.map(u -> "");

            STextField text = new STextField(sClearIt, "Hello");
            frame.add(text);
            frame.add(clear);
            frame.setSize(400, 160);
            frame.setVisible(true);
        });
    }
}
