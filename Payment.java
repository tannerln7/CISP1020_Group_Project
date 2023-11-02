public class Payment {
    private String paymentType;
    private double amountPaid;
    private boolean isPaid;

    public Payment(String paymentType, double amountPaid, boolean isPaid){
        this.paymentType = paymentType;
        this.amountPaid = amountPaid;
    }

    public String getPaymentType(){
        return paymentType;
    }

    public void changePaymentType(String newPaymentType){
        this.paymentType = newPaymentType;
    }

    public double getAmountPaid(){
        return amountPaid;
    }

    public void changeAmountPaid(double newAmountPaid){
        this.amountPaid = newAmountPaid;
    }

    public boolean getIsPaid(){
        return isPaid;
    }

    public void changeIsPaid(boolean newIsPaid){
        this.isPaid = newIsPaid;
    }
}
