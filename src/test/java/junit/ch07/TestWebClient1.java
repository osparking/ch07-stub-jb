package junit.ch07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestWebClient1 {

	@BeforeAll
	public static void setUp() {
		URL.setURLStreamHandlerFactory(new StubStreamHandlerFactory());
	}

	private static class StubStreamHandlerFactory
			implements URLStreamHandlerFactory {

		@Override
		public URLStreamHandler createURLStreamHandler(String protocol) {
			return new StubHttpURLStreamHandler();
		}
	}

	private static class StubHttpURLStreamHandler extends URLStreamHandler {

		@Override
		protected URLConnection openConnection(URL u) throws IOException {
			return new StubHttpURLConnection(u);
		}
	}

	@Test
	void testGetContentOk() throws MalformedURLException {
		WebClient client = new WebClient();
		String workingContent = client.getContent(new URL("http://localhost"));
		assertEquals("제티 작동함", workingContent);
	}
}

