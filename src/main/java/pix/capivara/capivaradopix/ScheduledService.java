package pix.capivara.capivaradopix;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledService {

  private final UserRepository userRepository;

  public ScheduledService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Scheduled(fixedRate = 30000)
  public void updateProfits() {
    List<User> users = userRepository.findAll();
    users.forEach(user -> {
      double additionalProfit = user.getInvestment() * 0.33;
      user.addProfit(additionalProfit);
      userRepository.save(user);
    });
  }
}
