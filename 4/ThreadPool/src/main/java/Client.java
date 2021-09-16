import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 54542);
		     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		     BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			Pojo pojo = new Pojo();
			pojo.nums = new int[] {1, 2 ,3, 4, 5};
			pojo.id = 1;
			pojo.str = "some string";
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(pojo) + "\n";
			System.out.println(json);
			bufferedWriter.write(json);
			bufferedWriter.flush();
			String response = inputStream.readLine();
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
