package alitea.am.ali_travel.api_wrapper.rese_planerare;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by axel on 09/03/17.
 */

public class Product {
    private String name, catOutS, catOutL, operator, operatorUrl;
    private int num, catCode, operatorCode;

    public Product(JSONObject product) {
        try {
            this.name = product.getString("name");
            this.num = product.getInt("num");
            this.catCode = product.getInt("catCode");
            this.catOutS = product.getString("catOutS");
            this.catOutL = product.getString("catOutL");
            this.operatorCode = product.getInt("operatorCode");
            this.operator = product.getString("operator");
            this.operatorUrl = product.getString("operatorUrl");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getCatOutS() {
        return catOutS;
    }

    public String getCatOutL() {
        return catOutL;
    }

    public String getOperator() {
        return operator;
    }

    public String getOperatorUrl() {
        return operatorUrl;
    }

    public int getNum() {
        return num;
    }

    public int getCatCode() {
        return catCode;
    }

    public int getOperatorCode() {
        return operatorCode;
    }
}
