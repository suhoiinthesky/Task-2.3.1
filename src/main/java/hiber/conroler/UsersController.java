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
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/", produces = "text/html; charset=UTF-8")
    public String getHomePage(Model model) {
        model.addAttribute("allUsers", userService.listUsers());
        return "startPage";
    }

    @GetMapping(value = "/show", produces = "text/html; charset=UTF-8")     // По заданию это не обязательно
    public String showUser(@RequestParam("id") Long id, Model model) {        // но раз сделал, то ладно
        model.addAttribute("user", userService.getUserById(id));
        return "userInfo";
    }

    @GetMapping(value = "/add", produces = "text/html; charset=UTF-8")
    public String addOrUpdateUser(@RequestParam(value = "id", required = false) Long id, Model model) {
        User user;                                              // Тут я реализовал два действия в одном
        if (id != null) {                                       // тк формы для заполнения идентичны
            user = userService.getUserById(id);
        } else {
            user = new User();
        }
        model.addAttribute("user", user);
        return "newUser";
    }


    @PostMapping(produces = "text/html; charset=UTF-8")                 // Возможно название не совсем подлежит
    public String UserIsAddOrUpdate(@ModelAttribute("user") User user) {   // конвенции, тк почти сопадает с назваанием
        userService.save(user);                                            // метода выше, но на большее мне не хватило
        return "redirect:/";                                               // фантазии
    }

    @PostMapping(value = "/delete", produces = "text/html; charset=UTF-8")
    public String delete(@RequestParam("id") Long id) {
        User userToDelete = userService.getUserById(id);
        userService.delete(userToDelete);
        return "redirect:/";
    }

}
