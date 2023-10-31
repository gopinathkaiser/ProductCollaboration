package com.Collaboration.MessageService.DTO;

import com.Collaboration.MessageService.Model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {

    private Status status;
}
