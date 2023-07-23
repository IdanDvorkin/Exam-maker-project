package Model;



import javafx.scene.control.Label;

public class LabelReact implements Observer {
    private Label label;
    private IteratorSubject subject;

    public  LabelReact(Label label2, IteratorSubject subject) {
        this.label = label2;
        this.subject = subject;
    }

    @Override
    public void update() {
        label.setVisible(true);
    }
}