package codestates.preproject.stackoverflow.cvote.service;

import codestates.preproject.stackoverflow.comments.entity.Comments;
import codestates.preproject.stackoverflow.cvote.entity.Cvote;
import codestates.preproject.stackoverflow.cvote.repository.CvoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CvoteService {
    private CvoteRepository cvoteRepository;

    public CvoteService(CvoteRepository cvoteRepository){
        this.cvoteRepository = cvoteRepository;
    }

    public boolean findCvote(long memberid, long commentsid){
        Optional<Cvote> cvoteOptional = cvoteRepository.findByMemberid(memberid, commentsid);
        if(cvoteOptional.isEmpty()){
            System.out.println("투표 전");
            return false;
        }else{
            System.out.println("투표 함");
            return true;
        }
    }

    public void saveCvote(long memberid, Comments comments){
        Cvote cvote = new Cvote();
        cvote.setComments(comments);
        cvote.setMemberid(memberid);
        cvoteRepository.save(cvote);
    }

    public void deleteCvote(long memberid, Comments comments){
        Optional<Cvote> cvoteOptional = cvoteRepository.findByMemberid(memberid, comments.getCommentsid());
        Cvote cvote = cvoteOptional.get();
        cvoteRepository.delete(cvote);
    }
}
