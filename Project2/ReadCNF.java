import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ReadCNF {
	//Read .cnf file(Conjunctive Normal Form)
	//Part1
	public ReadCNF() {	
	}
    public static ArrayList<ArrayList<Integer>> CreateClause(File fileName) throws IOException {
        System.out.println(fileName.getName());
//        FileInputStream.open(fileName)
    	Scanner s = new Scanner(fileName);
        ArrayList<Integer> list = new ArrayList<Integer>();
        //read the file until the end
        while(s.hasNextLine()){
//    	BufferedReader br = null;
//    	try {
//    		String line;
//    		br = new BufferedReader(new FileReader(fileName));
//    		while((line=br.readLine())!=null) {
//    			if(line.charAt(0) == 'p'){
//                    while(line.hasNext()){
//                        if(line.hasNextInt()){
//                            list.add(line.nextInt());
//                        }else{
//                            line.next();
//                        }  
//                    }
//                    break;
//                }
//    		}
//    	}
//    	while(i=br.read()!=-1) {
            String line = s.nextLine();
            if(line.charAt(0) == 'p'){
                while(s.hasNext()){
                    if(s.hasNextInt()){
                        list.add(s.nextInt());
                    }else{
                        s.next();
                    }  
                }
                break;
            }
        }
        
        ArrayList<ArrayList<Integer>> list2 = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int i = 0;
        while(i<list.size()-1) {
        	if(list.get(i) != 0){
                temp.add(list.get(i));
                i++;
            }
            else if(i == list.size() - 1){
                list2.add(temp);
                i++;
            }
            else{
                list2.add(temp);
                temp = new ArrayList<Integer>();
                i++;
            }
        }

        list2.add(temp);
        return list2;
    }

}
