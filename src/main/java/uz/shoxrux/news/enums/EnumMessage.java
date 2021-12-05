package uz.shoxrux.news.enums;

public enum EnumMessage {
    NOT_NULL_LIST("List must not be null"),
    NOT_FOUND("User not found"),
    SENDER_NOT("Sender not found"),
    SAVE_ALL("All news successfully saved"),
    SUCCESS_APP("News successfully approved"),
    UPDATE("News successfully updated"),
    SUCCESS_DEL("News successfully deleted"),
    INCORRECT("News Language is incorrect"),
    INCORRECT_APP("Approved type did not match"),
    INCORRECT_DEL("DELETED type did not match"),
    USER_NEWS("this User don't have any news");


    private final String value;

    EnumMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
