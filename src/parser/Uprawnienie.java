package parser;

public class Uprawnienie {
    
    public Uprawnienie(String id, String regon, String nip, String uprawnienie){
        this.identyfikatorWpisu = id;
        this.regon = regon;
        this.nip = nip;
        this.uprawnienie = uprawnienie.replace("|", " ");
    }
    
    @Override
    public String toString(){
        return  Parser.cleanString(this.identyfikatorWpisu + "|" +
                this.nip + "|" +
                this.regon + "|" +
                this.uprawnienie + "|");
 
    }

    private String identyfikatorWpisu = "";
    private String regon = "";
    private String nip = "";
    private String uprawnienie = "";
}
