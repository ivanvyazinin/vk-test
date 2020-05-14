package test;

import api.VkLikes;
import api.VkWall;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.queries.likes.LikesType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import static env.Configuration.getVkConfig;

public abstract class BaseTest {
    public VkLikes vkLikesApi = new VkLikes();
    public VkWall vkWallApi = new VkWall();
    public UserActor actor = new UserActor(getVkConfig().getUserId(), getVkConfig().getAccessToken());
    public Integer ownerId = getVkConfig().getUserId();

    public Integer likedPostId;
    public Integer notLikedPostId;

    @BeforeSuite
    void checkAuth(){
        vkLikesApi.auth(actor);
    }

    @BeforeClass(groups = { "smoke", "regression" })
    void init() throws Exception {
        likedPostId = vkWallApi.addPostOnWall(actor, ownerId);
        vkLikesApi.addLike(actor, LikesType.POST, ownerId, likedPostId);
        notLikedPostId = vkWallApi.addPostOnWall(actor, ownerId);
    }

    @AfterClass (groups = { "smoke", "regression" })
    void clean(){
        vkWallApi.removePostFromWall(actor, notLikedPostId, ownerId);
        vkWallApi.removePostFromWall(actor, likedPostId, ownerId);
    }
}
