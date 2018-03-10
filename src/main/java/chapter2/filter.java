package chapter2;

import nz.sodium.CellLoop;
import nz.sodium.Stream;
import nz.sodium.Transaction;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import swidgets.SLabel;
import swidgets.SButton;

import java.awt.FlowLayout;

public class filter {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JFrame view = new JFrame();
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            view.setLayout(new FlowLayout());

            Transaction.runVoid(() -> {
                CellLoop<Integer> value = new CellLoop<>();
                SLabel lblValue = new SLabel(value.map(i -> Integer.toString(i)));

                SButton plus = new SButton("+");
                SButton minus = new SButton("-");

                view.add(lblValue);
                view.add(plus);
                view.add(minus);

                Stream<Integer> sPlusDelta = plus.sClicked.map(u -> 1);
                Stream<Integer> sMinusDelta = minus.sClicked.map(u -> -1);
                Stream<Integer> sDelta = sPlusDelta.orElse(sMinusDelta);
                Stream<Integer> sFilter = sDelta.snapshot(value,
                        (delta,value_) -> delta + value_).filter(n -> n>=0);

                value.loop(sFilter.hold(0));
            });

            view.setSize(400, 150);
            view.setVisible(true);
        });
    }
}
