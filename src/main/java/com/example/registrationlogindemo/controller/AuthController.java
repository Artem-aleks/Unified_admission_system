package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.entity.Slide;
import com.example.registrationlogindemo.repository.UserService;
import jakarta.validation.Valid;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Здесь вы можете добавить данные для слайдов карусели
        model.addAttribute("slides", getSlides());
        return "home";
    }

    private List<Slide> getSlides() {
        String imagePath = "src/main/resources/static/img";

        List<Slide> slides = new ArrayList<>();
        slides.add(new Slide("Московский государственный университет", imagePath + "Mgu.jpeg", "Один из самых престижных университетов России."));
        slides.add(new Slide("МГТУ им. Н.Э. Баумана", imagePath + "baum.jpeg", "Ведущий технический университет в России."));
        slides.add(new Slide("МФТИ (Физтех)", imagePath + "mirea.jpeg", "Один из самых сильных университетов в области физики и математики."));
        return slides;
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home2() {
        return "home";
    }

    @GetMapping("/img/{imageName}")
    public ResponseEntity<Resource> serveImage(@PathVariable String imageName) throws IOException {
        Resource resource = new ClassPathResource("static/img/" + imageName);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(resource.getFile().toPath())))
                .body(resource);
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/dop")
    public String dop() {
        return "dop";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model) {
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
