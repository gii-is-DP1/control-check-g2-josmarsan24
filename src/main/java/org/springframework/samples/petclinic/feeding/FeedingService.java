package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Service;

@Service
public class FeedingService {

    private FeedingRepository feedingRepo;

    @Transactional
    public List<Feeding> getAll(){
        return feedingRepo.findAll();
    }

    @Transactional
    public List<FeedingType> getAllFeedingTypes(){
        return feedingRepo.findAllFeedingTypes();
    }

    @Transactional
    public FeedingType getFeedingType(@Param("typeName") String typeName) {
        return feedingRepo.findByName(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        PetType t = p.getPet().getType();
        if (t.equals(p.getFeedingType().getPetType())) {
            feedingRepo.save(p);
        } else {
            throw new UnfeasibleFeedingException();
        }
        return p;
    }

    
}
