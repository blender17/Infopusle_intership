import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Random;
import java.util.ResourceBundle;

@WebServlet("/")
public class CalculatorServlet extends HttpServlet {
	private final String METHOD_MULT = "MULT";
	private final String METHOD_DIV = "DIV";
	private final String METHOD_SUB = "SUB";
	private final String METHOD_ADD = "ADD";
	private static final ResourceBundle lStrings = ResourceBundle.getBundle("javax.servlet.http.LocalStrings");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		Random random = new Random();
		PrintWriter printWriter = resp.getWriter();
		printWriter.println("<p>random num - ");
		printWriter.println("<b>" + random.nextInt() + "<b><p>");
		printWriter.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = req.getParameter("num");
		resp.setContentType("text/html");
		PrintWriter printWriter = resp.getWriter();
		printWriter.println("<p>echo response<p>");
		printWriter.println("<b>" + str + "<b>");
		printWriter.close();
	}

	public void doMult(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double num1 = Double.parseDouble(req.getParameter("num1"));
		double num2 = Double.parseDouble(req.getParameter("num2"));
		double result = num1 * num2;
		PrintWriter printWriter = resp.getWriter();
		printWriter.println("<p>result = ");
		printWriter.println("<b>" + result + "<b><p>");
		printWriter.close();
	}

	public void doDiv(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double num1 = Double.parseDouble(req.getParameter("num1"));
		double num2 = Double.parseDouble(req.getParameter("num2"));
		double result = num1 / num2;
		PrintWriter printWriter = resp.getWriter();
		printWriter.println("<p>result = ");
		printWriter.println("<b>" + result + "<b><p>");
		printWriter.close();
	}

	public void doSub(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double num1 = Double.parseDouble(req.getParameter("num1"));
		double num2 = Double.parseDouble(req.getParameter("num2"));
		double result = num1 - num2;
		PrintWriter printWriter = resp.getWriter();
		printWriter.println("<p>result = ");
		printWriter.println("<b>" + result + "<b><p>");
		printWriter.close();
	}

	public void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double num1 = Double.parseDouble(req.getParameter("num1"));
		double num2 = Double.parseDouble(req.getParameter("num2"));
		double result = num1 + num2;
		PrintWriter printWriter = resp.getWriter();
		printWriter.println("<p>result = ");
		printWriter.println("<b>" + result + "<b><p>");
		printWriter.close();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		switch (method) {
			case "GET" -> this.doGet(req, resp);
			case "POST" -> this.doPost(req, resp);
			case "MULT" -> this.doMult(req, resp);
			case "DIV" -> this.doDiv(req, resp);
			case "SUB" -> this.doSub(req, resp);
			case "ADD" -> this.doAdd(req, resp);
			default -> {
				String errMsg = lStrings.getString("http.method_not_implemented");
				Object[] errArgs = new Object[]{method};
				errMsg = MessageFormat.format(errMsg, errArgs);
				resp.sendError(501, errMsg);
			}
		}
	}
}
