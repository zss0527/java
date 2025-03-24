package com.example.oauth2client.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class OAuth2LoginController {

    @GetMapping("/")
    public String index(Model model, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient, @AuthenticationPrincipal OAuth2User oAuth2User) {
        model.addAttribute("userName",oAuth2User.getName());
        model.addAttribute("clientName",oAuth2AuthorizedClient.getClientRegistration().getClientName());
        model.addAttribute("userAttributes",oAuth2User.getAttributes());

        return "index";
    }
}
