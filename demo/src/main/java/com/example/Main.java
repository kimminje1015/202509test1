package com.example;

public class Main {
    public static void main(String[] args) throws Exception {
        
        connect dbSql = new connect();
        // ExecutorService executor = Executors.newSingleThreadExecutor();

        // CompletableFuture<NEW> future = CompletableFuture.supplyAsync(() -> {
        //     try (SqlSession session = sqlSessionFactory.openSession()) {
        //         return session.selectOne("NEWTABLE.selectNEWTABLE");
        //     }
        // }, executor);

        // 비동기 결과 받기 (콜백 방식)
        // future.thenAccept(newCol -> {
        //     System.out.println("비동기 결과: " + newCol.getCOLUMN5());
        // });

        // 혹은 결과가 꼭 필요하다면 future.get() 사용 가능 (이때는 다시 동기화됨)
        // NEW newCol = future.get();
    }
}
