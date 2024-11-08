package kr.hs.sdh.workbook1.controller;

import kr.hs.sdh.workbook1.entity.Hamburger;
import kr.hs.sdh.workbook1.service.HamburgerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public final class HamburgerController {

    private final HamburgerService hamburgerService;

    public HamburgerController(final HamburgerService hamburgerService) {
        this.hamburgerService = hamburgerService;
    }

    @GetMapping(value = "/lotteria")
    private String lotteria(final Model model) {
        final List<Hamburger> hamburgers = this.hamburgerService.getHamburgers();

        model.addAttribute("hamburgers", hamburgers);

        return "lotteria";
    }

    @GetMapping(value = "/lotteria-example")
    private String lotteriaExample() {
        return "lotteria-example";
    }

    @GetMapping(value = "/login")
    private String login() { return "login"; }
}
