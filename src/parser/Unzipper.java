package parser;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzipper {
	
	/**
     * Rozpakowuję pliki w folderu wejściowego do folderu docelowego
     * 
     * @param folderZip
     * @param folderWyjsciowy
     * @throws java.io.IOException
     */
    
    public void unzipList(DefaultListModel<File> zipFiles, File folderWyjsciowy) throws IOException {


    	File[] listaPlikowZip;// = folderZip.listFiles(Parser.FILTR_ZIP);
        
        // czyszcze folder wyjsciowy z plikow xml jeśli zawiera pliki
        if(folderWyjsciowy.listFiles(Parser.FILTR_XML) != null){
            // tworze liste plikow xml, ktore moga byc w folderze wyjsciowym, pobieram filtr z klasy Parser
            File[] listaPlikowXML = folderWyjsciowy.listFiles(Parser.FILTR_XML);
            for(File plik: listaPlikowXML) {
				Files.deleteIfExists(plik.toPath());
			}
        }
        
    	for(int i = 0; i < zipFiles.getSize(); i++) {
    		File plik = zipFiles.get(i);
            System.out.println("rozpakowuję: " + plik.getAbsolutePath());
            unzipIt(plik, folderWyjsciowy);
    	}
    	
    	System.out.println("Koniec rozpakowywania");
    }
    
    /**
     * Rozpakowuję pojedynczy plik
     * 
     * @param plikZip
     * @param folderWyjsciowy
     * @throws java.io.IOException
     */
    
    public void unzipIt(File plikZip, File folderWyjsciowy)  throws IOException{
    	
    	String plikZipLokacja = plikZip.getAbsolutePath();
    	String folderWyjsciowyLokacja = folderWyjsciowy.getAbsolutePath();
    	
    	byte[] buffer = new byte[1024];
    	
		// utwórz folder jeśli nie istnieje
		if(!folderWyjsciowy.exists()) {
			folderWyjsciowy.mkdir();
		}
		
		// pobierz plik do zipowania
		ZipInputStream zipInS = new ZipInputStream(new FileInputStream(plikZipLokacja), Charset.forName("Cp775"));
		ZipEntry zipEntry = zipInS.getNextEntry();
		
		
		while(zipEntry != null) {
			
			String zipEntryNazwa = zipEntry.getName();

			File newFile = new File(folderWyjsciowyLokacja + File.separator + zipEntryNazwa);
			
			// if(newFile.exists())
			//	newFile.delete();
			
			System.out.println("rozpakowywany xml: " + newFile.getAbsoluteFile());
			
			// stworz nieistniejące foldery, nie powinno ich być w ceidg ale zabezpieczamy się
			new File(newFile.getParent()).mkdirs();
			
			FileOutputStream fOs = new FileOutputStream(newFile);
			
			int len;
			while((len = zipInS.read(buffer)) > 0) {
				fOs.write(buffer, 0, len);
			}
			
			fOs.close();
			
			zipEntry = zipInS.getNextEntry();
		}
		
		zipInS.closeEntry();
		zipInS.close();
    }
}
