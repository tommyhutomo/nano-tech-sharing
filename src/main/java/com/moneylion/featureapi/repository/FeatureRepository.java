package com.moneylion.featureapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moneylion.featureapi.entity.Features;

@Repository
public interface FeatureRepository extends CrudRepository<Features, Integer> {
	int countByEmailAndName(String email, String featureName);
}
