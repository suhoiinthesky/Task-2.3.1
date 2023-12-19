package hiber.conroler;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UsersController {
    private final UserService userService;

    @Autowired
    UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/", produces = "text/html; charset=UTF-8")
    public String home(Model model) {
        model.addAttribute("allUsers", userService.listUsers());
        return "startPage";
    }

    @GetMapping(value = "/show", produces = "text/html; charset=UTF-8")
    public String show(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userInfo";
    }

    @GetMapping(value = "/add", produces = "text/html; charset=UTF-8")
    public String addOrUpdateUser(@RequestParam(value = "id", required = false) Long id, Model model) {
        User user;
        if (id != null) {
            user = userService.getUserById(id);
        } else {
            user = new User();
        }
        model.addAttribute("user", user);
        return "newUser";
    }


    @PostMapping(produces = "text/html; charset=UTF-8")
    public String UsesIsAddOrUpdate(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @PostMapping(value = "/delete", produces = "text/html; charset=UTF-8")
    public String delete(@RequestParam("id") Long id) {
        User userToDelete = userService.getUserById(id);
        userService.delete(userToDelete);
        return "redirect:/";
    }

}
