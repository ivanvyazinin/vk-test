package test;

import com.vk.api.sdk.exceptions.ApiParamException;
import com.vk.api.sdk.objects.likes.responses.GetListResponse;
import com.vk.api.sdk.queries.likes.LikesType;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LikesGetTest extends BaseTest {

    @Test (groups = { "smoke", "regression" })
    @Description("Проверим, что можно получить список тех, кто лайкнул пост")
    public void checkGetList() throws Exception {
        GetListResponse response = vkLikesApi.getLikes(actor, LikesType.POST, ownerId, likedPostId);
        assertThat(response.getCount()).as("Проверяем количество лайков").isEqualTo(1);
    }

    @Test (groups = {"regression" })
    @Description("Проверим, что нельзя получить список лайков у несуществующего поста")
    public void checkGetListFromUnknownPost() {
        assertThatThrownBy(() -> {
            vkLikesApi.getLikes(actor, LikesType.POST, ownerId, 0);
        })
                .isInstanceOf(ApiParamException.class)
                .hasMessageContaining("One of the parameters specified was missing or invalid");

    }
}