package scraping;

import java.util.ArrayList;

import schedule.*;

public class DataWorker {
	public ArrayList<Course> potentials;
	
	public DataWorker() {
		this.potentials = new ArrayList<Course>();
	}
	/**
	 * 
	 * @param t
	 * @param dataSet a set of no-prerequisite courses
	 */
	public void massCheckConflict(PrereqChecker p, ArrayList<Course> dataSet, Timetable t) {
		for (int i = 0; i < dataSet.size();i++) {
			if (dataSet.get(i).sendToConflict(t) == false) {
				if (p.historyMatcher(dataSet.get(i))== false) {
					this.potentials.add(dataSet.get(i));
				}
			}
		}	
	}
	public void printPotentials() {
		for (int i = 0; i<this.potentials.size(); i++) {
			this.potentials.get(i).printMyInfo();
			System.out.println("\n");
		}
	}
	
	public void addAllPotentials(Timetable t) {
		for (int i = 0; i < this.potentials.size(); i++) {
			this.potentials.get(i).addMe(t);
		}
	}
		
	public void massCheckPrerequisite(PrereqChecker p, ArrayList<Course> dataSet, Timetable t) {
		for (int i = 0; i< dataSet.size(); i++) {
			System.out.println("I am " + dataSet.get(i).Code + " my prerequisites are: " + dataSet.get(i).Prerequisite);
			if (p.prereqMet(dataSet.get(i))) {
				System.out.println("You meet my prerequisites. ");
				if (dataSet.get(i).sendToConflict(t) == false) {
					if (p.historyMatcher(dataSet.get(i)) == false) {
						this.potentials.add(dataSet.get(i));
					}
				}
			}
			else {
				System.out.println("You do not meet my prerequisites. ");
			}
		}
	}

}
