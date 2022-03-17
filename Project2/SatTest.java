 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

//Part 3
public class SatTest {
	protected ArrayList<Integer> heuristicValues = new ArrayList<>();
	public SatTest() {
		ArrayList<Integer> heuristicValues = this.heuristicValues;
	}
	//PigenHole problem
    public ArrayList<ArrayList<Integer>> getKB_PigeonHole(int holes) {
	       ArrayList<ArrayList<Integer>> kb = new ArrayList<ArrayList<Integer>>();
	       //their quantities are the same
	       int pigeons = holes; 
	       if (holes == 1) {
	            kb.add(new ArrayList<Integer>(Arrays.asList(1)));
				return kb;
	        } else {
	        	//add clauses	
	           for (int p = 1; p <= pigeons; p++) { 
	                ArrayList<Integer> conjunc = new ArrayList<Integer>();
	                for (int h = 1; h <= holes; h++) {
	                    int i = (p-1)*holes + h; 
	                    conjunc.add(i);
	                }
	                kb.add(conjunc);
	            }
	           //add the constraints
	            for (int i = 0; i < pigeons; i++) { 
	                for (int j = 0; j < holes-1; j++) {
	                    for (int k = j+1; k < holes; k++) {
	                        ArrayList<Integer> conjunc = new ArrayList<Integer>();
	                        conjunc.add(-kb.get(j).get(i));
	                        conjunc.add(-kb.get(k).get(i));
	                        kb.add(conjunc);
	                    }
	                }
	            }

	        }

	        return kb;
	    }
    
	//a randomly generated truth assignment
	public void RandomAssignments(HashMap<Integer, Boolean> T, int variableCount) {
		for(int i = 1;i<=variableCount;i++) {
			if(Math.random() > 0.5) {
				T.put(i, true);
//				T.put(i, Boolean.TRUE);
			}else {
				T.put(i, false);
//				T.put(i, Boolean.FALSE);
			}
		}
	}
	
	public Boolean GSATHelper(ArrayList<ArrayList<Integer>> sentence, HashMap<Integer,Boolean> model) {
		Model m = new Model();
		return m.KbPlTrue(sentence, model);
	}

	public HashMap<Integer,Boolean> performFlip(HashMap<Integer, Boolean> T, boolean trace) {
		int MaxHeuristic = Collections.max(heuristicValues);
		int index = heuristicValues.indexOf(MaxHeuristic);
//		if(T.get(index+1) == Boolean.FALSE) {
		if(T.get(index+1) == false) {
			T.replace(index+1, Boolean.TRUE);
		}else {
//			T.replace(index+1, Boolean.FALSE);
			T.replace(index+1, false);
		}
		if(trace) {
			System.out.println("At index" + index+", value changed because of heuristic value");
		}
		return T;
	}

	public Boolean tryFlip(ArrayList<ArrayList<Integer>> sentence, HashMap<Integer, Boolean> model, int index){
//		Boolean result = Boolean.TRUE;
		Boolean result = false;
		int heuristic = 0;
		for(int i = 0; i < sentence.size(); i++){
			ArrayList<Integer> clause = sentence.get(i);
			Boolean clauseEval = clauseEval(clause,model, index);
			result = result && clauseEval;
//			if(clauseEval == Boolean.TRUE) {
			if(clauseEval == true) {
				heuristic++;
			}
		}
		heuristic_GSAT(heuristic);
		return result;
	}
	private Boolean clauseEval(ArrayList<Integer> clause, HashMap<Integer, Boolean> model, int index){
//		Boolean result = Boolean.FALSE;
		Boolean result = false;
		ArrayList<Integer> clause2 = new ArrayList<>();
		for(int i = 0; i < clause.size(); i++){
			clause2 = extracted(clause);
			int x = clause.get(i);
			Boolean b = model.get(Math.abs(x));
			if(b != null){
				if(Math.abs(x) == index) {
					clause2.set(i, x*-1);
				}
				boolean bo = intEval(clause2.get(i), model, index);
				result = result || bo; 
				if(Math.abs(x) == index) {
					clause2.set(i, x*-1);
				}
			}
		}
		return result;
	}
	
	private Boolean intEval(Integer atomic, HashMap<Integer, Boolean> model, int index){
		if(atomic < 0){
			Boolean assignment = model.get(Math.abs(atomic));
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
		return model.get(atomic);
	}

	private ArrayList<Integer> extracted(ArrayList<Integer> clause) {
		return (ArrayList<Integer>) clause.clone();
	}

	public void heuristic_GSAT(int heuristic) {
		heuristicValues.add(heuristic);
	}
	
}


