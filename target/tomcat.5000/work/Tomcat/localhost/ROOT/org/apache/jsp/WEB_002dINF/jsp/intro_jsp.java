/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.11
 * Generated at: 2017-10-13 14:00:31 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class intro_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("jar:file:/C:/Users/Dan/git/benchwarmer-timemachine/target/tomcat.5000/webapps/expanded/WEB-INF/lib/jstl-1.2.jar!/META-INF/fmt.tld", Long.valueOf(1153399482000L));
    _jspx_dependants.put("/WEB-INF/lib/spring-webmvc-4.3.10.RELEASE.jar", Long.valueOf(1501847214000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1501266464000L));
    _jspx_dependants.put("jar:file:/C:/Users/Dan/git/benchwarmer-timemachine/target/tomcat.5000/webapps/expanded/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153399482000L));
    _jspx_dependants.put("jar:file:/C:/Users/Dan/git/benchwarmer-timemachine/target/tomcat.5000/webapps/expanded/WEB-INF/lib/spring-webmvc-4.3.10.RELEASE.jar!/META-INF/spring.tld", Long.valueOf(1500565206000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fspring_005furl_0026_005fvar_005fvalue_005fnobody;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fspring_005furl_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fspring_005furl_0026_005fvar_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"\" />\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      if (_jspx_meth_spring_005furl_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_spring_005furl_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${benchwarmerCSS}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" rel=\"stylesheet\" />\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${timemachineJS}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"></script>\r\n");
      out.write("<title>Benchwarmer Time Machine</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body id=\"iframe\">\r\n");
      out.write("<br>\r\n");
      out.write("\t<div id=\"introduction\" class=\"intro\">\r\n");
      out.write("\t\t<h4>What would Gretzky's stats look like in today's NHL?\r\n");
      out.write("\t\tHow many points could Crosby have put up in the high-flying 80's?\r\n");
      out.write("\t\tWhere does Price rank among the great goalies of the recent past?</h4>\r\n");
      out.write("\t\t<h4><em>Use the NHL Time Machine to find out!</em></h4>\r\n");
      out.write("\t\t<p><b>Option #1</b> - click 'skaters' or 'goalies' to adjust any individual season since\r\n");
      out.write("\t\t   1987/88 to the production rates of any other season in that timeframe.  Alternatively, choose \"All\" to translate every\r\n");
      out.write("\t\t   available season and see how players from the different eras measure up.</p>\r\n");
      out.write("\t\t<div class=\"list\">\t\r\n");
      out.write("\t\t\t<ol>\r\n");
      out.write("\t\t\t\t<li>Select the season(s) you wish to adjust\r\n");
      out.write("\t\t\t\t<li>Select the season to use as a baseline (production rates)\r\n");
      out.write("\t\t\t\t<li>Set the minimum games played filter\r\n");
      out.write("\t\t\t\t<li>Check 'include' if you wish to display player stats from the baseline season\r\n");
      out.write("\t\t\t\t<li>Click 'Convert'\r\n");
      out.write("\t\t\t</ol>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<p><b>Option #2</b> - click 'player comparator' to compare player's era-adjusted historical performances head-to-head.  \r\n");
      out.write("\t\t<div class=\"list\">\r\n");
      out.write("\t\t\t<ol>\r\n");
      out.write("\t\t\t\t<li>Select the season to use as a baseline (production rates)\r\n");
      out.write("\t\t\t\t<li>Select the two players you wish to compare\r\n");
      out.write("\t\t\t\t<li>Check 'include' if you wish to display each player's individual seasons instead of their career totals\r\n");
      out.write("\t\t\t\t<li>Click 'Convert'\r\n");
      out.write("\t\t\t</ol>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<p>*All table columns can be sorted by clicking on their respective headers.</p>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_spring_005furl_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:url
    org.springframework.web.servlet.tags.UrlTag _jspx_th_spring_005furl_005f0 = (org.springframework.web.servlet.tags.UrlTag) _005fjspx_005ftagPool_005fspring_005furl_0026_005fvar_005fvalue_005fnobody.get(org.springframework.web.servlet.tags.UrlTag.class);
    boolean _jspx_th_spring_005furl_005f0_reused = false;
    try {
      _jspx_th_spring_005furl_005f0.setPageContext(_jspx_page_context);
      _jspx_th_spring_005furl_005f0.setParent(null);
      // /WEB-INF/jsp/intro.jsp(11,0) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_spring_005furl_005f0.setValue("/resources/blue/css/style.css");
      // /WEB-INF/jsp/intro.jsp(11,0) name = var type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_spring_005furl_005f0.setVar("benchwarmerCSS");
      int[] _jspx_push_body_count_spring_005furl_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_spring_005furl_005f0 = _jspx_th_spring_005furl_005f0.doStartTag();
        if (_jspx_th_spring_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_spring_005furl_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_spring_005furl_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_spring_005furl_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fspring_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_spring_005furl_005f0);
      _jspx_th_spring_005furl_005f0_reused = true;
    } finally {
      if (!_jspx_th_spring_005furl_005f0_reused) {
        _jspx_th_spring_005furl_005f0.release();
        _jsp_getInstanceManager().destroyInstance(_jspx_th_spring_005furl_005f0);
      }
    }
    return false;
  }

  private boolean _jspx_meth_spring_005furl_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:url
    org.springframework.web.servlet.tags.UrlTag _jspx_th_spring_005furl_005f1 = (org.springframework.web.servlet.tags.UrlTag) _005fjspx_005ftagPool_005fspring_005furl_0026_005fvar_005fvalue_005fnobody.get(org.springframework.web.servlet.tags.UrlTag.class);
    boolean _jspx_th_spring_005furl_005f1_reused = false;
    try {
      _jspx_th_spring_005furl_005f1.setPageContext(_jspx_page_context);
      _jspx_th_spring_005furl_005f1.setParent(null);
      // /WEB-INF/jsp/intro.jsp(12,0) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_spring_005furl_005f1.setValue("/resources/js/timemachine.js");
      // /WEB-INF/jsp/intro.jsp(12,0) name = var type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_spring_005furl_005f1.setVar("timemachineJS");
      int[] _jspx_push_body_count_spring_005furl_005f1 = new int[] { 0 };
      try {
        int _jspx_eval_spring_005furl_005f1 = _jspx_th_spring_005furl_005f1.doStartTag();
        if (_jspx_th_spring_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_spring_005furl_005f1[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_spring_005furl_005f1.doCatch(_jspx_exception);
      } finally {
        _jspx_th_spring_005furl_005f1.doFinally();
      }
      _005fjspx_005ftagPool_005fspring_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_spring_005furl_005f1);
      _jspx_th_spring_005furl_005f1_reused = true;
    } finally {
      if (!_jspx_th_spring_005furl_005f1_reused) {
        _jspx_th_spring_005furl_005f1.release();
        _jsp_getInstanceManager().destroyInstance(_jspx_th_spring_005furl_005f1);
      }
    }
    return false;
  }
}
