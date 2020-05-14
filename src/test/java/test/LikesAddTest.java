package test;

import com.vk.api.sdk.exceptions.ApiParamException;
import com.vk.api.sdk.objects.likes.responses.AddResponse;
import com.vk.api.sdk.queries.likes.LikesType;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LikesAddTest extends BaseTest {

    @DataProvider(name = "negativeData")
    public Object[][] negativeData() {
        return new Object[][]{
                {ownerId, 0},
                {0, likedPostId},
        };
    }

    @Test (groups = { "smoke", "regression" })
    @Description("Проверим, что можно лайкнуть свой собственный пост")
    public void testAdd() throws Exception {
        AddResponse response = vkLikesApi.addLike(actor, LikesType.POST, ownerId, notLikedPostId);
        assertThat(response.getLikes()).as("Проверяем количество лайков").isEqualTo(1);
    }

    @Test(dataProvider = "negativeData", groups = {"regression" })
    @Description("Проверим, валидацию параметров метода isLiked")
    public void checkValidation(Integer ownerId, Integer itemId) {
        assertThatThrownBy(() -> {
            vkLikesApi.addLike(actor, LikesType.POST, ownerId, itemId);
        })
                .isInstanceOf(ApiParamException.class)
                .hasMessageContaining("One of the parameters specified was missing or invalid");
    }
}