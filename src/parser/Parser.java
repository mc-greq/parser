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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Parser {

    public static void main(String[] args){
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        
        File folderXml = new File(System.getProperty("user.dir") + "/XML/");

        try {
        	
            // obiekt unzippera do rozpakowania archiwów z plikami xml
            Unzipper unzipper = new Unzipper();        
            unzipper.unzipList(new File(System.getProperty("user.dir")), folderXml);
            
            SAXParser saxParser = saxParserFactory.newSAXParser();
            
            //otwieram strumień zapisu dla firm
            FileWriter outSFirma = new FileWriter("nowy.txt");
            //otwieram strumień zapisu dla uprawnień
            FileWriter outSUprawnienie = new FileWriter("uprawnienia.txt");
            //otwieram strumien zapisu dla oddziałów
            FileWriter outSOddzial = new FileWriter("oddzialy.txt");
            
            //drukuję strukturę pliku dla Firm - tworzyć tutaj czy we własnej klasie strumień?
            outSFirma.write("IdentyfikatorWpisu|"
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
                        + System.getProperty("line.separator"));
            
            //drukuję strukturę pliku dla uprawnień
            outSUprawnienie.write("IdentyfikatorWpisu|"
                                + "pNip|"
                                + "pREGON|"
                                + "WARTOSC"
                                + System.getProperty("line.separator"));
            
            //drukuję strukturę pliku dla oddziałów
            outSOddzial.write("IdentyfikatorWpisu|"
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
            				+ System.getProperty("line.separator"));
            
            //tworzę obiekt handlera i przesyłam do konstruktora strumienie zapisu
            MyHandler handler = new MyHandler(outSFirma, outSUprawnienie, outSOddzial);
            
            //pobieram pliki XML z folderu


            File[] listaPlikow = folderXml.listFiles(FILTR_XML);
            if(listaPlikow == null) {
            	System.out.println("Brak plików do pracy, przerwano.");
            }
            else {
                for (File plik : listaPlikow) {
                    System.out.println(plik);
                    saxParser.parse(plik, handler);
                }
            }
            

            //odpalam parsowanie przy pomocy handlera
            //saxParser.parse(new File(System.getProperty("user.dir") + File.separator + "test.xml"), handler);
            
            //Get Employees list
            //List<Firma> listaFirm = handler.getListaFirm();
            
            //zamykam strumienie zapisu
            outSFirma.close();
            outSUprawnienie.close();
            outSOddzial.close();
            
            //print employee information
            //for(Firma emp : listaFirm)
                //System.out.println(emp);
            //System.out.println(i);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            new MyExceptionHandler(e);
        }
        
    }

    public static final FilenameFilter FILTR_XML = new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            return lowercaseName.endsWith(".xml") && lowercaseName.startsWith("calosc_");
        }
    };
    
    public static final FilenameFilter FILTR_ZIP = new FilenameFilter() {

		@Override
		public boolean accept(File dir, String name) {
			 String lowercaseName = name.toLowerCase();
             return lowercaseName.endsWith(".zip") && lowercaseName.startsWith("calosc_");
		}
    };
}

