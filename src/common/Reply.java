package common;

import java.io.Serializable;

public class Reply implements Serializable
{

    private CheckCode code;
    private String body;

    public Reply(CheckCode code, String body)
    {
        this.code = code;
        this.body = body;
    }

    public CheckCode getReplyCode()
    {
        return code;
    }

    public String getReplyBody()
    {
        return body;
    }

}
