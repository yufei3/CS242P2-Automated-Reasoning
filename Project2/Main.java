import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	static GSAT gsat = new GSAT();
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);	
		
		SatTest s = new SatTest();
		
		//Part2
		//Problems for Part are wrote in Model 
		System.out.println("PART 2--------------------------------------------- ");
		Model model = new Model();
		String quit = "y";
		while(quit.equals("y")) {
		System.out.println("Please enter 1,2,3 to choose the problem.");
		int chose = sc.nextInt();
		model.ProblemChoosing(chose);
		System.out.println();
		System.out.println("Do you want to keep running with other Problem in Part2? ");
		System.out.println("Enter 'n' if you want to stop running Part2. ");
		System.out.println("Enter 'y' if you want to keep running Part2.  ");
		quit = sc.next();
		}
			
		//Part3
		//Ask the user for MAX-TRIES and MAX-FLIPS
		int MaxTries;
		int MaxFlips;
		
		System.out.println("PART 3--------------------------------------------- ");
		//Problem 1
		ArrayList<ArrayList<Integer>> clauses = new ArrayList<ArrayList<Integer>>();
		clauses.clear();
		clauses.add(new ArrayList<Integer>(Arrays.asList(1,3,-4)));
		clauses.add(new ArrayList<Integer>(Arrays.asList(4)));
		clauses.add(new ArrayList<Integer>(Arrays.asList(2,-3)));
		System.out.println("Problem1: check (x1 ∨x3 ∨¬x4)∧(x4)∧(x2 ∨¬x3)");
		//Enter setting of MAX-TRIES and MAX-FLIPS
		System.out.println("Please enter the MAX-TRIES:");
		MaxTries = sc.nextInt();
		System.out.println("Please enter the MAX-FLIPS:");
		MaxFlips =sc.nextInt();
		boolean a = gsat.GSATAlgorithm(clauses,MaxFlips,MaxTries,4, true, true);
		System.out.println("The result of Problem 1: "+a);
		clauses.clear();
		System.out.println();
		
		//Problem2
		//Ask user to choose
		System.out.println("Problem2: N-Queens");
		System.out.println();
		System.out.println("Please enter number for N-Queens(4,8,12,16): ");
		int Nq = sc.nextInt();
		String current = System.getProperty("user.dir");
		String n;
		File f1,f2,f3,f4;
		if(Nq==4) {
			n = current + "/nqueens_4.cnf";
			f1 = new File(n);
			//Read file
			try {
				clauses = ReadCNF.CreateClause(f1);
//				System.out.print(f1.getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(Nq==8) {
			n = current + "/nqueens_8.cnf";
			f2= new File(n);
		//Read file
		try {
			clauses = ReadCNF.CreateClause(f2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		else if(Nq==12) {
			n = current + "/nqueens_12.cnf";
			f3= new File(n);
		//Read file
		try {
			clauses = ReadCNF.CreateClause(f3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}else if(Nq==16) {

			n = current + "/nqueens_16.cnf";
			f4= new File(n);
		//Read file
		try {
			clauses = ReadCNF.CreateClause(f4);
			System.out.print(f4.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		//Enter setting of MAX-TRIES and MAX-FLIPS
		System.out.println("Please enter the MAX-TRIES (e.g 100):");
		MaxTries = sc.nextInt();
		System.out.println("Please enter the MAX-FLIPS (e.g 50):");
		MaxFlips =sc.nextInt();
		a = gsat.GSATAlgorithm(clauses,MaxFlips,MaxTries,16, false, true);
		System.out.println("The result for N-Queens is: "+a);
		System.out.println();
		clauses.clear();
		
		//Problem3(1)
		System.out.println("Problem3(1): quinn.cnf ");
		File qui;
		String current2 = System.getProperty("user.dir");
		String n2;
//		System.out.println("Enter the number k from ");
		n2 = current + "/quinn.cnf";
		qui= new File(n2);
		System.out.print(qui.getName());
	//Read file
	try {
		clauses = ReadCNF.CreateClause(qui);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		//Enter setting of MAX-TRIES and MAX-FLIPS
		System.out.println("Please enter the MAX-TRIES (e.g 100):");
		MaxTries = sc.nextInt();
		System.out.println("Please enter the MAX-FLIPS (e.g 100):");
		MaxFlips =sc.nextInt();
		System.out.println("Tracing failed");
		a = gsat.GSATAlgorithm(clauses,MaxFlips,MaxTries,16, false, true);
		System.out.println("quinn.cnf: "+a);
		System.out.println();
		
		//Problem3(2)
		//Pigeon Hole Problem n pigeons m holes where n = m
		System.out.println("Problem3(2): Pigeonhole problems ");
		clauses.clear();
		System.out.println("Please enter a numbers for pigeons and holes(1-20): ");
		int m = sc.nextInt();
		String current3 = System.getProperty("user.dir");
		String n3;
		n3 = current + "/CSC242_Project_2_cnf/pigeonhole/pigeonhole_"+m+"_"+m+".cnf";
		File pig= new File(n3);
		
	//Read file
	try {
		clauses = ReadCNF.CreateClause(pig);
//		System.out.print(pig.getAbsolutePath());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		clauses = s.getKB_PigeonHole(m);
		//For the larger problems you should probably turn off the tracing, or at least make it optional
		System.out.println("Do you want to print out the tracing for pigeons hole?( Enter 'y' to print out, 'n' to not print out)");
		Boolean print = true;
		String p = sc.next();
		String p2 = p.toLowerCase();
		if(p.equals("y")) {
			 print = true;
		}else{
			print = false;
			}
		//Enter setting of MAX-TRIES and MAX-FLIPS
		System.out.println("Please enter the MAX-TRIES (e.g 1000):");
		MaxTries = sc.nextInt();
		System.out.println("Please enter the MAX-FLIPS (e.g 500):");
		MaxFlips =sc.nextInt();
		System.out.println("Test Pigeon Hole Problem with "+m+" pigeons "+ m+" holes");
		if(m>5) {
			System.out.println("Tracing failed");
			a = gsat.GSATAlgorithm(clauses,MaxFlips,MaxTries,m*m, false, print);
		}else {
			System.out.println("Tracing success");
			a = gsat.GSATAlgorithm(clauses,MaxFlips,MaxTries,m*m, true, print);
		}
		System.out.println("Answer for Problem Pigeon Hole Problem with "+m+" pigeons "+ m+" holes: "+a);
		System.out.println("If n > m then this would be unsatisfiable(as shown below) and if n < m then it would be easier to solve for satisfiability.\n");
		clauses.clear();		
		
		//Pigeonshole6
		System.out.println("hole6.cnf problem:");
		System.out.println();
		String current4 = System.getProperty("user.dir");
		String n4;
		n4 = current + "/CSC242_Project_2_cnf/pigeonhole/pigeonhole_6_6.cnf";
		File pig6= new File(n4);

	//Read file
	try {
		clauses = ReadCNF.CreateClause(pig6);
//		System.out.print(pig6.getAbsolutePath());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		//Enter setting of MAX-TRIES and MAX-FLIPS
		System.out.println("Please enter the MAX-TRIES (e.g 100):");
		MaxTries = sc.nextInt();
		System.out.println("Please enter the MAX-FLIPS (e.g 50):");
		MaxFlips =sc.nextInt();
		System.out.println("Test Pigeon Hole Problem with 6 pigeons 6 holes");
		System.out.println("Test hole6.cnf");
		System.out.println("Tracing Failed");
		a = gsat.GSATAlgorithm(clauses,MaxFlips,MaxTries,42, false, true);
		System.out.println("whether do printing for hole6.cnf: "+a);
		System.out.println("Unsatifiable since pigeon > holes");

	}
	//print knowledge base
	public void printer(ArrayList<ArrayList<Integer>> clause) {
		for(int i=1;i<clause.size()+1;i++) {
			ArrayList<Integer > cl= clause.get(i-1);
			System.out.print(i+": ");
			for(int j=0;j<cl.size();j++) {
				System.out.print(cl.get(j)+" ");
			}
			System.out.println();
		}
	} 
	//Use HashMap to represent model
		public boolean TtEntails(ArrayList<Integer> alpha, ArrayList<Integer> symbols, ArrayList<ArrayList<Integer>> kb){
			HashMap<Integer,Boolean> model = new HashMap<Integer,Boolean>();
			return checkAll(kb, alpha, symbols, model);
		}
		
		public Boolean PlTrue(ArrayList<Integer> alpha, HashMap<Integer,Boolean> model){
			return Clause(alpha, model);
		}

		public Boolean KbPlTrue(ArrayList<ArrayList<Integer>> sentence,HashMap<Integer,Boolean> model){
//			Boolean result = Boolean.TRUE;
			Boolean result = true;
			for(int i = 0; i < sentence.size(); i++){
				ArrayList<Integer> clause = sentence.get(i);
				result = result && Clause(clause,model);
			}
			return result;
		}


		public boolean checkAll(ArrayList<ArrayList<Integer>> kb, ArrayList<Integer> alpha, ArrayList<Integer> symbols, HashMap<Integer,Boolean> model){
			if(symbols.isEmpty()){
				boolean pl = KbPlTrue(kb,model); 
				if(pl){
					return PlTrue(alpha, model);
				}else{
					return true;
				}
			}else{ 
				Integer P = symbols.get(0);
				ArrayList<Integer> rest = new ArrayList<Integer>(symbols.subList(1, symbols.size()));
//				return (checkAll(kb, alpha, rest, Uni(model, P, Boolean.TRUE)) && checkAll(kb, alpha, rest, Uni(model, P, Boolean.FALSE))) ; 
				return (checkAll(kb, alpha, rest, Uni(model, P, true)) && checkAll(kb, alpha, rest, Uni(model, P, false))) ; 
			}
		}
		
		//Union
		public HashMap<Integer,Boolean> Uni (HashMap<Integer,Boolean> model, Integer key, Boolean b){
			HashMap<Integer,Boolean> HM = new HashMap<Integer,Boolean>();
			model.put(key,b);
			HM.putAll(model);
			return HM;
		}
		
		//For Clause
		private Boolean Clause(ArrayList<Integer> clause, HashMap<Integer, Boolean> model){
			Boolean result = Boolean.FALSE;
			for(int i = 0; i < clause.size(); i++){
				int j= clause.get(i);
				Boolean b = model.get(Math.abs(j));
				if(b != null){
					boolean m = Evaluation(clause.get(i), model);
					result = result || m; 
				}
			}
			return result;
		}

		//evaluate the assignment of integer
		private Boolean Evaluation(Integer a, HashMap<Integer, Boolean> model){
			Boolean result = null; 
			if(a < 0){
				Boolean assignment = model.get(Math.abs(a));
				if(assignment != null){
					if(assignment.booleanValue() == true){
//						return Boolean.FALSE;
						return false;
					}else{ 
//						return Boolean.TRUE;
						return true;
					}
				}
			}
			result = model.get(a);
			return result;
		}
}

