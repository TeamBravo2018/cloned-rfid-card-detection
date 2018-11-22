package com.cit.common.om.access.token;

/**
 * Class to repreent an access token
 */
public abstract class Token {

    private String tokenId;

    public Token() {
    }

    public Token(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        return tokenId != null ? tokenId.equals(token.tokenId) : token.tokenId == null;
    }

    @Override
    public int hashCode() {
        return tokenId != null ? tokenId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenId='" + tokenId + '\'' +
                '}';
    }
}
