package app.dto;

public class Transaction {

    private Integer customerId;
    private Integer receiverId;
    private Double amount;

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
