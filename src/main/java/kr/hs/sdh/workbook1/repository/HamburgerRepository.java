package kr.hs.sdh.workbook1.repository;

import kr.hs.sdh.workbook1.entity.Hamburger;
import kr.hs.sdh.workbook1.entity.History;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface HamburgerRepository {

    // 햄버거 데이터 전체 조회
    @Select("select * from hamburger")
    Set<Hamburger> findHamburgers();

    // 햄버거 데이터 삭제
    @Delete("delete from hamburger where name = #{name}")
    void deleteHamburger(Hamburger hamburger);

    // 햄버거 데이터 추가/수정
    @Insert("insert into hamburger(name, price, image_path, is_new, is_recommended) values(#{name}, #{price}, #{imagePath}, #{isNew}, #{isRecommended}) on duplicate key update price = #{price}, image_path = #{imagePath}, is_new = #{isNew} #{isRecommended}")
    void saveHamburger(Hamburger hamburger);

    @Insert("insert into history(name, price, sale_datetime) values(#{name}, #{price}, #{saleDateTime}) on duplicate key update price = #{price}")
    void saveHamburgerHistory (History history);
}