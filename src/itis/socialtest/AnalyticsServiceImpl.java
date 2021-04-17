package itis.socialtest;

import itis.socialtest.entities.Author;
import itis.socialtest.entities.Post;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyticsServiceImpl implements AnalyticsService {
    @Override
    public List<Post> findPostsByDate(List<Post> posts, String date) {
        return posts.stream()
                .filter(p -> p.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public String findMostPopularAuthorNickname(List<Post> posts) {
        Map<Long, Author> result = posts.stream()
                .collect(Collectors.toMap(p -> posts.stream()
                .filter(q -> q.getAuthor().equals(p.getAuthor()))
                .mapToLong(q -> q.getLikesCount())
                .sum(), p -> p.getAuthor()));

        ArrayList<Long> sortedKeySet = new ArrayList<>();
        sortedKeySet.addAll(result.keySet());
        Collections.sort(sortedKeySet);

        return result.get(sortedKeySet.get(0)).getNickname();
    }

    @Override
    public Boolean checkPostsThatContainsSearchString(List<Post> posts, String searchString) {
        return posts.stream().
                noneMatch(p -> p.getContent().toLowerCase().contains(searchString.toLowerCase()));
    }

    @Override
    public List<Post> findAllPostsByAuthorNickname(List<Post> posts, String nick){
        return posts.stream()
                .filter(p -> p.getAuthor().getNickname().equals(nick))
                .collect(Collectors.toList());
    }
}
