package parser;

import java.io.FileWriter;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler{
    
    //własny konstruktor wywołujący konstruktor nadrzędny i dodatkowo przyjmujący strumienie do zapisu pliku
    //sprawdzamy który plik ma byc zapisywany - plik główny ze strumienia outSFirma czy plik bez danych adresowych outExAddress
    public MyHandler(FileWriter outSFirma, FileWriter outSUprawnienie, FileWriter outSOddzial, FileWriter outExAddress, FileWriter outTeryt){
        super();
        this.outSFirma = outSFirma;
        this.outSUprawnienie = outSUprawnienie;
        this.outSOddzial = outSOddzial;
        this.outExAddress = outExAddress;
        this.outTeryt = outTeryt;
    }
    
    //private List<Firma> listaFirm = null;
    private Firma firma = null;
    private Uprawnienie uprawnienie = null;
    private Oddzial oddzial = null;
    /*
    public List<Firma> getListaFirm(){
        return listaFirm;
    }*/
    
    /**
     * Wykrywamy tutaj początek znacznika w pliku XML i ustawiamy wartość
     * zmiennej dla danego znacznika na TRUE.
     * 
     * @param atrbts
     * @throws org.xml.sax.SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atrbts) throws SAXException {

        if(qName.equalsIgnoreCase("InformacjaOWpisie")){
            //stworz nowy obiekt firma
            firma = new Firma();
            
            //wykrycie nowego rekordu
        } else if (qName.equalsIgnoreCase("IdentyfikatorWpisu")){
            identyfikatorWpisu = true;
            
        } else if (qName.equalsIgnoreCase("DanePodstawowe")){ // flaga danych podstawowych 
            danePodstawowe = true;
            
        } else if (qName.equalsIgnoreCase("Imie") && danePodstawowe){ // flaga imię
            imie = true;
            
        } else if (qName.equalsIgnoreCase("Nazwisko") && danePodstawowe){ //flaga nazwisko
            nazwisko = true;
            
        } else if (qName.equalsIgnoreCase("NIP") && danePodstawowe){
            nip = true;
            
        } else if (qName.equalsIgnoreCase("REGON") && danePodstawowe){
            regon = true;
            
        } else if (qName.equalsIgnoreCase("Firma") && danePodstawowe){
            nazwaFirmy = true; 
            
        } else if (qName.equalsIgnoreCase("DaneKontaktowe")){
            daneKontaktowe = true;
            
        } else if (qName.equalsIgnoreCase("AdresPocztyElektronicznej") && daneKontaktowe){
            adresPocztyElektronicznej = true;

        } else if (qName.equalsIgnoreCase("AdresStronyInternetowej") && daneKontaktowe){
            adresStronyInternetowej = true;

        } else if (qName.equalsIgnoreCase("Telefon") && daneKontaktowe){
            telefon = true;

        } else if (qName.equalsIgnoreCase("Faks") && daneKontaktowe){
            faks = true;
        
        } else if (qName.equalsIgnoreCase("DaneAdresowe")){
            daneAdresowe = true;
            
        /*
         * schodząc niżej w hierarchii flag musimy sprawdać ich prawdziwość po kolei, w tym przypadku
         * AdresGlownegoMiejscaWykonywaniaDzialalnosci znajduje się poniżej DaneAdresowe i zawiera kolejne
         * flagi oznaczające adresy. Mamy tam jeszcze inne typy adresów jak na przykład adres korespondencyjny.
         * 
         */
        } else if (qName.equalsIgnoreCase("AdresGlownegoMiejscaWykonywaniaDzialalnosci") && daneAdresowe) {
            adresGlowny = true;

        } else if (qName.equalsIgnoreCase("TERC") && adresGlowny){
            terytTerc = true;

        } else if (qName.equalsIgnoreCase("SIMC") && adresGlowny){
            terytSimc = true;

        } else if (qName.equalsIgnoreCase("ULIC") && adresGlowny){
            terytUlic = true;

        } else if (qName.equalsIgnoreCase("Miejscowosc") && adresGlowny){
            miejscowosc = true;
        
        } else if (qName.equalsIgnoreCase("Ulica") && adresGlowny){
            ulica = true;
        
        } else if (qName.equalsIgnoreCase("Budynek") && adresGlowny){
            budynek = true;
        
        } else if (qName.equalsIgnoreCase("Lokal") && adresGlowny){
            lokal = true;
        
        } else if (qName.equalsIgnoreCase("KodPocztowy") && adresGlowny){
            kodPocztowy = true;
        
        } else if (qName.equalsIgnoreCase("Poczta") && adresGlowny){
            poczta = true;
        
        } else if (qName.equalsIgnoreCase("Powiat") && adresGlowny){
            powiat = true;
        
        } else if (qName.equalsIgnoreCase("Wojewodztwo") && adresGlowny) {
            wojewodztwo = true;

        } else if (qName.equalsIgnoreCase("AdresDoDoreczen") && daneAdresowe && outExAddress != null) {
            adresDoDoreczen = true;

        } else if (qName.equalsIgnoreCase("TERC") && adresDoDoreczen) {
            dTerytTerc = true;

        } else if (qName.equalsIgnoreCase("SIMC") && adresDoDoreczen) {
            dTerytSimc = true;

        } else if (qName.equalsIgnoreCase("ULIC") && adresDoDoreczen) {
            dTerytUlic = true;

        } else if (qName.equalsIgnoreCase("Wojewodztwo") && adresDoDoreczen) {
            dWojewodztwo = true;

        } else if (qName.equalsIgnoreCase("Powiat") && adresDoDoreczen) {
            dPowiat = true;

        } else if (qName.equalsIgnoreCase("Gmina") && adresDoDoreczen) {
            dGmina = true;

        } else if (qName.equalsIgnoreCase("Miejscowosc") && adresDoDoreczen) {
            dMiejscowosc = true;

        } else if (qName.equalsIgnoreCase("Ulica") && adresDoDoreczen) {
            dUlica = true;

        } else if (qName.equalsIgnoreCase("Budynek") && adresDoDoreczen) {
            dBudynek = true;
            
        } else if (qName.equalsIgnoreCase("Lokal") && adresDoDoreczen) {
            dLokal = true;

        } else if (qName.equalsIgnoreCase("KodPocztowy") && adresDoDoreczen) {
            dKodPocztowy = true;

        } else if (qName.equalsIgnoreCase("Poczta") && adresDoDoreczen) {
            dPoczta = true;

        } else if (qName.equalsIgnoreCase("PrzedsiebiorcaPosiadaObywatelstwaPanstw") && daneAdresowe){
            obywatelstwo = true;
        
        } else if (qName.equalsIgnoreCase("DaneDodatkowe")){
            daneDodatkowe = true;
        
        } else if (qName.equalsIgnoreCase("DataRozpoczeciaWykonywaniaDzialalnosciGospodarczej") && daneDodatkowe){
            dataRozpoczecia = true;
        
        } else if (qName.equalsIgnoreCase("DataZawieszeniaWykonywaniaDzialalnosciGospodarczej") && daneDodatkowe){
            dataZawieszenia = true;
        
        } else if (qName.equalsIgnoreCase("DataWznowieniaWykonywaniaDzialalnosciGospodarczej") && daneDodatkowe){
            dataWznowienia = true;
        
        } else if (qName.equalsIgnoreCase("DataZaprzestaniaWykonywaniaDzialalnosciGospodarczej") && daneDodatkowe){
            dataZaprzestania = true;
        
        } else if (qName.equalsIgnoreCase("DataWykresleniaWpisuZRejestru") && daneDodatkowe){
            dataWykreslenia = true;
        
        } else if (qName.equalsIgnoreCase("MalzenskaWspolnoscMajatkowa") && daneDodatkowe){
        	malzenskaWspolnoscMajatkowa = true;
        
        } else if (qName.equalsIgnoreCase("Status") && daneDodatkowe){
            status = true;
        
        } else if (qName.equalsIgnoreCase("KodyPKD") && daneDodatkowe){
            pkd = true;
            
        } else if (qName.equalsIgnoreCase("Uprawnienia")){
            uprawnieniaKategoria = true;
            
        } else if (qName.equalsIgnoreCase("Uprawnienie") && uprawnieniaKategoria){
            uprawnienieTekst = true;
            
        /**
         * Dane oddziałów
         */
            
        } else if (qName.equalsIgnoreCase("AdresyDodatkowychMiejscWykonywaniaDzialalnosci")){
            adresyDodatkoweGrupa = true;

        } else if (qName.equalsIgnoreCase("Adres") && adresyDodatkoweGrupa){
        	adresDodatkowy = true;
        	oddzial = new Oddzial();
        	oddzial.setId(identyfikatorWpisuTemp);
        	
        } else if (qName.equalsIgnoreCase("TERC") && adresDodatkowy){
            terytTerc = true;
            
        } else if (qName.equalsIgnoreCase("SIMC") && adresDodatkowy){
            terytSimc = true;
            
        } else if (qName.equalsIgnoreCase("ULIC") && adresDodatkowy){
            terytUlic = true;
        	
        } else if (qName.equalsIgnoreCase("Miejscowosc") && adresDodatkowy){
            miejscowosc = true;
        
        } else if (qName.equalsIgnoreCase("Ulica") && adresDodatkowy){
            ulica = true;
        
        } else if (qName.equalsIgnoreCase("Budynek") && adresDodatkowy){
            budynek = true;
        
        } else if (qName.equalsIgnoreCase("Lokal") && adresDodatkowy){
            lokal = true;
        
        } else if (qName.equalsIgnoreCase("KodPocztowy") && adresDodatkowy){
            kodPocztowy = true;
        
        } else if (qName.equalsIgnoreCase("Poczta") && adresDodatkowy){
            poczta = true;
        
        } else if (qName.equalsIgnoreCase("Powiat") && adresDodatkowy){
            powiat = true;
        
        } else if (qName.equalsIgnoreCase("Wojewodztwo") && adresDodatkowy){
            wojewodztwo = true;
        
        } else if (qName.equalsIgnoreCase("OpisNietypowegoMiejscaLokalizacji") && adresDodatkowy){
            opisLokalizacji = true;
        
        }
        
    }
    
    /**
     * Wykrywamy koniec pojawiania się znacznika, zapisujemy wcześniej pobrane wartości
     * oraz ustawiamy jego wartość na FALSE.
     * @throws org.xml.sax.SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("InformacjaOWpisie")) {
            //add Employee object to list
            //listaFirm.add(firma);
    
            try {
                //jeśli trafi na element znaczący koniec rekordu zapisuje ten rekord do pliku przy pomocy przesłanego strumienia
                //zapisujemy do strumienia głównego pliku
                if(outSFirma != null) {
                    outSFirma.write(Parser.cleanString(firma.toString(true))
                            + Parser.lineSeparator);
                //zapisujemy do strumienia pliku bez adresów
                } else if(outExAddress != null){
                    outExAddress.write(Parser.cleanString(firma.toString(false))
                            + Parser.lineSeparator);
                }

                //zapis kodów teryt do pliku
                outTeryt.write(Parser.cleanString(firma.terytToString())
                        + Parser.lineSeparator);

                //jeśli odnaleziono uprawnienie to jest ono zapisywane do pliku
                if(!uprawnienieTemp.equals("")){
                    uprawnienie = new Uprawnienie(identyfikatorWpisuTemp, regonTemp, nipTemp, uprawnienieTemp);
                    outSUprawnienie.write(Parser.cleanString(uprawnienie.toString())
	                                                    + Parser.lineSeparator);
                }
                
            } catch (IOException ex) {
            	new MyExceptionHandler(ex);
            }
            
            //zeruję tymczasowe zmienne pod koniec rekordu
            identyfikatorWpisuTemp = "";
            nipTemp = "";
            regonTemp = "";
            uprawnienieTemp = "";
            
        } else if(qName.equalsIgnoreCase("DanePodstawowe") && danePodstawowe){
            danePodstawowe = false; // pod koniec danych podstawowych czyścimy flagę
            
        } else if (qName.equalsIgnoreCase("DaneKontaktowe") && daneKontaktowe){
            daneKontaktowe = false; // pod koniec danych kontaktowych czyścimy flagę
            
        } else if (qName.equalsIgnoreCase("DaneAdresowe") && daneAdresowe){
            daneAdresowe = false; // pod koniec danych adresowych czyścimy flagę
            
        } else if (qName.equalsIgnoreCase("DaneDodatkowe") && daneDodatkowe){
            daneDodatkowe = false; // pod koniec danych dodatkowych czyścimy flagę
            
        } else if (qName.equalsIgnoreCase("AdresGlownegoMiejscaWykonywaniaDzialalnosci") && adresGlowny){
            adresGlowny = false; // pod koniec adresu głównego czyścimy flagę

        } else if (qName.equalsIgnoreCase("AdresDoDoreczen") && adresGlowny && outExAddress != null){
            adresDoDoreczen = false; // pod koniec adresu do doręczeń czyścimy flagę, działa to tylko przy zapisie firm bez adresów
            
        } else if (qName.equalsIgnoreCase("Uprawnienia") && uprawnieniaKategoria){
            uprawnieniaKategoria = false; // pod koniec uprawnień czyścimy flagę
            
        } else if (qName.equalsIgnoreCase("AdresyDodatkowychMiejscWykonywaniaDzialalnosci") && adresyDodatkoweGrupa){
        	adresyDodatkoweGrupa = false; // pod koniec uprawnień czyścimy flagę
            
        /**
         * Zapis oddziałów
         */
        } else if (qName.equalsIgnoreCase("Adres") && adresDodatkowy){
        	adresDodatkowy = false;
        	
        	try{
        		outSOddzial.write(Parser.cleanString(oddzial.toString())
					                        + Parser.lineSeparator);// pod koniec adresu czyścimy flagę, oznacza to koniec oddziału, zapisujemy oddział do pliku
        	} catch(IOException e) {
        		new MyExceptionHandler(e);
        	}
            
        } else if (qName.equalsIgnoreCase("IdentyfikatorWpisu")){
            firma.setIdentyfikator(textContent.toString());
            identyfikatorWpisuTemp = textContent.toString();
            textContent.setLength(0);
            identyfikatorWpisu = false;
            
        } else if (qName.equalsIgnoreCase("Imie") && imie){
            firma.setImie(new String(textContent));
            textContent.setLength(0);
            imie = false;
            
        }else if (qName.equalsIgnoreCase("Nazwisko") && nazwisko){
            firma.setNazwisko(new String(textContent));
            textContent.setLength(0);
            nazwisko = false;
            
        }else if (qName.equalsIgnoreCase("NIP") && nip){
            firma.setNip(new String(textContent));
            nipTemp = new String(textContent);
            textContent.setLength(0);
            nip = false;
            
        }else if (qName.equalsIgnoreCase("REGON") && regon){
            firma.setRegon(new String(textContent));
            regonTemp = new String(textContent);
            textContent.setLength(0);
            regon = false;
            
        } else if (qName.equalsIgnoreCase("Firma") && nazwaFirmy){
            firma.setNazwaFirmy(textContent.toString());//.replace("\n", " ").replace("\r", " ").replace("\t", " ").replace("  ", " "));
            textContent.setLength(0);
            nazwaFirmy = false;
        
        } else if (qName.equalsIgnoreCase("AdresPocztyElektronicznej") && adresPocztyElektronicznej){
            firma.setEmail(textContent.toString());
            textContent.setLength(0);
            adresPocztyElektronicznej = false;

        } else if (qName.equalsIgnoreCase("AdresStronyInternetowej") && adresStronyInternetowej){
            firma.setWww(textContent.toString());
            textContent.setLength(0);
            adresStronyInternetowej = false;

        } else if (qName.equalsIgnoreCase("Telefon") && telefon){
            firma.setTelefon(textContent.toString());
            textContent.setLength(0);
            telefon = false;

        } else if (qName.equalsIgnoreCase("Faks") && faks){
            firma.setFaks(textContent.toString());
            textContent.setLength(0);
            faks = false;
            
        /**
         * Początek danych adresowych centrali
         */

        } else if (qName.equalsIgnoreCase("TERC") && terytTerc && adresGlowny){
            firma.setTerytTerc(textContent.toString());
            textContent.setLength(0);
            terytTerc = false;

        } else if (qName.equalsIgnoreCase("SIMC") && terytSimc && adresGlowny){
            firma.setTerytSimc(textContent.toString());
            textContent.setLength(0);
            terytSimc = false;

        } else if (qName.equalsIgnoreCase("ULIC") && terytUlic && adresGlowny){
            firma.setTerytUlic(textContent.toString());
            textContent.setLength(0);
            terytUlic = false;
            
        } else if (qName.equalsIgnoreCase("Miejscowosc") && miejscowosc && adresGlowny){
            firma.setMiejscowosc(textContent.toString());
            textContent.setLength(0);
            miejscowosc = false;
        
        } else if (qName.equalsIgnoreCase("Ulica") && ulica && adresGlowny){
            firma.setUlica(textContent.toString());
            textContent.setLength(0);
            ulica = false;
        
        } else if (qName.equalsIgnoreCase("Budynek") && budynek && adresGlowny){
            firma.setBudynek(textContent.toString());
            textContent.setLength(0);
            budynek = false;
        
        } else if (qName.equalsIgnoreCase("Lokal") && lokal && adresGlowny){
            firma.setLokal(textContent.toString());
            textContent.setLength(0);
            lokal = false;
        
        } else if (qName.equalsIgnoreCase("KodPocztowy") && kodPocztowy && adresGlowny){
            firma.setKod(textContent.toString());
            textContent.setLength(0);
            kodPocztowy = false;
        
        } else if (qName.equalsIgnoreCase("Poczta") && poczta && adresGlowny){
            firma.setPoczta(textContent.toString());
            textContent.setLength(0);
            poczta = false;
        
        } else if (qName.equalsIgnoreCase("Powiat") &&  powiat && adresGlowny){
            firma.setPowiat(textContent.toString());
            textContent.setLength(0);
            powiat = false;
        
        } else if (qName.equalsIgnoreCase("Wojewodztwo") && wojewodztwo && adresGlowny){
            firma.setWojewodztwo(textContent.toString());
            textContent.setLength(0);
            wojewodztwo = false;
            
        /**
         * Koniec danych adresowych centrali
         *
         * Początek adresów do doręczeń
         */

        } else if (qName.equalsIgnoreCase("TERC") && dTerytTerc && adresDoDoreczen){
            firma.setdTerytTerc(textContent.toString());
            textContent.setLength(0);
            dTerytTerc = false;

        } else if (qName.equalsIgnoreCase("SIMC") && dTerytSimc && adresDoDoreczen){
            firma.setdTerytSimc(textContent.toString());
            textContent.setLength(0);
            dTerytSimc = false;

        } else if (qName.equalsIgnoreCase("ULIC") && dTerytUlic && adresDoDoreczen){
            firma.setdTerytUlic(textContent.toString());
            textContent.setLength(0);
            dTerytUlic = false;

        } else if (qName.equalsIgnoreCase("Wojewodztwo") && dWojewodztwo && adresDoDoreczen){
            firma.setdWojewodztwo(textContent.toString());
            textContent.setLength(0);
            dWojewodztwo = false;

        } else if (qName.equalsIgnoreCase("Powiat") && dPowiat && adresDoDoreczen){
            firma.setdPowiat(textContent.toString());
            textContent.setLength(0);
            dPowiat = false;

        } else if (qName.equalsIgnoreCase("Gmina") && dGmina && adresDoDoreczen){
            firma.setdGmina(textContent.toString());
            textContent.setLength(0);
            dGmina = false;

        } else if (qName.equalsIgnoreCase("Miejscowosc") && dMiejscowosc && adresDoDoreczen){
            firma.setdMiejscowosc(textContent.toString());
            textContent.setLength(0);
            dMiejscowosc = false;

        } else if (qName.equalsIgnoreCase("Ulica") && dUlica && adresDoDoreczen){
            firma.setdUlica(textContent.toString());
            textContent.setLength(0);
            dUlica = false;

        } else if (qName.equalsIgnoreCase("Budynek") && dBudynek && adresDoDoreczen){
            firma.setdBudynek(textContent.toString());
            textContent.setLength(0);
            dBudynek = false;
            
        } else if (qName.equalsIgnoreCase("Lokal") && dLokal && adresDoDoreczen){
            firma.setdLokal(textContent.toString());
            textContent.setLength(0);
            dLokal = false;

        } else if (qName.equalsIgnoreCase("KodPocztowy") && dKodPocztowy && adresDoDoreczen){
            firma.setdKodPocztowy(textContent.toString());
            textContent.setLength(0);
            dKodPocztowy = false;

        } else if (qName.equalsIgnoreCase("Poczta") && dPoczta && adresDoDoreczen){
            firma.setdPoczta(textContent.toString());
            textContent.setLength(0);
            dPoczta = false;

        /**
         * Koniec adresów do doręczeń
         *
         * Początek danych adresowych oddziału
         */
            
        } else if(qName.equalsIgnoreCase("TERC") && terytTerc && adresDodatkowy) {
        	oddzial.setTerytTerc(textContent.toString());
        	textContent.setLength(0);
        	terytTerc = false;
    	
        } else if(qName.equalsIgnoreCase("SIMC") && terytSimc && adresDodatkowy) {
        	oddzial.setTerytSimc(textContent.toString());
        	textContent.setLength(0);
        	terytSimc = false;
    	
        } else if(qName.equalsIgnoreCase("ULIC") && terytUlic && adresDodatkowy) {
        	oddzial.setTerytUlic(textContent.toString());
        	textContent.setLength(0);
        	terytUlic = false;
    	
        } else if(qName.equalsIgnoreCase("Miejscowosc") && miejscowosc && adresDodatkowy) {
        	oddzial.setMiejscowosc(textContent.toString());
        	textContent.setLength(0);
        	miejscowosc = false;
    	
        } else if(qName.equalsIgnoreCase("Ulica") && ulica && adresDodatkowy) {
        	oddzial.setUlica(textContent.toString());
        	textContent.setLength(0);
        	ulica = false;
        	
        } else if(qName.equalsIgnoreCase("Budynek") && budynek && adresDodatkowy) {
        	oddzial.setBudynek(textContent.toString());
        	textContent.setLength(0);
        	budynek = false;
        	
        } else if(qName.equalsIgnoreCase("Lokal") && lokal && adresDodatkowy) {
        	oddzial.setLokal(textContent.toString());
        	textContent.setLength(0);
        	lokal = false;
        	
        } else if(qName.equalsIgnoreCase("KodPocztowy") && kodPocztowy && adresDodatkowy) {
        	oddzial.setKodPocztowy(textContent.toString());
        	textContent.setLength(0);
        	kodPocztowy = false;
        	
        } else if(qName.equalsIgnoreCase("Poczta") && poczta && adresDodatkowy) {
        	oddzial.setPoczta(textContent.toString());
        	textContent.setLength(0);
        	poczta = false;
        	
        } else if(qName.equalsIgnoreCase("Powiat") && powiat && adresDodatkowy) {
        	oddzial.setPowiat(textContent.toString());
        	textContent.setLength(0);
        	powiat = false;
        	
        } else if(qName.equalsIgnoreCase("Wojewodztwo") && wojewodztwo && adresDodatkowy) {
        	oddzial.setWojewodztwo(textContent.toString());
        	textContent.setLength(0);
        	wojewodztwo = false;
        	
        } else if(qName.equalsIgnoreCase("OpisNietypowegoMiejscaLokalizacji") && opisLokalizacji && adresDodatkowy) {
        	oddzial.setOpis(textContent.toString());
        	textContent.setLength(0);
        	opisLokalizacji = false;
        	
        /**
         * Koniec danych oddziału
         */
        	
    	}else if (qName.equalsIgnoreCase("PrzedsiebiorcaPosiadaObywatelstwaPanstw") && obywatelstwo){
            firma.setObywatelstwo(textContent.toString());
            textContent.setLength(0);
            obywatelstwo = false;
        
        } else if (qName.equalsIgnoreCase("DataRozpoczeciaWykonywaniaDzialalnosciGospodarczej") && dataRozpoczecia){
            firma.setDataRozp(textContent.toString());
            textContent.setLength(0);
            dataRozpoczecia = false;
        
        } else if (qName.equalsIgnoreCase("DataZawieszeniaWykonywaniaDzialalnosciGospodarczej") && dataZawieszenia){
            firma.setDataZaw(textContent.toString());
            textContent.setLength(0);
            dataZawieszenia = false;
        
        } else if (qName.equalsIgnoreCase("DataWznowieniaWykonywaniaDzialalnosciGospodarczej") && dataWznowienia){
            firma.setDataWznow(textContent.toString());
            textContent.setLength(0);
            dataWznowienia = false;
            
        } else if (qName.equalsIgnoreCase("DataZaprzestaniaWykonywaniaDzialalnosciGospodarczej") && dataZaprzestania){
            firma.setDataZaprz(textContent.toString());
            textContent.setLength(0);
            dataZaprzestania = false;
        
        } else if (qName.equalsIgnoreCase("DataWykresleniaWpisuZRejestru") && dataWykreslenia){
            firma.setDataWykr(textContent.toString());
            textContent.setLength(0);
            dataWykreslenia = false;
        
        } else if (qName.equalsIgnoreCase("MalzenskaWspolnoscMajatkowa") && malzenskaWspolnoscMajatkowa){
            firma.setMalzeznskaWspolnoscMajatkowa(textContent.toString());
            textContent.setLength(0);
            malzenskaWspolnoscMajatkowa = false;
        
        } else if (qName.equalsIgnoreCase("Status") && status){
            firma.setStatus(textContent.toString());
            textContent.setLength(0);
            status = false;
        
        } else if (qName.equalsIgnoreCase("KodyPKD") && pkd){
            firma.setPkd(textContent.toString());
            textContent.setLength(0);
            pkd = false;
        
        } else if (qName.equalsIgnoreCase("Uprawnienie") && uprawnienieTekst){
            uprawnienieTemp = textContent.toString();//.replace("\n", " ").replace("\r", " ").replace("\t", " ").replace("  ", " ");
            textContent.setLength(0);
            uprawnienieTekst = false;
        }
    }
    
    /**
     * Jeśli wartość danego znacznika jest TRUE to wtedy sklejamy jego zawartość
     * characters podaje dane we fragmentach z bufora więc zmienna jest sklejana
     * przez append.
     * @throws org.xml.sax.SAXException
     */
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (identyfikatorWpisu) {
        //characters może być wywołane kilkukrotnie dla jednego elementu stąd konieczne sklejanie, wartość ustawiana jest w endElement
            textContent.append(ch, start, length);
        } else if (imie) {
            textContent.append(ch, start, length);
            
        } else if (nazwisko) {
            textContent.append(ch, start, length);
            
        } else if (nip){
            textContent.append(ch, start, length);
            
        } else if (regon){
            textContent.append(ch, start, length);
            
        } else if(nazwaFirmy){
            textContent.append(ch, start, length);
            
        } else if(adresPocztyElektronicznej){
            textContent.append(ch, start, length);

        } else if(adresStronyInternetowej){
            textContent.append(ch, start, length);

        } else if(telefon){
            textContent.append(ch, start, length);
        
        } else if(faks){
            textContent.append(ch, start, length);
        
        } else if(miejscowosc){
            textContent.append(ch, start, length);
        
        } else if(ulica){
            textContent.append(ch, start, length);
        
        } else if(budynek){
            textContent.append(ch, start, length);
        
        } else if(lokal){
            textContent.append(ch, start, length);
        
        } else if(kodPocztowy){
            textContent.append(ch, start, length);
        
        } else if(poczta){
            textContent.append(ch, start, length);
        
        } else if(powiat){
            textContent.append(ch, start, length);
        
        } else if(wojewodztwo){
            textContent.append(ch, start, length);

        } else if(dTerytTerc){
            textContent.append(ch, start, length);

        } else if(dTerytSimc){
            textContent.append(ch, start, length);

        } else if(dTerytUlic){
            textContent.append(ch, start, length);

        } else if(dWojewodztwo){
            textContent.append(ch, start, length);

        } else if(dPowiat){
            textContent.append(ch, start, length);

        } else if(dGmina){
            textContent.append(ch, start, length);

        } else if(dMiejscowosc) {
            textContent.append(ch, start, length);

        } else if(dUlica) {
            textContent.append(ch, start, length);

        } else if(dBudynek) {
            textContent.append(ch, start, length);
            
        } else if(dLokal) {
            textContent.append(ch, start, length);

        } else if(dKodPocztowy) {
            textContent.append(ch, start, length);

        } else if(dPoczta){
            textContent.append(ch, start, length);
        
        } else if(obywatelstwo){
            textContent.append(ch, start, length);
        
        } else if(dataRozpoczecia){
            textContent.append(ch, start, length);
        
        } else if(dataZawieszenia){
            textContent.append(ch, start, length);
        
        } else if(dataWznowienia){
            textContent.append(ch, start, length);
        
        } else if(dataZaprzestania){
            textContent.append(ch, start, length);
        
        } else if(dataWykreslenia){
            textContent.append(ch, start, length);
        
        } else if(malzenskaWspolnoscMajatkowa){
            textContent.append(ch, start, length);
        
        } else if(status){
            textContent.append(ch, start, length);
        
        } else if(pkd){
            textContent.append(ch, start, length);
        
        } else if(uprawnienieTekst){
            textContent.append(ch, start, length);
            
        } else if(terytTerc){
            textContent.append(ch, start, length);
            
        } else if(terytSimc){
            textContent.append(ch, start, length);
            
        } else if(terytUlic){
            textContent.append(ch, start, length);
            
        } else if(opisLokalizacji){
            textContent.append(ch, start, length);
        }
        
    }
    //zmienne strumieni danych
    private final FileWriter outSFirma;
    private final FileWriter outSUprawnienie;
    private final FileWriter outSOddzial;
    private final FileWriter outExAddress;
    private final FileWriter outTeryt;

    //zmienne znaczników kategorii
    private boolean danePodstawowe = false;
    private boolean daneKontaktowe = false;
    private boolean daneAdresowe = false;
    private boolean adresGlowny = false;
    private boolean daneDodatkowe = false;
    private boolean uprawnieniaKategoria = false;
    private boolean adresyDodatkoweGrupa = false;
    private boolean adresDodatkowy = false;
    private boolean adresDoDoreczen = false;
    
    //tymczasowe zmienne dla przechowywania danych wpisu
    private String identyfikatorWpisuTemp = "";
    private String nipTemp = "";
    private String regonTemp = "";
    private String uprawnienieTemp = "";
    
    //zmienna do której trafiają dane przed przekazaniem do obiektu
    private StringBuilder textContent = new StringBuilder();
    
    //zmienne które są przełączne true-false jeśli trafiono na odpowiedni znacznik
    private boolean terytTerc = false;
    private boolean terytSimc = false;
    private boolean terytUlic = false;
    private boolean opisLokalizacji = false;
    		
    private boolean identyfikatorWpisu = false;
    private boolean imie = false;
    private boolean nazwisko = false;
    private boolean nip = false;
    private boolean regon = false;
    private boolean nazwaFirmy = false;
    private boolean telefon = false;
    private boolean faks = false;
    private boolean adresPocztyElektronicznej = false;
    private boolean adresStronyInternetowej = false;
    private boolean miejscowosc = false;
    private boolean ulica = false;
    private boolean budynek = false;
    private boolean lokal = false;
    private boolean kodPocztowy = false;
    private boolean poczta = false;
    private boolean powiat = false;
    private boolean wojewodztwo = false;
    private boolean dTerytTerc = false;
    private boolean dTerytSimc = false;
    private boolean dTerytUlic = false;
    private boolean dWojewodztwo = false;
    private boolean dGmina = false;
    private boolean dPowiat = false;
    private boolean dMiejscowosc = false;
    private boolean dUlica = false;
    private boolean dBudynek = false;
    private boolean dLokal = false;
    private boolean dKodPocztowy = false;
    private boolean dPoczta = false;
    private boolean obywatelstwo = false;
    private boolean dataRozpoczecia = false;
    private boolean dataZawieszenia = false;
    private boolean dataWznowienia = false;
    private boolean dataZaprzestania = false;
    private boolean dataWykreslenia = false;
    private boolean malzenskaWspolnoscMajatkowa = false;
    private boolean status = false;
    private boolean pkd = false;
    private boolean uprawnienieTekst = false;
    
}
