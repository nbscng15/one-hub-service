package com.benifex.one_hub_service.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class EmployeeResponseDTO extends BaseResponse {

	private Object data;

}
