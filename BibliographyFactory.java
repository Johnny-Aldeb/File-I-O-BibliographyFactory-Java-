

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.lang.model.element.NestingKind;


class FileInvalidException extends Exception{
	
	public FileInvalidException() {
		super("Error: Input file cannot be parsed due to missing information (i.e. month={}, title={}, etc.)");
	}
	public FileInvalidException(String s) {
		
		super(s);
	}
	public String getMessage()
	{
		return super.getMessage();
	}

	
}

public class BibliographyFactory {
	
	static int NumberOfInvalidFiles=0;
	

	
	public static void processFilesForValidation(File f_IEEE, File f_ACN ,File f_NJ, Scanner s, PrintWriter pwIEEE, PrintWriter pwACM, PrintWriter pwNJ, int fileNumber){
		
		int HowManyAuthor=0;
		int HowManyTitle=0;
		int HowManyJournal=0;
		int HowManyVolume=0;
		int HowManyNumber=0;
		int HowManyPages = 0;
		int HowManyMonth = 0;
		int HowManyYear =0;
		int HowManyDoi =0;
		
		String author_IEEE[] = new String[30];
		String author_ACM[]=  new String[30];
		String author_NJ[] = new String[30];	
		String Title[] = new String[30];
		String journals[] = new String[30];
		String volumes[] = new String[30];
		String numbers[] = new String[30];	
		String pages[] = new String[30];
		String months[] = new String[30];
		String years[] = new String[30];
		String dois[] = new String[30];
		
	try {
		while(s.hasNextLine()) {
			String str = s.nextLine();
			
			StringTokenizer word = new StringTokenizer(str,"={,}");
			
			while(word.hasMoreTokens()) {
				String name = word.nextToken();
			
			
				if((name.equals("keywords") || name.equals("ISSN") )) {
					String x = word.nextToken();
				
					if(x.equals(" ")) {

					
					f_IEEE.delete();
					f_ACN.delete();
					f_NJ.delete();
					
					System.out.println("Error: Detected Empty Field!");
					System.out.println("============================\n");
					System.out.println("Problem detected with input file: Latex"+fileNumber+".bib");

					throw new FileInvalidException("File is Invalid: Field \""+name+"\" is Empty. Processing stoped at this point."
							+" Other empty fields may be present as well!");
					}
				}	

					
					
				if(name.equals("author")) {
					String author = word.nextToken();
					
					if(!word.hasMoreTokens()) {
						

						
						f_IEEE.delete();
						f_ACN.delete();
						f_NJ.delete();
						
						System.out.println("Error: Detected Empty Field!");
						System.out.println("============================\n");
						System.out.println("Problem detected with input file: Latex"+fileNumber+".bib");

						throw new FileInvalidException("File is Invalid: Field \""+name+"\" is Empty. Processing stoped at this point."
								+" Other empty fields may be present as well!");
					
					
				}
					
					String[] authorACM = author.split(" and");
					author_IEEE[HowManyAuthor] = author.replace(" and", ",");
					author_NJ[HowManyAuthor] = author.replace("and", "&");
					author_ACM[HowManyAuthor] = authorACM[0];
					HowManyAuthor++;
					break;
					
				}else if(name.equals("title")) {
					
					String title = word.nextToken();
					if(!word.hasMoreTokens()) {
						

						
						f_IEEE.delete();
						f_ACN.delete();
						f_NJ.delete();
						
						System.out.println("Error: Detected Empty Field!");
						System.out.println("============================\n");
						System.out.println("Problem detected with input file: Latex"+fileNumber+".bib");

						throw new FileInvalidException("File is Invalid: Field \""+name+"\" is Empty. Processing stoped at this point."
								+" Other empty fields may be present as well!");
					
					
				}
			
					Title[HowManyTitle] = title;
					HowManyTitle++;
					break;
				
				}else if(name.equals("journal")) {
					
					String journal = word.nextToken();
					if(!word.hasMoreTokens()) {

						
						f_IEEE.delete();
						f_ACN.delete();
						f_NJ.delete();
						
						System.out.println("Error: Detected Empty Field!");
						System.out.println("============================\n");
						System.out.println("Problem detected with input file: Latex"+fileNumber+".bib");

						throw new FileInvalidException("File is Invalid: Field \""+name+"\" is Empty. Processing stoped at this point."
								+" Other empty fields may be present as well!");
					
					
				}
				
					journals[HowManyJournal] = journal;
					HowManyJournal++;
					break;
			
				}else if(name.equals("volume")) {
					String volume = word.nextToken();
					
					if(volume.equals(" ")) {

						
						f_IEEE.delete();
						f_ACN.delete();
						f_NJ.delete();
						
						System.out.println("Error: Detected Empty Field!");
						System.out.println("============================\n");
						System.out.println("Problem detected with input file: Latex"+fileNumber+".bib");

						throw new FileInvalidException("File is Invalid: Field \""+name+"\" is Empty. Processing stoped at this point."
								+" Other empty fields may be present as well!");
					
					
				}
					
					volumes[HowManyVolume] = volume;
					HowManyVolume++;
					break;
			
				}else if(name.equals("number")) {
					
					String number = word.nextToken();
					
					if(number.equals(" ")) {
						
						f_IEEE.delete();
						f_ACN.delete();
						f_NJ.delete();
						
						System.out.println("Error: Detected Empty Field!");
						System.out.println("============================\n");
						System.out.println("Problem detected with input file: Latex"+fileNumber+".bib");

						throw new FileInvalidException("File is Invalid: Field \""+name+"\" is Empty. Processing stoped at this point."
								+" Other empty fields may be present as well!");
					
					
				}
				
						
						numbers[HowManyNumber] = number;
						HowManyNumber++;
						
					
					

				}else if(name.equals("pages")) {
					String page = word.nextToken();
					
					if(page.equals(" ")) {

						
						f_IEEE.delete();
						f_ACN.delete();
						f_NJ.delete();
						
						System.out.println("Error: Detected Empty Field!");
						System.out.println("============================\n");
						System.out.println("Problem detected with input file: Latex"+fileNumber+".bib");

						throw new FileInvalidException("File is Invalid: Field \""+name+"\" is Empty. Processing stoped at this point."
								+" Other empty fields may be present as well!");
					
					
				}
					
					
					pages[HowManyPages] = page;
					HowManyPages++;
					break;
			
				
				}
				else if(name.equals("month")) {
						
					
					if(!word.hasMoreTokens()) {

							
							f_IEEE.delete();
							f_ACN.delete();
							f_NJ.delete();
							
							System.out.println("Error: Detected Empty Field!");
							System.out.println("============================\n");
							System.out.println("Problem detected with input file: Latex"+fileNumber+".bib");

							throw new FileInvalidException("File is Invalid: Field \""+name+"\" is Empty. Processing stoped at this point."
									+" Other empty fields may be present as well!");
						
						
							
					}
					String month = word.nextToken();
				
					months[HowManyMonth] = month;
					HowManyMonth++;
					break;
			
			
					
					
				}
				else if(name.equals("year")) {
					
					String year = word.nextToken();
					if(year.equals(" ")) {

						
						f_IEEE.delete();
						f_ACN.delete();
						f_NJ.delete();
						
						System.out.println("Error: Detected Empty Field!");
						System.out.println("============================\n");
						System.out.println("Problem detected with input file: Latex"+fileNumber+".bib");

						throw new FileInvalidException("File is Invalid: Field \""+name+"\" is Empty. Processing stoped at this point."
								+" Other empty fields may be present as well!");
					
					
				}
					
                    years[HowManyYear] = year;
					HowManyYear++;
					break;
				
				}else if(name.equals("doi")) {
					
					String doi = word.nextToken();
					if(!word.hasMoreTokens()) {

						f_IEEE.delete();
						f_ACN.delete();
						f_NJ.delete();
						
						System.out.println("Error: Detected Empty Field!");
						System.out.println("============================\n");
						System.out.println("Problem detected with input file: Latex"+fileNumber+".bib");

						throw new FileInvalidException("File is Invalid: Field \""+name+"\" is Empty. Processing stoped at this point."
								+" Other empty fields may be present as well!");
					
					
				}
					
					dois[HowManyDoi] = doi;
					HowManyDoi++;
					break;

					
				}
				
			
		}
	

}
		
		}catch(FileInvalidException e){
			NumberOfInvalidFiles++;
			String s1 = e.getMessage();
			System.out.println(s1+"\n\n");
			
			
		}
		
		try {
			for(int i=0; i<HowManyAuthor; i++) {

				
				pwIEEE.println(author_IEEE[i]+". \""+Title[i]+"\", "+journals[i]+", vol. "
	                       +volumes[i]+", no. "+numbers[i]+", p. "+pages[i]+", "+
				             months[i]+" "+years[i]+".\n");
				
				pwACM.println("["+(i+1)+"] "+author_ACM[i]+" et la. "+years[i]+". "+ Title[i]+". "+journals[i]+". "+volumes[i]+", "
                       +numbers[i]+" ("+years[i]+"), "+pages[i]+". DOI:https://doi.org/"+dois[i]+".\n");
				
				pwNJ.println(author_NJ[i]+". "+Title[i]+". "+journals[i]+". "+volumes[i]+", "+pages[i]+"("+years[i]+").\n");
				
				
			}
			pwIEEE.close();
			pwACM.close();
			pwNJ.close();
			}catch(NullPointerException e){
				
			}
	}


	public static void main(String[] args) {
		
		System.out.println("Welcome to the BibliographyFactory!\n\n");
		
		
		Scanner[] sc = new Scanner[11];
		File[] fileIEEE = new File[11];
		File[] fileACM = new File[11];
		File[] fileNJ = new File[11];

		int i =0;
		

		try
		{ 
			for (i=0; i<10; i++ ) {
				sc[i] = new Scanner(new FileInputStream("Comp249_F22_Assg3-Part1_Needed-Files/Latex"+(i+1)+".bib"));
				
			}
			
			PrintWriter[] pwIEEE = new PrintWriter[10];
			PrintWriter[] pwACM = new PrintWriter[10];
			PrintWriter[] pwNJ = new PrintWriter[10];
		
			for (int j=0; j<10; j++) {
				
				fileIEEE[j] = new File("IEEE"+(j+1)+".json");
				fileACM[j] = new File("ACM"+(j+1)+".json");
				fileNJ[j] = new File("NJ"+(j+1)+".json");
				
			
				pwIEEE[j] = new PrintWriter(new FileOutputStream(fileIEEE[j]));
				pwACM[j] = new PrintWriter(new FileOutputStream(fileACM[j]));
				pwNJ[j] = new PrintWriter(new FileOutputStream(fileNJ[j]));
				
				
				
				processFilesForValidation(fileIEEE[j], fileACM[j], fileNJ[j] ,sc[j],pwIEEE[j], pwACM[j], pwNJ[j], (j+1));
	
				
			}
			
			System.out.println("A total of "+NumberOfInvalidFiles+" files were invalid, and could not be processed. All other "
			                    +(10-NumberOfInvalidFiles)+" \"Valid\" files have been created. ");
			for(int j=0; j<10; j++) {
				sc[j].close();
			}
	
			
							     
		}
		catch(FileNotFoundException e) 
		{							   
			System.out.println("Could not open input file Latex"+(i+1)+".bib for reading.");	
			System.out.print("Please check if file exists! Program will terminate after closing any opened files");
			for(int j=0; j<i; j++) {
				sc[j].close();
			}
			System.exit(0);			   
		}
		//-----------------------------------------
		
		
		
		Scanner s = new Scanner(System.in);
		BufferedReader br = null;
		System.out.println("\n");
		System.out.print("Please enter the name of one of the files that you need to review:");
		String fileName = s.next();
		
		
		try
		{
			
			br = new BufferedReader(new FileReader(fileName));
			System.out.println("\n");
			String str;
			
			str = br.readLine();
			while(str != null)
			{
				System.out.println(str);
				str = br.readLine();		
				
			}
			System.out.println("\n");
			System.out.println("Goodbye! Hope you have enjoyed creating the needed files using BibilographyFactory.");

			br.close();

			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Could not open file. Files does not exist; possibly it could not be created\n");
			System.out.println("However, you will be allowed another chance to enter another file name.");
			try
			{
				System.out.print("Please enter the name of one of the files that you need to review:");
				String fileName2 = s.next();
				System.out.println("\n");
				br = new BufferedReader(new FileReader(fileName2));
				String str2;
				
				str2 = br.readLine();
				while(str2 != null)
				{
					System.out.println(str2);
					str2 = br.readLine();		
					
				}
				System.out.println("\n");
				System.out.println("Goodbye! Hope you have enjoyed creating the needed files using BibilographyFactory.");

				br.close();
			}
			catch(FileNotFoundException e2) 
			{
				System.out.println("Could not open input file again! Either file does not exist or could not be created.");
				System.out.println("Sorry! I am unable to display your desired files! Program will exit!");
				System.exit(0);
				
			} catch (IOException e1) {
				System.out.println("Error: An error has occurred while reading from the " + fileName + " file. ");
				System.out.println("Program will terminate.");
				System.exit(0);
			}
			

		} catch (IOException e) {
			System.out.println("Error: An error has occurred while reading from the " + fileName + " file. ");
			System.out.println("Program will terminate.");
			System.exit(0);

		
		}
		

	}

}
