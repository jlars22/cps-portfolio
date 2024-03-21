package org.cpsportfolio.backend.repository;

import org.cpsportfolio.backend.service.dto.driverstandings.DriverStandingsDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverStandingsRepository extends MongoRepository<DriverStandingsDto, String>{
}
