package rest_assured_practice.main.auth_pojo.pojo;

import java.util.List;

public class Courses {

	private List<WebAutomation> webAutomation;
	private List<Api> api;
	private List<Mobile> mobile;

	// Getter for webAutomation
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}

	// Setter for webAutomation
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}

	// Getter for api
	public List<Api> getApi() {
		return api;
	}

	// Setter for api
	public void setApi(List<Api> api) {
		this.api = api;
	}

	// Getter for mobile
	public List<Mobile> getMobile() {
		return mobile;
	}

	// Setter for mobile
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}
}
