package com.mouridedev.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
@Slf4j

public class NotificationServiceImpl implements NotificationService {

  private final NotificationRepository notificationRepository;
  @Override
  public void sendNotification(NotificationRequest notificationRequest) {
    Notification notification = Notification.builder()
        .toCustomerEmail(notificationRequest.getToCustomerEmail())
        .toCustomerId(notificationRequest.getToCustomerId())
        .message(notificationRequest.getMessage())
        .sender("mouridedev")
        .sentAt(LocalDateTime.now())
        .build();
    notificationRepository.saveAndFlush(notification);
  }
}
