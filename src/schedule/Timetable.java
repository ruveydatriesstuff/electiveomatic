package schedule;

public class Timetable {
	public Course table[][];
	public Timetable() {
		this.table = new Course[5][14];
	}
	
	public void addtoSchedule(Course c, String[] DotW, int[] TotD) {
		if (this.isThereAConflict(c, DotW, TotD) == false) {
			for (int i = 0; i< TotD.length; i++) {
				int g = this.convertDays(DotW[i]);
				int s = TotD[i]-1;
				this.table[g][s] = c;
			}
		}
	}
	
	public int convertDays(String day) {
		int num = 0;
		if (day.equals("M")) {
			num = 0;
		}
		else if (day.equals("T")) {
			num = 1;
		}
		else if (day.equals("W")) {
			num = 2;
		}
		else if (day.equals("Th")) {
			num = 3;
		}
		else if (day.equals("F")) {
			num = 4;
		}
		return num;
	}
	
	
/*	public int[] separateHours(String hours) {
		String[] split = hours.split("");
		int[] classTimes = new int[hours.length()];
		for (int i = 0; i<hours.length(); i++) {
			classTimes[i] = Integer.parseInt(split[i]);
		}
		return classTimes;
	}
	*/
	
	
	
	public void printSchedule() {
		for (int m = 0; m < 14; m++) {
			for (int i = 0; i < 5; i++) {
				if (this.table[i][m] != null) {
					System.out.print(this.table[i][m].Code + " ");
				}
				else if (this.table[i][m]== null) {
					System.out.print("			");
				}
			}
			System.out.print("\n");

		}
	}
	
	public boolean isThereAConflict(Course c, String[] DotW, int[] TotD) {
		Boolean conflict = false;
		for (int i = 0; i< TotD.length; i++) {
			int g = this.convertDays(DotW[i]);
			int s = TotD[i]-1;
			if (this.table[g][s] == null) {
				continue;
			}
			else {
				conflict = true;
			}
		}
		return conflict;
	}
}
