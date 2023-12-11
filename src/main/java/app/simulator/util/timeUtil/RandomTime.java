package app.simulator.util.timeUtil;

import app.simulator.types.ServicePointType;
import app.simulator.util.distributions.Normal;
import app.simulator.util.distributions.Poisson;

public class RandomTime {
    private static final double PANTTI_SERVICE_TIME = 5.0;
    private static final double MARKET_SERVICE_TIME = 15.0;
    private static final double SELFCHECKOUT_SERVICE_TIME = 3.0;
    private static final double CASHIER_SERVICE_TIME = 4.0;
    private static final double AVG_SHOPPING_TIME = 20.0;

    // customerNumber and timeIntervalsInHours are used for Poisson distribution calculation
    private static double customerNumber = 6.0;
    private static double timeIntervalsInHours = 1.0; // 30 minutes = 0.5 hours

    public static double generateShoppingTime() {
        Normal normalGenerator = new Normal(AVG_SHOPPING_TIME, 10);
        return normalGenerator.sample();
    }

    /***
     * Generate arrival timegap based on the average customers and time intervals in hours.
     * The values are defined on the top as averageCustomers and timeIntervalsInHours.
     * @return
     */
    public static double generateArrivalTimeGap() {
        double lambda = customerNumber / timeIntervalsInHours;
        Poisson poisson = new Poisson(lambda);
        return (double) poisson.sample();
    }

    public static double generate(ServicePointType type) {
        double meanValue = switch (type) {
            case PANTTI -> PANTTI_SERVICE_TIME;
            case MARKET -> MARKET_SERVICE_TIME;
            case SELF_CHECKOUT -> SELFCHECKOUT_SERVICE_TIME;
            case CASHIER -> CASHIER_SERVICE_TIME;
            default -> 2.0;
        };

        Normal normalGenerator = new Normal(meanValue, 1);
        return normalGenerator.sample();
    }

    public static int getCustomerNumber() {
        return (int) customerNumber;
    }

    public static void setCustomerNumber(int i) {
        customerNumber = (double) i;
    }

}