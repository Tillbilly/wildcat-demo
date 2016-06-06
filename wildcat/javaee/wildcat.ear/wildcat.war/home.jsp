<%@ page language="java"%>
<%@ page import="java.security.Principal"%>
<%@ page import="javax.servlet.http.Cookie"%>
<%@ page import="org.skyve.CORE"%>
<%@ page import="org.skyve.metadata.router.UxUiSelector"%>
<%@ page import="org.skyve.metadata.user.User"%>
<%@ page import="org.skyve.metadata.view.View.ViewType"%>
<%@ page import="org.skyve.util.Util"%>
<%@ page import="org.skyve.web.WebAction"%>
<%@ page import="org.skyve.wildcat.metadata.repository.router.Router"%>
<%@ page import="org.skyve.wildcat.metadata.repository.router.RouteCriteria"%>
<%@ page import="org.skyve.wildcat.persistence.AbstractPersistence"%>
<%@ page import="org.skyve.wildcat.web.faces.FacesUtil"%>
<%@ page import="org.skyve.wildcat.web.AbstractWebContext"%>
<%@ page import="org.skyve.wildcat.web.WebUtil"%>

<%
	String customerName = request.getParameter(AbstractWebContext.CUSTOMER_COOKIE_NAME);
	Cookie cookie = new Cookie(AbstractWebContext.CUSTOMER_COOKIE_NAME, 
								(customerName == null) ? "" : customerName);
	cookie.setPath("/");
	if (customerName == null) {
		cookie.setMaxAge(0); // remove the cookie
	}
	response.addCookie(cookie);

	String a = request.getParameter("a"); // action
	String m = request.getParameter("m"); // module
	String d = request.getParameter("d"); // document
	String q = request.getParameter("q"); // query
	String i = request.getParameter("i"); // id

	// NB Some app servers can detect if a welcome URL is secured and will forward to the login form.
	// Some do not, if we make it here without a principal, then we should redirect directly to home.jsp
	Principal userPrincipal = request.getUserPrincipal();
	if (userPrincipal == null) { // some welcome files send redirect, some server-side forward
		StringBuilder sb = new StringBuilder(64);
		sb.append(Util.getHomeUrl()).append("home.jsp");
		if ((m != null) && (d != null)) {
			sb.append("?m=").append(m);
			if (d != null) {
				sb.append("&d=").append(d);
			}
			if (a != null) {
				sb.append("&a").append(a);
			}
			if (q != null) {
				sb.append("&q").append(q);
			}
			if (i != null) {
				sb.append("&i=").append(i);
			}
		}
		response.sendRedirect(response.encodeRedirectURL(sb.toString()));
	}
	else if (customerName != null) {
		response.sendRedirect(response.encodeRedirectURL(Util.getHomeUrl()));
	}
	else {
		// Get the user
		User user = (User) request.getSession().getAttribute(AbstractWebContext.USER_SESSION_ATTRIBUTE_NAME);
		if (user == null) { // if the user is not established yet (but we've logged in...)
			AbstractPersistence persistence = AbstractPersistence.get();
			try {
				persistence.begin();
				user = WebUtil.processUserPrincipalForRequest(request, userPrincipal.getName(), true);
			}
			finally {
				if (persistence != null) {
					persistence.commit(true);
				}
			}
		}

		// Set the UX/UI
		Router router = CORE.getRepository().getRouter();
		String uxui = ((UxUiSelector) router.getUxuiSelector()).select(request);
		request.setAttribute(FacesUtil.UX_UI_KEY, uxui);
		
		// Determine the route
		RouteCriteria criteria = new RouteCriteria();
		WebAction webAction = null;
		criteria.setCustomerName(user.getCustomerName());
		criteria.setDataGroupId(user.getDataGroupId());
		criteria.setDocumentName(d);
		criteria.setModuleName(m);
		criteria.setQueryName(q);
		criteria.setUserId(user.getId());
		if (a != null) {
			webAction = WebAction.valueOf(a);
			criteria.setWebAction(webAction);
		}
		if (WebAction.e.equals(webAction)) { // editing
			criteria.setViewType((i == null) ? ViewType.create : ViewType.edit);
		}
		String outcomeUrl = router.getOutcomeUrl(uxui, criteria);
		if (outcomeUrl == null) {
			throw new ServletException("The route criteria " + criteria + " for uxui " + uxui + " did not produce an outcome URL");
		}
			
		// forward
		request.getRequestDispatcher(outcomeUrl).forward(request, response);
	}
%>
