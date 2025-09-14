package com.example;

import java.io.InputStream;

// import org.apache.ibatis.io.Resources;
// import org.apache.ibatis.session.SqlSession;
// import org.apache.ibatis.session.SqlSessionFactory;
// import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// import NEW.NEW;

// public class Main {
//     public static void main(String[] args) throws Exception {
//         String resource = "mybatis-config.xml";
//         InputStream inputStream = Resources.getResourceAsStream(resource);
//         SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        
//         try (SqlSession session = sqlSessionFactory.openSession()) {
//             NEW newCol = session.selectOne("NEWTABLE.selectNEWTABLE");
//             System.out.println(newCol.getCOLUMN5());
//         } catch (Exception e){
//             System.out.println(e);
//         }
//     }
// }

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// ... (MyBatis 설정 동일)

public class Main {
    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        ExecutorService executor = Executors.newSingleThreadExecutor();

        CompletableFuture<NEW> future = CompletableFuture.supplyAsync(() -> {
            try (SqlSession session = sqlSessionFactory.openSession()) {
                return session.selectOne("NEWTABLE.selectNEWTABLE");
            }
        }, executor);

        // 비동기 결과 받기 (콜백 방식)
        future.thenAccept(newCol -> {
            System.out.println("비동기 결과: " + newCol.getCOLUMN5());
        });

        // 혹은 결과가 꼭 필요하다면 future.get() 사용 가능 (이때는 다시 동기화됨)
        // NEW newCol = future.get();

        executor.shutdown();
    }
}
