package webhook.teamcity.payload;

import org.jdom.Element;

public class WebHookTemplateContent {
	String templateState;
	String templateText;
	boolean enabled;
	String preferredDateTimeFormat = "";
	
	public static final String XML_ELEMENT_NAME = "template";
	public static final String ATTR_STATE = "build-state";
	public static final String ATTR_TEMPLATE = "template-text";
	public static final String ATTR_ENABLED = "enabled";
	
	public static WebHookTemplateContent create(String templateState, String templateText, boolean enabled, String dateTimeFormat){
		WebHookTemplateContent t = new WebHookTemplateContent();
		t.templateState = templateState;
		t.templateText = templateText;
		t.enabled = enabled;
		t.preferredDateTimeFormat = dateTimeFormat;
		return t;
	}
	
	public String getTemplateText() {
		return templateText;
	}
	
	public String getPreferredDateTimeFormat() {
		return preferredDateTimeFormat;
	}

	public Element getAsElement() {
		Element e = new Element(XML_ELEMENT_NAME);
			e.setAttribute(ATTR_STATE, this.templateState);
			e.setAttribute(ATTR_TEMPLATE, this.templateText);
			e.setAttribute(ATTR_ENABLED, String.valueOf(this.enabled));
		return e;
	}
	
	public WebHookTemplateContent copy (){
		WebHookTemplateContent t = new WebHookTemplateContent();
		t.templateState = this.templateState;
		t.templateText = this.templateText;
		t.enabled = this.enabled;
		t.preferredDateTimeFormat = this.preferredDateTimeFormat;
		return t;
	}
}