/**
 * Parsowanie pliku xml realizuję przy pomocy SAXa gdyż nie potrzebujemy dostępu do całego pliku xml
 * Ponadto DOM nie łapie większych plików XML
 * Można by to zrealizować przy pomocy DOMa ale wymagałoby to zabawy ze zwiększaniem RAMu dla JVM.
 */
package parser;


import parser.actions.ActionAdd;
import parser.actions.ActionDelete;
import parser.actions.ActionExit;
import parser.actions.ActionStart;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

public class Parser extends JFrame {

//    public static final FilenameFilter FILTR_NO_ADDRESS_XML = (dir, name) ->
//            name.toLowerCase().equals("calosc_ExtendedAddress_.xml");

    public static final FilenameFilter FILTR_XML = (dir, name) -> name.toLowerCase().endsWith(".xml");

    public static final FilenameFilter FILTR_ZIP = (dir, name) -> name.toLowerCase().endsWith(".zip");


    public static final String lineSeparator = System.getProperty("line.separator");

    // komponenty Swing
    public static final FileFilter jFileChooserFilter =
            new FileNameExtensionFilter("Zip", "zip");

    private JList<File> filesList = new JList<>();

    private JMenuBar menuBar = new JMenuBar();
    private JFileChooser fileChooser = new JFileChooser();
    private DefaultListModel<File> listModel = new DefaultListModel<>();

    public Parser(){
        setComponents();
    }


    public static void main(String[] args){
        new Parser();
    }

    private void setComponents(){

        this.setBounds(300,200, 700,300);
        this.setVisible(true);
        this.setTitle("Parser");
        this.setJMenuBar(menuBar);

        // dodaję do listy wszystkie pliki zip z aktualnego foldera
        List<File> filesList = Arrays.asList(new File(System.getProperty("user.dir")).listFiles(FILTR_ZIP));
        listModel.addAll(filesList);

        // zdefiniowanie akcji dla przycisków
        Action actionAdd = new ActionAdd(this);
        Action actionDelete = new ActionDelete(this);
        Action actionStart = new ActionStart(this);
        Action actionExit = new ActionExit();

        // przyciski skonstruowane z powyższymi akcjami
        JButton buttonAdd = new JButton(actionAdd);
        JButton buttonDelete = new JButton(actionDelete);
        JButton buttonStart = new JButton(actionStart);
        JButton buttonExit = new JButton(actionExit);

        buttonAdd.setName("Dodaj");
        buttonDelete.setName("Usuń");
        buttonStart.setName("Start");
        buttonExit.setName("Koniec");

        JMenu menuFile = menuBar.add(new JMenu("Pliki"));

        menuFile.add(actionAdd);
        menuFile.add(actionDelete);
        menuFile.add(actionStart);
        menuFile.addSeparator();
        menuFile.add(actionExit);

        JScrollPane scrollPane = new JScrollPane(this.filesList);
        this.filesList.setBorder(BorderFactory.createEtchedBorder());
        // cellRenderer pozwala wyświetlać tylko nazwę pliku
        this.filesList.setCellRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if(value != null){
                    value = ((File)value).getName();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        this.filesList.setModel(listModel);
        GroupLayout layout = new GroupLayout(this.getContentPane());

        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(scrollPane, 300, 350, Short.MAX_VALUE)
                        .addContainerGap(0, Short.MAX_VALUE)
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(buttonAdd)
                                        .addComponent(buttonDelete)
                                        .addComponent(buttonStart)
                        )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(buttonAdd)
                                        .addComponent(buttonDelete)
                                        .addGap(5, 40, Short.MAX_VALUE)
                                        .addComponent(buttonStart)
                        )
        );

        this.getContentPane().setLayout(layout);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public DefaultListModel<File> getListModel() {
        return listModel;
    }

    public JList<File> getFilesList() {
        return filesList;
    }

    // czyszczenie strignow ze wszystkich znaków końca linii, mogą zepsuć strukturę pliku
    public static String cleanString(String input){
        return input.replace("\n", " ")
                .replace("\r", " ")
                .replace("\t", " ")
                .replace("  ", " ");
    }
}

