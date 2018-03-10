package chapter2;

import swidgets.STextField;
import swidgets.SButton;
import swidgets.SLabel;

import nz.sodium.Stream;
import nz.sodium.Cell;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import java.awt.FlowLayout;

public class snapshot {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JFrame view = new JFrame("Translate");
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            view.setLayout(new FlowLayout());
            STextField english = new STextField("I like FRP");
            SButton translate = new SButton("Translate");

            Stream<String> sLatin =
                    translate.sClicked.snapshot(english.text, (u, txt) -> txt.trim().replaceAll(" |$", "us").trim());

            Cell<String> latin = sLatin.hold("");
            SLabel lblLatin = new SLabel(latin);

            view.add(english);
            view.add(translate);
            view.add(lblLatin);
            view.setSize(400, 100);
            view.setVisible(true);
        });
    }
}
