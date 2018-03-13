package chapter3;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.GridBagLayout;

import swidgets.STextField;
import swidgets.SButton;

import nz.sodium.Cell;
import nz.sodium.Stream;
import nz.sodium.StreamLoop;
import nz.sodium.Transaction;

public class SSpinner extends JPanel {
    SSpinner(int initialValue) {
        StreamLoop<Integer> sSetValue = new StreamLoop<>();
        STextField textField = new STextField(
                sSetValue.map(v -> Integer.toString(v)),
                Integer.toString(initialValue), 5);

        this.value = textField.text.map(txt -> {
            try {
                return Integer.parseInt(txt);
            } catch (NumberFormatException ex) {
                return 0;
            }
        });

        SButton plus = new SButton("+");
        SButton minus = new SButton("-");
        setLayout(new GridBagLayout());
        add(textField);
        add(plus);
        add(minus);

        Stream<Integer> sPlusDelta = plus.sClicked.map(u -> 1);
        Stream<Integer> sMinusDelta = minus.sClicked.map(u -> -1);
        Stream<Integer> sDelta = sPlusDelta.orElse(sMinusDelta);
        sSetValue.loop(sDelta.snapshot(this.value,
                (delta, value) -> delta + value));
    }

    public final Cell<Integer> value;

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            Transaction.runVoid(() ->{
                JFrame frame = new JFrame("Spinner Widget Demo");
                SSpinner spinner = new SSpinner(0);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(spinner);
                frame.setSize(300, 100);
                frame.setVisible(true);
            });
        });
    }
}
