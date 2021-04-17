package itis.socialtest;


import itis.socialtest.entities.Author;
import itis.socialtest.entities.Post;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/*
 * В папке resources находятся два .csv файла.
 * Один содержит данные о постах в соцсети в следующем формате: Id автора, число лайков, дата, текст
 * Второй содержит данные о пользователях  - id, никнейм и дату рождения
 *
 * Напишите код, который превратит содержимое файлов в обьекты в package "entities"
 * и осуществите над ними следующие опреации:
 *
 * 1. Выведите в консоль все посты в читабельном виде, с информацией об авторе.
 * 2. Выведите все посты за сегодняшнюю дату
 * 3. Выведите все посты автора с ником "varlamov"
 * 4. Проверьте, содержит ли текст хотя бы одного поста слово "Россия"
 * 5. Выведите никнейм самого популярного автора (определять по сумме лайков на всех постах)
 *
 * Для выполнения заданий 2-5 используйте методы класса AnalyticsServiceImpl (которые вам нужно реализовать).
 *
 * Требования к реализации: все методы в AnalyticsService должны быть реализованы с использованием StreamApi.
 * Использование обычных циклов и дополнительных переменных приведет к снижению баллов, но допустимо.
 * Парсинг файлов и реализация методов оцениваются ОТДЕЛЬНО
 *
 *
 * */

public class MainClass {

    private List<Post> allPosts;

    private AnalyticsService analyticsService = new AnalyticsServiceImpl();

    private Author findAuthorById(ArrayList<Author> athrs, Long id){
        for(Author elem : athrs){
            if(elem.getId() == id){
                return elem;
            }
        }

        return null;
    }

    public static void main(String[] args){
        new MainClass().run("src\\itis\\socialtest\\resources\\PostDatabase.csv",
                "src\\itis\\socialtest\\resources\\Authors.csv");
    }

    private void run(String postsSourcePath, String authorsSourcePath){
        ArrayList<Author> authorsInList = new ArrayList<>();
        ArrayList<Post> postsInList = new ArrayList<>();

        try{
            File posts  = new File(postsSourcePath);
            File authors = new File(authorsSourcePath);

            FileReader fileReaderPosts = new FileReader(posts);
            FileReader fileReaderAuthors = new FileReader(authors);

            BufferedReader bufferedReaderPosts = new BufferedReader(fileReaderPosts);
            BufferedReader bufferedReaderAuthors = new BufferedReader(fileReaderAuthors);

            String string;

            while (true) {
                string = bufferedReaderAuthors.readLine();

                if (string == null) {
                    break;
                }

                String[] splittedString = string.split(", ");

                authorsInList.add(new Author(Long.parseLong(splittedString[0]), splittedString[1], splittedString[2]));
            }

            bufferedReaderAuthors.close();

            while (true) {
                string = bufferedReaderPosts.readLine();

                if (string == null) {
                    break;
                }

                String[] splittedString = string.split(", ");

                postsInList.add(new Post(splittedString[2], splittedString[3], Long.parseLong(splittedString[1]),
                        findAuthorById(authorsInList, Long.parseLong(splittedString[0]))));
            }

            bufferedReaderPosts.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        for(Post post : postsInList){
            System.out.println(post.getContent());
            System.out.println(post.getLikesCount() + " " + post.getDate());
            System.out.println(post.getAuthor().getNickname() + " " + post.getAuthor().getId() + " " +
                    post.getAuthor().getBirthdayDate());
            System.out.println();
        }

        System.out.println("/ / / / / / /");

        ArrayList<Post> postsByDate = (ArrayList<Post>) analyticsService.findPostsByDate(postsInList, "17.04.2021T10:00");

        for(Post post : postsByDate){
            System.out.println(post.getContent());
            System.out.println(post.getLikesCount() + " " + post.getDate());
            System.out.println(post.getAuthor().getNickname() + " " + post.getAuthor().getId() + " " +
                    post.getAuthor().getBirthdayDate());
            System.out.println();
        }

        System.out.println("/ / / / / / /");

        ArrayList<Post> postsByNick = (ArrayList<Post>) analyticsService.findAllPostsByAuthorNickname(postsInList, "varlamov");

        for(Post post : postsByNick){
            System.out.println(post.getContent());
            System.out.println(post.getLikesCount() + " " + post.getDate());
            System.out.println(post.getAuthor().getNickname() + " " + post.getAuthor().getId() + " " +
                    post.getAuthor().getBirthdayDate());
            System.out.println();
        }

        System.out.println("/ / / / / / /");

        System.out.println(analyticsService.checkPostsThatContainsSearchString(postsInList, "Россия"));

        System.out.println(analyticsService.findMostPopularAuthorNickname(postsInList));
    }
}

