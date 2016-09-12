package org.github.Elizaveta.hello;

import org.github.Elizaveta.hello.dao.Message;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class SpecialJSPTag extends TagSupport {
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override

    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<td width=\"70\" valign=\"top\">");
            out.write("<div id=\"logo\">");
            out.write("<a href=\"otheruser?id=" + message.getSenderID() + "\">");
            out.write("<img src=\"/static/${photos.get(" + message.getSenderID() + ")}\" width=\"50\"></a>");
            out.write("</div>");
            out.write("</td>");
            out.write("<td align=\"left\">");
            out.write(message.getText());
            out.write("</td>");
            out.write("<td width=15%><font size=\"-1\" color=\"grey\">");
            out.write(message.getDate());
            out.write("</font></td>");

        } catch (IOException e) {

            throw new JspException(e.getMessage());

        }

        return SKIP_BODY;
    }

}
