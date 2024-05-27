package com.example.authapi.responses;

public class LoginResponse {
    private String token;
    private long   expiresIn;

    public String getToken() {
        return token;
    }

    /**
     * @return the expiresIn
     */
    public long getExpiresIn() {
        return expiresIn;
    }

    /**
     * @param expiresIn the expiresIn to set
     */
    public void setExpiresIn(final long expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * @param token the token to set
     */
    public void setToken(final String token) {
        this.token = token;
    }

}
