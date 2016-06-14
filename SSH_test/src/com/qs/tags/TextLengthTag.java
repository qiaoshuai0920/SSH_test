package com.qs.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class TextLengthTag extends BodyTagSupport {

    /**
     *
     */
    private static final long serialVersionUID = 425381814168752507L;

    /**
     * Logger for this class
     */
    private static final Logger LOG = Logger.getLogger(TextLengthTag.class);

    /**
     * 文本长度限制
     */
    private int maxLength;

    
    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    /*
    * @see javax.servlet.jsp.tagext.BodyTagSupport#doAfterBody()
    */
    
    public int doAfterBody() throws JspException {
        if (bodyContent != null) {
            try {
                String content = bodyContent.getString();
                if (StringUtils.isBlank(content)) {
                    return SKIP_BODY;
                }
                content = StringEscapeUtils.unescapeHtml(content);
                if (maxLength > 0) {
                    int end = maxLength > content.length() ? content.length() : maxLength;
                    content = content.substring(0, end)
                            + (end < content.length() ? "..." : "");
                }
                bodyContent.clearBody();
                bodyContent.append(content);
                bodyContent.writeOut(bodyContent.getEnclosingWriter());
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
                throw new JspException(e);
            }
        }
        return SKIP_BODY;
    }


    /**
     * @return the maxLength
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * @param maxLength the maxLength to set
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

}
