package Pages;

public enum Text {

    HELADERAS("Heladeras"),
    SAMSUNG("Samsung");

    private String text;

    Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
