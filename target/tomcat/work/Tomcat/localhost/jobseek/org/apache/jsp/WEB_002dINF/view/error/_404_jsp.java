/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-02-26 08:26:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view.error;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.jobseek.model.Account;

public final class _404_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("/WEB-INF/tags/header.tag", Long.valueOf(1614319251648L));
    _jspx_dependants.put("/WEB-INF/tags/footer.tag", Long.valueOf(1614318405548L));
    _jspx_dependants.put("/WEB-INF/tags/navbar.tag", Long.valueOf(1614327554057L));
    _jspx_dependants.put("/WEB-INF/tags/script.tag", Long.valueOf(1614318162971L));
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
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
      // /WEB-INF/view/error/404.jsp(8,8) name = userSession type = com.jobseek.model.Account reqTime = true required = false fragment = false deferredValue = false expectedTypeName = java.lang.String deferredMethod = false methodSignature = null
      _jspx_th_tag_005fnavbar_005f0.setUserSession( currentAccount );
      _jspx_th_tag_005fnavbar_005f0.setJspBody(new Helper( 0, _jspx_page_context, _jspx_th_tag_005fnavbar_005f0, null));
      _jspx_th_tag_005fnavbar_005f0.doTag();
      _jsp_instancemanager.destroyInstance(_jspx_th_tag_005fnavbar_005f0);
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"jumbotron\">\n");
      out.write("            <h1>404 Error</h1>\n");
      out.write("            <p>URL: ");
      out.print( request.getAttribute("javax.servlet.forward.request_uri") );
      out.write(" could not be found.</p>\n");
      out.write("            <a class=\"btn btn-primary\" href=\"/jobseek\" role=\"button\">Return to Homepage</a>\n");
      out.write("        </div>\n");
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
