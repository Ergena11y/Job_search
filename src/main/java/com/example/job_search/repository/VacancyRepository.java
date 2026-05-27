package com.example.job_search.repository;

import com.example.job_search.model.Vacancies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancies, Integer> {

    Page<Vacancies> findByIsActiveTrue(Pageable pageable);

    Page<Vacancies> findByCategoryIdAndIsActiveTrue(Integer categoryId, Pageable pageable);

    Page<Vacancies> findByAuthorId(Integer authorId, Pageable pageable);

    @Query("SELECT v FROM Vacancies v " +
            "JOIN RespondedApplicants ra ON ra.vacancy = v " +
            "WHERE ra.resume.applicant.id = :applicantId")
    List<Vacancies> findRespondedByApplicant(@Param("applicantId") int applicantId);

    @Query(nativeQuery = true,
            value = "SELECT v.* FROM vacancies v " +
                    "LEFT JOIN responded_applicants ra ON ra.vacancy_id = v.id " +
                    "WHERE v.is_active = TRUE " +
                    "GROUP BY v.id " +
                    "ORDER BY COUNT(ra.id) DESC",
            countQuery = "SELECT count(*) FROM vacancies WHERE is_active = TRUE")
    Page<Vacancies> findAllActiveOrderByResponseCount(Pageable pageable);


    @Query("SELECT v FROM Vacancies v WHERE v.isActive = TRUE ORDER BY v.salary DESC")
    Page<Vacancies> findAllActiveOrderBySalaryDesc(Pageable pageable);

    @Query("SELECT v FROM Vacancies v WHERE v.isActive = TRUE " +
            "AND (:search IS NULL OR LOWER(v.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "     OR LOWER(v.description) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND (:salaryMin IS NULL OR v.salary >= :salaryMin) " +
            "AND (:expFrom IS NULL OR v.expFrom <= :expFrom)")
    Page<Vacancies> findWithFilter(
            @Param("search") String search,
            @Param("salaryMin") Float salaryMin,
            @Param("expFrom") Integer expFrom,
            Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT v.* FROM vacancies v " +
                    "LEFT JOIN responded_applicants ra ON ra.vacancy_id = v.id " +
                    "WHERE v.is_active = TRUE " +
                    "AND (:search IS NULL OR LOWER(v.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
                    "     OR LOWER(v.description) LIKE LOWER(CONCAT('%', :search, '%'))) " +
                    "AND (:salaryMin IS NULL OR v.salary >= :salaryMin) " +
                    "AND (:expFrom IS NULL OR v.exp_from <= :expFrom) " +
                    "GROUP BY v.id ORDER BY COUNT(ra.id) DESC",
            countQuery = "SELECT COUNT(*) FROM vacancies v WHERE v.is_active = TRUE " +
                    "AND (:search IS NULL OR LOWER(v.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
                    "     OR LOWER(v.description) LIKE LOWER(CONCAT('%', :search, '%'))) " +
                    "AND (:salaryMin IS NULL OR v.salary >= :salaryMin) " +
                    "AND (:expFrom IS NULL OR v.exp_from <= :expFrom)")
    Page<Vacancies> findWithFilterOrderByResponses(
            @Param("search") String search,
            @Param("salaryMin") Float salaryMin,
            @Param("expFrom") Integer expFrom,
            Pageable pageable);
}