package parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;

public class MyExceptionHandler{
	
	public MyExceptionHandler(Exception ex) {
		File plikLogowania = new File("log.txt");
		
		try {
			if(plikLogowania.exists()) {
				FileWriter log = new FileWriter(plikLogowania, true);
				log.append(new GregorianCalendar().getTime() + Parser.lineSeparator
						+ ex.getMessage() + Parser.lineSeparator
						+ "---------------------------------" + Parser.lineSeparator);
				log.close();
				System.out.println(Parser.lineSeparator + new GregorianCalendar().getTime() + Parser.lineSeparator
						+ ex.getMessage() + Parser.lineSeparator);
			}else {
				FileWriter log = new FileWriter(plikLogowania);
				log.write(new GregorianCalendar().getTime() + Parser.lineSeparator
						+ ex.getMessage() + Parser.lineSeparator
						+ "---------------------------------" + Parser.lineSeparator);
				log.close();
				System.out.println(Parser.lineSeparator + new GregorianCalendar().getTime() + Parser.lineSeparator
						+ ex.getMessage() + Parser.lineSeparator);
			}
			
			System.out.println("Błąd, przerwano - zapis błędu w pliku log.txt");
			
		} catch(IOException ee) {
                        System.out.println("Błąd dostępu do pliku logowania" + Parser.lineSeparator + ee.getMessage());
		} 

	}

}
