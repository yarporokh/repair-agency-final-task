package org.example.models;

import java.sql.Date;

public class Application {
    private int applicationId;
    private String email;
    private String text;
    private double price;
    private Date date;
    private String paymentStatus;
    private String progress;
    private String servicemanEmail;
    private String responseText;

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getServicemanEmail() {
        return servicemanEmail;
    }

    public void setServicemanEmail(String servicemanEmail) {
        this.servicemanEmail = servicemanEmail;
    }
    public String getResponseText() {
        return responseText;
    }
    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public static Application.Builder newBuilder() {
        return new Application().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Application.Builder setApplicationId(int applicationId) {
            Application.this.applicationId = applicationId;
            return this;
        }

        public Application.Builder setEmail(String email) {
            Application.this.email = email;
            return this;
        }

        public Application.Builder setText(String text) {
            Application.this.text = text;
            return this;
        }

        public Application.Builder setPrice(double price) {
            Application.this.price = price;
            return this;
        }

        public Application.Builder setDate(Date date) {
            Application.this.date = date;
            return this;
        }

        public Application.Builder setPaymentStatus(String paymentStatus) {
            Application.this.paymentStatus = paymentStatus;
            return this;
        }

        public Application.Builder setProgress(String progress) {
            Application.this.progress = progress;
            return this;
        }

        public Application.Builder setServicemanEmail(String servicemanEmail) {
            Application.this.servicemanEmail = servicemanEmail;
            return this;
        }

        public Application.Builder setResponseText (String responseText) {
            Application.this.responseText = responseText;
            return this;
        }

        public Application build() {
            return Application.this;
        }
    }
}
