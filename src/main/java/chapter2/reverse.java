package chapter2;

/**
 * Created by coolk on 11-02-2017.
 */
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;
import swidgets.STextField;
import swidgets.SLabel;
import nz.sodium.Cell;

public class reverse
{
    public static void main(String... args) throws Exception
    {
        SwingUtilities.invokeAndWait(() -> {
            JFrame frame = new JFrame("reverse");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            STextField msg = new STextField("Hello");
            Cell<String> reversed = msg.text.map(t ->
                    new StringBuilder(t).reverse().toString());
            SLabel lbl = new SLabel(reversed);
            frame.add(msg);
            frame.add(lbl);
            frame.setSize(400, 160);
            frame.setVisible(true);
        });
    }
}
