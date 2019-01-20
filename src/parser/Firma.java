package parser;

public class Firma {

    public String terytToString(){
        return this.identyfikatorWpisu + "|" +
                this.terytTerc + "|" +
                this.terytSimc + "|" +
                this.terytUlic + "|";
    }

    public String toString(Boolean firma){
        String danePodstawowe = this.identyfikatorWpisu + "|" +
                this.imie + "|" +
                this.nazwisko + "|" +
                this.nip + "|" +
                this.regon + "|" +
                this.nazwaFirmy + "|" +
                this.telefon + "|" +
                this.faks + "|" +
                this.adresPocztyElektronicznej + "|" +
                this.adresStronyInternetowej + "|" +
                this.miejscowosc + "|" +
                this.ulica + "|" +
                this.budynek + "|" +
                this.lokal + "|" +
                this.kodPocztowy + "|" +
                this.poczta + "|" +
                this.powiat + "|" +
                this.wojewodztwo + "|";
        if(firma) {
            return danePodstawowe +
                    this.obywatelstwo + "|" +
                    this.dataRozpoczecia + "|" +
                    this.dataZawieszenia + "|" +
                    this.dataWznowienia + "|" +
                    this.dataZaprzestania + "|" +
                    this.dataWykreslenia + "|" +
                    this.malzenskaWspolnoscMajatkowa + "|" +
                    this.status + "|" +
                    this.pkd;
        } else {
            return danePodstawowe +
                    this.dTerytTerc + "|" +
                    this.dTerytSimc + "|" +
                    this.dTerytUlic + "|" +
                    this.dWojewodztwo + "|" +
                    this.dPowiat + "|" +
                    this.dGmina + "|" +
                    this.dMiejscowosc + "|" +
                    this.dUlica + "|" +
                    this.dBudynek + "|" +
                    this.dLokal + "|" +
                    this.dKodPocztowy + "|" +
                    this.dPoczta + "|" +
                    this.obywatelstwo + "|" +
                    this.dataRozpoczecia + "|" +
                    this.dataZawieszenia + "|" +
                    this.dataWznowienia + "|" +
                    this.dataZaprzestania + "|" +
                    this.dataWykreslenia + "|" +
                    this.malzenskaWspolnoscMajatkowa + "|" +
                    this.status + "|" +
                    this.pkd;
        }
    }

    public String getIdentyfikator(){
        return this.identyfikatorWpisu;
    }

    public void setIdentyfikator(String identyfikatorWpisu){
        this.identyfikatorWpisu = identyfikatorWpisu.replace("|", "");
    }

    public void setImie(String imie){
        this.imie = imie.replace("|", "");
    }

    public void setNazwisko(String nazwisko){
        this.nazwisko = nazwisko.replace("|", "");
    }

    public void setNip(String nip){
        this.nip = nip.replace("|", "");
    }

    public void setRegon(String regon){
        this.regon = regon.replace("|", "");
    }

    public void setNazwaFirmy(String nazwaFirmy){
        this.nazwaFirmy = nazwaFirmy.replace("|", "");
    }

    public void setTelefon(String telefon){
        this.telefon = telefon.replace("|", "");
    }

    public void setFaks(String faks){
        this.faks = faks.replace("|", "");
    }

    public void setEmail(String email){
        this.adresPocztyElektronicznej = email.replace("|", "");
    }

    public void setWww(String www){
        this.adresStronyInternetowej = www.replace("|", "");
    }

    public void setTerytTerc(String terytTerc) {
        this.terytTerc = terytTerc;
    }

    public void setTerytSimc(String terytSimc) {
        this.terytSimc = terytSimc;
    }

    public void setTerytUlic(String terytUlic) {
        this.terytUlic = terytUlic;
    }

    public void setMiejscowosc(String miejscowosc){
        this.miejscowosc = miejscowosc.replace("|", "");
    }

    public void setUlica(String ulica){
        this.ulica = ulica.replace("|", "");
    }

    public void setBudynek(String budynek){
        this.budynek = budynek.replace("|", "");
    }

    public void setLokal(String lokal){
        this.lokal = lokal.replace("|", "");
    }

    public void setKod(String kod){
        this.kodPocztowy = kod.replace("|", "");
    }

    public void setPoczta(String poczta){
        this.poczta = poczta.replace("|", "");
    }

    public void setPowiat(String powiat){
        this.powiat = powiat.replace("|", "");
    }

    public void setWojewodztwo(String wojewodztwo){
        this.wojewodztwo = wojewodztwo.replace("|", "");
    }

    public void setdTerytTerc(String dTerytTerc) {
        this.dTerytTerc = dTerytTerc;
    }

    public void setdTerytSimc(String dTerytSimc) {
        this.dTerytSimc = dTerytSimc;
    }

    public void setdTerytUlic(String dTerytUlic) {
        this.dTerytUlic = dTerytUlic;
    }

    public void setdWojewodztwo(String dWojewodztwo) {
        this.dWojewodztwo = dWojewodztwo;
    }

    public void setdGmina(String dGmina) {
        this.dGmina = dGmina;
    }

    public void setdPowiat(String dPowiat) {
        this.dPowiat = dPowiat;
    }

    public void setdMiejscowosc(String dMiejscowosc) {
        this.dMiejscowosc = dMiejscowosc;
    }

    public void setdUlica(String dUlica) {
        this.dUlica = dUlica;
    }

    public void setdBudynek(String dBudynek) {
        this.dBudynek = dBudynek;
    }

    public void setdLokal(String dLokal) {
        this.dLokal = dLokal;
    }

    public void setdKodPocztowy(String dKodPocztowy) {
        this.dKodPocztowy = dKodPocztowy;
    }

    public void setdPoczta(String dPoczta) {
        this.dPoczta = dPoczta;
    }

    public void setObywatelstwo(String obywatelstwo){
        this.obywatelstwo = obywatelstwo.replace("|", "");
    }

    public void setDataRozp(String dataRozpoczecia){
        this.dataRozpoczecia = dataRozpoczecia.replace("|", "");
    }

    public void setDataZaw(String dataZawieszenia){
        this.dataZawieszenia = dataZawieszenia.replace("|", "");
    }

    public void setDataWznow(String dataWznowienia){
        this.dataWznowienia = dataWznowienia.replace("|", "");
    }

    public void setDataZaprz(String dataZaprzestania){
        this.dataZaprzestania = dataZaprzestania.replace("|", "");
    }

    public void setDataWykr(String dataWykreslenia){
        this.dataWykreslenia = dataWykreslenia.replace("|", "");
    }

    public void setMalzeznskaWspolnoscMajatkowa(String malzenskaWspolnoscMajatkowa){
        this.malzenskaWspolnoscMajatkowa = malzenskaWspolnoscMajatkowa.replace("|", "");
    }

    public void setStatus(String status){
        this.status = status.replace("|", "");
    }

    public void setPkd(String pkd){
        this.pkd = pkd.replace("|", "");
    }
    
    private String identyfikatorWpisu= "";
    private String imie= "";
    private String nazwisko= "";
    private String nip= "";
    private String regon= "";
    private String nazwaFirmy= "";
    private String telefon= "";
    private String faks= "";
    private String adresPocztyElektronicznej= "";
    private String adresStronyInternetowej= "";
    private String terytTerc = "";
    private String terytSimc = "";
    private String terytUlic = "";
    private String miejscowosc= "";
    private String ulica= "";
    private String budynek= "";
    private String lokal= "";
    private String kodPocztowy= "";
    private String poczta= "";
    private String powiat= "";
    private String wojewodztwo= "";
    private String dTerytTerc = "";
    private String dTerytSimc = "";
    private String dTerytUlic = "";
    private String dWojewodztwo = "";
    private String dGmina = "";
    private String dPowiat = "";
    private String dMiejscowosc = "";
    private String dUlica = "";
    private String dBudynek = "";
    private String dLokal = "";
    private String dKodPocztowy = "";
    private String dPoczta = "";
    private String obywatelstwo= "";
    private String dataRozpoczecia= "";
    private String dataZawieszenia= "";
    private String dataWznowienia= "";
    private String dataZaprzestania= "";
    private String dataWykreslenia= "";
    private String malzenskaWspolnoscMajatkowa= "";
    private String status= "";
    private String pkd= "";
}
