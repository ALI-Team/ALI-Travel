package alitea.am.ali_travel.api_wrapper;

import java.util.EnumSet;

/**
 * Created by axel on 05/03/17.
 */

public enum TrafikSlag {
    FLYG (1 << 0),       //0000000001
    SNABBTÅG (1 << 1),   //0000000010
    TÅG (1 << 2),        //0000000100
    EXPRESSBUSS (1 << 3),//0000001000
    LOKALTÅG (1 << 4),   //0000010000
    TUNNELBANA (1 << 5), //0000100000
    SPÅRVAGN (1 << 6),   //0001000000
    BUSS (1 << 7),       //0010000000
    BÅT (1 << 8),        //0100000000
    TAXI (1 << 9);       //1000000000


    private int num;
    public static EnumSet<TrafikSlag> all = EnumSet.allOf(TrafikSlag.class);
    public static int allInt = getProducts(all);

    TrafikSlag(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public boolean in(int products) {
        return (this.getNum() & products) == this.getNum();
    }

    public static EnumSet<TrafikSlag> getModes(int products) {
        EnumSet<TrafikSlag> enumSet = EnumSet.noneOf(TrafikSlag.class);
        for(TrafikSlag ts : TrafikSlag.all) {
            if(ts.in(products)) {
                enumSet.add(ts);
            }
        }
        return enumSet;
    }

    public static int getProducts(EnumSet<TrafikSlag> modes) {
        int products = 0;
        for(TrafikSlag ts : modes) {
            products += ts.getNum();
        }
        return products;
    }

}