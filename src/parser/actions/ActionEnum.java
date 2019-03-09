package parser.actions;

import javax.swing.*;

public enum ActionEnum {
    ADD("Dodaj",
            "Dodaj plik do listy",
            "ctrl A",
            new ImageIcon("dodaj.png")
        ),
    DELETE("Usuń",
            "Usuń plik z listy",
            "ctrl D",
            new ImageIcon("usun.png")
        ),
    START("Start",
            "Rozpocznij pracę",
            "ctrl Z",
            new ImageIcon()),
    CLOSE("Koniec",
            "Zamknij program",
            "alt F4",
            new ImageIcon());

    private String name;
    private String desc;
    private String keyStroke;
    private ImageIcon icon;

    ActionEnum(String name, String desc, String keyStroke, ImageIcon icon) {
        this.name = name;
        this.desc = desc;
        this.keyStroke = keyStroke;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getKeyStroke() {
        return keyStroke;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return this.name;
    }
}