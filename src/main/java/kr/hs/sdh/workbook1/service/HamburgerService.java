package kr.hs.sdh.workbook1.service;

import kr.hs.sdh.workbook1.entity.Hamburger;
import kr.hs.sdh.workbook1.entity.History;
import kr.hs.sdh.workbook1.repository.HamburgerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public final class HamburgerService {

    private static final String ABSOLUTE_PATH = "C:\\workbook1 (1)\\src\\main\\resources\\static\\images\\";

    // 햄버거 데이터 저장소
    private final HamburgerRepository hamburgerRepository;

    // "HamburgerService" 클래스의 생성자
    public HamburgerService(HamburgerRepository hamburgerRepository) {
        this.hamburgerRepository = hamburgerRepository;
    }

    // 햄버거 데이터를 조회하고 금액 순서로 정렬하여 반환
    public List<Hamburger> getHamburgers() {
        return this.hamburgerRepository.findHamburgers()
            .stream()
            .sorted(
                Comparator.comparing(Hamburger::getPrice)
            )
            .toList();
    }

    public void setHamburger(final Hamburger hamburger, final MultipartFile multipartFile) {
        final String fileName = multipartFile.getOriginalFilename();

        if (fileName != null && !fileName.isEmpty()) {
            try {
                final File file = new File(ABSOLUTE_PATH + fileName);

                multipartFile.transferTo(file);
                hamburger.setImagePath("/images/" + fileName);
                this.hamburgerRepository.saveHamburger(hamburger);
            }
            catch (final IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    public void removeHamburger(final String hamburgerName) {
        Hamburger hamburger = new Hamburger(
                hamburgerName,
                0,
                null,
                false,
                false
        );
        System.out.println(hamburger.getName());
        this.hamburgerRepository.deleteHamburger(hamburger);
    }

    public void setHamburgerHistory(final History history) {
        if(history.getPrice() > 0) {
            List<Hamburger> hamburgers = getHamburgers();
            for(Hamburger hamburger : hamburgers) {
                if(hamburger.getName().equals(history.getName())) {
                    hamburgerRepository.saveHamburgerHistory(history);
                }
            }
        }
    };
}