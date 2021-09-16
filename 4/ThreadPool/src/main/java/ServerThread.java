import parser.JSONObject;
import parser.JSONParser;
import parser.ParseException;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable{

	private final Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
			String inputStr = inputStream.readLine();
			if (inputStr != null) {
				if (inputStr.equals("\\stop")) {
					MultiThreadServer.stopServer();
				}
				JSONObject jsonObject = JSONParser.parseToJSONObject(inputStr);
				bufferedWriter.write("HTTP/1.1 200 OK\n");
				bufferedWriter.flush();
			}
		}
		catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
}
