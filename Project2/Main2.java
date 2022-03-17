import java.util.*;
import java.io.*;
public class Main2 {
	
	static String []config=new String[2]; 
	static int depth=0;
	static boolean tracker=true;
	public static void main(String[] args) throws IOException {
	
		
		//-----------------------part1---------------------//
		if(args.length>0) {
			File inf =new File (args[0]); // use first argument as file address of part 1 for testing
			
			ArrayList<ArrayList<String >> clauses= new ArrayList<ArrayList<String>>();
			clauses=cnfReader(inf);
			printer(clauses);
		}
		
		
		//_____________________part2_________________________//
		Scanner sc=new Scanner (System.in);
		
		
		System.out.println("Enter a number for the question that you want to test(1,2,3): ");
		Scanner s2=new Scanner (System.in);
		int input= s2.nextInt();
		while(true) {
			if(input==1) {
				File inf1=new File ("1p2.cnf");
				ArrayList<ArrayList<String>> kb1=new ArrayList<ArrayList<String>>();
				kb1=cnfReader(inf1);
				System.out.println("Now, cnf form of knowledge base of the first question is : ");
				printer(kb1);
				System.out.println("The statement that the knowledgebase entails Q is :");
				int numP=Integer.parseInt(config[0]);
				boolean resl=ttEntail(kb1,stringToCnf("2"),numP);
				System.out.println(resl+"\n");
				
				
				
			}
			else if (input == 2) {
				File inf2=new File("2p2.cnf");
				ArrayList<ArrayList<String>> kb2= new ArrayList<ArrayList<String>>();
				kb2=cnfReader(inf2);
				System.out.println("Now, the cnf form of the knowledge base of the second question is: ");
				printer(kb2);
				System.out.println("------------------------------------------------------");
				System.out.println("The agent perceive no breeze at [1,1], so add !b_1,1 to kb2\n");
				addK("-2",kb2);
				System.out.println("The statement that kb2 entails !p_1,2 and !p_2,1 is ");
				int nump=Integer.parseInt(config[0]);
				//ArrayList<ArrayList<String> > test= new ArrayList<ArrayList<String> >();
				//test=stringToCnf("-3 0 -4");
				//printer(test);
				boolean resl= ttEntail(kb2,stringToCnf("-3 0 -4"),nump);
				System.out.println(resl+"\n");
				System.out.println("The statement that kb2 entails p_2,2 is ");
				resl=ttEntail(kb2, stringToCnf("7"),nump);
				System.out.println(resl+"\n");
				System.out.println("The statement that kb2 entails !p_2,2 is ");
				resl=ttEntail(kb2, stringToCnf("-7"),nump);
				System.out.println(resl+"\n");
				System.out.println("---------------------------------------------------------");
				System.out.println("The agent moves to [2,1] and perceive breeze at it, so we add b_21 to its kb\n");
				
				addK("5",kb2);
				System.out.println("The statement that kb2 entails \"p_2,2 or p_3,1\" is ");
				resl=ttEntail(kb2, stringToCnf("7 8"),nump);
				System.out.println(resl+"\n");
				System.out.println("The statement that kb2 entails p_2,2 is ");
				resl=ttEntail(kb2, stringToCnf("7"),nump);
				System.out.println(resl+"\n");
				System.out.println("The statement that kb2 entails !p_2,2 is ");
				resl=ttEntail(kb2, stringToCnf("-7"),nump);
				System.out.println(resl+"\n");
				System.out.println("The statement that kb2 entails p_3,1 is ");
				resl=ttEntail(kb2, stringToCnf("8"),nump);
				System.out.println(resl+"\n");
				System.out.println("The statement that kb2 entails !p_3,1 is ");
				resl=ttEntail(kb2, stringToCnf("-8"),nump);
				System.out.println(resl+"\n");
				System.out.println("------------------------------------------------------");
				System.out.println("The agent moves to [1,2] and perceive no breeze, so we add !b_1,2 to it is kb\n");
				addK("-6",kb2);
				System.out.println("The statement that kb2 entails !p_2,2 is ");
				resl=ttEntail(kb2, stringToCnf("-7"),nump);
				System.out.println(resl+"\n");
				System.out.println("The statement that kb2 entails p_3,1 is ");
				resl=ttEntail(kb2, stringToCnf("8"),nump);
				System.out.println(resl+"\n");
				System.out.println("----------------------------end------------------------");
				
				Scanner future= new Scanner(System.in);
				while(true) {
					System.out.println("Do you want to continue?(y/n)");
					String ans1=future.next();
					if(ans1.equals("y")) {
						System.out.println("Do you want to add more knowledege?(y/n/q enter q to exit)");
						String ans2=future.next();
						if(ans2.equals("y")) {
							Scanner future3 =new Scanner (System.in);
							System.out.println("please enter a string in cnf form useing integers to represent variable(like 4 0 -1 2 0 3)");
							System.out.println("1. p_1,1; 2. b_1,1; 3. p_1,2; 4. p_2,1; 5. b_2,1; 6. b_1,2; 7. p_2,2; 8. p_3,1; 9. p_1,3 ");
							String cnf=future3.nextLine();
							addK(cnf,kb2);
						}
						else if( ans2.equals("q")) {
							break;
						}
						Scanner future2 =new Scanner (System.in);
						System.out.println("Enter the sentence you want to test in cnf form(like 4 0 -1 2 0 3): ");
						System.out.println("1. p_1,1; 2. b_1,1; 3. p_1,2; 4. p_2,1; 5. b_2,1; 6. b_1,2; 7. p_2,2; 8. p_3,1; 9. p_1,3 ");
						String sentence= future2.nextLine();
						resl=ttEntail(kb2,stringToCnf(sentence),nump);
						System.out.print("The sentence is entailed by kb : " +resl+"\n\n");
					}
					else if (ans1.equals("n")) {
						break;
					}
					else {
						System.out.println("wrong key entered");
						continue;
					}
				}
			}
			else if (input ==3) {
				
				File inf3=new File("3p2.cnf");
				ArrayList<ArrayList<String>> kb3=new ArrayList<ArrayList<String>>();
				kb3=cnfReader(inf3);
				System.out.println("Now the cnf form of the knowledge base of the third question is: ");
				printer(kb3);
				System.out.println();
				System.out.println("The statement that unicorn is mythical is :");
				int numP=Integer.parseInt(config[0]);
				boolean resl=ttEntail(kb3,stringToCnf("1"),numP);
				System.out.println(resl);
				System.out.println("The statement that unicorn is magical is :");
				resl=ttEntail(kb3,stringToCnf("5"),numP);
				System.out.println(resl);
				System.out.println("The statement that unicorn is horned is :");
				resl=ttEntail(kb3,stringToCnf("4"),numP);
				System.out.println(resl);
			}
			else {
				System.out.println("Wrong number, please re-enter:");
				input=sc.nextInt();
				continue;
			}
			break;
		}
		//---------------------------------------part3---------------------------------------------------//
		
		System.out.println("\n\n\n\n Now it is part3\n");
		
		String open;
		System.out.println("Enter the type of the problem which you want to test\n "
				+ "cnf  nqueens  pigeonhole");
		Scanner sc3=new Scanner (System.in);
		while(true) {
			
			open=sc.next();
			if(open.equals("cnf")) {
				System.out.println("Enter full name of the cnf file you want to test: ");
				while(true) {
					open=sc.next();
					if(open.equals("aim-50-1_6-yes1-4.cnf")) {
						open="CSC242_Project_2_cnf/cnf/aim-50-1_6-yes1-4.cnf";
						break;
					}
					else if (open.equals("hole6.cnf")) {
						open="CSC242_Project_2_cnf/cnf/hole6.cnf";
						break;
					}
					else if (open.equals("par8-1-c.cnf")) {
						
						open ="CSC242_Project_2_cnf/cnf/par8-1-c.cnf";
						break;
					}
					else if (open.equals("quinn.cnf")) {
						open="CSC242_Project_2_cnf/cnf/quinn.cnf";
						break;
					}
					else if (open.equals("zebra_v155_c1135.cnf")) {
						open="CSC242_Project_2_cnf/cnf/zebra_v155_c1135.cnf";
						break;
					}
					else {
						System.out.println("No such file exits, re-enter: ");
						continue;
					}
					
					
				}
				break;
			}
			else if (open.equals("nqueens")) {
				System.out.println("Enter the number of queens you want to test"
						+ "\n(4,8,12,16): ");
				while (true) {
					int num=sc3.nextInt();
					if(num==4) {
						open="CSC242_Project_2_cnf/nqueens/nqueens_4.cnf";
						break;
					}
					else if(num==8) {
						open="CSC242_Project_2_cnf/nqueens/nqueens_8.cnf";
						break;
					}
					else if (num==12) {
						open="CSC242_Project_2_cnf/nqueens/nqueens_12.cnf";
						break;
						
					}
					else if (num==16) {
						open="CSC242_Project_2_cnf/nqueens/nqueens_16.cnf";
						break;
					}
					else {
						
						System.out.println("No such cnf file exits for that number, re enter: ");
						continue;
					}
				}
				break;
				
			}
			else if(open.equals("pigeonhole")) {
				while (true) {
					System.out.println("Enter full name of the file"
							+ "\n \"pigeonhole_k_k.cnf\" k is number from 1 to 20:");
					try {
						open="CSC242_Project_2_cnf/pigeonhole/"+sc3.next();
						File test= new File (open);
						break;
					}
					catch(Exception e) {
						System.out.println("Wrong input re-enter: ");
						continue;
					}
				}
				break;
			}
			else {
				
				System.out.println("Wrong key word, re-enter: ");
				continue;
			}
		}
		
		Scanner scp3=new Scanner (System.in);
		
		
		
		String inputF= "CSC242_Project_2_cnf/cnf/hole6.cnf";
		File inf2=new File(open);
		ArrayList<ArrayList<String>> clauses=new ArrayList<ArrayList<String>>();
		int numP=Integer.parseInt(config[0]);
		clauses=cnfReader(inf2);
		//int [] assignment= new int [numP];
		
		//printer(clauses);
		System.out.println("Enter max-tries:");
		int mt=scp3.nextInt();
		//System.out.println(mt);
		System.out.println("Enter max-flips");
		int mf=scp3.nextInt();
		double start=System.currentTimeMillis();
		int [] rightAssign= GSAT(clauses,mf,mt);
		double end=System.currentTimeMillis();
		System.out.println("This test for "+open);
		System.out.println("The assignment is represented by an integer array. Truth value of each variable is represented by each entry of the arry");
		System.out.println("For example if you want to find truth value of \"i\", you should look at value of int[i-1](1 for true, -1 for false.) \n\n");
		System.out.println(Arrays.toString(rightAssign)+"\n it used "+((end-start)/1000)+"s"+"\n");
		if(rightAssign!=null) {
			
			System.out.println("It is(using plChecker to check whether it is truly right assignment): "+plChecker(clauses,rightAssign));
		
		}
		else {
			System.out.println("Sorry, the agent did not find solution, please re-run the program and try another value for MAX-FLIPS and MAX-TRIES");
		}
		
	}
	static int [] GSAT(ArrayList<ArrayList<String>> clauses, int MF, int MT) {
		int numP= Integer.parseInt(config[0]);
		int [] assignment =new int[numP];
		
		for(int numTry =0; numTry<MT; numTry++) {
			
			for (int i=0; i<numP;i++) {
				assignment[i] = randGen();
			}
			for(int numF=1;numF<=MF; numF++) {
				int [] max =new int [2]; //first entry for max count, second for position.
				max[0]=0;
				
				for(int p=0;p<numP;p++) {
					
					int count=sTest(assignment, clauses);
					//max[0]=count;
					if(count==Integer.parseInt(config[1])) {
						return assignment;
					}
							
					int [] tempAssign= copyOf(assignment);			
					if(tempAssign[p]==1) {
						tempAssign[p]=-1;
						count=sTest(tempAssign,clauses);
						if(count>max[0]) {
							max[0]=count;
							max[1]=p;
						}
					}
					else {
						tempAssign[p]=1;
						count=sTest(tempAssign,clauses);
						if(count>max[0]) {
							max[0]=count;
							max[1]=p;
						}
						
					}
					
					
				}
				System.out.println("Number of true clauses: "+max[0]+"      "+" num of Clauses: "+ Integer.parseInt(config[1]));
				if(assignment[max[1]]==1) {
					assignment[max[1]]=-1;
					
				}
				else {
					assignment[max[1]]=1;
				}
			}
		}
		
		return null;
	}
	
	static int sTest(int [] assignment , ArrayList<ArrayList<String>> clauses) {
		//boolean indicator=true;
		int count =0;
		for(ArrayList<String> clause: clauses) {
			for(String term : clause) {
				if(term.equals("")) break;
				
				int num =Integer.parseInt(term);
				
				if(num<0) {
					num = -1*num;
					if(assignment[num-1]==-1) {
						count++;
						//indicator=true;
						break;
					}
					
				}
				else {
					if(assignment[num-1]==1) {
						count++;
						//indicator=true;
						break;
					}
					
				}
				//indicator=false;
				
				
			}
			
			
			
		}
		
		
		return count;
	}
	
	/**
	 * 
	 * @return -1 or 1 randomly
	 * 
	 */
	static int randGen() {
		
		Random rand=new Random();
		int i=rand.nextInt(10);
		if(i<=4) {
			return -1;
		}
		else return 1;
		
	}
	
	static void addK(String cl,ArrayList<ArrayList<String>> oriKb)throws IOException {
		
		
		String [] toW=cl.split(" ");
		
		
		ArrayList<String> newCl= new ArrayList<String>();
		for(int i=0;i<toW.length;i++) {
			newCl.add(toW[i]);
			
		}
		oriKb.add(newCl);
	}
	
	static boolean ttEntail(ArrayList<ArrayList<String>> kb, ArrayList<ArrayList<String> >sentence, int numP) {
		
		int [] model =new int [numP];
		
		int pointer=0;
		
		return checkAll(kb,sentence,model,pointer,1)&&checkAll(kb,sentence,model,pointer,-1);
	}
	static boolean checkAll(ArrayList<ArrayList<String >> kb, ArrayList<ArrayList<String >> sentence, int [] model,int pointer,int value) {
		
		clearTail(model,pointer);
		
		if(pointer>model.length-1) {
			//System.out.println("reached");
			if(plChecker(kb,model)) {
				//System.out.println("kb is true");
				//System.out.println(plChecker(sentence,model));
				//System.out.println(Arrays.toString(model));
				//System.out.println("kb is true and sentence is "+plChecker(sentence,model) +"\n");
				return plChecker(sentence,model);
				
			}
			else {
				//System.out.println("kb is false");
				return true;
			}
		}
		else {
			//System.out.println(pointer);
			model[pointer]=value;
		}
		//System.out.println(Arrays.toString(model));
		//depth++;
		//System.out.println("depth is "+ depth);
		if(pointer+1>model.length-1) {
			return checkAll(kb,sentence,model,pointer+1,1);
		}
		return checkAll(kb,sentence,model,pointer+1,1)&&checkAll(kb,sentence,model,pointer+1,-1);
	}
	static boolean plChecker(ArrayList<ArrayList<String>> kb, int [] model) {
		
		boolean result=false;
		
		for(ArrayList<String> clause: kb) {
			
			for(String term: clause) {
				if(term.equals("")) {
					break;
				}
				int number= Integer.parseInt(term);
				if(number<0) {
					number=-1*number;
					if(model[number-1]==-1) {
						result=true;
						break;
					}
					else {
						result=false;
					}
				}
				else {
					//System.out.println(number);
					if(model[number-1]==1) {
						result=true;
						break;
						
					}
					else {
						result=false;
					}
				}
				
			}
			if(!result) return false;
		}
		return result;
	}
	static void clearTail(int [] model, int pointer) {
		
		
		for(int i=pointer +1;i<model.length;i++) {
			model [i]=0;
		}
		
		
	}
	static ArrayList<ArrayList<String>>stringToCnf(String s){
			ArrayList<String> cl=new ArrayList<String>();
			ArrayList<ArrayList<String>> allCl=new ArrayList<ArrayList<String>>();
			String [] toAdd= s.split(" ");
			for(int i=0;i<toAdd.length;i++) {
				if(toAdd[i].equals("0")) {
					allCl.add(cl);
					cl=new ArrayList<String>();
					continue;
				}
				cl.add(toAdd[i]);
			}
		
		allCl.add(cl); //if your argument end with 0, then you will have a repeating clause for the last one.
		return allCl; //However, it doesn't matter, since all clauses have relation of conjunction.
	}
	
	
	static ArrayList<ArrayList<String>> cnfReader(File inf) throws IOException{
		
		Scanner fc =new Scanner (inf);
		
		ArrayList<ArrayList<String>> clauses= new ArrayList<>();
		//String [] config=new String[2];
		while(fc.hasNext()) {
			String b=fc.next();
			//System.out.println(b);
			Clause cl=new Clause();
			if(b.equalsIgnoreCase("c")) {
				fc.nextLine();
			}
			else if(b.equalsIgnoreCase("p")) {
				fc.next();
				config[0]=fc.next();
				
				config[1]=fc.next();
				
				
			}
			else {
				cl.add(b);
				String n = fc.next();
				while (!n.equals("0")) {
					cl.add(n);
					n=fc.next();
				}
				clauses.add(cl.get());
			}
			
		}
		return clauses;
	}
	
	static void printer(ArrayList<ArrayList<String>> clauses) {
		
		
		for(int i=0;i<clauses.size();i++) {
			ArrayList<String > cl= clauses.get(i);
			System.out.print(i+". ");
			for(int j=0;j<cl.size();j++) {
				System.out.print(cl.get(j)+" ");
			}
			System.out.println();
		}
	} 
	static int [] copyOf(int [] ary) {
		
		int l=ary.length;
		int [] res= new int [l];
		for (int i=0;i<l;i++) {
			res[i]=ary[i];
		}
		
		return res;
	}

}
