package kg.megacom.miniTinder.models.enums;

public enum Gender {
    MALE("1.Мужчина"),
    FEMALE("2.Женщина"),
    OTHER("3.Другое");

    private String val;

    Gender(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}

