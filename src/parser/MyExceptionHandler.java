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
				log.append(new GregorianCalendar().getTime() + System.getProperty("line.separator")
						+ ex.getMessage() + System.getProperty("line.separator")
						+ "---------------------------------" + System.getProperty("line.separator"));
				log.close();
				System.out.println(System.getProperty("line.separator") + new GregorianCalendar().getTime() + System.getProperty("line.separator")
						+ ex.getMessage() + System.getProperty("line.separator"));
			}else {
				FileWriter log = new FileWriter(plikLogowania);
				log.write(new GregorianCalendar().getTime() + System.getProperty("line.separator")
						+ ex.getMessage() + System.getProperty("line.separator")
						+ "---------------------------------" + System.getProperty("line.separator"));
				log.close();
				System.out.println(System.getProperty("line.separator") + new GregorianCalendar().getTime() + System.getProperty("line.separator")
						+ ex.getMessage() + System.getProperty("line.separator"));
			}
			
			System.out.println("Błąd, przerwano - zapis błędu w pliku log.txt");
			
		} catch(IOException ee) {
                        System.out.println("Błąd dostępu do pliku logowania" + System.getProperty("line.separator") + ee.getMessage());
		} 

	}

}
