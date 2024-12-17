package cashflow.demo.entity.dto;

import lombok.Data;

@Data
public class AssetRequest {

  private String assetType;
  private String assetName;
  private String downpayment;
  private String price;
  private String income;
}
