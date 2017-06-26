package tp.Wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginWrapper {
    @JsonProperty
    String sessionID;

    public LoginWrapper(String SessionID){ this.sessionID = SessionID; }

    public String getSessionID() { return sessionID; }
    public void setSessionID(String sessionID) { this.sessionID = sessionID; }
}
