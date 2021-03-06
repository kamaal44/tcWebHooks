package webhook.teamcity.server.rest.data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import jetbrains.buildServer.serverSide.SBuildServer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import webhook.teamcity.ProjectIdResolver;
import webhook.teamcity.payload.WebHookPayloadManager;
import webhook.teamcity.payload.WebHookTemplateManager;
import webhook.teamcity.payload.template.AbstractXmlBasedWebHookTemplate;
import webhook.teamcity.payload.template.SlackComCompactXmlWebHookTemplate;
import webhook.teamcity.settings.config.WebHookTemplateConfig;
import webhook.teamcity.settings.entity.WebHookTemplateJaxHelperImpl;

public class TemplateFinderTest {
	
	@Mock	SBuildServer server;
	
	private WebHookTemplateManager webHookTemplateManager;
	
	@Mock
	private ProjectIdResolver projectIdResolver;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		when(server.getRootUrl()).thenReturn("http://test.url");
		when(projectIdResolver.getInternalProjectId("_Root")).thenReturn("preoject0");
		
		WebHookPayloadManager webHookPayloadManager = new WebHookPayloadManager(server);
		webHookTemplateManager = new WebHookTemplateManager(webHookPayloadManager, new WebHookTemplateJaxHelperImpl(), projectIdResolver);
		
		AbstractXmlBasedWebHookTemplate wht = new SlackComCompactXmlWebHookTemplate(webHookTemplateManager, webHookPayloadManager, new WebHookTemplateJaxHelperImpl(), projectIdResolver, null);
		wht.register();
	}

	@Test
	public void testFindTemplateWithNoDimention() {
		TemplateFinder templateFinder = new TemplateFinder(webHookTemplateManager, projectIdResolver);
		WebHookTemplateConfig e = templateFinder.findTemplateById("slack.com-compact").getTemplateConfig();
		assertEquals("slack.com-compact", e.getId());
	}
	
	@Test
	public void testFindTemplateByIdDimension() {
		TemplateFinder templateFinder = new TemplateFinder(webHookTemplateManager, projectIdResolver);
		WebHookTemplateConfig e = templateFinder.findTemplateById("id:slack.com-compact").getTemplateConfig();
		assertEquals("slack.com-compact", e.getId());
	}
	
	@Test
	public void testFindTemplateByNameDimension() {
		TemplateFinder templateFinder = new TemplateFinder(webHookTemplateManager, projectIdResolver);
		WebHookTemplateConfig e = templateFinder.findTemplateById("name:slack.com-compact").getTemplateConfig();
		assertEquals("slack.com-compact", e.getId());
		System.out.println(e.getTemplateDescription());
	}

}
