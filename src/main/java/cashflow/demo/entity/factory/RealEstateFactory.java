package cashflow.demo.entity.factory;

import cashflow.demo.entity.dto.AssetRequest;
import cashflow.demo.entity.Asset;
import cashflow.demo.entity.Player;
import cashflow.demo.entity.RealEstate;

public class RealEstateFactory extends AssetFactory {

  public RealEstateFactory(AssetRequest assetRequest) {
    super(assetRequest);
  }

  @Override
  public Asset createAsset(AssetRequest assetRequest, Player player) {

    return RealEstate.builder()
        .price(price)
        .name(name.substring(0, name.length() - 2))
        .firstPayment(downpayment)
        .mortgage(mortgage)
        .income(income)
        .player(player)
        .build();
  }
}
