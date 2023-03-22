package scraping;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import schedule.*;

public class PrereqPuller {
	public ArrayList<Course> noPrereqs;
	public ArrayList<Course> metPrereqs;
	public ArrayList<Course> yesPrereqs;
	public Elements descriptions;
	
	public PrereqPuller() {
		this.metPrereqs = new ArrayList<Course>();
		this.noPrereqs = new ArrayList<Course>();
		this.yesPrereqs = new ArrayList<Course>();
	}
	
	public static Elements removeDuplicates(Elements list)  { 
        Set<Element> set = new LinkedHashSet<>(); 
        set.addAll(list); 
        list.clear(); 
        list.addAll(set);
        return list; 
    }
	
	public void removeMyDuplicates() {
		this.descriptions = PrereqPuller.removeDuplicates(this.descriptions);
	}
  
	
	public void getAllDescriptions(String url) {
		try {
			Document trial = Jsoup.connect(url).get();
			for (Element body : trial.select("div.nonaccordion")) {
				this.descriptions = body.getElementsContainingOwnText("COURSE DESCRIPTIONS").first().parent().parent().nextElementSiblings();
				if (this.descriptions.text().isEmpty()) {
					this.descriptions = body.getElementsContainingOwnText("COURSE DESCRIPTIONS").first().parent().nextElementSiblings();
				}
				this.removeMyDuplicates();
			}
		}
		catch (Exception ex) {
			
		}
		
	}
	
	public void matchCourseInstances(ArrayList<Course> setofCourses, DataPuller d) {
		for (int i = 0; i < setofCourses.size(); i++) {
			for (int n = 0; n < this.descriptions.size(); n++) {
				if(this.descriptions.get(n).child(0).ownText().contains(setofCourses.get(i).removeMySections()) || this.descriptions.get(n).child(0).ownText().contains(setofCourses.get(i).codeAlternate(setofCourses.get(i).removeMySections()))) {
					if(this.descriptions.get(n).childrenSize() > 2 && this.descriptions.get(n).text().contains("Prerequisite")) {
						if(this.yesPrereqs.contains(d.modifyMyCourses(i, this.parsePrereq(this.descriptions.get(n)))) == false) {
							this.yesPrereqs.add(d.modifyMyCourses(i, this.parsePrereq(this.descriptions.get(n))));
						}
					}
					else {
						if (this.noPrereqs.contains(setofCourses.get(i)) == false) {
							this.noPrereqs.add(setofCourses.get(i));
						}
					}
				}
			}
		}
	}
	
	public String parsePrereq(Element e) {
		String parsed;
		if (e.text().contains("Prerequisites")) {
			//parsed = e.text().substring(e.text().indexOf("Prerequisites: ")+14);
			parsed = e.child(e.childrenSize()-1).ownText().substring(e.child(e.childrenSize()-1).ownText().indexOf("Prerequisites:")+14);
		}
		else {
			//parsed = e.text().substring(e.text().indexOf("Prerequisite: ")+13);
			parsed = e.child(e.childrenSize()-1).ownText().substring(e.child(e.childrenSize()-1).ownText().indexOf("Prerequisite:")+13);
		}
		return parsed;
	}
	
	public void printWhatIHave() {
		System.out.println("Courses without prerequisites: ");
		for (int i = 0; i < this.noPrereqs.size(); i++) {
			System.out.print("\n" + (i+1) + ". ");
			this.noPrereqs.get(i).printMyInfo();
		}
		System.out.println("\nCourses with prerequisites: ");
		for (int a = 0; a < this.yesPrereqs.size(); a++) {
			System.out.print("\n" + (a+1) + ". ");
			this.yesPrereqs.get(a).printMyInfo();
		}
	}
	
	
}
