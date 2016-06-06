<%@ page language="java"%>
<%@ page import="org.skyve.wildcat.web.UserAgent"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 
						request.getServerPort() + request.getContextPath() + "/";
	boolean mobile = UserAgent.getType(request).isMobile();
	String referer = request.getHeader("Referer");
%>
<html>
	<head>
		<title>Biz Hub: Logged out</title>
		<base href="<%=basePath%>" />

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="private,no-cache,no-store" />
		<meta http-equiv="expires" content="0" />

		<% if (mobile) { %>
			<meta name="format-detection" content="telephone=no" />
			<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" />
		<% } %>

		<link rel="icon" type="image/png" href="images/window/BizHub16.png" />
		<link rel="stylesheet" type="text/css" href="css/basic-min.css" />
	</head>
	<body>
		<table class="logo" align="center">
			<tr height="150px">
				<td>
					<img src="images/bizhub_logo4.jpg" alt="Get Organized" />
				</td>
			</tr>
		</table>
		<%request.getSession().invalidate();

			%>


		<form>
			<table align="center">
				<tr>
					<td>
						<div class="x-box" style="width:100%;">
							<div class="x-box-tl">
								<div class="x-box-tr">
									<div class="x-box-tc">
									</div>
								</div>
							</div>
							<div class="x-box-ml">
								<div class="x-box-mr">
									<div class="x-box-mc">
										<table align="center">
											<tr>
												<td align=center>
													<strong>
														Logged Out
													</strong>
													<br>
													<span style="font-size:14">You have logged out of Biz Hub.</span>
													<br>
													<table>
														<tr>
															<td background="images/blankbutton2.jpg" height=35 width=70 align=center>
																<% if (referer == null) { %>
																	<a href="<%=request.getContextPath()%><%=org.skyve.util.Util.getHomeUri()%>">Log In</a>
																<% } else { %>
																	<a href="<%=referer%>">Log In</a>
																<% } %>
															</td>
														</tr>
													</table>
							
												</td>
											</tr>
										</table>
										<div></div>
									</div>
								</div>
							</div>
							<div class="x-box-bl">
								<div class="x-box-br">
									<div class="x-box-bc">
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
