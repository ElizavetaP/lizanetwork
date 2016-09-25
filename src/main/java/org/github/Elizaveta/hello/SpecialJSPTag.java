package org.github.Elizaveta.hello;

import org.github.Elizaveta.hello.dao.MessageWithAvatar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class SpecialJSPTag extends TagSupport {
    private MessageWithAvatar messageWithAvatar;

    public MessageWithAvatar getMessageWithAvatar() {
        return messageWithAvatar;
    }

    public void setMessageWithAvatar(MessageWithAvatar messageWithAvatar) {
        this.messageWithAvatar = messageWithAvatar;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<td width=\"70\" valign=\"top\">");
            out.write("<div id=\"logo\">");
            out.write("<a href=\"otheruser?id=" + messageWithAvatar.getMessage().getSenderID() + "\">");
            out.write("<img src=\"/static/" + messageWithAvatar.getAvatar() + "\" width=\"50\"></a>");
            out.write("</div>");
            out.write("</td>");
            out.write("<td align=\"left\">");
            out.write(messageWithAvatar.getMessage().getText());
            out.write("</td>");
            out.write("<td width=15%><font size=\"-1\" color=\"grey\">");
            out.write(messageWithAvatar.getMessage().getDate());
            out.write("</font></td>");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }

}
