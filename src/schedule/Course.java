package schedule;

public class Course {
	public String Name;
	public String Code;
	public String Days;
	public String Hours;
	public String Prerequisite;
	public boolean hasPrerequisite;
	public String[] DotW;
	public int[] TotD;
	
	public Course (String code, String name, String days, String hours) {
		this.Name = name;
		this.Code = code;
		this.Days = days;
		this.Hours = hours;
		this.hasPrerequisite = false;
		this.Prerequisite = null;
		this.DotW = this.separateDays(days);
		this.TotD = this.separateHours(hours, this.DotW);
		
	}
	
	public void addMe(Timetable t) {
		t.addtoSchedule(this, this.DotW, this.TotD);
	}
	
	public void printMyInfo() {
		System.out.println("Code: " + this.Code + " \nName: " + this.Name + " \nDays: " + this.Days +
				" \nHours: " + this.Hours);
		if (this.hasPrerequisite) {
			System.out.println("Has a prerequisite: Yes. \nPrerequisite: " + this.Prerequisite);
		}
		else if (this.hasPrerequisite == false) {
			System.out.println("Has a prerequisite: No. ");
		}
	}
	
	public boolean sendToConflict(Timetable t) {
		return t.isThereAConflict(this, this.DotW, this.TotD);
	}
	
	public String removeMySections() {
		String code = this.Code.substring(0, this.Code.indexOf("."));
		return code;
	}
	
	public String codeAlternate(String code) {
		String alternate = code;
		if (Character.isDigit(code.charAt(3))) {
			alternate = alternate.substring(0, 3) + " " + alternate.substring(3);
		}
		else if (Character.isWhitespace(code.charAt(3))) {

		}
		else if (Character.isAlphabetic(code.charAt(3))) {
			if (Character.isWhitespace(code.charAt(4))) {
				
			}
			else if (Character.isDigit(code.charAt(4))) {
				alternate = alternate.substring(0, 4) + " " + alternate.substring(4);
			}
		}
		return alternate;
	}
	
	public void addPrereq(String prereq) {
			this.Prerequisite = prereq;
			this.hasPrerequisite = true;
	}
	
	public boolean doYouMeet(String history) {
		boolean met = false;
		if (this.Prerequisite.contains(history)) {
			met = true;
		}
		return met;
	}
	
	public boolean didITake(String input) {
		boolean didI = false;
		if (this.Code.contains(input)) {
			didI = true;
		}
		return didI;
	}
	
	public String[] separateDays(String days) {
		String[] eachDay = days.split("(?<!^)(?=[A-Z])");
		return eachDay;
	}
	
	//non-consecutive late hours!! 
	public int[] separateHours(String hours, String[] days) {
		String separated = " "; 
		int idealLength = days.length + hours.length() -1;
		int[] classTimes = new int[days.length];
		for (int i = 0; i<hours.length(); i++) {
			separated = separated + hours.charAt(i) + " ";
		}
		separated = separated.trim();
		if (days.length == hours.length()) {
		}
		else if (hours.length() > days.length){
				for (;separated.length() != idealLength;) {
					if(separated.contains("1 0")) {
						separated = separated.substring(0, separated.indexOf("1 0")) + "10" + separated.substring(separated.indexOf("1 0")+3);
					}
					if (separated.contains("10 1 1")) {
						separated = separated.substring(0, separated.indexOf("10 1 1")) + "10 11" + separated.substring(separated.indexOf("10 1 1")+6);

					}
					if (separated.contains("11 1 2") && days[separated.replaceAll(" ", "").indexOf("11")] == days[separated.replaceAll(" ", "").indexOf("12")]) {
						separated = separated.substring(0, separated.indexOf("11 1 2")) + "11 12" + separated.substring(separated.indexOf("11 1 2")+6);
					}
				}

			}
		String[] split = separated.split(" ");
		for (int i = 0; i< classTimes.length; i++) {
			classTimes[i] = Integer.parseInt(split[i]);
		}
		
		return classTimes;
	}
	
}
