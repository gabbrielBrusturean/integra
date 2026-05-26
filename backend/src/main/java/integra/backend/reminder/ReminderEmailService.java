package integra.backend.reminder;

import java.time.format.DateTimeFormatter;

import integra.backend.event.model.Event;
import integra.backend.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class ReminderEmailService {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    private final JavaMailSender mailSender;
    private final String fromAddress;

    public ReminderEmailService(JavaMailSender mailSender,
            @Value("${app.reminders.from:notifications@integra.local}") String fromAddress) {
        this.mailSender = mailSender;
        this.fromAddress = fromAddress;
    }

    public void sendReminder(User user, Event event) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromAddress);
            helper.setTo(user.getEmail());
            helper.setSubject("Reminder: You are registered for " + event.getTitle());
            helper.setText(buildTextBody(user, event), buildHtmlBody(user, event));
            mailSender.send(message);
        } catch (MessagingException | MailException exception) {
            throw new IllegalStateException("Failed to send reminder email to " + user.getEmail(), exception);
        }
    }

    private String buildTextBody(User user, Event event) {
        return "Hello " + user.getFirstName() + ",\n\n"
                + "This is a reminder that you are registered for the following event:\n\n" + "Event: "
                + event.getTitle() + "\n" + "Date: " + event.getStartAt().format(DATE_FORMAT) + ",\n" + "Time: "
                + event.getStartAt().format(TIME_FORMAT) + "\n" + "Location: " + event.getLocation() + "\n\n"
                + "See you there!";
    }

    private String buildHtmlBody(User user, Event event) {
        return "<html><body style='font-family:Arial,sans-serif;'>" + "<p>Hello " + escape(user.getFirstName())
                + ",</p>" + "<p>This is a reminder that you are registered for the following event:</p>" + "<ul>"
                + "<li><strong>Event:</strong> " + escape(event.getTitle()) + "</li>" + "<li><strong>Date:</strong> "
                + event.getStartAt().format(DATE_FORMAT) + "</li>" + "<li><strong>Time:</strong> "
                + event.getStartAt().format(TIME_FORMAT) + "</li>" + "<li><strong>Location:</strong> "
                + escape(event.getLocation()) + "</li>" + "</ul>" + "<p>See you there!</p>" + "</body></html>";
    }

    private String escape(String value) {
        return value == null ? "" : value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
