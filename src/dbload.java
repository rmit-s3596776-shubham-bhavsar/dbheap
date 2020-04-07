import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class dbload {
	
	//heap : declared as array of pages 
	static ArrayList<Page> heap = new ArrayList<Page>();
	//pageSize given as input
	static int pageSize;
	//total number of pages
	static int noOfPages = 0;
	//keep track of number of building records
	static int noOfBuildings = 0;
	//to check the read&write times
	static long startTime = 0;
	static long endTime = 0;
	
	public static void main(String[] args) {
		
		startTime = System.currentTimeMillis();
		
		//validate input arguements
		if(args.length >= 3 ){
			if(args[0].equals("-p")){
				//get dataset file name from input
				File f = new File(args[2]);
				try{
					//get the pagesize from input
					pageSize = Integer.parseInt(args[1]);
				} catch(NumberFormatException e){
					pageSize = 4096;
					System.out.println("Invalid page size given. Set to default value of 4096");
				}
				
				try{
					
					System.out.println("Reading input file");
					System.out.println(pageSize);
					readLines(f);
				}
				catch(FileNotFoundException e){
					System.out.println(e.getMessage());
					System.out.println("File not found");
					System.exit(0);
				}
				catch(IOException e){
					e.printStackTrace();
				}
				System.out.println("Read complete");
				System.out.println("Writing to heap file");
				writeLines();
				System.out.println("Write complete");
				endTime = System.currentTimeMillis();
				stdout();

			}
		}
		else{
			System.out.println("Invalid arguement format, Use : java dbload -p [pagesize] [file]");
		}
		
	}
	
	//Reading dataset
	public static void readLines(File f) throws IOException{
		Building building = null;
		Page page = new Page(pageSize);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line;
		br.readLine();
		
		while((line = br.readLine()) != null){
		
			//split columns using delimiter
			String[] values = line.split(",",-1);
			
			//Inserting values into object
			building = new Building(Integer.parseInt(values[0]),Integer.parseInt(values[1]),
					Integer.parseInt(values[2]),Integer.parseInt(values[3]),Integer.parseInt(values[9]),values[6],values[10]);
		

			if(!values[4].isEmpty()) {
				building.setBuildingName(values[4]);
			}
			if(!values[5].isEmpty()) {
				building.setStreetAddr(values[5]);
			}
		
			if(!values[7].isEmpty()) {
				int constructYear = Math.round(Float.parseFloat(values[7]));
				building.setConstructYear(constructYear);
			}
			if(!values[8].isEmpty()) {
				int refurbishYear = Math.round(Float.parseFloat(values[8]));
				building.setRefurbishYear(refurbishYear);
			}
			if(!values[11].isEmpty()) {
				building.setAccessType(values[11]);
			}
			if(!values[12].isEmpty()) {
				building.setAccessDesc(values[12]);
			}
			if(!values[13].isEmpty()) {
				
				building.setAccessRating(Math.round(Float.parseFloat(values[13])));
			}
			if(!values[14].isEmpty()) {	
				building.setHasBicycle(Math.round(Float.parseFloat(values[14])));
			}
			if(!values[15].isEmpty()) {
				building.setHasShower(Boolean.parseBoolean(values[15]));
			}
			if(!values[16].isEmpty()) {
				building.setxCoord(Double.parseDouble(values[16]));
			}
			
			if(!values[17].isEmpty()) {
				building.setyCoord(Double.parseDouble(values[17]));
			}
			if(!values[18].isEmpty()) {
				building.setLocation(values[18]);
			}
			
			//check if size of all records less than pagesize
			if(building.byteAllocation()+page.getPageSize() < pageSize){
				page.addBuildings(building);
				noOfBuildings++;

			//add new page if page is full	
			}else{
				heap.add(page);
				page = new Page(pageSize);
				noOfPages++;
				page.addBuildings(building);
				noOfBuildings++;
			}
		}
		heap.add(page);
		br.close();
		fr.close();
		
	}
	
	//Write into heap file
	public static void writeLines(){
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try{
			//concatenate pagesize with "heap" to create file name
			fos = new FileOutputStream("heap."+pageSize);
			oos = new ObjectOutputStream(fos);
			//for each page in heap, write into heapfile
			for(Page p:heap){
				if(p != null){
					oos.writeObject(p);
				}
			}
			//close both streams
			fos.close();
			oos.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//function to display record details
	public static void stdout(){
		System.out.println("Number of Buildings: " + noOfBuildings);
		System.out.println("Number of Pages: " + noOfPages);
		System.out.println("Total Time " + (endTime - startTime) +"ms");
	}

}
