package cashflow.demo.entity.factory;

import cashflow.demo.entity.dto.AssetRequest;
import cashflow.demo.entity.Asset;
import cashflow.demo.entity.Business;
import cashflow.demo.entity.Player;

public class BusinessFactory extends AssetFactory {

  public BusinessFactory(AssetRequest assetRequest) {
    super(assetRequest);
  }

  @Override
  public Asset createAsset(AssetRequest assetRequest, Player player) {

    return Business.builder()
        .price(price)
        .firstPayment(downpayment)
        .name(name.substring(1, name.length() - 1))
        .mortgage(mortgage)
        .income(income)
        .player(player)
        .build();
  }
}
