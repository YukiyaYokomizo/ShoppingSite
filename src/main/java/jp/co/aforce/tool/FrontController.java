
package jp.co.aforce.tool;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.action")
public class FrontController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");

			String path = request.getServletPath().substring(1);
			String name = "jp.co.aforce.servlet." + path.replace(".a", "A").replace("/", ".");
			Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();
			String url = action.execute(request, response);

			if (url.startsWith("redirect:")) {
				String redirectUrl = url.substring("redirect:".length());

				if (redirectUrl.startsWith("/")) {
					response.sendRedirect(request.getContextPath() + redirectUrl);
				} else {
					response.sendRedirect(request.getContextPath() + "/views/" + redirectUrl);
				}

			} else {
				request.getRequestDispatcher("/views/" + url).forward(request, response);
			}

		} catch (Exception e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			e.printStackTrace(out);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
