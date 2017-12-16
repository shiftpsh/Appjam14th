package kr.laptop.school.petitions.datas.comparators;

import java.util.Comparator;

import kr.laptop.school.petitions.datas.Article;

public class ArticlesOldestComparator implements Comparator<Article> {

    @Override
    public int compare(Article o1, Article o2) {
        if (o1.getOpenedDate() > o2.getOpenedDate()) {
            return 1;
        } else if (o1.getOpenedDate() < o2.getOpenedDate()) {
            return -1;
        } else return 0;
    }
}
