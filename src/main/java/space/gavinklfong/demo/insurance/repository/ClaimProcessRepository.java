package space.gavinklfong.demo.insurance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import space.gavinklfong.demo.insurance.model.ClaimReviewResult;

@Repository
public interface ClaimProcessRepository extends MongoRepository<ClaimReviewResult, String>  {
}
