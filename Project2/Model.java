import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Model {
	

	public void ProblemChoosing(int num) {
		switch(num){
			case 1:
				p1();
				break;
			case 2:
				p2();
				break;
			case 3:
				p3();
				break;
			default:
				System.out.println("Please enter 1,2,3 to choose the problem.");		
		}
	}
//Part 2
	//Problem 1
	public void p1() {
		System.out.println("Problem 1: Show that {P,P->Q} |= Q");
		//Knowledge base
		ArrayList<ArrayList<Integer>> kb = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> symbols = new ArrayList<Integer>();
		int Q = 2;
		//Eliminate ->: From P->Q to -P v Q
		kb.add(new ArrayList<Integer>(Arrays.asList(1)));
		kb.add(new ArrayList<Integer>(Arrays.asList(-1, 2)));
		symbols.add(1);
		symbols.add(2);
		boolean answer = TtEntails(new ArrayList<Integer>(Arrays.asList(Q)),symbols, kb);
		System.out.println("The answer is: "+ answer);

	}
	
	//Problem 2
	//Wumpus World Problem
	public void p2() {
		//Knowledge base for Wumpus World
		System.out.println("Problem 2: Solve Wumpus World Example in textbook");
		ArrayList<ArrayList<Integer>> kb = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> symbols = new ArrayList<Integer>();
		//P1,1 = 1; B1,1 = 2; P1,2 = 3; P2,1 = 4; B2,1 = 5; P2,2 = 6; P3,1 = 7; B1,2 = 8; P1,3 = 9
		//For R1
		kb.add(new ArrayList<Integer>(Arrays.asList(-1)));

		//For R2
		//After Eliminate <->
		kb.add(new ArrayList<Integer>(Arrays.asList(-2,3,4)));
		kb.add(new ArrayList<Integer>(Arrays.asList(2,-3)));
		kb.add(new ArrayList<Integer>(Arrays.asList(2,-4)));
		//For R3
		//After Eliminate <->
		kb.add(new ArrayList<Integer>(Arrays.asList(-5,1,6,7)));
		kb.add(new ArrayList<Integer>(Arrays.asList(5,-1)));
		kb.add(new ArrayList<Integer>(Arrays.asList(5,-6)));
		kb.add(new ArrayList<Integer>(Arrays.asList(5,-7)));
		//For R7
		//After Eliminate
		kb.add(new ArrayList<Integer>(Arrays.asList(-8,1,6,9)));
		kb.add(new ArrayList<Integer>(Arrays.asList(8,-1)));
		kb.add(new ArrayList<Integer>(Arrays.asList(8,-6)));
		kb.add(new ArrayList<Integer>(Arrays.asList(8,-9)));
		for(int i = 1;i<10;i++) {
			symbols.add(i);
		}
		System.out.println();
		System.out.println("Background knowledge:");
		System.out.println();
		printer(kb);
		System.out.println();
		
		//For R4: ¬B1,1 The agent starts at [1, 1] and Add perception
		System.out.println("Add perception R4");
		kb.add(new ArrayList<Integer>(Arrays.asList(-2)));
		//Show that this knowledge base entails ¬P1,2 and ¬P2,1, but not P2,2 or ¬P2,2
		//check the result of TT-ENTAILS
		boolean negp12 = TtEntails(new ArrayList<Integer>(Arrays.asList(-3)),symbols, kb);
		boolean negp21 = TtEntails(new ArrayList<Integer>(Arrays.asList(-4)),symbols, kb);
		boolean p22 = TtEntails(new ArrayList<Integer>(Arrays.asList(6)),symbols, kb);
		boolean negp22 = TtEntails(new ArrayList<Integer>(Arrays.asList(-6)),symbols, kb);
		
		//Print out the result
		System.out.println(" ¬P1,2(not P1,2) is known from the knowledgebase with TT-ENTAILS: "+negp12);
		System.out.println(" ¬P2,1(not P2,1) is known from the knowledgebase with TT-ENTAILS: "+negp21);
		System.out.println(" P2,2(P2,2) is known from the knowledgebase with TT-ENTAILS: "+p22);
		System.out.println(" ¬P2,2(not P2,2) is known from the knowledgebase with TT-ENTAILS: "+negp22);
		System.out.println("This knowledge base entails ¬P1,2(not P1,2) and ¬P2,1(not P2,1), but not P2,2 or ¬P2,2(not P2,2). The agent doesn’t know enough to conclude anything about P2,2.");

		
		//For R5: B2,1 The agent moves to [2, 1] and Add perception
		System.out.println();
		System.out.println("Add perception R5");
		kb.add(new ArrayList<Integer>(Arrays.asList(5)));
		//Show that this knowledge base entails P2,2 ∨ P3,1, but not P2,2, ¬P2,2, P3,1, or ¬P3,1. The agent knows more, but not enough.
		//check the result of TT-ENTAILS
		boolean p22vp31 = TtEntails(new ArrayList<Integer>(Arrays.asList(6,7)),symbols, kb);
		p22 = TtEntails(new ArrayList<Integer>(Arrays.asList(6)),symbols, kb);
		negp22 = TtEntails(new ArrayList<Integer>(Arrays.asList(-6)),symbols, kb);
		boolean p31 = TtEntails(new ArrayList<Integer>(Arrays.asList(7)),symbols, kb);
		boolean negp31 = TtEntails(new ArrayList<Integer>(Arrays.asList(-7)),symbols, kb);
		
		//Print out the result whether can be known from kb with TT-ENTAILS
		System.out.println(" P2,2 ∨ P3,1 is known from the knowledgebase with TT-ENTAILS: "+p22vp31);
		System.out.println(" P2,2 is known from the knowledgebase with TT-ENTAILS: "+p22);
		System.out.println("  ¬P2,2(not P2,2) is known from the knowledgebase with TT-ENTAILS: "+negp22);		
		System.out.println(" P3,1 is known from the knowledgebase with TT-ENTAILS: "+p31);
		System.out.println(" ¬P3,1(not P3,1) is known from the knowledgebase with TT-ENTAILS: "+negp31);
		System.out.println("This knowledge base entails P2,2 ∨ P3,1, but not P2,2, ¬P2,2(not P2,2), P3,1, or ¬P3,1(not P3,1). The agent knows more, but not enough.");

		
		//For R6: ¬B1,2 The agent moves to [1, 2] and Add perception:
		System.out.println();
		System.out.println("Add perception R6");
		kb.add(new ArrayList<Integer>(Arrays.asList(-8)));
		//Show that this knowledge base entails ¬P2,2 and P3,1.
		//check the result of TT-ENTAILS
		negp22 = TtEntails(new ArrayList<Integer>(Arrays.asList(-6)),symbols, kb);
		p31 = TtEntails(new ArrayList<Integer>(Arrays.asList(7)),symbols, kb);
		
		//Print out result
		System.out.println(" ¬P2,2(not P2,2) is known from the knowledgebase with TT-ENTAILS: "+negp22);
		System.out.println(" P3,1  is known from the knowledgebase with TT-ENTAILS: "+p31);
		System.out.println("This knowledge base entails ¬P2,2(not P2,2) and P3,1.");
	}
	
	//Problem 3
	public void p3() {
		System.out.println("Problem 3: If the Unicorn is mythical, magical or horned");
		ArrayList<ArrayList<Integer>> kb = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> symbols = new ArrayList<Integer>();
		//background knowledge: mythical =1; immortal=2; mammal =3; horned= 4; magical =5
		
		//If the unicorn is mythical, then it is immortal
		kb.add(new ArrayList<Integer>(Arrays.asList(-1,2))); 
		
		// If it is not mythical, then it is a mortal mammal
		kb.add(new ArrayList<Integer>(Arrays.asList(1,-2))); 
		kb.add(new ArrayList<Integer>(Arrays.asList(1,3))); 
		
		//If the unicorn is either immortal or a mammal, then it is horned
        kb.add(new ArrayList<Integer>(Arrays.asList(-2, 4)));
        kb.add(new ArrayList<Integer>(Arrays.asList(-3, 4)));
        
        //The unicorn is magical if it is horned
        kb.add(new ArrayList<Integer>(Arrays.asList(-4, 5)));
        for(int i = 1; i < 6; i++){
            symbols.add(i);
        }
        System.out.println();
        System.out.println("Knowledge base in the form of cnf");
        System.out.println();
        printer(kb);
        System.out.println();
        
        
        //can we prove that a unicorn is mythical?
        boolean mythical = TtEntails(new ArrayList<Integer>(Arrays.asList(1)),symbols, kb);
		System.out.println("Result of proving the unicorn is mythical is: "+ mythical);
		System.out.println("No sufficient information about mythical unicorn.");
		
		//can we prove that a unicorn is magical?
		boolean magical = TtEntails(new ArrayList<Integer>(Arrays.asList(5)),symbols, kb);
		System.out.println("Result of proving the unicorn is magical is: "+ magical);
		
		//can we prove that a unicorn is horned?
		boolean horned = TtEntails(new ArrayList<Integer>(Arrays.asList(4)),symbols, kb);
		System.out.println("Result of proving the unicorn is horned is: "+ horned);
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
//		Boolean result = Boolean.TRUE;
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
//			return (checkAll(kb, alpha, rest, Uni(model, P, Boolean.TRUE)) && checkAll(kb, alpha, rest, Uni(model, P, Boolean.FALSE))) ; 
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
//					return Boolean.FALSE;
					return false;
				}else{ 
//					return Boolean.TRUE;
					return true;
				}
			}
		}
		result = model.get(a);
		return result;
	}
}