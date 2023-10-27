package com.Collaboration.MessageService.Entity;

import com.Collaboration.MessageService.Model.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MessageDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID messageId;

    private String message;

    private Long senderId;

    private Long receiverId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Timestamp messageTime;

    private Status adminStatus;
}
