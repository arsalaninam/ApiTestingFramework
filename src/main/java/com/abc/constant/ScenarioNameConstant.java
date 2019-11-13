package com.abc.constant;

public interface ScenarioNameConstant {

    String VALIDATE_STATUS_CODE_404 = "Validate Response Status Code is 404 of API : ";
    String VALIDATE_LIST_OF_ITEM = "Validate List of Items in API : ";
    String VALIDATE_RESPONSE_BODY = "Validate Response Body of API : ";

    String VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON =
            "Validate Response Status Code is 200 and Content Type is JSON of API : ";

    String VALIDATE_EMAIL_FORMAT_IN_COMMENTS_FOR_POSTS_BY_USER =
            "Validate Email Format in Comments for Posts by a User is in proper format ";

    String VALIDATE_URL_FORMAT_IN_PHOTOS_FOR_ALBUMS_ASSOCIATED_WITH_USER =
            "Validate Url Format in Photos for Albums associated with a User is in proper format ";

    String VALIDATE_TOTAL_NUMBER_OF_ALBUMS_ASSOCIATED_WITH_USER =
            "Validate Total Number of Albums associated with a User and Validate Response Body of Albums ";

    String VALIDATE_TOTAL_NUMBER_OF_POSTS_MADE_BY_USER_WITH_RESPONSE_BODY =
            "Validate Total Number of Posts made by an Individual User and Response body of Post is not null ";

    String VALIDATE_TOTAL_NUMBER_OF_TODOS_ASSOCIATED_WITH_USER =
            "Validate Total Number of Todos associated with a User and Validate Response Body of Todos ";
}
