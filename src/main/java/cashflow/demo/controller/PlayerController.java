package cashflow.demo.controller;

import cashflow.demo.entity.dto.AssetDeleteRequest;
import cashflow.demo.entity.dto.PlayerRequest;
import cashflow.demo.entity.dto.PlayerResponse;
import cashflow.demo.entity.dto.AssetRequest;
import cashflow.demo.entity.enums.Professions;
import cashflow.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/player")
public class PlayerController {

  @Autowired
  private PlayerService playerService;

  private static final String CONSTANT_MAIN_VIEW = "player-response.html";

  @PostMapping("/removeAsset/{name}")
  public String removeAsset(AssetDeleteRequest assetDeleteRequest,
                            @PathVariable("name") String name,
                            Model model) {
    PlayerResponse playerResponse = playerService.removeAsset(assetDeleteRequest, name);
    model.addAttribute("playerResponse", playerResponse);
    return CONSTANT_MAIN_VIEW;
  }

  @GetMapping("/createpage")
  public String getCreatePlayerPage(Model model) {
    model.addAttribute("professions", Professions.values());
    return "player-create.html"; // Имя шаблона без расширения
  }

  @PostMapping(value = "/create")
  public String createPlayer(PlayerRequest playerRequest, Model model) {
    PlayerResponse playerResponse = playerService.createPlayer(playerRequest);
    model.addAttribute("playerResponse", playerResponse);
    return CONSTANT_MAIN_VIEW; // Имя шаблона без расширения
  }

  @PostMapping("/addRealEstate/{name}")
  public String addRealEstate(AssetRequest assetRequest,
                              @PathVariable("name") String name,
                              Model model) {
    PlayerResponse playerResponse = playerService.addAssetToPlayer(assetRequest, name);
    model.addAttribute("playerResponse", playerResponse);
    return CONSTANT_MAIN_VIEW;
  }

  @PostMapping("/removeAll/{name}")
  public String removeAll(@PathVariable("name") String name, Model model) {
    PlayerResponse playerResponse = playerService.removeAll(name);
    model.addAttribute("playerResponse", playerResponse);
    return CONSTANT_MAIN_VIEW;
  }

  @PostMapping("/addChild/{name}")
  public String addChild(@PathVariable("name") String name, Model model) {
    PlayerResponse playerResponse = playerService.addChild(name);
    model.addAttribute("playerResponse", playerResponse);
    return CONSTANT_MAIN_VIEW;
  }

  @PostMapping("/deleteChild/{name}")
  public String deleteChild(@PathVariable("name") String name, Model model) {
    PlayerResponse playerResponse = playerService.deleteChild(name);
    model.addAttribute("playerResponse", playerResponse);
    return CONSTANT_MAIN_VIEW;
  }

  @PostMapping("/payOffDebt/{name}")
  public String addChild(@PathVariable("name") String name, Model model, String debtType) {
    PlayerResponse playerResponse = playerService.payOff(debtType, name);
    model.addAttribute("playerResponse", playerResponse);
    return CONSTANT_MAIN_VIEW;
  }

  @PostMapping("/takeCredit/{name}")
  public String takeCredit(int credit, @PathVariable("name") String name, Model model) {
    PlayerResponse playerResponse = playerService.takeCredit(credit, name);
    model.addAttribute("playerResponse", playerResponse);
    return CONSTANT_MAIN_VIEW;
  }

  @PostMapping("/payCredit/{name}")
  public String payCredit(int credit, @PathVariable("name") String name, Model model) {
    PlayerResponse playerResponse = playerService.payCredit(credit, name);
    model.addAttribute("playerResponse", playerResponse);
    return CONSTANT_MAIN_VIEW;
  }


}
