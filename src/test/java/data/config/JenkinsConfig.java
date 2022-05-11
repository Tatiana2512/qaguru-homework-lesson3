package data.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configParameters.properties")
public interface JenkinsConfig extends Config {


    String login();
    String password();
    String baseURL();
    String remoteBrowserSize();
    String remoteBrowserURL();
    String remoteConfig();
}
