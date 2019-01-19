package parser;

public class Uprawnienie {
    
    public Uprawnienie(String id, String regon, String nip, String uprawnienie){
        this.identyfikatorWpisu = id;
        this.regon = regon;
        this.nip = nip;
        this.uprawnienie = uprawnienie.replace("|", " ");
    }
    
    /*
    public void setIdentyfikator(String identyfikator){
        this.identyfikatorWpisu = identyfikator;
    }
    
    public void setRegon(String regon){
        this.regon = regon;
    }
    
    public void setNip(String nip){
        this.nip = nip;
    }
    
    public void setUprawnienie(String uprawnienie){
        this.uprawnienie = uprawnienie;
    }*/
    
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
