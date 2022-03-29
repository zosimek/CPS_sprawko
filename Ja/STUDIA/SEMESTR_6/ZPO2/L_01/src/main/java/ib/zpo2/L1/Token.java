package ib.zpo2.L1;

// The token is converted into an object form (Token) which will contain all
// information from the token and facilitate the use of this information.
public class Token {
    private String header;
    private String payload;

    public Token(String header, String payload) {
        this.header = header;
        this.payload = payload;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
