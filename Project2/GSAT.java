import java.util.ArrayList;
import java.util.HashMap;

public class GSAT extends SatTest {
	SatTest sa = new SatTest();
	//GSAT does hillclimbing for at most MAX-FLIPS steps per run, with random restarts up to MAX-TRIES times.
	//boolean print is a choice for choose print tracing or not
	public boolean GSATAlgorithm(ArrayList<ArrayList<Integer>> clauses, int maxFlips, int maxTries, int count, boolean trace, boolean print) {
		HashMap<Integer,Boolean> Table = new HashMap<Integer,Boolean>();
		//Max Tries
		for(int t = 1;t<maxTries;t++) {
			sa.RandomAssignments(Table, count);
			//Max Flips
			for(int j = 0; j<maxFlips;j++) {
				sa.heuristicValues.clear();
				if(sa.GSATHelper(clauses, Table)) {
//				if(true) {
					System.out.println("Performing "+t+" times || Flips "+maxFlips+" times");
					System.out.println("We can find the proposition is satisfiable");
					System.out.print("The row that contain the truth table: ");
					if(print) {
						System.out.println(Table.values().toString());
						}
					return true;
				}
				int l =1;
				while(l<=count) {
					sa.tryFlip(clauses, Table, l);
					l++;
				}
				Table = sa.performFlip(Table, trace);
				if(trace) {
					System.out.println(Table.values().toString());
					System.out.println();
				}
			}
		}
		return false;
	}
	
	
	
	
}
