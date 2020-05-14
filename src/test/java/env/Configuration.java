package env;

import org.aeonbits.owner.ConfigFactory;

public class Configuration {
    public static VkConfig getVkConfig(){
        return ConfigFactory.newInstance().create(VkConfig.class);
    }
}
