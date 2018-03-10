package chapter2;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import nz.sodium.Cell;

import swidgets.STextField;
import swidgets.SLabel;

import java.awt.FlowLayout;

public class lift {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            STextField txtA = new STextField("5");
            STextField txtB = new STextField("10");

            Cell<Integer> a = txtA.text.map(t -> parseInt(t));
            Cell<Integer> b = txtB.text.map((t -> parseInt(t)));
            Cell<Integer> sum = a.lift(b, (a_, b_) -> a_ + b_);

            SLabel lblSum = new SLabel(sum.map(i -> Integer.toString(i)));

            frame.add(txtA);
            frame.add(txtB);
            frame.add(lblSum);
            frame.setSize(400, 150);
            frame.setVisible(true);
        });
    }

    private static Integer parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
