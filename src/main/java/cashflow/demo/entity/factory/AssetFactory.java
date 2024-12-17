package cashflow.demo.entity.factory;

import cashflow.demo.entity.dto.AssetRequest;
import cashflow.demo.entity.Asset;
import cashflow.demo.entity.Player;
import org.apache.commons.lang3.StringUtils;

public abstract class AssetFactory {

  String name;
  int price;
  int downpayment;
  int mortgage;
  int income;

  public abstract Asset createAsset(AssetRequest assetRequest, Player player);

  public AssetFactory(AssetRequest assetRequest) {
    name = assetRequest.getAssetName();
    price = Integer.parseInt(assetRequest.getPrice());
    downpayment = Integer.parseInt(assetRequest.getDownpayment());
    mortgage = price - downpayment;
    income = StringUtils.isNotBlank(assetRequest.getIncome())
             ? Integer.parseInt(assetRequest.getIncome())
             : 0;
  }


}