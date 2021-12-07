package com.gmail.olgabots.session.simple.example;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String encodeURL = response.encodeURL(request.getContextPath())+"/controller";
		request.setAttribute("encodeURL", encodeURL);
		System.out.println("encodeURL " + encodeURL);

		request.setAttribute("sessionId", request.getSession().getId());
		System.out.println("sessionId " + request.getSession().getId());

		saveCookiesToAttributes(request, response);

		saveParametersToSession(request);
		setSessionVariablesAsMapAttribute(request, response);
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	private void saveCookiesToAttributes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			Map<String, String> cookieValues = new HashMap<String, String>();
			for (Cookie cookie : cookies) {
				cookieValues.put(cookie.getName(), cookie.getValue());
			}

			request.setAttribute("cookies", cookieValues);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

	private void saveParametersToSession(HttpServletRequest request) {
		String varName = request.getParameter("var_name");
		String varValue = request.getParameter("var_value");

		if (varName != null && !varName.isEmpty()) {
			request.getSession().setAttribute(varName, varValue);
		}
	}

	private void setSessionVariablesAsMapAttribute(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Enumeration<String> attributeNames = request.getSession().getAttributeNames();
		Map<String, String> sessionAttributes = new HashMap<String, String>();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attribute = request.getSession().getAttribute(attributeName);
			if (attribute instanceof String) {
				sessionAttributes.put(attributeName, (String) attribute);
			}
		}

		request.setAttribute("session_attributes", sessionAttributes);
	}

}
