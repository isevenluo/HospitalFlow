package com.seventech.hospitalflow.dao;

import com.seventech.hospitalflow.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Seven Luo
 * @date 2024/2/14 21:36
 */
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}
