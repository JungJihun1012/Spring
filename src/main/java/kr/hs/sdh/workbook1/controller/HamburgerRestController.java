package kr.hs.sdh.workbook1.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.hs.sdh.workbook1.entity.Hamburger;
import kr.hs.sdh.workbook1.entity.History;
import kr.hs.sdh.workbook1.service.HamburgerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
public class HamburgerRestController {

    private final HamburgerService hamburgerService;

    public HamburgerRestController(final HamburgerService hamburgerService) {
        this.hamburgerService = hamburgerService;
    }

    // http://localhost:8080/lotteria-menus
    @GetMapping("/lotteria-menus")
    private List<Hamburger> lotteriaMenus(@RequestParam(defaultValue = "") String hamburgerName) {
        List<Hamburger> hamburgers = this.hamburgerService.getHamburgers();
        List<Hamburger> filterHamburgers = new ArrayList<>();

        for (int index = 0; index < hamburgers.size(); index++) {
            Hamburger hamburger = hamburgers.get(index);

            if (hamburger.getName().contains(hamburgerName)) {
                filterHamburgers.add(hamburger);
            }
        }

        return filterHamburgers;
    }

    @PostMapping("/lotteria-add-menu")
    private void lotteriaAddMenu(
            Hamburger hamburger,
            @RequestParam(value = "image") MultipartFile multipartFile,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        this.hamburgerService.setHamburger(hamburger, multipartFile);
        httpServletResponse.sendRedirect("lotteria-example");
    }

    @DeleteMapping("/lotteria-delete-menu")
    private Map<String, String> lotteriaDeleteMenu(@RequestParam final String hamburgerName) {
        this.hamburgerService.removeHamburger(hamburgerName);
        return Collections.singletonMap("message", hamburgerName + "이/가 메뉴에서 삭제하였습니다");
    }

    @PostMapping("/hamburger-sell")
    private String saleHamburger(final History history) {
        this.hamburgerService.setHamburgerHistory(history);
        return "판매가 완료되었습니다.";
    }

}