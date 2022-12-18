package app.dto;

public class Transaction {

    private final Integer customerId;
    private final Integer receiverId;
    private final Double amount;

    public Transaction() {
        customerId = 0;
        receiverId = 0;
        amount = 0.0;

    }

    public Transaction(Integer customerId, Double amount) {
        this.customerId = customerId;
        this.amount = amount;
        this.receiverId = 0;
    }

    public Transaction(Integer customerId, Integer receiverId, Double amount) {
        this.customerId = customerId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public Double getAmount() {
        return amount;
    }


}

