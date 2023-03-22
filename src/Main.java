//import java.util.ArrayList;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.*;
//import org.jsoup.select.*;

import schedule.*;
import scraping.*;

public class Main {

	public static void main(String[] args) {
		
		DataPuller pull = new DataPuller();
		PrereqPuller reqs = new PrereqPuller();
		DataWorker check = new DataWorker();
		PrereqChecker hist = new PrereqChecker();
		//String sched = "https://registration.boun.edu.tr/scripts/sch.asp?donem=2019/2020-2&kisaadi=LING&bolum=LINGUISTICS";
		String sched = "https://registration.boun.edu.tr/scripts/sch.asp?donem=2019/2020-2&kisaadi=YADYOK&bolum=SCHOOL+OF+FOREIGN+LANGUAGES";
		////String curr = "http://www.boun.edu.tr/en_US/Content/Academic/Undergraduate_Catalogue/Faculty_of_Arts_and_Sciences/Department_of_Linguistics";
		String curr = "http://www.boun.edu.tr/en_US/Content/Academic/Undergraduate_Catalogue/The_School_of_Foreign_Languages/Modern_Languages_Unit";
		pull.getCoursesOnPage(sched);
		reqs.getAllDescriptions(curr);
		
		pull.sendtoMatch(reqs);
		
		pull.printAll();

		//reqs.letsCheck();
		//reqs.printWhatIHave();
		
		Timetable t = new Timetable();
		//pull.sendMyCourses().get(8).addMe(t);
		
		//t.printSchedule();
		
		//hist.readRecords();
		
		//check.massCheckConflict(hist, reqs.noPrereqs, t);
		//check.massCheckPrerequisite(hist, reqs.yesPrereqs, t);
		//check.printPotentials();
		//check.addAllPotentials(t);
		
		//t.printSchedule();
		
		
		
		
		
	}

}
