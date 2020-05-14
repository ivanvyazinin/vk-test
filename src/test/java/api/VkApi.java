package api;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class VkApi {
    protected VkApiClient vk;
    private static final long DEFAULT_SLEEP = 334L;

    public VkApi() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
    }

    public void auth(UserActor actor) {
        sleep();
        try {
            vk.account()
                .getProfileInfo(actor)
                .execute();

        } catch(ApiException | ClientException e) {
            e.printStackTrace();
            throw new AssertionError("Auth failed");
        }
    }

    protected void sleep() {
        try {
            Thread.sleep(DEFAULT_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
