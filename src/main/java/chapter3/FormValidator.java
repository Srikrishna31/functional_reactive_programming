package chapter3;

/*
Demonstrates a simple form validation logic using FRP.
- Name must be non-blank and contain at least one space.
- The number of email addresses must be in the range of 1-4
- Onlyh the selected number of email address fields is enabled.
- Emails must contain an @ character.
 */

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.lang.reflect.Array;
import javax.swing.JComponent;
import javax.swing.JLabel;

import swidgets.STextField;
import swidgets.SButton;
import swidgets.SLabel;

import nz.sodium.Cell;
import nz.sodium.Stream;
import nz.sodium.Transaction;

public class FormValidator {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JFrame view = new JFrame("Form Validation");
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Transaction.runVoid(() -> {
                final int maxMails = 4;

                JLabel[] labels = new JLabel[maxMails + 2];
                JComponent[] fields = new JComponent[maxMails + 2];

                Cell<String>[] valids = (Cell<String>[]) Array.newInstance(Cell.class, maxMails + 2);

                int row = 0;

                labels[row] = new JLabel("Name");
                STextField name = new STextField("", 30);
                fields[row] = name;
                valids[row] = name.text.map(t -> t.trim().equals("") ?
                            "<-- enter something" : t.trim().indexOf(' ')
                                < 0 ? "<-- must contain space" : "");

                row++;

                labels[row] = new JLabel("No of email adresses");
                SSpinner number = new SSpinner(1);
                fields[row] = number;
                valids[row] = number.value.map(n ->
                        n < 1 || n > maxMails ? "<-- must be 1 to " + maxMails : "");
                row++;

                STextField[] emails = new STextField[maxMails];
                for (int i = 0; i < maxMails; i++, row++) {
                    labels[row] = new JLabel("Email #" + (i + 1));
                    final int ii = i;
                    Cell<Boolean> enabled = number.value.map(n -> ii < n);
                    STextField email = new STextField("", 30, enabled);
                    fields[row] = email;
                    valids[row] = email.text.lift(number.value, (e, n) ->
                                ii >= n ? "" :
                                e.trim().equals("") ? "<-- enter something" :
                                e.indexOf('@') < 0 ? "<-- must contain @" : "");
                }

                GridBagLayout layout = new GridBagLayout();
                view.setLayout(layout);
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.gridwidth = 1;
                c.gridheight = 1;
                Cell<Boolean> allValid = new Cell<>(true);
                for (int i = 0; i < row; i++) {
                    c.weightx = 0;
                    c.gridx = 0;
                    c.gridy = i;
                    view.add(labels[i], c);
                    c.weightx = 1.0;
                    c.gridx = 1;
                    view.add(fields[i], c);
                    c.weightx = 0;
                    c.gridx = 2;
                    SLabel validLabel = new SLabel(valids[i]);
                    view.add(validLabel, c);
                    Cell<Boolean> thisValid = valids[i].map(t -> t.equals(""));
                    allValid = allValid.lift(thisValid, (a, b) -> a && b);
                }
                c.weightx = 1.0;
                c.gridx = 0;
                c.gridy = row;
                c.fill = GridBagConstraints.NONE;

                SButton ok = new SButton("OK", allValid);
                view.add(ok, c);
            });
            view.setSize(600, 200);
            view.setVisible(true);
        });
    }
}
