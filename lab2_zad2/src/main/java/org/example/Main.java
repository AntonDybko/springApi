package org.example;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Main {
    public static void main(String[] args) {
        double[] data = {1.2, 2.3, 3.4, 4.5, 5.6};

        double mean = calculateMean(data);
        double stdDev = calculateStandardDeviation(data);
        double variance = calculateVariance(data);

        System.out.println("Mean: " + mean);
        System.out.println("Standard Deviation: " + stdDev);
        System.out.println("Variance: " + variance);
    }

    public static double calculateMean(double[] data) {
        DescriptiveStatistics stats = new DescriptiveStatistics(data);
        return stats.getMean();
    }

    public static double calculateStandardDeviation(double[] data) {
        DescriptiveStatistics stats = new DescriptiveStatistics(data);
        return stats.getStandardDeviation();
    }

    public static double calculateVariance(double[] data) {
        DescriptiveStatistics stats = new DescriptiveStatistics(data);
        return stats.getVariance();
    }
}