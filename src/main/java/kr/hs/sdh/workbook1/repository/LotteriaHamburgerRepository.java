package kr.hs.sdh.workbook1.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hs.sdh.workbook1.entity.Hamburger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public final class LotteriaHamburgerRepository implements HamburgerRepository, InitializingBean {

    private final ObjectMapper objectMapper;

    private Set<Hamburger> delteHamburgers;

    private Set<Hamburger> hamburgers;


    @Value(value = "classpath:static/lotteria-menu.json")
    private Resource lotteriaMenuResource;

    public LotteriaHamburgerRepository(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try (var inputStream = lotteriaMenuResource.getInputStream()) {
            final var hamburgerTypeReference = new TypeReference<Set<Hamburger>>() {};

            this.hamburgers = this.objectMapper.readValue(inputStream, hamburgerTypeReference);
        }
    }

    @Override
    public Set<Hamburger> findHamburgers() {
        return this.hamburgers;
    }

    @Override
    public void deleteHamburger(final Hamburger hamburger) {
        this.delteHamburgers.add(hamburger);
        this.hamburgers.remove(hamburger);
    }

    @Override
    public void saveHamburger(final Hamburger hamburger) {
        this.deleteHamburger(hamburger);
        this.hamburgers.add(hamburger);
    }

}