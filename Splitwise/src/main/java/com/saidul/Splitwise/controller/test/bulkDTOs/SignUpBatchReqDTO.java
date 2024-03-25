package com.saidul.Splitwise.controller.test.bulkDTOs;

import com.saidul.Splitwise.dto.UserSignUpRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignUpBatchReqDTO {
    private List<UserSignUpRequestDTO> singUpDTOBatch;
}
