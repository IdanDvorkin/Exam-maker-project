package Model;

import javafx.scene.control.Button;


public class ButtonReact implements Observer {
    private Button button;
    private IteratorSubject subject;

    public ButtonReact(Button button2, IteratorSubject subject) {
        this.button = button2;
        this.subject = subject;
    }

    @Override
    public void update() {
        button.setVisible(true);
    }
}