package com.liaody.ssl.base.java8;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * 流操作
 *
 * @author yuanhaha
 */
@Slf4j
public class StreamTest {

    public static void main(String[] args) throws IOException, IllegalAccessException {
        testOptional();
    }

    /**
     * 使用Files工具类读取文件
     *
     * @throws IOException
     */
    private static void readFile() throws IOException {
        // 读取小文件，大文件会爆掉内存
        String contents = new String(
                Files.readAllBytes(Paths.get("C:\\Users\\yuanhaha\\Desktop\\iptables.txt")),
                StandardCharsets.UTF_8);
        List<String> worlds = Arrays.asList(contents.split("\\PL+"));
        long count = worlds.stream().filter(data -> data.length() > 10).count();
        log.info("wolrds count:{}", count);
    }

    /**
     * 读取超大文件
     *
     * @throws IOException
     */
    public static void readBigFile() throws IOException {

        File file = new File("D:\\key.txt");
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        // 用5M的缓冲读取文本文件
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8), 100 * 1024 * 1024);
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    public static void testStream() {
        // 生成流
        Stream<String> stream1 = Stream.of("aa", "bb", "cc");
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");
        // 测试 Map，并使用limit截断流（抽取子流），skip(n)，丢弃前面n个流.(下面语句输出BB)
        /** peek方法会产生另一个流，它的元素与原来流中的元素相同。但是在每一次获取流的时候
         都会调用一个函数，peek接收一个没有返回值的λ表达式，可以做一些输出，外部处理等。
         map接收一个有返回值的λ表达式，之后Stream的泛型类型将转换为map参数λ表达式返回的类型
         仅在对流内元素进行操作时，peek才会被调用。该方法主要用于调试，方便debug查看Stream内进行处理的每个元素。
         */
        stream1.peek(System.out::println).limit(2).skip(1).map(String::toUpperCase).forEach(System.out::println);
//        stream2.map(String::toUpperCase).peek(System.out::println).forEach(System.out::println);
        // 测试filter
    }

    public static void testOptional() throws IllegalAccessException {
        // 参数如果传入null，会抛出空指针异常，可以使用ofNullable(null)，这时候会返回一个空的Optional
        Optional<String> key = Optional.of("Test-Key");
        Optional<Object> nullKey = Optional.ofNullable(null);
        /** orElseGet与orElse方法类似，区别在于得到的默认值。
         orElse方法将传入的字符串作为默认值，orElseGet方法可以接受Supplier接口的实现用来生成默认值.
         orElseThrow 抛出一个异常
         */
        System.out.println(nullKey.orElse("key is null"));
        System.out.println(nullKey.orElseGet(()->"return null key"));
        //System.out.println(nullKey.orElseThrow(IllegalAccessException::new));

        Stream<String> stream1 = Stream.of("aa", "bb", "cc");
        Optional<String> optional = key.map(String::toUpperCase);

        /**
         * map和reduce
         * map用来归类，结果一般是一组数据，比如可以将list中的学生分数映射到一个新的stream中。
         * reduce用来计算值，结果是一个值，比如计算最高分。
         */
        /**
         * 第一种变形：BinaryOperator接口，可以看到reduce方法接受一个函数，
         * 这个函数有两个参数，第一个参数是上次函数执行的返回值（也称为中间结果），
         * 第二个参数是stream中的元素。
         */
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
        Optional<Integer> reduce = integerStream.reduce((acc, item) -> {
            System.out.println("acc:" + acc);
            acc += item;
            System.out.println("item:" + item);
            System.out.println("acc+:" + acc);
            System.out.println("--------");
            return acc;
        });
        System.out.println("accResult: " + reduce.get());

        /**
         * 第二个变形，与第一种变形相同的是都会接受一个BinaryOperator函数接口，
         * 不同的是其会接受一个identity参数，用来指定Stream循环的初始值。如果Stream为空，就直接返回该值。
         * 另一方面，该方法不会返回Optional，因为该方法不会出现null。
         */
        int accResult = Stream.of(1, 2, 3, 4)
                .reduce(0, (acc, item) -> {
                    System.out.println("acc : "  + acc);
                    acc += item;
                    System.out.println("item: " + item);
                    System.out.println("acc+ : "  + acc);
                    System.out.println("--------");
                    return acc;
                });
        System.out.println("accResult: " + accResult);
        System.out.println("--------");
        /**
         * 变形1，未定义初始值，从而第一次执行的时候第一个参数的值是Stream的第一个元素，第二个参数是Stream的第二个元素
         * 变形2，定义了初始值，从而第一次执行的时候第一个参数的值是初始值，第二个参数是Stream的第一个元素
         */
        /**
         * 因为reduce的变形的第一个参数类型是实际返回实例的数据类型，
         * 同时其为一个泛型也就是意味着该变形的可以返回任意类型的数据。
         * 从上面文档介绍的字面意思解读是第三个参数函数用来组合两个值，
         * 而这两个值必须与第二个函数参数相兼容，也就是说它们所得的结果是一样的
         * 。看到这里肯定有迷惑的地方，第三个参数到底是用来干嘛的？
         * 我们先看一段代码，为了便于了解其中的缘由，并没有使用Lambda表达式，
         */
        ArrayList<Integer> accResult_ = Stream.of(1, 2, 3, 4)
                .reduce(new ArrayList<Integer>(),
                        new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, Integer item) {

                                acc.add(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("BiFunction");
                                return acc;
                            }
                        }, new BinaryOperator<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, ArrayList<Integer> item) {
                                System.out.println("BinaryOperator");
                                acc.addAll(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("--------");
                                return acc;
                            }
                        });
        System.out.println("accResult_: " + accResult_);
        /**
         * 这里找到了答案，Stream是支持并发操作的，为了避免竞争，对于reduce线程都会有独立的result，
         * combiner的作用在于合并每个线程的result得到最终结果。这也说明了了第三个函数参数的数据类型必须为返回数据类型了。
         *
         *
         * 需要注意的是，因为第三个参数用来处理并发操作，如何处理数据的重复性，应多做考虑，否则会出现重复数据！
         */
        Map<String,Integer> mapInt = new HashMap<>(16);
        mapInt.put("key1",1);
        mapInt.put("key2",2);
        mapInt.put("key3",3);
        mapInt.put("key4",4);
        mapInt.put("key5",5);
       //  mapInt.computeIfPresent();
    }

}
