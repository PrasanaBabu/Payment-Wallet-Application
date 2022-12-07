package app.dto;

public class WalletDto {

    private Integer customerId;
    private Integer receiverId;
    private Double amount;

    public WalletDto() {

    }

    public WalletDto(Integer customerId, Double amount) {
        this.customerId = customerId;
        this.amount = amount;
    }

    public WalletDto(Integer customerId, Integer receiverId, Double amount) {
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

