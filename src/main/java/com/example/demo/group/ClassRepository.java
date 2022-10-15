package com.example.demo.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ClassRepository
        extends JpaRepository<Class, Long> {

    Optional<Class> findByGroupId(Long groupId);

    List<Class> findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Class  g " +
            "SET g.name = :name WHERE g.groupId = :groupId")
    int updateName(@Param(value = "name") String name, @Param(value = "groupId") Long groupId);

    @Transactional
    @Modifying
    @Query("UPDATE Class  g " +
            "SET g.groupId = :newGroupId WHERE g.groupId = :groupId")
    int updateGroupIdNumber(@Param(value = "newGroupId") Long newGroupId, @Param(value = "groupId") Long groupId);


}
