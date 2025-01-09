package rest_assured_practice.main.pojo;

public class GetCourse {

	private String url;
	private String services;
	private String expertise;
	private Courses courses;
	private String instructor;
	private String linkedIn;

	// Getter for url
	public String getUrl() {
		return url;
	}

	// Setter for url
	public void setUrl(String url) {
		this.url = url;
	}

	// Getter for services
	public String getServices() {
		return services;
	}

	// Setter for services
	public void setServices(String services) {
		this.services = services;
	}

	// Getter for expertise
	public String getExpertise() {
		return expertise;
	}

	// Setter for expertise
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	// Getter for courses
	public Courses getCourses() {
		return courses;
	}

	// Setter for courses
	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	// Getter for instructor
	public String getInstructor() {
		return instructor;
	}

	// Setter for instructor
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	// Getter for linkedIn
	public String getLinkedIn() {
		return linkedIn;
	}

	// Setter for linkedIn
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
}
