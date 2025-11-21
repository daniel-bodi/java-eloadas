package hu.nje.gfekak.java.eloadas.forex.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author danielbodi
 */
@ConfigurationProperties(prefix = "odana")
public class OdanaConfiguration {

    private String apiUrl;
    private String accountId;
    private String token;

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
