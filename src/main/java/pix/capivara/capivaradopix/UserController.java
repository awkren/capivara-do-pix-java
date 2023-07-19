package pix.capivara.capivaradopix;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return userService.getUsers();
  }

  @PostMapping("/users")
  public User postUser(@RequestBody User user) {
    return userService.add(user);
  }

  @PostMapping("/users/withdraw/{id}")
  public ResponseEntity<?> withdrawProfits(@PathVariable Long id) {
    Optional<User> optionalUser = userService.getUserById(id);

    if (!optionalUser.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    User user = optionalUser.get();

    Long totalUsers = userService.countUsers();

    double randomChance = totalUsers > 5 ? (totalUsers - 5) * 0.05 : 0;

    if (totalUsers > 5 && new Random().nextInt(101) / 100.0 < randomChance) {
      return new ResponseEntity<>("Withdraw not allowed", HttpStatus.FORBIDDEN);
    }

    user.setProfit(0.0);
    userService.add(user);

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}
