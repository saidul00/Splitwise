package com.saidul.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddFriendRequestDTO {
    private Integer userId;
    private String friendEmail;
}
