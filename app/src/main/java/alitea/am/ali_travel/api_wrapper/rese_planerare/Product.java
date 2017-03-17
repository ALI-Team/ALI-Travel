package alitea.am.ali_travel.api_wrapper.rese_planerare;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by axel on 09/03/17.
 */

public class Product implements Parcelable {
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

    protected Product(Parcel in) {
        name = in.readString();
        catOutS = in.readString();
        catOutL = in.readString();
        operator = in.readString();
        operatorUrl = in.readString();
        num = in.readInt();
        catCode = in.readInt();
        operatorCode = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    /**
     * Gets name of company, ie "Öresundståg 1038"
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets short name for category, ie "JOR"
     * @return short name of category
     */
    public String getCatOutS() {
        return catOutS;
    }

    /**
     * Gets long name for category, ie "Öresundståg"
     * @return long name of category
     */
    public String getCatOutL() {
        return catOutL;
    }

    /**
     * Gets name of operator, ie "SJ" or "Östgötatrafiken"
     * @return name
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Gets url of operators website
     * @return url string
     */
    public String getOperatorUrl() {
        return operatorUrl;
    }

    /**
     * Bus or train number i think, same as number in Leg
     * @return int num
     */
    public int getNum() {
        return num;
    }

    /**
     * Type of thingy, should be equal to log2(TrafikSlag.getNum())
     * @return int catCode
     */
    public int getCatCode() {
        return catCode;
    }

    /**
     * operator code
     * @return int operator code
     */
    public int getOperatorCode() {
        return operatorCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(catOutL);
        dest.writeString(catOutS);
        dest.writeString(operator);
        dest.writeString(operatorUrl);
        dest.writeInt(num);
        dest.writeInt(catCode);
        dest.writeInt(operatorCode);
    }
}
