package test;

import com.vk.api.sdk.exceptions.ApiParamException;
import com.vk.api.sdk.objects.likes.responses.IsLikedResponse;
import com.vk.api.sdk.queries.likes.LikesType;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LikesIsTest extends BaseTest {
    @DataProvider(name = "postsList")
    public Object[][] postsList() {
        return new Object[][]{
                {likedPostId, true},
                {notLikedPostId, false},
        };
    }

    @Test(dataProvider = "postsList", groups = { "smoke", "regression" })
    @Description("Проверим, что метод возвращет верный статус залайканности поста")
    public void checkIsLikeMethod(Integer itemId, Boolean likeStatus) throws Exception {
        IsLikedResponse response = vkLikesApi.isLiked(actor, LikesType.POST, ownerId, itemId);
        assertThat(response.isLiked()).as("Проверяем статус залайканности").isEqualTo(likeStatus);
    }

    @Test (groups = {"regression" })
    @Description("Проверим, что метод возвращет ошибку для несуществующего поста")
    public void checkIsLikeMethodNegative() {
        assertThatThrownBy(() -> {
            vkLikesApi.isLiked(actor, LikesType.POST, ownerId, 0);
        })
                .isInstanceOf(ApiParamException.class)
                .hasMessageContaining("One of the parameters specified was missing or invalid");
    }
}