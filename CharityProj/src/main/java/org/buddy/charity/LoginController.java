package org.buddy.charity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest request) {
		Map<String, Object> map = model.asMap();

		HttpSession session = request.getSession();
		String code = request.getParameter("code");

		if (StringUtils.hasText(code)) {
			String authURL = Facebook.getAuthURL(code);
			try {
				URL url = new URL(authURL);
				String result = readURL(url);
				String accessToken = null;
				Integer expires = null;
				String[] pairs = result.split("&");
				for (String pair : pairs) {
					String[] kv = pair.split("=");
					if (kv.length != 2) {
						throw new RuntimeException("Unexpected auth response");
					} else {
						if (kv[0].equals("access_token")) {
							accessToken = kv[1];
						}
						if (kv[0].equals("expires")) {
							expires = Integer.valueOf(kv[1]);
						}
					}
				}
				if (accessToken != null && expires != null) {
					session.setAttribute("accessToken", accessToken);
					session.setAttribute("tokenExpires", expires);
					
					map.put("accessToken", accessToken);
					map.put("tokenExpires", expires);
					
					// res.sendRedirect("http://www.onmydoorstep.com.au/");
				} else {
					throw new RuntimeException(
							"Access token and expires not found");
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			map.put("facebookLoginURL", Facebook.getLoginRedirectURL());
			return "login";
		}

		return "home";
	}

	private String readURL(URL url) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = url.openStream();
		int r;
		while ((r = is.read()) != -1) {
			baos.write(r);
		}
		return new String(baos.toByteArray());
	}
}
