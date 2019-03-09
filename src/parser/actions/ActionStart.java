package parser.actions;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import parser.*;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionStart extends AbstractAction {

    private DefaultListModel<File> listModel;
    private Parser parser;

    public ActionStart(Parser parser) {
        this.parser = parser;
        this.listModel = parser.getListModel();
        ActionEnum action = ActionEnum.START;
        this.putValue(Action.NAME, action.toString());
        this.putValue(Action.SHORT_DESCRIPTION, action.getDesc());
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(action.getKeyStroke()));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
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

                File[] listaPlikow = folderXml.listFiles(Parser.FILTR_XML);
                if(listaPlikow == null) {
                    System.out.println("Brak plików do pracy, przerwano.");
                }
                else {
                    for (File plik : listaPlikow) {
                        System.out.println(plik);
                        if(plik.getName().equals("calosc_ExtendedAddress_.xml")) {
                            DefaultHandler handler = new MyHandler(null, outSUprawnienie, outSOddzial,outExAddress, outTertyt);
                            saxParser.parse(plik, handler);
                            testExAddress = true;
                        } else {
                            DefaultHandler handler = new MyHandler(outSFirma, outSUprawnienie, outSOddzial,null, outTertyt);
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
            JOptionPane.showMessageDialog(parser, "Parsowanie zakończone: " + timeFormat.format(new Date(end -start)));
        }).start();
    }

    private static void writeFirmaHeaders(FileWriter writer) throws IOException{
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
                + Parser.lineSeparator);
    }

    private static void writeNoAddressHeaders(FileWriter writer) throws IOException{
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
                + Parser.lineSeparator);
    }

    private static void writeUprawnieniaHeaders(FileWriter writer) throws IOException{
        writer.write("IdentyfikatorWpisu|"
                + "pNip|"
                + "pREGON|"
                + "WARTOSC"
                + Parser.lineSeparator);
    }

    private static void writeOddzialHeaders(FileWriter writer) throws IOException{
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
                + Parser.lineSeparator);
    }
}
