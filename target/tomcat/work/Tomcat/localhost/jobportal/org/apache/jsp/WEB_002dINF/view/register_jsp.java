/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-02-25 16:52:29 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import com.cafe.model.Account;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("/WEB-INF/tags/header.tag", Long.valueOf(1614222408624L));
    _jspx_dependants.put("/WEB-INF/tags/footer.tag", Long.valueOf(1614186143000L));
    _jspx_dependants.put("/WEB-INF/tags/navbar.tag", Long.valueOf(1614269595955L));
    _jspx_dependants.put("/WEB-INF/tags/script.tag", Long.valueOf(1614222729780L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    ");
      if (_jspx_meth_tag_005fheader_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("    <body>\n");
      out.write("        ");
 Account currentAccount = (Account) session.getAttribute("currentAccount"); 
      out.write("\n");
      out.write("        ");
      //  tag:navbar
      org.apache.jsp.tag.webnavbar_tag _jspx_th_tag_005fnavbar_005f0 = (new org.apache.jsp.tag.webnavbar_tag());
      _jsp_instancemanager.newInstance(_jspx_th_tag_005fnavbar_005f0);
      _jspx_th_tag_005fnavbar_005f0.setJspContext(_jspx_page_context);
      // /WEB-INF/view/register.jsp(9,8) name = userSession type = com.cafe.model.Account reqTime = true required = false fragment = false deferredValue = false expectedTypeName = java.lang.String deferredMethod = false methodSignature = null
      _jspx_th_tag_005fnavbar_005f0.setUserSession( currentAccount );
      _jspx_th_tag_005fnavbar_005f0.setJspBody(new Helper( 0, _jspx_page_context, _jspx_th_tag_005fnavbar_005f0, null));
      _jspx_th_tag_005fnavbar_005f0.doTag();
      _jsp_instancemanager.destroyInstance(_jspx_th_tag_005fnavbar_005f0);
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"jumbotron\">\n");
      out.write("            <h1>Register</h1>\n");
      out.write("            <p>Please enter your username and password.</p>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row justify-content-md-center\">\n");
      out.write("                <div class=\"myform form \">\n");
      out.write("                  <form action=\"register\" method=\"post\">\n");
      out.write("                     <div class=\"form-group\">\n");
      out.write("                        <label for=\"username\">Username</label>\n");
      out.write("                        <input type=\"text\" id=\"username\" name=\"username\" class=\"form-control\" placeholder=\"Enter Username\" required>\n");
      out.write("                     </div>\n");
      out.write("                     <div class=\"form-group\">\n");
      out.write("                        <label for=\"password\">Password</label>\n");
      out.write("                        <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" placeholder=\"Enter Password\" required>\n");
      out.write("                     </div>\n");
      out.write("                     <div class=\"form-group\">\n");
      out.write("                         <label for=\"confirm\">Confirm Password</label>\n");
      out.write("                         <input type=\"password\" id=\"confirm\" name=\"confirm\" class=\"form-control\" placeholder=\"Confirm Password\" required>\n");
      out.write("                     </div>\n");
      out.write("                     <div class=\"form-group\">\n");
      out.write("                         <label for=\"educationSelect\">Select your highest level of education</label>\n");
      out.write("                         <select class=\"form-control\" id=\"educationSelect\" name=\"educationLevel\" size=\"3\">\n");
      out.write("                             <option value=\"primary\" selected>Primary</option>\n");
      out.write("                             <option value=\"secondary\">Secondary</option>\n");
      out.write("                             <option value=\"tertiary\">Short-cycle Tertiary</option>\n");
      out.write("                             <option value=\"bachelor\">Bachelor's or equivalent</option>\n");
      out.write("                             <option value=\"master\">Master's or equivalent</option>\n");
      out.write("                             <option value=\"doctoral\">Doctoral or equivalent</option>\n");
      out.write("                         </select>\n");
      out.write("                     </div>\n");
      out.write("                     <div class=\"form-group\">\n");
      out.write("                         <label for=\"school\">School</label>\n");
      out.write("                         <input type=\"text\" id=\"school\" name=\"school\" class=\"form-control\" placeholder=\"Enter the name of your School\">\n");
      out.write("                     </div>\n");
      out.write("                     <div class=\"form-group\">\n");
      out.write("                         <label for=\"yearSelect\">Year Graduated</label>\n");
      out.write("                         <select class=\"form-control\" id=\"yearSelect\" name=\"yearGraduated\" size=\"3\">\n");
      out.write("                             ");
 for(int i = 2021; i >= 1900; i--) { 
      out.write("\n");
      out.write("                                 <option value=");
      out.print( i );
      out.write(" selected>");
      out.print( i );
      out.write("</option>\n");
      out.write("                             ");
 } 
      out.write("\n");
      out.write("                         </select>\n");
      out.write("                     </div>\n");
      out.write("\n");
      out.write("                     ");

                        String registerError = (String) request.getAttribute("registerError");
                        if(registerError != null) {
                            out.println("<p>" + registerError + "</p>");
                        }
                     
      out.write("\n");
      out.write("\n");
      out.write("                     <div class=\"form-group\">\n");
      out.write("                        <p class=\"text-center\">By registering, you accept our <a href=\"#\">Terms Of Use</a></p>\n");
      out.write("                     </div>\n");
      out.write("                     <div class=\"col-md-12 text-center\">\n");
      out.write("                        <button type=\"submit\" class=\" btn btn-block btn-primary\">Register</button>\n");
      out.write("                     </div>\n");
      out.write("                     <hr>\n");
      out.write("                     <div class=\"form-group\">\n");
      out.write("                        <p class=\"text-center\">Have an account? <a href=\"./login\">Login here</a></p>\n");
      out.write("                     </div>\n");
      out.write("                  </form>\n");
      out.write("              </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <br>\n");
      out.write("\n");
      out.write("        ");
      if (_jspx_meth_tag_005ffooter_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("        ");
      if (_jspx_meth_tag_005fscript_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_tag_005fheader_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tag:header
    org.apache.jsp.tag.webheader_tag _jspx_th_tag_005fheader_005f0 = (new org.apache.jsp.tag.webheader_tag());
    _jsp_instancemanager.newInstance(_jspx_th_tag_005fheader_005f0);
    _jspx_th_tag_005fheader_005f0.setJspContext(_jspx_page_context);
    _jspx_th_tag_005fheader_005f0.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tag_005fheader_005f0);
    return false;
  }

  private boolean _jspx_meth_tag_005ffooter_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tag:footer
    org.apache.jsp.tag.webfooter_tag _jspx_th_tag_005ffooter_005f0 = (new org.apache.jsp.tag.webfooter_tag());
    _jsp_instancemanager.newInstance(_jspx_th_tag_005ffooter_005f0);
    _jspx_th_tag_005ffooter_005f0.setJspContext(_jspx_page_context);
    _jspx_th_tag_005ffooter_005f0.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tag_005ffooter_005f0);
    return false;
  }

  private boolean _jspx_meth_tag_005fscript_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tag:script
    org.apache.jsp.tag.webscript_tag _jspx_th_tag_005fscript_005f0 = (new org.apache.jsp.tag.webscript_tag());
    _jsp_instancemanager.newInstance(_jspx_th_tag_005fscript_005f0);
    _jspx_th_tag_005fscript_005f0.setJspContext(_jspx_page_context);
    _jspx_th_tag_005fscript_005f0.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tag_005fscript_005f0);
    return false;
  }

  private class Helper
      extends org.apache.jasper.runtime.JspFragmentHelper
  {
    private javax.servlet.jsp.tagext.JspTag _jspx_parent;
    private int[] _jspx_push_body_count;

    public Helper( int discriminator, javax.servlet.jsp.JspContext jspContext, javax.servlet.jsp.tagext.JspTag _jspx_parent, int[] _jspx_push_body_count ) {
      super( discriminator, jspContext, _jspx_parent );
      this._jspx_parent = _jspx_parent;
      this._jspx_push_body_count = _jspx_push_body_count;
    }
    public void invoke0( javax.servlet.jsp.JspWriter out ) 
      throws java.lang.Throwable
    {
      out.write("\n");
      out.write("        ");
      return;
    }
    public void invoke( java.io.Writer writer )
      throws javax.servlet.jsp.JspException
    {
      javax.servlet.jsp.JspWriter out = null;
      if( writer != null ) {
        out = this.jspContext.pushBody(writer);
      } else {
        out = this.jspContext.getOut();
      }
      try {
        Object _jspx_saved_JspContext = this.jspContext.getELContext().getContext(javax.servlet.jsp.JspContext.class);
        this.jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,this.jspContext);
        switch( this.discriminator ) {
          case 0:
            invoke0( out );
            break;
        }
        jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,_jspx_saved_JspContext);
      }
      catch( java.lang.Throwable e ) {
        if (e instanceof javax.servlet.jsp.SkipPageException)
            throw (javax.servlet.jsp.SkipPageException) e;
        throw new javax.servlet.jsp.JspException( e );
      }
      finally {
        if( writer != null ) {
          this.jspContext.popBody();
        }
      }
    }
  }
}
