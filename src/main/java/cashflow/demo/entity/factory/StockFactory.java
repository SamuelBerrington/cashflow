package cashflow.demo.entity.factory;

import cashflow.demo.entity.dto.AssetRequest;
import cashflow.demo.entity.Asset;
import cashflow.demo.entity.Player;
import cashflow.demo.entity.Stock;

public class StockFactory extends AssetFactory {

  public StockFactory(AssetRequest assetRequest) {
    super(assetRequest);
  }

  @Override
  public Asset createAsset(AssetRequest assetRequest, Player player) {

    return Stock.builder()
        .price(price)
        .firstPayment(downpayment)
        .name(name.substring(2))
        .mortgage(mortgage)
        .income(income)
        .player(player)
        .build();
  }
}
