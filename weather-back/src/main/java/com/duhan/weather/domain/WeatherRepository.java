package com.duhan.weather.domain;

import com.duhan.weather.web.dto.WeatherListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    List<Weather> findByFcstDate(String date);


    @Query(
            value = "SELECT * FROM weather WHERE nx = :nx AND ny =:ny AND (category=\"POP\" OR category=\"T3H\")",
            nativeQuery = true
    )
    List<Weather> findByNxAndNy(@Param("nx")Long nx, @Param("ny")Long ny);
}
