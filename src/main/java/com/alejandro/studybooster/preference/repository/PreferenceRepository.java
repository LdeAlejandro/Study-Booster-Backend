package com.alejandro.studybooster.preference.repository;

import com.alejandro.studybooster.preference.entity.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

}