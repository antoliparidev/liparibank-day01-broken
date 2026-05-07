package com.lipari.bank.account;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    private final NotificationService notificationService;

    public AlertService(@Qualifier("emailNotificationService") NotificationService emailNotificationService) {
        this.notificationService = emailNotificationService;
    }

    public void raiseAlert(String accountId, String reason) {
        notificationService.notify(accountId, "ALERT: " + reason);
    }
}
