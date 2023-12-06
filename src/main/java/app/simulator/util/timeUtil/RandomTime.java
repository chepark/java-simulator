package app.simulator.util.timeUtil;

import app.simulator.types.ServicePointType;
import app.simulator.util.distributions.Normal;

import java.util.Random;

public class RandomTime {
    static final double PANTTI_SERVICE_TIME = 5.0;
    static final double MARKET_SERVICE_TIME = 15.0;
    static final double SELFCHECKOUT_SERVICE_TIME = 3.0;
    static final double CASHIER_SERVICE_TIME = 4.0;

    static final double AVG_SHOPPING_TIME = 20.0;

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

    public static double generateShoppingTime() {
        Normal normalGenerator = new Normal(AVG_SHOPPING_TIME, 20);
        return normalGenerator.sample();
    }
}
