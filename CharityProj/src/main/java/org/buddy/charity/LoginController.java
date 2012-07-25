package org.buddy.charity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.Carrot2Test.FBDirector;
import org.Carrot2Test.Keyword;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
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
		ArrayList<Keyword> keywords;

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
					
					keywords = FBDirector.FetchFBInterests(accessToken);
					String keywordsCSV = StringUtils.collectionToCommaDelimitedString(keywords);		       		
		       		
		       		map.put("matcherKeywords", keywordsCSV);
		       		
		       		for(Keyword each: keywords) {
		       			System.out.println(connectCharityNav(each));
		       			//? How do I print them to the webpage? through jsp?
		       		}
		       		
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

	private byte[] connectCharityNav(Keyword keyword) throws UnsupportedEncodingException {
		String keywordVal = keyword.getKeyword();
		String parsedKeyword = URLEncoder.encode(keywordVal, "UTF-8");
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod("http://www.charitynavigator.org/index.cfm?keyword_list="
				+parsedKeyword+"&Submit2=GO&bay=search.results");

		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
				  new DefaultHttpMethodRetryHandler());
		
		byte[] responseBody = null;
		try {
		      // Execute the method.
		      int statusCode = client.executeMethod(method);

		      if (statusCode != HttpStatus.SC_OK) {
		        System.err.println("Method failed: " + method.getStatusLine());
		      }

		      // Read the response body.
		      responseBody = method.getResponseBody();

		      // Deal with the response.
		      // Use caution: ensure correct character encoding and is not binary data
		      System.out.println(new String(responseBody));

		    } catch (HttpException e) {
		      System.err.println("Fatal protocol violation: " + e.getMessage());
		      e.printStackTrace();
		    } catch (IOException e) {
		      System.err.println("Fatal transport error: " + e.getMessage());
		      e.printStackTrace();
		    } finally {
		      // Release the connection.
		      method.releaseConnection();
		    }  
		return responseBody;
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
