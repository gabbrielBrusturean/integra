package integra.backend.reminder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.reminders.enabled", havingValue = "true", matchIfMissing = true)
public class EventReminderScheduler {
    private static final Logger log = LoggerFactory.getLogger(EventReminderScheduler.class);

    private final EventReminderService reminderService;

    public EventReminderScheduler(EventReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @Scheduled(cron = "${app.reminders.cron:0 0 9 * * *}")
    public void sendDailyReminders() {
        int sent = reminderService.sendScheduledReminders();
        log.info("Daily reminder job completed, emails sent={}", sent);
    }
}
