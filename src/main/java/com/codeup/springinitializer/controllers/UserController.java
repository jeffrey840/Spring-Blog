package com.codeup.springinitializer.controllers;

import com.codeup.springinitializer.models.User;
import com.codeup.springinitializer.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

//    @PostMapping("/sign-up")
//    public String saveUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userDao.save(user);
//        return "redirect:/login";
//    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String handle(@ModelAttribute User user, BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "users/sign-up";
        }
        // Save account ...
        redirectAttrs.addAttribute("id", user.getId()).addFlashAttribute("message", "Account created!");
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

//    @PostMapping("/user/resetPassword")
//    public GenericResponse resetPassword(HttpServletRequest request,
//    @RequestParam("email") String userEmail) {
//        User user = userService.findUserByEmail(userEmail);
//        if (user == null) {
//            throw new UserNotFoundException();
//        }
//        String token = UUID.randomUUID().toString();
//        userService.createPasswordResetTokenForUser(user, token);
//        mailSender.send(constructResetTokenEmail(getAppUrl(request),
//                request.getLocale(), token, user));
//        return new GenericResponse(
//                messages.getMessage("message.resetPasswordEmail", null,
//                        request.getLocale()));
//    }

}

