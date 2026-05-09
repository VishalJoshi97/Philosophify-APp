package com.philosophify.backend.service.fav;

import com.philosophify.backend.dto.fav.FavoriteRequest;
import com.philosophify.backend.dto.fav.FavoriteResponse;
import com.philosophify.backend.dto.philosopher.PhilosopherMapper;
import com.philosophify.backend.dto.philosopher.PhilosopherResponse;
import com.philosophify.backend.dto.quote.QuoteResponse;
import com.philosophify.backend.exception.UserNotFoundException;
import com.philosophify.backend.model.Favorite;
import com.philosophify.backend.model.Philosopher;
import com.philosophify.backend.model.Quote;
import com.philosophify.backend.repository.FavoriteRepository;
import com.philosophify.backend.repository.PhilosopherRepository;
import com.philosophify.backend.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final QuoteRepository quoteRepository;
    private final PhilosopherRepository philosopherRepository;

    //create favourite quotes/philosophers for users
    @Override
    public void addFavorite(FavoriteRequest request) {

        boolean exists = favoriteRepository.existsByUserIdAndTypeAndReferenceId(
                request.getUserId(),
                request.getType(),
                request.getReferenceId()
        );
        if (exists) {
                return;
        }
        Favorite f = new Favorite();
        f.setUserId(request.getUserId());
        f.setType(request.getType());
        f.setReferenceId(request.getReferenceId());//ref->philosopher

        favoriteRepository.save(f);
    }


    //get all favorite -quotes/philosophers
    @Override
    public List<FavoriteResponse> getUserFavorites(Long userId) {

        List<Favorite> favorites = favoriteRepository.findByUserId(userId);

        return favorites.stream().map(f -> {

            if (f.getType().equals("QUOTE")) {

                Quote q = quoteRepository.findById(f.getReferenceId())
                        .orElseThrow(() -> new RuntimeException("Quote not found"));

                Philosopher p=q.getPhilosopher();

                QuoteResponse qr = new QuoteResponse();
                qr.setId(q.getId());
                qr.setText(q.getText());

                return new FavoriteResponse(f.getId(), "QUOTE", qr, p.getName());

                //fav philosopher->use this when u want to scale up
            } else {

                Philosopher p = philosopherRepository.findById(f.getReferenceId())
                        .orElseThrow(() -> new UserNotFoundException("Philosopher not found"));


                PhilosopherResponse pr = PhilosopherMapper.toResponse(p);

                return new FavoriteResponse(f.getId(), "PHILOSOPHER", pr, p.getName());
            }

        }).toList();
    }

    //remove favorite
    @Override
    public void removeFavorite(Long id){
        if(favoriteRepository.existsById(id)){
          favoriteRepository.deleteById(id);
        }
        else throw new RuntimeException("NO favorite id found: "+id);
    }
}