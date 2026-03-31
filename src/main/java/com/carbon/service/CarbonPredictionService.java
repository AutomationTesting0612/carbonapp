package com.carbon.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.tribuo.Feature;
import org.tribuo.Model;
import org.tribuo.MutableDataset;
import org.tribuo.Trainer;
import org.tribuo.impl.ArrayExample;
import org.tribuo.regression.Regressor;
import org.tribuo.regression.sgd.linear.LinearSGDTrainer;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.data.vector.DoubleVector;
import smile.regression.LinearModel;
import smile.regression.OLS;

//@Service
//public class CarbonPredictionService {
//
//    private double slope;
//    private double intercept;
//
//    @PostConstruct
//    public void train() {
//
//        double[] x = {100, 200, 300, 400, 500};
//        double[] y = {92, 184, 276, 368, 460};
//
//        double xMean = mean(x);
//        double yMean = mean(y);
//
//        double numerator = 0;
//        double denominator = 0;
//
//        for (int i = 0; i < x.length; i++) {
//            numerator += (x[i] - xMean) * (y[i] - yMean);
//            denominator += Math.pow(x[i] - xMean, 2);
//        }
//
//        slope = numerator / denominator;
//        intercept = yMean - slope * xMean;
//    }
//
//    public double predict(double electricity) {
//        return slope * electricity + intercept;
//    }
//
//    private double mean(double[] arr) {
//        double sum = 0;
//        for (double v : arr) sum += v;
//        return sum / arr.length;
//    }
//}

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import smile.regression.RidgeRegression;

@Service
public class CarbonPredictionService {

    private LinearModel model;

    @PostConstruct
    public void train() {

        DoubleVector electricity = DoubleVector.of("electricity",
                new double[]{100, 200, 300, 400, 500});

        DoubleVector carbon = DoubleVector.of("carbon",
                new double[]{92, 184, 276, 368, 460});

        DataFrame data = DataFrame.of(electricity, carbon);

        Formula formula = Formula.lhs("carbon");

        model = RidgeRegression.fit(formula, data, 0.01);
    }

    public double predict(double electricityValue) {

        DataFrame test = DataFrame.of(
                DoubleVector.of("electricity",
                        new double[]{electricityValue})
        );

        return model.predict(test)[0];
    }
}
