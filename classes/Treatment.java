package classes;

public class Treatment {
    private int treatmentId;
    private String treatmentName;
    private double treatmentPrice;

    public Treatment(int treatmentId, String treatmentName, double treatmentPrice) {
        this.treatmentId = treatmentId;
        this.treatmentName = treatmentName;
        this.treatmentPrice = treatmentPrice;
    }


    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public void setTreatmentPrice(int treatmentPrice) {
        this.treatmentPrice = treatmentPrice;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public double getTreatmentPrice() {
        return treatmentPrice;
    }

    public void showTreatmentDetails() {
        System.out.println("Treatment ID: " + treatmentId);
        System.out.println("Treatment Name: " + treatmentName);
        System.out.println("Treatment Price: " + treatmentPrice);
    }
}
