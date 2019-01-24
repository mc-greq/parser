/**
 * Parsowanie pliku xml realizuję przy pomocy SAXa gdyż nie potrzebujemy dostępu do całego pliku xml
 * Ponadto DOM nie łapie większych plików XML
 * Można by to zrealizować przy pomocy DOMa ale wymagałoby to zabawy ze zwiększaniem RAMu dla JVM.
 */
package parser;


import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.DefaultListModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Parser extends JFrame {

//    public static final FilenameFilter FILTR_NO_ADDRESS_XML = (dir, name) -> {
//        String lowercaseName = name.toLowerCase();
//        return lowercaseName.equals("calosc_ExtendedAddress_.xml");
//    };

    public static final FilenameFilter FILTR_XML = (dir, name) -> {
        String lowercaseName = name.toLowerCase();
        return lowercaseName.endsWith(".xml");
    };

    public static final FilenameFilter FILTR_ZIP = (dir, name) -> {
        String lowercaseName = name.toLowerCase();
        return lowercaseName.endsWith(".zip");
    };

    public static final String lineSeparator = System.getProperty("line.separator");

    // komponenty Swing
    private static final FileFilter jFileChooserFilter =
            new FileNameExtensionFilter("Zip", "zip");

    private JList<File> list = new JList<>();

    private JMenuBar menuBar = new JMenuBar();
    private JFileChooser fileChooser = new JFileChooser();
    private DefaultListModel<File> listModel = new DefaultListModel<>();

    // typy akcji kliknięcia
    private enum ACTION {
        ADD("Dodaj"),
        DELETE("Usuń"),
        START("Start"),
        CLOSE("Koniec");

        private String name;

        ACTION(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }



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
        Action actionAdd = new Akcja(
                ACTION.ADD,
                "Dodaj plik do listy",
                "ctrl A",
                new ImageIcon("dodaj.png")
        );
        Action actionDelete = new Akcja(
                ACTION.DELETE,
                "Usuń plik z listy",
                "ctrl D",
                new ImageIcon("usun.png"));
        Action actionStart = new Akcja(
                ACTION.START,
                "Rozpocznij pracę",
                "ctrl Z");
        Action actionExit = new Akcja(
                ACTION.CLOSE,
                "Zamknij program",
                "alt F4");

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

        JScrollPane scrollPane = new JScrollPane(list);
        list.setBorder(BorderFactory.createEtchedBorder());
        // cellRenderer pozwala wyświetlać tylko nazwę pliku
        list.setCellRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if(value != null){
                    value = ((File)value).getName();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        list.setModel(listModel);
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

    private class Akcja extends AbstractAction{

        private ACTION action;

        public Akcja(ACTION action, String desc, String keyboardShortcut){
            this.action = action;
            this.putValue(Action.NAME, action.toString());
            this.putValue(Action.SHORT_DESCRIPTION, desc);
            this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(keyboardShortcut));
        }

        public Akcja(ACTION action, String desc, String keyboardShortcut, Icon icon){
            this(action, desc, keyboardShortcut);
            this.putValue(Action.SMALL_ICON, icon);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (action){
                case ADD:
                    System.out.println("Dodawanie pliku");
                    addEntry();
                    break;

                case DELETE:
                    deleteEntry();
                    System.out.println("Usuwanie pliku");
                    break;

                case START:
                    startParsing();
                    System.out.println("Działanie parsera");
                    break;

                case CLOSE:
                    System.exit(0);
                    break;

            }
        }

        private void addEntry(){

            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(jFileChooserFilter);
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

        private void deleteEntry(){
            int[] indices = list.getSelectedIndices();
            for(int i = 0; i < indices.length; i++){
                listModel.remove(indices[i] - i);
            }
        }

    }

    // uruchomienie parsowania
    public void startParsing(){
        new Thread(() -> {
            System.out.println("START");

            long start = System.currentTimeMillis();
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            boolean testExAddress = false;

            File folderXml = new File(System.getProperty("user.dir") + "/XML/");

            try {

                // obiekt unzippera do rozpakowania archiwów z plikami xml
                Unzipper unzipper = new Unzipper();
                unzipper.unzipList(listModel, folderXml);

                SAXParser saxParser = saxParserFactory.newSAXParser();

                // otwieram strumień zapisu dla firm
                FileWriter outSFirma = new FileWriter("nowy.txt");
                // otwieram strumień zapisu dla uprawnień
                FileWriter outSUprawnienie = new FileWriter("uprawnienia.txt");
                // otwieram strumien zapisu dla oddziałów
                FileWriter outSOddzial = new FileWriter("oddzialy.txt");
                // otwieram strumien zapisu dla firm bez adresu calosc_calosc_ExtendedAddress_.xml
                FileWriter outExAddress = new FileWriter("no_address.txt");
                // otwieram strumiem zapisu kodów teryt do pliku
                FileWriter outTertyt = new FileWriter("teryt.txt");

                //drukuję nagłówki pliku dla Firm
                writeFirmaHeaders(outSFirma);

                // drukuję nagłówki pliku NoAddress
                writeNoAddressHeaders(outExAddress);

                // drukuję nagłówki pliku dla uprawnień
                writeUprawnieniaHeaders(outSUprawnienie);

                // drukuję nagłówki pliku dla oddziałów
                writeOddzialHeaders(outSOddzial);

                //tworzę obiekt handlera i przesyłam do konstruktora strumienie zapisu
//            MyHandler handler = new MyHandler(outSFirma, outSUprawnienie, outSOddzial);

                //pobieram pliki XML z folderu

                File[] listaPlikow = folderXml.listFiles(FILTR_XML);
                if(listaPlikow == null) {
                    System.out.println("Brak plików do pracy, przerwano.");
                }
                else {
                    for (File plik : listaPlikow) {
                        System.out.println(plik);
                        if(plik.getName().equals("calosc_ExtendedAddress_.xml")) {
                            MyHandler handler = new MyHandler(null, outSUprawnienie, outSOddzial,outExAddress, outTertyt);
                            saxParser.parse(plik, handler);
                            testExAddress = true;
                        } else {
                            MyHandler handler = new MyHandler(outSFirma, outSUprawnienie, outSOddzial,null, outTertyt);
                            saxParser.parse(plik, handler);
                        }
                    }


                }

                // zamykam strumienie zapisu
                outSFirma.close();
                outSUprawnienie.close();
                outSOddzial.close();
                outExAddress.close();
                outTertyt.close();

                if(!testExAddress){
                    Files.deleteIfExists(new File("no_address.txt").toPath());
                }

            } catch (ParserConfigurationException | SAXException | IOException e) {
                new MyExceptionHandler(e);
                System.out.println(e.getMessage());
            }
            long end = System.currentTimeMillis();
            DateFormat timeFormat = new SimpleDateFormat("mm:ss:SSS");
            JOptionPane.showMessageDialog(this, "Parsowanie zakończone: " + timeFormat.format(new Date(end -start)));
        }).start();
    }

    // drukowanie nagłówków generowanych plików

    public static void writeFirmaHeaders(FileWriter writer) throws IOException{
        writer.write("IdentyfikatorWpisu|"
                + "pImie|"
                + "pNazwisko|"
                + "pNip|"
                + "pREGON|"
                + "pFirma|"
                + "Telefon|"
                + "Faks|"
                + "AdresPocztyElektronicznej|"
                + "AdresStronyInternetowej|"
                + "gMiejscowosc|"
                + "gUlica|"
                + "gBudynek|"
                + "gLokal|"
                + "gKodPocztowy|"
                + "gPoczta|"
                + "gPowiat|"
                + "gWojewodztwo|"
                + "PrzedsiebiorcaPosiadaObywatelstwaPanstw|"
                + "DataRozpoczeciaWykonywaniaDzialalnosciGospodarczej|"
                + "DataZawieszeniaWykonywaniaDzialalnosciGospodarczej|"
                + "DataWznowieniaWykonywaniaDzialalnosciGospodarczej|"
                + "DataZaprzestaniaWykonywaniaDzialalnosciGospodarczej|"
                + "DataWykresleniaWpisuZRejestru|"
                + "MalzenskaWspolnoscMajatkowa|"
                + "Status|"
                + "KodyPKD"
                + lineSeparator);
    }

    public static void writeNoAddressHeaders(FileWriter writer) throws IOException{
        writer.write("IdentyfikatorWpisu|"
                + "pImie|"
                + "pNazwisko|"
                + "pNip|"
                + "pREGON|"
                + "pFirma|"
                + "Telefon|"
                + "Faks|"
                + "AdresPocztyElektronicznej|"
                + "AdresStronyInternetowej|"
                + "gMiejscowosc|"
                + "gUlica|"
                + "gBudynek|"
                + "gLokal|"
                + "gKodPocztowy|"
                + "gPoczta|"
                + "gPowiat|"
                + "gWojewodztwo|"
                + "dTERC|"
                + "dSIMC|"
                + "dULIC|"
                + "dWojewodztwo|"
                + "dPowiat|"
                + "dGmina|"
                + "dMiejscowsc|"
                + "dUlica|"
                + "dBudynek|"
                + "dLokal|"
                + "dKodPocztowy|"
                + "dPoczta|"
                + "PrzedsiebiorcaPosiadaObywatelstwaPanstw|"
                + "DataRozpoczeciaWykonywaniaDzialalnosciGospodarczej|"
                + "DataZawieszeniaWykonywaniaDzialalnosciGospodarczej|"
                + "DataWznowieniaWykonywaniaDzialalnosciGospodarczej|"
                + "DataZaprzestaniaWykonywaniaDzialalnosciGospodarczej|"
                + "DataWykresleniaWpisuZRejestru|"
                + "MalzenskaWspolnoscMajatkowa|"
                + "Status|"
                + "KodyPKD"
                + lineSeparator);
    }

    public static void writeUprawnieniaHeaders(FileWriter writer) throws IOException{
        writer.write("IdentyfikatorWpisu|"
                + "pNip|"
                + "pREGON|"
                + "WARTOSC"
                + lineSeparator);
    }

    public static void writeOddzialHeaders(FileWriter writer) throws IOException{
        writer.write("IdentyfikatorWpisu|"
                + "TERC|"
                + "SIMC|"
                + "ULIC|"
                + "Miejscowosc|"
                + "Ulica|"
                + "Budynek|"
                + "Lokal|"
                + "KodPocztowy|"
                + "Poczta|"
                + "Powiat|"
                + "Gmina|"
                + "Wojewodztwo|"
                + "OpisNietypowegoMiejscaLokalizacji"
                + lineSeparator);
    }

    // czyszczenie strignow ze wszystkich znaków końca linii, mogą zepsuć strukturę pliku
    public static String cleanString(String input){
        return input.replace("\n", " ")
                .replace("\r", " ")
                .replace("\t", " ")
                .replace("  ", " ");
    }
}

