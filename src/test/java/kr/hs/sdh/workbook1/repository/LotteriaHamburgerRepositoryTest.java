package kr.hs.sdh.workbook1.repository;

import kr.hs.sdh.workbook1.entity.Hamburger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

class LotteriaHamburgerRepositoryTest {

    private Set<Hamburger> hamburgers;

    @Test
    void saveHamburger() {
        Hamburger appleHambuger = new Hamburger(
                "사과 햄버거",
                4500,
                "/",
                true,
                false
        );
        this.deleteHamburger();
        this.hamburgers.add(appleHambuger);
    }

    @Test
    void deleteHamburger() {
        Hamburger appleHambuger = new Hamburger(
                "사과 햄버거",
                4500,
                "/",
                true,
                false
        );
        this.hamburgers.removeIf(deleteHamburger -> deleteHamburger == appleHambuger);
    }

    @BeforeEach
    void beforeEach() {
        Hamburger appleHambuger = new Hamburger(
                "사과 햄버거",
                4500,
                "/",
                true,
                false
        );
        this.hamburgers.add(appleHambuger);
    }
}