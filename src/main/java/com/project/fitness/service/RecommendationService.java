package com.project.fitness.service;

import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.dto.RecommendationResponse;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.RecommendationRepository;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final RecommendationRepository recommendationRepository;
    public Recommendation generateRecommendation(RecommendationRequest request) {
         User user= userRepository.findById(request.getUserId())
                 .orElseThrow(()-> new RuntimeException("User Not Found: "+request.getUserId()));

        Activity activity=activityRepository.findById(request.getActivityId())
                .orElseThrow(()-> new RuntimeException("Activity Not Found: "+request.getActivityId()));

        Recommendation recommendation=Recommendation.builder()
                .user(user)
                .activity(activity)
                .improvements(request.getImprovements())
                .suggestions(request.getSuggestions())
                .safety(request.getSafety())
                .build();

        Recommendation savedRecommendation=recommendationRepository.save(recommendation);

        return savedRecommendation;
    }

    public RecommendationResponse mapToResponse(Recommendation recommendation){
        RecommendationResponse response=new RecommendationResponse();
        response.setId(recommendation.getId());
        response.setActivityId(recommendation.getActivity().getId());
        response.setUserId(recommendation.getUser().getId());
        response.setType(recommendation.getType());
        response.setRecommendation(recommendation.getRecommendation());
        response.setImprovements(recommendation.getImprovements());
        response.setSuggestions(recommendation.getSuggestions());
        response.setSafety(recommendation.getSafety());
        response.setCreatedAt(recommendation.getCreatedAt());
        response.setUpdatedAt(recommendation.getUpdatedAt());

        return response;
    }

    public List<RecommendationResponse> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<Recommendation> getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId);

    }
}
