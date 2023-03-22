package scraping;


import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import schedule.*;

public class DataPuller {
	public ArrayList<Course> allCourses;
	
	public DataPuller() {
		this.allCourses = new ArrayList<Course>();
	}
	public void getCoursesOnPage(String url) {
		try {
			Document trial = Jsoup.connect(url).get();
			for (Element row : trial.select("table:nth-child(10)")) {
				if (row.select("td:nth-child(1)").text().equals("")) {
					continue; 
				}
				else {
					Elements allCodes = row.select("td:nth-child(1)");
					Elements allNames = row.select("td:nth-child(3)");
					Elements allDays = row.select("td:nth-child(8)");
					Elements allHours = row.select("td:nth-child(9)");
					for (int i = 1; i < allHours.size(); i++) {
						if (allHours.get(i).hasText() && allHours.get(i).ownText().matches("\\d+")) {
						this.allCourses.add(new Course(allCodes.get(i).text(), allNames.get(i).text(), allDays.get(i).text(), allHours.get(i).text()));
						}
					}
				} 
			}
		}

		catch (Exception ex) {
			
		}
	}
	
	public ArrayList<Course> sendMyCourses() {
		return this.allCourses;
	}
	
	public Course modifyMyCourses(int i, String prereq) {
		this.allCourses.get(i).addPrereq(prereq);
		return this.allCourses.get(i);
	}
	
	public void sendtoMatch(PrereqPuller p) {
		p.matchCourseInstances(this.allCourses, this);
	}
	
	public void printAll() {
		for (int i = 0; i<this.allCourses.size(); i++) {
			this.allCourses.get(i).printMyInfo();
		}
	}
	

}
