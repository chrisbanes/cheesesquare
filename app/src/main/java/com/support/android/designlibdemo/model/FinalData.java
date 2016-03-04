package com.support.android.designlibdemo.model;

import com.support.android.designlibdemo.R;

import java.util.Arrays;

public class FinalData {

    static Question o1q1 = new Question("Желите ли да сазнате да ли грешке младих возача имају заједничке особине?", "Доказано је да саобраћајне незгоде младих возача имају неке заједничке особине.");
    static Question o1q2 = new Question("Желите ли да сазнате разлику између ризичног понашања и несвесне вожње?", ". . . ");

    static CategoryObject o1 = new CategoryObject("Грешке младих возача", "Непажљива вожња . . .",R.drawable.cheese_1, Arrays.asList(o1q1, o1q2), "http://www.nesto.com");
    static CategoryObject o2 = new CategoryObject("Особине, личности и понашање", "Непажљива вожња . . .",R.drawable.cheese_1, Arrays.asList(o1q1, o1q2), "http://www.nesto.com");
    static CategoryObject o3 = new CategoryObject("Нова категоризација", "Непажљива вожња . . .",R.drawable.cheese_1, Arrays.asList(o1q1, o1q2), "http://www.nesto.com");
//    static CategoryObject o4 = new CategoryObject(R.drawable.cheese_4, R.drawable.cheese_4, "Budjavi sir", "desc");
//    static CategoryObject o5 = new CategoryObject(0, R.drawable.cheese_5, "Mocarela", "To je jedan opak sir");

    static Category oneCat1 = new Category("За младе возаче", "desc", Arrays.asList(o1, o2, o3), Arrays.asList(o1q1, o1q2));
    static Category oneCat2 = new Category("Безбедно понашање", "desc", Arrays.asList(o1), Arrays.asList(o1q1, o1q2));
    static Category oneCat3 = new Category("Знакови", "desc", Arrays.asList(o2, o3), Arrays.asList(o1q1, o1q2));
    static Category oneCat4 = new Category("Упитник", "desc", Arrays.asList(o2, o3), Arrays.asList(o1q1, o1q2));

    public static final CategoryContainer one = new CategoryContainer("Mladi u saobracaju", Arrays.asList(oneCat1, oneCat2, oneCat3, oneCat4));

    static Category twoCat1 = new Category("Обавезно осигурање путника", "desc", Arrays.asList(o1, o2, o3), Arrays.asList(o1q1, o1q2));
    static Category twoCat2 = new Category("Кривичноправна одговорност", "desc", Arrays.asList(o1), Arrays.asList(o1q1, o1q2));
    static Category twoCat3 = new Category("Неке од казни", "desc", Arrays.asList(o2, o3), Arrays.asList(o1q1, o1q2));
    static Category twoCat4 = new Category("Накнада штете", "desc", Arrays.asList(o2, o3), Arrays.asList(o1q1, o1q2));
    static Category twoCat5 = new Category("Како наплатити штету", "desc", Arrays.asList(o2, o3), Arrays.asList(o1q1, o1q2));

    public static final CategoryContainer two = new CategoryContainer("Prekrsaji", Arrays.asList(twoCat1, twoCat2, twoCat3, twoCat4, twoCat5));

    static Category threeCat1 = new Category("Безбедност деце", "desc", Arrays.asList(o1, o2, o3), Arrays.asList(o1q1, o1q2));
    static Category threeCat2 = new Category("Бициклизам", "desc", Arrays.asList(o1), Arrays.asList(o1q1, o1q2));
    static Category threeCat3 = new Category("Анализе", "desc", Arrays.asList(o2, o3), Arrays.asList(o1q1, o1q2));
    static Category threeCat4 = new Category("Занимљивости", "desc", Arrays.asList(o2, o3), Arrays.asList(o1q1, o1q2));
    static Category threeCat5 = new Category("Догађаји", "desc", Arrays.asList(o2, o3), Arrays.asList(o1q1, o1q2));

    public static final CategoryContainer three = new CategoryContainer("Aktivizam", Arrays.asList(threeCat1, threeCat2, threeCat3, threeCat4, threeCat5));

}
