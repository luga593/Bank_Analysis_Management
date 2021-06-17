package Parser;


public class Line86Parsed {
    private String line;
    private String account;
    private String nameParty;

    public Line86Parsed(String line) {
        this.line = line;
    }

    public int countDigits(String text) {
        int cnt = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                cnt++;
            }
        }
        return cnt;
    }

    public void setDetails() {
        if (getDelimiter(line) == "/") {
            String splitted[] = line.split("/");
            this.account = splitted[2];
            this.nameParty = splitted[4];
        } else {
            String splitted1[] = line.split("  ");
            String splitted2[] = splitted1[0].split(" ");
            this.nameParty = "";
            for (int i = 0; i < splitted2.length; i++) {
                if (countDigits(splitted2[i]) >= 10) {
                    this.account = splitted2[i];
                } else {
                    this.nameParty += splitted2[i] + " ";
                }
            }
        }
    }

    public String getAccount() {
        return this.account;
    }

    public String getNameParty() {
        return this.nameParty;
    }

    public String getDelimiter(String text) {
        if (text.contains("CNTP")) {
            return "/";
        } else {
            return " ";
        }

    }

}
