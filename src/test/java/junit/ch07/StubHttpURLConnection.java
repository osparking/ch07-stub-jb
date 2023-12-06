package junit.ch07;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class StubHttpURLConnection extends HttpURLConnection {
	private boolean isInput = true;

	protected StubHttpURLConnection(URL url) {
		super(url);
	}

	@Override
	public InputStream getInputStream() throws IOException {
		if (!isInput) {
			throw new ProtocolException("URLConnection 에서 읽을 수 없음"
					+ " 만일 !doInput 이면, setDoInput(true) 를 호출하라");
		}
		ByteArrayInputStream readStream = new ByteArrayInputStream(
				new String("제티 작동함").getBytes());
		return readStream;
	}

	@Override
	public void connect() throws IOException {
	}

	@Override
	public void disconnect() {
	}

	@Override
	public boolean usingProxy() {
		return false;
	}
}

