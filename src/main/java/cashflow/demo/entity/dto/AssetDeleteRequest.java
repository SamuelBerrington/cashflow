package cashflow.demo.entity.dto;

import lombok.Data;

@Data
public class AssetDeleteRequest {

  private String assetType;
  private Long assetId;
}
