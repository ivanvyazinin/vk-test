package env;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:vk.properties"})
public interface VkConfig extends Config{
    @Key("access_token")
    String getAccessToken();

    @Key("user_id")
    Integer getUserId();
}