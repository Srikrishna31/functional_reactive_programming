package chapter1;
import nz.sodium.Lambda2;
import nz.sodium.Cell;
import java.util.Calendar;
/*
A class that represents a decision based on two calendar dates.
 */
public class Rule {
    public Rule(Lambda2<Calendar, Calendar, Boolean> f) {
        this.f = f;
    }

    public final Lambda2<Calendar, Calendar, Boolean> f;

    public Cell<Boolean> reify(Cell<Calendar> dep, Cell<Calendar> ret) {
        return dep.lift(ret, f);
    }

    public Rule and (Rule other) {
        return new Rule(
                (d, r) -> this.f.apply(d, r) && other.f.apply(d, r));
    }
}
