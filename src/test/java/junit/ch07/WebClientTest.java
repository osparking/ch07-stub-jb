package junit.ch07;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class WebClientTest {

	@BeforeAll
	public static void setUp() {
		// 제티를 시작하고, 구성하여 다음 URL 이 호출되면
		// http://localhost:8081/testGetContentOk URL
		// "제티 작동함"을 반환하게 하라.
	}
	
	@AfterAll
	public static void teadDown() {
		// 제티를 멈추라.
	}
	
	@Test
	@Disabled("이것은 시험의 골격이다. 따라서, 실행하면, 실패할 것이다.")
	void testGetContentOk() throws MalformedURLException {
		WebClient client = new WebClient();
		String url = "http://localhost:8081/testGetContentOk";
		String workingContent = client.getContent(new URL(url));
		
		assertEquals("제티 작동함", workingContent);
	}
}

