package org.github.Elizaveta.hello;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SpecialJSPTag extends TagSupport {
    private JSPListBean list;

    public JSPListBean getList() {
        return list;
    }

    public void setList(JSPListBean list) {
        this.list = list;
    }

    @Override

    public int doStartTag() throws JspException {
        int size = new Integer(list.getSize());
        try {
            JspWriter out = pageContext.getOut();

            for(int i=0; i<size; i++) {
                out.write("<tr>");
                out.write("<td width=\"70\" valign=\"top\">");
                out.write("<div id=\"logo\">");
                out.write("<a href=\"otheruser?id="+ list.getElement().getSenderID() +"\">");
                out.write("<img src=\"/static/${photos.get("+ list.getElement().getSenderID()+")}\" width=\"50\"></a>");
                out.write("</div>");
                out.write("</td>");
                out.write("<td align=\"left\">");
                out.write(list.getElement().getText());
                out.write("</td>");
                out.write("<td width=15%><font size=\"-1\" color=\"grey\">");
                out.write(list.getElement().getDate());
                out.write("</font></td>");
                out.write("</tr>");
            }

        } catch (IOException e) {

            throw new JspException(e.getMessage());

        }

        return SKIP_BODY;
    }

}
