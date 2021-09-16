import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer implements Runnable{
	private static boolean stopServer = false;
	private final int port;
	private final ExecutorService executorService = Executors.newFixedThreadPool(7);
	ServerSocket serverSocket;

	public MultiThreadServer(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		openServerSocket();
		while (!stopServer) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			executorService.execute(new ServerThread(socket));
		}
		closeServerSocket();
		executorService.shutdown();
	}

	private void openServerSocket() {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void closeServerSocket() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized void stopServer() {
		stopServer = true;
	}
}
