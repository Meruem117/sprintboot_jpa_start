package niit.start.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import niit.start.bean.UList;

public interface UListDao extends JpaRepository<UList, Long> {
    UList getUpByMid(String mid);
}
