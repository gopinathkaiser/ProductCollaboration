package com.Collaboration.MessageService.DAO;

import com.Collaboration.MessageService.Entity.MessageDetails;
import com.Collaboration.MessageService.Model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageServiceRepo extends JpaRepository<MessageDetails, UUID> {

    List<MessageDetails> findAllByAdminStatus(Status status);

    List<MessageDetails> findAllBySenderIdAndReceiverIdAndAdminStatusOrderByMessageTimeDesc(Long sId,Long rId,Status status);
}
