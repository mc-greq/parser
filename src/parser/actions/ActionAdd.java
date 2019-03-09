package parser.actions;

import parser.Parser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ActionAdd extends AbstractAction {

    private JFileChooser fileChooser;
    private DefaultListModel<File> listModel;
    private JRootPane rootPane;

    public ActionAdd(Parser parser) {
        this.fileChooser = parser.getFileChooser();
        this.listModel = parser.getListModel();
        this.rootPane = parser.getRootPane();
        ActionEnum action = ActionEnum.ADD;
        this.putValue(Action.NAME, action.toString());
        this.putValue(Action.SHORT_DESCRIPTION, action.getDesc());
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(action.getKeyStroke()));
        this.putValue(Action.SMALL_ICON, action.getIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(Parser.jFileChooserFilter);
        fileChooser.setMultiSelectionEnabled(true);

        int tmp = fileChooser.showDialog(rootPane, "Dodaj");

        if(tmp == JFileChooser.APPROVE_OPTION){
            File[] files = fileChooser.getSelectedFiles();

            for(File file: files){
                if(!listModel.contains(file)) {
                    listModel.addElement(file);
                }
            }
        }
    }
}
