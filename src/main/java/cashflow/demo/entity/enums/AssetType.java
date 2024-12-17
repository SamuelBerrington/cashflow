package cashflow.demo.entity.enums;

import cashflow.demo.entity.Business;
import cashflow.demo.entity.RealEstate;
import cashflow.demo.entity.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AssetType {
  REAL_ESTATE("realEstate", RealEstate.class),
  STOCK("stock", Stock.class),
  BUSINESS("business", Business.class);
  private String assetName;
  private Class asset;

  public static AssetType getAssetType(String assetName) {
    for (AssetType assetType : AssetType.values()) {
      if (assetType.assetName.equals(assetName)) {
        return assetType;
      }
    }
    throw new IllegalArgumentException("Invalid asset type: " + assetName);
  }
}