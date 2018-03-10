package chapter2;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;

import swidgets.SButton;
import nz.sodium.Cell;
import nz.sodium.Stream;
import swidgets.SLabel;
public class hold {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(()-> {
            JFrame frame = new JFrame("redgreen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());
            SButton red = new SButton("red");
            SButton green = new SButton("green");

            Stream<String> sRed = red.sClicked.map(u -> "red");
            Stream<String> sGreen = green.sClicked.map(u -> "green");
            Stream<String> sColor = sRed.orElse(sGreen);

            Cell<String> color = sColor.hold("");
            SLabel lbl = new SLabel(color);
            frame.add(green);
            frame.add(red);
            frame.add(lbl);
            frame.setSize(400, 160);
            frame.setVisible(true);
        });
    }
}
