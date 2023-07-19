package pix.capivara.capivaradopix;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<User> withdrawProfits(@PathVariable Long id) {
    Optional<User> optionalUser = userService.getUserById(id);

    if (!optionalUser.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    User user = optionalUser.get();
    user.setProfit(0.0);
    userService.add(user);

    return new ResponseEntity<User>(user, HttpStatus.OK);
  }
}
