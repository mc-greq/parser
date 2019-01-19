/**
 * Parsowanie pliku xml realizuję przy pomocy SAXa gdyż nie potrzebujemy dostępu do całego pliku xml
 * Ponadto DOM nie łapie większych plików XML
 * Można by to zrealizować przy pomocy DOMa ale wymagałoby to zabawy ze zwiększaniem RAMu dla JVM.
 */
package parser;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Parser {

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

    public static void main(String[] args){
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        boolean testExAddress = false;
        
        File folderXml = new File(System.getProperty("user.dir") + "/XML/");
        File folderZip = new File(System.getProperty("user.dir"));

        try {
        	
            // obiekt unzippera do rozpakowania archiwów z plikami xml
            Unzipper unzipper = new Unzipper();
            unzipper.unzipList(folderZip, folderXml);
            
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

                if(!testExAddress){
                    Files.deleteIfExists(new File("no_address.txt").toPath());
                }
            }
            
            //zamykam strumienie zapisu
            outSFirma.close();
            outSUprawnienie.close();
            outSOddzial.close();
            outExAddress.close();
            outTertyt.close();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            new MyExceptionHandler(e);
        }
        
    }

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

    // czyszczenie strinow ze wszystkich znaków końca linii, mogą zepsuć strukturę pliku
    public static String cleanString(String input){
        return input.replace("\n", " ")
                .replace("\r", " ")
                .replace("\t", " ")
                .replace("  ", " ");
    }
}

