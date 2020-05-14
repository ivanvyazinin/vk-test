package api;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.likes.responses.AddResponse;
import com.vk.api.sdk.objects.likes.responses.DeleteResponse;
import com.vk.api.sdk.objects.likes.responses.GetListResponse;
import com.vk.api.sdk.objects.likes.responses.IsLikedResponse;
import com.vk.api.sdk.queries.likes.LikesType;
import io.qameta.allure.Step;

public class VkLikes extends VkApi {

    @Step("Ставим лайк посту {itemId} владельца {ownerId}")
    public AddResponse addLike(UserActor userActor, LikesType likesType, int ownerId, int itemId) throws ClientException, ApiException{
        sleep();
        return vk.likes()
                .add(userActor, likesType, itemId)
                .ownerId(ownerId)
                .execute();
    }

    @Step("Убираем лайк у поста {itemId} владельца {ownerId}")
    public DeleteResponse deleteLike(UserActor userActor, LikesType likesType, int ownerId, int itemId) throws ClientException, ApiException {
        sleep();
        return vk.likes()
                .delete(userActor, likesType, itemId)
                .ownerId(ownerId)
                .execute();
    }

    @Step("Получаем список лайков у поста {itemId} владельца {ownerId}")
    public GetListResponse getLikes(UserActor userActor, LikesType likesType, int ownerId, int itemId) throws ClientException, ApiException {
        sleep();
        return vk.likes()
                .getList(userActor, likesType)
                .ownerId(ownerId)
                .itemId(itemId)
                .execute();
    }

    @Step("Проверяем стоит ли лайк у поста {itemId} владельца {ownerId}")
    public IsLikedResponse isLiked(UserActor userActor, LikesType likesType, int ownerId, int itemId) throws ClientException, ApiException {
        sleep();
        return vk.likes()
                .isLiked(userActor, likesType, itemId)
                .ownerId(ownerId)
                .execute();
    }
}