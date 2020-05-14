package api;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static properties.Constants.TEST_POST_MESSAGE;

public class VkWall extends VkApi {
    final Logger logger = LoggerFactory.getLogger(VkApi.class);

    @Step("Создаем тестовый пост у владельца {ownerId}")
    public Integer addPostOnWall(UserActor userActor, int ownerId) {
        sleep();
        Integer postId;
        try {
            postId = vk.wall()
                    .post(userActor)
                    .message(TEST_POST_MESSAGE)
                    .ownerId(ownerId)
                    .friendsOnly(true)
                    .execute()
                    .getPostId();
            logger.info("Created post with id {}", postId);
            return postId;

        } catch (ClientException | ApiException exception) {
            exception.printStackTrace();
            throw new AssertionError("Creation of new post failed");
        }
    }

    @Step("Убираем тестовый пост {postId} у владельца {ownerId}")
    public void removePostFromWall(UserActor userActor, Integer postId, int ownerId) {
        sleep();
        try {
            vk.wall()
                    .delete(userActor)
                    .ownerId(ownerId)
                    .postId(postId)
                    .execute();
            logger.info("Deleted post with id {}", postId);
        } catch (ClientException | ApiException exception) {
            exception.printStackTrace();
        }
    }
}