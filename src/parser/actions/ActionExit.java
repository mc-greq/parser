package parser.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionExit extends AbstractAction {

    public ActionExit() {
        ActionEnum action = ActionEnum.CLOSE;
        this.putValue(Action.NAME, action.toString());
        this.putValue(Action.SHORT_DESCRIPTION, action.getDesc());
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(action.getKeyStroke()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
