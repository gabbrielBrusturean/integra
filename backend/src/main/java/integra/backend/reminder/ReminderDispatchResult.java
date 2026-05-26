package integra.backend.reminder;

public record ReminderDispatchResult(Long eventId, String eventTitle, int recipientCount, int sentCount,
        int failedCount, boolean alreadySent, boolean skippedNoRecipients) {
}
