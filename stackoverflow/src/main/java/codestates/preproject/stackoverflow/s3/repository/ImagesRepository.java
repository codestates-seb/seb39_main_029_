package codestates.preproject.stackoverflow.s3.repository;


import codestates.preproject.stackoverflow.s3.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Images, Long> {
}
