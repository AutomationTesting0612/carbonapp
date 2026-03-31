package com.carbon.entity;

public class PredictionResponse {

    private double predictedCarbon;
    private String suggestion;

    private double recommendedElectricity;
    private double recommendedPetrol;

    public PredictionResponse(double predictedCarbon, String suggestion,
                              double recommendedElectricity,
                              double recommendedPetrol) {
        this.predictedCarbon = predictedCarbon;
        this.suggestion = suggestion;
        this.recommendedElectricity = recommendedElectricity;
        this.recommendedPetrol = recommendedPetrol;
    }

    public double getPredictedCarbon() {
        return predictedCarbon;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public double getRecommendedElectricity() {
        return recommendedElectricity;
    }

    public double getRecommendedPetrol() {
        return recommendedPetrol;
    }
}
