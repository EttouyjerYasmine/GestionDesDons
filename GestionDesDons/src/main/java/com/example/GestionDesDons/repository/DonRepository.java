package com.example.GestionDesDons.repository;

import com.example.GestionDesDons.entity.Campagne;
import com.example.GestionDesDons.entity.Don;
import com.example.GestionDesDons.entity.Donateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonRepository extends JpaRepository<Don, Long> {
    List<Don> findByDonateur(Donateur donateur);
    
    List<Don> findByCampagne(Campagne campagne);

    @Query("select coalesce(sum(d.montant), 0) from Don d")
    java.math.BigDecimal sumMontants();

    @Query("select c.titre as label, coalesce(sum(d.montant), 0) as total from Don d join d.campagne c group by c.titre")
    List<Object[]> sumByCampagne();

    @Query("select function('date_format', d.dateDon, '%Y-%m') as ym, coalesce(sum(d.montant),0) as total from Don d group by ym order by ym")
    List<Object[]> sumByMonth();

    @Query("select d.moyen as moyen, coalesce(sum(d.montant),0) as total from Don d group by d.moyen")
    List<Object[]> sumByMoyen();
}
