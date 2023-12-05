package junit.ch07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;

class WebClientTest {

	@BeforeAll
	public static void setUp() throws Exception {
		// 제티를 시작하고, 구성하여 다음 URL 이 호출되면
		Server server = new Server(8081);
		
		// http://localhost:8081/testGetContentOk URL
		Context root = new Context(server, "/testGetContentOk");
		
		// "제티 작동함"을 반환하게 하라.
		root.setHandler(new TestGetContentOkHandler());
		
		server.setStopAtShutdown(true);
		server.start();		
	}
	
	@AfterAll
	public static void teadDown() {
		// 제티를 멈추라.
	}
	
	@Test
	void testGetContentOk() throws MalformedURLException {
		WebClient client = new WebClient();
		String url = "http://localhost:8081/testGetContentOk";
		String workingContent = client.getContent(new URL(url));
		
		assertEquals("제티 작동함", workingContent);
	}
	
	private static class TestGetContentOkHandler extends AbstractHandler {

		@Override
		public void handle(String target, HttpServletRequest request,
				HttpServletResponse response, int dispatch)
				throws IOException, ServletException {
			try (OutputStream out = response.getOutputStream();
					ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer()) {
				writer.write("제티 작동함");
				response.setIntHeader(HttpHeaders.CONTENT_LENGTH,
						writer.size());
				writer.writeTo(out);
			} 			
		}
	}
}

