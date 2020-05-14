package test;

import com.vk.api.sdk.exceptions.ApiAccessException;
import com.vk.api.sdk.objects.likes.responses.DeleteResponse;
import com.vk.api.sdk.queries.likes.LikesType;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LikesDeleteTest extends BaseTest {

    @Test (groups = { "smoke", "regression" })
    @Description("Проверим, что лайк можно убрать с поста")
    public void checkLikeRemoving() throws Exception{
        DeleteResponse response = vkLikesApi.deleteLike(actor, LikesType.POST, ownerId, likedPostId);
        assertThat(response.getLikes()).as("Проверяем количество лайков").isEqualTo(0);
    }

    @Test (groups = {"regression" })
    @Description("Проверим, что нельзя удалить лайк, если его нет")
    public void checkLikeRemovingNegative() {
        assertThatThrownBy(() -> {
            vkLikesApi.deleteLike(actor, LikesType.POST, ownerId, notLikedPostId);
        })
                .isInstanceOf(ApiAccessException.class)
                .hasMessageContaining("Access denied");

    }
}