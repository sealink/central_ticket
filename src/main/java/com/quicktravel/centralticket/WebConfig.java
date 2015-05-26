package com.quicktravel.centralticket;

public class WebConfig {

  private Integer port = 443;
  private String keystore = null;
  private String password = null;

  public WebConfig(Integer port, String keystore, String password) {
    this.port = port;
    this.keystore = keystore;
    this.password = password;
  }

  public WebConfig(Integer port) {
    this(port, null, null);
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public Boolean isSecure() {
    return (getKeystore() != null && getPassword() != null);
  }

  public String getKeystore() {
    return keystore;
  }

  public void setKeystore(String keystore) {
    this.keystore = keystore;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
