package com.Collaboration.MessageService.DTO;

import com.Collaboration.MessageService.Model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDTO {

    private String message;

    private Long senderId;

    private Long receiverId;
}
