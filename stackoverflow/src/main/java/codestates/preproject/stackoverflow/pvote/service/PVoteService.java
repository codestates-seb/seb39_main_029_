package codestates.preproject.stackoverflow.pvote.service;

import codestates.preproject.stackoverflow.pvote.entity.Pvote;
import codestates.preproject.stackoverflow.pvote.repository.PVoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PVoteService {

    private final PVoteRepository pVoteRepository;

    public PVoteService(PVoteRepository pVoteRepository) {
        this.pVoteRepository = pVoteRepository;
    }

    public Pvote findPVote(long postId, long memberId) {
        Optional<Pvote> vote = pVoteRepository.findByPostIdMemberId(postId, memberId);
        if (vote.isPresent()) {
            return vote.get();
        }else{
            return null;
        }

    }
}
