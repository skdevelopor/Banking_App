package grip.example.bankappgrip;

public class Zmodel {

    String recName;
    String sendName;
    int amount;
    Boolean transaction;

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Boolean getTransaction() {
        return transaction;
    }

    public void setTransaction(Boolean transaction) {
        this.transaction = transaction;
    }

    public Zmodel(String recName, String sendName, int amount, Boolean transaction) {
        this.recName = recName;
        this.sendName = sendName;
        this.amount = amount;
        this.transaction = transaction;
    }
}
