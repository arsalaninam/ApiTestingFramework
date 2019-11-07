package com.abc.constant;

public interface ServiceConstant {

    String BASE_URL = "baseURL";
    String POSTS_ENDPOINT = "postsEndpoint";
    String COMMENTS_ENDPOINT = "commentsEndpoint";
    String ALBUMS_ENDPOINT = "albumsEndpoint";
    String PHOTOS_ENDPOINT = "photosEndpoint";
    String TODOS_ENDPOINT = "todosEndpoint";
    String USERS_ENDPOINT = "usersEndpoint";

    String POSTS_ENDPOINT_QUERY_PARAM_USERS_ID = "postsEndpointWithUserIdAsQueryParam";
    String COMMENTS_ENDPOINT_QUERY_PARAM_POST_ID = "commentsEndpointWithPostIdAsQueryParam";
    String ALBUMS_ENDPOINT_QUERY_PARAM_USER_ID = "albumsEndpointWithUserIdAsQueryParam";
    String PHOTOS_ENDPOINT_QUERY_PARAM_ALBUM_ID = "photosEndpointWithAlbumIdAsQueryParam";
    String TODOS_ENDPOINT_QUERY_PARAM_USER_ID = "todosEndpointWithUserIdAsQueryParam";
}
