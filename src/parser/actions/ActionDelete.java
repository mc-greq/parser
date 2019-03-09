package parser.actions;

import parser.Parser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ActionDelete extends AbstractAction {

    private JList<File> filesList;
    private DefaultListModel<File> listModel;

    public ActionDelete(Parser parser) {
        this.filesList = parser.getFilesList();
        this.listModel = parser.getListModel();
        ActionEnum action = ActionEnum.DELETE;
        this.putValue(Action.NAME, action.toString());
        this.putValue(Action.SHORT_DESCRIPTION, action.getDesc());
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(action.getKeyStroke()));
        this.putValue(Action.SMALL_ICON, action.getIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] indices = filesList.getSelectedIndices();
        for(int i = 0; i < indices.length; i++){
            listModel.remove(indices[i] - i);
        }
    }
}
