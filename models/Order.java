package kg.megacom.miniTinder.models;

public class Order {
    private Long id;
    private User senderId;
    private User recipientId;
    private String message;
    private Boolean match;

    public Order() {
    }

    public Order(Long id, User senderId, User recipientId, String message, Boolean match) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.message = message;
        this.match = match;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSenderId() {
        return senderId;
    }

    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }

    public User getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(User recipientId) {
        this.recipientId = recipientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getMatch() {
        return match;
    }

    public void setMatch(Boolean match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "ID заявки: " + id + "\n" +
                "ID отправителя: " + senderId + "\n" +
                "ID получателя: " + recipientId + "\n" +
                "Сообщение: " + message + "\n" +
                "Совпадение: " + match + "\n----------";
    }
}
